package priklady;

import rup.cesky.utility.IO;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Kompresor;
import rup.cesky.tvary.Presouvac;


/*******************************************************************************
 * Testovaci trida XObdelnikTest slouzi ke komplexnimu otestovani tridy
 * XObdelnik.
 *
 * @author    Rudolf Pecinovsky
 * @version   1.00,  05.2004
 */
public class XObdelnikTest extends junit.framework.TestCase
{
    private SpravcePlatna SP;
    private int krok;
    private XObdelnik xo1;

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *
     * @param nazev  Nazev konstruovaneho testu
     */
    public XObdelnikTest(String nazev)
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
        krok = 50;
        SP = SpravcePlatna.getInstance();
        SP.odstranVse();
        SP.setKrokRozmer( krok, 6, 6 );
        xo1  = new XObdelnik();
        SP.pridej( xo1 );
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
    }


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
    public void testPosuny()
    {
        for( int i=0;   i < 2;   i++ )
        {
            assertTrue( IO.souhlas(getName() + "\nPrijde posun vpravo") );
            xo1.posunVpravo();

            assertTrue( IO.souhlas(getName() + "\nPrijde posun dolu") );
            xo1.posunDolu();

            assertTrue( IO.souhlas(getName() + "\nPrijde posun vlevo") );
            xo1.posunVlevo();

            assertTrue( IO.souhlas(getName() + "\nPrijde posun vzhuru") );
            xo1.posunVzhuru();

            if( i == 1 ) {
                break;
            }

            assertTrue( IO.souhlas(getName() + "\nKrok se zmensi na polovinu") );
            SP.setKrokRozmer( krok /= 2, 8, 8 );

            assertTrue( IO.souhlas(getName() + 
                               "\nPrijde posun vpravo o " + 2*krok) );
            xo1.posunVpravo(2*krok);

            assertTrue( IO.souhlas(getName() + 
                               "\nPrijde posun dolu o " + 2*krok) );
            xo1.posunDolu(2*krok);
        }
        assertTrue( IO.souhlas(getName() + "\nV poradku posunuto") );
    }


    /***************************************************************************
     *
     */
    public void testPresovace()
    {
        Presouvac presouvac3 = new Presouvac(3);
        Presouvac presouvac9 = new Presouvac(9);

        presouvac3.presunNa( xo1, 150, 50 );
        assertEquals( "\nx-ova souradnice xobdelniku", 150, xo1.getX() );
        assertEquals( "\ny-ova souradnice xobdelniku",  50, xo1.getY() );
        presouvac9.presunNa( xo1, 150, 150 );
        assertEquals( "\nx-ova souradnice elipsy", 150, xo1.getX() );
        assertEquals( "\ny-ova souradnice elipsy", 150, xo1.getY() );

        assertTrue( IO.souhlas(getName() + 
                               "\nObjekty presunuty na [150;150]?") );

        presouvac3 = presouvac9 = null;
    }


    /***************************************************************************
     *
     */
    public void testHybaci()
    {
        int k2 = 25;
        int kr = 2*k2;

        SP.setKrokRozmer( kr, 6, 6 );
        xo1.setPozice( 3*kr, 3*kr );
        assertTrue( IO.souhlas(getName() + "\nUmisten ve stredu platna?") );
        xo1.setRozmer( 4*kr, 4*kr );
        assertTrue( IO.souhlas(getName() + "\nZvetsen na rozmer 44 pole?") );
        xo1.setRozmer( 2*kr, 2*kr );
        assertTrue( IO.souhlas(getName() + "\nZmensen na rozmer 22 pole?") );

        Kompresor pk = new Kompresor( 4 );
        pk.nafoukniNa( xo1, 4*kr, 4*kr );
        pk.nafoukniNa( xo1, kr,   kr );
    }
}

