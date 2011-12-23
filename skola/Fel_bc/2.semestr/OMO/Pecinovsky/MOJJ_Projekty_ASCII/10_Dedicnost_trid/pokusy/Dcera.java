package pokusy;

/*******************************************************************************
 * Testovaci trida Dcera slouzi k demonstraci funkci, procesu a zavislosti,
 * ktere souviseji se zavedenm dedicnosti. 
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00,  05.2004
 */
public class Dcera
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int d_pocet  = 0;


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private int d_poradi = ++d_pocet;
    
    /** Nazev instance urceny pro pozdejsi identifikaci podobjektu. */
    private String nazev = "Dcera_" + d_pocet;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================
  
    /***************************************************************************
     * Metoda slouzi k demonstraci relativni nezavislosti statickych metod
     * na definici stejnojmenne metody v dcerine tride.     
     * 
     * @param text Text zpravy
     */
    public static void zprava( String text )
    {
        System.out.println( text + " (D)" );
    }
   
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vypise na stadnardni vystup zpravu o vytvoreni
     * instance s uvedenim jejiho poradi.
     * Jsou zde pripraveny dve verze:
     *  - Soucasna verze tela je urcena pro dostupny rodicovsky
     *    bezparametricky konstruktor.          
     *  - Zakomentovany prikaz this() se odkomentuje a druhy prikaz zakomentuje 
     *    tehdy, nebude-li rodicovsky bezparametricky konstruktor definovan
     *    nebo bude-li definovan jako soukromy.       
     */
    public Dcera()
    {
//         this( "" );
        System.out.println( "Vytvarim " + d_poradi + 
            ". instanci tridy Dcera " );
    }
    

    /***************************************************************************
     * Jednoparametricky konstruktor prida za zpravu, kterou by vypsal 
     * implicitni konsturktor jeste text, ktery prevzal jako parametr.
     * 
     * @param s Text charakterizujici danou instanci
     */
    public Dcera( String s )
    {
//        super( "- pro dceru " + s );
        System.out.println( "Vytvarim " + d_poradi + 
            ". instanci tridy Dcera " + s );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani verejnych metod 
     * volanych ze zdedenych metod.
     */    
//    @Override
    public void verejna()
    {
        System.out.println("  Trida Dcera, metoda verejna(): " + 
                         "\n    Podobjekt: " + nazev +
                         "\n    Instance:  " + this  );
    }
    

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani statickych metod
     * v hierarchii dedicnosti.
     */    
//    @Override
    public void zpravy()
    {
        zprava( "\nDcera - moje zprava" );
//         Matka .zprava( "- Zprava matky"  );
//         Dcera .zprava( "- Zprava dcery"  );
//         Vnucka.zprava( "- Zprava vnucky" );
    }
    

    
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Metoda slouzi k demonstraci vlivu prekryti volane metody
     * na funkci volajici metody.     
     */
    public void dcera() 
    {
        System.out.println( "\nMetoda dcera() instance " + this +
                            "\n  Nazev podobjektu: " + nazev );
        soukroma();
        verejna();
        System.out.println( nazev + ".dcera - konec");
    }


    /***************************************************************************
     * Metoda slouzi k demonstraci vlivu volani rodicovskych verzi 
     * prekrytych metod na zpusob jejich provedeni.
     */
    public void rodice()
    {
        System.out.println("Dcera - moje verze metody verejna():");
        verejna();
//        System.out.println("Dcera - rodicovska verze metody verejna():");
//        super.verejna();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani soukromych metod 
     * volanych ze zdedenych metod.
     */
    private void soukroma()
    {
        System.out.println("  Trida Dcera, metoda soukroma(): " + 
                         "\n    Podobjekt: " + nazev +
                         "\n    Instance:  " + this  );
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

