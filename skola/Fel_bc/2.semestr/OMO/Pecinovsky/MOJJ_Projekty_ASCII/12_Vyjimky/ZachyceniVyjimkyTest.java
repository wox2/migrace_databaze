/*******************************************************************************
 * Testovaci trida {@code ZachyceniVyjimkyTest} slouzi k demonstraci
 * testovani spravneho vyhozeni vyjimky.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000, 0.0.2006
 */
public class ZachyceniVyjimkyTest extends junit.framework.TestCase
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public ZachyceniVyjimkyTest(String nazev)
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
     */
    public void testRekurze3()
    {
        try {
            ZachyceniVyjimky.rekurze3(7);
            fail("Nezachyceni vyjimky pri deleni nulou");
        }catch( RuntimeException e ) {}
         catch( Throwable e ) {
            fail("Vyhozeni spatne vyjimky pri deleni nulou - " + e);
        }
    }


    /***************************************************************************
     *
     * /
    public void testXXX()
    {
    }

/**/
}

