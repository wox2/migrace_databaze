package rup.cesky.tvary_1;

/*******************************************************************************
 * Trida pro praci s trojuhelnikem komunikujicim s aktivnim platnem.
 *
 * Oproti verzi z balicku rup.cesky._09_dedicnost_rozhrani.tvary_0
 * se zmenilo pouze implementovane rozhrani
 * a pristupnost atributu {@link SP} byla zmenena na {@code protected}.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Trojuhelnik implements IHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro trojuhelnik Barva.ZELENA. */
    public static final Barva IMPLICITNI_BARVA = Barva.ZELENA;

    /** Pocatecni barva nakresleneho trojuhelniku v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada.    */
    public static final Smer8 IMPLICITNI_SMER = Smer8.SEVER;

    /** Aktivni platno, ktere dohlizi na spravne vykresleni instance. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet vytvorenych instanci */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Nazev sestavajici z nazvu tridy a poradi instance */
    private final String nazev;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int    xPos;    //Bodova x-ova souradnice instance
    private int    yPos;    //Bodova y-ova souradnice instance
    private int    sirka;   //sirka v bodech
    private int    vyska;   //Vyska v bodech
    private Barva  barva;   //Barva instance
    private Smer8  smer;    //Smer, do nejz je otocen vrchol trojuhelniku



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim, barvou
     * a natocenim.
     * Instance bude umistena v levem hornim rohu platna
     * a bude mit implicitni barvu,
     * vysku rovnu kroku a sirku dvojnasobku kroku (tj. implicitne 50x100 bodu)
     * a bude natocena vrocholem na sever.
     */
    public Trojuhelnik()
    {
        this( 0, 0, 2*SP.getKrok(), SP.getKrok() );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou a smerem natoceni.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   sirka vytvarene instance,  sirka >= 0
     * @param vyska   vyska vytvarene instance,  vyska >= 0
     */
    public Trojuhelnik( int x, int y, int sirka, int vyska )
    {
        this( x, y, sirka, vyska, IMPLICITNI_BARVA, IMPLICITNI_SMER );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a smerem natoceni
     * a s implicitni barvou.
     *
     * @param x       x-ova souradnice, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice, y>=0, y=0 ma horni okraj platna
     * @param sirka   sirka instance,  sirka >= 0
     * @param vyska   vyska instance,  vyska >= 0
     * @param smer    Smer, do nejz bude natocen vrchol trojuhelniku -
     *                je treba zadat nekterou z instanci tridy Smer8
     */
    public Trojuhelnik( int x, int y, int sirka, int vyska, Smer8 smer )
    {
        this( x, y, sirka, vyska, IMPLICITNI_BARVA, smer );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     * Smer natoceni bude implicitni, tj. na sever.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   sirka vytvarene instance,  sirka >= 0
     * @param vyska   vyska vytvarene instance,  vyska >= 0
     * @param barva   Barva vytvarene instance
     */
    public Trojuhelnik( int x, int y, int sirka, int vyska, Barva barva )
    {
        this( x, y, sirka, vyska, barva, IMPLICITNI_SMER );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery, barvou a merem.
     *
     * @param pozice  Pozice vytvarene instance
     * @param rozmer  Rozmer vytvarene instance
     * @param barva   Barva vytvarene instance
     * @param smer    Smer, do nejz je natocen vrchol trojuhelniku -
     *                je treba zadat nekterou z instanci tridy Smer8
     */
    public Trojuhelnik( Pozice pozice, Rozmer rozmer, Barva barva, Smer8 smer )
    {
        this( pozice.x, pozice.y, rozmer.sirka, rozmer.vyska, barva, smer );
    }


    /***************************************************************************
     * Vytvori novou instanci v zadane oblasti a se zadanou barvou.
     *
     * @param oblast  Oblast, v niz se ma instance vytvorit
     * @param barva   Barva vytvarene instance
     * @param smer    Smer, do nejz je natocen vrchol trojuhelniku -
     *                je treba zadat nekterou z instanci tridy Smer8
     */
    public Trojuhelnik( Oblast oblast, Barva barva, Smer8 smer )
    {
        this( oblast.x, oblast.y, oblast.sirka, oblast.vyska, barva, smer );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou, barvou,
     * i smerem natoceni.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   sirka vytvarene instance,  sirka >= 0
     * @param vyska   vyska vytvarene instance,  vyska >= 0
     * @param barva   Barva vytvarene instance
     * @param smer    Smer, do nejz bude natocen vrchol trojuhelniku -
     *                je treba zadat nekterou z instanci tridy Smer8
     */
    public Trojuhelnik( int x, int y, int sirka, int vyska, Barva barva, Smer8 smer )
    {
        //Test platnosti parametru
        if( (x<0) || (y<0) || (sirka<0) || (vyska<0) ) {
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: x="
                + x + ", y=" + y + ", sirka=" + sirka + ", vyska=" + vyska );
        }
        //Parametry akceptovany --> muzeme tvorit
        this.nazev = this.getClass().getSimpleName() + "_" + ++pocet;
        this.xPos  = x;
        this.yPos  = y;
        this.sirka = sirka;
        this.vyska = vyska;
        this.barva = barva;
        this.smer  = smer;
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public int getX()
    {
        return xPos;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public int getY()
    {
        return yPos;
    }


    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return new Pozice( xPos, yPos );
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        xPos = x;
        yPos = y;
        SP.prekresli();
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param pozice   Nova pozice instance
     */
    public void setPozice(Pozice pozice)
    {
        setPozice( pozice.x, pozice.y );
    }


    /***************************************************************************
     * Vrati sirku instance.
     *
     * @return  Sirka instance v bodech
     */
     public int getSirka()
     {
         return sirka;
     }


    /***************************************************************************
     * Vrati vysku instance.
     *
     * @return  Vyska instance v bodech
     */
     public int getVyska()
     {
         return vyska;
     }


    /***************************************************************************
     * Vrati instanci tridy Rozmer s rozmery instance.
     *
     * @return   Rozmer s rozmery instance.
     */
    public Rozmer getRozmer()
    {
        return new Rozmer( sirka, vyska );
    }


    /***************************************************************************
     * Nastavi novy "ctvercovy" rozmer instance -
     * na zadany rozmer se nastavi vyska i sirka.
     *
     * @param rozmer  Nove nastavovany rozmer v obou smerech; rozmer>0
     */
    public void setRozmer(int rozmer)
    {
        setRozmer( rozmer, rozmer );
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>=0
     * @param vyska    Nove nastavovana vyska; vyska>=0
     */
    public void setRozmer(int sirka, int vyska)
    {
        if( (sirka < 0) || (vyska < 0) ) {
            throw new IllegalArgumentException(
                "Rozmery musi byt nezaporne: sirka="+sirka + ", vyska="+vyska);
        }
        this.sirka = sirka;
        this.vyska = vyska;
        SP.prekresli();
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param rozmer    Nove nastavovany rozmer.
     */
    public void setRozmer(Rozmer rozmer)
    {
        setRozmer( rozmer.sirka, rozmer.vyska );
    }


    /***************************************************************************
     * Vrati instanci tridy Oblast s informacemi o pozici a rozmerech instance.
     *
     * @return   Oblast s informacemi o pozici a rozmere instance.
     */
    public Oblast getOblast()
    {
        return new Oblast( xPos, yPos, sirka, vyska );
    }


    /***************************************************************************
     * Nastavi novou polohu a rozmery instance.
     *
     * @param o    Nove nastavovana oblast zaujimana instanci.
     */
    public void setOblast(Oblast o)
    {
        SP.nekresli(); {
            setPozice( o.x,     o.y     );
            setRozmer( o.sirka, o.vyska );
        } SP.vratKresli();
    }


    /***************************************************************************
     * Vrati barvu instance.
     *
     * @return  Instance tridy Barva definujici nastavenou barvu.
     */
    public Barva getBarva()
    {
        return barva;
    }


    /***************************************************************************
     * Nastavi novou barvu instance.
     *
     * @param nova   Pozadovana nova barva.
     */
    public void setBarva(Barva nova)
    {
        barva = nova;
        SP.prekresli();
    }


    /***************************************************************************
     * Vrati smer instance. tj. smer, co nejz je otocen vrchol.
     *
     * @return  Instance tridy Smer8 definujici nastaveny smer.
     */
    public Smer8 getSmer()
    {
        return smer;
    }


    /***************************************************************************
     * Nastavi novy smer instance.
     *
     * @param novy  Pozadovany novy smer.
     */
    public void setSmer(Smer8 novy)
    {
        smer = novy;
        SP.prekresli();
    }


    /***************************************************************************
     * Vrati nazev instance, tj. nazev jeji tridy nasledovany poradim.
     *
     * @return  Retezec s nazvem instance.
     */
     public String getNazev()
     {
        return nazev;
     }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.
     */
    public void nakresli( Kreslitko kreslitko )
    {
        int[][] points = getVrcholy();
        kreslitko.vyplnPolygon( points[0], points[1], barva );
    }



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
        return nazev + ": x=" + xPos + ", y=" + yPos +
               ",sirka=" + sirka + ",vyska=" + vyska +
               ", barva=" + barva + ", smer=" + smer;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Presune instanci o zadany pocet bodu vpravo,
     * pri zaporne hodnote parametru vlevo.
     *
     * @param vzdalenost Vzdalenost, o kterou se instance presune.
     */
    public void posunVpravo(int vzdalenost)
    {
        setPozice( xPos+vzdalenost, yPos );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vpravo.
     */
    public void posunVpravo()
    {
        posunVpravo( SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vlevo.
     */
    public void posunVlevo()
    {
        posunVpravo( -SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu dolu,
     * pri zaporne hodnote parametru nahoru.
     *
     * @param vzdalenost    Pocet bodu, o ktere se instance presune.
     */
    public void posunDolu(int vzdalenost)
    {
        setPozice( xPos, yPos+vzdalenost );
    }


    /***************************************************************************
     * Presune instanci o krok bodu dolu.
     */
    public void posunDolu()
    {
        posunDolu( SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu nahoru.
     */
    public void posunVzhuru()
    {
        posunDolu( -SP.getKrok() );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Vrati matici se souradnicemi vrcholu daneho trojuhelniku.
     *
     * @return Pozadovana matice
     */
    private int[][] getVrcholy()
    {
        int[] xpoints = null;
        int[] ypoints = null;

        switch( smer )
        {
            case VYCHOD:
                xpoints = new int[]{ xPos,  xPos + (sirka),    xPos };
                ypoints = new int[]{ yPos,  yPos + (vyska/2),  yPos + vyska };
                break;

            case SEVEROVYCHOD:
                xpoints = new int[]{ xPos,  xPos + sirka,  xPos + sirka };
                ypoints = new int[]{ yPos,  yPos,          yPos + vyska };
                break;

            case SEVER:
                xpoints = new int[]{ xPos,         xPos + (sirka/2), xPos + sirka };
                ypoints = new int[]{ yPos + vyska, yPos,             yPos + vyska };
                break;

            case SEVEROZAPAD:
                xpoints = new int[]{ xPos,          xPos,  xPos + sirka };
                ypoints = new int[]{ yPos + vyska,  yPos,  yPos         };
                break;

            case ZAPAD:
                xpoints = new int[]{ xPos,             xPos + sirka, xPos + sirka };
                ypoints = new int[]{ yPos + (vyska/2), yPos,         yPos + vyska };
                break;

            case JIHOZAPAD:
                xpoints = new int[]{ xPos,  xPos,          xPos + sirka };
                ypoints = new int[]{ yPos,  yPos + vyska,  yPos + vyska };
                break;

            case JIH:
                xpoints = new int[]{ xPos,  xPos + (sirka/2),  xPos + sirka };
                ypoints = new int[]{ yPos,  yPos + vyska,      yPos,        };
                break;

            case JIHOVYCHOD:
                xpoints = new int[]{ xPos,          xPos +sirka,   xPos + sirka };
                ypoints = new int[]{ yPos + vyska,  yPos + vyska,  yPos         };
                break;

            default:
                throw new IllegalStateException(
                    "Instance ukazuje do nedefinovaneho smeru" );
        }
        return new int[][] { xpoints, ypoints };
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

