package priklady;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Rozmer;

import rup.cesky.tvary.Posuvny;
import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.Obdelnik;


/*******************************************************************************
 * Trida Strom_10a obsahuje podobu tridy po definici rodicovske tridy Posuvny.
 *
 *  Oproti tride {&code Strom_9} je zmeneno:
 *  - odstraneny atributy SP, pocet, poradi, nazev -
 *    jsou nyni zdedeny od predka
 *  - upraven kompletni konstruktor aby volal rodice
 *  - odstraneny metody getX(), getY(), setPozice(Pozice)
 *  - upravena metoda setPozice(int,int)
 *  - odstranena metoda getNazev();
 *  - upravena metoda toString()
 *  - odstraneny metody posunu
 *  - u zdedenych metod pribyla anotace @Override
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_10a extends Posuvny implements IHybaci 
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Udava, kolikrat je koruna sirsi nez kmen. */
    public static final int IMPLICITNI_POMER_SIRKY = 10;

    /** Udava, kolikrat je strom vyssi nez samotny kmen. */
    public static final int IMPLICITNI_POMER_VYSKY =  3;


//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /* Odkaz na instanci predstavujici korunu stromu. */
    private final Elipsa   koruna;

    /* Odkaz na instanci predstavujici korunu stromu. */
    private final Obdelnik kmen;



//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    /** Kolikrat je cely strom sirsi nez samotny kmen. */
    private int podilSirkyKmene;
    
    /** Kolikrat je cely strom vyssi nez samotny kmen. */
    private int podilVyskyKmene;

    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
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
        SP.nekresli(); {        
            SP.setKrokRozmer( 1, sirka, vyska );
            new Strom_10a( 0, 0, sirka, vyska ).zobraz();
        } SP.vratKresli();
    }


    /***************************************************************************
     * Metoda upravi rozmer platna a "vysadi" na nej alej dvou rad stromu
     * se tremi stromy v kazde rade. Stromy budou vysazeny sikmo ve smeru
     * hlavni diagonaly s kmenem zabirajicim 1/3 vysky a 1/10 sirky stromu.
     */
    public static void alej()
    {
        SP.nekresli(); {        
            SP.setKrokRozmer( 50, 8, 7 );
            new Strom_10a( 100,   0 ).zobraz();
            new Strom_10a( 300,   0 ).zobraz();
            new Strom_10a(  50, 100 ).zobraz();
            new Strom_10a( 250, 100 ).zobraz();
            new Strom_10a(   0, 200 ).zobraz();
            new Strom_10a( 200, 200 ).zobraz();
        } SP.vratKresli();
    }


    /***************************************************************************
     * Vytvori v zadane oblasti zaramovany obrazek stromu, pricemz sirka
     * ramu je zadana jako druhy parametr.
     * 
     * @param oblast     Oblast, do niz se ma strom v ramu vykreslit.
     * @param sirkaRamu  Sirka ramu v obrazovych bodech.
     */
    public static void obrazek( Oblast oblast, int sirkaRamu )
    {
        SP.nekresli(); {        
            //Nakresli budouci ram
            SP.pridej( new Obdelnik(oblast, Barva.CERNA) );
            Oblast vnitrek = new Oblast( oblast.x + sirkaRamu,
                                         oblast.y + sirkaRamu,
                                         oblast.sirka - 2*sirkaRamu,
                                         oblast.vyska - 2*sirkaRamu );
            //Vykresli pozadi budouciho obrazku
            Obdelnik pozadi = new Obdelnik( vnitrek, Barva.AZUROVA );        
            SP.pridej( pozadi );
            new Strom_10a( vnitrek ).zobraz();
        } SP.vratKresli();
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vytvori v levem hornim rohu platna
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     */
    public Strom_10a()
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
    public Strom_10a(int x, int y)
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
    public Strom_10a(int x, int y, int sirka, int vyska)
    {
        this( x, y, sirka, vyska,
            IMPLICITNI_POMER_SIRKY, IMPLICITNI_POMER_VYSKY );
    }


    /**************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery.
     *
     * @param pozice  Pozice vytvarene instance
     * @param rozmer  Rozmer vytvarene instance
     */
    public Strom_10a(Pozice pozice, Rozmer rozmer)
    {
        this( pozice.x, pozice.y, rozmer.sirka, rozmer.vyska );
    }


    /**************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast.
     *
     * @param oblast   Oblast definujici pozici a rozmer vytvarene instance
     */
    public Strom_10a(Oblast oblast)
    {
        this( oblast.x, oblast.y, oblast.sirka, oblast.vyska );
    }


    /***************************************************************************
     * Vytvori na zadanych souradnicich instanci se zadanou sirkou, vyskou.
     * a pomerem velikosti kmene ku zbytku stromu.
     * Vytvorene instanci priradi jeji "rodne cislo".
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     * @param podilSirkyKmene  Kolikrat je kmen uzsi nez cely strom
     * @param podilVyskyKmene  Kolikrat je kmen mensi nez cely strom
     */
    public Strom_10a(int x, int y, int sirka, int vyska, int podilSirkyKmene, int podilVyskyKmene)
    {
        super( x, y );
        this.podilVyskyKmene = podilVyskyKmene;
        this.podilSirkyKmene = podilSirkyKmene;
        SP.nekresli(); {        
            koruna = new Elipsa  ( x, y, 1, 1, Barva.ZELENA  );
            kmen   = new Obdelnik( x, y, 1, 1, Barva.HNEDA );
            setRozmer( sirka, vyska );
        } SP.vratKresli();
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    @Override
    public void setPozice(int x, int y)
    {
        SP.nekresli(); {
            super .setPozice( x, y );
            koruna.setPozice( x, y );
            kmen  .setPozice( x  +  (koruna.getSirka() - kmen.getSirka()) / 2,
                              y  +  koruna.getVyska()     );
        } SP.vratKresli();
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
     * Vrati instanci tridy Rozmer s rozmery instance.
     *
     * @return   Rozmer s rozmery instance.
     */
    public Rozmer getRozmer()
    {
        return new Rozmer( getSirka(), getVyska() );
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>0
     * @param vyska    Nove nastavovana vyska; vyska>0
     */
    public void setRozmer(int sirka, int vyska)
    {
        int x = getX();
        int y = getY();
        int vyskaKmene  = vyska / podilVyskyKmene;
        int vyskaKoruny = vyska - vyskaKmene;
        int sirkaKmene  = sirka / podilSirkyKmene;
        int posunKmene  = ( sirka - sirkaKmene) / 2;
        SP.nekresli(); {        
            koruna.setRozmer( sirka, vyskaKoruny );
            kmen.setPozice( x+posunKmene, y+vyskaKoruny );
            kmen.setRozmer( sirkaKmene, vyskaKmene );
        } SP.vratKresli();
    }

    
    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param rozmer    Nove nastavovany rozmer instance.
     */
    public void setRozmer( Rozmer rozmer )
    {
        setRozmer( rozmer.sirka, rozmer.vyska );
    }


    /***************************************************************************
     * Vrati instanci tridy Oblast s informacemi o pozici a rozmerech instance.
     *
     * @return   Oblast s informacemi o pozici a rozmere instance.
     */
    public Oblast getOblast()
    {
        return new Oblast( getX(), getY(), getSirka(), getVyska() );
    }

    
    /***************************************************************************
     * Nastavi novou polohu a rozmery instance.
     *
     * @param oblast    Nove nastavovana oblast zaujimana instanci.
     */
    public void setOblast( Oblast oblast )
    {
        SP.nekresli(); {
            setPozice( oblast.x,     oblast.y );
            setRozmer( oblast.sirka, oblast.vyska );
        } SP.vratKresli();
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



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Vykresli obraz sve instance na platno.
     *
     * @param kreslitko  Objekt, jehoz prostrednictvim se ma instance nakreslit.
     */
    public void nakresli(Kreslitko kreslitko)
    {
        //Tady se dvojice nekresli() - vratKresli() nepouzije,
        //protoze se beztak prave prekresluje
        koruna.nakresli(kreslitko);
        kmen  .nakresli(kreslitko);
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec obsahujici nazev tridy, poradi instance,
     * jeji souradnice a rozmery.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return super.toString() + ", sirka=" + getSirka() + 
                                  ", vyska=" + getVyska();
    }

    
    
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Prihlasi instanci u aktivniho platna do jeho spravy.
     */
    public void zobraz()
    {
        SP.pridej( this );
    }


    /***************************************************************************
     * Odstrani obraz sve instance z platna.
     */
    public void smaz()
    {
        SP.odstran( this );
    }


    /***************************************************************************
     * Odstrani z platna vsechny ostatni instance a nastavi
     * parametry okna s platnem tak, aby prave zaramovalo danou instanci.
     */
    public void zaramuj()
    {
        SP.nekresli(); {        
            SP.odstranVse();
            SP.setKrokRozmer( 1, getSirka(), getVyska() );
            setPozice( 0, 0 );
            SP.pridej( this );
        } SP.vratKresli();
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

