import rup.cesky.utility.IO;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kruh;
import rup.cesky.tvary.SpravcePlatna;


/*******************************************************************************
 * Trida {@code Balon_15a} simuluje pruzny padajici balon,
 * ktery pada zrychlene dolu a po dopadu se odrazi zmensenou rychlosti, 
 * takze o nejake dobe doskace; kazdy balon skace ve vlastnim vlakne,
 * takze muze skakat nekolik balonu soucasne.
 * Oproti tride {@link rup.cesky._14_cykly.balon.Balon_14g} neimplementuje
 * rozhrani {@link Runnable} a misto toho definuje vlakna jako instance
 * soukrome vnitrni tridy.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public final class Balon_15a
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Zmena rychlosti za jeden snimek. */
    private static final int IMPLICITNI_VELIKOST = 20;

    /** Zmena rychlosti za jeden snimek. */
    private static final Barva IMPLICITNI_BARVA = Barva.CERVENA;

    /** Gravitacni zrychleni. **/
    private static final double GRAVITACNI_ZRYCHLENI  = 400;

    /** Pocet prekresleni za sekundu. */
    private static final int FREKVENCE = 50;

    /** Cekani mezi snimky. */
    private static final int PERIODA = 1000 / FREKVENCE;

    /** Zmena rychlosti za jeden snimek. */
    private static final double SNIMKOVE_ZRYCHLENI =
                                    GRAVITACNI_ZRYCHLENI / FREKVENCE;

    /** Zmena rychlosti za jeden snimek. */
    private static final double UTLUM = 0.85;

    /** Zmena rychlosti za jeden snimek. */
    private static final double MINIMALNI_RYCHLOST = 50;
    
    /** Spravce platna, na nemz probiha animace. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    private final Kruh balon;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private Vlakno vlakno;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vytvori v levem hornim rohu aktivniho platna
     * balon o prumeru 25 a necha jej spadnout.
     */
    public Balon_15a()
    {
        this( 0, 0, IMPLICITNI_VELIKOST, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Vytvori novy balon na zadane pozici a o zadanem prumeru
     * a necha jej spadnout.
     * 
     * @param x      Vodorovna bodova souradnice
     * @param y      Svisla bodova souradnice
     * @param prumer Prumer vytvareneho balonu   
     * @param barva  Barva vytvareneho balonu
     */
    public Balon_15a(int x, int y, int prumer, Barva barva)
    {
        balon = new Kruh( x, y, prumer, barva );
        SP.pridej( balon );
        spadni();       //Inicializuje atribut vlakno
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Presune balon do zadane pozice a necha jej odtud spadnout.
     * 
     * @param x Vodorovna bodova souradnice cilove pozice
     * @param y Svisla bodova souradnice cilove pozice
     */
    public void presunNa( int x, int y )
    {
        vlakno.interrupt();
        try {
            vlakno.join();
        }catch( Exception e ) {
            //Ocekavam NullPointerException ci InterruptedException
        }
        balon.setPozice( x, y );  //Nastaveni pozice muzes sverit rodici
        spadni();                 //Zacni padat
    }

    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getClass().getSimpleName() + 
               "(Barva="   + balon.getBarva() + 
               ", prumer=" + balon.getPrumer() +
               ", x="      + balon.getX() + ")";
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Zabezpeci animaci padani balonu z jeho aktualni pozice.
     */
    private void spadni()
    {
        vlakno = new Vlakno();
        vlakno.start();
    }

    
//== VNORENE A VNITRNI TRIDY ===================================================
    
    /***************************************************************************
     * Vnitrni trida realizujici vlakno animujici balonek predstavovany
     * sdruzenou instanci vnejsi tridy.
     */
    private class Vlakno extends Thread
    {
        /***********************************************************************
         * Vytvori novou instanci pojmenovanou podle sdruzene instance
         * vnejsi tridy.
         */
        Vlakno()
        {
            super( Balon_15a.this.toString()  );
        }


        /***********************************************************************
         * Simuluje poskakovani balonu v gravitacnim poli.
         * Balon se po dopadu odrazi zmensenou rychlosti,
         * takze po nejake dobe doskace.
         */
        @Override
        public void run()
        {
            double konecne_y = SP.getBVyska() - balon.getVyska();
            double rychlost  = 0;
        int    x = balon.getX(); //Nemeni se, takze nepotrebujeme double
        double y = balon.getY();

    VNEJSI:
        do{
            while( y < konecne_y )
            {
                rychlost += SNIMKOVE_ZRYCHLENI;
                y += rychlost / FREKVENCE;
                //Rodicovska verze setPozice vyzaduje celociselne parametry -
                // aktualni svislou souradnici proto musime pretypovat
                balon.setPozice( x, (int)y );
                IO.cekej( PERIODA );
                if( Thread.interrupted() ) {
                    break VNEJSI;       //----- Vyskok z nekolika bloku ----->
                    }
                }
                //setPozice( xPos, (int)konecne_y);
                y = konecne_y - 1;     //Aby opet vstoupil do cyklu while
                rychlost = -rychlost * UTLUM;
            }while( -rychlost > MINIMALNI_RYCHLOST );
        }
    }
    
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Test vytvori implicitni balon a necha jej spadnout.
     * Pak vytvori druhy balon a po jeho dopadu presune prvni do nove pozice
     * a opet jej odtud necha spadnout.
     */
    public static void test()
    {
        final int KROK = 50;
        final int K2   = 2*KROK;
        SP.odstranVse();
        //Velikost aktivniho platna ne nastavena na obrazovku 1024768
        SP.setKrokRozmer( KROK, 4 , 15 );
        SP.setMrizka( true );

        Balon_15a b1 = new Balon_15a();
        IO.cekej( 1000 );
        Balon_15a b2 = new Balon_15a( KROK, KROK, K2, Barva.ZELENA );
        IO.cekej( 1000 );
        Balon_15a b3 = new Balon_15a( K2, K2, KROK, Barva.MODRA );
        IO.cekej( 3000 );
        b1.presunNa(3*KROK, 0);
    }


}

