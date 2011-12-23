package displej;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


import rup.cesky.tvary.SpravcePlatna;


/*******************************************************************************
 * Testovaci trida Test_Displej slouzi ke komplexnimu otestovani ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class DisplejTest extends TestCase
 {
 //== SOUKROME KONSTANTY ========================================================
     
     private static final SpravcePlatna ap = SpravcePlatna.getInstance();
     
     
 //== VEREJNE KONSTANTY =========================================================
 //== RETEZCOVE LITERALY ========================================================
 //== ATRIBUTY TRIDY ============================================================
 //== ATRIBUTY INSTANCI =========================================================
 //== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
 //== OSTATNI METODY TRIDY ======================================================
     
    /***************************************************************************
     * Definuje sadu testu, ktere ma dana trida na starosti.
     * Do teto sady mohou patrit jak jednotlive testy tak sady testu.
     * <li>
     * <ul><code>suite.addSuite( trida.class )</code><br>
     *     Prida mezi testy vsechny verejne bezparametricke metody tridy
     *     <code>trida</code>, zacinajici slovem <b>test</b>.</ul>
     * <ul><code>suite.addTest( Test_XXX.suite() )</code><br>
     *     Prida mezi testy sadu definovanou metodou <code>trida.suite</code>.
     * </ul></li>
     *
     * @return Sada testu
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite( DisplejTest.class.getName() );

        //Maji-li byt sadou testy teto tridy, lze prechozi deklaraci nahradit
        //TestSuite suite = new TestSuite( Test_Displej.class );
        //...nebo vlozit prikaz
        suite.addTestSuite( DisplejTest.class );

        //Ma-li byt sada sestavena ze sad z jinych trid
        //suite.addTest( Test_XXX.suite() );

        //Ma-li byt do sady pridan jeden konkretni test konkretni tridy
        //suite.addTest(new Test_XXX("testXXX"));

        return suite;
    }
     
     
     
 //##############################################################################
 //== KONSTRUKTORY A TOVARNI METODY =============================================
     
    /***************************************************************************
    * Vytvori test se zadanym nazvem.
    *
    * @param nazev     Nazev konstruovaneho testu
    */
    public DisplejTest(String nazev)
    {
         super( nazev );
    }
     
     
     
 //== PRIPRAVA A UKLID PRIPRAVKU ================================================
     
    /***************************************************************************
    * Vytvoreni pripravku, tj. sady objektu, s nimiz budou vsechny testy
    * pracovat a ktera se proto vytvori pred spustenim kazdeho testu.
    */
    @Override
    protected void setUp()
    {
    }

     
    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
        ap.odstranVse();
    }
     
     
 //== VLASTNI TESTY =============================================================
     
    /***************************************************************************
     * Sablona pro generaci testovacich metod.
     */
    public void testXXX()
    {
     
    }
     
     
    /***************************************************************************
     * Otestuje konstruktory s ruzne prebarvenymi platny.
     */
    public void test3modre()
    {
        
    }
     
     
     
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
     
    /***************************************************************************
     * Prime spusteni testu v teto tride.
     *
     * @param args Parametry prikazoveho radku se nepouzivaji.
     */
    public static void main( String[] args )
    {     
        junit.textui.TestRunner.run(suite());
    }
     
     
}

