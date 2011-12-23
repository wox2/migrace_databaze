package doprava;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Cara;
import rup.cesky.tvary.Elipsa;


/*******************************************************************************
 * Instance tridy {@code Linka} predstavuji linku, po ktere se budou pohybovat
 * kabinky, ktere budou jezdit porad kolem dokola.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class Linka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final java.util.Set<Barva> zprovoznene = 
                                            new java.util.HashSet<Barva>();
    
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    private final Barva barva;


//== PROMENNE ATRIBUTY INSTANCI ================================================

    private Zastavka prvni;
    private int      rychlost = 4;
    private boolean  zrusena  = false;
    private int      cekani   = 500;


//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================


    /***************************************************************************
     * Vytvori vnejsi, modrou linku, po niz se budouci kabinky budou pohybovat
     * zadanou rychlosti; ocekava, ze platno ma rozmery alespon 77 poli.
     * 
     * @param rychlost Rychlost pohybu po vytvorene lince
     * @return Pozadovana linka
     */
    public static Linka vytvorVnejsi( int rychlost )
    {
        int sirka = Math.max( SP.getSloupcu() * SP.getKrok(), 350 );
        int vyska = Math.max( SP.getRadku() * SP.getKrok(), 350 );
        SP.setKrokRozmer( 1, sirka, vyska );
        Linka vnejsi  = new Linka( Barva.MODRA, new int[][] 
                      { {25, 25}, {25,275}, {175,325}, {325,275}, {325, 25}});
        vnejsi.setRychlost( rychlost );
        return vnejsi;
    }
        

    /***************************************************************************
     * Vytvori stredni, zelenou linku, po niz se budouci kabinky budou pohybovat
     * zadanou rychlosti; ocekava, ze platno ma rozmery 66 poli.
     * 
     * @param rychlost Rychlost pohybu po vytvorene lince
     * @return Pozadovana linka
     */
    public static Linka vytvorStredni( int rychlost )
    {
        int sirka = Math.max( SP.getSloupcu() * SP.getKrok(), 300 );
        int vyska = Math.max( SP.getRadku() * SP.getKrok(), 300 );
        SP.setKrokRozmer( 1, sirka, vyska );
        Linka stredni = new Linka( Barva.CERVENA, new int[][] 
                      { {75, 75}, {75,225}, {175,275}, {275,225}, {275, 75}});
        stredni.setRychlost( rychlost );
        return stredni;
    }
        
        
    /***************************************************************************
     * Vytvori vnitrni, zelenou linku, po niz se budouci kabinky budou pohybovat
     * zadanou rychlosti; ocekava, ze platno ma rozmery 77 poli.
     * 
     * @param rychlost Rychlost pohybu po vytvorene lince
     * @return Pozadovana linka
     */
    public static Linka vytvorVnitrni( int rychlost )
    {
        int sirka = Math.max( SP.getSloupcu() * SP.getKrok(), 250 );
        int vyska = Math.max( SP.getRadku() * SP.getKrok(), 250 );
        SP.setKrokRozmer( 1, sirka, vyska );
        Linka vnitrni = new Linka( Barva.ZELENA, new int[][] 
                      {{125,125},{125,175}, {175,225}, {225,175}, {225,125}});
        vnitrni.setRychlost( rychlost );
        return vnitrni;
    }
    
    
    
//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor Linky definuje jeji barvu a preda ji vektor souradnic
     * jejich jednotlivych zastavek.
     *
     * @param barva     Barva dane linky. Dve linky nesmi mit stejnou barvu.
     * @param xyStanic  Souradnice jednotlivych stanic na dane lince.
     */
    public Linka( Barva barva, int[][] xyStanic )
    {
        final String dveSouradnice = "Stanice musi mit prave dve souradnice";
        
        if( !zprovoznene.add( barva ) ) {
            throw new IllegalArgumentException(
                    "Zadne dve linky nesmeji mit stejnou barvu.");
        }
        if( xyStanic.length < 2 ) {
            throw new IllegalArgumentException(
                    "Linka musi mit nejmene dve stanice.");
        }
        if( xyStanic[0].length != 2 ) {
            throw new IllegalArgumentException(dveSouradnice);
        }

        this.barva = barva;
        SP.nekresli(); {
            prvni = new Zastavka( this, xyStanic[0][0], xyStanic[0][1] );
            Zastavka dalsi = prvni;
            for( int i=1;   i < xyStanic.length;   i++ )
            {
                if( xyStanic[i].length != 2 ) {
                    throw new IllegalArgumentException(dveSouradnice);
                }
                dalsi = new Zastavka( dalsi, xyStanic[i][0], xyStanic[i][1] );
            }
        } SP.vratKresli();
    }


    /***************************************************************************
     * Konstruktor Linky definuje jeji barvu a preda ji vektor souradnic
     * jejich jednotlivych zastavek.
     *
     * @param barva  Barva dane linky. Dve linky nesmi mit stejnou barvu.
     * @param x1     Vodorovna souradnice prvni zastavky
     * @param y1     Svisla souradnice prvni zastavky
     * @param x2     Vodorovna souradnice druhe zastavky
     * @param y2     Svisla souradnice druhe zastavky
     */
    public Linka( Barva barva, int x1, int y1, int x2, int y2 )
    {
        this( barva, new int[][] { {x1,y1}, {x2,y2} } );
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Vrati odkaz na prvni zastavku na lince.
     * 
     * @return Prvni zastavka na lince.
     */
    public IZastavka getPrvni()
    {
        ziva();
        return prvni;
    }


    /***************************************************************************
     * Vrati odkaz na barvu linky.
     * 
     * @return Barva linky.
     */
    public Barva getBarva()
    {
        ziva();
        return barva;
    }
    
    
    /***************************************************************************
     * Vrati dobu v milisekundach, kterou je kabinkam doporuceno 
     * cekat ve stanici pred tim, nez vyjedou dal.
     * 
     * @return Doporucena doba cekani ve stanici.
     */
    public int getCekani()
    {
        ziva();
        return cekani;
    }
    
    
    /***************************************************************************
     * Nastavi doporucenou dobu cekani kabin ve stanici nez vyjednou dal;
     * doba je uvadena v milisekundach.
     * 
     * @param milisekund Doporucena doba cekani ve stanici
     */
    public void setCekani( int milisekund )
    {
        ziva();
        if( (milisekund < 0) || (1000 < milisekund) ) {
            throw new IllegalArgumentException(
                "Doporucena doba cekani smi byt vozsahu 0-1000 milisekund " +
                "\nZadano: " + milisekund + " milisekund" );
        }
        this.cekani = milisekund;
    }
    
    
    /***************************************************************************
     * Vrati doporucenou rychlost, kterou by se mely kabinky pohybovat 
     * po lince. Pojedou-li vsechny kabinky stejnou rychlosti, nemohou se 
     * nabourat na trati. Budou-li dostatecne daleko za sebou a dodrzi-li
     * doporucenou dobu cekani, nemohou se nabourat ani ve stanici.
     * 
     * @return Doporucena rychlost presunu po lince.
     */
    public int getRychlost()
    {
        ziva();
        return rychlost;
    }

    
    /***************************************************************************
     * Nastavi novou doporucenou rychlost pro danou linku.
     * 
     * @param rychlost Doporucena rychlost presunu po lince.
     */
    public void setRychlost( int rychlost )
    {
        ziva();
        if( rychlost < 1 ) {
            throw new IllegalArgumentException(
                    "Rychlost kabinek na lince musi byt kladna");
        }
        if( rychlost > 200 ) {
            throw new IllegalArgumentException(
                    "Rychlost kabinek na lince smi byt nejvyse 200");
        }
        this.rychlost = rychlost;
    }

    
    /***************************************************************************
     * Vrati informaci o tom, jestli je linka jiz zrusena.
     * 
     * @return  Informace o zruseni linky: true=zrusena, false=ziva.
     */
    public boolean isZrusena()
    {
        return zrusena;
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "_" + barva;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
    
    /***************************************************************************
     * Vlozi za zadanou zastavku novou, a to na definovanou pozici.
     * @param zaMne Zastavka, za niz se vklada
     * @param x     Vodorovna sourcadnice nove zastavky
     * @param y     Svisla sourcadnice nove zastavky
     * @return Pozadovana zastavka
     */
    public IZastavka pridejZa( IZastavka zaMne, int x, int y )
    {
        Zastavka z = (Zastavka)zaMne;
        moje( z );
        return z.napoj( x, y );
    }
    
    
    /***************************************************************************
     * Odstrani zadnou zastavku z linky.
     * @param koho Odstranovana zastavka
     */
    public void odstran( IZastavka koho )
    {
        moje( koho );
        ((Zastavka)koho).zrus();
    }
    
    
    /***************************************************************************
     * 
     */
    public void zrus()
    {
        ziva();
        while( prvni != null )
        {
            prvni = prvni.zrus();
        }
        zprovoznene.remove( barva );
        zrusena = true;
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
    
    /***************************************************************************
     * 
     */
    private void ziva()
    {
        if( zrusena ) {
            throw new IllegalStateException(
                "Linka je jiz zrusena a nelze ji tedy pouzivat" );
        }
    }
    
    
    /***************************************************************************
     * @param z
     */
    private void moje( IZastavka z )
    {
        ziva();
        Zastavka dalsi = prvni;
        boolean  nasel = false;
        do{
            if( z == dalsi )
            {
                nasel = true;
                break;
            }
            dalsi = (Zastavka)dalsi.getNasledujici();
        }while( dalsi != prvni );
        if( ! nasel ) {
            throw new IllegalArgumentException(
                "Zastavka " + z + " neni na lince " + this );
        }
    }
    
    
//== VNORENE A VNITRNI TRIDY ===================================================

    private int zpocet = 0;
    /***************************************************************************
     * Instance tridy Zastavka predstavuji zastavky na trati kabinove lanovky.
     * Pri vytvareni se musi zastavky budovat postupne od zacatku do konce
     * a kazde z nich je treba dodat odkaz na jejiho predchudce. Nove vytvarena
     * zastavka se pak se svou predchudkyne spoji carou predstavujici lano.
     * Zaroven ji oznami, ze je jejim nasledovnikem.
     * Kazda zastavka tak zna sveho predchudce a sveho nasledovnika
     * a je na nej schopna dodat na pozadani odkaz.
     */
    private class Zastavka implements IZastavka
    {
    //== KONSTANTNI ATRIBUTY TRIDY =============================================

        /** Prumer kruhu predstavujiciho nastupiste */
        public static final int  PRUMER = 20;

        /** Polovina prumeru nastupiste. */
        private static final int  P2 = PRUMER / 2;
        

        
    //== PROMENNE ATRIBUTY TRIDY ===============================================
    //== KONSTANTNI ATRIBUTY INSTANCI ==========================================

        /** Poradi vytvoreni dane instance v ramci tridy. */
        private final int zporadi = ++zpocet;

        /** Linka, na niz se zastavka nachazi a ktera mimo jie definuje napr.
         *  barvu nastupiste i prijezdove trati. */
        private final Linka linka;

        /** Souradnice zastavky. */    
        private final int xPos, yPos;

        /** Obrazec symbolizujici nastupiste na dane zastavce. */
        private Elipsa nastupiste;

        

    //== PROMENNE ATRIBUTY INSTANCI ============================================

        /** Predchozi zastavka na trati. */
        private Zastavka predchozi;

        /** Nasledujici zastavka na trati. */
        private Zastavka nasledujici;

        /** Trat vedouci z predchozi zastavky do teto. */
        private Cara trat;
        
        /** Indikuje, je-li zastavka soucasti linky nebo je-li jiz vyjmuta. */
        private boolean funkcni = true;


        
    //== PRISTUPOVE METODY VLASTNOSTI TRIDY ====================================
    //== OSTATNI METODY TRIDY ==================================================

    //##########################################################################
    //== KONSTRUKTORY A TOVARNI METODY =========================================

        /***********************************************************************
         * Tento konstruktor lze pouzit pouze pro linku bez zastavek - vytvori 
         * pocatecni zastavku linky. Vsechny nasledujici zastavky
         * jakoz i spojovaci trate budou kresleny stejnou barvou.
         * Souradnice zastavky jsou souradnicemi stredu kruhu, ktery predstavuje
         * jeji nastupiste.
         * 
         * @param linka   Linka, na ktere se bude zastavka nachazet.
         * @param x       x-ova souradnice stredu nastupiste.
         * @param y       y-ova souradnice stredu nastupiste.
         */
        public Zastavka( Linka linka, int x, int y )
        {
            this( null, linka, x, y );
        }


        /***********************************************************************
         * Vytvori novou instanci tridy Zastavka. Jeji souradnice jsou pritom
         * souradnicemi stredu kruhu, ktery predstavuje jeji nastupiste.
         * Zastavka prevezme barvu sveho predchudce a spoji se s nim carou
         * stejne barvy. Po vytvoreni prihlasi zastavku u AnimPlatna.
         * 
         * @param predchozi Predchozi zastavka, odkud prijizdeji kabiny
         *                  pohybujici se ve smeru jizdy.
         * @param x         x-ova souradnice stredu nastupiste.
         * @param y         y-ova souradnice stredu nastupiste.
         */
        public Zastavka( Zastavka predchozi, int x, int y )
        {
            this( predchozi, predchozi.getLinka(), x, y );
        }


        /***********************************************************************
         * Vytvori novou instanci tridy Zastavka. Jeji souradnice jsou pritom
         * souradnicemi stredu kruhu, ktery predstavuje jeji nastupiste.
         * Zastavka prevezme barvu sveho predchudce a spoji se s nim carou
         * stejne barvy. Po vytvoreni prihlasi zastavku u AnimPlatna.
         * 
         * @param predchozi Predchozi zastavka, odkud prijizdeji kabiny
         *                  pohybujici se ve smeru jizdy.
         * @param linka     Linka, na ktere se bude zastavka nachazet.
         * @param x         x-ova souradnice stredu nastupiste.
         * @param y         y-ova souradnice stredu nastupiste.
         */
        private Zastavka( Zastavka predchozi, Linka linka, int x, int y )
        {
            this.linka = linka;
            this.xPos = x;
            this.yPos = y;
            nastupiste = 
                new Elipsa( x-P2, y-P2, PRUMER, PRUMER, linka.getBarva() );
            if( predchozi == null ) //Prvni zastavka na lince
            {
                this.predchozi = this.nasledujici = this;
            }
            else
            {
                napojNa( predchozi );
            }
            SP.pridej( this );
        }



    //== ABSTRAKTNI METODY =====================================================
    //== PRISTUPOVE METODY VLASTNOSTI INSTANCI =================================

        /***********************************************************************
         * Vrati barvu zastavky.
         * 
         * @return Barva zastavky.
         */
        public Linka getLinka()
        {
            naLince();
            return linka;
        }


        /***********************************************************************
         * Vrati pozici zastavky, tj. pozici jejiho stredu.
         * 
         * @return Pozice stredu zastavky.
         */
        public Pozice getPozice()
        {
            naLince();
            return new Pozice( xPos, yPos );
        }


        /***********************************************************************
         * Vrati odkaz na predchozi zastavku na trati.
         * -
         * @return Odkaz na predchozi zastavku.
         */
        public IZastavka getPredchozi()
        {
            naLince();
            return predchozi;
        }


        /***********************************************************************
         * Vrati odkaz na nasledujici zastavku na trati.
         * 
         * @return Odkaz na nasledujici zastavku.
         */
        public IZastavka getNasledujici()
        {
            naLince();
            return nasledujici;
        }



    //== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =============================

        /***********************************************************************
         * Nakresli se dodanym kreslitkem.
         * 
         * @param g Dodane kreslitko.
         */
        public void nakresli(Kreslitko g)
        {
            if( funkcni )
            {
                trat.nakresli( g );
                nastupiste.nakresli( g );
            }
        }



    //== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===========================
    //== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ============================

        /***********************************************************************
         * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
         *
         * @return Retezcova reprezentace dane instance.
         */
        @Override
        public String toString()
        {
            return this.getClass().getSimpleName() + "_" + barva + "_" + zporadi + 
                ", pozice=[" + xPos + ";" + yPos + "], linka=" + linka;
        }



    //== NOVE ZAVEDENE METODY INSTANCI =========================================
                
        /***********************************************************************
         * Na zastavku (tj. za ni) napoji zastavku na zadanych souradnicich.
         * Nepovazuje-li ji predchozi zastavka za sveho naslednika, 
         * necha puvodniho naslednika napojit na sebe. 
         * Povazuje-li ji za naslednika, tak jiz dalsi akce nedela.
         *
         * @param predchozi  Budouci predchozi zastavka dane stanice;
         */
        public IZastavka napoj( int x, int y )
        {
            return new Zastavka( this, x, y );
        }

        
        /***********************************************************************
         * Napoji zastavku trati na zastavku prevzatou jako predchozi.
         * Nepovazuje-li ji predchozi zastavka za sveho naslednika, 
         * necha puvodniho naslednika napojit na sebe. 
         * Povazuje-li ji za naslednika, tak jiz dalsi akce nedela.
         *
         * @param predchozi  Budouci predchozi zastavka dane stanice;
         */
        void napojNa( Zastavka predchozi )
        {
            if( linka != predchozi.getLinka() ) {
                 throw new IllegalArgumentException(
                    "Zastavka musi mit byt na stejne lince jako jeji budouci" +
                    " predchudce, na ktereho se nyni napojuje: \nzastavka=" +
                    linka + ", predchudce=" + predchozi.linka );
            }
            this.predchozi = predchozi;
            SP.nekresli(); {
                if( predchozi.nasledujici != this )
                {
                    this.nasledujici = predchozi.nasledujici;
                    //this.nasledujici.predchozi = this;
                    this.nasledujici.napojNa( this );
                    predchozi.nasledujici = this;
                }
                trat = new Cara( predchozi.getPozice(), getPozice(),
                                 linka.getBarva() );
            } SP.vratKresli();
        }
        
        
        /***********************************************************************
         * Odstrani tuto zastavku z trati a sveho byvaleho predchudce napoji 
         * na sveho byvaleho naslednika, na nejz zaroven vrati odkaz.
         *
         * @return   Odkaz na nasledujici zastavku. Neni-li takova, vrati null.
         */
        public Zastavka zrus()
        {
            SP.nekresli(); {
                if( predchozi != this )
                {
                    predchozi.nasledujici = nasledujici;
                    nasledujici.napojNa( predchozi );
                }
                SP.odstran( this );
                nastupiste = null;
                trat = null;
            } SP.vratKresli();
            funkcni = false;
            return (nasledujici == this)  ?  null :  nasledujici;
        }
        
        
        
    //== SOUKROME A POMOCNE METODY INSTANCI ====================================
        
        /***********************************************************************
         * Overi, ze zastavka je soucasti linky. Neni-li, vyvola vyjimku.
         */
        private void naLince() 
        {
            if( funkcni ) {
                return;
            }
            throw new IllegalStateException(
                "Zasavka je jiz zrusena a nelze ji tedy pouzivat" );
        }
        
        
        
    //== VNORENE A VNITRNI TRIDY ===============================================
    //== TESTY A METODA MAIN ===================================================
    }//public class Zastavka
    
    
    
//== TESTY A METODA MAIN =======================================================
}

