/*******************************************************************************
 * Testovaci trida Strom_7aTest.
 * Upravuje dedictvi od tridy Strom_5Test tak, aby chodilo v novem prostredi.
 * Musi rozchodit testy:
 *  - ze se stromy umeji nakreslit a smazat 
 *  - zakladni bezparametricke posuny 
 *  - test posunu po zmene kroku
 *  - test metody instance zaramuj()  
 *  - test PocitaniInstanci
 *  - test staticke metody obrazek() 
 *    testuje metodu vyuzivajici konstruktor pracujici s prepravkou Pozice  
 *  - test staticke metody Alej
 *  - test staticke metody zaramuj(int,int))  
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_7aTest extends junit.framework.TestCase
{
    private Strom_7a strom1;
    private Strom_7a strom2;
    private Strom_7a strom3;
    private Strom_7a strom4;


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public Strom_7aTest(String nazev)
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
        SpravcePlatna platno = SpravcePlatna.getInstance();
        platno.setKrokRozmer( 50, 6, 6 );
        platno.setMrizka( true );
        platno.odstranVse();
        strom1 = new Strom_7a();                        
        strom2 = new Strom_7a( 25, 150);                
        strom3 = new Strom_7a(100, 100, 100,  90);      
        strom4 = new Strom_7a(200,   0, 100, 300, 3, 5);
        strom1.zobraz();
        strom2.zobraz();
        strom3.zobraz();
        strom4.zobraz();
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
        strom1.zobraz();
        strom2.zobraz();
        strom3.zobraz();
        strom4.zobraz();
        assertEquals(true, IO.souhlas(
            "Strom 1: x=0,   y=  0, s=100, v=150, sSt/sKm=10, vSt/vKo=3\n" +
            "Strom 2: x= 25, y=150, s=100, v=150, sSt/sKm=10, vSt/vKo=3\n" +
            "Strom 3: x=100, y=100, s=100, v= 90, sSt/sKm=10, vSt/vKo=3\n" +
            "Strom 4: x=200, y=100, s=100, v=300, sSt/sKm= 3, vSt/vKo=5\n" +
            "\nPozice, rozmery a pomery kmen a korun souhlasi?"));
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
    
    public void testKrok()
    {
        IO.zprava("Nasleduje zmena kroku na 10");
        Strom_7a.setKrok(10);
        testPosuny();
    }
        
    public void testZaramuj()
    {
        strom1.zaramuj();
        assertTrue( IO.souhlas("Implicitni strom zaramovan?") );
        strom1.smaz();
        strom3.zaramuj();
        assertTrue( IO.souhlas("Treti strom zaramovan?") );
        strom3.smaz();
        strom4.zaramuj();
        assertTrue( IO.souhlas("Ctvrty strom zaramovan?") );
    }

    public void testPocitaniInstanci()
    {
        assertTrue( IO.souhlas( "Test pocitani instanci a prevodu na retezec\n" +
            strom1 + "\n" + strom2 + "\n" + strom3 + "\n" + strom4 + "\n" +
            "\n\nSouhlasi?"));
    }

    public void testObrazek()
    {
        Strom_7a.obrazek( new Oblast (100, 100, 100, 100), 10 );
        assertTrue( IO.souhlas("V poradku na stredu zaramovany strom?"));
    }

    public void testAlej()
    {
        SpravcePlatna.getInstance().odstranVse();
        Strom_7a.alej();
        assertTrue( IO.souhlas("Alej nakreslena spravne?"));
    }

    public void testStatickeZaramuj()
    {
        SpravcePlatna.getInstance().odstranVse();
        Strom_7a.zaramuj( 200, 300 );
        assertTrue( IO.souhlas("Strom zaramovan v okne 200300?"));
    }


}

