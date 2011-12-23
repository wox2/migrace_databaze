package rup.cesky.tvary_5;

/*******************************************************************************
 * Trida pro praci s trojuhelnikem komunikujicim s aktivnim platnem.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.02, 18.2.2005
 */
public class Trojuhelnik extends AHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro trojuhelnik Barva.ZELENA. */
    public static final Barva IMPLICITNI_BARVA = Barva.ZELENA;

    /** Pocatecni barva nakresleneho trojuhelniku v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada.    */
    public static final Smer8 IMPLICITNI_SMER = Smer8.SEVER;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

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
        super( x, y, sirka, vyska );
        this.barva = barva;
        this.smer  = smer;
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

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
        return super.toString() + ", barva=" + barva + ", smer=" + smer;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Vrati matici se souradnicemi vrcholu daneho trojuhelniku.
     *
     * @return Pozadovana matice
     */
    private int[][] getVrcholy()
    {
        int[] x = null;
        int[] y = null;

        //Volam rodicovskou verzi metody, abych mel jistotu, co se zavola,
        //a aby se pri prekryti nektere z pouzitych metod
        //nezavolalo neco jineho, co muze vrati pro mne nevhodnou hodnotu.
        int gx = super.getX();
        int gy = super.getY();
        int gs = super.getSirka();
        int gv = super.getVyska();

        switch( smer )
        {
            case VYCHOD:
                x = new int[]{ gx, gx+gs,     gx    };
                y = new int[]{ gy, gy+(gv/2), gy+gv };
                break;

            case SEVEROVYCHOD:
                x = new int[]{ gx, gx+gs, gx+gs };
                y = new int[]{ gy, gy,    gy+gv };
                break;

            case SEVER:
                x = new int[]{ gx,    gx+(gs/2), gx+gs };
                y = new int[]{ gy+gv, gy,        gy+gv };
                break;

            case SEVEROZAPAD:
                x = new int[]{ gx,    gx, gx+gs };
                y = new int[]{ gy+gv, gy, gy    };
                break;

            case ZAPAD:
                x = new int[]{ gx,        gx+gs, gx+gs };
                y = new int[]{ gy+(gv/2), gy,    gy+gv };
                break;

            case JIHOZAPAD:
                x = new int[]{ gx, gx,    gx+gs };
                y = new int[]{ gy, gy+gv, gy+gv };
                break;

            case JIH:
                x = new int[]{ gx, gx+(gs/2), gx+gs };
                y = new int[]{ gy, gy+gv,     gy    };
                break;

            case JIHOVYCHOD:
                x = new int[]{ gx,    gx+gs, gx+gs };
                y = new int[]{ gy+gv, gy+gv, gy    };
                break;

            default:
                throw new IllegalStateException(
                    "Instance ukazuje do nedefinovaneho smeru" );
        }
        return new int[][] { x, y };
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

