/*******************************************************************************
 * Instance tridy {@code KontrolovaneVyjimky} prestavuji ...
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class KontrolovaneVyjimky
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================
    
    /***************************************************************************
     * Metoda vyhazujici kontrolovanou vyjimku.
     * @throws KException Popis vyhazovane vyjimky
     */
    public static void vyhazujeKE() 
           throws KException
    {
        throw new KException("Kontrolovana vyjimka");
    }


    /***************************************************************************
     * Metoda vyhazujici kontrolovanou vyjimku.
     * @throws NKException Popis vyhazovane vyjimky
     */
    public static void vyhazujeNKE() 
           throws NKException   //, KException
    {
        throw new NKException("Nekontrolovana vyjimka");
    }


    /***************************************************************************
     * Vola metody vyhazujici kontrolovane i nekontrolovane vyjimky
     * a demonstruje rozdilny pristup prekladace k nutnosti jejich osetreni.
     */
    public static void volaVyhazovaci() 
//           throws KException
    {
        vyhazujeNKE();
//        vyhazujeKE();
        try {
            vyhazujeKE();
        }catch(KException e) {
            System.out.println("Osetreni kontrolovane vyjimky: " + e);
        }
    }
    

    /***************************************************************************
     * Demonstruje prevedeni nekontrolovane vyjimky na kontrolovanou.
     */
    public static void prevedeni() 
    {
        try {
            vyhazujeKE();
        }catch(KException e) {
            throw new NKException("Necekane vyhozena vyjimka", e);
        }
    }
    
    
    /***************************************************************************
     * Metoda demonstrujici zpusob zobrazeni informace o prevedeni
     * jedne vyjimky na jinou.
     * @param hloubka Kolikrat se jeste budeme rekurzivne zanorovat
     */
    public static void rekurze4( int hloubka ) {
        System.err.println("+++++ Prichod na hloubku "  + hloubka);
        try {
            int zarazka = 1 / hloubka;
            rekurze4( hloubka - 1 );
        }catch( RuntimeException rex ) {
            System.err.println("V hloublce " + hloubka + " prisla " + rex );
            rex.printStackTrace();
            System.err.println("========================================");
            throw new RuntimeException( "Hloubka " + hloubka, rex);
        }
        System.err.println("----- Konec akce v hloubce " + hloubka);
    }


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /** Soukromy konstruktor branici vytvoreni instance. */
    private KontrolovaneVyjimky() {}


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
        rekurze4(3);
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

