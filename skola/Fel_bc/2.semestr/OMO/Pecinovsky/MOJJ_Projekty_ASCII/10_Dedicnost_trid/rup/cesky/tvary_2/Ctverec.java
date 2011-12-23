package rup.cesky.tvary_2;

/*******************************************************************************
 * Trida pro praci s ctvercem na virtualnim platne.
 * Ctverec je definovan jako potomek obdelniku, 
 * pricemz pri nastavovani rozmeru nastavi mensi ze zadanych velikosti.
 *
 * @author Rudolf Pecinovsky
 * @version 0.00.000,  0.0.2003
 */
public class Ctverec extends Obdelnik
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
    public Ctverec()
    {
        super( 0, 0, SP.getKrok(), SP.getKrok() );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param strana  Delka strany vytvarene instance,  strana > 0
     */
    public Ctverec( int x, int y, int strana )
    {
        super( x, y, strana, strana );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param rozmer    Rozmer instance
     */
    public Ctverec(Pozice pocatek, Rozmer rozmer)
    {
        this( pocatek.x, pocatek.y, Math.min(rozmer.sirka, rozmer.vyska) );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param rozmer    Rozmer instance
     * @param barva     Barva instance
     */
    public Ctverec(Pozice pocatek, Rozmer rozmer, Barva barva)
    {
        this( pocatek.x, pocatek.y, Math.min(rozmer.sirka, rozmer.vyska), barva );
    }


    /***************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast 
     * a majici implicitni barvu.
     *
     * @param oblast   Oblast definujici pozici a rozmer instance
     */
    public Ctverec(Oblast oblast)
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
    public Ctverec(Oblast oblast, Barva barva)
    {
        this( oblast.x, oblast.y, Math.min(oblast.sirka, oblast.vyska), barva );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param strana  Delka strany vytvarene instance,  strana > 0
     * @param barva   Barva vytvareneho ctverce
     */
    public Ctverec( int x, int y, int strana, Barva barva )
    {
        super( x, y, strana, strana, barva );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi delku strany ctverce jako mensi ze zadanych delek.
     * Prekryva prislusnou metodu rodickovske tridy tak,
     * aby upraveny obrazec "nevycnival" ze zadane oblasti.
     *
     * @param sirka kandidat na delku strany ctverce - sirka opsaneho obdelnika
     * @param vyska kandidat na delku strany ctverce - vyska opsaneho obdelnika
     */
    @Override
    public void setRozmer(int sirka, int vyska)
    {
        int strana = Math.min( sirka, vyska );
        super.setRozmer( strana, strana );
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

