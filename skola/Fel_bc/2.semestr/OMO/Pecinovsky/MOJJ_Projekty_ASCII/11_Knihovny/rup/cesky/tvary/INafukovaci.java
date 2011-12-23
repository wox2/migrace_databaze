package rup.cesky.tvary;

/*******************************************************************************
 * Rozhrani INafukovaci definuje povinou sadu metod, jez musi byt poskytovany 
 * objekty, ktere ma byt instance tridy Kompresor schopna "nafouknout".
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, 3.2.2003
 */
public interface INafukovaci extends IKresleny
{
//== VEREJNE KONSTANTY =========================================================
//== DEKLAROVANE METODY ========================================================

    /***************************************************************************
     * Vrati aktualni rozmer nafukovaciho objektu (vetsinou rozmer opsaneho
     * obdelniku) jako instanci tridy {@code Rozmer}.
     *
     * @return  Rozmer objektu.
     */
    public Rozmer getRozmer();


    /***************************************************************************
     * Nastavi nove rozmery nafukovaciho objektu zadane jako jako instance
     * tridy {@code Rozmer}; pozice objektu by se pritom nemela zmenit.
     *
     * @param rozmer  Nove rozmery objektu
     */
    public void setRozmer( Rozmer rozmer );


    /***************************************************************************
     * Nastavi novou velikost nafukovaciho objektu;
     * pozice objektu by se pritom nemela zmenit.
     *
     * @param vyska  Nova sirka objektu
     * @param sirka  Nova vyska objektu
     */
    public void setRozmer(int sirka, int vyska);


//== ZDEDENE METODY ============================================================
//== VNORENE TRIDY =============================================================
    
    
    /***************************************************************************
     * Trida definuje implicitni verze vsech metod pozadovanych 
     * implementovanym rozhranim.
     */
    public static class Adapter implements INafukovaci
    {
        /** {@inheritDoc} */
        public Rozmer getRozmer() {
            throw new UnsupportedOperationException();
        }

        /** {@inheritDoc} */
        public void setRozmer( Rozmer rozmer ) {
            throw new UnsupportedOperationException();
        }

        /** {@inheritDoc} */
        public void setRozmer(int sirka, int vyska) {
            throw new UnsupportedOperationException();
        }

        /** {@inheritDoc} */
        public void nakresli( Kreslitko kreslitko ) {
            throw new UnsupportedOperationException();
        }
    }
}

