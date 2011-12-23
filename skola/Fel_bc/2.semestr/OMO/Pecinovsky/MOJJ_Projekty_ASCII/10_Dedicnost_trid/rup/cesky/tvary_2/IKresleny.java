package rup.cesky.tvary_2;

/*******************************************************************************
 * Rozhrani IKresleny musi implementovat vsechny tridy, ktere chteji,
 * aby jejich instance byly zobrazeny na animacnim platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.01, 4.1.2003
 */
public interface IKresleny
{
//== VEREJNE KONSTANTY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI METODY K IMPLEMENTACI =============================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *  
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.     
     */
    public void nakresli( Kreslitko kreslitko );


//== VNORENE TRIDY =============================================================
}

