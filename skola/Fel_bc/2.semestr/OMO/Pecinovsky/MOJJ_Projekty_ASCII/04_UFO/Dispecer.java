import java.awt.geom.Ellipse2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.lang.reflect.Constructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/*******************************************************************************
 * Trida Dispecer ma na starosti vedeni hry,
 * pri ktere ma uzivatel dopravit UFO ze startovni rampy do hangaru.
 * <p>
 * V prvni variante uzivatel zadava rychlost UFO primym volanim metody
 * setRychlost(double,double).
 * <p>
 * Pri druhe varianty hry, uzivatel zadava tah motoru a tim i zrychleni UFO
 * prostrednictvim kurzorovych klaves a mezerniku, kterym motory vypina.
 * Stiskem ciselne klavesy muze prepinat ktere UFO zrovna ovlada.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class Dispecer
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Frekvence snimkovani = pocet prekresleni Vesmiru za vterinu. */
    public static final int FREKVENCE = 8;

    /** Odkaz na okno, ve kterem se vsechno kresli. */
    protected static final Vesmir V = Vesmir.getVesmir();

    /** Nahrada za prepisovani Talir.VELIKOST pro lenochy. */
    protected static final int TV = Talir.VELIKOST;

    /** Nejmensi povolena velikost prekazejici planety. */
    private static final int NEJMENSI = TV;

    /** Nejvetsi povolena velikost prekazejici planety. */
    private static final int NEJVETSI = NEJMENSI * 4;

    /** Vektor s moznymi barvami planet. Prvni tri ale nebudeme pouzivat.
     *  Cerna [0] je barva vesmiru, takze by nebyla videt,
     *  Modra [1] bude barva startovni rampy a hangaru a
     *  Cervena [2] bude barva letajicich taliru. */
    private static final Barva[] barvyPlanet;
    public  static final Barva   barvaVesmiru;
    public  static final Barva   barvaRampy;
    public  static final Barva   barvaUFO;
            static
    {
        Barva[] b    = Barva.getZnameBarvy();
        barvaVesmiru = b[0];
        barvaRampy   = b[1];
        barvaUFO     = b[2];
        barvyPlanet  = new Barva[ b.length-3 ];
        System.arraycopy( b, 3, barvyPlanet, 0, barvyPlanet.length );
        b = null;
    }



//== PROMENNE ATRIBUTY TRIDY ===================================================

    private static Dispecer dispecer;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final int HANGARU;

    /** Startovaci rampa (nulta polozka) a pristavaci rampy = hangary. */
    private final Rampa[]   rampa;

    /** Planety, ktere maji za ukol prekazet v rychlem dosazeni cile. */
    private final Planeta[] planeta;

    /** Seznam doposud vygenerovanych UFO. */
    private final List<UFO> ufoList = new ArrayList<UFO>();

    /** Casovac, ktery zabezpeci pravidelne, opakovane vyvolani metody
     *  run() pusteneho filmu. */
    private final Timer timer = new Timer("Dispecer UFO");

    /** Indikuje, zda je v provozu verze ovladane z klavesnice
     *  nebo verze ovladana primym volanim metody setRychlost(int,int) */
    private final boolean zKlavesnice;

    /** Svisla souradnice hangaru. */
    private final int yHangaru;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    /** Aktualni pocet vygenerovanych UFO. */
    protected int pocetUFO = 0;

    /** Objekt, ktery ma na starosti korektni zobrazovani pohybujicich se
     *  objektu. */
    private Film film = null;

    /** Promenna registrujici beh filmu -
     *  zabezpecuje, aby nebylo mozno spustit prave bezici film. */
    private boolean stoji = true;

    /** UFO, ktere je v danou chvili ovladane klavesnici. */
    private UFO ovladane;

    /** Posluchac reagtujici na stisky klaves. */
    private Posluchac posluchac;

    /** Trida, jejiz instance budou vytvareny jako UFO. */
    private Class<? extends UFO> ufoClass = UFO.class;

    /** Konstruktor, kterym budou vytvarena nova UFO. */
    private Constructor<? extends UFO> ufoKonstruktor;
    {
        try
        {
            ufoKonstruktor = ufoClass.getConstructor(
                new Class[] { Talir.class, int.class } );
        }
        catch( Exception e )
        {
            throw new RuntimeException(
                "Nepodarilo se vytvorit konsturktor tridy " + ufoClass, e );
        }
    }



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Je-li jiz vytvoren vesmir s dispecerem, vyvola vyjimku,
     * neni-li jeste vytvoren vesmir s dispecerem, vytvori vesmir,
     * v nemz se budou pohybovat UFO a u horniho okraje platna pro ne
     * pripravi startovaci rampu a u dolniho okraje zadany pocet hangaru,
     * zaroven nastavi, bude-li hra ovladana z klavesnice.
     *
     * @param hangaru      Pozadovany pocet hangaru.
     * @param zKlavesnice  true = hra bude ovladana z klavesnice
     * @return Odkaz na dispecera
     */
    static Dispecer getDispecer( int hangaru, boolean zKlavesnice )
    {
        if( dispecer != null ) {
            throw new IllegalStateException(
                "Muze byt vytvoren pouze jeden dispecer." );
        }
        dispecer = new Dispecer( hangaru, 0, zKlavesnice );
        return dispecer;
    }


    /***************************************************************************
     * Je-li jiz vytvoren vesmir s dispecerem, vrati odkaz na dispecera;
     * neni-li jeste vytvoren vesmir s dispecerem, vytvori vesmir,
     * v nemz se budou pohybovat UFO, u horniho okraje platna pro ne
     * pripravi startovaci rampu a u dolniho okraje 5 hangaru
     * a odstartuje hru rizenou pomoci klavesnice.
     * 
     * @return Odkaz na dispecera
     */
    public static Dispecer getDispecer( )
    {
        if( dispecer != null ) {
            return dispecer;
        }
        return getDispecer( 5, true );
    }


    /***************************************************************************
     * Vytvori vesmir, v nemz se budou pohybovat UFO a u horniho okraje platna
     * pro ne pripravi zadany pocet hangaru.
     * Pozaqdovany pocet prekazejicich planet i cilovych hangaru
     * muze byt omezen.
     *
     * @param hangaru  Pozadovany pocet hangaru.
     * @param prekazek Pocet nahodne rozmistenych planet.
     */
    private Dispecer(int hangaru, int planet, boolean zKlavesnice)
    {
        this.zKlavesnice = zKlavesnice;

        HANGARU = hangaru;
        int rozmer = 3*HANGARU*TV;
        V.setRozmer( rozmer, rozmer );

        rampa   = new Rampa[HANGARU+1];
        planeta = new Planeta[planet];

        /*# Vytvorit novy vesmir, v jehoz levem hornim rohu bude startovaci
         *  rampa (ctverec), kam budou pristavovana nova ufo, a podel dolniho
         *  okraje bude sada ocislovanych hangaru (opet ctverce), kam budou
         *  ufo pristavat. */
        yHangaru = V.getVyska() - TV;
        rampa[0] = new Rampa( 0, TV, TV );
        for( int h=1,  hx=3*TV/2;
             h <= HANGARU;
             h++, hx+=3*TV )
        {
            rampa[h] = new Rampa( h, hx, yHangaru );
        }

        /*# Vytvorit pozadovany pocet planet, kterym se budou muset ufo
         *  vyhybat. Planety musi byt dostatecne daleko od sebe,
         *  aby mezi nimi mohlo ufo s rezervou proletet (aby mezi planetami
         *  bylo misto alespon pro 2 talire (nezpomente vzit v uvahu velikost
         *  platnet). Nechete-li volit umisteni plaent zcela nahodne,
         *  muzete je mit nekde preddefinovane.
         *  Planety nesmeji byt ve spodnim startovacim pasu ani v hornim pasu
         *  s hangary; mezi planetami a hangary musi zustat manevrovaci prostor.
         */
        for(int i=0; i < planeta.length; i++) {
            planeta[i] = new Planeta(mezi(25, 275), mezi(25, 250));
            planeta[i].nakresli();
        }
        V.prekresli();
        start();
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Nastavi pouzivani UFO, jez budou instancemi tridy UFO.
     */
    public void pouzijUFO()
    {
        pouzijTridu( UFO.class );
    }


    /***************************************************************************
     * Nastavi pouzivani UFO, jez budou instancemi tridy UFO.
     */
    public void pouzijUFO_4()
    {
        pouzijTridu( UFO_4.class );
    }


    /***************************************************************************
     * Nastavi pouzivani UFO, jez budou instacemi tridy zadane v parametru.

     *
     * @param ufoClass  Class objekt tridy, jajiz instance budou
     *                  pouzity jako UFO.
     */
    public void pouzijTridu( Class<? extends UFO> ufoClass )
    {
        if( this.ufoClass == ufoClass ) {
            return;
        }
        try
        {
            ufoKonstruktor = ufoClass.getConstructor(
                new Class[] { Talir.class, int.class } );
        }
        catch( Exception e )
        {
            throw new RuntimeException(
                "Nepodarilo se vytvorit konsturktor tridy " + ufoClass, e );
        }
        if( ! UFO.class.isAssignableFrom( ufoClass ) ) {
            throw new RuntimeException(
                "Trida " + ufoClass + " neni potomkem tridy UFO" );
        }
        this.ufoClass = ufoClass;
    }


    /***************************************************************************
     * Vytvori instanci UFO, umisti ji na startovni rampu
     * v levem dolnim rohu platna zobrazujiciho vesmir a vrati odkaz na ni.
     *
     * @return Odkaz na vytvorene UFO pripravene na startovaci rampe.
     */
    public UFO pristavUFO()
    {
        /*# Pro talir je treba spocitat spravnou pozici na startovni rampe. */
        Talir talir = new Talir( TV, TV );
        try
        {
            //ovladane = new UFO_4a( talir, ++pocetUFO );
            ovladane = (UFO)(ufoKonstruktor.newInstance(
                new Object[] { talir, new Integer( ++pocetUFO ) } ));
        }
        catch( Exception e )
        {
            throw new RuntimeException(
                "Nepodarilo se vytvorit instanci tridy " + ufoClass, e );
        }
        /* Zarazeni do seznamu musi byt synchronizovano, aby k nemu nemohlo
         * dojit uprostred prochazeni seznamem pri prekreslovani vesmiru. */
        synchronized( ufoList )
        {
            ufoList.add( ovladane );
        }
        V.prekresli();
        return ovladane;
    }


    /***************************************************************************
     * Ukonci multipresun a tim uvolni procesor.
     */
    public void stop()
    {
        film.cancel();
        stoji = true;
        if( posluchac != null )
        {
            V.odhlasKlavesnici( posluchac );
            posluchac = null;
        }
    }


    /***************************************************************************
     * Spusti novy multipresun.
     */
    public void start()
    {
        if( stoji )
        {
            //perioda uchovav pocet milisekund mezi dvema snimky
            int perioda = 1000 / FREKVENCE;
            film = new Film();
            //timer.scheduleAtFixedRate(film, perioda, perioda );
            timer.schedule(film, perioda, perioda);
            stoji = false;
            if( zKlavesnice )
            {
                posluchac = new Posluchac();
                V.prihlasKlavesnici( posluchac );
            }
        }
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================

    /** Generator nahodnych cisel slouzi pouze nasledujici metode. */
    private static final java.util.Random rnd = new java.util.Random();

    /***************************************************************************
     * Vrati nahodne cislo v zadanem rozsahu, tj. od spodni meze vcetne
     * do horni meze nevcetne.
     *
     * @param od Dolni mez, tj. nejmensi generovatelne cislo.
     * @param k  Horni mez, tj. nejmensi uz negenerovatelne cislo.
     */
    private static int mezi( int od, int k )
    {
        return od + rnd.nextInt( k-od );
    }


    /***************************************************************************
     * Pomocna metoda pro test chovani klavesnice.
     */
    private static void vypis( KeyEvent e )
    {
        char kch = e.getKeyChar();
        char ch = ((32<=kch)&&(kch<=0xff)) ? kch : '?';
        int kc=e.getKeyCode();
        System.out.println(": znak='" + ch +
          "', kodZnaku=" + (int)kch +
          ", kod=" + kc +
          ", loc=" + e.getKeyLocation() +
          ", txt=" + KeyEvent.getKeyText(kc) );
    }



//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Zkontroluje, je-li UFO v nejakem hangaru a je-li jeho rychlost mensi
     * nez 10, tak je v hangaru vystredi a nastavi jeho rychlost i tah
     * na nulu.
     *
     * @param ufo Kontrolovane UFO.
     */
    private void zkontroluj( UFO ufo )
    {
        //Dostalo se UFO do oblasti hangaru?
        if( Math.abs(ufo.getY() - yHangaru) > TV/2 ) {
            return;
        }
        //Leti dostatecne pomalu?
        if( (Math.abs(ufo.getXRychlost()) + Math.abs(ufo.getYRychlost())) > TV ) {
            return;
        }
        //Naleta-li do nektereho hangaru, pozadej hangar, at je zaparkuje
        double ux = ufo.getX();
        for( int ir=rampa.length;   --ir > 0;   )
        {
            if( Math.abs(rampa[ir].getX() - ux) <= TV/2 )
            {
                rampa[ir].zaparkuj( ufo );
                break;
            }
        }
    }



//== VNORENE A VNITRNI TRIDY ===================================================

    //==========================================================================
    /***************************************************************************
     * Spolecny rodic vsech ctvercu a elips.
     */
    public static abstract class Tvar
    {
        double xPos;    //Bodova x-ova souradnice instance
        double yPos;    //Bodova y-ova souradnice instance
        int    rozmer;  //velikost v bodech
        Barva  barva;   //Barva instance

        Tvar( double x, double y, int rozmer, Barva barva )
        {
            this.xPos   = x;
            this.yPos   = y;
            this.rozmer = rozmer;
            this.barva  = barva;
        }


        /***********************************************************************
         * Nakresli svoji instanci.
         */
        public abstract void nakresli();


        /***********************************************************************
         * Vrati x-ovou souradnici pozice instance.
         *
         * @return  x-ova souradnice.
         */
        public double getX()
        {
            return xPos;
        }


        /***********************************************************************
         * Vrati y-ovou souradnici pozice instance.
         *
         * @return  y-ova souradnice.
         */
        public double getY()
        {
            return yPos;
        }


        /***********************************************************************
         * Nastavi novou pozici instance.
         *
         * @param x   Nova x-ova pozice instance
         * @param y   Nova y-ova pozice instance
         */
        public void setPozice( double x, double y )
        {
            xPos = x;
            yPos = y;
        }


        /***********************************************************************
         * Vrati barvu instance.
         *
         * @return  Instance tridy Barva definujici nastavenou barvu.
         */
        public Barva getBarva()
        {
            return barva;
        }


        /***********************************************************************
         * Nastavi novou barvu instance.
         *
         * @param nova  Pozadovana nova barva.
         */
        public void setBarva(Barva nova)
        {
            barva = nova;
            nakresli();
        }

    }


    //==========================================================================
    /***************************************************************************
     * Ekvivalent tridy Elipsa, pouze s nejnutnejsim poctem metod.
     *
     * @return Nazev tridy nasledovnay podtrzitkem a "rodnym cislem" instance.
     */
    public static class Kruh extends Tvar
    {
        Ellipse2D.Double kruh;

        /***********************************************************************
         * Vytvori instanci o zadanych parametrech.
         */
        Kruh( double x, double y, int rozmer, Barva barva )
        {
            super( x, y, rozmer, barva );
            int r2 = rozmer >> 1;   //Polovicni rozmer
            kruh = new Ellipse2D.Double(
                (int)xPos-r2, (int)yPos-r2, rozmer, rozmer );
        }


        /***********************************************************************
         * Nakresli svoji instanci.
         * Pri kresleni povazuje za svoji pozici stred kruhu.
         */
        public void nakresli()
        {
            V.setBarvaPopredi( barva );
            V.zapln( kruh );
        }


        /***********************************************************************
         * Nakresli svoji instanci.
         * Pri kresleni povazuje za svoji pozici stred kruhu.
         */
        @Override
        public void setPozice( double x, double y )
        {
            super.setPozice( x, y );
            int r2 = rozmer >> 1;   //Polovicni rozmer
            kruh.setFrame( (int)xPos-r2, (int)yPos-r2, rozmer,  rozmer );
        }

    }//static class Kruh extends Tvar



    //==========================================================================
    /***************************************************************************
     * Instance tridy predstavuji planety, kterym se ufo musi vyhybat.
     */
    private static class Planeta extends Kruh
    {
        /***********************************************************************
         * Vytvori planetu na zadanych souradnicich.
         */
        Planeta( int x, int y )
        {
            super( 0, 0, mezi( NEJMENSI, NEJVETSI ),
                   barvyPlanet[ mezi(0,barvyPlanet.length)]  );
            /*# Najit takovou pseudonahodnout pozici,
             *  aby planeta byla od vsech drive vygenerovanych planet
             *  dostatecne daleko - tj. aby mezi planetami bylo misto alespon
             *  pro 2 talire (nezpomente vzit v uvahu velikost platnet).
             *  Nepodari-li se takove misto najit, ohlasi chybu. */
            nakresli();
        }

    }//private static class Planeta extends Kruh



    //==========================================================================
    /***************************************************************************
     * Instance tridy Film jsou ulohy simulujici zivot vesmiru.
     * Film je definvan jako samostatna trida proto, aby jej bylo mozno
     * snadno zapnout a vypnout.
     */
    private class Film extends TimerTask
    {
        /***********************************************************************
         * Metoda vyzadovana rozhranim Runable implementovanym rodicovskou
         * tridou TimerTask - tuto metodu zavola Timer pokazde,
         * kdyz se rozhodne spustit dalsi provedeni opakovaneho ukolu
         * (Timertask) = multipresunu.
         */
        public void run()
        {
            V.smaz();
            for( int i=  rampa.length;  --i >= 0;    rampa[i].nakresli() );
            for( int i=planeta.length;  --i >= 0;  planeta[i].nakresli() );
            //Pri prekreslovani se nesmi menit pocet objektu v seznamu
            synchronized( ufoList )
            {
                for( int i=ufoList.size();      --i >= 0;  )
                {
                    UFO u = ufoList.get( i );
                    u.popojed( FREKVENCE );
                    u.nakresli();
                    zkontroluj( u );
                }
            }//synchronized
            V.prekresli();
       }//public void run()

    }//private class Film extends TimerTask



    //==========================================================================
    /***************************************************************************
     * Instance tridy Posluchac slouzi jako posluchac klavesnice
     * ridici pohyb UFO v druhe variante hry.
     */
    private class Posluchac extends KeyAdapter
    {
        /***********************************************************************
         * Osetreni klavesnice.
         */
        @Override
        public void keyPressed(KeyEvent e)
        {
            //vypis( e );
            int kc = e.getKeyChar();
            if( ('1' <= kc)  &&  (kc <= '9') )
            {
                int iu = kc - '1';  //1. ufo bude v 0. prvku
                if( iu < ufoList.size() ) {
                    ovladane = ufoList.get(iu);
                }
                return;
            }
            kc = e.getKeyCode();
            if( kc == KeyEvent.VK_ENTER )
            {
                pristavUFO();
                return;
            }
            if( ovladane == null ) {
                return;
            }
            switch( kc )
            {
                case KeyEvent.VK_DOWN:
                    ovladane.dolu();
                    return;

                case KeyEvent.VK_LEFT:
                    ovladane.vlevo();
                    return;

                case KeyEvent.VK_RIGHT:
                    ovladane.vpravo();
                    return;

                case KeyEvent.VK_UP:
                    ovladane.vzhuru();
                    return;

                case KeyEvent.VK_SPACE:
                    ovladane.vypniMotory();
                    return;
            }//switch
        }

    }//private class Posluchac extends KeyAdapter



//== TESTY A METODA MAIN =======================================================
}//public class Dispecer



