package doprava;

/*******************************************************************************
 * Instance tridy {@code ObousmernaKabina} predstavuji kabinky pohybujici se 
 * po linkach smerem zadanym pri jejich vytvoreni.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class ObousmernaKabina extends Kabina_13a
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    private boolean dopredu;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     *
     * @param linka  Linka, po ktere se kabina pohybuje.
     * @param smer   Pohybuje-li se k nasledujici nebo predchozi stanici.
     */
    protected ObousmernaKabina(Linka linka, Smer smer )
    {
        super( linka );
        /*# Telo konstruktoru je treba dotvorit. */
    }


    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     * 
     * @param linka  Linka, po ktere se kabina pohybuje.
     * @param smer   Pohybuje-li se k nasledujici nebo predchozi stanici.
     * @return Pozadovana kabina
     */
    public static ObousmernaKabina getInstance( Linka linka, Smer smer )
    {
        ObousmernaKabina kabina = new ObousmernaKabina( linka, smer );
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
        /*# Doplnit telo. */
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

