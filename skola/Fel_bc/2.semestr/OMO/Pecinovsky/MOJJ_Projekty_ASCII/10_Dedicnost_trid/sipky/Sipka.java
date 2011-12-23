package sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Kreslitko;


/*******************************************************************************
 * Trida Sipka definuje sipku pohybujici se po animacnim platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class Sipka implements IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final SpravcePlatna SP = SpravcePlatna.getInstance();


//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int pocet = 0;


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;


//== PROMENNE ATRIBUTY INSTANCI ================================================

    private ASipka sipka;



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na vychod otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public Sipka(int xPole, int yPole, Barva barva)
    {
        sipka = new VSipka( xPole, yPole, barva );
    }
    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param kr Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli(Kreslitko kr)
    {
        sipka.nakresli( kr );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

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



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Otoci sipku o 90 vlevo.
     */
    public void vlevoVbok()
    {
        sipka = sipka.doleva();
        SP.prekresli();
    }



    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        sipka.vpred();
        SP.prekresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

