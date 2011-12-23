package priklady;

/*******************************************************************************
 * Trida Equals slouzi k ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public final class Equals
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY ATRIBUTU TRIDY ==========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Ukazuje nevhodnost porovnani dvou retezcu pomoci operatoru {@code ==}.
     */
    public static void string()
    {
        String dobryDen = "Dobry den";
        String dobry    = "Dobry";
        String den      = "den";
        String soucet   = dobry + " " + den;
        String dobry2   = "Dobry";
        
        System.out.println( "(dobryDen == soucet)    : " + 
                             (dobryDen == soucet) );
        System.out.println( "dobryDen.eguals(soucet) : " + 
                             dobryDen.equals(soucet) );
        System.out.println( "(dobry == dobry2) : " + (dobry == dobry2) );
    }
    
    
    
//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Soukromy bezparametricky konstruktor ...
     */
    private Equals() {}


//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main(String[] args) {
        string();
    }
//--
}

