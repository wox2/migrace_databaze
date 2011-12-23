/*******************************************************************************
 * Instance tridy {@code ZachyceniVyjimky} prestavuji ...
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class ZachyceniVyjimky
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final String TEST = "\n==================\n";
    private static final String SADA = "\n##################\n";
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================
    
    /***************************************************************************
     * Metoda demonstruje zachyceni vyjimky a zpravu o nem.
     * @param hloubka Kolikrat se jeste budeme rekurzivne zanorovat
     */
    public static void rekurze1( int hloubka ) 
    {
        System.out.println("+++++ Prichod na hloubku "  + hloubka);
        int zarazka = 1 / hloubka;
        System.out.println("      Deleni v hloubce " + hloubka + " se povedlo");
        try {
            rekurze1( hloubka - 1 );
        }catch( RuntimeException rex ) {
            //Bude chodit i pro typ ArithmeticException
            System.out.println("xxxxx V hloubce " + hloubka + " prisla " + rex);
            rex.printStackTrace(System.out);
        }
        System.out.println("----- Konec akce v hloubce " + hloubka);
    }

    
    /***************************************************************************
     * Metoda demonstruje soucasne odchytavani ruznych typu vyjimek.
     * @param hloubka Kolikrat se jeste budeme rekurzivne zanorovat
     */
    public static void rekurze2( int hloubka ) 
    {
        System.out.println("+++++ Prichod na hloubku "  + hloubka);
        try {
            multitest(hloubka);
            System.out.println("     Test v hloubce " + hloubka + " prosel");
            rekurze2( hloubka - 1 );
        }
        catch( ArithmeticException e ) {
            System.out.println("xxxxx Neco se nespocitalo - " + e );
        }
        catch( NullPointerException e ) {
            System.out.println("xxxxx Prazdny ukazatel - " + e );
        }
        catch( IndexOutOfBoundsException e ) {
            System.out.println("xxxxx Index mimo meze - " + e );
        }
        catch( RuntimeException e ) {
            System.out.println("xxxxx Nekde se stala chyba - " + e );
        }
        catch( Throwable e ) {
            System.out.println("xxxxx Neco se stalo - " + e );
        }
        System.out.println("----- Konec akce v hloubce " + hloubka);
    }

    
    /***************************************************************************
     * Metoda demonstruje pouziti bloku {@code finally}.
     * @param hloubka Kolikrat se jeste budeme rekurzivne zanorovat
     */
    public static void rekurze3( int hloubka ) 
    {
        System.out.println("+++++ Prichod na hloubku "  + hloubka);
        String zprava =    "      Hl." + hloubka + ": ";
        try {
            multitest(hloubka);
            zprava += "Test v poradku";
            rekurze3( hloubka - 1 );
            zprava += " vcetne volani nizsi hladiny";
        }
        catch( ArithmeticException e ) {
            zprava += " x Neco se nespocitalo - " + e;
        }
        catch( NullPointerException e ) {
            zprava += " x Prazdny ukazatel - " + e;
            throw new RuntimeException("z hloubky " + hloubka);
        }
        catch( IndexOutOfBoundsException e ) {
            zprava += " x Index mimo meze - " + e;
        }
        catch( RuntimeException e ) {
            zprava += " x Nekde se stala chyba - " + e;
        }
        catch( Throwable e ) {
            zprava += " x Neco se stalo - " + e ;
        }
        finally {
            System.out.println(zprava);
        }
        System.out.println("----- Konec akce v hloubce " + hloubka);
    }


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /** Soukromy konstruktor branici vytvoreni instance. */
    private ZachyceniVyjimky() {}


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
    
    private static void multitest(int hloubka) 
    {
        //Pro hloubku 0
        int    h0 = 1 / hloubka;
        //Pro zaporne a hodne velke hloubky
        char   hM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(hloubka + 5);
        //Pro hloubky delitelne 7
        String h7 = (hloubka%7 == 0) ?  null  :  "";
               h7.toString();
    }

    
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================


    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test1()
    {
        rekurze1(3);    
    }


    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test2()
    {
        rekurze2( 3);   System.out.println(TEST);
        rekurze2(-3);   System.out.println(TEST);
        rekurze2( 9);  
    }

    
    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test3()
    {
        rekurze3( 3);   System.out.println(TEST);
        rekurze3(-3);   System.out.println(TEST);
        rekurze3( 9); 
    }

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test()
    {
        test1();   System.out.println(SADA);
        test2();   System.out.println(SADA);
        test3();   System.out.println(SADA);
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

