package sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Smer8;


/*******************************************************************************
 * Trida Sipka definuje sipku pohybujici se po animacnim platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class SipkaCase implements ISipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final SpravcePlatna SP = SpravcePlatna.getInstance();


//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int pocet = 0;
    
    static int krok = SP.getKrok();
    
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;
    

//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, do zadaneho smeru otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  Barva sipky
     * @param smer   Smer, do nejz je sipka natocena
     */
    public SipkaCase(int xPole, int yPole, Barva barva, Smer8 smer)
    {
    }
    

//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param kr Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli(Kreslitko kr)
    {
    }


    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "_" + poradi;
    }


    /***************************************************************************
     * Otoci sipku o 90 vlevo.
     */
    public void vlevoVbok()
    {
        int k2 = krok/2;
        SP.nekresli(); {
        } SP.vratKresli();
    }


    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
        } SP.vratKresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

