package ufo;

/*******************************************************************************
 * Trida RotUFO_13d obsahuje vzorove reseni pro tridu UFO, ktera
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
public class RotUFO_13d extends RotUFO_13b
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
     * Preda konstruktoru talir tvorici kostru UFO,
     * jeho poradi v ramci vytvorenych UFO
     * a maximalnibtah motoru, ktery ovlivnuje zmeny zrychleni instance.
     *
     * @param talir   Odkaz na talir tvorici kostru UFO.
     * @param poradi  Poradi konstruovaneho UFO v ramci jiz vytvorenych.
     */
    public RotUFO_13d(Talir talir, int poradi)
    {
        super( talir, poradi );
    }


//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Nastavi nove souradnice instance tak,
     * ze k soucasne hodnote souradnic pricte aktualni rychlost.
     * Je-li nastavene nenulove zrychleni (tah motoru), musi nejprve
     * pripocist k rychlosti aktualni zrychleni a tak nastavit
     * jeji aktualni velikost. Teprve tu muze pricist k hodnote souradnic.
     * <p>
     * Pri pripocitavani zrychleni k rychlosti a rychlosti k pozici je treba
     * pocitat s tim, ze Dispcer vola tuto metodu mnohokrat za sekundu.
     * Proto predava jako parametr pocet volani za sekundu, aby program vedel,
     * cim ma nastavenou velikost zrychleni ci rychlosti podelit,
     * aby ziskal spravnou velikost opakovaneho prirustku.
     */
    @Override
    public void popojed( int frekvence )
    {
        super.popojed( frekvence );

        if( Math.abs(xRychlost) > VSIRKA/3 )
        {
            System.out.println("Standard - prijde vyjimka" );
            System.err.println("CHYBY - prijde vyjimka" );
            throw new IllegalStateException( "Prilis velka vodorna rychlost");
//            System.out.println("Standard - prisla vyjimka" );
//            System.out.println("CHYBY - prisla vyjimka" );
        }
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void start_R13d()
    {
        Dispecer.getDispecer().pouzijTridu( RotUFO_13d.class    );
    }


}

