package molekuly;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Iterator;

import rup.cesky.tvary.Kruh;
import rup.cesky.tvary.SpravcePlatna;


/*******************************************************************************
 * Trida Molekula_13c definuje sadu metod, ktere vygeneruji na platno
 * skupiny nahodne rozmistenych molekul, jez pak rozpohybuji simulujice
 * Brownuv pohyb molekul.
 * Tato verze ma v prostoru molekul umistenu "vylevku",
 * ktera vysaje kazdou molekulu, ktera se cele dostane do jejiho dosahu.
 *
 * Zmeny oproti tride Molekula_13b:
 * - atribut PRUMER zmenil svuj pristup z private na implicitni
 * - trida AnimatorMolekul_13c nyni simuluje vedle pohybu i cinost vylevky,
 *   ktera z platna odstrani molekuly, jez se dostaly do jeji oblasti
 *   (podrobnosti viz dokumentace tridy) - podrobnosti v jejim
 *   dokumentacnim komentari
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Molekula_16d
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Frekvence prekreslovani planta s molekulami. */
    static final int FREKVENCE = 50;

    /** MAximalni instanci pokusu venovanych na umisteni dalsi moelkuly. */
    private static final int POKUSU = 3;

    /** Spolecny prumer vsech moelkul. */
    static final int PRUMER = 50;

    /** Maximalni povolena rychlost molekuly,
     * tj. kolik bodu muze molekula urazit za sekundu. */
    private static int maxR = 300 ;

    /** Mnozina vsech dosud vygenerovanych molekul.
     *  Atribut neni private aby k nemu mohl pristupovat animator. */
    private static final Set<Molekula_16d> molekuly =
                                       new LinkedHashSet<Molekula_16d>();

    /** Spolecny generator nahodnych cisel. */
    private static final Random rnd = new Random();

    /** Aktivni platno, ktere dohlizi na spravne vykresleni instance. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int instanci = 0;

    /** Maximalni povolene souradnice molekuly. */
    private static int maxX;
    private static int maxY;

    static { nastavMaxima(); }



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++instanci;
    
    /** Kruh predstavujici molekulu na platne. */
    private final Kruh kruh;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================

    /** Slozky aktualni pozice molekuly. */
    private double x, y;

    /** Slozky aktualni rychlosti molekuly. */
    private double rx, ry;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Nastavi maximalni povolene souradnice molekul
     * podle aktualni velikosti aktivniho platna.
     */
    public static void nastavMaxima()
    {
        maxX = SP.getBsirka() - PRUMER;
        maxY = SP.getBVyska() - PRUMER;
    }


    /***************************************************************************
     * Vytvori sadu nahodne umistenych molekul. Generaci ukonci pote,
     * so se po zadanem poctu pokusu nepodari najit umisteni pro dalsi molekulu.
     *
     * @param molekul   Pozadovany instanci vytvorenych molekul
     * @param pokusu    Maximalne povoleny instanci pokusu pri hledani
     *                  pozice vytvarene molekuly
     * @return Pocet vytvorenych instanci
     */
    public static int novaSada( int molekul, int pokusu )
    {
        int pocet = 0;
        molekuly.clear();
        do{
            if( pocet >= molekul ) {
                return pocet;
            }
            System.out.println("Generace " + ++pocet + ". molekuly:");
        }while( dalsi( pokusu ) );    //Dokud se dari molekuly generovat
        System.out.println("Vygenerovali jsme " + (pocet-1) + " molekul");
        return pocet - 1;
    }



    /***************************************************************************
     * Najde nahodne umisteni dalsi molekuly tak, aby se neprekryvala s zadnou
     * z dosud vytvorenych molekul, a necha molekulu na teto pozici vytvorit.
     * Nepodari-li se to do zadaneho maximalniho poctu pokusu,
     * vzda dalsi snazeni a vrati false. Jinak vrati true.
     *
     * @param pokusu    Maximalne povoleny instanci pokusu pri hledani
     *                  pozice vytvarene molekuly
     *
     * @return Priznak uspesnosti: true = podarilo se vytvorit dalsi molekulu
     */
    public static boolean dalsi( int pokusu )
    {
        int x, y;         //Navrhovane souradnice nove molekuly
        int K_PROVERENI = molekuly.size();  //Pocet dosud vytvorenych
        int provereno;
        int pokus = 0;
        do{
            if( pokus >= pokusu ) {
                return false; //Na dany instanci pokusu se generace nezdarila
            }

            //Generace navrhu nove pozice
            x = rnd.nextInt( maxX );
            y = rnd.nextInt( maxY );
            System.out.print( "  " + ++pokus + ". pokus: x=" + x +
                                                      ", y=" + y );
            //Otestovani pripadnych kolizi
            provereno = 0;
            //Projdeme mnozinu a otestujeme molekulu za molekulou
            for( Molekula_16d m : molekuly )
            {
                if( PRUMER > Math.hypot( x - m.x,  y - m.y ) )
                {
                    System.out.println(" koliduje s " + m.poradi +
                                       ". molekulou" );
                    break;
                }
                provereno++;    //Dalsi byla proverena
            }
        //Nebyly-li provereny vsechny, je treba zkusit znovu
        }while( provereno < K_PROVERENI );
        System.out.println( " - USPESNY" );

        Molekula_16d m = new Molekula_16d( x, y );
        return true;
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Na pozici o zadanych souradnicich vytvori molekulu s nahodnou rychlosti
     * a necha ji zobrazit na aktivnim platne.
     * 
     * @param x   x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y   y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     */
    public Molekula_16d(int x, int y)
    {
        kruh = new Kruh( x, y, PRUMER );
        this.x = x;
        this.y = y;
        this.rx = (rnd.nextDouble() - 0.5) * 2 * maxR / FREKVENCE;
        this.ry = (rnd.nextDouble() - 0.5) * 2 * maxR / FREKVENCE;
        molekuly.add( this );      //Pridej molekulu do mnoziny vygenerovanych
        SP.pridej( kruh );         //a nech ji zobrazit na aktivni platno
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    private final String ps = ((poradi < 10) ? " " : "" ) + instanci;

    /***************************************************************************
     * Posune molekulu do nove pozice definovane jeji soucasnou pozici
     * a snimkovou rychlosti. V nove pozici zkontroluje, jestli molekula
     * nenarazila do okraje platna ci nektere jine molekuly. Pokud ano,
     * tak posun zrusi a upravi rychlost zucastnenych tak, aby similovaly
     * dokonaly odraz.
     */
    public void popojed()
    {
        double xn = x + rx;     //Planovana nova vodorovna souradnice
        double yn = y + ry;     //Planovana nova svisla souradnice
        boolean odraz = false;  //Predpokladame, ze se neodrazi

        //Test odrazu od zdi
        if( (xn <= 0) || (maxX <= xn) )   {  rx =-rx;  odraz = true; }
        if( (yn <= 0) || (maxY <= yn) )   {  ry =-ry;  odraz = true; }
        if( x<=0  || y<=0  ) {
            System.out.println( "Zed: "+
                "xPos="+ kruh.getX() +", x="+ x +", rx="+ rx +", xn="+ x +
         "\n     yPos="+ kruh.getY() +", y="+ y +", ry="+ ry +", yn="+ y );
        }

        //Testy moznych odrazu od ostatnich molekul
        for( Molekula_16d m : molekuly )
        {
            if( (m != this)  &&  //Sama od sebe se neodrazi
                (Math.hypot( xn - m.x,  yn - m.y )  <  PRUMER) )
            {
                double p;   //Molekuly si prohodi rychlosti
                p = rx;   rx = m.rx;   m.rx = p;
                p = ry;   ry = m.ry;   m.ry = p;
                if( x<0  || y<0  ) {
                    System.out.println( "Mol."+ ps +": "+
                        "xPos="+ kruh.getX() +", x="+ x +", rx="+ rx +", xn="+ xn +
              "\n        yPos="+ kruh.getY() +", y="+ y +", ry="+ ry +", yn="+ yn +
              "\n    "+ m.ps +": "+
                        "xPos="+ m.kruh.getX() +", rx="+ m.rx +", x="+ m.x +
              "\n        yPos="+ m.kruh.getY() +", ry="+ m.ry +", y="+ m.y +
                         "");
                }
                odraz = true;
            }
        }
        //Pokud se molekula odrazila, jeji pozice se nezmeni
        //(spokojime se pouze s nastavenou zmenou rchlosti)
        if( !odraz ) {
            kruh.setPozice((int) (x = xn), (int) (y = yn));
        }
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================

    /***************************************************************************
     * Trida Animator definuje objekt, ktery zabezpeci
     * animaci molekul ulozenych ve statickem atributu <code>molekuly</code>.
     * Animator by mel spravne byt bud jedinacek, nebo alespon kontrolovat,
     * ze se druhy nespusti drive, nez prvni dobehne.
     * Vzhledem k tomu, ze je urcen pouze pro testovaci ucely a bude jej
     * pouzivat pouze jeho vnejsi trida, dopustime se maleho hrichu
     * a tyto kontroly do kodu nezahrneme.
     *
     * Oproti verzi {@code Molekula_16b.Animator} doslo k temot zmenam:
     * - Trida nyni simuluje vedle pohybu molekul i cinost vylevky,
     *   ktera z platna odstrani molekuly, jez se dostaly do jeji oblasti.
     * - Pribyly konstanty POMER, V_POZICE, V_ROZMER
     *   definujici nektere parametry vylevky
     * - Konstruktor umisti do leveho horniho rohu platna velky cerny kruh
     *   predstavujici vylevku.
     * - Metoda run() odstrani z platna instanci,
     *   ktera se nachazi cela v oblasti vylevky.
     *
     * @author    Rudolf Pecinovsky
     * @version   0.00.000,  0.0.2003
     */
    private static final class Animator extends TimerTask
    {
    //== KONSTANTNI ATRIBUTY TRIDY =============================================
        /** O jaky dil prumeru molekuly budou vylevka a generator vetsi. */
        private static final double POMER = 0.3;

        /** Minimalni souradnice molekuly vycucle vyvevou. */
        private static final int V_POZICE = (int)(PRUMER * POMER);

        /** Rozmer vyvevy. */
        private static final int V_ROZMER = (int)(PRUMER * (1+POMER));


    //##########################################################################
    //== KONSTRUKTORY A TOVARNI METODY =========================================

        /***********************************************************************
         * Vytvori novy animator molekul, ktery bude s frekvenci zadanou ve tride
         * {@code Molekula_16d} zadat moelkuly o aktulizaci jejich pozice
         * a nasledne nechavat prekreslit platno.
         * Soucasti kontrukce animatoru je i nakresleni jimky
         * v levem hornim rohu aktivniho platna.
         */
        public Animator()
        {
            SP.pridejDospod( new Kruh( 0, 0, V_ROZMER,
                                    rup.cesky.tvary.Barva.CERNA ));
            new Timer().schedule( this, 0, 1000 / FREKVENCE );
        }
    
    
    //== OSTATNI NESOUKROME METODY INSTANCI ====================================

        /***********************************************************************
         * Metoda pozadovana tridou TimerTask. Bude spoustena casovacem
         * s pozadovanou frekvenci. Tato metoda ma na starosti vlastni animaci
         * vcetne "pozirani molekul" jimkou.
         */
        public void run()
        {
            SP.nekresli(); {
                for( Iterator it = molekuly.iterator();
                     it.hasNext();  )
                {
                    Molekula_16d m = (Molekula_16d)(it.next());
                    if( (m.x <= V_POZICE)  &&  (m.y <= V_POZICE) )
                    {
                        it.remove();
                        SP.odstran( m.kruh );
                    }
                    else
                    {
                        m.popojed();
                    }
                }
            } SP.vratKresli();
        }
    }



//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci program - vygeneruje novou sadu molekul a spusti animaci
     * jejich pohybu.
     */
    public static void test()
    {
        novaSada( 20, 20 );    //Zadavam instanci molekul
        new Animator();
    }


}//public class Molekula_13c extends Kruh



