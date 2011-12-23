package dela;

import rup.cesky.utility.IO;

import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Delo_13c extends Delo_13b
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /** Pocet vystrlu za vterinu. */
    private static final int KADENCE = 10;
    
    /** Prodleva mezi jednotlivymi vystrely v milisekundach. */
    private static final int PRODLEVA = 1000 / KADENCE;
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param x         Vodorovnai souradnice dela
     * @param spoust    Znak, kterym se delo odpaluje
     */
    public Delo_13c(int x, char spoust)
    {
        super( x, spoust );
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    private long naposledy = 0;

    @Override
    public void keyPressed( KeyEvent ke )
    {
        if( ke.getKeyChar() == spoust )
        {
            Strela strela = new Strela( xPos, VYSKA_PLATNA );
            SP.pridejPod( this,  strela );
            MP.presun( strela, xPos, 0, RYCHLOST_STRELY );
            IO.cekej( PRODLEVA );
        }
    }

//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void start_13c()
    {
        new Delo_13c( 100, '1' );
        new Delo_13c( 200, '2' );
    }


}

