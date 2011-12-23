/*******************************************************************************
 * Testovaci trida Zlomek_5Test slouzi ke komplexnimu otestovani vzoroveho
 * reseni definice tridy zlomku spolu s operacemi zlomkove aritmetiky.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Zlomek_5Test extends junit.framework.TestCase
{
    Zlomek_5 z0, z1, z2, z12, z34, z56;
    Zlomek_5 v1, v2, v3;
    
//== METODY TRIDY PRO PRIPRAVU A SPUSTENI SADY =================================
//##############################################################################
//== PRIPRAVA A UKLID PRIPRAVKU ================================================

    /***************************************************************************
     * Vytvoreni pripravku (fixture), tj. sady objektu, s nimiz budou vsechny
     * testy pracovat a ktera se proto vytvori pred spustenim kazdeho testu.         
     */
    @Override
    protected void setUp()
    {
        System.out.println( "\n" + getName() );
        z0 = new Zlomek_5( 0 );
        z1 = new Zlomek_5( 1 );
        z2 = new Zlomek_5( 2 );
        z12 = new Zlomek_5( 1, 2 );
        z34 = new Zlomek_5( 3, 4 );
        z56 = new Zlomek_5( 5, 6 );
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
     * Pomocna metoda tisknouci zadanou operaci na standardni vystup.
     * 
     * @param v    Vysledek
     * @param z1   Levy operand
     * @param op   Znak operace
     * @param z2   Pravy operand
     */
    public static void tiskni( Zlomek_5 v, Zlomek_5 z1, char op, Zlomek_5 z2 )
    {
        System.out.println( v + " = " + z1 + ' ' + op + ' ' + z2 );
    }
    
    
    /***************************************************************************
     * Pomocna metoda tisknouci zadanou operaci na standardni vystup.
     * 
     * @param cislo    Vysledek prevedeny na retezec
     * @param z        Prevadeny zlomek
     */
    public static void tiskni( String cislo, Zlomek_5 z )
    {
        System.out.println( cislo + " = " + z );
    }
    

//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== VLASTNI TESTY =============================================================

    /***************************************************************************
     * Testuje konstruktory a prevod zlomku na retezec.
     */
    public void testToString()
    {
        z1 = new Zlomek_5( 2 );
        System.out.println("Zlomek_5(2) = " + z1);
        assertEquals( "[2/1]", z1.toString() );

        z1 = new Zlomek_5( 1, 2 );
        System.out.println("Zlomek_5(1, 2) = " + z1);
        assertEquals( "[1/2]", z1.toString() );

        z1 = new Zlomek_5( 2, 4 );
        System.out.println("Zlomek_5(2, 4) = " + z1);
        assertEquals( "[1/2]", z1.toString() );

        z2 = new Zlomek_5( z1 );
        System.out.println("Zlomek_5(" + z1 + ") = " + z2);
        assertEquals(z1.getCitatel(), z2.getCitatel());
        assertEquals(z1.getJmenovatel(), z2.getJmenovatel());
    }
    

    /***************************************************************************
     * Testuje scitani zlomku a zlomku a cisla.
     */
    public void testPlus()
    {
        v1 = z1.plus( z2 );    
        tiskni( v1, z1, '+', z2 );
        assertEquals( "[3/1]", v1.toString() );
        
        v2 = z12.plus( z34 );  
        tiskni( v2, z12, '+', z34 );
        assertEquals( "[5/4]", v2.toString() );
        
        v2 = z34.plus( z12 );  
        tiskni( v2, z34, '+', z12 );
        assertEquals( "[5/4]", v2.toString() );
        
        v3 = v1.plus(v2);      
        tiskni( v3, v1, '+', v2 );
        assertEquals( "[17/4]", v3.toString() );
        
        v3 = v2.plus(v2);      
        tiskni( v3, v2, '+', v2 );
        assertEquals( "[5/2]", v3.toString() );
        
        v3 = v2.plus(2);      
        tiskni( v3, v2, '+', new Zlomek_5(2) );
        assertEquals( "[13/4]", v3.toString() );
    }
    

    /***************************************************************************
     * Testuje odcitani zlomku a zlomku a cisla.
     */
    public void testMinus()
    {
        v1 = z1.minus( z2 );    
        tiskni( v1, z1, '-', z2 );
        assertEquals( "[-1/1]", v1.toString() );
        
        v2 = z12.minus( z34 );
        tiskni( v2, z12, '-', z34 );
        assertEquals( "[-1/4]", v2.toString() );
        
        v2 = z34.minus( z12 );
        tiskni( v2, z34, '-', z12 );
        assertEquals( "[1/4]", v2.toString() );
        
        v3 = v1.minus(v2);
        tiskni( v3, v1, '-', v2 );
        assertEquals( "[-5/4]", v3.toString() );
        
        v3 = v2.minus(v2);
        tiskni( v3, v2, '-', v2 );
        assertEquals( "[0/1]", v3.toString() );
        
        v3 = v2.minus(2);
        tiskni( v3, v2, '-', new Zlomek_5(2) );
        assertEquals( "[-7/4]", v3.toString() );
        
        v3 = v2.odectiOd(2);
        tiskni( v3, new Zlomek_5(2), '-', v2 );
        assertEquals( "[7/4]", v3.toString() );
    }

    
    /***************************************************************************
     * Testuje nasobeni zlomku a zlomku a cisla.
     */
    public void testKrat()
    {
        v1 = z1.krat( z2 );
        tiskni( v1, z1, '*', z2 );
        assertEquals( "[2/1]", v1.toString() );
        
        v2 = z12.krat( z34 );
        tiskni( v2, z12, '*', z34 );
        assertEquals( "[3/8]", v2.toString() );
        
        v2 = z34.krat( z12 );
        tiskni( v2, z34, '*', z12 );
        assertEquals( "[3/8]", v2.toString() );
        
        v3 = v1.krat(v2);
        tiskni( v3, v1, '*', v2 );
        assertEquals( "[3/4]", v3.toString() );
        
        v3 = v2.krat(v2);
        tiskni( v3, v2, '*', v2 );
        assertEquals( "[9/64]", v3.toString() );
        
        v3 = v2.krat(2);
        tiskni( v3, v2, '*', new Zlomek_5(2) );
        assertEquals( "[3/4]", v3.toString() );
    }
    

    /***************************************************************************
     * Testuje deleni zlomku a zlomku a cisla.
     */
    public void testDeleno()
    {
        v1 = z1.deleno( z2 );    
        tiskni( v1, z1, ':', z2 );
        assertEquals( "[1/2]", v1.toString() );
        
        v2 = z12.deleno( z34 );
        tiskni( v2, z12, ':', z34 );
        assertEquals( "[2/3]", v2.toString() );
        
        v2 = z34.deleno( z12 );
        tiskni( v2, z34, ':', z12 );
        assertEquals( "[3/2]", v2.toString() );
        
        v3 = v1.deleno(v2);
        tiskni( v3, v1, ':', v2 );
        assertEquals( "[1/3]", v3.toString() );
        
        v3 = v2.deleno(v2);
        tiskni( v3, v2, ':', v2 );
        assertEquals( "[1/1]", v3.toString() );
        
        v3 = v2.deleno(2);
        tiskni( v3, v2, ':', new Zlomek_5(2) );
        assertEquals( "[3/4]", v3.toString() );
        
        v3 = v2.delCislo(2);
        tiskni( v3, new Zlomek_5(2), ':', v2 );
        assertEquals( "[4/3]", v3.toString() );
    }


    /***************************************************************************
     * Test prevodu zlomku na cislo
     */
    public void testPrevody()
    {
        int i;
        double d;
        Zlomek_5 z125 = new Zlomek_5( 12, 5 );
        
        i = z1.intValue();
        tiskni( ""+i, z1 );
        assertEquals( 1, i );
        
        i = z34.intValue();
        tiskni( ""+i, z34 );
        assertEquals( 0, i );
        
        i = z125.intValue();
        tiskni( ""+i, z125 );
        assertEquals( 2, i, 0 );
        
        d = z1.doubleValue();
        tiskni( ""+d, z1 );
        assertEquals( 1.0, d, 0 );
        
        d = z12.doubleValue();
        tiskni( ""+d, z34 );
        assertEquals( 0.5, d, 0 );
        
        d = z125.doubleValue();
        tiskni( ""+d, z125 );
        assertEquals( 2.4, d, 0 );
    }
    
    
}


