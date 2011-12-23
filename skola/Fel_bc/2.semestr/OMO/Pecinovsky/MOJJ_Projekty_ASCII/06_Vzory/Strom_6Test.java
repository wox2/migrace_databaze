/*******************************************************************************
 * Testovaci trida Strom_6Test.
 * = dedi od tridy Strom_4Test:
 *    - ze se stromy umeji nakreslit a smazat 
 *    - zakladni bezparametricke posuny 
 *    - test posunu po zmene kroku
 *    - test Zaramuj  
 *    - test PocitaniInstanci
 * = sama pridava
 *    - test Obrazek - testuje metodu, vyuzivajici konstruktor pracujici
 *      s prepravkou Pozice  
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_6Test extends junit.framework.TestCase
{
    private Strom_6 strom1;
    private Strom_6 strom2;
    private Strom_6 strom3;
    private Strom_6 strom4;


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public Strom_6Test(String nazev)
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
        Platno platno = Platno.getInstance();
        platno.setRozmer( 300, 300 );
        strom1 = new Strom_6();                        
        strom2 = new Strom_6( 25, 150);                
        strom3 = new Strom_6(100, 100, 100,  90);      
        strom4 = new Strom_6(200,   0, 100, 300, 3, 5);
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
        Strom_6.setKrok(10);
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
        Strom_6.obrazek( new Oblast (100, 100, 100, 100), 10 );
        assertTrue( IO.souhlas("V poradku na stredu zaramovany strom?"));
    }


}

