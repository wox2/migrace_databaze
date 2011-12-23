package rup.cesky.tvary_4;

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
    
    private int hashCode = Integer.MIN_VALUE;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================
    
    /***************************************************************************
     * Zjisti oblast zaujimanou zadanym objektem.
     * 
     * @param ih Objekt, jehoz oblast zjistujeme
     * @return Oblast zaujimana zadanym objektem
     */
    public static Oblast get(IHybaci ih) 
    {
        return new Oblast( ih.getPozice(), ih.getRozmer() );
    }

    
    /***************************************************************************
     * Nastavi pro zadany hybaci objekt jeho oblast, tj. pozici a rozmer.
     * 
     * @param ih Objekt, jehoz oblast nastavujeme
     * @param o  Nastavovana oblast
     */
    public static void set(IHybaci ih, Oblast o) 
    {
        SpravcePlatna SP = SpravcePlatna.getInstance();
        SP.nekresli(); {
            ih.setPozice(o.x,     o.y);
            ih.setRozmer(o.sirka, o.vyska);
        } SP.vratKresli();
    }


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
        return new Pozice ( x, y );
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
        if( ! (objekt instanceof Oblast) ) {
            return false;               //==========>
        }
        Oblast oblast = (Oblast)objekt;
        return  (oblast.x     == x    )  &&  (oblast.y     == y    )  &&
                (oblast.sirka == sirka)  &&  (oblast.vyska == vyska);
    }

    
    /***************************************************************************
     * 
     * @return
     */
    @Override
    public int hashCode() {
        if( hashCode == Integer.MIN_VALUE )  {
            hashCode = 7;
            hashCode = 59 * hashCode + this.x;
            hashCode = 59 * hashCode + this.y;
            hashCode = 59 * hashCode + this.sirka;
            hashCode = 59 * hashCode + this.vyska;
        }
        return hashCode;
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

