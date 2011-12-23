package pokusy;

/*******************************************************************************
 * Testovaci trida SDedicnosti slouzi ke komplexnimu otestovani pokusnych
 * trid Mataka - Dcera - Vnucka po zavedeni vztahu dedicnosti.
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00,  05.2004
 */
public class SDedicnosti extends junit.framework.TestCase
{
    private Matka  matka;
    private Dcera  dcera;
    private Vnucka vnucka;


//##############################################################################
//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.     
     */
    @Override
    protected void setUp()
    {
        System.out.print( "\f" );
        matka  = new Matka("");
        dcera  = new Dcera();
        vnucka = new Vnucka();
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
     * Otestuje parametricke verze konstruktoru vsech tri testovanych trid
     * a demonstruje na nich, jak dceriny konstrukto zavola pre zacatkem 
     * sve prace konstruktor rodicovskeho podobjektu.
     */
    public void testParametrickeKonstruktory()
    {
        matka  = new Matka ( "MATKA"  );
        dcera  = new Dcera ( "DCERA"  );
        vnucka = new Vnucka( "VNUCKA" );
    }


    /***************************************************************************
     * Spusti pro kazdou instanci metodu, ktera se jmenuje stejne jako
     * vlastni trida teto instance a je v teto tride definovana. 
     * Vystup je urcen pro srovnani s vystupem testu MatkaMatkaMatka.
     */
    public void testMatkaDceraVnucka()
    {
        Matka.zprava("\nInstance matka" );
        matka.matka();
        
        Dcera.zprava("\nInstance dcera" );
        dcera.dcera();
        
        Vnucka.zprava("\nInstance vnucka" );
        vnucka.vnucka();
    }


    /***************************************************************************
     * Spusti pro kazdou instanci metodu matka() definovanou ve tride Matka.
     * Pro dceru a vnucku se tak spusti zdedena metoda se vsemi dusledky.     
     * Vystup je urcen pro srovnani s vystupem testu MatkaDceraVnucka.
     */
    public void testMatkaMatkaMatka()
    {
        Matka.zprava("\nInstance matka" );
        matka.matka();
        
        Dcera.zprava("\nInstance dcera" );
        dcera.matka();
        
        Vnucka.zprava("\nInstance vnucka" );
        vnucka.matka();
    }


    /***************************************************************************
     * Demonstruje vliv volani rodicovskych verzi prekrytych metod
     * na zpusob jejich provedeni.     
     */
    public void testSuper()
    {
        System.out.println("\nVolam dcera.rodice\n");
        dcera.rodice();
        System.out.println("\nVolam vnucka.rodice\n");
        vnucka.rodice();
    }
    

    /***************************************************************************
     * Demonstruje nezavislost statickych metod na definici stejnojmenne
     * staticke metody v dcerine tride.
     */
    public void testZpravy()
    {
        matka.zpravy();
        dcera.zpravy();
        vnucka.zpravy();
    }
}
//public class SDedicnosti extends junit.framework.TestCase

