/*******************************************************************************
 * Trida {@code Oblast} slouzi jako prepravka uchovavajici informace
 * o pozici a rozmeru dane obdelnikove oblasti.
 * Proto jsou jeji atributy deklarovany jako verejne konstanty.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Oblast
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Vodorovna souradnice dane oblasti, tj. jejiho leveho horniho rohu. */
    public final int x;

    /** Svisla souradnice dane oblasti, tj. jejiho leveho horniho rohu. */
    public final int y;

    /** Sirka oblasti. */
    public final int sirka;

    /** Vyska oblasti. */
    public final int vyska;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori oblast se zadanym umistenim a rozmery.
     *
     * @param x      Vodorovna souradnice oblasti, tj. jejiho leveho horniho rohu.
     * @param y      Svisla souradnice oblasti, tj. jejiho leveho horniho rohu.
     * @param sirka  Sirka oblasti.
     * @param vyska  Vyska oblasti.
     */
    public Oblast( int x, int y, int sirka, int vyska )
    {
        this.x     = x;
        this.y     = y;
        this.sirka = sirka;
        this.vyska = vyska;
    }


    /***************************************************************************
     * Vytvori oblast se zadanym umistenim a rozmery.
     *
     * @param pozice  Pozice oblasti, tj pozice jejiho leveho horniho rohu.
     * @param rozmer  Rozmer vytvarene oblasti.
     */
    public Oblast( Pozice pozice, Rozmer rozmer )
    {
        this( pozice.x, pozice.y, rozmer.sirka, rozmer.vyska );
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati pozici dane oblasti.
     *
     * @return Pozice dane oblasti
     */
    public Pozice getPozice()
    {
        return new Pozice( x, y );
    }


    /***************************************************************************
     * Vrati rozmer dane oblasti.
     *
     * @return Rozmer dane oblasti
     */
    public Rozmer getRozmer()
    {
        return new Rozmer ( sirka, vyska );
    }


    /***************************************************************************
     * Vrati velikost sirky oblasti.
     *
     * @return  Sirka oblasti.
     */
    public int getSirka()
    {
        return sirka;
    }


    /***************************************************************************
     * Vrati velikost vysky oblasti.
     *
     * @return  Vyska oblasti.
     */
    public int getVyska()
    {
        return vyska;
    }


    /***************************************************************************
     * Vrati hodnotu vodorovne souradnice dane oblasti.
     *
     * @return  Pozadovana souradnice.
     */
    public int getX()
    {
        return x;
    }


    /***************************************************************************
     * Vrati hodnotu svisle souradnice dane oblasti.
     *
     * @return  Pozadovana souradnice.
     */
    public int getY()
    {
        return y;
    }


//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *
     * @return Pozadovana textova reprezentace.
     */
    @Override
    public String toString()
    {
        return "Oblast[x=" + x + ",y=" + y + ",sirka=" +
                sirka + ",vyska=" + vyska + "]";
    }


    /***************************************************************************
     * Vrati informaci o tom, reprezentuje-li zadana instance stejnou oblast 
     * jako objekt zadany jako parametr.
     *
     * @param  objekt Objekt, s nimz je dana instance porovnavana
     * @return {@code true} reprezentuje-li objekt stejnou oblast, 
     *         jinak {@code false}
     */
    @Override
    public boolean equals(Object objekt) {
        return (objekt instanceof Oblast)         &&
               (((Oblast)objekt).x     == x    )  &&  
               (((Oblast)objekt).y     == y    )  &&
               (((Oblast)objekt).sirka == sirka)  &&  
               (((Oblast)objekt).vyska == vyska);
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

