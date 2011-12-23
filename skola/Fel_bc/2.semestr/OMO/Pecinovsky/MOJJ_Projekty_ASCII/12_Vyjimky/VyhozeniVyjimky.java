/*******************************************************************************
 * Instance tridy {@code VyhozeniVyjimky} prestavuji ...
 *
 * @author    jmeno autora
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class VyhozeniVyjimky
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Metoda demonstruje vyhozeni vyjimky.
     */
    public static void vyhozeni() 
    {
        throw new RuntimeException("\nCvicne vyhazovana vyjimka");
    }


    /***************************************************************************
     * Metoda demonstruje moznost vytvorit vyjimku na jinem miste, 
     * nez kde bude vyhozena.
     */
    public static void nejprveUklid() 
    {
        System.err.println("Obstaravam prostredky");
        System.err.println("Zacinam vykonavat pozadovanou operaci");
        RuntimeException rex = new RuntimeException(
                "\nOperace zhavarovaly protoze ...");
        System.err.println("Vyjimka je vytvorena - bude se vyhazovat");
        throw rex;
    }

    
    /***************************************************************************
     * Metoda demonstruje nedsazitelnost kodu za vyhozenim vyjimky.
     */
    public static void nedosazitelna() {
        System.err.println("Pred vyhozenim vyjimky");
        throw new RuntimeException("\nZa mnou uz se nic neudela");
//        System.err.println("Po vyhozeni vyjimky");
    }
    
    
    /***************************************************************************
     * Demonstrace pouziti metod vyjimek.
     */
    public static void metodyVyjimek() {
        RuntimeException vyjimka = new RuntimeException("Popis priciny");
        System.out.println("Zprava:   " + vyjimka.getMessage() +
                         "\ntoString: " + vyjimka +
                         "\nZasobnik:" );
        vyjimka.printStackTrace(System.out);
        System.err.println("===== Zacatek");
        vyjimka.printStackTrace();
        System.err.println("===== Konec");
    }

    
//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /** Soukromy konstruktor branici vytvoreni instance. */
    private VyhozeniVyjimky() {}


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test()
    {
        metodyVyjimek();
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

