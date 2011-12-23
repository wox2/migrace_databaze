package rup.cesky.tvary_3;

import java.awt.Font;
import java.awt.Graphics2D;


/*******************************************************************************
 * Trida Kreslitko slouzi k zprostredkovani kreslicich cshopnosti objektum
 * prihlasenym u {@code SpravcePlatna}.
 * Je konstruovan jako adapter objektu {@code java.awt.Graphics2D}.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Kreslitko
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private java.awt.Graphics2D g;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    private Barva barvaPozadi = null;


//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Inicializuje atribut adaptovanym objkektem.
     * @param g
     */
    public Kreslitko( Graphics2D g )
    {
        this.g = g;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi font, kterym se budou sazet vypisovane texty.
     *
     * @param font Nastavovany font
     */
    public void setFont( Font font )
    {
        g.setFont( font );
    }


    /***************************************************************************
     * Nastavi barvu pozadi kreslenych objektu
     *
     * @param barva Nastavovana barva pozadi
     */
    public void setPozadi( Barva barva )
    {
        g.setBackground( barva.getColor() );
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vykresli zadanou barvou na zadanych souradnicich nevyplneny oval
     * zadaneho rozmeru.
     *
     * @param x       x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y=0 ma horni okraj platna
     * @param sirka   Sirka kresleneho ovalu
     * @param vyska   Vyska kresleneho ovalu
     * @param barva   Barva kresleneho ovalu
     */
    public void kresliOval( int x, int y, int sirka, int vyska, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.drawOval( x, y, sirka, vyska );
    }


    /***************************************************************************
     * Vykresli zadanou barvou na zadanych souradnicich vyplneny oval
     * zadaneho rozmeru.
     *
     * @param x       x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y=0 ma horni okraj platna
     * @param sirka   Sirka kresleneho ovalu
     * @param vyska   Vyska kresleneho ovalu
     * @param barva   Barva kresleneho ovalu
     */
    public void vyplnOval( int x, int y, int sirka, int vyska, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.fillOval( x, y, sirka, vyska );
    }


    /***************************************************************************
     * Vykresli zadanou barvou na zadanych souradnicich nevyplneny obdelnik
     * zadaneho rozmeru.
     *
     * @param x       x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y=0 ma horni okraj platna
     * @param sirka   Sirka kresleneho obdelniku
     * @param vyska   Vyska kresleneho obdelniku
     * @param barva   Barva kresleneho obdelniku
     */
    public void kresliRam( int x, int y, int sirka, int vyska, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.drawRect( x, y, sirka, vyska );
    }


    /***************************************************************************
     * Vykresli zadanou barvou na zadanych souradnicich vyplneny obdelnik
     * zadaneho rozmeru.
     *
     * @param x       x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y=0 ma horni okraj platna
     * @param sirka   Sirka kresleneho obdelniku
     * @param vyska   Vyska kresleneho obdelniku
     * @param barva   Barva kresleneho obdelniku
     */
    public void vyplnRam( int x, int y, int sirka, int vyska, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.fillRect( x, y, sirka, vyska );
    }


    /***************************************************************************
     * Vykresli zadanou barvou nevyplneny mnohouhelnik se zadnymi vrcholy.
     *
     * @param x       Pole x-ovych souradnic vrcholu
     * @param y       Pole y-ovych souradnic vrcholu
     * @param barva   Barva kresleneho obdelniku
     */
    public void kresliPolygon( int[] x, int[] y, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.drawPolygon( x, y, Math.min(x.length, y.length) );
    }


    /***************************************************************************
     * Vykresli zadanou barvou vyplneny mnohouhelnik se zadnymi vrcholy.
     *
     * @param x       Pole x-ovych souradnic vrcholu
     * @param y       Pole y-ovych souradnic vrcholu
     * @param barva   Barva kresleneho obdelniku
     */
    public void vyplnPolygon( int[] x, int[] y, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.fillPolygon( x, y, Math.min(x.length, y.length) );
    }


    /***************************************************************************
     * Vykresli zadanou barvou caru se zadanymi vrcholy.
     *
     * @param x1      x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y1      y-ova souradnice instance, y=0 ma horni okraj platna
     * @param x2      x-ova souradnice konce
     * @param y2      y-ova souradnice konce
     * @param barva   Barva kresleneho obdelniku
     */
    public void kresliCaru( int x1, int y1, int x2, int y2, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.drawLine( x1, y1, x2, y2 );
    }


    /***************************************************************************
     * Vypise zadanou barvou zadany text na zadanych souradnicich.
     *
     * @param text    Vypisovany text
     * @param x       x-ova souradnice instance, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y=0 ma horni okraj platna
     * @param barva   Barva kresleneho obdelniku
     */
    public void kresliText( String text, int x, int y, Barva barva )
    {
        g.setColor( barva.getColor() );
        g.drawString( text, x, y );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

