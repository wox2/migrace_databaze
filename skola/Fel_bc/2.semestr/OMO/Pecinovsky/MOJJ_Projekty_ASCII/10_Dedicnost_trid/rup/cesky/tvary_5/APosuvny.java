package rup.cesky.tvary_5;

/*******************************************************************************
 * Trida {@code APosuvny} je spolecnou rodicovskou tridou trid implementujicich
 * rozhrani {@link IPosuvny}.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.02, 18.2.2005
 */
public abstract class APosuvny implements IPosuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Instance tridy {@link SpravcePlatna} spravujici platno,
     *  na ktere se vsechny tvary kresli. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Celkovy pocet vytvorenych instanci. */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Rodne cislo instance = jako kolikata byla vytvorena. */
    protected final int poradi = ++pocet;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    /** Nazev instance sestavajici implicitne z nazvu tridy a poradi instance */
    protected String nazev;

    /** Bodova x-ova souradnice instance. */
    private int xPos;

    /** Bodova y-ova souradnice instance. */
    private int yPos;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x  x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y  y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     */
    public APosuvny( int x, int y )
    {
        //Test platnosti parametru
        if( (x<0) || (y<0) ) {
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: x=" + x + ", y=" + y );
        }
        //Parametry akceptovany --> muzeme tvorit
        xPos  = x;
        yPos  = y;
        nazev = this.getClass().getSimpleName() + "_" + pocet;
    }



//== ABSTRAKTNI METODY =========================================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno
     */
    abstract public void nakresli( Kreslitko kreslitko );



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati nazev instance, tj. nazev jeji tridy nasledovany poradim.
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
        if((x != xPos)  ||  (y != yPos))  {
            xPos = x;
            yPos = y;
            SP.prekresli();
        }
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
     * Nastavi novou vodorovnou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     */
    public void setX(int x)
    {
        setPozice( x, getY());
    }


    /***************************************************************************
     * Nastavi novou svislou pozici instance.
     *
     * @param y   Nova y-ova pozice instance
     */
    public void setY(int y)
    {
        setPozice( getX(), y);
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

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


    /***************************************************************************
     * Prihlasi instanci u spravce platna do jeho spravy
     * a tim zaridi jeji zobrazeni.
     */
    public void zobraz()
    {
        SP.pridej( this );
    }


    /***************************************************************************
     * Odstrani obraz sve instance z platna.
     */
    public void smaz()
    {
        SP.odstran( this );
    }


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


