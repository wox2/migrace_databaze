package pokusy;

/*******************************************************************************
 * Testovaci trida Vnucka slouzi k demonstraci funkci, procesu a zavislosti,
 * ktere souviseji se zavedenm dedicnosti.
 * Zakomentovani nekterych jejich casti umoznuje jeji preklad
 * i v situaci, kdy neni jeste potomkem tridy {@link Dcera}.
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00,  05.2004
 */
public class Vnucka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int v_pocet  = 0;


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private int v_poradi = ++v_pocet;

    /** Nazev instance urceny pro pozdejsi identifikaci podobjektu. */
    private String nazev = "Vnucka_" + v_pocet;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Metoda slouzi k demonstraci relativni nezavislosti statickych metod
     * na definici stejnojmenne metody v dcerine tride.
     * V teto tride je metoda zakomentovana proto, aby bylo lze pozorovat
     * chovani instance v pripade, kdy metoda neni prekryta.
     * 
     * @param text Text zpravy
     */
    public static void zprava( String text )
    {
        System.out.println( text + " (V)" );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vypise na stadnardni vystup zpravu o vytvoreni
     * instance s uvedenim jejiho poradi.
     * Jsou zde pripraveny dve verze:
     */
    public Vnucka()
    {
        System.out.println( "Vytvarim " + v_poradi +
            ". instanci tridy Vnucka " );
    }


    /***************************************************************************
     * Jednoparametricky konstruktor prida za zpravu, kterou by vypsal
     * implicitni konsturktor jeste text, ktery prevzal jako parametr.
     * 
     * @param s Text charakterizujici danou instanci
     */
    public Vnucka( String s )
    {
//        super( "- pro vnucku " + s );
        System.out.println( "Vytvarim " + v_poradi +
            ". instanci tridy Vnucka " + s);
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
        System.out.println("  Trida Vnucka, metoda verejna(): " +
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
        zprava( "\nVnucka - moje zprava" );
//         Matka .zprava( "- Zprava matky"  );
//         Dcera .zprava( "- Zprava dcery"  );
//         Vnucka.zprava( "- Zprava vnucky" );
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Metoda slouzi k demonstraci vlivu prekryti volane metody
     * na funkci volajici metody.
     */
    public void vnucka()
    {
        System.out.println( "\nMetoda vnucka() instance " + this +
                            "\n  Nazev podobjektu: " + nazev );
        soukroma();
        verejna();
        System.out.println( nazev + ".vnucka - konec");
    }


    /***************************************************************************
     * Metoda slouzi k demonstraci vlivu volani rodicovskych verzi
     * prekrytych metod na zpusob jejich provedeni.
     * Oproti rodicovske verzi zde prybylo volani prekryte verze teto metody.
     */
//    @Override
    public void rodice()
    {
        System.out.println("Vnucka - moje verze metody verejna():");
        verejna();
//        System.out.println("Vnucka - rodicovska verze metody verejna():");
//        super.verejna();
//        System.out.println("\nVnucka - rodicovska verze metody rodice():\n");
//        super.rodice();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Metoda slouzi k demonstraci chovani soukromych metod
     * volanych ze zdedenych metod.
     */
    private void soukroma()
    {
        System.out.println("  Trida Vnucka, metoda soukroma(): " +
                         "\n    Podobjekt: " + nazev +
                         "\n    Instance:  " + this  );
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

