/*******************************************************************************
 * Instance tridy {@code MojeException} prestavuji ...
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class NKException extends RuntimeException
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
     * Vytvori nekontrolovanou vyjimku s popisem priciny jejiho vzniku.
     * 
     * @param zprava  Popisu duvodu vyhozeni vyjimky
     */
    public NKException( String zprava )
    {
        super( zprava );
    }


    /***************************************************************************
     * Vytvori nekontrolovanou vyjimku s popisem priciny jejiho vzniku.
     * 
     * @param zprava  Popisu duvodu vyhozeni vyjimky
     * @param pricina Vyjimka, ktera byla pricinou vyhozeni teto vyjimky
     */
    public NKException( String zprava, Throwable pricina )
    {
        super( zprava, pricina );
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
//        MojeException MojeException = new MojeException();
//    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

