package priklady;

import rup.cesky.utility.IO;
import rup.cesky.tvary.IMultiposuvny;
import rup.cesky.tvary.Kruh;
import rup.cesky.tvary.Multipresouvac;

import java.util.Random;


/*******************************************************************************
 * Trida Molekuly_10a slouzi k ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class Molekuly extends Kruh implements IMultiposuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /* Minimalni rychlost pohybujicich se molekul. */
    private static final int MIN_RYCHLOST = 50;

    /* Maximalni rychlost pohybujicich se molekul. */
    private static final int MAX_RYCHLOST = 500;

    /* Rozdil mezi maximalni a minimalni rychlosti pro generovani
     * nahodnych rychlosti. */
    private static final int MM_RYCHLOST = MAX_RYCHLOST - MIN_RYCHLOST;

    /* Velikost kroku aktivniho platna (konstatnu SP trida dedi po rodicich. */
    private static final int KROK = SP.getKrok();

    /* Maximlalni vodorovna souradnice molekuly -
     * ma-li molekula zustat cela na platne,
     * musi mit souradnici o KROK mensi ne maximalni sirka platna. */
    public static final int MAX_X = (SP.getSloupcu() - 1) * KROK;

    /* Maximlalni svisla souradnice molekuly. */
    public static final int MAX_Y = (SP.getRadku() - 1) * KROK;

    /* Spolecny generator  nahodnych cisel. */
    private static final Random RND = new Random();

    /* Multipresouvac starajici se o presuny vsech molekul. */
    private static final Multipresouvac MP = Multipresouvac.getInstance();


//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Zepta se uzivatele, jak dlouho ma simulace nahodneho pohybu molekul 
     * bezet, a pak ji na zadanou dobu spusti.
     */
    public static void sekundBehu()
    {
        int sekund = IO.zadej( "Kolik sekund maji molekuly bezet", 5 );
        start5();
        int milisekund = 1000 * sekund;
        IO.cekej( milisekund );
        System.exit( 0 );
    }
    
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Bezparametricky konstruktor vytvori instanci ve stredu platna a
     * zavola metodu presunuto, cimz odstartuje opakovany presun.
     */
    public Molekuly()
    {
        super( MAX_X / 2,  MAX_Y / 2,  KROK );
        SP.pridej( this );
        presunuto();
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Nastavi novou nahodnou pozici a rychlost a pozada multipresouvac,
     * aby ji tam presunul.
     */
    public void presunuto()
    {
        int x = RND.nextInt( MAX_X );
        int y = RND.nextInt( MAX_Y );
        int r = MIN_RYCHLOST + RND.nextInt(MM_RYCHLOST);
        Multipresouvac mp = Multipresouvac.getInstance();
        mp.presun( this, x, y, r );
    }

//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda vytvori 5 instanci a "pusti je do sveta".
     */
    public static void start5()
    {
        SP.setMrizka( false );
        new Molekuly();
        new Molekuly();
        new Molekuly();
        new Molekuly();
        new Molekuly();
    }


}

