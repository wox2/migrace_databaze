package sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida ZSipka definuje sipku otocenou na zapad.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class ZSipka extends ASipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
    

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na zapad otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public ZSipka(int xPole, int yPole, Barva barva)
    {
        super( xPole, yPole );
        int  k2    = krok/2;
        spicka = new Trojuhelnik( xPole*krok, yPole*krok, 
                                  k2, krok, barva, Smer8.ZAPAD );
        telo   = new Obdelnik( xPole*krok + k2, yPole*krok + krok/4, 
                               k2, k2, barva );
    }
    

    
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ASipka doleva()
    {
        return new JSipka( xPole, yPole, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
            spicka.posunVpravo( -krok );
            telo  .posunVpravo( -krok );
        } SP.vratKresli();
        xPole -= 1;
    }
    


//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

