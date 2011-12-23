/*******************************************************************************
 * Testovaci trida {@code VytahXDPTest} slouzi ke komplexnimu otestovani 
 * vsech tri realizaci vytahu, tj vytahu s dvermi, skrytym i viditelnym
 * pasazerem.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2004
 */
public class VytahXDPTest extends junit.framework.TestCase
{
    private static SpravcePlatna SP = SpravcePlatna.getInstance();

    private int krok = 50;      //Krok aktivniho platna
    private int sirka = 7;      //Pocet sloupcu aktivniho platna
    private int vyska = 7;      //Pocet radku aktivniho platna
    
    private VytahX v1;        //Vytah nezobrazujici pasazera
    private VytahD v3;        //Vytah se zaviracimi dvermi
    private VytahP v5;        //Vytah zobrazujici pasazera za jizdy
    
    private Elipsa      kk;      //Kruhovy pasazer
    private Trojuhelnik ts;      //Trojuhelnikovy pasazer se spickou na sever
    private Trojuhelnik tj;      //Trojuhelnikovy pasazer se spickou na jih



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public VytahXDPTest(String nazev)
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
        
        kk = new Elipsa     ( x(0), y(1), krok, krok, Barva.CERVENA );
        SP.pridej( kk );
        ts = new Trojuhelnik( x(2), y(4), krok, krok, Barva.CERVENA );
        SP.pridej( ts );
        tj = new Trojuhelnik( x(4), y(6), krok, krok, Barva.CERVENA, Smer8.JIH );
        SP.pridej( tj );
        
        v1 = new VytahX( 1 );
        v3 = new VytahD( 3, 3, Barva.MODRA );
        v5 = new VytahP( 5, 5, Barva.FIALOVA );
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
        souradnice( v5, 5, 0 );
    }
    

    /***************************************************************************
     * Otestuje, ze oba vytahy umi spravne dojet do 3. patra
     * a sjet zase zpet do prizemi.     
     */
    public void testPrazdnyVytahDoPatra()
    {
        v1.doPatra( 3 );    souradnice( v1, 1, 3 );
        v3.doPatra( 3 );    souradnice( v3, 3, 3 );
        v5.doPatra( 3 );    souradnice( v5, 5, 3 );
        
        v1.doPatra( 0 );    souradnice( v1, 1, 0 );
        v3.doPatra( 0 );    souradnice( v3, 3, 0 );
        v5.doPatra( 0 );    souradnice( v5, 5, 0 );
    }
    

    /***************************************************************************
     * Otestuje, ze oba vytahy umi spravne dojet do 3. patra
     * a sjet zase zpet do prizemi.     
     */
    public void testPrijedK()
    {
        v1.prijedK( kk );    souradnice( v1, 1, 1 );
        v3.prijedK( ts );    souradnice( v3, 3, 4 );
        v5.prijedK( tj );    souradnice( v5, 5, 6 );
    }
    

    /***************************************************************************
     * Testuje schopnost vytahu premistit nastupujici pasazery do vytahu a
     * pak je vysadit na sousedni policko v zadnem smeru.
     */
    public void testNastupVystup()
    {
        v1.nastup( kk );    souradnice( kk, 1, 0 );
        v3.nastup( ts );    souradnice( ts, 3, 0 );
        v5.nastup( tj );    souradnice( tj, 5, 0 );
        
        v1.vystupVlevo();   souradnice( kk, 0, 0 );
        v3.vystupVpravo();  souradnice( ts, 4, 0 );
        v5.vystupVpravo();  souradnice( tj, 6, 0 );
    }
    

    /***************************************************************************
     * Testuje schopnost vytahu prevezt pasazery do pozadovaneho patra.
     */
    public void testPrevozPasazera()
    {
        v1.nastup( kk );
        v1.doPatra( 2 );
        v1.vystupVlevo();
        souradnice( kk, 0, 2 );
        
        v3.nastup( ts );
        v3.doPatra( 4 );
        v3.vystupVpravo(); 
        souradnice( ts, 4, 4 );
        
        v5.nastup( tj );
        v5.doPatra( 6 );
        v5.vystupVpravo(); 
        souradnice( tj, 6, 6 );
    }
    

    /***************************************************************************
     * Testuje metody zabezpecujici kompletni obsluhu pasazera, 
     * tj. dojeti do jeho patra, nastup, prevezeni do pozadovaneho patra
     * a vysazeni pasazera na pozadovanou stranu.
     */
    public void testOdvez()
    {
        v1.odvezVpravo( kk, 3 );   
        souradnice( v1, 1, 3 );  souradnice( kk, 2, 3 );
        
        v3.odvezVlevo ( ts, 1 );
        souradnice( v3, 3, 1 );  souradnice( ts, 2, 1 );
        
        v3.odvezVpravo( kk, 0 );
        souradnice( v3, 3, 0 );  souradnice( kk, 4, 0 );
        
        v5.odvezVpravo ( kk, 6 );
        souradnice( v5, 5, 6 );  souradnice( kk, 6, 6 );
        
        v5.odvezVpravo( tj, 0 );
        souradnice( v5, 5, 0 );  souradnice( tj, 6, 0 );
    }
    
}

