/*******************************************************************************
 * Trida Pocty slouzi k predvedeni vlastnosti nekterych binarnich operatoru.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, kveten 2004
 */
public class Pocty
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Metoda demonstruje na jedinem prikladu vlastnosti celociselneho deleni.
     * Priklad je jeden a napevno "zadratovany" proto, aby bylo mozno vysledky
     * lepe zarovnat.          
     */
    public static void deleni()
    {
        IO.zprava( "Vysledky deleni:" +
            "\ncela cisla         realna cisla" +
            "\n 12 /  5 = " +  12/ 5 + "       12.0 /  5   = " +  12.0/ 5   +
            "\n-12 /  5 = " + -12/ 5 +  "     -12   /  5.0 = " + -12  / 5.0 +
            "\n 12 / -5 = " +  12/-5 +  "      12.0 / -5.0 = " +  12  /-5.0 +
            "\n-12 / -5 = " + -12/-5 + "      -12.5 / -5.5 = " + -12.5/-5.5 );
    }
    

    /***************************************************************************
     * Metoda prevadi vysledky celociselneho a realneho deleni cisel.
     * 
     * @param citatel    Hodnota citatele testovanych zlomku
     * @param jmenovatel Hodnota jmenovatele testovanych zlomku
     */
    public static void deleni( double citatel, double jmenovatel )
    {
        double d1 = citatel;
        double d2 = jmenovatel;
        int i1 = (int) d1;
        int i2 = (int) d2;
        
        IO.zprava( "Vysledky deleni:" +
            "\ncela cisla         realna cisla" +
            "\n"      +  i1 + " / " +  i2 + " = " +   i1/i2  + 
            "       " +  d1 + " / " +  i2 + " = " +   d1/i2  +
            
            "\n"      + -i1 + " / " +  i2 + " = " +  -i1/i2  + 
            "       " + -i1 + " / " +  d2 + " = " +  -i1/d2  +

            "\n"      +  i1 + " / " + -i2 + " = " +   i1/-i2  + 
            "       " +  d1 + " / " + -i2 + " = " +   d1/-i2  +

            "\n"      + -i1 + " / " + -i2 + " = " +  -i1/-i2  + 
            "       " + -d1 + " / " + -d2 + " = " +  -d1/-d2  );
    }
    

    /***************************************************************************
     * Metoda demonstruje na jedinem prikladu vlastnosti deleni modulo.
     * Priklad je jeden a napevno "zadratovany" proto, aby bylo mozno vysledky
     * lepe zarovnat.          
     */
    public static void modulo()
    {
        IO.zprava( "Vysledky deleni modulo:" +
            "\ncela cisla         realna cisla" +
            "\n 12 %  5 = " +  12 % 5 + "      12.0 %  5   = " +  12.0 % 5   +
            "\n-12 %  5 = " + -12 % 5 +  "    -12   %  5.0 = " + -12   %-5.0 +
            "\n 12 % -5 = " +  12 %-5 + "      12.0 % -5.0 = " +  12.0 %-5.0 +
            "\n-12 % -5 = " + -12 %-5 +  "    -12.5 % -5.5 = " + -12.5 %-5.5 );
    }


    /***************************************************************************
     * Metoda prevadi vysledky celociselneho a realneho deleni modulo.
     * 
     * @param citatel    Hodnota citatele testovanych zlomku
     * @param jmenovatel Hodnota jmenovatele testovanych zlomku
     */
    public static void modulo( double citatel, double jmenovatel )
    {
        double d1 = citatel;
        double d2 = jmenovatel;
        int i1 = (int) d1;
        int i2 = (int) d2;
        
        IO.zprava( "Vysledky deleni modulo:" +
            "\ncela cisla         realna cisla" +
            "\n"      +  i1 + " % " +  i2 + " = " +   i1%i2  + 
            "       " +  d1 + " % " +  i2 + " = " +   d1%i2  +
            
            "\n"      + -i1 + " % " +  i2 + " = " +  -i1%i2  + 
            "       " + -i1 + " % " +  d2 + " = " +  -i1%d2  +

            "\n"      +  i1 + " % " + -i2 + " = " +   i1%-i2  + 
            "       " +  d1 + " % " + -i2 + " = " +   d1%-i2  +

            "\n"      + -i1 + " % " + -i2 + " = " +  -i1%-i2  + 
            "       " + -d1 + " % " + -d2 + " = " +  -d1%-d2  );
    }
 
    
    /***************************************************************************
     * Metoda demonstruje mozne pouziti vysledku operace prirazeni ve vyrazech.
     * 
     * @param a   Prvni z cisel vystupujich v predvadenych vyrazech.
     * @param b   Druhe z cisel vystupujich v predvadenych vyrazech.
    */
    public static void prirazeni( int a, int b )
    {
        int a2, b2, soucet, rozdil, soucin;
        String s1 = "Cisla: a=" + a + ",  b=" + b;
        String s2 = "\nMocniny: a2=" + (a2=a*a) + ",  b2=" + (b2=b*b);
        String s3 = "\nsoucet=" + (soucet=a+b) + ",  rozdil=" + (rozdil=a-b);
        String s4 = "\nRozdil mocnin (a2 - b2):  " + (a2 - b2);
        String s5 = "\nSoucin (a+b)*(a-b):  " + (soucet*rozdil);
        IO.zprava( s1 + s2 + s3 + s4 + s5 );
    }

    
    /***************************************************************************
     * Metoda demonstruje mozne pouziti slozeneho prirazeni ve vyrazech.
     * 
     * @param a   Prvni z cisel vystupujich v predvadenych vyrazech.
     * @param b   Druhe z cisel vystupujich v predvadenych vyrazech.
     */
    public static void slozene( int a, int b  )
    {
        String s = "\nProved: a+=a+b; b+=b; ";
        String s0 = "Start: a="   + a + ",   b=" + b;
        a += a + b;     
        b += b;
        String s1 = s + "poprve: a=" + a + ",   b=" + b;
        a += a + b;     
        b += b;
        String s2 = s + "podruhe: a=" + a + ",   b=" + b;
        a += a + b;     
        b += b;
        String s3 = s + "potreti: a=" + a + ",   b=" + b;         
        IO.zprava( s0 + s1 + s2 + s3 );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /** Soukromy konstruktor znemoznujici vytvoreni instance. */
    private Pocty() {}


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Souhrnny test jednotlivych metod.
     */
    public static void test()
    {
        deleni();
        deleni( 12, 5 );
        deleni( 12.5, 5.5 );
        modulo();
        modulo( 12, 5 );
        modulo( 12.5, 5.5 );
        prirazeni( 1, 2 );
        prirazeni( 3, 4 );
        slozene( 1, 2 );
        slozene( 3, 4 );
    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

