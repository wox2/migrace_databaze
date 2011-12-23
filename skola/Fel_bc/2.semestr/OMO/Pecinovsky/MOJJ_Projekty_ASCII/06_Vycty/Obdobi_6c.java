/*******************************************************************************
 * Trida {@code Obdobi} slouzi k demonstraci definice a pouziti vyctoveho typu.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000, 0.0.2008
 */
public enum Obdobi_6c
{
//== HODNOTY VYCTOVEHO TYPU ====================================================

    JARO("puci"), LETO("zraje"), PODZIM("plodi"), ZIMA("spi");
    
    
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final String cinnost;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Bezparametricky konstruktor ...
     */
    private Obdobi_6c( String cinnost )
    {
        this.cinnost = cinnost;
    }


//== ABSTRAKTNI METODY =========================================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
    
    /***************************************************************************
     * Vrati zpravu charakterizujici dane rocni obdobi.
     * @return Pozadovana zprava
     */
    public String hlaseni()
    {
        //Pouzita metoda toLowerCase() prevede retezec na mala pismena
        return "Ja jsem " + name() + 
               ". Kdyz je " + name().toLowerCase() + 
               ", priroda " + cinnost + ".";
    }    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test()
    {
        System.out.println( JARO  .hlaseni() );
        System.out.println( LETO  .hlaseni() );
        System.out.println( PODZIM.hlaseni() );
        System.out.println( ZIMA  .hlaseni() );
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

