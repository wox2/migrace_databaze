package sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida JSipka definuje sipku otocenou na jih.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class JSipka extends ASipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na jih otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public JSipka(int xPole, int yPole, Barva barva)
    {
        super( xPole, yPole );
        int  k2    = krok/2;
        spicka = new Trojuhelnik( xPole*krok, yPole*krok + k2, 
                                  krok, k2, barva, Smer8.JIH );
        telo   = new Obdelnik( xPole*krok + krok/4, yPole*krok, 
                               k2, k2, barva );
    }

    

//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ASipka doleva()
    {
        return new VSipka( xPole, yPole, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
            spicka.posunDolu( krok );
            telo  .posunDolu( krok );
        } SP.vratKresli();
        yPole += 1;
    }
    


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

