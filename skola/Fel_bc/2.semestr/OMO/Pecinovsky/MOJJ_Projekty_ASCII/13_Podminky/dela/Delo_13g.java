package dela;

import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida {@code Delo_13g} slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Delo_13g extends Delo_13e
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocet bodu, o nez se delo posune po stisku posunove klavesy. */    
    protected static final int KROK = 5;
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    protected final char VLEVO;
    protected final char VPRAVO;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param x         Vodorovnai souradnice dela
     * @param ovladani  Znaky, kterymi se delo ovlada
     */
    public Delo_13g( int x, String ovladani )
    {
        super( x, ovladani.charAt(1) );
        VLEVO = ovladani.charAt(0);
        VPRAVO= ovladani.charAt(2);
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    
    public void posun( int dx )
    {
        xPos += dx;
        podstavec.posunVpravo( dx );
        hlaven   .posunVpravo( dx );
        popisek  .posunVpravo( dx );
    }
    
    
    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    @Override
    public void keyPressed( KeyEvent ke )
    {
        switch( ke.getKeyCode() )
        {
            case KeyEvent.VK_DOWN:
                super.keyPressed( ke );
                break;
            
            case KeyEvent.VK_LEFT:
                posun( -KROK );
                break;
    
            case KeyEvent.VK_RIGHT:
                posun( KROK );
                break;
        }
    }
    
    
//     public void keyPressed( KeyEvent ke )
//     {
//         switch( ke.getKeyChar() )
//         {
//             case spoust:
//                 super.keyPressed( ke );
//                 break;
//             
//             case VLEVO:
//                 posun( -KROK );
//                 break;
//     
//             case VPRAVO:
//                 posun( KROK );
//                 break;
//         }
//     }
    

//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void start_13g()
    {
        new Delo_13g( 100, "asd" );
        new Delo_13g( 200, "123" );
    }


}

