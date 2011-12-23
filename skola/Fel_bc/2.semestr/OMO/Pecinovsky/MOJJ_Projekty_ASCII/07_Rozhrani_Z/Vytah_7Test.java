/*******************************************************************************
 * Testovaci trida Vytah_7Test slouzi ke komplexnimu otestovani 
 * tridy Vytah_7 
 *
 * @author    Rudolf Pecinovsky
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2004
 */
public class Vytah_7Test extends junit.framework.TestCase
{
    private int krok = 50;
    private int sirka = 5;
    private int vyska = 6;

    private SpravcePlatna SP;
    private Vytah_7 v1;
    private Vytah_7 v2;
    
    private Elipsa      p1;
    private Trojuhelnik p2;
    
    
    
//== METODY TRIDY PRO PRIPRAVU A SPUSTENI SADY =================================
//##############################################################################
//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.         
     */
    @Override
    protected void setUp()
    {
        SP = SpravcePlatna.getInstance();
        SP.setKrokRozmer( krok, sirka, vyska );
        
        p1 = new Elipsa( 0, 0, krok, krok );
        SP.pridej( p1 );
        p2 = new Trojuhelnik( 2*krok, krok*(vyska-2), krok, krok );
        SP.pridej( p2 );
        
        v1 = new Vytah_7( 1, 4, Barva.CERVENA );
        v2 = new Vytah_7( 3 );
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
    public void testVytah()
    {
        v1.doPatra( 5 );
        v1.nastup( p1 );
        v1.doPatra( 3 );
        v1.vystupVlevo();
        v1.doPatra(1);
        v1.nastup(p2);
        v1.doPatra(3);
        v1.vystupVpravo();
        
        v2.doPatra( 3 );
        v2.nastup( p2 );
        v2.doPatra( 0 );
        v2.vystupVpravo();
    }
    
}

