/*******************************************************************************
 * Trida {@code Zlomek} definuje zlomky a potrebne operace, aby se zlomky bylo
 * mozno pocitat obdobne jako s cisly. Definuje proto operace pro scitani,
 * odcitani, nasobeni a deleni dvou zlomku a zlomku a cisla, 
 * jakoz i operace pro prevod celeho cisla na zlomek a zlomku na cislo.
 *
 * @author     Rudolf Pecinovsky
 * @version    0.00.000,  0.0.2003
 */
public class Zlomek
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci tridy Zlomek s hodnotou danou citatelem a
     * jmenovatelem dodanymi jako parametry. Hodnota citatele a jmenovatele 
     * ulozena v atributech vsak bude jiz zkracena a jmenovatel bude kladny.
     * 
     * @param c  Zadavany citatel
     * @param j  Zadavany jmenovatel
     */
    public Zlomek(int c, int j)
    {
    }
    
    
    /***************************************************************************
     * Kopirovaci konstruktor -- vytvori novou instanci 
     * se stejnymi hodnotami atributu, jake ma zlomek zadany jako parametr.
     *
     * @param z  Kopirovany zlomek
     */
    public Zlomek(Zlomek z)
    {
    }
    
    
    /***************************************************************************
     * Vytvori zlomek, ktery bude mit hodnotu cisla zadaneho jako parametr.
     *
     * @param cislo  Hodnota vytvareneho zlomku
     */
    public Zlomek(int cislo)
    {
    }
    

    
//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati hodnotu citatele.
     * 
     * @return hodnota citatele              
     */     
    public int getCitatel()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati hodnotu jmenovatele.
     * 
     * @return hodnota jmenovatele              
     */     
    public int getJmenovatel()
    {
        return 0;
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================
    
    /***************************************************************************
     * Vrati retezec reprezentujici zlomek ve tvaru
     * [citatel/jmenovatel]  -  napr. [7/2]
     *
     * @return Textova reprezentace zlomku.
     */
    @Override
    public String toString()
    {
        return "";
    }
    
    
    
//== NOVE ZAVEDENE METODY INSTANCI =============================================
    
    /***************************************************************************
     * Vrati hodnotu zlomku oriznutou na cele cislo.
     *
     * @return  Hodnota zlomku oriznuta na cele cislo.
     */
    public int intValue()
    {
        return 0;
    }
    
    
    /***************************************************************************
     * Vrati hodnotu zlomku prevedenou na realne cislo typu double.
     *
     * @return  Hodnota zlomku prevedena na realne cislo typu double.
     */
    public double doubleValue()
    {
        return 0;
    }

    
    /***************************************************************************
     * Pripocte ke zlomku zlomek zadany jako parametr 
     * a vrati zlomek, ktery je jejich souctem. 
     *
     * @param  z  Pricitany zlomek.
     *
     * @return Zlomek, ktrery je souctem obou zlomku.
     */
    public Zlomek plus(Zlomek z)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Pripocte ke zlomku cele cislo zadane jako parametr 
     * a vrati zlomek, ktery je jejich souctem. 
     *
     * @param  cislo   Pricitane cislo.
     *
     * @return Zlomek, ktrery je souctem obou hodnot.
     */
    public Zlomek plus(int cislo)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Odecte od zlomku zlomek zadany jako parametr 
     * a vrati zlomek, ktery je jejich rozdilem. 
     *
     * @param  z  Odecitany zlomek.
     *
     * @return Zlomek, ktrery je rozdilem obou zlomku.
     */
    public Zlomek minus(Zlomek z)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Odecte od zlomku cele cislo zadane jako parametr 
     * a vrati zlomek, ktery je jejich rozdilem. 
     *
     * @param  cislo   Odecitane cislo.
     *
     * @return Zlomek, ktrery je rozdilem obou hodnot.
     */
    public Zlomek minus(int cislo)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Odecte zlomek od celeho cisla zadaneho jako parametr 
     * a vrati zlomek, ktery je jejich rozdilem. 
     *
     * @param  cislo   Cislo, od ktereho se zlomek odecte.
     *
     * @return Zlomek, ktrery je rozdilem obou hodnot.
     */
    public Zlomek odectiOd(int cislo)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Vynasobi zlomek zlomkem zadanym jako parametr 
     * a vrati zlomek, ktery je jejich soucinem. 
     *
     * @param  z  Zlomek, kterym se nasobi.
     *
     * @return Zlomek, ktrery je soucinem obou zlomku.
     */
    public Zlomek krat(Zlomek z)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Vynasobi zlomek celym cislem zadanym jako parametr 
     * a vrati zlomek, ktery je jejich soucinem. 
     *
     * @param  cislo  Cislo, kterym se nasobi.
     *
     * @return Zlomek, ktrery je soucinem obou hodnot.
     */
    public Zlomek krat(int cislo)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Vydeli zlomek zlomkem zadanym jako parametr 
     * a vrati zlomek, ktery je jejich podilem. 
     *
     * @param  z  Zlomek, kterym se deli.
     *
     * @return Zlomek, ktrery je podilem obou zlomku.
     */
    public Zlomek deleno(Zlomek z)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Vydeli zlomek celym cislem zadanym jako parametr 
     * a vrati zlomek, ktery je jejich podilem. 
     *
     * @param  cislo  Cislo, kterym se deli.
     *
     * @return Zlomek, ktrery je podilem obou hodnot.
     */
    public Zlomek deleno(int cislo)
    {
        return null;
    }
    
    
    /***************************************************************************
     * Vydeli zlomkem cele cislo zadane jako parametr 
     * a vrati zlomek, ktery je jejich podilem. 
     *
     * @param  cislo  Cislo, ktere se deli.
     *
     * @return Zlomek, ktrery je podilem obou hodnot.
     */
    public Zlomek delCislo(int cislo)
    {
        return null;
    }

    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

