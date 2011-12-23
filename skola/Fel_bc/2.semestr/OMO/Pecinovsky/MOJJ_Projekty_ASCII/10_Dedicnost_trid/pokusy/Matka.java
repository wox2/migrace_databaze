package pokusy;

/*******************************************************************************
 * Testovaci trida Matka slouzi k demonstraci funkci, procesu a zavislosti,
 * ktere souviseji se zavedenm dedicnosti. 
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00,  05.2004
 */
public class Matka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int m_pocet  = 0;


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private int m_poradi = ++m_pocet;
    
    /** Nazev instance urceny pro pozdejsi identifikaci podobjektu. */
    private String nazev = "Matka_" + m_pocet;


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
        System.out.println( text + " (M)" );
    }
   
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vypise na stadnardni vystup zpravu o vytvoreni
     * instance s uvedenim jejiho poradi.     
     */
    public Matka()
    {
        System.out.println( "\nVytvarim " + m_poradi + 
            ". instanci tridy Matka " );
    }
    

    /***************************************************************************
     * Jednoparametricky konstruktor prida za zpravu, kterou by vypsal 
     * implicitni konsturktor jeste text, ktery prevzal jako parametr.
     * 
     * @param s Text charakterizujici danou instanci
     */
    public Matka( String s )
    {
        System.out.println( "\nVytvarim " + m_poradi + 
            ". instanci tridy Matka " + s );
    }
    


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Metoda slouzi k demonstraci vlivu prekryti volane metody
     * na funkci volajici metody.     
     */
    public void matka() 
    {
        System.out.println( "\nMetoda matka() instance " + this +
                            "\n  Nazev podobjektu: " + nazev );
        soukroma();
        verejna();
        System.out.println( nazev + ".matka - konec");
    }
    

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani verejnych metod 
     * volanych ze zdedenych metod.
     */    
    public void verejna()
    {
        System.out.println("  Trida Matka, metoda verejna(): " + 
                         "\n    Podobjekt: " + nazev +
                         "\n    Instance:  " + this  );
    }
    

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani statickych metod
     * v hierarchii dedicnosti.
     */    
    public void zpravy()
    {
        zprava( "\nMatka - moje zprava" );
//         Matka .zprava( "- Zprava matky"  );
//         Dcera .zprava( "- Zprava dcery"  );
//         Vnucka.zprava( "- Zprava vnucky" );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani soukromych metod 
     * volanych ze zdedenych metod.
     */
    private void soukroma()
    {
        System.out.println("  Trida Matka, metoda soukroma(): " + 
                         "\n    Podobjekt: " + nazev +
                         "\n    Instance:  " + this  );
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

