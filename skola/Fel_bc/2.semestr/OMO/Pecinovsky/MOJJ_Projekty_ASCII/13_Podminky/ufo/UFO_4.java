package ufo;

/*******************************************************************************
 * Trida UFO_4 obsahuje vzorove reseni pro tridu UFO, ktera
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
public class UFO_4 extends UFO
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

//     /** Zmena tahu pri posunuti plynu o jeden stupen
//      *  (a take pri stisku sipky). */
//     public static final double DTAH = 2;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private Talir talir;
    private Cislo cislo;
    private int   poradi;


//== PROMENNE ATRIBUTY INSTANCI ================================================

    private double xTah,      yTah;
    private double xRychlost, yRychlost;
    private double xPos,      yPos;



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
    public UFO_4(Talir talir, int poradi)
    {
        super( talir, poradi );
        xPos = talir.getX();
        yPos = talir.getY();

        this.talir  = talir;
        this.poradi = poradi;
        this.cislo  = new Cislo( poradi, xPos, yPos );

        nakresli();
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati poradove cislo UFO obdrzene pri konstrukci.
     *
     * @return Pozadovane poradove cislo.
     */
    @Override
    public int getCislo()
    {
        return poradi;
    }


    /***************************************************************************
     * Vrati aktualni velikost tahu motoru ve vodorovnem smeru
     * a tim i velikost vodorovneho zrychleni, resp. zpomaleni UFO.
     * Velikost tahu udava o kolik se zmeni rychlost UFO za jednu sekundu.
     *
     * @return  Aktualni velikost tahu. Pro zrychlovani doprava je tah kladny,
     *          pro zrychlovani doleva je tah zaporny.
     */
    @Override
    public double getXTah()
    {
        return xTah;
    }


    /***************************************************************************
     * Vrati aktualni velikost tahu motoru ve svislem smeru
     * a tim i velikost svisleho zrychleni, resp. zpomaleni UFO.
     * Velikost tahu udava o kolik se zmeni rychlost UFO za jednu sekundu.
     *
     * @return  Aktualni velikost tahu. Pro zrychlovani dolu je tak kladny,
     *          pro zrychlovani vzhury je tah zaporny.
     */
    @Override
    public double getYTah()
    {
        return yTah;
    }


    /***************************************************************************
     * Vrati aktualni velikost rychlost ve vodorovnem smeru,
     * tj. pocet bodu, o ktere se UFO presune za jednu sekundu.
     *
     * @return  Aktualni velikost rychlosti. Pri pohybu doprava je rychlost
     *          kladna, pri pohybu doleva je zaporna.
     */
    @Override
    public double getXRychlost()
    {
        return xRychlost;
    }


    /***************************************************************************
     * Vrati aktualni velikost rychlost ve svislem smeru,
     * tj. pocet bodu, o ktere se UFO presune za jednu sekundu.
     *
     * @return  Aktualni velikost rychlosti. Pri pohybu dolu je rychlost kladna,
     *          pri pohybu vzhuru je zaporna.
     */
    @Override
    public double getYRychlost()
    {
        return yRychlost;
    }


    /***************************************************************************
     * Nastavi rychlost jako pocet bodu, o nez se UFO presune za jednu sekundu.
     *
     * @param x  Pozadovana velikost rychlosti ve vodorovnem smeru
     *           (kladna cisla doprava, zaporna doleva).
     * @param y  Pozadovana velikost rychlosti ve svislem smeru
     *           (kladna cisla dolu, zaporna vzhuru).
     */
    @Override
    public void setRychlost( double x, double y )
    {
        xRychlost = x;
        yRychlost = y;
    }


    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    @Override
    public double getX()
    {
        return xPos;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    @Override
    public double getY()
    {
        return yPos;
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *
     * @return Nazev tridy nasledovnay podtrzitkem a "rodnym cislem" instance.
     */
    @Override
    public String toString()
    {
        return "UFO_" + poradi;
    }


    /***************************************************************************
     * Zvetsi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vpravo()
    {
        xTah = xTah + DTAH;
    }


    /***************************************************************************
     * Zmensi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vlevo()
    {
        xTah = xTah - DTAH;
    }


    /***************************************************************************
     * Zvetsi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void dolu()
    {
        yTah = yTah + DTAH;
    }


    /***************************************************************************
     * Zmensi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    @Override
    public void vzhuru()
    {
        yTah = yTah - DTAH;
    }


    /***************************************************************************
     * Zastavi motory (zada nulovy tah) a tim nastavi nulove zrychleni.
     * Od tohoto okamziku az do pristiho zapnuti motoru se bude UFO pohybovat
     * stale stejne rychle.
     */
    @Override
    public void vypniMotory()
    {
        xTah = 0;
        yTah = 0;
    }


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
        //Musime zvysit rychlost o odpovidajici zlomek velikosti tahu
        //Zmeni-li se za vterinu rychlost o velikost tahu,
        //zmeni se za (1/frekvence) cast vteriny o (1/frekvence) cast tahu
        xRychlost = xRychlost + (xTah / frekvence);
        yRychlost = yRychlost + (yTah / frekvence);

        //Musime zmenit pozici o odpovidajici zlomek velikosti rychlosti
        //Zmeni-li se za vterinu pozice o velikost rychlosti,
        //zmeni se za (1/frekvence) cast vteriny o (1/frekvence) cast rychlosti
        xPos = xPos + (xRychlost / frekvence);
        yPos = yPos + (yRychlost / frekvence);

        //Pro talir i cislo nastavime novou pozici
        talir.setPozice( xPos, yPos );
        cislo.setPozice( xPos, yPos );
    }


    /***************************************************************************
     * Vykresli obraz sve instance ve vesmiru.
     */
    @Override
    public void nakresli()
    {
        talir.nakresli();
        cislo.nakresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

