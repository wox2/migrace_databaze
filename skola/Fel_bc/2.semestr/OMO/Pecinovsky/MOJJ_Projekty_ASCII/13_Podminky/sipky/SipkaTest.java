package sipky;

import rup.cesky.utility.IO;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Smer8;


/*******************************************************************************
 * Testovaci trida {@code SipkaTest} slouzi ke komplexnimu otestovani
 * chovani sipek demonstrujicih navrhovych vzor <i>Stav<i/>.
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class SipkaTest extends junit.framework.TestCase
{
    ISipka s1;
    ISipka s2;
    ISipka s3;
    ISipka s4;

    private static final int CYKLU    = 2;    //Kolikrat sipky obehnou kolo
    private static final int PRODLEVA = 100;  //Prodelva mezi akcemi v ms
    private static final int MIN      = 0;     //Minimalni souradnice
    private static final int KROKU    = 6;     //Pocet kroku pred otockou
    private static final int MAX      = MIN + KROKU;
    
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
    
    /***************************************************************************
     * Popojede se vsemi sikpami o krok vpred.
     */
    public void vpred() 
    {
        s1.vpred();
        s2.vpred();
        s3.vpred();
        s4.vpred();
        IO.cekej( PRODLEVA );
    }

    
    /***************************************************************************
     * Otorci vsemi sikpami o 90vlevo.
     */
    public void vlevoVbok() 
    {
        s1.vlevoVbok();
        s2.vlevoVbok();
        s3.vlevoVbok();
        s4.vlevoVbok();
//        IO.cekej( PRODLEVA );
    }


    /***************************************************************************
     * 
     */
    public void okolo()
    {
        SpravcePlatna SP = SpravcePlatna.getInstance();
        int d = MIN + KROKU + MIN + 1;
        SP.setRozmer( d,  d );
        SP.pridej( s1 );
        SP.pridej( s2 );
        SP.pridej( s3 );
        SP.pridej( s4 );
        IO.cekej( PRODLEVA );
        for( int i=4*CYKLU;   i-- > 0;  )   {
            for( int k=0;   k++ < KROKU;   vpred() );
            vlevoVbok();
        }
    }
    
    
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
    public void testSipka()
    {
        s1 = new Sipka( MIN,  MAX,  Barva.CERVENA, Smer8.VYCHOD );
        s2 = new Sipka( MAX,  MAX,  Barva.MODRA,   Smer8.SEVER );
        s3 = new Sipka( MAX,  MIN,  Barva.ZELENA,  Smer8.ZAPAD );
        s4 = new Sipka( MIN,  MIN,  Barva.CERNA,   Smer8.JIH );
        okolo();
    }
    

    /***************************************************************************
     * 
     */
    public void testSipkaCase()
    {
        s1 = new SipkaCase( MIN,  MAX,  Barva.CERVENA, Smer8.VYCHOD );
        s2 = new SipkaCase( MAX,  MAX,  Barva.MODRA,   Smer8.SEVER );
        s3 = new SipkaCase( MAX,  MIN,  Barva.ZELENA,  Smer8.ZAPAD );
        s4 = new SipkaCase( MIN,  MIN,  Barva.CERNA,   Smer8.JIH );
        okolo();
    }
    
}


