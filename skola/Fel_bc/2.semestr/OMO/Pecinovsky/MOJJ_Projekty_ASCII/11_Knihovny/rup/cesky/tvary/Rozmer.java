package rup.cesky.tvary;

/*******************************************************************************
 * Trida Rozmer slouzi jako prepravka k uchovavani informaci o rozmeru objektu.
 * Proto jsou jeji atributy deklarovany jako verejne konstanty.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Rozmer
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Sirka objektu. */
    public final int sirka;

    /** Vyska objektu. */
    public final int vyska;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci se zadanymi rozmery.
     *
     * @param sirka  Sirka objektu.
     * @param vyska  Vyska objektu.
     */
    public Rozmer( int sirka, int vyska )
    {
        this.sirka = sirka;
        this.vyska = vyska;
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati velikost sirky objektu.
     *
     * @return  Sirka objektu.
     */
    public int getSirka()
    {
        return sirka;
    }


    /***************************************************************************
     * Vrati velikost vysky objektu.
     *
     * @return  Vyska objektu.
     */
    public int getVyska()
    {
        return vyska;
    }


//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vrati informaci o tom, reprezentuje-li zadana instance stejny rozmer 
     * jako objekt zadany jako parametr.
     *
     * @param  o Objekt, s nimz je dana instance porovnavana
     * @return {@code true} reprezentuje-li objekt stejny rozmer, 
     *         jinak {@code false}
     */
    @Override
    public boolean equals( Object o )
    {
        if( ! (o instanceof Rozmer) ) {
            return false;               //==========>
        }
        Rozmer p = (Rozmer)o;
        return (p.sirka == sirka) && (p.vyska == vyska);
    }


    /***************************************************************************
     * Vrati hodnotu hes-kodu dane instance.
     *
     * @return  Hodnota hes-kodu
     */
    @Override
    public int hashCode()
    {
        //Nema smvyskasl si jej pamatovat,
        //protoze nemame zarucenou nemennost instance
        return 17 + 37*(37*sirka + vyska);
    }


    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *
     * @return Pozadovana textova reprezentace.
     */
    @Override
    public String toString()
    {
        return "Rozmer[sirka=" + sirka + ", vyska=" + vyska + "]";
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

