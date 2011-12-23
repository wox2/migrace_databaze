package letadla;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IMultiposuvny;
import rup.cesky.tvary.Kruh;
import rup.cesky.tvary.Multipresouvac;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Strela extends Kruh implements IMultiposuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Vsechny strely budou mit stejny prumer. */
    public static final int PRUMER = 10;


//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final Letadlo letadlo;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param xPos     Vodorovna pozice strely
     * @param letadlo  Letadlo, ktere ma strela sestrelit
     */
    public Strela( int xPos, Letadlo letadlo )
    {
        super( xPos, SP.getRadku(), PRUMER, Barva.CERVENA );
        this.letadlo = letadlo;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    public void presunuto()
    {
        SP.odstran( this );
        if( (letadlo.getX() < getX())  &&
            (getX()+PRUMER  < letadlo.getX()+letadlo.getSirka()) )
        {
            letadlo.setBarva( Barva.CERVENA );
            Multipresouvac mp = Multipresouvac.getInstance();
            mp.zastav( letadlo );
            mp.presun( letadlo, 0, SP.getRadku(), 150 );
        }

    }

//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

