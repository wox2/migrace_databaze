package sipky;

import rup.cesky.tvary.IKresleny;


/*******************************************************************************
 * Instance rozhrani {@code ISipka} prestavuji ...
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public interface ISipka extends IKresleny
{
//== VEREJNE KONSTANTY =========================================================
//== DEKLAROVANE METODY ========================================================

    /***************************************************************************
     * Otoci sipku o 90 vlevo.
     */
    public void vlevoVbok();


    /***************************************************************************
     * Posune sipku na dalsi policko ve smeru, do ktereho je natocena.
     */
    public void vpred();
            
            
//== ZDEDENE METODY ============================================================
//== VNORENE TRIDY =============================================================
}

