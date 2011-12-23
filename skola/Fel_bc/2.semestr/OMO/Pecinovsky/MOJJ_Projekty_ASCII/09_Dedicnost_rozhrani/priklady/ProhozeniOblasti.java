package priklady;

import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Oblast;
import rup.cesky.tvary.SpravcePlatna;

import rup.cesky.utility.IO;


/*******************************************************************************
 * Instance tridy {@code ProhozeniOblasti} prestavuji ...
 *
 * @author    jmeno autora
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class ProhozeniOblasti
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Prohodi oblasti zaujimane zadanymi hybacimi objekty;
     * 
     * @param ih1   Prvni z prohazovanych objektu
     * @param ih2   Druha z prohazovanych objektu
     */
    public static void prohodOblasti(IHybaci ih1, IHybaci ih2) 
    {
       Oblast o1 = Oblast.get( ih1 );
       Oblast o2 = Oblast.get( ih2 );
       Oblast.set( ih1, o2 );
       Oblast.set( ih2, o1 );
    }

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /** Soukromy konstruktor branici vytvoreni instance. */
    private ProhozeniOblasti() {}

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
        SpravcePlatna SP = SpravcePlatna.getInstance();
        IHybaci o = new Obdelnik(  0,   0, 150, 100);
        IHybaci e = new Elipsa  (200, 150, 100, 150);
        SP.pridej( o );
        SP.pridej( e );
        IO.zprava("Pripraveno");
        prohodOblasti( o, e );
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

