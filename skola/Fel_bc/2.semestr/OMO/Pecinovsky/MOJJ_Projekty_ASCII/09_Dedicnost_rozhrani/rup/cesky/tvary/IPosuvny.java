package rup.cesky.tvary;

/*******************************************************************************
 * Rozhrani {@code IPosuvny} definuje povinou sadu metod, jez musi byt
 * poskytovany objekty, ktere maji byt schopny posunu po animovanem platne.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.01, 28.1.2003
 */
public interface IPosuvny 
{
//== VEREJNE KONSTANTY =========================================================
//== DEKLAROVANE METODY ========================================================

    /***************************************************************************
     * Vrati aktualni pozici pposuvneho objektu (vetsinou pozici leveho horniho
     * rohu opsaneho obdelniku) jako instanci tridy {@code Pozice}.
     *
     * @return Akutalni pozice objektu.
     */
     public Pozice getPozice();


    /***************************************************************************
     * Presune posuvny objekt do nove pozice.
     *
     * @param pozice  Nova pozice objektu.
     */
    public void setPozice( Pozice pozice );


    /***************************************************************************
     * Nastavi novou pozici objektu.
     *
     * @param x   Nova x-ova pozice objektu
     * @param y   Nova y-ova pozice objektu
     */
    public void setPozice(int x, int y);


//== ZDEDENE METODY ============================================================
//== VNORENE TRIDY =============================================================
}

