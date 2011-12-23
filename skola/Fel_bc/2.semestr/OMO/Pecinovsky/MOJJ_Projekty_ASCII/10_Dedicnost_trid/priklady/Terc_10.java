package priklady;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;
import rup.cesky.tvary.Pozice;

import rup.cesky.tvary.Cara;
import rup.cesky.tvary.Kruh;


/*******************************************************************************
 * Trida pro praci s kruhem na virtualnim platne.
 * Kruh je definovan jako potomek elipsy,
 * pricemz pri nastavovani rozmeru nastavi mensi ze zadanych velikosti.
 *
 * @author Rudolf Pecinovsky
 * @version 0.00.000,  0.0.2003
 */
public class Terc_10 extends Kruh
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final Barva B1 = Barva.ZLUTA;
    private static final Barva B2 = Barva.MODRA;
    private static final Barva B3 = Barva.CERVENA;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final Kruh mezi;
    private final Kruh stred;
    private final Cara vodor;
    private final Cara svisla;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna
     * a bude mit vysku a sirku 2 pole.
     */
    public Terc_10()
    {
        this( SP.getKrok()/2, SP.getKrok()/2, SP.getKrok() );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     */
    public Terc_10(int x, int y, int prumer)
    {
        this( x, y, prumer, B1, B2, B3 );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery
     * a implicitni barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     */
    public Terc_10(Pozice pocatek, int prumer)
    {
        this( pocatek.x, pocatek.y, prumer );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a barvou.
     *
     * @param pocatek   Pozice pocatku instance
     * @param prumer    Prumer vytvarene instance,  prumer > 0
     * @param barva1    Barva vnejsiho kruhu
     * @param barva2    Barva stredniho kruhu, mezikruhu
     * @param barva3    Barva centralniho kruhu
     */
    public Terc_10(Pozice pocatek, int prumer, 
                   Barva barva1, Barva barva2, Barva barva3)
    {
        this( pocatek.x, pocatek.y, prumer, barva1, barva2, barva3 );
    }


    /***************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast
     * a majici implicitni barvu.
     *
     * @param oblast   Oblast definujici pozici a rozmer instance
     */
    public Terc_10(Oblast oblast)
    {
        this( oblast.x, oblast.y, Math.min(oblast.sirka, oblast.vyska) );
    }


    /***************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast
     * a majici zadanou barvu.
     *
     * @param oblast   Oblast zabrana vytvarenym tercem
     * @param barva1   Barva vnejsiho kruhu
     * @param barva2   Barva stredniho kruhu, mezikruhu
     * @param barva3   Barva centralniho kruhu
     */
    public Terc_10(Oblast oblast, Barva barva1, Barva barva2, Barva barva3)
    {
        this( oblast.x, oblast.y, Math.min(oblast.sirka, oblast.vyska),
              barva1, barva2, barva3 );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param prumer  Prumer vytvarene instance,  prumer > 0
     * @param barva1  Barva vnejsiho kruhu
     * @param barva2  Barva stredniho kruhu, mezikruhu
     * @param barva3  Barva centralniho kruhu
     */
    public Terc_10(int x, int y, int prumer, Barva barva1, Barva barva2, Barva barva3)
    {
        super( x-prumer/2, y-prumer/2, prumer, barva1 );
        mezi  = new Kruh( 0, 0, 0, barva2 );
        stred = new Kruh( 0, 0, 0, barva3 );
        vodor = new Cara( 0, 0, 0, 0 );
        svisla= new Cara( 0, 0, 0, 0 );

        setRozmer( prumer );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    @Override
    public int getX()
    {
        return super.getX() + getSirka()/2;
    }


    @Override
    public int getY()
    {
        return super.getY() + getVyska()/2;
    }


    @Override
    public void setPozice( int x, int y )
    {
        int r = getSirka() / 2;
        int s = r / 3;
        SP.nekresli(); {
            super .setPozice( x-r,   y-r   );
            mezi  .setPozice( x-2*s, y-2*s );
            stred .setPozice( x-s,   y-s   );
            vodor .setPozice( x-r,   y     );
            svisla.setPozice( x,     y-r   );
        } SP.vratKresli();
    }


    @Override
    public void setRozmer( int sirka, int vyska )
    {
        int prumer = Math.min( sirka, vyska );
        int p2 = prumer / 2;
        int p3 = prumer / 3;
        int x  = getY();
        int y  = getY();

        SP.nekresli(); {
            //Nejprve nastavime vsechny rozmery
            super.setRozmer( prumer, prumer );
            mezi .setRozmer(  2*p3  );
            stred.setRozmer(   p3   );
            //A pak vsechny hromadne presuneme
            setPozice( x, y );

            //Osovy kriz nestaci presunout, protoze se mohl zmenit jeho rozmer
            vodor .spoj( x-p2, y,    x+p2, y    );
            svisla.spoj( x,    y-p2, x,    y+p2 );
        } SP.vratKresli();
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    @Override
    public void nakresli( Kreslitko kr )
    {
        super .nakresli( kr );
        mezi  .nakresli( kr );
        stred .nakresli( kr );
        vodor .nakresli( kr );
        svisla.nakresli( kr );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

