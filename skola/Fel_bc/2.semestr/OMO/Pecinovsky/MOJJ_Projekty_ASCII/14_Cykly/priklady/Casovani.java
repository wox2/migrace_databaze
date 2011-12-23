package priklady;

/*******************************************************************************
 * Trida {@code Casovani} obsahuje nekolik statickym metod, ktere testuji
 * zrnitost udaju systemovych hodin a rychlost porcesoru.
 * Metody vypisuji pocet milisekund uplynuvsich mezi dvema tiky hodin
 * a soucasne take pocet behu cyklem mezi temito tiky.
 * Vetsina provadi tento test opakovane a tak nazorne demonstruje
 * nerovnomernou rychlost zpracovani programu.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class Casovani
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Jednorazovy test vyuzivajici cyklus do.
     */
    public static void testHodin_do()
    {
        long prvni = System.currentTimeMillis();
        int  dotaz = 0;
        long druhy;

        do{
            dotaz++;
            druhy = System.currentTimeMillis();
        }while( prvni == druhy );
        System.out.println("Tik " + (druhy-prvni) +
                           " ms po " + dotaz + ". cteni" );
    }


    /***************************************************************************
     * Opakovani predchoziho testu v cyklu while.
     * @param pokusu Kolikrat budeme probihat cyklem
     */
    public static void testHodin_while( int pokusu )
    {
        int pokus = 1;

        System.out.println("===== ZACATEK TESTU WHILE =====");
        while( pokus <= pokusu )
        {
            System.out.print( "Pokus " + pokus + ": " );
            testHodin_do();
            pokus++;
        }
        System.out.println("===== KONEC TESTU WHILE =====");
    }


    /***************************************************************************
     * Opakovani predchoziho testu v cyklu while.
     * @param pokusu Kolikrat budeme probihat cyklem
     */
    public static void testHodin_for( int pokusu )
    {
        System.out.println("===== ZACATEK TESTU FOR =====");
        for( int pokus = 1;   pokus <= pokusu;   pokus++ )
        {
            System.out.print( "Pokus " + pokus + ": " );
            testHodin_do();
        }
//      System.out.println("Provedli jsme " + pokus + " pokusu" );
        System.out.println("===== KONEC TESTU FOR =====");
    }


    /***************************************************************************
     * Opakovani predchoziho testu v cyklu while.
     */
    public static void nekonecny()
    {
        System.out.println("===== ZACATEK NEKONECNEHO TESTU =====");
        for(;;)
        {
            testHodin_do();
        }
    //      System.out.println("===== KONEC NEKONECNEHO TESTU =====");
    }


    /***************************************************************************
     * Slouceni obou predchozich testu do jedine metody.
     * @param pokusu Kolikrat budeme probihat cyklem
     */
    public static void testHodin_vnoreny( int pokusu )
    {
        System.out.println("===== ZACATEK TESTU S VNORENYM CYKLEM =====");

        long minule = System.currentTimeMillis();

        for( int pokus = 1;   pokus <= pokusu;   pokus++ )
        {
            int  dotaz = 0;
            long nyni;

            do{
                dotaz++;
                nyni = System.currentTimeMillis();
            }while( nyni == minule );
            System.out.println("Pokus " + pokus +
                               ": tik " + (nyni-minule) +
                               " ms po " + dotaz + ". cteni" );
            minule = nyni;
        }
        System.out.println("===== KONEC TESTU S VNORENYM CYKLEM =====");
    }


    /***************************************************************************
     * Slouceni obou predchozich testu do jedine metody.
     * @param sekund Jak dlouho budeme probihat cyklem
     */
    public static void testHodin_uprostred( int sekund )
    {
        System.out.println("===== ZACATEK TESTU S PEVNOU DOBOU MERENI =====");

        long start  = System.currentTimeMillis();
        long minule = start;

        for(;;)
        {
            long nyni;
            int  dotaz = 0;

            do{
                nyni = System.currentTimeMillis();
                dotaz++;
            }while( nyni == minule );
            long meri_s = (int)((nyni - start)/1000);

            if( meri_s  >=  sekund ) {  //Podminka testujici setrvani v cyklu
                break;                  //Prikaz k ukonceni cyklu
            }
            int meri_ms = (int)((nyni - start)%1000);
            System.out.println("Cas " + meri_s + "s, " + meri_ms +
                               "ms, tik " + (nyni-minule) +
                               " ms po " + dotaz + ". cteni" );
            minule = nyni;
        }
        System.out.println("===== KONEC TESTU S PEVNOU DOBOU MERENI =====");
    }


    /***************************************************************************
     * Metoda ukazuje, ze v nekterych pripadech muze mit cyklus prazdne telo.
     * @param sekund Jak dlouho budeme probihat cyklem
     */
    @SuppressWarnings("empty-statement")
    public static void testHodin_bezTela( int sekund )
    {
        System.out.println("===== ZACATEK TESTU S PRAZDNYM TELEM =====");
        long start  = System.currentTimeMillis();
        long minule = start;

        for(;;)
        {
            while( minule == System.currentTimeMillis() );
            long nyni = System.currentTimeMillis();
            long meri_s = (int)((nyni - start)/1000);

            if( meri_s  >=  sekund ) {  //Podminka testujici setrvani v cyklu
                break;                  //Prikaz k ukonceni cyklu
            }
            int meri_ms = (int)((nyni - start)%1000);
            System.out.println("Cas " + meri_s + "s, " + meri_ms +
                               "ms, tik " + (nyni-minule) );
            minule = nyni;
        }
        System.out.println("===== KONEC TESTU S PRAZDNYM TELEM =====");
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================
    
    /** Soukromy konstruktor branici vytvoreni instance. */
    private Casovani() {}
    
    
//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

