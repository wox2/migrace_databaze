package doprava;

import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Pozice;


/*******************************************************************************
 * Instance tridy implementujici rozhrani IZastavka predstavuji zastavky 
 * na trati kabinove lanovky.
 * Kazda zastavka zna sveho predchudce a sveho nasledovnika
 * a je na nej schopna dodat na pozadani odkaz.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public interface IZastavka extends IKresleny
{
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati linku, na ktere zastavka lezi.
     * 
     * @return Linka zastavky.
     */
    public Linka getLinka();


    /***********************************************************************
     * Vrati pozici zastavky, tj. pozici jejiho stredu.
     * 
     * @return Pozice stredu zastavky.
     */
    public Pozice getPozice();


    /***************************************************************************
     * Vrati odkaz na predchozi zastavku na trati.
     * 
     * @return Odkaz na predchozi zastavku.
     */
    public IZastavka getPredchozi();


    /***************************************************************************
     * Vrati odkaz na nasledujici zastavku na trati.
     * 
     * @return Odkaz na nasledujici zastavku.
     */
    public IZastavka getNasledujici();


}

