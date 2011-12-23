package priklady;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;

import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Cara;


/*******************************************************************************
 * Trida XObdelnik_10 slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class XObdelnik_10 extends Obdelnik
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private Cara hlavni;    //Hlavni diagonala
    private Cara vedlejsi;  //Vedlejsi diagonala



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude mit stred na pruseciku hran prvniho a druheho pole
     * a bude mit vysku 1 pole a sirku 2 pole.
     */
    public XObdelnik_10()
    {
        this( SP.getKrok(), SP.getKrok(), 2*SP.getKrok(), SP.getKrok() );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param x      x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y      y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     */
    public XObdelnik_10(int x, int y, int sirka, int vyska)
    {
        this( x, y, sirka, vyska, Barva.CERVENA );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery a barvou.
     *
     * @param x      x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y      y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka  Sirka vytvarene instance,  sirka > 0
     * @param vyska  Vyska vytvarene instance,  vyska > 0
     * @param barva  Barva obdelnika tvoriciho instanci
     */
    public XObdelnik_10(int x, int y, int sirka, int vyska, Barva barva)
    {
        super( x-sirka/2, y-vyska/2, sirka, vyska, barva );
        //Pomocne promenne mohu bohuzel definovat az nyni,
        //protoze pred volanim rodicovskeho kontruktoru nesmi byt nic
        int x0 = x - sirka/2;
        int y0 = y - vyska/2;
        hlavni   = new Cara( x0, y0,       x0+sirka, y0+vyska );
        vedlejsi = new Cara( x0, y0+vyska, x0+sirka, y0       );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    @Override
    public int getX()
    {
        return super.getX() + getSirka()/2;
    }


    @Override
    public int getY()
    {
        return super.getY() + getVyska()/2;
    }


    @Override
    public void setPozice( int x, int y )
    {
        int s2 = getSirka() / 2;
        int v2 = getVyska() / 2;
        SP.nekresli(); {
            super.setPozice( x-s2, y-v2 );
            hlavni.setPozice( x-s2, y-v2 );
            vedlejsi.setPozice( x-s2, y+v2 );
        } SP.vratKresli();
    }


    @Override
    public void setRozmer( int sirka, int vyska )
    {
        int s2 = sirka / 2;
        int v2 = vyska / 2;
        SP.nekresli(); {
            super.setPozice( getX()-s2, getY()-v2 );
            super.setRozmer( sirka,     vyska );
            int x0 = super.getX();      //getX() - sirka/2;
            int y0 = super.getY();      //getY() - vyska/2;
            int xn = x0 + sirka;
            int yn = y0 + vyska;
            hlavni  .spoj( x0, y0, xn, yn );
            vedlejsi.spoj( x0, yn, xn, y0 );
        } SP.vratKresli();
    }


//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    @Override
    public void nakresli( Kreslitko kr )
    {
        super   .nakresli( kr );
        hlavni  .nakresli( kr );
        vedlejsi.nakresli( kr );
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}


