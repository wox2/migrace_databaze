package priklady;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Podminky
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    public static boolean jeVelke( int cislo )
    {
        final int MOC = 1000000;    //milion
        return cislo > MOC;
    }


    /***************************************************************************
     * Prvni verze metody, ktera vrati informaci o tom,
     * lezil-li hodnota jejiho prostredniho parametru
     * mezi hodnotami zbylych dvou.
     * 
     * @param a  Spodni mez intervalu
     * @param x  Testovana hodnota
     * @param b  Horni mez intervalu
     * @return {@code true} lezi-li testovana hodnota v intervalu
     */
    public boolean mezi1( int a, int x, int b )
    {
        if( ((a < x)  &&  (x < b))  ||      //  a < x < b
            ((b < x)  &&  (x < a))  )       //  b < x < a
        {
            return true;
        }
        return false;
    }


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
    public Podminky()
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

