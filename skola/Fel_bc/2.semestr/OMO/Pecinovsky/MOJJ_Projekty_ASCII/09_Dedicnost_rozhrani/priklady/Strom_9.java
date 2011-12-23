package priklady;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Rozmer;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.Obdelnik;


/*******************************************************************************
 * Trida Strom_9 obsahuje podobu tridy po implementaci rozhrani IHybaci.
 *
 *  Oproti tride {&code Strom_7c} je zmeneno:
 *  - Mista nekolika rozhrani nyni implementuje pouze IHybaci
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_9 implements IHybaci 
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Udava, kolikrat je koruna sirsi nez kmen. */
    public static final int IMPLICITNI_POMER_SIRKY = 10;

    /** Udava, kolikrat je strom vyssi nez samotny kmen. */
    public static final int IMPLICITNI_POMER_VYSKY =  3;

    /** Pamatuje si odkaz na aktivni platno, aby se zrychlily nektere operace,
     *  ale hlavne aby se zjednodusilo jeho pouziti. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet doposud vytvorenych instanci. */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi kolikata byla dana instance vytvorena v ramci tridy. */
    private final int poradi = ++pocet;

    /** Nazev sestavajici z nazvu tridy a poradi instance */
    private final String nazev  = "Strom_" + poradi;

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
//== OSTATNI METODY TRIDY ======================================================

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
            new Strom_9( 0, 0, sirka, vyska ).zobraz();
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
            new Strom_9( 100,   0 ).zobraz();
            new Strom_9( 300,   0 ).zobraz();
            new Strom_9(  50, 100 ).zobraz();
            new Strom_9( 250, 100 ).zobraz();
            new Strom_9(   0, 200 ).zobraz();
            new Strom_9( 200, 200 ).zobraz();
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
            new Strom_9( vnitrek ).zobraz();
        } SP.vratKresli();
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Implicitni konstruktor vytvori v levem hornim rohu platna
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     */
    public Strom_9()
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
    public Strom_9(int x, int y)
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
    public Strom_9(int x, int y, int sirka, int vyska)
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
    public Strom_9(Pozice pozice, Rozmer rozmer)
    {
        this( pozice.x, pozice.y, rozmer.sirka, rozmer.vyska );
    }


    /**************************************************************************
     * Vytvori novou instanci vyplnujici zadanou oblast.
     *
     * @param oblast   Oblast definujici pozici a rozmer vytvarene instance
     */
    public Strom_9(Oblast oblast)
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
    public Strom_9(int x, int y, int sirka, int vyska,
                   int podilSirkyKmene, int podilVyskyKmene)
    {
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


    /**************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return new Pozice( getX(), getY() );
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        SP.nekresli(); {        
            koruna.setPozice( x, y );
            kmen  .setPozice( x  +  (koruna.getSirka() - kmen.getSirka()) / 2,
                              y  +  koruna.getVyska()     );
        } SP.vratKresli();
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param pozice   Nova pozice instance
     */
    public void setPozice(Pozice pozice)
    {
        setPozice( pozice.x, pozice.y );
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
     * Vrati nazev instance, tj. nazev jeji tridy nasledovany poradim.
     *
     * @return  Retezec s nazvem instance.
     */
    public String getNazev()
    {
       return nazev;
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


    /***************************************************************************
     * Prevede instanci na retezec obsahujici nazev tridy, poradi instance,
     * jeji souradnice a rozmery.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return nazev + ": x=" + getX() + ", y=" + getY() +
               ", sirka=" + getSirka() + ", vyska=" + getVyska();
    }


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
     * Presune instanci o zadany pocet bodu vpravo,
     * pri zaporne hodnote parametru vlevo.
     *
     * @param vzdalenost Vzdalenost, o kterou se instance presune.
     */
    public void posunVpravo( int vzdalenost )
    {
        setPozice( getX()+vzdalenost, getY() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vpravo.
     */
    public void posunVpravo()
    {
        posunVpravo( SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vlevo.
     */
    public void posunVlevo()
    {
        posunVpravo( -SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu dolu,
     * pri zaporne hodnote parametru nahoru.
     *
     * @param vzdalenost   Pocet bodu, o ktere se instance presune.
     */
    public void posunDolu( int vzdalenost )
    {
        setPozice( getX(), getY()+vzdalenost );
    }


    /***************************************************************************
     * Presune instanci o krok bodu dolu.
     */
    public void posunDolu()
    {
        posunDolu( SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu nahoru.
     */
    public void posunVzhuru()
    {
        posunDolu( -SP.getKrok() );
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

