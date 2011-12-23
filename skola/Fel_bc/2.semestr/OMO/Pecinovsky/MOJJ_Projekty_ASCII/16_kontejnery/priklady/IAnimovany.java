package priklady;

import rup.cesky.tvary.IKresleny;

/*******************************************************************************
 * Rozhrani Sbl_Rozhrani ...
 *
 * @author  Rudolf Pecinovsky
 * @version 0.00.000, 0.0.2003
 */
public interface IAnimovany extends IKresleny
{
//== VEREJNE KONSTANTY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI METODY INSTANCI ===================================================

    /***************************************************************************
     * Premisti ci jinak animuje dany objekt a vrati animatoru informaci o tom,
     * zda jiz animace skoncila.
     *
     * @return {@code true} pokud touto akci animace skoncila a
     *         animator ma danya objekt vyradit ze seznamu animovanyczh objektu
     */
    public boolean popojed();
    
             
//== VNORENE TRIDY =============================================================
}

