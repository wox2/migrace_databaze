package posuvne_sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Presouvac;
import rup.cesky.tvary.Smer8;

import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Trojuhelnik;


/*******************************************************************************
 * Trida VSipka definuje sipku otocenou na vychod.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class VSipka implements ISipka
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    private static final Presouvac pres = new Presouvac( 5 );
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private Trojuhelnik spicka;
    private Obdelnik    telo;
    
    

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich novou, na vychod otocenou sipku
     * zadane barvy.
     *
     * @param x  x-ova polickova souradnice
     * @param y  y-ova polickova souradnice
     * @param barva  barva sipky
     */
    public VSipka( int x, int y, Barva barva )
    {
        int krok = SP.getKrok();
        int k2   = krok/2;
        spicka = new Trojuhelnik( x*krok + k2, y*krok, k2, krok, barva, 
				  Smer8.VYCHOD );
        telo   = new Obdelnik(  x*krok, y*krok + krok/4, k2, k2, barva );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati vodorovnou souradnici sipky.
     *
     * @return  x-ova souradnice sipky
     */
    public int getX()
    {
        return telo.getX();
    }
    
    
    /***************************************************************************
     * Vrati svislou souradnici sipky.
     *
     * @return  y-ova souradnice sipky
     */
    public int getY()
    {
        return spicka.getY();
    }
    

    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return new Pozice( telo.getX(), spicka.getY() );
    }

    
    /***************************************************************************
     * Nastavi vodorovnou a svislou souradnici sipky.
     *
     * @param x  Zadavana x-ova souradnice
     * @param y  Zadavana y-ova souradnice
     */
    public void setPozice(int x, int y)
    {
        int krok = SP.getKrok();
        spicka.setPozice( x + krok/2, y );
        telo  .setPozice( x,          y + krok/4 );
    }
    

    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param pozice   Nova pozice instance
     */
    public void setPozice(Pozice pozice)
    {
        setPozice( pozice.x, pozice.y );
    }

    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param g Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli(Kreslitko g)
    {
        spicka.nakresli( g );
        telo.nakresli( g );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public ISipka doleva()
    {
        int krok = SP.getKrok();
        return new SSipka( getX()/krok, getY()/krok, spicka.getBarva() );
    }
    
    

    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred()
    {
        pres.presunNa( this, getX()+SP.getKrok(),  getY() );
        SP.odstran( this );  //Presouvac ji nasadil, odstranit se musi sama
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

