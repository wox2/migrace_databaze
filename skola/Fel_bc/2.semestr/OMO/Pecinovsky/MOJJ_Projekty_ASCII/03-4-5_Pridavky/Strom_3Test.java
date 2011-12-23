/*******************************************************************************
 * Testovaci trida {@code Strom_3Test} testuje tridu {@link Strom_3}, a to: 
 *  = vytvoreni pripravku proverujici funkci vsech konstruktoru
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_3Test extends junit.framework.TestCase
{
    private Strom_3 strom1;
    private Strom_3 strom2;
    private Strom_3 strom3;
    private Strom_3 strom4;


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public Strom_3Test( String nazev )
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
        strom1 = new Strom_3();
        strom2 = new Strom_3( 25, 150);
        strom3 = new Strom_3(100, 100, 100,  90);
        strom4 = new Strom_3(200,   0, 100, 300);
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     * Bezparametricky konstruktor pro testovaci tridu StromTest
     */
    @Override
    protected void tearDown()
    {
    }


//== VLASTNI TESTY =============================================================
    

}

