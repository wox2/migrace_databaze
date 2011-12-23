package posuvne_sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IPosuvny;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.SpravcePlatna;


/*******************************************************************************
 * Trida Sipka definuje sipku pohybujici se po animacnim platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class Sipka implements IPosuvny
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

    private ISipka sipka;



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na vychod otocenou sipku
     * zadane barvy.
     *
     * @param x  x-ova polickova souradnice
     * @param y  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public Sipka(int x, int y, Barva barva)
    {
        sipka = new VSipka( x, y, barva );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//
//    /***************************************************************************
//     * Vrati vodorovnou souradnici sipky.
//     *
//     * @return  x-ova souradnice sipky
//     */
//    public int getX()
//    {
//        return sipka.getX();
//    }
//
//
//    /***************************************************************************
//     * Vrati svislou souradnici sipky.
//     *
//     * @return  y-ova souradnice sipky
//     */
//    public int getY()
//    {
//        return sipka.getY();
//    }
//

    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return sipka.getPozice();
    }


    /***************************************************************************
     * Nastavi vodorovnou a svislou souradnici sipky.
     *
     * @param x  Zadavana x-ova souradnice
     * @param y  Zadavana y-ova souradnice
     */
    public void setPozice(int x, int y)
    {
        sipka.setPozice( x, y );
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param pozice   Nova pozice instance
     */
    public void setPozice(Pozice pozice)
    {
        setPozice( pozice.x, pozice.y );
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param g Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli(Kreslitko g)
    {
        sipka.nakresli( g );
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
        //Nebude-li importovano IO, lze pouzit
        //return this.getClass().getName() + "_" + poradi;
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

