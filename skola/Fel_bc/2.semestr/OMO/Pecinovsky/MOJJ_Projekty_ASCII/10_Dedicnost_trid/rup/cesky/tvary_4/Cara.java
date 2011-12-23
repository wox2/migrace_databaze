package rup.cesky.tvary_4;

/*******************************************************************************
 * Trida pro praci s carou komunikujici s aktivnim platnem.
 * Cara je urcena svymi krajnimi body, pricemz souradnice pocatecniho bodu
 * je soucasne povazovana za pozici cele instance.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.02, 18.2.2005
 */
public class Cara extends APosuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro caru Barva.CERNA. */
    public static final Barva IMPLICITNI_BARVA = Barva.CERNA;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int    kx;      //xBodova -ova souradnice konce
    private int    ky;      //Bodova y-ova souradnice konce
    private Barva  barva;   //Barva instance



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna
     * a bude mit implicitni barvu,
     * Koncit bude ve stredu platna.
     */
    public Cara()
    {
        this( 0, 0, SP.getKrok()*SP.getSloupcu()/2,
                    SP.getKrok()*SP.getRadku()/2 );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param x   x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y   y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param kx  x-ova souradnice koncoveho bodu instance
     * @param ky  y-ova souradnice koncoveho bodu instance
     */
    public Cara(int x, int y, int kx, int ky)
    {
        this( x, y, kx, ky, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param konec     Pozice koncoveho bodu instance
     */
    public Cara(Pozice pocatek, Pozice konec)
    {
        this( pocatek.x, pocatek.y, konec.x, konec.y, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param konec     Pozice koncoveho bodu instance
     * @param barva     Barva vytvarene instance
     */
    public Cara(Pozice pocatek, Pozice konec, Barva barva)
    {
        this( pocatek.x, pocatek.y, konec.x, konec.y, barva );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param kx      x-ova souradnice koncoveho bodu instance
     * @param ky      y-ova souradnice koncoveho bodu instance
     * @param barva   Barva vytvarene instance
     */
    public Cara(int x, int y, int kx, int ky, Barva barva)
    {
        super( x, y );
        this.kx = kx;
        this.ky = ky;
        this.barva = barva;
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Nastavi novou pozici instance.
     * Koncovy bod se pritom presune tak,
     * aby zustala zachovana velikost a smer instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    @Override
    public void setPozice( int x, int y )
    {
        kx  += x - super.getX();
        ky  += y - super.getY();
        super.setPozice( x, y );
        SP.prekresli();
    }


    /***************************************************************************
     * Vrati x-ovou souradnici koncoveho bodu instance.
     *
     * @return  x-ova souradnice koncoveho bodu.
     */
    public int getKX()
    {
        return kx;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici koncoveho bodu instance.
     *
     * @return  y-ova souradnice koncoveho bodu.
     */
     public int getKY()
     {
         return ky;
     }


    /***************************************************************************
     * Vrati pozici koncoveho bodu instance.
     *
     * @return  Pozice koncoveho bodu.
     */
     public Pozice getKPozice()
     {
         return new Pozice( getKX(), getKY() );
     }


    /***************************************************************************
     * Nastavi novou pozici koncoveho bodu instance.
     *
     * @param kx      Nova x-ova souradnice koncoveho bodu
     * @param ky      Nova y-ova souradnice koncoveho bodu
     */
     public void setKPozice( int kx, int ky )
     {
         this.kx = kx;
         this.ky = ky;
         SP.prekresli();
     }


    /***************************************************************************
     * Nastavi pozici koncoveho bodu instance.
     *
     * @param kpozice  Nova pozice koncoveho bodu.
     */
     public void setKPozice( Pozice kpozice )
     {
         setPozice( kpozice.x, kpozice.y );
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



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.
     */
    public void nakresli( Kreslitko kreslitko )
    {
        kreslitko.kresliCaru( getX(), getY(), kx, ky, getBarva() );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vrati textovou reprezentaci dane instanci.
     * Pouziva se predevsim pri ladeni.
     *
     * @return Nazev tridy nasledovany poradim a souradnicemi krajnich bodu
     */
    @Override
    public String toString()
    {
        return super.toString() + ", kx=" + kx + ", ky=" + ky +
               ", barva=" + barva;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Spoji carou zadane body.
     *
     * @param px      x-ova souradnice pocatku, x=0 ma levy okraj platna
     * @param py      y-ova souradnice pocatku, y=0 ma horni okraj platna
     * @param kx      x-ova souradnice koncoveho bodu
     * @param ky      y-ova souradnice koncoveho bodu
     */
    public void spoj( int px, int py, int kx, int ky    )
    {
        setPozice( px, py );
        this.kx   = kx;
        this.ky   = ky;
        SP.prekresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

