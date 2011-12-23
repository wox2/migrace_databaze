package priklady;

import rup.cesky.utility.IO;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Kruh;


/*******************************************************************************
 * Trida Balon_12a simuluje pruzny padajici balon, ktery pada zrychlene dolu
 * a po dopadu se odrazi zmensenou rychlosti, takze o nejake dobe doskace.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public final class Balon_16 extends Kruh implements IAnimovany
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Zmena rychlosti za jeden snimek. */
    private static final int IMPLICITNI_VELIKOST = 20;

    /** Zmena rychlosti za jeden snimek. */
    private static final Barva IMPLICITNI_BARVA_BALONU = Barva.CERVENA;

    private static final Animator ANIMATOR = Animator.getInstance();

    /** Gravitacni zrychleni. **/
    private static final double GRAVITACNI_ZRYCHLENI  = 400;

    /** Zmena rychlosti za jeden snimek. */
    private static final double UTLUM = 0.85;

    /** Zmena rychlosti za jeden snimek. */
    private static final double MINIMALNI_RYCHLOST = 50;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final double konecne_y = SP.getBVyska() - getVyska();


//== PROMENNE ATRIBUTY INSTANCI ================================================

    private double rychlost;
    private double y;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vytvori v levem hornim rohu aktivniho platna
     * balon o prumeru 25 a necha jej spadnout.
     */
    public Balon_16()
    {
        this( 0, 0, IMPLICITNI_VELIKOST, IMPLICITNI_BARVA_BALONU );
    }


    /***************************************************************************
     * Vytvori novy balon na zadane pozici a o zadanem prumeru
     * a necha jej spadnout.

     * @param x      Vodorovna souradnice
     * @param y      Svisla souradnice
     * @param prumer Prumer vytvareneho balonu
     * @param barva  Barva vytvareneho balonu
     */
    public Balon_16(int x, int y, int prumer, Barva barva)
    {
        super( x, y, prumer, barva );
        SP.pridej( this );
        spadni();
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *
     * @param kr Kreslitko, kterym se instance nakresli na platno.
     */
    @Override
    public void nakresli( Kreslitko kr )
    {
        super.nakresli(kr);
    }

    /***************************************************************************
     * Simuluje poskakovani balonu v gravitacnim poli.
     * Balon se po dopadu odrazi po dopadu se odrazi zmensenou rychlosti,
     * takze o nejake dobe doskace.
     *
     * @return {@code true} pokud touto akci animace skoncila a
     *         animator ma danya objekt vyradit ze seznamu animovanyczh objektu
     */
    public boolean popojed()
    {
        if( y < konecne_y )
        {
            rychlost += GRAVITACNI_ZRYCHLENI * ANIMATOR.getUplynulo() /1000;
            y += rychlost * ANIMATOR.getUplynulo() /1000;
        }
        else
        {
            y = konecne_y - 1;
            rychlost = -rychlost * UTLUM;
            if( -rychlost < MINIMALNI_RYCHLOST )
            {
                return true;
            }
        }
        //Rodicovska verze setPozice vyzaduje celociselne parametry -
        // aktualni svislou souradnici proto musime pretypovat
        setPozice( getX(), (int)y );
        return false;
    }


//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Presune balon do zadane pozice a necha jej odtud spadnout.
     */
    public void presunNa( int x, int y )
    {
        setPozice( x, y );  //Nastaveni pozice muzes sverit rodici
        spadni();           //Zacni padat
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
//        this.y = y;
        this.rychlost = 0;
        ANIMATOR.start();
        ANIMATOR.pridej( this );
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
        //Velikost aktivniho platna ne nastavena na obrazovku 1024768
        SP.setKrokRozmer( 50, 3 , 15 );
        SP.setMrizka( true );

        Balon_16 b1 = new Balon_16();
        IO.cekej( 1000 );
        Balon_16 b2 = new Balon_16( 50, 50, 100, Barva.ZELENA );
        IO.cekej( 1000 );
        Balon_16 b3 = new Balon_16( 100, 100, 50, Barva.MODRA );
    }


}

