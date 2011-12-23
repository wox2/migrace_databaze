package letadla;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Smer8;

import rup.cesky.tvary.IMultiposuvny;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.APosuvny;
import rup.cesky.tvary.Multipresouvac;
import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.Text;


/*******************************************************************************
 * Trida {@code Letadlo} slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Letadlo extends APosuvny implements IMultiposuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final int SIRKA_TRUPU = 20;
    private static final int SIRKA_KRIDLA = 5 * SIRKA_TRUPU;
    public  static final int VYSKA_OSY = SIRKA_KRIDLA / 2;
    
    private static final Barva BARVA = Barva.MODRA;

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    private final Elipsa trup;
    private final Trojuhelnik kridlo;
    private final int rychlost;
    private final int x0;
    
    private final int xPosunKridla;
    private final int yPosunTrupu;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private int  poradiLetadla;
    private Text cislo;
    
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param delka     Vodorovna delka letadla
     * @param rychlost  Rychlkost kleticiho letadla
     */
    public Letadlo( int delka, int rychlost )
    {
        super( SP.getSloupcu() + delka, 0 );
        this.x0 = getX();
        this.xPosunKridla = delka / 3;
        this.yPosunTrupu  = VYSKA_OSY - SIRKA_TRUPU/2;
        int delkaKridla = delka / 3;
        
        trup = new Elipsa( x0, yPosunTrupu, delka, SIRKA_TRUPU, BARVA );
        kridlo = new Trojuhelnik( x0+xPosunKridla, 0, 
                                  delkaKridla, SIRKA_KRIDLA, 
                                  BARVA, Smer8.ZAPAD );
        poradiLetadla = 0;
        this.rychlost = rychlost;
        presunuto();
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    
    @Override
    public void setPozice( int x, int y )
    {
        super .setPozice( x, y );
        trup  .setPozice( x,                y + yPosunTrupu );
        kridlo.setPozice( x + xPosunKridla, y );
        cislo .setPozice( x + xPosunKridla, y + yPosunTrupu );
    }

    
    public int getSirka()
    {
        return trup.getSirka();
    }
    
    
    public void setBarva( Barva barva )
    {
        trup  .setBarva( barva );
        kridlo.setBarva( barva );
    }
    
    
    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
    
    public void presunuto()
    {
        this.cislo = new Text( ++poradiLetadla+"", 0, 0, Barva.ZLUTA );
        setBarva( BARVA );
        setPozice( x0, 0 );
        Multipresouvac mp = Multipresouvac.getInstance();
        mp.presun( this, -trup.getSirka(), 0, rychlost );
    }
    
    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    public void nakresli( rup.cesky.tvary.Kreslitko kreslitko )
    {
        trup  .nakresli( kreslitko );
        kridlo.nakresli( kreslitko );
        cislo .nakresli( kreslitko );
    }    

}

