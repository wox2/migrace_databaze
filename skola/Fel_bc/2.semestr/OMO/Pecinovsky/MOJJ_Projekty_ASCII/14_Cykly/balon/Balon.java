package balon;

import rup.cesky.tvary.Barva;


/*******************************************************************************
 * Instance tridy {@code Balon} simuluji gumovy balon, ktery pada kolmo dolu
 * a odrazi se od spodniho okraje platna.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Balon 
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor umiste balon implicitni velikosti a barvy
     * do leveho horniho rohu platna a necha jej spadnout.
     */
    public Balon()
    {
    }


    /***************************************************************************
     * Konstruktor vytvori balon zadane velikosti a barvy, umisti jej 
     * na zadanou pozici a necha jej odtud spadnout.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     * @param barva   Barva vytvareneho ctverce
     */
    public Balon( int x, int y, int prumer, Barva barva )
    {
    }

    

//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
    

    /***************************************************************************
     * Premisti balon na zadanou pozici a necha jej spadnout.
     * @param x
     * @param y 
     */
    public void presunNa( int x, int y )
    {
    }

    
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

