package doprava;

import rup.cesky.utility.IO;
import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Ctverec;


/*******************************************************************************
 * Instance tridy ObousmernaKabina predstavuji kabinky pohybujici se po linkach.
 *
 * Zmeny oproti tride ZpetnaKabina_9a:
 * - Trida je nyni potomkem tridy Kabina_10b
 *
 * @author  Rudolf Pecinovsky
 * @version 1.00,  29.02.2004
 */
public class ZpetnaKabina_10b extends Kabina_10b
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    Ctverec ctverec;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     * 
     * @param linka    Linka, po ktere se kabina pohybuje.
     */
    public ZpetnaKabina_10b(Linka linka)
    {
        super( linka );
        ctverec = new Ctverec( kabina.getPozice(), PRUMER, Barva.FIALOVA );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    @Override
    public void setPozice( int x, int y )
    {
        SP.nekresli(); {
            super  .setPozice( x,         y         );
            ctverec.setPozice( x-POLOMER, y-POLOMER );
        } SP.vratKresli();
    }
    
    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *  
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.     
     */
    @Override
    public void nakresli( Kreslitko kreslitko )
    {
        ctverec.nakresli( kreslitko );
        super  .nakresli( kreslitko );
    }

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Metoda vyzadovana rozhranim IMultiposuvny: definuje akci, 
     * ktera se ma provest v okamziku, 
     * kdy je objekt doveden do pozadovane cilove pozicie.
     */
    @Override
    public void presunuto()
    {
        //Zastavka, kam jsme dorazili, musi znat sveho predchudce
        dalsi = dalsi.getPredchozi();
        
        IO.cekej( linka.getCekani() );
        
        //Nechame kabinu presunout na dalsi zastavku
        mp.presun( this,  dalsi.getPozice(),  linka.getRychlost() );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

