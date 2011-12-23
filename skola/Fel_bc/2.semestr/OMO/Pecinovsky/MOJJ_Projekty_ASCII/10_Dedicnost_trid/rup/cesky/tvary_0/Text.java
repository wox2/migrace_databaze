package rup.cesky.tvary_0;

import java.awt.Font;


/*******************************************************************************
 * Trida pro Zobrazeni textu na aktivnim platne.
 *
 * Oproti verzi z balicku rup.cesky._09_dedicnost_rozhrani.tvary_0
 * se zmenilo pouze implementovane rozhrani
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Text  implements  IPosuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Konstanta oznacujici text vysazeny netucnym, nekurzivnim pismem. */
    public static final int OBYCEJNY = Font.PLAIN;

    /** Konstanta oznacujici text vysazeny tucnym, nekurzivnim pismem. */
    public static final int TUCNY    = Font.BOLD;

    /** Konstanta oznacujici text vysazeny netucnym, kurzivnim pismem. */
    public static final int KURZIVA  = Font.ITALIC;
    
    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro text Barva.CERNA. */
    public static final Barva IMPLICITNI_BARVA = Barva.CERNA;

    /** Aktivni platno, ktere dohlizi na spravne vykresleni instance. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet vytvorenych instanci */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Text uchovavany a zobrazovany instanci. */
    private final String nazev;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int    xPos;    //Bodova x-ova souradnice instance
    private int    yPos;    //Bodova y-ova souradnice instance
    private Barva  barva;   //Barva instance
    private Font   font;    //Pismo, kterym se text sazi
    private Font   kfont;   //Pismo nastavene pro kreslitko



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna 
     * a bude mit implicitni barvu, 
     * a vysazena implicitnim pismem (tucnym 12bodovym pismem Dialog).    
     *
     * @param text  Vypisovany text
     */
    public Text( String text )
    {
        this( text, 0, 0 );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou
     * a vysazena implicitnim pismem (tucnym 12bodovym pismem Dialog).    
     *
     * @param text  Vypisovany text
     * @param x     x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y     y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     */
    public Text( String text, int x, int y )
    {
        this( text, x, y, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou
     * a implicitnim pismem (bude vysazen tucnym 12bodovym pismem Dialog).    
     *
     * @param text  Vypisovany text
     * @param pocatek   Pozice pocatku instance
     */
    public Text( String text, Pozice pocatek )
    {
        this( text, pocatek.x, pocatek.y );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a barvou.
     *
     * @param text      Vypisovany text
     * @param pocatek   Pozice pocatku instance
     * @param barva     Barva vytvarene instance
     */
    public Text(String text, Pozice pocatek, Barva barva)
    {
        this( text, pocatek.x, pocatek.y, barva );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou, rozmery a barvou.
     *
     * @param text    Vypisovany text
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param barva   Barva vytvarene instance
     */
    public Text( String text, int x, int y, Barva barva )
    {
        this.nazev = text;
        this.xPos  = x;
        this.yPos  = y;
        this.barva = barva;
        this.font = new Font( "Dialog", Font.BOLD, 12 );
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
        xPos = x;
        yPos = y;
        SP.prekresli();
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
     * @param nova   Pozadovana nova barva.
     */
    public void setBarva(Barva nova)
    {
        barva = nova;
        SP.prekresli();
    }


    /***************************************************************************
     * Tato metoda je tu jenom proto, aby se text sjednotil s ostatnimi
     * tridami - jinak text ji samozrejme nepotrebuje, protoze tisteny text
     * je sam nazvem instance.               
     *
     * @return  Ulozeny text.
     */
     public String getNazev()
     {
        return nazev;
     }


    /***************************************************************************
     * Nastavi font, kterym se bude dany text sazet.
     *
     * @param nazev     Nazev fontu - je mozno zadat jeden z nazvu:
     *                   "Dialog", "DialogInput", "Monospaced",
     *                   "Serif",  "SansSerif".
     * @param rez       Je mozno zadat nektery z rezu:
     *                   Text.OBYCEJNY, Text.TUCNY, Text.KURZIVA,
     *                   pripadne Text.TUCNY|Text.KURZIVA
     * @param velikost  Velikost pisma v bodech.
     */
    public void setFont( String nazev, int rez, int velikost )
    {
        font = new Font( nazev, rez, velikost );
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *  
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.     
     */
    public void nakresli( Kreslitko kreslitko )
    {
        if( font != kfont )
        {
            kreslitko.setFont( font );
            kfont = font;
        }
        kreslitko.kresliText( nazev, xPos, yPos + font.getSize(), barva );
    }



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
        return nazev;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

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
     * @param vzdalenost    Pocet bodu, o ktere se instance presune.
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
        posunDolu( SP.getKrok() );
    }


    /***************************************************************************
     * Presune instanci o krok bodu nahoru.
     */
    public void posunVzhuru()
    {
        posunDolu( -SP.getKrok() );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

