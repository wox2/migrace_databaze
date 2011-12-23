package priklady;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class BlokPrikazu
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Prvni verze metody, ktera vrati informaci o tom,
     * lezil-li hodnota jejiho prostredniho parametru
     * mezi hodnotami zbylych dvou.
     */
    public static void blok_1()
    {
        int hodnota = -1;
        System.out.println("\f");
        if( hodnota < 0 )
        {
            String mensi = " - hodnota je mensi nez nula!";
            System.out.println("Uvnitr: hodnota=" + hodnota + mensi );
        }
        System.out.println("Vne: dvojnasobek=" + 2*hodnota );
    //    System.out.println("Vne: mensi=" + mensi );
    }


    /***************************************************************************
     * Prvni verze metody, ktera vrati informaci o tom,
     * lezil-li hodnota jejiho prostredniho parametru
     * mezi hodnotami zbylych dvou.
     */
    public static void blok_2()
    {
        int hodnota = -1;
        System.out.println("\f");
        if( hodnota <= 0 )
        {
            String mensi = " - hodnota je mensi nebo rovna nula!";
            System.out.println("Uvnitr: hodnota=" + hodnota + mensi );
            
            if( hodnota < 0 )
            {
                String mocMala = "Je dokonce zaporna";
                int i = 0;
                System.out.println( "Jeste hur - " + mocMala );
                
                {
                    //Jen si tu na chvili neco deklaruji
                    int cislo = 5;
                    System.out.println( "Zcela uvnitr je cislo " + cislo );
                }
    //             System.out.println( "Nedosazitelna promenna: " + cislo );        
            }
    //         System.out.println( "Nedosazitelna promenna: " + mocMala );        
        }
        System.out.println("Vne: dvojnasobek=" + 2*hodnota );
    //     System.out.println("Vne: mensi=" + mensi );
    }


    /***************************************************************************
     * Prvni verze metody, ktera vrati informaci o tom,
     * lezil-li hodnota jejiho prostredniho parametru
     * mezi hodnotami zbylych dvou.
     */
    public static void blok_E()
    {
        int hodnota = -1;
        System.err.println("\f");
        if( hodnota < 0 )
        {
            System.err.println("Pred vyhozenim vyjimky");
            RuntimeException rte = new RuntimeException();
            System.err.println( "" + rte );
            System.err.println("======================");
            rte.printStackTrace( System.err );
//             System.err.println("Po vyhozeni vyjimky");
            throw rte;
        }
        System.err.println("Za podminenym prikazem" );
    }


    
////Nasleduje dalsi blok prikazu
//{
//    String zcelaUvnitr = "Ted jsem jeste vic uvnitr";
//    System.out.println( zcelaUvnitr );
//}


    /***************************************************************************
     * Druha verze metody, ktera vrati informaci o tom,
     * lezil-li hodnota jejiho prostredniho parametru
     * mezi hodnotami zbylych dvou.
     * 
     * @param a  Spodni mez intervalu
     * @param x  Testovana hodnota
     * @param b  Horni mez intervalu
     * @return {@code true} lezi-li testovana hodnota v intervalu
     */
    public boolean mezi2( int a, int x, int b )
    {
        return ((a < x)  &&  (x < b))  ||      //  a < x < b
               ((b < x)  &&  (x < a));         //  b < x < a
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor tridy Trida
     */
    public BlokPrikazu()
    {
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

