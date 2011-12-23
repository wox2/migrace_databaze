package ufo;

/*******************************************************************************
 * Trida RotUFO_13b obsahuje vzorove reseni pro tridu UFO, ktera
 * simuluje dopravni prostredek mimozemstanu, pomoci nejz se pohybuji
 * vesmirem. Ukolem hrace je dopravit vsechna UFO ze startovni rampy do
 * hangaru. Cestopu musi davat pozor, aby nenarazil do planet a jinych UFO.
 * Hrac muze ovladat soucasne tolik UFO, na kolik si troufne.
 * <p>
 * Pri prvni variante hry zada hrac o UFO dispecera RDispecer (preferuje
 * prime ovladani Rychlosti). Ukolem hrace je pozadat dispecera postupne
 * o jednotliva UFO a primym zadavanim jejich rychlosti prostrednictvim
 * volani metody setRychlost(int,int);
 * <p>
 * Pri druhe variante hry zada hrac o UFO dispecera ZDispecer (preferue
 * ovladni pohybu pres Zrychleni). Ukolem hrace je opet prevzit od dispecera
 * jednotliva UFO a dopravit je do hangaru, avsak tentokrat ovlivnuje jejich
 * pohyb primo klavesami. Stiskem klaves ale nenastavuje rychlost UFO,
 * ale tah jeho motoru a tim i velikost jeho zrychleni resp. zpomaleni.
 * <p>
 * Druha varianta lepe simuluje stav ve vesmiru, kde neexistuje zadne treni,
 * takze pri zapnutych motorech lod porad zrychluje ci brzdi a naopak
 * pri vypnutych motorech si udrzuje konstantni rychlost.
 * <p>
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class RotUFO_13b extends RotUFO_13a
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Maximalni dosazitelny tah motoru. */
    public static final double MAX_TAH = 8;

    /** Pocet stupnu, v nichz je mozno nastavovat tah.
     *  Tah je mozno nastavovat v obou smerech,
     *  takze skutecny pocet stupnu je dvojnasobny. */
    public static final int STUPNU = 4;

//    /** Zmena tahu pri posunuti plynu o jeden stupen
//     *  (a take pri stisku sipky). */
//    public static final double DTAH = MAX_TAH / STUPNU;
    
    

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Preda konstruktoru talir tvorici kostru UFO,
     * jeho poradi v ramci vytvorenych UFO
     * a maximalnibtah motoru, ktery ovlivnuje zmeny zrychleni instance.
     *
     * @param talir   Odkaz na talir tvorici kostru UFO.
     * @param poradi  Poradi konstruovaneho UFO v ramci jiz vytvorenych.
     */
    public RotUFO_13b(Talir talir, int poradi)
    {
        super( talir, poradi );
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Zvetsi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vpravo()
    {
        xTah += DTAH;
        if( xTah > MAX_TAH ) {
            xTah = MAX_TAH;
        }
    }


    /***************************************************************************
     * Zmensi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vlevo()
    {
        xTah -= DTAH;
        if( xTah < -MAX_TAH ) {
            xTah = -MAX_TAH;
        }
    }


    /***************************************************************************
     * Zvetsi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void dolu()
    {
        yTah += DTAH;
        if( yTah > MAX_TAH ) {
            yTah = MAX_TAH;
        }
    }


    /***************************************************************************
     * Zmensi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vzhuru()
    {
        yTah -= DTAH;
        if( yTah < -MAX_TAH ) {
            yTah = -MAX_TAH;
        }
    }

    
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void start_R13b()
    {
        Dispecer.getDispecer().pouzijTridu( RotUFO_13b.class    );
    }


}

