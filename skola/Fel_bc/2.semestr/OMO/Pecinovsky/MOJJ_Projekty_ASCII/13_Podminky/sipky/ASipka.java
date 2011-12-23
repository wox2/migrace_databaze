package sipky;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida {@code ASipka} slouzi jako spolecny rodic jednostavovych trid
 * demonstrujicih funkci navrhoveho vzoru <i>Stav<i/>.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public abstract class ASipka implements IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
    
    static int krok = SP.getKrok();

        
        
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    Trojuhelnik spicka;
    Obdelnik    telo;
    int xPole, yPole;
    
    

//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na policku o zadanych souradnicich novou, 
     * do zadaneho smeru otocenou sipku zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     */
    protected ASipka(int xPole, int yPole) 
    {
        this.xPole = xPole;
        this.yPole = yPole;
    }

    /***************************************************************************
     * Vytvori na policku o zadanych souradnicich novou, 
     * do zadaneho smeru otocenou sipku zadane barvy.
     *
     * @param xPole  x-ova polickova souradnice
     * @param yPole  y-ova polickova souradnice
     * @param barva  Barva sipky
     * @param smer   Smer, do nejz je sipka natocena
     * @return Sipka za zadnaych souradnicich otocena do pozadovaneho smeru
     */
    public static ASipka getInstance(int xPole, int yPole, 
                                     Barva barva, Smer8 smer)  
    {
        switch( smer )
        {
            case VYCHOD:
                return new VSipka( xPole, yPole,  barva );      //==========>

            case SEVER:
                return new SSipka( xPole, yPole,  barva );      //==========>

            case ZAPAD:
                return new ZSipka( xPole, yPole,  barva );      //==========>

            case JIH:
                return new JSipka( xPole, yPole,  barva );      //==========>

            default:
                throw new IllegalArgumentException(
                    "Smer " + smer + " pro sipku zadat nelze.");
        }
    }
    

//== ABSTRAKTNI METODY =========================================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public abstract ASipka doleva();

    
    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public abstract void vpred();
    
    
    
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
    
    

//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param g Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli( Kreslitko g )
    {
        spicka.nakresli( g );
        telo  .nakresli( g );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

