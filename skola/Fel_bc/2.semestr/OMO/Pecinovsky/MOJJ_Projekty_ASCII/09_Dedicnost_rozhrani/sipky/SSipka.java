package sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.Obdelnik;


/*******************************************************************************
 * Trida SSipka definuje sipku otocenou na sever.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class SSipka implements ISipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    
    
//== ATRIBUTY TRIDY ============================================================
    
    private static int krok = SP.getKrok();

        
        
//== ATRIBUTY INSTANCI =========================================================
    
    private Trojuhelnik spicka;
    private Obdelnik    telo;
    private int xPole, yPole;
    
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na sever otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public SSipka(int xPole, int yPole, Barva barva)
    {
        this.xPole = xPole;
        this.yPole = yPole;
        int  k2    = krok/2;
        spicka = new Trojuhelnik( xPole*krok, yPole*krok, 
                                  krok, k2, barva, Smer8.SEVER );
        telo   = new Obdelnik( xPole*krok + krok/4, yPole*krok + k2, 
                               k2, k2, barva );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati vodorovnou souradnici sipky.
     *
     * @return  x-ova souradnice sipky
     */
    public int getXPole()
    {
        return xPole;
    }
    
    
    /***************************************************************************
     * Vrati svislou souradnici sipky.
     *
     * @return  y-ova souradnice sipky
     */
    public int getYPole()
    {
        return yPole;
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ISipka doleva()
    {
        return new ZSipka( xPole, yPole, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
            spicka.posunDolu( -krok );
            telo  .posunDolu( -krok );
        } SP.vratKresli();
        yPole -= 1;
    }
    

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param g Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli(Kreslitko g)
    {
        spicka.nakresli( g );
        telo.nakresli( g );
    }


    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

