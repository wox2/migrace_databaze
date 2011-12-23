package letadla;

import rup.cesky.tvary.Barva;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Multipresouvac;
import rup.cesky.tvary.Obdelnik;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Strelba extends KeyAdapter
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /* Aplikacni okno, v nemz se vsechno odehrava. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();

    /* Spolecny mulitpresouvac vsech pohyblivych predmetu ve hre. */
    private static final Multipresouvac MP = Multipresouvac.getInstance();

    /** Vsechny strely budou mit stejnou rychlost. */
    public static final int RYCHLOST = 200;


//== PROMENNE ATRIBUTY TRIDY ===================================================

    /* Priznak umoznujici spustit pouze jednu instanci hry. */
    private static boolean spusteno = false;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /* Instance predstavujici sestrelovana letadla. */
    private final Letadlo letadlo;

    /* Instance predstavujici hlaven protiletadloveho dela. */
    private final Obdelnik hlaven;

    /* Vodorovna souradnice hlavne a strely. */
    private final int xStrely;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor je soukromy, aby trida mohla zabezpecit,
     * ze bude vytvorena jedina instance.
     *
     * @param sirkaNebe        Sirka zobrazovane casti nebe v bodech.
     * @param vyskaNebe        Vyska zobrazovane casti nebe v bodech.
     * @param delkaLetadla     Vodorovny rozmer preletavajicich letadel.
     * @param rychlostLetadla  Rychlost letadel v bodech za sekundu.
     */
    private Strelba( int sirkaNebe,    int vyskaNebe,
                     int delkaLetadla, int rychlostLetadla)
    {
        spusteno = true;    //Aby nebylo mozo vytvorit dalsi instanci

        //Nastav bodovou velikost platna a udelej z nej nebe
        SP.setKrokRozmer( 1,  sirkaNebe,  vyskaNebe );
        SP.setBarvaPozadi( Barva.AZUROVA );

        //Strilet budeme z poloviny platna
        xStrely = (SP.getSloupcu() - Strela.PRUMER) / 2;
        int vyskaHlavne = Strela.PRUMER * 3;

        //Hlaven bude na kazde strane o 1 bod sirsi nez strela
        //podle toho se musi upravit jeji rozmer i vodorovna pozice
        hlaven  = new Obdelnik(
                     xStrely - 1,                       //x
                     SP.getRadku() - vyskaHlavne,       //y
                     Strela.PRUMER + 2,                 //sirka
                     vyskaHlavne,                       //vyska
                     Barva.CERNA );                     //barva
        SP.pridej( hlaven );

        letadlo = new Letadlo( delkaLetadla, rychlostLetadla );

        //Prihlas se jako posluchac klavesnice
        SP.prihlasKlavesnici( this );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Metoda reagujici na udalosti klavesnice.
     *
     * @param ke   Udalost klavesnice, na niz se bude reagovat.
     */
    @Override
    public void keyPressed( KeyEvent ke )
    {
        if( ke.getKeyChar() == ' ' )
        {
            Strela strela = new Strela( xStrely, letadlo );
            MP.presun( strela, xStrely, Letadlo.VYSKA_OSY, RYCHLOST );
       }
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================


    /***************************************************************************
     * Spusti hru se zadanymi parametry. Pokusi-li se nekdo spustit dalsi
     * instanci hry, vyhodi vyjimku IllegalStateException.
     *
     * @param sirkaNebe        Sirka zobrazovane casti nebe v bodech.
     * @param vyskaNebe        Vyska zobrazovane casti nebe v bodech.
     * @param delkaLetadla     Vodorovny rozmer preletavajicich letadel.
     * @param rychlostLetadla  Rychlost letadel v bodech za sekundu.
     */
    public static void start( int sirkaNebe,    int vyskaNebe,
                              int delkaLetadla, int rychlostLetadla)
    {
        if( spusteno ) {
            throw new IllegalStateException(
                "Hra jiz bezi - nelze spustit soucasne nekolik instanic hry");
        }
        new Strelba( sirkaNebe, vyskaNebe, rychlostLetadla, delkaLetadla );
    }


}

