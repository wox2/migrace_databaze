/*******************************************************************************
 * Testovaci trida TestTestu slouzici k demonstraci zakladnich vlastnosti testu.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class TestTestu extends junit.framework.TestCase
{
    private int i1;
    private String s1;
    private Elipsa e;

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

//##############################################################################
//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.
     */
    @Override
    protected void setUp()
    {
        System.out.println( "\n=== Priprava testu " + getName() );
        i1 = 1;
        s1 = "Jedna";
        e  = new Elipsa();
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
        Platno.smazPlatno();
        System.out.println( "XXX Uklizeno po testu " + getName() );
    }


//== VLASTNI TESTY =============================================================
//
//    /***************************************************************************
//     *
//     * /
//    public void testXXX()
//    {
//    }

    public void testCisel()
    {
        System.out.println( "Cisla souhlasi" );
        assertEquals( "Neshoda celeho cisla", 1, i1 );
        System.out.println( "Cisla nesouhlasi" );
        assertEquals( "Neshoda celeho cisla", 0, i1 );
        System.out.println( "Konec testu cisel" );
    }

    public void testSouradnic()
    {
        System.out.println( "Souradnice souhlasi" );
        assertEquals( "Neshoda souradnic", 0, e.getX() );
        System.out.println( "Souradnice nesouhlasi" );
        assertEquals( "Objekty se lisi", null, e );
        assertEquals( "Neshoda souradnic", 1, e.getX() );
        System.out.println( "Konec testu souradnic" );
    }

    public void testRetezcu()
    {
        System.out.println( "Retezce souhlasi" );
        assertEquals( "Neshoda textu", "Jedna", s1 );
        System.out.println( "Retezce souhlasi" );
        assertEquals( "Neshoda textu", "Dva", s1 );
        System.out.println( "Konec testu retezcu" );
    }

}

