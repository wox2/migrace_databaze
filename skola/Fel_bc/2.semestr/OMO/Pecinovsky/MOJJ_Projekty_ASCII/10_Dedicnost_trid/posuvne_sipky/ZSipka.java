package posuvne_sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.Obdelnik;


/*******************************************************************************
 * Trida ZSipka definuje sipku otocenou na zapad.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class ZSipka extends ASipka
{
//== VEREJNE KONSTANTY =========================================================
//== SOUKROME KONSTANTY ========================================================
//== RETEZCOVE LITERALY ========================================================
//== ATRIBUTY TRIDY ============================================================
//== ATRIBUTY INSTANCI =========================================================
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================
//== KONSTRUKTORY A TOVARNI METODY =============================================
    

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na zapad otocenou sipku
     * zadane barvy.
     *
     * @param x  x-ova polickova souradnice
     * @param y  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public ZSipka(int x, int y, Barva barva)
    {
        int krok = SP.getKrok();
        int k2   = krok/2;
        spicka = new Trojuhelnik( x*krok, y*krok, k2, krok, barva, 
                                  Smer8.ZAPAD );
        telo   = new Obdelnik(  x*krok + k2, y*krok + krok/4, k2, k2, barva );
    }
    

    
//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================


    /***************************************************************************
     * Vrati vodorovnou souradnici sipky.
     *
     * @return  x-ova souradnice sipky
     */
    public int getX()
    {
        return spicka.getX();
    }
    
    
    /***************************************************************************
     * Vrati svislou souradnici sipky.
     *
     * @return  y-ova souradnice sipky
     */
    public int getY()
    {
        return spicka.getY();
    }
    
    
    /***************************************************************************
     * Nastavi vodorovnou a svislou souradnici sipky.
     *
     * @param x  Zadavana x-ova souradnice
     * @param y  Zadavana y-ova souradnice
     */
    public void setPozice(int x, int y)
    {
        int krok = SP.getKrok();
        spicka.setPozice( x,          y );
        telo  .setPozice( x + krok/2, y + krok/4 );
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ASipka doleva()
    {
        int krok = SP.getKrok();
        return new JSipka( getX()/krok, getY()/krok, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        pres.presunNa( this, getX()-SP.getKrok(),  getY() );
    }
    
    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

