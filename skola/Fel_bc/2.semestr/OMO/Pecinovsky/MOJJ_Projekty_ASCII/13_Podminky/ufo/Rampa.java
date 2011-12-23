package ufo;

import java.awt.geom.Rectangle2D;


/*******************************************************************************
 * Instance tridy predstavuji startovaci a pristavaci rampy.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Rampa extends Dispecer.Tvar
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Odkaz na okno, ve kterem se vsechno kresli. */
    protected static final Vesmir V = Vesmir.getVesmir();
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    Rectangle2D.Double ctverec;
    Cislo cislo;
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich rampu se zadanym cislem.
     * 
     * @param cislo  Cislo konstruovane rampy
     * @param x      Vodorovna souradnice konstruovane rampy
     * @param y      Svisla souradnice konstruovane rampy
     */
    public Rampa( int cislo, double x, double y )
    {
        super( x, y, 2*Talir.VELIKOST, Dispecer.barvaRampy );
        this.cislo = new Cislo( cislo, x, y );
        int r2 = rozmer >> 1;   //Polovicni rozmer
        ctverec = new Rectangle2D.Double(
            (int)xPos-r2, (int)yPos-r2, rozmer, rozmer );
        nakresli();
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***********************************************************************
     * Nakresli svoji instanci.
     * Pri kresleni povazuje za svoji pozici stred ctverce.
     */
    public void nakresli()
    {
        V.setBarvaPopredi( barva );
        V.zapln( ctverec );
        cislo.nakresli();
    }
    
    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***********************************************************************
     * Zaparkuje zadane UFO nad svym stredem.
     *
     * @param ufo  UFO, ktere je treba zaparkovat.
     */
    public void zaparkuj( UFO ufo )
    {
        ufo.vypniMotory();
        ufo.setRychlost( xPos - ufo.getX(),  yPos - ufo.getY() );
        ufo.popojed( 1 );
        ufo.setRychlost( 0, 0 );
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

