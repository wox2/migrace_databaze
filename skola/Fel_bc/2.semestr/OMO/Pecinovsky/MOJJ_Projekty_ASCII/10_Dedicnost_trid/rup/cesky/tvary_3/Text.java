package rup.cesky.tvary_3;

import java.awt.Font;


/*******************************************************************************
 * Trida pro Zobrazeni textu na aktivnim platne.
 *
 * Oproti verzi z balicku <b>rup.cesky._10_dedicnost_trid.tvary_1</b>
 * se nic nezmenilo
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Text extends Posuvny  implements  IPosuvny
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



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    private String text;    //Zobrazovany text
    private Barva  barva;   //Barva instance
    private Font   font;    //Pismo, kterym se text sazi
    private Font   kfont;   //Pismo nastavene pro kreslitko



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

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
        super( x, y );
        this.text = text;
        this.barva = barva;
        this.font = new Font( "Dialog", Font.BOLD, 12 );
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

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
     * Vrati vypisovany text.
     *
     * @return  Vypisovany text
     */
    public String getText() {
        return text;
    }


    /***************************************************************************
     * Nastavi vypisovany text.
     *
     * @param text  Vypisovany text
     */
    public void setText(String text) {
        this.text = text;
        SP.prekresli();
    }


    /***************************************************************************
     * Nastavi font, kterym se bude dany text sazet.
     *
     * @param nazev     Nazev fontu - je mozno zadat jeden z nazvu:
     *                  "Dialog", "DialogInput", "Monospaced",
     *                  "Serif",  "SansSerif".
     * @param rez       Je mozno zadat nektery z rezu:
     *                  Text.OBYCEJNY, Text.TUCNY, Text.KURZIVA,
     *                  pripadne Text.TUCNY|Text.KURZIVA
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
        kreslitko.kresliText( getNazev(), getX(), getY() + font.getSize(),
                              getBarva() );
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
        return text;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

