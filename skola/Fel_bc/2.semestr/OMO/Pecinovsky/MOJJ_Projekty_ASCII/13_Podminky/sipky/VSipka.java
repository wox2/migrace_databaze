package sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida VSipka definuje sipku otocenou na vychod.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class VSipka extends ASipka
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
     * Vytvori na zadanych souradnicich novou, na vychod otocenou sipku
     * zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public VSipka(int xPole, int yPole, Barva barva)
    {
        super( xPole, yPole );
        int k2 = krok/2;
        spicka = new Trojuhelnik( xPole*krok + k2, yPole*krok, 
                                  k2, krok, barva, Smer8.VYCHOD );
        telo   = new Obdelnik( xPole*krok, yPole*krok + krok/4, 
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
        return new SSipka( xPole, yPole, spicka.getBarva() );
    }
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
            spicka.posunVpravo( krok );
            telo  .posunVpravo( krok );
        } SP.vratKresli();
        xPole += 1;
    }

    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

