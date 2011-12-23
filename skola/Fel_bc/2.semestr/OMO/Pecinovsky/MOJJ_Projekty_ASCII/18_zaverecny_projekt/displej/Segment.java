package displej;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Smer8;


/*******************************************************************************
 * Instance tridy Segment predstavuji jednotlive segmenty sedmisegmentoveho 
 * displeje pro zobrazeni cislic.
 *
 * @author Rudolf Pecinovsky
 */
public class Segment implements IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /***************************************************************************
     * Parametry jednotlivych segmetu:                                    0
     * 0. index: vodorovna polickova souradnice v ramci cislice         1   2
     * 1. index: svisla polickova souradnice v ramci cislice              3
     * 3. index: priznak vodorovnosti (0=vodorovny, 1=svisly)           4   5
     *                                                                    6
     */
    private static final int[][] param = 
        { {0,0,0}, {0,0,1}, {1,0,1}, {0,1,0}, {0,1,1}, {1,1,1}, {0,2,0} };
        //  V-H     S-H-L    S-H-R     V-S     S-D-L    S-D-R     V-D
        
        

//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Sirka segmentu v bodech - Modul. */
    private static int M;
    
    /** Delka segmentu bez krajich trojuhelniku jako nasobek jeho sirky. */
    private static int D;
    
    /** Celkova (Totatlni) delka segmentu, tj. delka vcetne krajnich 
     *  trojuhelniku jako nasobek sirky segmentu. */
    private static int T;
    
    /** Polovina sirky segmentu v bodech (= vyska trojuhelniku). */
    private static int P;
    
    /** Barva popredi = barva sviticich segmetnu. */
    private static Barva A;
    
    /** Barva pozadi = pocatecni barva segmentu + barva zhasnutych segmentu. */
    private static Barva Z;
    
    
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Leva nebo horni spicka. */
    private final Trojuhelnik spickaLH;
    
    /** Telo segmentu. */
    private final Obdelnik telo;    
    
    /** Prava nebo dolni spicka. */
    private final Trojuhelnik spickaRD; 
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private boolean sviti = false;
    
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================
    
    /***************************************************************************
     * Nastavi hodnoty konstant, s jejichz pomoci se budou vypocitavat
     * pozice a rozmenry vytvarenych segmetnu.
     */
    static void nastavKonstanty( int modul, int delka, 
                                 Barva popredi, Barva pozadi )
    {
        M = modul;
        P = modul / 2;
        D = delka * M;
        T = M + D;
        A = popredi;
        Z = pozadi;
    }

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadane polickove souradnici segment 
     * se zadanou orientaci, modulem a pocatecni barvou.
     *
     * @param i   Index segmentu.
     * @param x0  Vodorovna polickova souradnice instance bloku..
     * @param y0  Svisla polickova souradnice instance bloku.
     */
    private Segment( int i, int x0, int y0 )
    {
        int x = x0 + T*param[i][0];
        int y = y0 + T*param[i][1]; 
        
        if( param[i][2] == 0 )  //vodorovny
        {
            spickaLH = new Trojuhelnik( x+P, y, P, M, Z, Smer8.ZAPAD );
            telo     = new Obdelnik   ( x+M, y, D, M, Z              );
            spickaRD = new Trojuhelnik( x+T, y, P, M, Z, Smer8.VYCHOD);
        }
        else //svisly
        {    
            spickaLH = new Trojuhelnik( x, y+P, M, P, Z, Smer8.SEVER );
            telo     = new Obdelnik   ( x, y+M, M, D, Z              );
            spickaRD = new Trojuhelnik( x, y+T, M, P, Z, Smer8.JIH   );
        }
        SpravcePlatna.getInstance().pridej( this );
    }
    
    

    /***************************************************************************
     * Tovarni metoda, ktera vrati kompletni sedmici segmentu
     * pro cislici sedmisegmentoveho displeje na zadane pozici.
     *
     * @param mm  Velikost modulu v bodech.
     * @param xp  Vodorovna polickova souradnice.
     * @param yp  Svisla polickova souradnice.
     * @param b   Barva segmentu.
     */
    public static Segment[] getSedmicka( int x0, int y0 )
    {
        Segment[] sedmicka = new Segment[7];
        for( int i=0;   i < 7;   i++)
            sedmicka[i] = new Segment( i, x0, y0 );
        return sedmicka;
    }
    
    
//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Podle hodnoty parametr segmen tozsviti, tj. nastavi mu barvu poredi,
     * nebo zhasne, tj. nastavi mu barvu pozadi. 
     * Pokud segment pozadovanou barvu jiz ma, neudela nic.
     *
     * @param rozsvitit   Zda segmentu nastavit barvu popredi.
     */
    public void setBarva( boolean rozsvitit )
    {
        if( rozsvitit == sviti ) {
            return; //================>
        }

        sviti = ! sviti;
        Barva b = sviti ? A : Z;
        spickaLH.setBarva(b);
        telo    .setBarva(b);
        spickaRD.setBarva(b);
    }


    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Nakresli segment za pomoci dodaneho kreslitka.
     * 
     * @param kr  Kreslitko.
     */
    public void nakresli( Kreslitko kr )
{
        spickaLH.nakresli( kr );
        telo    .nakresli( kr );
        spickaRD.nakresli( kr );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== OSTATNI METODY INSTANCI ===================================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

