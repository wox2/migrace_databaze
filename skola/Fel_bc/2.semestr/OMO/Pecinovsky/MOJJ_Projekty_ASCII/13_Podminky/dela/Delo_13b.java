package dela;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Multipresouvac;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Text;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/*******************************************************************************
 * Trida Delo_1 demonstruje moznosti reakce na udalost klavesnice.
 * 
 * Oproti tride Delo
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Delo_13b extends KeyAdapter implements IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /* Aplikacni okno, v nemz se vsechno odehrava. */
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    /* Spolecny multipresouvac vsech pohyblivych predmetu ve hre. */
    protected static final Multipresouvac MP = Multipresouvac.getInstance();
    
    protected static final int   RYCHLOST_STRELY    = 200;
    
    private static final int   VELIKOST_ZASOBNIKU = 100;
    private static final int   VYSKA_PODSTAVCE = 20;
    private static final Barva BARVA_PODSTAVCE = Barva.ZELENA;
    private static final Barva BARVA_HLAVNE    = Barva.CERNA;
    
    private static final int   X_POSUN_POPISKU = 5;
    private static final int   Y_POSUN_POPISKU = 3;
    private static final Barva BARVA_POPISKU = Barva.CERNA;
    
    protected static final int VYSKA_PLATNA;
    static 
    {
        //VYSKA_PLATNA = P.zadej( "Zadej rozmer platna", 500 );
        VYSKA_PLATNA = 300;
        SP.setKrokRozmer( 1, VYSKA_PLATNA, VYSKA_PLATNA );
    }
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
    
    private static int pocet = 0;
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    protected final Obdelnik podstavec;
    protected final Obdelnik hlaven;
    protected final char     spoust;
    protected final String   odpal;
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    protected int  xPos;
    protected Text popisek;
    protected int  naboju = VELIKOST_ZASOBNIKU;
    
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param x         Vodorovnai souradnice dela
     * @param spoust    Znak, kterym se delo odpaluje
     */
    public Delo_13b(int x, char spoust)
    {
        this.xPos = x;
        //Sirka podstavce a vyska hlavne bude rovna petinasobku prumeru strely
        podstavec = new Obdelnik( 
                        x - 2*Strela.PRUMER, 
                        VYSKA_PLATNA - VYSKA_PODSTAVCE,
                        5 * Strela.PRUMER,
                        VYSKA_PODSTAVCE,
                        BARVA_PODSTAVCE );
        hlaven = new Obdelnik(
                        x - 1, 
                        VYSKA_PLATNA - VYSKA_PODSTAVCE - 3*Strela.PRUMER,
                        Strela.PRUMER + 2,
                        3*Strela.PRUMER,
                        BARVA_HLAVNE );
        this.spoust  = spoust;
        this.odpal   = "[" + spoust + "] ";
        this.popisek = new Text( 
                        odpal + naboju,
                        podstavec.getX() + X_POSUN_POPISKU, 
			podstavec.getY() + Y_POSUN_POPISKU, 
			BARVA_POPISKU );
        SP.pridej( this );
        SP.prihlasKlavesnici( this );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
    
    public void nakresli(rup.cesky.tvary.Kreslitko kreslitko)
    {
        podstavec.nakresli( kreslitko );
        hlaven   .nakresli( kreslitko );
        popisek  .nakresli( kreslitko );
    }    

    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    @Override
    public void keyPressed( KeyEvent ke )
    {
        if( ke.getKeyChar() == spoust )
        {
            Strela strela = new Strela( xPos, VYSKA_PLATNA );
            SP.pridejPod( this, strela );
            MP.presun( strela, xPos, 0, RYCHLOST_STRELY );
        }
    }

//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void start_13b()
    {
        new Delo_13b( 100, 'n' );
        new Delo_13b( 200, 'm' );
    }


}

