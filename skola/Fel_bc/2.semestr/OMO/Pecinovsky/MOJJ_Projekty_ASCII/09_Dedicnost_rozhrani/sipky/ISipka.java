package sipky;

import rup.cesky.tvary.IKresleny;


/*******************************************************************************
 * Rozhrani ISipka slouzi k ...
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public interface ISipka extends IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI METODY URCENE K IMPLEMENTACI ======================================
    
    /***************************************************************************
     * Metoda vpred presune sipku na dalsi pole ve smeru, 
     * do ktereho je natocena.
     */
    void vpred();    
    
    
    /***************************************************************************
     * Vrati sipku na stejnem poli, ale otocenou o 90vlevo.
     * 
     * @return Pozadovana otocena sipka
     */
    ISipka doleva();    
    
    
    /***************************************************************************
     * Vrati vodorovnou polickovou souradnici sipky.
     * 
     * @return Pozadovana souradnice
     */
    int getXPole();  
    
    
    /***************************************************************************
     * Vrati svislou polickovou souradnici sipky.
     * 
     * @return Pozadovana souradnice
     */
    int getYPole();  
    
}

