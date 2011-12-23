/*******************************************************************************
 * Trida {@code Strom_4c} obsahuje podobu tridy po zavedeni komentaru.
 *
 * Oproti verzi {@link Strom_4b} je zdrojovy kod pouze okomentovan
 * a jednotlive metody jsou serazeny do sekci definovanych radkovymi komentari.
 *
 * Obsahuje dokumentacni komentare vsech verejnych atributu a metod.
 *
 * @author     Rudolf PECINOVSKY
 * @version    4.00
 */
public class Strom_4c
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Udava, kolikrat je strom vyssi nez samotny kmen. */
    public static final int IMPLICITNI_POMER_VYSKY =  3;

    /** Udava, kolikrat je koruna sirsi nez kmen. */
    public static final int IMPLICITNI_POMER_SIRKY = 10;



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Velikost posunu pro bezparametricke posunove metody. */
    private static int krok = 50;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final Elipsa   koruna;   //Koruna stromu
    private final Obdelnik kmen;     //Kmen stromu



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================

    /***************************************************************************
     * Vrati velikost implicitniho kroku, o ktery se instance presune
     * pri volani bezparametrickych metod presunu.
     *
     * @return Velikost implicitniho kroku v bodech
     */
     public static int getKrok()
     {
         return krok;
     }


    /***************************************************************************
     * Nastavi velikost implicitniho kroku, o ktery se instance presune
     * pri volani bezparametrickych metod presunu.
     *
     * @param velikost  Velikost implicitniho kroku v bodech;<br/>
     *                  musi platit:  0 <= velikost <= 100
     */
    public static void setKrok( int velikost )
    {
        krok = velikost;
    }


    /***************************************************************************
     * Metoda se dotaze uzivatele na pozadovanou velikost kroku pouzivaneho
     * v bezparametrickych posunovych metodach a zadanou hodnotu nastavi.
     */
    public static void setKrok()
    {
        krok = IO.zadej( "Zadejte novou velikost kroku:", krok );
    }



//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Vytvori instanci zadane velikosti a upravi rozmer platna tak,
     * aby byla na platne prave zaramovana.
     *
     * @param sirka   Sirka stromu, ktery chceme vytvorit a zaramovat.
     * @param vyska   Vyska stromu, ktery chceme vytvorit a zaramovat.
     */
    public static void zaramuj( int sirka, int vyska )
    {
        Platno.getInstance().setRozmer( sirka, vyska );
        new Strom_4c( 0, 0, sirka, vyska );
    }


    /***************************************************************************
     * Metoda upravi rozmer platna a "vysadi" na nej alej dvou rad stromu
     * se tremi stromy v kazde rade. Stromy budou vysazeny sikmo ve smeru
     * hlavni diagonaly s kmenem zabirajicim 1/3 vysky a 1/10 sirky stromu.
     */
    public static void alej()
    {
        Platno.getInstance().setRozmer( 400, 350 );
        new Strom_4c( 100,   0 );      new Strom_4c( 300,   0 );
        new Strom_4c(  50, 100 );      new Strom_4c( 250, 100 );
        new Strom_4c(   0, 200 );      new Strom_4c( 200, 200 );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor tridy Strom_4c vytvori v levem hornim rohu platna
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     */
    public Strom_4c()
    {
        this( 0, 0 );
    }


    /***************************************************************************
     * Vytvori na zadanych souradnicich
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     */
    public Strom_4c( int x, int y )
    {
        this( x, y, 100, 150 );
    }


    /***************************************************************************
     * Vytvori na zadanych souradnicich instanci se zadanou sirkou a vyskou.
     * Pomer velikosti kmene ku zbytku stromu zustane implicitni, tj.
     * kmen bude zabirat 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     */
    public Strom_4c( int x, int y, int sirka, int vyska )
    {
        this( x, y, sirka, vyska,
            IMPLICITNI_POMER_SIRKY, IMPLICITNI_POMER_VYSKY );
    }


    /***************************************************************************
     * Vytvori na zadanych souradnicich instanci se zadanou sirkou, vyskou.
     * a pomerem velikosti kmene ku zbytku stromu.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     * @param podilVyskyKmene  Kolikrat je kmen mensi nez cely strom
     * @param podilSirkyKmene  Kolikrat je kmen uzsi nez cely strom
     */
    public Strom_4c( int x, int y, int sirka, int vyska,
                  int podilSirkyKmene, int podilVyskyKmene )
    {
        int vyskaKmene  = vyska / podilVyskyKmene;
        int vyskaKoruny = vyska - vyskaKmene;
        int sirkaKmene  = sirka / podilSirkyKmene;
        int posunKmene  = ( sirka - sirkaKmene) / 2;
        koruna = new Elipsa  ( x, y, sirka, vyskaKoruny, Barva.ZELENA  );
        kmen   = new Obdelnik( x+posunKmene, y+vyskaKoruny,
                               sirkaKmene, vyskaKmene, Barva.HNEDA );
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public int getX()
    {
        return koruna.getX();
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public int getY()
    {
        return koruna.getY();
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        koruna.setPozice( x, y );
        kmen  .setPozice( x  +  (koruna.getSirka() - kmen.getSirka()) / 2,
                          y  +  koruna.getVyska()     );
        koruna.nakresli();
    }


    /***************************************************************************
     * Vrati sirku instance.
     *
     * @return  Sirka instance v bodech
     */
    public int getSirka()
    {
        return koruna.getSirka();
    }


    /***************************************************************************
     * Vrati vysku instance.
     *
     * @return  Vyska instance v bodech
     */
    public int getVyska()
    {
        return koruna.getVyska() + kmen.getVyska();
    }


    /***************************************************************************
     * Vrati barvu koruny stromu.
     *
     * @return  Instance tridy Barva definujici nastavenou barvu koruny.
     */
    public Barva getBarvaKoruny()
    {
        return koruna.getBarva();
    }


    /***************************************************************************
     * Nastavi novou barvu koruny.
     *
     * @param nova  Pozadovana nova barva.
     */
    public void setBarvaKoruny( Barva nova )
    {
        koruna.setBarva( nova );
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vykresli obraz sve instance na platno.
     */
    public void nakresli()
    {
        koruna.nakresli();
        kmen  .nakresli();
    }


    /***************************************************************************
     * Smaze obraz sve instance z platna (nakresli ji barvou pozadi platna).
     */
    public void smaz()
    {
        koruna.smaz();
        kmen  .smaz();
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu vpravo,
     * pri zaporne hodnote parametru vlevo.
     *
     * @param vzdalenost Vzdalenost, o kterou se instance presune.
     */
    public void posunVpravo( int vzdalenost )
    {
        koruna.posunVpravo( vzdalenost );
        kmen  .posunVpravo( vzdalenost );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vpravo.
     */
    public void posunVpravo()
    {
        posunVpravo( krok );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vlevo.
     */
    public void posunVlevo()
    {
        posunVpravo( -krok );
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu dolu,
     * pri zaporne hodnote parametru nahoru.
     *
     * @param vzdalenost   Pocet bodu, o ktere se instance presune.
     */
    public void posunDolu( int vzdalenost )
    {
        koruna.posunDolu( vzdalenost );
        kmen  .posunDolu( vzdalenost );
        koruna.nakresli();
    }


    /***************************************************************************
     * Presune instanci o krok bodu dolu.
     */
    public void posunDolu()
    {
        posunDolu( krok );
    }


    /***************************************************************************
     * Presune instanci o krok bodu nahoru.
     */
    public void posunVzhuru()
    {
        posunDolu( -krok );
    }


    /***************************************************************************
     * Nastavi parametry okna s platnem tak, aby prave zaramovalo danou
     * instanci. Instanci pred tim presune do leveho horniho rohu platna.
     */
    public void zaramuj()
    {
        Platno.getInstance().setRozmer( getSirka(), getVyska() );
        setPozice( 0, 0 );
    }


    /***************************************************************************
     * Prevede instanci na retezec obsahujici nazev tridy, poradi instance,
     * jeji souradnice a rozmery.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return "Strom: x=" + getX() + ", y=" + getY() +
               ", sirka=" + getSirka() + ", vyska=" + getVyska();
    }


    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
//
//    /***************************************************************************
//     * Testovaci metoda.
//     */
//    public static void test()
//    {
//        Strom_4c x = new Strom_4c();
//    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

