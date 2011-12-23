package dela;

import rup.cesky.tvary.Text;

import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Delo_13e extends Delo_13b
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /** Pocet vystrlu za vterinu. */
    protected static final int KADENCE = 2;
    
    /** Prodleva mezi jednotlivymi vystrely v milisekundach. */
    protected static final int PRODLEVA = 1000 / KADENCE;
    
    
    
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
    public Delo_13e(int x, char spoust)
    {
        super( x, spoust );
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    private long naposledy  = 0;
    private int  nabijecich = 0;
    private boolean nabito = true;
    private static final String A = "+++";
    private static final String N = "---";
    
    @Override
    public void keyPressed( KeyEvent ke )
    {
        if( ke.getKeyChar() == spoust )
        {
            long nyni = ke.getWhen();
            System.out.printf(
                "Prodleva=%,6d, naboju=%3d, nabijecich=%1d, nabito=%s ",
                (nyni-naposledy), naboju, nabijecich, (nabito?A:N) );
            if( (nyni - naposledy) > PRODLEVA )
            {
                System.out.print( "--- strelba  ---  " );
                if( nabito  &&  (naboju > 0) )
                {
                    System.out.print( "### PAL ###" );
                    Strela strela = new Strela( xPos, VYSKA_PLATNA );
                    SP.pridejPod( this,  strela );
                    MP.presun( strela, xPos, 0, RYCHLOST_STRELY );
                    --naboju;
                    popisek = new Text( odpal + naboju, 
                                        popisek.getX(), popisek.getY() );
                }
            }
            else
            {
                System.out.print( "+++ nabijeni +++  " );
                nabito = false;
                if( ++nabijecich >= 5 )
                {
                    System.out.print( "### NABITO ###" );
                    naboju += 5;
                    popisek = new Text( odpal + naboju, 
                                        popisek.getX(), popisek.getY() );
                    nabijecich = 0;
                    nabito = true;
                }
            }
            naposledy = nyni;
            System.out.println();
        }
    }

//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     * -
     * @param args Parametry prikazoveho radku se nepouzivaji.
     */
    public static void start_13e()
    {
        System.out.println('\f');
        new Delo_13e( 100, ' ' );
//         new Delo_13e( 200, ' ' );
    }

}

