package posuvne_sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.Obdelnik;


/*******************************************************************************
 * Trida SSipka definuje sipku otocenou na sever.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class SSipka extends ASipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== RETEZCOVE LITERALY ========================================================
//== ATRIBUTY TRIDY ============================================================
//== ATRIBUTY INSTANCI =========================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na sever otocenou sipku
     * zadane barvy.
     *
     * @param x  x-ova polickova souradnice
     * @param y  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public SSipka(int x, int y, Barva barva)
    {
        int krok = SP.getKrok();
        int k2   = krok/2;
        spicka = new Trojuhelnik( x*krok, y*krok, krok, k2, barva, 
                                  Smer8.SEVER );
        telo   = new Obdelnik( x*krok + krok/4, y*krok + k2, k2, k2, barva );
    }

    

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
        telo  .setPozice( x + krok/4, y + krok/2 );
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ASipka doleva()
    {
        int krok = SP.getKrok();
        return new ZSipka( getX()/krok, getY()/krok, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        pres.presunNa( this, getX(),  getY()-SP.getKrok() );
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

