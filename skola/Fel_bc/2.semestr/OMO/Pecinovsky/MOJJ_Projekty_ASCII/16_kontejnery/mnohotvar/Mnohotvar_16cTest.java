package mnohotvar;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Ctverec;
import rup.cesky.tvary.Presouvac;

import rup.cesky.utility.IO;


/*******************************************************************************
 * Testovaci trida Sbl_MinJUnitTest slouzi ke komplexnimu otestovani tridy
 * Sbl_MinJUnit 
 *
 * @author    Rudolf Pecinovsky
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2004
 */
public class Mnohotvar_16cTest extends junit.framework.TestCase
{
    private SpravcePlatna SP;
    private Mnohotvar_16c petka;
    private Ctverec ccc, csv, csz, cjz, cjv, cc;
    
    
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *  
     * @param nazev  Nazev konstruovaneho testu
     */
    public Mnohotvar_16cTest(String nazev)
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
        SP = SpravcePlatna.getInstance();
        SP.setNazev(getClass().getSimpleName() + ":" + toString());
        SP.odstranVse();
        SP.setKrokRozmer( 50, 6, 6 );
        SP.setMrizka( true );
        petka = new Mnohotvar_16c( "Petka" );
    }


    /***************************************************************************
     * Uklid po testu - tato metoda se spusti po vykonani kazdeho testu.
     */
    @Override
    protected void tearDown()
    {
    }


//== SOUKROME A POMOCNE METODY INSTANCI ========================================
    
    private int cekani = 500;
    
    private void overOblast( String pocText, Mnohotvar_16c tvar,
                             int x, int y, int sirka, int vyska )
    {
        IO.cekej( cekani );    //Aby byl cas si vysledek akce prohlednout 
        String pt = "\n" + pocText + ", spatna ";
        assertEquals( pt + "x-ova souradnice", x, tvar.getX() );
        assertEquals( pt + "y-ova souradnice", y, tvar.getY() );
        assertEquals( pt + "sirka", sirka, tvar.getSirka() );
        assertEquals( pt + "vyska", vyska, tvar.getVyska() );
    }
//== VLASTNI TESTY =============================================================

    /***************************************************************************
     * 
     */
    public void testPrazdny()
    {
        SP.pridej( petka );
        overOblast( "Prazdny tvar", petka, 0, 0, 0, 0 );
        assertEquals( "Nazev neodpovida", "Petka", petka.getNazev() );
    }

    
    /***************************************************************************
     * 
     */
    public void testPetka()
    {
        SP.pridej( petka );
        petka.pridej( ccc = new Ctverec( 100, 100, 50 ) );
        overOblast( "Stred", petka, 100, 100, 50, 50 );
        petka.pridej( csz = new Ctverec( 50, 50, 50 ) );
        overOblast( "SZ", petka, 50, 50, 100, 100 );
        petka.pridej( cjz = new Ctverec( 50, 150, 50 ) );
        overOblast( "JZ", petka, 50, 50, 100, 150 );
        petka.pridej( cjv = new Ctverec( 150, 150, 50 ) );
        overOblast( "JV", petka, 50, 50, 150, 150 );
        petka.pridej( csv = new Ctverec( 150, 50, 50 ) );
        overOblast( "SV", petka, 50, 50, 150, 150 );
    }
    
    
    /***************************************************************************
     * 
     */
    public void testPosuny()
    {
        cekani = 0;
        testPetka();
        cekani = 500;
        petka.posunVpravo();
        overOblast( "Posun vpravo", petka, 100, 50, 150, 150 );
        petka.posunDolu();
        overOblast( "Posun dolu", petka, 100, 100, 150, 150 );
        petka.posunVlevo();
        overOblast( "Posun vlevo", petka, 50, 100, 150, 150 );
        petka.posunVzhuru();
        overOblast( "Posun vzhuru", petka, 50, 50, 150, 150 );
        petka.setPozice( 0, 0 );
        overOblast( "Pozice[0;0]", petka, 0, 0, 150, 150 );
        Presouvac p = new Presouvac( 5 );
        p.presunNa( petka, 150, 150 );
        overOblast( "Pozice[0;0]", petka, 150, 150, 150, 150 );
    }


    /***************************************************************************
     * 
     */
    public void testRozmery()
    {
        cekani = 0;
        testPetka();
        IO.cekej( 1000 );
        cekani = 1000;
        petka.setRozmer( 50, 50 );
        overOblast( "Rozmer 5050", petka, 50, 50, 50, 50 );
        petka.setRozmer( 5, 5 );
        overOblast( "Rozmer 5050", petka, 50, 50, 5, 5 );
        petka.setPozice( 0, 0 );
        petka.setRozmer( 300, 300 );
        overOblast( "Rozmer 150150", petka, 0, 0, 300, 300 );
        cekani = 500;
    }


    /***************************************************************************
     * 
     * /
    public void testXXX()
    {
    }
    
/**/ 
}

