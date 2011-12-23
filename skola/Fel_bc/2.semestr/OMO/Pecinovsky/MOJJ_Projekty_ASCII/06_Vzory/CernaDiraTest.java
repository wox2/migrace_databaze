/*******************************************************************************
 * Testovaci trida CernaDiraTest slouzi ke komplexnimu otestovani
 * tridy ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class CernaDiraTest extends junit.framework.TestCase
{
	private CernaDira cernaDira;
	private Elipsa e1;
	private Elipsa e2;
	private Elipsa e3;
	private Elipsa e4;


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public CernaDiraTest(String nazev)
    {
        super( nazev );
    }



//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.         
     */
    @Override
    protected void setUp()
    {
        e1 = new Elipsa(  0,   0, 100, 100);
        e2 = new Elipsa(150,   0, 150, 100);
        e3 = new Elipsa(  0, 200,  50, 100);
        e4 = new Elipsa(150, 150, 150, 150, Barva.FIALOVA);
        cernaDira = CernaDira.getInstance();
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== VLASTNI TESTY =============================================================

    /***************************************************************************
     * 
     * /
    public void testXXX()
    {
    }
    
/**/ 

    /***************************************************************************
     * 
     */
    public void testSpolkni()
    {
        cernaDira.spolkni( e2 );
        cernaDira.spolkni( e1 );
        cernaDira.spolkni( e3 );
        cernaDira.spolkni( e4 );
    }

}


