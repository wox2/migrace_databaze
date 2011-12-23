package rup.cesky.tvary_3;

/*******************************************************************************
 * Trida Posuvny je spolecnou rodicovskou tridou trid implementujicich
 * rozhrani IPosuvny. 
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Posuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Aktivni platno, ktere dohlizi na spravne vykresleni instance. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Celkovy pocet vytvorenych instanci. */
    private static int pocet = 0;
    
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Rodne cislo instance = kolikata v poradi byla vytvorena. */
    protected final int poradi = ++pocet;
  
  
    
//== PROMENNE ATRIBUTY INSTANCI ================================================

    /** Nazev instance sestavajici implicitne z nazvu tridy a poradi instance */
    protected String nazev;
    
    private int xPos;    //Bodova x-ova souradnice instance
    private int yPos;    //Bodova y-ova souradnice instance



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     */
    protected Posuvny( int x, int y )
    {
        this( x, y, null );
        this.nazev = this.getClass().getSimpleName() + "_" + poradi;
    }

    /***************************************************************************
     * Vytvori na zadanych souradnicich
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x     x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y     y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param nazev Pozadovany nazev instance
     */
    protected Posuvny( int x, int y, String nazev )
    {
        //Test platnosti parametru
        if( (x<0) || (y<0) ) {
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: x=" + x + ", y=" + y );
        }
        //Parametry akceptovany --> muzeme tvorit
        this.xPos  = x;
        this.yPos  = y;
        this.nazev = nazev;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

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
        return new Pozice( getX(), getY() );
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
     * Vrati aktualni nazev instance.
     *
     * @return  Retezec s nazvem instance.
     */
     public String getNazev()
     {
        return nazev;
     }


    /***************************************************************************
     * Nastavi novy nazev instance.
     *
     * @param nazev  Retezec s novym nazvem instance.
     */
    public void setNazev(String nazev) 
    {
        this.nazev = nazev;
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *      
     * @return Nazev instance nasledovnay jejimi souradnicemi.
     */
    @Override
    public String toString()
    {
        return nazev + ": x=" + getX()  + ", y=" + getY();
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
        setPozice( getX()+vzdalenost, getY() );
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
        setPozice( getX(), getY()+vzdalenost );
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
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}


