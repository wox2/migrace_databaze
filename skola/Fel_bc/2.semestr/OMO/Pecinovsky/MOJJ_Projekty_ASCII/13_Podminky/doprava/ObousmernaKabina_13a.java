package doprava;

import rup.cesky.utility.IO;


/*******************************************************************************
 * Instance tridy {@code ObousmernaKabina} predstavuji kabinky pohybujici se 
 * po linkach smerem zadanym pri jejich vytvoreni.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class ObousmernaKabina_13a extends Kabina_13a
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    private Smer smer;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     *
     * @param linka  Linka, po ktere se kabina pohybuje.
     * @param smer   Smer pohybu kabiny - vpred ci zpet.
     */
    public ObousmernaKabina_13a( Linka linka, Smer smer )
    {
        //Vytvori novou kabinu a rozjede ji k nisledujici zastavce
        super( linka );
        this.smer = smer;
        SP.pridej( this );
    }


    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince, a vrati odkaz na ni.
     * 
     * @param linka Linka, po ktere se kabina pohybuje.
     * @param smer  Smer pohybu kabiny - vpred ci zpet.
     * @return Pozadovana kabina
     */
    public static ObousmernaKabina_13a getInstance( Linka linka, Smer smer )
    {
        ObousmernaKabina_13a kabina = new ObousmernaKabina_13a( linka, smer );
        kabina.presunuto();
        return kabina;
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Metoda vyzadovana rozhranim IMultiposuvny: definuje akci,
     * ktera se ma provest v okamziku,
     * kdy je objekt doveden do pozadovane cilove pozicie.
     */
    @Override
    public void presunuto()
    {
        if( smer == Smer.VPRED ) {
            dalsi = dalsi.getNasledujici();
        }
        else {
            dalsi = dalsi.getPredchozi();
        }

        IO.cekej( linka.getCekani() );

        //Nechame kabinu presunout na dalsi zastavku
        mp.presun( this,  dalsi.getPozice(),  linka.getRychlost() );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

