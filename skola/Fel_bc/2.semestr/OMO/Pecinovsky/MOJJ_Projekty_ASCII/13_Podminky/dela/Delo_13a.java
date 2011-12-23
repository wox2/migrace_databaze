package dela;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Multipresouvac;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Delo_0 demonstruje moznosti reakce na udalost klavesnice.
 * Tstovaci metoda vytvori dva palposty a po stisku zadane klavesy
 * se z daneho palpostu vystreli kolmovzhuru strela.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Delo_13a extends KeyAdapter
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /* Aplikacni okno, v nemz se vsechno odehrava. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    /* Spolecny multipresouvac vsech pohyblivych predmetu ve hre. */
    protected static final Multipresouvac MP = Multipresouvac.getInstance();
    
    protected static final int   RYCHLOST_STRELY    = 200;
    
   
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    protected final int      xPos;
    protected final char     spoust;
    
    
    
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
    public Delo_13a(int x, char spoust)
    {
        this.xPos   = x;
        this.spoust = spoust;
        SP.prihlasKlavesnici( this );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    @Override
    public void keyPressed( KeyEvent ke )
    {
        if( ke.getKeyChar() == spoust )
        {
            Strela strela = new Strela( xPos, SP.getRadku()*SP.getKrok() );
            MP.presun( strela, xPos, 0, RYCHLOST_STRELY );
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
    public static void start_13a()
    {
        new Delo_13a( 100, 'a' );
        new Delo_13a( 200, 's' );
    }
    

}

