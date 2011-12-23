package rup.cesky.tvary_5;

/*******************************************************************************
 * Trida pro praci s kruhem na virtualnim platne.
 * Kruh je definovan jako potomek elipsy,
 * pricemz pri nastavovani rozmeru nastavi mensi ze zadanych velikosti.
 * 
 * @author Rudolf Pecinovsky
 * @version 0.00.000,  0.0.2003
 */
public class Kruh extends Elipsa
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
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna 
     * a bude mit implicitni barvu, 
     * vysku a sirku 1 pole.
     */
    public Kruh()
    {
        super( 0, 0, SP.getKrok(), SP.getKrok() );
    }
    
    
    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     */
    public Kruh(int x, int y, int prumer)
    {
        super( x, y, prumer, prumer );
    }
    

    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param pocatek Pozice pocatku instance
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     */
    public Kruh( Pozice pocatek, int prumer )
    {
        super( pocatek.x, pocatek.y, prumer, prumer  );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param prumer    Prumer instance
     * @param barva     Barva instance
     */
    public Kruh(Pozice pocatek, int prumer, Barva barva)
    {
        this( pocatek.x, pocatek.y, prumer, barva );
    }


    /***************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast 
     * a majici implicitni barvu.
     *
     * @param oblast   Oblast definujici pozici a rozmer instance
     */
    public Kruh(Oblast oblast)
    {
        this( oblast.x, oblast.y, Math.min(oblast.sirka, oblast.vyska) );
    }


    /***************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast 
     * a majici zadanou barvu.
     *
     * @param oblast   Oblast definujici pozici a rozmer instance
     * @param barva    Barva instance
     */
    public Kruh(Oblast oblast, Barva barva)
    {
        this( oblast.x, oblast.y, Math.min(oblast.sirka, oblast.vyska), barva );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     * @param barva   Barva vytvarene instance
     */
    public Kruh(int x, int y, int prumer, Barva barva)
    {
        super( x, y, prumer, prumer, barva );
    }
    
    
    
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    
    /***************************************************************************
     * Nastavi prumer kruhu jako mensi ze zadanych delek.
     * Prekryva prislusnou metodu rodickovske tridy tak,
     * aby upraveny obrazec "nevycnival" ze zadane oblasti.
     * 
     * @param sirka kandidat na prumer kruhu - sirka opsane elipsy
     * @param vyska kandidat na prumer kruhu - vyska opsane elipsy
     */    
    @Override
    public void setRozmer(int sirka, int vyska)
    {
        int prumer = Math.min( sirka, vyska );
        super.setRozmer( prumer, prumer );
    }
    
    
    /***************************************************************************
     * Vrati prumer kruhu.
     * 
     * @return Prumer kruhu.
     */    
    public int getPrumer()
    {
        return super.getSirka();
    }
    

    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== OSTATNI METODY INSTANCI ===================================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

