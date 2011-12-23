/*******************************************************************************
 * Instance tridy {@code KException} prestavuji ...
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class KException extends Exception
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori kontrolovanou vyjimku s popisem priciny jejiho vzniku.
     * 
     * @param zprava  Popisu duvodu vyhozeni vyjimky
     */
    public KException( String zprava )
    {
        super( zprava );
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
//
//    /***************************************************************************
//     * Testovaci metoda.
//     */
//    public static void test()
//    {
//        KException KException = new KException();
//    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

