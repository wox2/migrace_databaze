/*******************************************************************************
 * Trida Vytah simuluje vytah vozici pasazery
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Vytah 
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int pocet = 0;

    

//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor definujici ve kterem sloupci bude vytah jezdit, 
     * jakou bude jezdit rychlosti a jakou bude mit barvu.
     * 
     * @param sloupec  Sloupec aktivniho platna, v nemz bude vytah jezdit.
     * @param rychlost Rychlost nastavena presouvaci, ktery bude mit dany vytah
     *                 na starosti.
     * @param barva    Barva vytahu.          
     */
    public Vytah( int sloupec, int rychlost, Barva barva )
    {
    }


    /***************************************************************************
     * Konstruktor definujici ve kterem sloupci bude vytah jezdit, 
     * jakou bude jezdit rychlosti a jakou bude mit barvu.
     * 
     * @param sloupec  Sloupec aktivniho platna, v nemz bude vytah jezdit.
     */
    public Vytah( int sloupec )
    {
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec, v nemz bude uvedeno poradove cislo
     * vytahu, sloupec, v nemz jezdi a patro, v nemz se prave nachazi.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return "Vytah_" + poradi;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
     
    /***************************************************************************
     * Prijede do patra, ve kterem se nachazi pasazer.
     * odveze jej do pozadovaneho patra; pasazer zustava ve vytahu.
     * 
     * @param pasazer   Pasazer, do jehoz radku je trebaq prijet.
     */
    public void prijedK( IPosuvny pasazer )
    {
    }
    
   
    /***************************************************************************
     * Presune zadaneho pasazera z jeho vychozi pozice do vytahu.
     * V zajmu realnosti graficke simulace by se mel pasazer vejit do 
     * jednoho policka aktivniho platna.          
     *
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     */
    public void nastup( IPosuvny pasazer )
    {
    }
    
   
    /***************************************************************************
     * Odjede s vytahem do zadaneho patra;
     * je-li nastoupeny pasazer, odjede i s nim.
     *
     * @param patro   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void doPatra( int patro )
    {
    }
    
    
    /***************************************************************************
     * Presune pasazera vytahu na sousedni policko vpravo vedle vytahu.
     */
    public void vystupVpravo()
    {
    }
    
    
    /***************************************************************************
     * Presune pasazera vytahu na sousedni policko vlevo vedle vytahu.
     */
    public void vystupVlevo()
    {
    }
        
     
    /***************************************************************************
     * Privola do pasazerova patra vytah, necha pasazera nastoupit,
     * odveze jej do pozadovaneho patra a tam jej necha vystoupit doprava.
     * 
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     * @param doPatra   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void odvezVpravo( IPosuvny pasazer, int doPatra )
    {
    }   
    
     
    /***************************************************************************
     * Privola do pasazerova patra vytah, necha pasazera nastoupit,
     * odveze jej do pozadovaneho patra a tam jej necha vystoupit doleva.
     * 
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     * @param doPatra   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void odvezVlevo( IPosuvny pasazer, int doPatra )
    {
    }   
    

    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

