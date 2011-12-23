/*******************************************************************************
 * Testovaci trida UFOTest slouzi ke komplexnimu otestovani
 * tridy ...
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class UFOTest extends junit.framework.TestCase
{
    Dispecer dispecer;
    UFO      ufo;

//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//##############################################################################
//== KONSTRUKTORY A METODY VRACEJICI INSTANCE VLASTNI TRIDY ====================

    /***************************************************************************
     * Vytvori test se zadanym nazvem.
     *  
     * @param nazev  Nazev konstruovaneho testu
     */
    public UFOTest(String nazev)
    {
        super( nazev );
    }



//##############################################################################
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
     */
    public void testUFO()
    {
        final int    TV   = Dispecer.TV;
        final double DTAH = UFO.DTAH;
        final int    f    = 4;  //frekvence

        double px=TV,  py=TV;   //Pozice
        double rx=0,   ry=0;    //rychlsot
        double tx=0,   ty=0;    //tah
        
        dispecer = Dispecer.getDispecer( 1, false );
        ufo = dispecer.pristavUFO();
        //----------------------------------------------------------------------
        assertEquals( "Spatne nastavena vodorovna pocatecni souradnice", 
                      px, ufo.getX(), .01 );
        assertEquals( "Spatne nastavena svisla pocatecni souradnice", 
                      py, ufo.getY(), .01 );
        assertEquals( "Spatne nastavena pocatecni vodorovna rychlost", 
                      rx, ufo.getXRychlost(), .01 );
        assertEquals( "Spatne nastavena pocatecni svisla rychlost", 
                      ry, ufo.getYRychlost(), .01 );
        assertEquals( "Spatne nastaveny pocatecni vodorovny tah", 
                      tx, ufo.getXTah(), .01 );
        assertEquals( "Spatne nastaveny pocatecni svisly tah", 
                      ty, ufo.getXTah(), .01 );
        //----------------------------------------------------------------------
        rx = 16;
        ry = 32;
        ufo.setRychlost( rx, ry );
        assertEquals( "Metoda setRychlost spatne nastavuje vodorovnou rychlost", 
                      rx, ufo.getXRychlost(), .01 );
        assertEquals( "Metoda setRychlost spatne nastavuje svislou rychlost", 
                      ry, ufo.getYRychlost(), .01 );
        ufo.popojed( 1 );
        assertEquals( "Metoda Popojed spatne nastavuje vodorovnou souradnici" +
                      " pri nulovem tahu motoru a jednotkove frekvenci", 
                      px+=rx, ufo.getX(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje svislou souradnici" +
                      " pri nulovem tahu motoru a jednotkove frekvenci", 
                      py+=ry, ufo.getY(), .01 );
        //----------------------------------------------------------------------
        ufo.vlevo();
        assertEquals( "Metoda vlevo spatne nastavuje vodorovny tah", 
                      tx-=DTAH, ufo.getXTah(), .01 );
        ufo.vlevo();
        assertEquals( "Metoda vlevo spatne nastavuje vodorovny tah", 
                      tx-=DTAH, ufo.getXTah(), .01 );
        assertEquals( "Metoda vlevo spatne nastavuje svisly tah", 
                      ty, ufo.getYTah(), .01 );
        //----------------------------------------------------------------------
        ufo.vzhuru();
        assertEquals( "Metoda vzhuru spatne nastavuje svisly tah", 
                      ty-=DTAH, ufo.getYTah(), .01 );
        ufo.vzhuru();
        assertEquals( "Metoda vzhuru spatne nastavuje svisly tah", 
                      ty-=DTAH, ufo.getYTah(), .01 );
        assertEquals( "Metoda vzhuru spatne nastavuje vodorovny tah", 
                      tx, ufo.getXTah(), .01 );
        //----------------------------------------------------------------------
        ufo.vypniMotory();
        assertEquals( "Metoda vypniMotory nevypina vodorovny tah", 
                      tx=0, ufo.getXTah(), .01 );
        assertEquals( "Metoda vypniMotory nevypina svisly tah", 
                      ty=0, ufo.getYTah(), .01 );
        //----------------------------------------------------------------------
        ufo.vpravo();
        assertEquals( "Metoda vpravo spatne nastavuje vodorovny tah", 
                      tx+=DTAH, ufo.getXTah(), .01 );
        ufo.vpravo();
        assertEquals( "Metoda vpravo spatne nastavuje vodorovny tah", 
                      tx+=DTAH, ufo.getXTah(), .01 );
        ufo.vpravo();
        assertEquals( "Metoda vpravo spatne nastavuje vodorovny tah", 
                      tx+=DTAH, ufo.getXTah(), .01 );
        assertEquals( "Metoda vpravo spatne nastavuje svisly tah", 
                      ty, ufo.getYTah(), .01 );
        //----------------------------------------------------------------------
        ufo.dolu();
        assertEquals( "Metoda dolu spatne nastavuje svisly tah", 
                      ty+=DTAH, ufo.getYTah(), .01 );
        ufo.dolu();
        assertEquals( "Metoda dolu spatne nastavuje svisly tah", 
                      ty+=DTAH, ufo.getYTah(), .01 );
        assertEquals( "Metoda dolu spatne nastavuje vodorovny tah", 
                      tx, ufo.getXTah(), .01 );
        //----------------------------------------------------------------------
        ufo.popojed( 1 );
        assertEquals( "Metoda popojed ovlivnuje vodorovny tah", 
                      tx, ufo.getXTah(), .01 );
        assertEquals( "Metoda popojed ovlivnuje svisly tah", 
                      ty, ufo.getYTah(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje vodorovnou rychlost" +
                      " pri NEnulovem tahu motoru a jednotkove frekvenci", 
                      rx+=tx, ufo.getXRychlost(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje svislou rychlost" +
                      " pri NEnulovem tahu motoru a jednotkove frekvenci", 
                      ry+=ty, ufo.getYRychlost(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje vodorovnou souradnici" +
                      " pri NEnulovem tahu motoru a jednotkove frekvenci", 
                      px+=rx, ufo.getX(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje svislou souradnici" +
                      " pri NEnulovem tahu motoru a jednotkove frekvenci", 
                      py+=ry, ufo.getY(), .01 );
        //----------------------------------------------------------------------
        ufo.popojed( f );
        assertEquals( "Metoda Popojed spatne nastavuje vodorovnou rychlost" +
                      " pri NEnulovem tahu motoru a NEjednotkove frekvenci", 
                      rx+=tx/f, ufo.getXRychlost(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje svislou rychlost" +
                      " pri NEnulovem tahu motoru a NEjednotkove frekvenci", 
                      ry+=ty/f, ufo.getYRychlost(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje vodorovnou souradnici" +
                      " pri NEnulovem tahu motoru a NEjednotkove frekvenci", 
                      px+=rx/f, ufo.getX(), .01 );
        assertEquals( "Metoda Popojed spatne nastavuje svislou souradnici" +
                      " pri NEnulovem tahu motoru a NEjednotkove frekvenci", 
                      py+=ry/f, ufo.getY(), .01 );
    }
    


    /***************************************************************************
     * 
     */
    public void testRychlost()
    {
       Dispecer disp = Dispecer.getDispecer();
       UFO u1 = disp.pristavUFO();
            u1.setRychlost(10,10);
        UFO u2 = disp.pristavUFO();
            u2.setRychlost(8,12);
        UFO u3 = disp.pristavUFO();
            u3.setRychlost(6,12);
        UFO u4 = disp.pristavUFO();
            u4.setRychlost(4,15);
        UFO u5 = disp.pristavUFO();
            u5.setRychlost(0,20);
    }
    

    /***************************************************************************
     * Vytvori standardnio dispecera a spusti tak hru.
     */
    public void testHra()
    {
        Dispecer.getDispecer();
    }
    

    /***************************************************************************
     * Vytvori standardnio dispecera a spusti tak hru.
     */
    public void testHra_4()
    {
        Dispecer disp = Dispecer.getDispecer();
        disp.pouzijUFO_4();
    }
    

    /***************************************************************************
     * Vytvori standardnio dispecera a spusti tak hru.
     */
    public void testRychlost_4()
    {
        Dispecer disp = Dispecer.getDispecer();
        disp.pouzijUFO_4();
        testRychlost();
    }
    

    /***************************************************************************
     * 
     * /
    public void testXXX()
    {
    }
    
/**/ 
}

