/*******************************************************************************
 * Testovaci trida Strom_4aTest testuje: 
 *  = ze se stromy umeji nakreslit a smazat 
 *  = zakladni bezparametricke posuny 
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_4aTest extends junit.framework.TestCase
{
    private Strom_4a strom1;
    private Strom_4a strom2;
    private Strom_4a strom3;
    private Strom_4a strom4;


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public Strom_4aTest( String nazev )
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
        strom1 = new Strom_4a();
        strom2 = new Strom_4a( 25, 150);
        strom3 = new Strom_4a(100, 100, 100,  90);
        strom4 = new Strom_4a(200,   0, 100, 300);
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

    public void testNakresliSmaz()
    {
        strom1.smaz();
        strom2.smaz();
        strom3.smaz();
        strom4.smaz();
        assertEquals(true, IO.souhlas("Stromy smazany?"));
        strom1.nakresli();
        strom2.nakresli();
        strom3.nakresli();
        strom4.nakresli();
        assertEquals(true, IO.souhlas("Stromy opet nakresleny?"));
    }

    public void testPosuny()
    {
        IO.zprava("Nasleduje posun vpravo");
        strom4.posunVpravo();
        strom3.posunVpravo();
        strom2.posunVpravo();
        strom1.posunVpravo();
        assertEquals(true, 
            IO.souhlas("Posun vpravo vporadku?\n\nNasleduje posun vlevo"));
        strom1.posunVlevo();
        strom2.posunVlevo();
        strom3.posunVlevo();
        strom4.posunVlevo();
        assertEquals(true, 
            IO.souhlas("Posun vlevo vporadku?\n\nNasleduje posun vzhuru"));
        strom1.posunVzhuru();
        strom3.posunVzhuru();
        strom4.posunVzhuru();
        strom2.posunVzhuru();
        assertEquals(true, 
            IO.souhlas("Posun vzhuru vporadku?\n\nNasleduje posun dolu"));
        strom2.posunDolu();
        strom4.posunDolu();
        strom3.posunDolu();
        strom1.posunDolu();
        assertEquals(true, 
            IO.souhlas("Posun dolu vporadku?"));
    }
    

}

