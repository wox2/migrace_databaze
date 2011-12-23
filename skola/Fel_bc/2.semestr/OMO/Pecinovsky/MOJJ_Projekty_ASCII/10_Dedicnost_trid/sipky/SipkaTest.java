package sipky;

import rup.cesky.utility.IO;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;


/*******************************************************************************
 * Testovaci trida {@code SipkaTest} slouzi ke komplexnimu otestovani
 * chovani sipek demonstrujicih navrhovych vzor <i>Stav<i/>.
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class SipkaTest extends junit.framework.TestCase
{

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *  
     * @param nazev  Nazev konstruovaneho testu
     */
    public SipkaTest(String nazev)
    {
        super( nazev );
    }



//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.         
     */
    @Override
    protected void setUp()
    {
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== VLASTNI TESTY =============================================================

    /***************************************************************************
     * 
     * /
    public void testXXX()
    {
    }
    
/**/ 

    /***************************************************************************
     * 
     */
    public void testOkolo()
    {
        SpravcePlatna ap = SpravcePlatna.getInstance();
        ap.setRozmer( 5,  5 );
        Sipka s = new Sipka( 1,  3,  Barva.CERNA );
        ap.pridej( s );
        IO.cekej( 250 );
        for( int i=4;   i-- > 0;  )
        {
            s.vpred();      IO.cekej( 250 );
            s.vpred();      IO.cekej( 250 );
            s.vlevoVbok();  IO.cekej( 250 );
        }
    }
    

}


