package priklady;

import rup.cesky.tvary.Barva;

import rup.cesky.tvary.Cara;
import rup.cesky.tvary.Kruh;


/*******************************************************************************
 * Prazdna trida pro vytvoreni terce zadaneho v 10. kapitole.
 */
public class Terc extends Kruh
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final Barva B1 = Barva.ZLUTA;
    private static final Barva B2 = Barva.MODRA;
    private static final Barva B3 = Barva.CERVENA;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final Kruh mezi;
    private final Kruh stred;
    private final Cara vodor;
    private final Cara svisla;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     * @param barva1  Barva vnejsiho kruhu
     * @param barva2  Barva stredniho kruhu, mezikruhu
     * @param barva3  Barva centralniho kruhu
     */
    public Terc(int x, int y, int prumer, 
                Barva barva1, Barva barva2, Barva barva3)
    {
        //Prazdne telo, kteer je treba teprve zaplnit.
        super();
        mezi  = null;
        stred = null;
        vodor = null;
        svisla= null;
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
//    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

