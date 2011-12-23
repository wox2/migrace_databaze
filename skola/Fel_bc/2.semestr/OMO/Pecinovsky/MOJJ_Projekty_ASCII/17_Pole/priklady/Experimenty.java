package priklady;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Experimenty
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Metoda demonstruje predani parametru, ktery je polem 
     * a moznost jeho zpracovani.
     * Prestoze deklarovany typ navratove hodnoty je Object,
     * programy v nem umeji poznat instanci typu String[].
     * 
     * @param poleRetezcu   Pole (typu String[]) s nimz experimentujeme
     * @return Vstupni parametr pretypovany na Object
     */
    public static Object objekty( String[] poleRetezcu )
    {
        Object[] poleObecnychObjektu = poleRetezcu;
        
        return   poleObecnychObjektu;
    }
    
    
    /***************************************************************************
     * 
     * @param poleCelychCisel
     * @return
     */
    public static Object primitiva( int[] poleCelychCisel )
    {
        double[] poleRealnychCisel;

        //poleRealnychCisel = poleCelychCisel;

        int pocetPrvku = poleCelychCisel.length;
        poleRealnychCisel = new double[ pocetPrvku ];

        for( int i=0;   i < pocetPrvku;   i++ ) {
            poleRealnychCisel[i] = poleCelychCisel[i];
        } 

        return poleRealnychCisel;
    }

    
    /***************************************************************************
     * 
     */
    public static void demoObjekty()
    {
        String[] texty = { "raz", "dva", "tri" };

        System.out.println("Objekty - primy tisk pole: " + texty );

        System.out.print("Objekty - tisk po prvcich: ");
        for( String string : texty ) {
            System.out.print(string + " - ");
        }
        System.out.println();

        System.out.println("Objekty - tisk prevodem na seznam: " +
            java.util.Arrays.asList( texty ) );

        Object objekty = objekty( texty );
        Object[] poleObjektu = (Object[]) objekty;
        String[] stringy = (String[])objekty;

        System.out.println("Objekty - tisk jako pole objektu: " +
            java.util.Arrays.asList( poleObjektu ) );
    }
    
    
    /***************************************************************************
     * 
     */
    public static void demoPrimitiva()
    {
        Object primitiva = primitiva( new int[] { 1, 2, 3 } );

        System.out.print("Primitiva - tisk po prvcich: ");
        
        double[] realna = (double[])primitiva;
        for( double realne : realna ) {
            System.out.print(realne + " - ");
        }
        System.out.println();

        System.out.println("Primitiva - tisk prevodem na seznam: " +
            java.util.Arrays.asList( realna ) );

        int[] cela = (int[])primitiva;
    }
    
    
    public static String[][] dovjrozmerne()
    {
        String[][] tabulka = new String[2][3];
        tabulka[0][1] = "nula - jedna";
        return tabulka;
    }
    
//    
//    /***************************************************************************
//     * 
//     * @return
//     */
//    public static rup.cesky.tvary.AHybaci pozna()
//    {
//        rup.cesky.tvary.Elipsa e = new rup.cesky.tvary.Kruh();
//        e.zobraz();
//        e.posunVpravo();
//        rup.cesky.tvary.AHybaci ah = e;
//        ah.posunDolu();
//        return ah;
//    }
//    
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Trida Experimenty je knihovni tridou a proto zatajuje svuj konstruktor.
     */
    private Experimenty() {}

    

//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

