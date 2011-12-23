/*******************************************************************************
 * Instance tridy Talir jsou jednou z casti UFO. 
 * Talire vytvari dipecer a predava je kontruktoru UFO jako parametr.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class Talir extends Dispecer.Kruh
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Velikost (=prumer) talire je mirou vsech rozmeru ve vesmiru. */
    public static final int VELIKOST = 20;

    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci talire na zadanych souradnicich.
     *
     * @param x  Vodorovna souradnice stredu talire.
     * @param y  Svisla souradnice stredu talire.
     */
    public Talir( double x, double y )
    {
        super( x, y, VELIKOST, Barva.CERVENA );
    }


    
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    
    /***************************************************************************
     * Vrati x-ovou souradnici stredu talire.
     *
     * @return  x-ova souradnice.
     */
    @Override
    public double getX()
    {
        return super.getX();
    }


    /***************************************************************************
     * Vrati y-ovou souradnici stredu talire.
     *
     * @return  y-ova souradnice.
     */
    @Override
    public double getY()
    {
        return super.getY();
    }

    /***************************************************************************
     * Nastavi novou pozici stredu talire.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    @Override
    public void setPozice( double x, double y )
    {
        super.setPozice( x, y );
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vykresli obraz sve instance ve vesmiru.
     */
    @Override
    public void nakresli()
    {
        super.nakresli();
    }

    
    
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

