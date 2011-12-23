package posuvne_sipky;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.IPosuvny;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Presouvac;
import rup.cesky.tvary.Trojuhelnik;
import rup.cesky.tvary.Obdelnik;
import rup.cesky.tvary.Pozice;


/*******************************************************************************
 * Trida ASipka slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public abstract class ASipka implements IPosuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    protected static final SpravcePlatna SP = SpravcePlatna.getInstance();
    protected static final Presouvac pres = new Presouvac( 5 );
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    protected Trojuhelnik spicka;
    protected Obdelnik    telo;
    

//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor tridy Trida
     */
    public ASipka() 
    {
    }

    

//== ABSTRAKTNI METODY =========================================================

    /***************************************************************************
     * Vrati sipku, ktera je na stejne pozici, pouze je otocena o 90 vlevo.
     *
     * @return Sipka natocena do noveho smeru.
     */
    public abstract ASipka doleva();

    
    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public abstract void vpred();
    

    /***************************************************************************
     * Vrati vodorovnou souradnici sipky.
     *
     * @return  x-ova souradnice sipky
     */
    protected abstract int getX();
    
    
    /***************************************************************************
     * Vrati svislou souradnici sipky.
     *
     * @return  y-ova souradnice sipky
     */
    protected abstract int getY();
    
    
    
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati instanci tridy {@link Pozice} s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return new Pozice( getX(), getY() );
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
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Nakresli sipku zadanym kreslitkem.
     *
     * @param g Kreslitko (objekt typu Graphics2D), kterym se sipka nakresli.
     */
    public void nakresli( Kreslitko g )
    {
        spicka.nakresli( g );
        telo  .nakresli( g );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}


