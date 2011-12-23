package sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Smer8;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida Sipka definuje sipku pohybujici se po animacnim platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class SipkaCase_13 implements ISipka
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
    
    private final Trojuhelnik spicka;
    private final Obdelnik    telo;


//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private int xPole, yPole;
    
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
    public SipkaCase_13(int xPole, int yPole, Barva barva, Smer8 smer)
    {
        this.xPole = xPole;
        this.yPole = yPole;
        int k2 = krok/2;
        switch( smer )
        {
            case VYCHOD:
                spicka = new Trojuhelnik( xPole*krok + k2, yPole*krok, 
                                          k2, krok, barva, Smer8.VYCHOD );
                telo   = new Obdelnik( xPole*krok, yPole*krok + krok/4, 
                                       k2, k2, barva );
                break;
                
            case SEVER:
                spicka = new Trojuhelnik( xPole*krok, yPole*krok, 
                                          krok, k2, barva, Smer8.SEVER );
                telo   = new Obdelnik( xPole*krok + krok/4, yPole*krok + k2, 
                                       k2, k2, barva );
                break;
                
            case ZAPAD:
                spicka = new Trojuhelnik( xPole*krok, yPole*krok, 
                                          k2, krok, barva, Smer8.ZAPAD );
                telo   = new Obdelnik( xPole*krok + k2, yPole*krok + krok/4, 
                                       k2, k2, barva );
                break;
                
            case JIH:
                spicka = new Trojuhelnik( xPole*krok, yPole*krok + k2, 
                                          krok, k2, barva, Smer8.JIH );
                telo   = new Obdelnik( xPole*krok + krok/4, yPole*krok, 
                                       k2, k2, barva );
                break;
                
            default:
                throw new IllegalArgumentException(
                    "Smer " + smer + " pro sipku zadat nelze.");
        }
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
        spicka.nakresli( kr );
        telo  .nakresli( kr );
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
            switch( spicka.getSmer() )
            {
                case VYCHOD:
                    spicka.setSmer( Smer8.SEVER );
                    spicka.setPozice( xPole*krok, yPole*krok );
                    spicka.setRozmer( krok, k2 );
                    telo.setPozice( xPole*krok + krok/4, yPole*krok + k2 );
                    telo.setRozmer( k2, k2 );
                    break;
                    
                case SEVER:
                    spicka.setSmer( Smer8.ZAPAD );
                    spicka.setPozice( xPole*krok, yPole*krok );
                    spicka.setRozmer( k2, krok );
                    telo.setPozice( xPole*krok + k2, yPole*krok + krok/4 );
                    telo.setRozmer( k2, k2 );
                    break;
                    
                case ZAPAD:
                    spicka.setSmer( Smer8.JIH );
                    spicka.setPozice( xPole*krok, yPole*krok + k2 );
                    spicka.setRozmer( krok, k2 );
                    telo.setPozice( xPole*krok + krok/4, yPole*krok );
                    telo.setRozmer( k2, k2 );
                    break;
                    
                case JIH:
                    spicka.setSmer( Smer8.VYCHOD );
                    spicka.setPozice( xPole*krok + k2, yPole*krok );
                    spicka.setRozmer( k2, krok );
                    telo.setPozice( xPole*krok, yPole*krok + krok/4 );
                    telo.setRozmer( k2, k2 );
                    break;
                    
                default:
                    throw new IllegalStateException(
                        "Neumim otocit sipku natocenou na " + spicka.getSmer());
            }
        } SP.vratKresli();
    }


    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        SP.nekresli(); {
            switch( spicka.getSmer() )
            {
                case VYCHOD:
                    spicka.posunVpravo( krok );
                    telo  .posunVpravo( krok );
                    xPole += 1;
                    break;
                    
                case SEVER:
                    spicka.posunDolu( -krok );
                    telo  .posunDolu( -krok );
                    yPole -= 1;
                    break;
                    
                case ZAPAD:
                    spicka.posunVpravo( -krok );
                    telo  .posunVpravo( -krok );
                    xPole -= 1;
                    break;
                    
                case JIH:
                    spicka.posunDolu( krok );
                    telo  .posunDolu( krok );
                    yPole += 1;
                    break;
                    
                default:
                    throw new IllegalStateException(
                        "Neumim popojet s sipkou na " + spicka.getSmer());
            }
        } SP.vratKresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

