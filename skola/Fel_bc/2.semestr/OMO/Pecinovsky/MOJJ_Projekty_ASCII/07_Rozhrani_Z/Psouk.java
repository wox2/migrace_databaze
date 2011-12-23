/*******************************************************************************
 * Trida Psouk slouzi k ...
 *
 * @author    jmeno autora
 * @version   0.00.000,  0.0.2003
 */
public class Psouk
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /** Doba mezi jednotlivymi pri/u-fouknutimi upravovaneho objektu. */
    private static final int ODDECH = 300;
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Sila instance predstavuje pocet bodu, o kolik zmeni zvetsovana, resp.
     *  zmensovana instance svuj rozmer pri jednom "dechu". */
    private final int sila;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY ATRIBUTU TRIDY ==========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci se silou rovnou jedne.
     */
    public Psouk()
    {
        this( 1 );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou silou.
     * 
     * @param sila Pocet bodu, o nez se zvetsi rozmer objektu
     */
    public Psouk( int sila )
    {
        this.sila = sila;
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Trikrat zvetsi zadanou instanci o velikost sve sily.
     * @param objekt Nafukovany objekt
     */
    public void prifoukni( INafukovaci objekt )
    {
        Rozmer r = objekt.getRozmer();
        int sirka = r.sirka;
        int vyska = r.vyska;

        objekt.setRozmer( sirka+=sila, vyska+=sila );
        IO.cekej( ODDECH );
        objekt.setRozmer( sirka+=sila, vyska+=sila );
        IO.cekej( ODDECH );
        objekt.setRozmer( sirka+sila,  vyska+sila  );
    }
    

    /***************************************************************************
     * Trikrat zmensi zadanou instanci o velikost sve sily.
     * @param objekt Nafukovany objekt
     */
    public void ufoukni( INafukovaci objekt )
    {
        Rozmer r = objekt.getRozmer();
        int sirka = r.sirka;
        int vyska = r.vyska;

        objekt.setRozmer( sirka-=sila, vyska-=sila );
        IO.cekej( ODDECH );
        objekt.setRozmer( sirka-=sila, vyska-=sila );
        IO.cekej( ODDECH );
        objekt.setRozmer( sirka-sila,  vyska-sila  );
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
    
}

