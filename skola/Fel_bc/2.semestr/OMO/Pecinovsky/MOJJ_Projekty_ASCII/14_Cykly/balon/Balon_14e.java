package balon;

import rup.cesky.utility.IO;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kruh;
import rup.cesky.tvary.SpravcePlatna;


/*******************************************************************************
 * Trida {@code Balon_14e} pracuje stejne jako {@link Balon_14d};
 * ma pouze upravenou testovaci metodu, ktera presun balonku
 * nahrazuje vytvorenim noveho balonku.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public final class Balon_14e implements Runnable
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
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vytvori v levem hornim rohu aktivniho platna
     * balon o prumeru 25 a necha jej spadnout.
     */
    public Balon_14e()
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
    public Balon_14e(int x, int y, int prumer, Barva barva)
    {
        balon = new Kruh( x, y, prumer, barva );
        SP.pridej( balon );
        spadni();
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
        balon.setPozice( x, y );  //Nastaveni pozice muzes sverit rodici
        spadni();                 //Zacni padat z nove pozice
    }


    /***************************************************************************
     * Simuluje poskakovani balonu v gravitacnim poli.
     * Balon se po dopadu odrazi zmensenou rychlosti,
     * takze po nejake dobe doskace.
     */
    public void run()
    {
        double konecne_y = SP.getBVyska() - balon.getVyska();
        double rychlost  = 0;
        int    x = balon.getX(); //Nemeni se, takze nepotrebujeme double
        double y = balon.getY();

        do{
            while( y < konecne_y )
            {
                rychlost += SNIMKOVE_ZRYCHLENI;
                y += rychlost / FREKVENCE;
                //Rodicovska verze setPozice vyzaduje celociselne parametry -
                // aktualni svislou souradnici proto musime pretypovat
                balon.setPozice( x, (int)y );
                IO.cekej( PERIODA );
            }
            //setPozice( xPos, (int)konecne_y);
            y = konecne_y - 1;     //Aby opet vstoupil do cyklu while
            rychlost = -rychlost * UTLUM;
        }while( -rychlost > MINIMALNI_RYCHLOST );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Simuluje poskakovani balonu v gravitacnim poli.
     * Balon se po dopadu odrazi po dopadu se odrazi zmensenou rychlosti,
     * takze o nejake dobe doskace.
     */
    private void spadni()
    {
        Thread vlakno = new Thread( this );
        vlakno.start();
    }

    
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Test vytvori implicitni balon a necha jej spadnout.
     * Pak vytvori druhy balon a po jeho dopadu presune prvni do nove pozice
     * a opet jej odtud necha spadnout.
     */
    public static void test()
    {
        SP.odstranVse();
        //Velikost aktivniho platna je nastavena na obrazovku 1024768
        SP.setKrokRozmer( 50, 3 , 14 );
        SP.setMrizka( true );

//        Balon_14e b1 = new Balon_14d();
//        rup.cesky.utility.IO.cekej(1000);
//        Balon_14e b2 = new Balon_14d( 100, 100, 50, Barva.MODRA );
//        b1.presunNa( 50, 250 );

        Balon_14e b1 = new Balon_14e();
        IO.cekej( 1000 );
        Balon_14e b2 = new Balon_14e( 50, 50, 100, Barva.ZELENA );
        IO.cekej( 1000 );
        Balon_14e b3 = new Balon_14e( 100, 100, 50, Barva.MODRA );
    }


}

