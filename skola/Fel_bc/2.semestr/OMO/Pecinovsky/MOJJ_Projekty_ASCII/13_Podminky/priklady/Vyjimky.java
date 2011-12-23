package priklady;

/*******************************************************************************
 * Trida Vyjimky slouzi k ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class Vyjimky
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY ATRIBUTU TRIDY ==========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     */
    public static void nedosazitelna()
    {
        boolean problem = false;//true;
        if( problem )
        {
            System.err.println( "Pred vyjimkou" );
            throw new RuntimeException( "Mam problem" );
    //         System.err.println( "Za vyjimkou" );
        }
        System.out.println( "Jsem bez problemu" );
        
    }
    
    
public static void zasobnik( int hladina ) 
{
    System.out.println( "Zavolano s parametrem " + hladina );
    if( hladina <= 0 )
    {
        RuntimeException rte = new RuntimeException( "Predana zprava" );
        
        System.err.println( "Zasobnik - err:" );
        rte.printStackTrace();
        System.err.println( "=== Konec zasobniku ===" );
        
        System.out.println( "Zasobnik - out:" );
        rte.printStackTrace( System.out );

        System.err.println( "toString() = " + rte );
        System.err.println( "getMessage() = " + rte.getMessage() );

        throw rte;
    }
    zasobnik( hladina-1 );
}
    


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Bezparametricky konstruktor ...
     */
    private Vyjimky() {}


//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}



