/*******************************************************************************
 * Trida ma za ukol otestovat chovani inkrementacnich a dekrementacnich
 * operatoru.
 */
public class Xkrementy_5
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    //i, j, k jsou definovany jako staticke atributy,
    //aby je metoda test mohla sdilet s metodou zobraz
    private static int     i, j, k;

    //Atribut vse slouzi jako akumulator vytvareneho retezce
    private static String  vse = "";


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Metoda overi, ze se operatory chovaji tak,
     * jak je v ucebnici uvedeno v tabulce.
     */
    public static void test()
    {
        //Staticke atributy jsou na pocatku nulove => staci nastavit i
        i = 1;              zobraz( "Pocatecni hodnoty" );
        j = i++;            zobraz( "Po (j = i++)" );
        k = ++i;            zobraz( "Po (k = ++i)" );
        k = i-- + j--;      zobraz( "Po (k = i-- + j--)" );
        k = --i + --j;      zobraz( "Po (k = --i + --j)" );
        IO.zprava( vse );
    }


    /***************************************************************************
     * Pomocna metoda, ktera zobrazi posledni akci a
     * vytiskne pomoci metody IO.zprava() hodnoty vsech ciselnych atributu.
     * Zaroven prida tisteny text do souhrneho retezce,
     * ktery se bude tisknout na zaver.
     */
    private static void zobraz( String text )
    {
        text = text + ":  i=" + i + ",  j=" + j + ",  k=" + k;
        IO.zprava( text );
        vse = vse + "\n" + text;
    }


    /***************************************************************************
     * Parametricka verze metody test umoznujici zadani vychozich hodnot
     * promennych a nepouzivajici atributy,
     * protoze je nahrazuje je lokalnimi promennymi.
     * 
     * @param i
     * @param j
     * @param k 
     */
    public static void test( int i, int j, int k )
    {
        i = 1;            vse  = zobraz( i, j, k, "Pocatecni hodnoty");
        j = i++;          vse += '\n' + zobraz( i, j, k, "Po (j = i++)" );
        k = ++i;          vse += '\n' + zobraz( i, j, k, "Po (k = ++i)" );
        k = i-- + j--;    vse += '\n' + zobraz( i, j, k, "Po (k = i-- + j--)" );
        k = --i + --j;    vse += '\n' + zobraz( i, j, k, "Po (k = --i + --j)" );
        IO.zprava( vse );
    }


    /***************************************************************************
     * Pomocna metoda pro parametrickou verzi metody test.
     * Zobrazi posledni akci a vytiskne pomoci metody IO.zprava()
     * hodnoty vsech ciselnych parametru.
     *
     * @return Vytisteny retezec.
     */
    private static String zobraz( int i, int j, int k, String text )
    {
        text = text + ":  i=" + i + ",  j=" + j + ",  k=" + k;
        IO.zprava( text );
        return text;
    }


    /***************************************************************************
     * Bezparametricka verze testu, ktera sve vysledky nezobrazuje
     * v dialogovem okne, ale posila je na standardni vystup.
     */
    public static void ttest()
    {
        System.out.println("\n===============================================");
        i = 1;              tiskni( "start");
        j = i++;            tiskni( "j = i++" );
        k = ++i;            tiskni( "k = ++i");
        k = i-- + j--;      tiskni( "k = i-- + j--");
        k = --i + --j;      tiskni( "k = --i + --j");
    }


    /***************************************************************************
     * Pomocna metoda, ktera vytiskne zadanou akci na standarni vystup.
     */
    private static void tiskni( String akce )
    {
        //Dvojice znaku \t v nasledujicim prikazu oznacuje znak tabulatoru
        System.out.println( "Po (" + akce + "):\ti="
                            + i + ",  j=" + j + ",  k=" + k );
    }


    /***************************************************************************
     * Bezparametricka verze testu, ktera sve vysledky nezobrazuje
     * v dialogovem okne, ale posila je na standardni vystup.
     */
    public static void etest()
    {
        System.err.println("\nEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        i = 1;              etiskni( "E-start");
        j = i++;            etiskni( "j = i++" );
        k = ++i;            etiskni( "k = ++i");
        k = i-- + j--;      etiskni( "k = i-- + j--");
        k = --i + --j;      etiskni( "k = --i + --j");
    }


    /***************************************************************************
     * Pomocna metoda, ktera vytiskne zadanou akci na standarni vystup.
     */
    private static void etiskni( String akce )
    {
        //Dvojice znaku \t v nasledujicim prikazu oznacuje znak tabulatoru
        System.err.println( "EPo (" + akce + "):\ti="
                            + i + ",  j=" + j + ",  k=" + k );
    }


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     *
     */
    private Xkrementy_5() {}


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

}


