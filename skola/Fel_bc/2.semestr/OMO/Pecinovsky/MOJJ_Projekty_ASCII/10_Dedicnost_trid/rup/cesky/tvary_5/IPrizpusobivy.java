package rup.cesky.tvary_5;

/*******************************************************************************
 * Rozhrani IPrizpusobivy je urceno pro instance, ktere chteji byt schopny
 * reagovat na velikosti kroku a tim i policka aktivniho platna.
 * Kdykoliv se zmeni velikost pole aktivniho platna, platno to oznami
 * vsem prihlasenym prizpuosbivym posluchacum.
 *
 * @author  Rudolf Pecinovsky
 * @version 0.00.000,    2. rijen 2004, 19:57
 */
public interface IPrizpusobivy
{
//== VEREJNE KONSTANTY =========================================================
//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== OSTATNI METODY INSTANCI ===================================================

    /***************************************************************************
     * Prihlasi-li se instance u aktivniho platna jako prizpusobivy posluchac,
     * zavola aktivni platno tuto jeji metodu po kazde zmene kroku
     * a tim i velikosti jeho pole.
     *
     * @param stary     Puvodni velikost kroku.
     * @param novy      Nove nastavena velikost kroku.
     */
    public void krokZmenen( int stary, int novy );


//== VNORENE TRIDY =============================================================
}

