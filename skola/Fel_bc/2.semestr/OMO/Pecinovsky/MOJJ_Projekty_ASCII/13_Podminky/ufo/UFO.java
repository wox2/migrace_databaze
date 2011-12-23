package ufo;

/*******************************************************************************
 * Trida UFO simuluje dopravni prostredek mimozemstanu, pomoci nejz se pohybuji
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
public class UFO
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Zmena tahu (a tim i zrychleni) pri posunuti plynu o jeden stupen
     *  (a take pri stisku sipky). */
    public static final double DTAH = 2;



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor je vyvolavan dipecerem pote, 
     * co dispecera nekdo pozada o pristaveni noveho UFO. 
     * Dispecer vytvori talir, umisti jej na startovni rampu a 
     * preda jej konstruktoru UFO jako parametr
     * spolu s jeho poradim v ramci vytvorenych UFO.
     *
     * @param talir   Odkaz na talir tvorici kostru UFO.
     * @param poradi  Poradi konstruovaneho UFO v ramci jiz vytvorenych.
     */
    public UFO( Talir talir, int poradi )
    {
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati poradove cislo UFO obdrzene pri konstrukci.
     *
     * @return Pozadovane poradove cislo.
     */
    public int getCislo()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati aktualni velikost tahu motoru ve vodorovnem smeru
     * a tim i velikost vodorovneho zrychleni, resp. zpomaleni UFO. 
     * Velikost tahu udava o kolik se zmeni rychlost UFO za jednu sekundu.
     *
     * @return  Aktualni velikost tahu. Pro zrychlovani doprava je tah kladny,
     *          pro zrychlovani doleva je tah zaporny.
     */
    public double getXTah()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati aktualni velikost tahu motoru ve svislem smeru
     * a tim i velikost svisleho zrychleni, resp. zpomaleni UFO.
     * Velikost tahu udava o kolik se zmeni rychlost UFO za jednu sekundu.
     *
     * @return  Aktualni velikost tahu. Pro zrychlovani dolu je tak kladny,
     *          pro zrychlovani vzhury je tah zaporny.
     */
    public double getYTah()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati aktualni velikost rychlost ve vodorovnem smeru,
     * tj. pocet bodu, o ktere se UFO presune za jednu sekundu.
     *
     * @return  Aktualni velikost rychlosti. Pri pohybu doprava je rychlost
     *          kladna, pri pohybu doleva je zaporna.
     */
    public double getXRychlost()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati aktualni velikost rychlost ve svislem smeru,
     * tj. pocet bodu, o ktere se UFO presune za jednu sekundu.
     *
     * @return  Aktualni velikost rychlosti. Pri pohybu dolu je rychlost kladna,
     *          pri pohybu vzhuru je zaporna.
     */
    public double getYRychlost()
    {
        return 0;
    }


    /***************************************************************************
     * Nastavi rychlost jako pocet bodu, o nez se UFO presune za jednu sekundu.
     *
     * @param x  Pozadovana velikost rychlosti ve vodorovnem smeru
     *           (kladna cisla doprava, zaporna doleva).
     * @param y  Pozadovana velikost rychlosti ve svislem smeru
     *           (kladna cisla dolu, zaporna vzhuru).
     */
    public void setRychlost( double x, double y )
    {
    }


    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public double getX()
    {
        return 0;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public double getY()
    {
        return 0;
    }

    

//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *      
     * @return Nazev tridy nasledovnay podtrzitkem a cislem dane instance. 
     */
    @Override
    public String toString()
    {
        //V nasledujicim textu staci "pripocist" k uvodnimu retezci
        //promennou obsahujici poradi dane instance - to dostava 
        //konstruktor ve svem druhem parametru.
        //V komentari u prikazu se tato promenna jmenuje "poradi".
        //Pojmenujete-li ji stajne, staci smazat strednik a nasledujici //
        return "UFO_"; // + poradi;
    }


    /***************************************************************************
     * Zvetsi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    public void vpravo()
    {
    }


    /***************************************************************************
     * Zmensi vodorovny tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    public void vlevo()
    {
    }


    /***************************************************************************
     * Zvetsi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    public void dolu()
    {
    }


    /***************************************************************************
     * Zmensi svisly tah motoru a tim i zrychleni (= prirustek rychlosti)
     * o jeden dil maximalniho tahu, tj. o DTAH.
     */
    public void vzhuru()
    {
    }


    /***************************************************************************
     * Zastavi motory (zada nulovy tah) a tim nastavi nulove zrychleni.
     * Od tohoto okamziku az do pristiho zapnuti motoru se bude UFO pohybovat
     * stale stejne rychle.
     */
    public void vypniMotory()
    {
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
     * 
     * @param frekvence Frekvence prekreslovani
     */
    public void popojed( int frekvence )
    {
    }


    /***************************************************************************
     * Vykresli obraz sve instance ve vesmiru.
     */
    public void nakresli()
    {
    }

    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

