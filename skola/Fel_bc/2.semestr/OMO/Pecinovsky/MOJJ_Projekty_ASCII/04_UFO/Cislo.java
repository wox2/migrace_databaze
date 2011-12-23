/*******************************************************************************
 * Trida Cislo k zobrazeni poohybujicicho se cisla ve vesmiru.
 * Predpoklada se pritom, ze toto cislo bude identifikovat UFO.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class Cislo
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Posunuti vodorovne souradnice cisla oproti souradnici jeho stredu. */
    public static final int DX = -3;

    /** Posunuti svisle souradnice cisla oproti souradnici jeho stredu. */
    public static final int DY = 5;


//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /* Oba atributy by meli byt konstantni a pak uz je jedno,
     * ze jsou verejne. */

    /** Cislo, ktere dana instnce predstavuje. */
    public final int    cislo;  //Uchovavane cislo

    /** Textova podoba cisla po prevodu na retezec. Bude se pouzivat tak casto,
     *  ze je vyhodne ji mit ulozenou v atributu. */
    public final String cifry;  //Retezcova podoba cisla



//== PROMENNE ATRIBUTY INSTANCI ================================================

    private double x;  //Vodorovna pozice zobrazeneho cisla
    private double y;  //Svisla pozice zobrazeneho cisla



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci predstavujici zadane cislo.
     * Instance je se schopna na pozadani nakreslit.
     * 
     * @param cislo Cislo reprezentovane danou istanci
     * @param x     Pocatecni vodorovna souradnice
     * @param y     Pocatecni svisla souradnice 
     */
    public Cislo( int cislo, double x, double y )
    {
        this.cislo = cislo;
        this.cifry = "" + cislo;
        this.x = x + DX;
        this.y = y + DY;
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati cislo reprezentvane danou instanci.
     * 
     * @return Cislo reprezentvane danou instanci
     */
    public int getCislo()
    {
        return cislo;
    }


    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public double getX()
    {
        return x - DX;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public double getY()
    {
        return y - DY;
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice( double x, double y )
    {
        this.x = x + DX;
        this.y = y + DY;
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance.
     *
     * @return Textovy retezec predstavujici uchovavane cislo.
     */
    @Override
    public String toString()
    {
        return cifry;
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Bezparametricky konstruktor ...
     */
    public void nakresli()
    {
        Vesmir.V.kresliString( cifry, (int)x, (int)y );
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

