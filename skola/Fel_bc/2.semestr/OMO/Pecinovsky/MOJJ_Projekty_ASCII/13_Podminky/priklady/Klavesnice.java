package priklady;

import rup.cesky.tvary.SpravcePlatna;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Klavesnice implements KeyListener
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Vypise zakladni informace o udalosti predane jako parametr.
     * Cas udalosti je jen jednoduse naormatovan, takze nevypisuje
     * mistni cas ale tzv. UTC cas, ktery se muze o nejakou hodinu lisit.
     * Zaroven nejsou tisteny vedouci nuly u poctu milisekund.
     *
     * @param udalost  Text oznacujici ke ktere ze tri moznych udalosti doslo.
     * @param ke       Udalost, o niz se vypisuji informace.
     */
    public static void vypis( String udalost, KeyEvent ke )
    {
        long cas = ke.getWhen();
        System.out.print( udalost + ke + "\n       kodKlavesy=" +
            ke.getKeyCode() + ", kodZnaku=" + (int)(ke.getKeyChar()) +
            ", kodPozice=" + ke.getKeyLocation() );
        System.out.printf( ", cas=%02d:%02d:%02d.%03d%n",  
             cas%86400000/3600000, 
             cas%3600000/60000, cas%60000/1000, cas%1000 );
//         System.out.println( "\n, cas=" +
//             //cas/86400000 + "-" + 
//             cas%86400000/3600000 + ":" +
//             cas%3600000/60000 + ":" + cas%60000/1000 + "," + cas%1000 );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor tridy Trida
     */
    public Klavesnice()
    {
        //Instance se sama prihlasi jako posluchac klavesnice
        SpravcePlatna.getInstance().prihlasKlavesnici( this );
        System.out.print('\f');
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    public void keyPressed( KeyEvent ke )
    {
        vypis("STISK: ", ke );
    }


    public void keyReleased( KeyEvent ke )
    {
        vypis("PUST:  ", ke );
    }


    public void keyTyped( KeyEvent ke )
    {
        vypis("ZNAK:  ",  ke );
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

