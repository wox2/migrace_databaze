/*******************************************************************************
 * Instance tridy {@code Elipsa} predstavuji elipsy urcene 
 * pro praci na virtualnim platne pri prvnim seznameni s tridami a objekty.
 *  
 * Oproti stejnojmenne tride z projektu 
 * <b>01_Tvary<b/> byly pridany konstruktory a pristupove metody pracujici
 * s prepravkami {@link Pozice}, {@link Rozmer} a {@link Oblast}.
 *   
 * @author   Rudolf PECINOVSKY
 * @version  3.00.002
 */
public class Elipsa 
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro elipsu Barva.MODRA. */
    public static final Barva IMPLICITNI_BARVA = Barva.MODRA;

    /** Maximalni povolena velikost kroku. */
    public static final int MAX_KROK = 100;

    /** Platno, na ktere se bude instance kreslit. */
    private static final Platno PLATNO = Platno.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet pixelu, o nez se instance posune 
     *  po bezparametrickem posunovem povelu */
    private static int krok = 50;

    /** Pocet vytvorenych instanci */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;

    /** Nazev sestavajici z nazvu tridy a poradi instance */
    private String nazev = "Elipsa_" + poradi;


//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int    xPos;    //Bodova x-ova souradnice instance
    private int    yPos;    //Bodova y-ova souradnice instance
    private int    sirka;   //sirka v bodech
    private int    vyska;   //Vyska v bodech
    private Barva  barva;   //Barva instance


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
     *                  musi platit:  0 &lt;= velikost &lt;= {@link #MAX_KROK}
     */
    public static void setKrok( int velikost )
    {
        if( (velikost < 0)  || (velikost > MAX_KROK) ) {
            throw new IllegalArgumentException(
                "Krok musi byt z intervalu <0;" + MAX_KROK + ">." );
        }
        krok = velikost;
    }


//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Pripravi novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna 
     * a bude mit implicitni barvu, 
     * vysku rovnu kroku a sirku dvojnasobku kroku (tj. implicitne 50x100 bodu).
     */
    public Elipsa()
    {
        this( 0, 0, 2*krok, krok );
    }


    /***************************************************************************
     * Pripravi novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     */
    public Elipsa( int x, int y, int sirka, int vyska )
    {
        this( x, y, sirka, vyska, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Pripravi novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param pozice  Pozice vytvarene instance
     * @param rozmer  Rozmer vytvarene instance
     * @param barva   Barva vytvarene instance
     */
    public Elipsa( Pozice pozice, Rozmer rozmer, Barva barva )
    {
        this( pozice.x, pozice.y, rozmer.sirka, rozmer.vyska, barva );
    }


    /***************************************************************************
     * Pripravi novou instanci vyplnujici zadanou oblast 
     * a majici zadanou barvu.
     *
     * @param oblast   Oblast definujici pozici a rozmer vytvarene instance
     * @param barva    Barva vytvarene instance
     */
    public Elipsa( Oblast oblast, Barva barva )
    {
        this( oblast.x, oblast.y, oblast.sirka, oblast.vyska, barva );
    }


    /***************************************************************************
     * Pripravi novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka > 0
     * @param vyska   Vyska vytvarene instance,  vyska > 0
     * @param barva   Barva vytvarene instance
     */
    public Elipsa( int x, int y, int sirka, int vyska, Barva barva )
    {
        //Test platnosti parametru
        if( (x<0) || (y<0) || (sirka<=0) || (vyska<=0) ) {
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: x="
                + x + ", y=" + y + ", sirka=" + sirka + ", vyska=" + vyska );
        }
        
        //Parametry akceptovany --> muzeme tvorit
        this.xPos  = x;
        this.yPos  = y;
        this.sirka = sirka;
        this.vyska = vyska;
        this.barva = barva;
        nakresli();
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public int getX()
    {
        return xPos;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public int getY()
    {
        return yPos;
    }


    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return new Pozice( xPos, yPos );
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        smaz();
        xPos = x;
        yPos = y;
        nakresli();
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
         return sirka;
     }


    /***************************************************************************
     * Vrati vysku instance.
     *
     * @return  Vyska instance v bodech
     */
     public int getVyska()
     {
         return vyska;
     }


    /***************************************************************************
     * Vrati instanci tridy Rozmer s rozmery instance.
     *
     * @return   Rozmer s rozmery instance.
     */
    public Rozmer getRozmer()
    {
        return new Rozmer( sirka, vyska );
    }


    /***************************************************************************
     * Nastavi novy "ctvercovy" rozmer instance -
     * na zadany rozmer se nastavi vyska i sirka.
     *
     * @param rozmer  Nove nastavovany rozmer v obou smerech; rozmer>0
     */
    public void setRozmer(int rozmer)
    {
        setRozmer( rozmer, rozmer );
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>=0
     * @param vyska    Nove nastavovana vyska; vyska>=0
     */
    public void setRozmer(int sirka, int vyska)
    {
        if( (sirka < 0) || (vyska < 0) ) {
            throw new IllegalArgumentException(
                "Rozmery musi byt nezaporne: sirka="+sirka + ", vyska="+vyska);
        }
        smaz();
        this.sirka = sirka;
        this.vyska = vyska;
        nakresli();
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param rozmer    Nove nastavovany rozmer.
     */
    public void setRozmer(Rozmer rozmer)
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
        return new Oblast( xPos, yPos, sirka, vyska );
    }


    /***************************************************************************
     * Nastavi novou polohu a rozmery instance.
     *
     * @param o    Nove nastavovana oblast zaujimana instanci.
     */
    public void setOblast(Oblast o)
    {
        setPozice( o.x,     o.y     );
        setRozmer( o.sirka, o.vyska );
    }


    /***************************************************************************
     * Vrati barvu instance.
     *
     * @return  Instance tridy Barva definujici nastavenou barvu.
     */
    public Barva getBarva()
    {
        return barva;
    }


    /***************************************************************************
     * Nastavi novou barvu instance.
     *
     * @param nova  Pozadovana nova barva.
     */
    public void setBarva(Barva nova)
    {
        barva = nova;
        nakresli();
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



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     * 
     * @return Retezcova reprezentace dane instance.      
     */
    @Override
    public String toString()
    {
        return nazev + "_(x=" + xPos + ",y=" + yPos  +
               ",sirka=" + sirka + ",vyska=" + vyska +
               ",barva=" + barva + ")";
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vykresli obraz sve instance na platno.
     */
    public void nakresli()
    {
        PLATNO.setBarvaPopredi( barva );
        PLATNO.zapln(new java.awt.geom.Ellipse2D.Double
                         (xPos, yPos, sirka, vyska));
    }


    /***************************************************************************
     * Smaze obraz sve instance z platna (nakresli ji barvou pozadi platna).
     */
    public void smaz()
    {
        PLATNO.setBarvaPopredi( PLATNO.getBarvaPozadi() );
        PLATNO.zapln(new java.awt.geom.Ellipse2D.Double
                         (xPos, yPos, sirka, vyska));
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu vpravo,
     * pri zaporne hodnote parametru vlevo.
     *
     * @param vzdalenost Vzdalenost, o kterou se instance presune.
     */
    public void posunVpravo(int vzdalenost)
    {
        setPozice( xPos+vzdalenost, yPos );
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
    public void posunDolu(int vzdalenost)
    {
        setPozice( xPos, yPos+vzdalenost );
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


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

