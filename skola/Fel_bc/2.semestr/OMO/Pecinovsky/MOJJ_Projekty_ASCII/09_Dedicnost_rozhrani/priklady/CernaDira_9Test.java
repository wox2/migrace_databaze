package priklady;

import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Testovaci trida CernaDira2Test slouzi ke komplexnimu otestovani
 * tridy ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class CernaDira_9Test extends junit.framework.TestCase
{
	private int krok;
	private SpravcePlatna SP;
	private Obdelnik    o1;
	private Elipsa      e1;
	private Trojuhelnik t1;
	private Strom_9     s1;


//##############################################################################
//== KONSTRUKTORY A METODY VRACEJICI INSTANCE VLASTNI TRIDY ====================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *  
     * @param nazev  Nazev konstruovaneho testu
     */
    public CernaDira_9Test(String nazev)
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
		SP.setKrokRozmer( krok, 6, 6 );
		
		e1 = new Elipsa();
		SP.pridej(e1);
		t1 = new Trojuhelnik( 0, 3*krok, 2*krok, 3*krok);
		SP.pridej(t1);
		o1 = new Obdelnik( 4*krok, 5*krok, 2*krok, krok );
		SP.pridej(o1);
		s1 = new Strom_9(4*krok, 0, 2*krok, 5*krok );
		SP.pridej(s1);
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

	public void testSpolkni()
	{
		CernaDira_9 dira = CernaDira_9.getInstance();
		assertNotNull(dira);
		dira.spolkni(e1);
		dira.spolkni(t1);
		dira.spolkni(o1);
		dira.spolkni(s1);
	}
}
//public class CernaDira2Test extends junit.framework.TestCase


