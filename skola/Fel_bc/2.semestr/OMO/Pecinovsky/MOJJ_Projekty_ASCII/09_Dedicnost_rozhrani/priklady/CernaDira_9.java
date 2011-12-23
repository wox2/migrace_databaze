package priklady;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Kompresor;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Presouvac;
import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.Rozmer;
import rup.cesky.tvary.Smer8;


/*******************************************************************************
 * Instance tridy CernaDira7 simuluje cernou diru, ktera pritahne oznacene
 * objekty a vcucne je. Objekty musi byt typu IHybaci.
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00.000,  05.2003
 */
public class CernaDira_9
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final SpravcePlatna SP     = SpravcePlatna.getInstance();

    private static final CernaDira_9 JEDINACEK = new CernaDira_9();
    private static final int        PRUMER    = 50;
    
    private static final Presouvac presouvac = new Presouvac( 5 );
    private static final Kompresor kompresor = new Kompresor( 3 );
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final Elipsa kruh;
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Vrati instanci jedinacka.
     * 
     * @return POzadovana instance
     */
    public static CernaDira_9 getInstance()
    {
        return JEDINACEK;
    }
    
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Soukromy konstruktor inicializujici jedinacka.
     */
    private CernaDira_9()
    {
        Pozice p = stred( new Rozmer( PRUMER, PRUMER ) );
        kruh = new Elipsa ( p.x, p.y, PRUMER, PRUMER, Barva.CERNA );
        SP.pridej( kruh );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *      
     * @return Nazev tridy nasledovnay podtrzitkem a "rodnym cislem" instance. 
     */
    @Override
    public String toString()
    {
        return "CernaDira";
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Presune zadany objekt nad cernou diru, zmensi jej a nakonec odstrani.
     *      
     * @param obet    Polykany objekt.
     */
    public void spolkni( IHybaci obet  )
    {
        SP.pridejPod( obet, kruh  );
        presouvac.presunNa  ( obet, stred( obet.getRozmer() ) );
        kompresor.nafoukniNa( obet, PRUMER/4, PRUMER/4, Smer8.ZADNY );
        SP.odstran( obet  );
    }


    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================


    /***************************************************************************
     * Vrati souradnice.
     *      
     * @param obet    Polykany objekt.
     */
    private static Pozice stred( Rozmer rozmer )
    {
        int kp = SP.getKrok();
        int sp = SP.getSloupcu() * SP.getKrok();
        int vp = SP.getRadku() * SP.getKrok();
        return new Pozice( (sp-rozmer.sirka)/2, (vp-rozmer.vyska)/2 );
    }
    
    
    
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

