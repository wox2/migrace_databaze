/*******************************************************************************
 * Testovaci trida VytahDTest slouzi ke komplexnimu otestovani
 * tridy VytahD
 *
 * @author    Rudolf Pecinovsky
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2004
 */
public class VytahDTest extends junit.framework.TestCase
{
    private static SpravcePlatna SP = SpravcePlatna.getInstance();

    private int krok = 50;      //Krok aktivniho platna
    private int sirka = 5;      //Pocet sloupcu aktivniho platna
    private int vyska = 5;      //Pocet radku aktivniho platna
    
    private VytahD v1;        //Vytah v prvnim sloupci
    private VytahD v3;        //Vytah ve tretim sloupci
    
    private Elipsa      k;      //Kruhovy pasazer
    private Trojuhelnik t;      //Trojuhelnikovy pasazer



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public VytahDTest(String nazev)
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
        SP.odstranVse();
        SP.setKrokRozmer( krok, sirka, vyska );
        
        k = new Elipsa     ( x(0), y(1), krok, krok, Barva.CERVENA );
        SP.pridej( k );
        t = new Trojuhelnik( x(2), y(4), krok, krok, Barva.CERVENA );
        SP.pridej( t );
        
        v1 = new VytahD( 1 );
        v3 = new VytahD( 3, 4, Barva.MODRA );
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================

    /***************************************************************************
     * Otestuje, jestli je zadany objekt ve spravenem policku.
     *
     * @param ip       Odkaz na objekt, jehoz sourqadnice testujeme.
     * @param sloupec  Sloupec, v nemz se ma proverovany objekt nachazet.
     * @param patro    Patro, v nemz se ma proverovany objekt nachazet.
     */
    public static void souradnice( IPosuvny ip, int sloupec, int patro )
    {
        Pozice cekam = pozice(sloupec, patro );
        assertEquals( "Nesouhlasi souradnice objektu " + ip + ":",
                      cekam, ip.getPozice() );
    }


    /***************************************************************************
     * Vrati x-ovou souradnici policek v zadanem sloupci.
     *
     * @param sloupec Sloupec, jehoz vodorovnou souradnici hledame
     * @return Vodorovna souradnice policek v danem sloupci
     */
    public static int x( int sloupec )
    {
        return sloupec * SP.getKrok();
    }


    /***************************************************************************
     * Vrati y-ovou souradnici policek v zadanem radku;
     *
     * @param patro Radek, jehoz svislou souradnici hledame
     * @return Svisla souradnice policek v danem radku
     */
    public static int y( int patro )
    {
        return (SP.getRadku() - patro - 1) * SP.getKrok();
    }


    /***************************************************************************
     * Vrati bodovou pozici policka v zadanem radku a sloupci.
     *
     * @param sloupec Sloupec, jehoz vodorovnou souradnici hledame
     * @param patro Radek, jehoz svislou souradnici hledame
     * @return POzice zadaneho policka
     */
    public static Pozice pozice( int sloupec, int patro )
    {
        return new Pozice( x(sloupec), y(patro) );
    }



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
     * Testuje spravne nastaveni pocatecnich podminek. 
     * Predpoklada pritom, ze metody getX() a getY() funguji u vytahu spravne.
     */
    public void testInicializace()
    {
        souradnice( v1, 1, 0 );
        souradnice( v3, 3, 0 );
    }
    

    /***************************************************************************
     * Otestuje, ze oba vytahy umi spravne dojet do 3. patra
     * a sjet zase zpet do prizemi.     
     */
    public void testPrazdnyVytahDoPatra()
    {
        v1.doPatra( 3 );    souradnice( v1, 1, 3 );
        v3.doPatra( 3 );    souradnice( v3, 3, 3 );
        v1.doPatra( 0 );    souradnice( v1, 1, 0 );
        v3.doPatra( 0 );    souradnice( v3, 3, 0 );
    }
    

    /***************************************************************************
     * Testuje schopnost vytahu premistit nastupujici pasazery do vytahu a
     * pak je vysadit na sousedni policko v zadnem smeru.
     */
    public void testNastupVystup()
    {
        v1.nastup( k );    souradnice( k, 1, 0 );
        v3.nastup( t );    souradnice( t, 3, 0 );
        v1.vystupVlevo();  souradnice( k, 0, 0 );
        v3.vystupVpravo(); souradnice( t, 4, 0 );
    }
    

    /***************************************************************************
     * Testuje schopnost vytahu prevezt pasazery do pozadovaneho patra.
     */
    public void testPrevozPasazera()
    {
        v1.nastup( k );
        v1.doPatra( 2 );
        v1.vystupVlevo();
        souradnice( k, 0, 2 );
        
        v3.nastup( t );
        v3.doPatra( 4 );
        v3.vystupVpravo(); 
        souradnice( t, 4, 4 );
    }
    

    /***************************************************************************
     * Testuje metody zabezpecujici kompletni obsluhu pasazera, 
     * tj. dojeti do jeho patra, nastup, prevezeni do pozadovaneho patra
     * a vysazeni pasazera na pozadovanou stranu.
     */
    public void testOdvez()
    {
        v1.odvezVpravo( k, 3 );   
        souradnice( v1, 1, 3 );  souradnice( k, 2, 3 );
        
        v3.odvezVlevo ( t, 1 );
        souradnice( v3, 3, 1 );  souradnice( t, 2, 1 );
        
        v3.odvezVpravo( k, 0 );
        souradnice( v3, 3, 0 );  souradnice( k, 4, 0 );
    }
    
}

