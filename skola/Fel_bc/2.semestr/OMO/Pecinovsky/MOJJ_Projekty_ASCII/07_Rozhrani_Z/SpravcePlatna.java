import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
//import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileReader;
//import java.io.IOException;
import java.io.Reader;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

//import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/*******************************************************************************
 * Trida <b><code>SpravcePlatna</code></b> slouzi k jednoduchemu kresleni
 * na virtualni platno a pripadne nasledne animaci nakreslenych obrazku.
 * </p><p>
 * Trida neposkytuje verejny konstruktor, protoze chce, aby jeji instance
 * byla jedinacek, tj. aby se vsechno kreslilo na jedno a to same platno.
 * Jedinym zpusobem, jak ziskat odkaz na instanci tridy
 * <code>SpravcePlatna</code>,
 * je volani jeji staticke metody <code>getInstance()</code>.
 * </p><p>
 * Trida <code>SpravcePlatna</code> funguje jako manazer, ktery dohlizi
 * na to, aby se po zmene zobrazeni nektereho z tvaru vsechny ostatni tvary
 * radne prekreslily, aby byly spravne zachyceny vsechny prekryvy
 * a aby se pri pohybu jednotlive obrazce vzajemne neodmazavaly.
 * Aby vse spravne fungovalo, je mozno pouzit jeden dvou pristupu:</p>
 * <ul>
 *   <li>Manazer bude obsah platna prekreslovat
 *       <b>v pravidelnych intervalech</b>
 *       bez ohledu na to, jestli se na nem udala nejaka zmena ci ne.
 *       <ul><li>
 *       <b>Vyhodou</b> tohoto pristupu je, ze se zobrazovane objekty
 *       nemusi starat o to, aby se manazer dozvedel, ze se jejich stav zmenil.
 *       </li><li>
 *       <b>Neyhodou</b> tohoto pristupu je naopak to, ze manazer
 *       spotrebovava na neustale prekreslovani jistou cast vykonu
 *       procesoru, coz muze u pomalejsich pocitacu pusobit problemy.
 *       <br>&nbsp;</li></ul></li>
 *   <li>Manazer prekresluje platno <b>pouze na vyslovne pozadani</b>.
 *       <ul><li>
 *       <b>Vyhodou</b> tohoto pristupu je uspora spotrebovaneho vykonu
 *       pocitace v obdobi, kdy se na platne nic nedeje.
 *       </li><li>
 *       <b>Nevyhodou</b> tohoto pristupu je naopak to, ze kreslene
 *       objekty musi na kazdou zmenu sveho stavu upozornit manazera,
 *       aby vedel, zed ma platno prekreslit.
 *   </li>
 * </ul><p>
 * Trida <code>SpravcePlatna</code> poziva druhou z uvedenych strategii,
 * tj. <b>prekresluje platno pouze na pozadani</b>.
 * </p><p>
 * Obrazec, ktery chce byt zobrazovan na platne, se musi nejprve prihlasit
 * u instance tridy <code>SpravcePlatna</code>, aby jej tato zaradila
 * mezi spravovane obrazce (sada metod <code>pridej&hellip;</code>).
 * Prihlasit se vsak mohou pouze instance trid, ktere implementuji
 * rozhrani <code>IKresleny</code>.
 * </p><p>
 * Neprihlaseny obrazec nema sanci byti zobrazen, protoze na platno
 * se muze zobrazit pouze za pomoci kreslitka, jez muze ziskat jedine od
 * instance tridy <code>SpravcePlatna</code>, ale ta je poskytuje pouze
 * instancim, ktere se prihlasily do jeji spravy.
 * </p><p>
 * Obrazec, ktery jiz dale nema byt kreslen, se muze odhlasit zavolanim
 * metody <code>odstran(IKresleny)</code>.Zavolanim metody
 * <code>odstranVse()</code> se ze seznamu spravovanych (a tim i z platna)
 * odstrani vsechny vykreslovane obrazce.
 * </p><p>
 * Efektivitu vykreslovani je mozne ovlivnit volanim metody
 * <code>nekresli()</code>, ktera pozastavi prekreslovani platna po nahlasenych
 * zmenach. Jeji volani je vyhodne napr. v situaci, kdy je treba vykreslit
 * obrazec slozeny z rady mensich obrazcu a bylo by nevhodne prekreslovat
 * platno po vykresleni kazdeho z nich.
 * </p><p>
 * Do puvodniho stavu prevedeme platno volanim metody <code>vratKresli()</code>,
 * ktera vrati vykreslovani do stavu pred poslednim volanim metody
 * <code>nekresli()</code>. Nemuzec se tedy stat, ze by se pri zavolani metody
 * <code>nekresli()</code> v situaci, kdy je jiz vykreslovani pozastaveno,
 * zacalo po nasledem zavolani <code>vratKresli()</code> hned vykreslovat.
 * Po dvou volanich <code>vratKresli()</code> se zacne vykreslovat az po
 * dvou zavolanich <code>vratKresli()</code>.
 * </p><p>
 * Proto platno pouze zadame, aby se vratilo do toho kresliciho stavu,
 * ve kterem bylo v okamziku, kdy jsme je naposledy zadali o to,
 * aby se prestalo prekreslovat. Nemuze se tedy stat, ze by se pri zavolani
 * metody <code>nekresli()</code> v situaci, kdy je jiz vykreslovani
 * pozastaveno, zacalo po naslednem zavolani <code>vratKresli()</code> hned
 * vykreslovat.
 * </p><p>
 * Kazde zavolani metody <code>nekresli()</code> musi byt doplneno
 * odpovidajicim volanim <code>vratKresli()</code>. Teprve kdyz posledni
 * <code>vratKresli()</code> odvola prvni <code>nekresli()</code>, bude
 * prekreslovani opet obnoveno.
 * </p>
 *
 * @author Rudolf PECINOVSKY
 * @version 4.00, 16.06.2005
 */
public final class SpravcePlatna
{
//     static { System.out.println( "Inicializace tridy SpravcePlatna" ); }
//     { System.out.println( "Inicializace instance tridy SpravcePlatna" ); }
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Titulek okna aktivniho platna. */
    private static final String TITULEK_0  = "Platno ovladane spravcem";

    /** Pocatecni polickova sirka aktivni plochy platna. */
    private static final int SIRKA_0 = 6;

    /** Pocatecni polickova vyska aktivni plochy platna. */
    private static final int VYSKA_0 = 6;

    /** Pocatecni barva pozadi platna. */
    private static final Barva POZADI_0 = Barva.KREMOVA;

    /** Pocatecni barva car mrizky. */
    private static final Barva BARVA_CAR_0 = Barva.CERNA;

    /** Implicitni roztec ctvercove site. */
    private static final int KROK_0 = 50;

    /** Maximalni povolena velikost roztece ctvercove site. */
    private static final int MAX_KROK = 200;

    /** Jedina instance tridy SpravcePlatna. */
    private static final SpravcePlatna SP = new SpravcePlatna();

    //Pri kresleni car se pta APosuvny po spravci planta. Proto se mohou cary
    //kreslit az pote, co bude jedninacek (konstanta SP) inicializovan.
    static
    {
        //Pripravi a vykresli prazdne platno
        SP.setRozmer(SIRKA_0, VYSKA_0);
        //Nyni je znam rozmer platna, tak muzeme umistit dialogova okna
        int x = SP.okno.getX();
        int y = SP.okno.getY() + SP.okno.getHeight();
        IO.oknaNa(x, y);
    }



//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Aplikacni okno animacniho platna. */
    private final JFrame okno;

    /** Instance lokalni tridy, ktera je zrizena proto, aby odstinila
     *  metody sveho rodice JPanel. */
    private final JPanel platno;

    /** Seznam zobrazovanych predmetu. */
    private final List<IKresleny> predmety = new ArrayList<IKresleny>();

//    /** Seznam prihlasenych prizpusobivych predmetu. */
//    private final Collection<IPrizpusobivy> prizpusobivi =
//                                            new ArrayList<IPrizpusobivy>();


//== PROMENNE ATRIBUTY INSTANCI ================================================

    //Z venku neovlivnitelne Atributy pro zobrazeni platna v aplikacnim okne

        /** Vse se kresli na obraz - ten se snadneji prekresli. */
        private Image obrazPlatna;

        /** Kreslitko ziskane od obrazu platna, na nejz se vlastne kresli. */
        private Kreslitko kreslitko;

        /** Semafor branici prilis castemu prekreslovani. Prekresluje se pouze
         *  je-li ==0. Nesmi byt <0. */
        private int nekreslit = 0;

        /** Priznak toho, ze kresleni prave probiha,
         *  takze vypinani nefunguje. */
        private boolean kreslim = false;

        /** Cary zobrazujici na plante mrizku. */
        private Cara[] vodorovna,   svisla;

    //Primo ovlivnitelne atributy

        /** Roztec ctvercove site. */
        private int krok = KROK_0;

        /** Zobrazuje-li se mrizka. */
        private boolean mrizka = true;

        /** Barva pozadi platna. */
        private Barva barvaPozadi = POZADI_0;

        /** Barva car mrizky. */
        private Barva barvaCar = BARVA_CAR_0;

        /** Sirka aktivni plochy platna v udavana v polich. */
        private int sloupcu = SIRKA_0;

        /** Vyska aktivni plochy platna v udavana v polich. */
        private int radku = VYSKA_0;

        /** Sirka aktivni plochy platna v bodech. */
        private int sirkaBodu = SIRKA_0 * krok;

        /** Vyska aktivni plochy platna v bodech. */
        private int vyskaBodu = VYSKA_0 * krok;

//        /** Zda se maji prizpusobivi upozornovat na zmeny rozmeru pole. */
//        private boolean hlasitZmenyRozmeru = true;

        /** Zda je mozno menit velikost kroku. */
        private Object vlastnikPovoleniZmenyKroku = null;

        /** Nazev v titulkove liste animacniho platna. */
        private String nazev  = TITULEK_0;

        /** Pozice platna na obrazovace - pri pouzivani vice obrazovek
         *  je obcas treba ji po zviditelneni obnovit. */
        Point pozice = new Point(0, 0);



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci tridy - je volana pouze jednou.
     */
    private SpravcePlatna()
    {
        okno  = new JFrame();          //Vytvori nove aplikacni okno
        okno.setTitle(nazev);
        inicializace();

        //Zavrenim okna se zavre cela aplikace
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Vlastni platno je definovano jako instance anonymni tridy
        platno =
            new JPanel()
            {   /** Povinne prekryvana abstraktni metoda tridy JPanel. */
                @Override
                public void paint( Graphics g ) {
                    synchronized( SP ) {
                        g.drawImage( obrazPlatna, 0, 0, null );
                    }
                }
            };//Konec definice tridy platna
        okno.setContentPane(platno);
    }


    /***************************************************************************
     * Jedina metoda umoznujici ziskat odkaz na instanci platna.
     * Vraci vsak pokazde odkaz na stejnou instanci,
     * protoze instance platna je jedinacek.
     * <p>
     * Pokud instance pri volani metody jeste neexistuje,
     * metoda instanci vytvori.</p>
     *
     * @return Instance tridy SpravcePlatna
     */
    public static SpravcePlatna getInstance()
    {
        return SP;
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Nastavi rozmer platna zadanim bodove velikosti policka a
     * poctu policek ve vodorovnem a svislem smeru.
     * Pri velikosti policka = 1 se vypina zobrazovani mrizky.
     *
     * @param  krok    Nova bodova velikost policka
     * @param  pSirka  Novy pocet policek vodorovne
     * @param  pVyska  Novy pocet policek svisle
     */
    public synchronized void setKrokRozmer( int krok, int pSirka, int pVyska )
    {
        setKrokRozmer( krok, pSirka, pVyska, null );
    }


    /***************************************************************************
     * Nastavi rozmer platna zadanim bodove velikosti policka a
     * poctu policek ve vodorovnem a svislem smeru.
     * Pri velikosti policka = 1 se vypina zobrazovani mrizky.
     *
     * @param  krok    Nova bodova velikost policka
     * @param  pSirka  Novy pocet policek vodorovne
     * @param  pVyska  Novy pocet policek svisle
     * @param  menic   Objekt, ktery zada o zmenu rozmeru. Jakmile je jednou
     *                 tento objekt nastaven, nesmi jiz rozmer platna
     *                 menit nikdo jiny.
     */
    public synchronized void setKrokRozmer( int krok, int pSirka, int pVyska,
                                            Object menic )
    {
        if( menic != vlastnikPovoleniZmenyKroku ) {
            if( vlastnikPovoleniZmenyKroku == null ) {
                vlastnikPovoleniZmenyKroku = menic;
            } else {
                throw new IllegalStateException(
                    "Zmena kroku a rozmeru neni danemu objektu povolena" );
            }
        }
        //Overeni korektnosti zadanych parametru
        Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
        if( (krok   < 1)  ||
            (pSirka < 2)  ||  (obrazovka.width  < sirkaBodu) ||
            (pVyska < 2)  ||  (obrazovka.height < vyskaBodu) )
        {
            throw new IllegalArgumentException(
                "\nSpatne zadane rozmery: " +
                "\n  krok =" + krok  + " bodu," +
                "\n  sirka=" + pSirka + " poli = " + pSirka*krok + " bodu," +
                "\n  vyska=" + pVyska + " poli = " + pVyska*krok + " bodu," +
                "\n  obrazovka= " + obrazovka.width  + "" +
                                    obrazovka.height + " bodu\n" );
        }

        sirkaBodu = pSirka * krok;
        vyskaBodu = pVyska * krok;

        okno.setResizable( true );
        platno.setPreferredSize( new Dimension( sirkaBodu, vyskaBodu ) );
        okno.pack();
        okno.setResizable( false );

        obrazPlatna = platno.createImage( sirkaBodu, vyskaBodu );
        kreslitko = new Kreslitko( (Graphics2D)obrazPlatna.getGraphics() );
        kreslitko.setPozadi( barvaPozadi );

        int stary      = this.krok;
        this.krok      = krok;
        this.sloupcu = pSirka;
        this.radku = pVyska;

        pripravCary();

//        if( hlasitZmenyRozmeru  &&  (krok != stary) )
//        {
//            nekreslit++; {
//                for( IPrizpusobivy ip : prizpusobivi ) {
//                    ip.krokZmenen(stary, krok);
//                }
//            }nekreslit--;
//        }
        setViditelne(true);
    }


    /***************************************************************************
     * Vrati vzdalenost car mrizky = bodovou velikost policka.
     *
     * @return Bodova velikost policka
     */
     public synchronized int getKrok()
     {
         return krok;
     }


    /***************************************************************************
     * Nastavi vzdalenost car mrizky = bodovou velikost policka.
     * Pri velikosti policka = 1 se vypina zobrazovani mrizky.
     *
     * @param velikost  Nova bodova velikost policka
     */
    public synchronized void setKrok( int velikost )
    {
        setKrokRozmer( velikost, sloupcu, radku    );
    }


    /***************************************************************************
     * Vrati pocet sloupcu platna, tj. jeho polickovou sirku.
     *
     * @return  Aktualni polickova sirka platna (pocet policek vodorovne)
     */
    public synchronized int getSloupcu()
    {
        return sloupcu;
    }


    /***************************************************************************
     * Vrati bodovou sirku platna.
     *
     * @return  Aktualni bodova sirka platna (pocet bodu vodorovne)
     */
    public synchronized int getBsirka()
    {
        return sirkaBodu;
    }


    /***************************************************************************
     * Vrati pocet radku platna, tj. jeho polickovou vysku.
     *
     * @return  Aktualni polickova vyska platna (pocet policek svisle)
     */
    public synchronized int getRadku()
    {
        return radku;
    }


    /***************************************************************************
     * Vrati bodovou vysku platna.
     *
     * @return  Aktualni bodova vyska platna (pocet bodu svisle)
     */
    public synchronized int getBVyska()
    {
        return vyskaBodu;
    }


    /***************************************************************************
     * Vrati polickovy rozmer platna, tj. sirku a vysku v polich.
     *
     * @return  Aktualni polickovy rozmer platna
     */
    public synchronized Rozmer getRozmer()
    {
        return new Rozmer( sloupcu, radku );
    }


    /***************************************************************************
     * Nastavi rozmer platna zadanim jeho polickove vysky a sirky.
     *
     * @param  sirka  Novy pocet policek vodorovne
     * @param  vyska  Novy pocet policek svisle
     */
    public synchronized void setRozmer(int sirka, int vyska)
    {
        setKrokRozmer( krok, sirka, vyska );
    }


    /***************************************************************************
     * Nastavi rozmer platna zadanim jeho polickove vysky a sirky.
     *
     * @param  rozmer  Zadavany rozmer v poctu policek
     */
    public synchronized void setRozmer(Rozmer rozmer)
    {
        setRozmer( rozmer.sirka, rozmer.vyska );
    }


    /***************************************************************************
     * Vrati informaci o tom, je-li zobrazovana mrizka.
     *
     * @return Mrizka je zobrazovana = true, neni zobrazovana = false.
     */
    public synchronized boolean isMrizka()
    {
    	return mrizka;
    }


    /***************************************************************************
     * V zavislosti na hodnte parametru nastavi nebo potlaci
     * zobrazovani car mrizky.
     *
     * @param zobrazit  Jestli mrizku zobrazovat.
     */
    public synchronized void setMrizka( boolean zobrazit )
    {
        mrizka = zobrazit;
        pripravCary();
        prekresli();
    }


    /***************************************************************************
     * Poskytuje informaci o aktualni viditelnosti okna.
     *
     * @return Je-li okno viditelne, vraci <b>true</b>, jinak vraci <b>false</b>
     */
    public synchronized boolean isViditelne()
    {
        return okno.isVisible();
    }


    /***************************************************************************
     * V zavislosti na hodnte parametru nastavi nebo potlaci viditelnost platna.
     *
     * @param viditelne logicka hodnota pozadovane viditelnost (true=viditelne)
     */
    public synchronized void setViditelne(boolean viditelne)
    {
        boolean zmena = (isViditelne() != viditelne);
        if( zmena )
        {
            pozice = okno.getLocation();
            okno.setVisible(viditelne);
            if( viditelne )
            {
                //Pri vice obrazovkach po zviaditelneni blbne =>
                okno.setLocation(pozice);   //je treba znovu nastavit pozici
                okno.setAlwaysOnTop(true);
                okno.toFront();
                prekresli();
                okno.setAlwaysOnTop(false);
            }
        }
    }


    /***************************************************************************
     * Vrati aktualni barvu pozadi.
     *
     * @return  Nastavena barva pozadi
     */
    public synchronized Barva getBarvaPozadi()
    {
        return barvaPozadi;
    }


    /***************************************************************************
     * Nastavi pro platno barvu pozadi.
     *
     * @param  barva  Nastavovana barva pozadi
     */
    public synchronized void setBarvaPozadi(Barva barva)
    {
        barvaPozadi = barva;
        kreslitko.setPozadi( barvaPozadi );
        prekresli();
    }


    /***************************************************************************
     * Pomocna metoda pro ucely ladeni aby bylo mozno zkontrolovat,
     * ze na konci metody ma semafor stejnou hodnotu, jako mel na pocatku.
     *
     * @return  Stav vnitrniho semaforu: >0  - nebude se kreslit,<br>
     *                                   ==0 - kresli se,<br>
     *                                   <0  - chyba
     */
    public synchronized int getNekresli()
    {
        return nekreslit;
    }


    /***************************************************************************
     * Vrati aktualni nazev v titulkove liste okna platna.
     *
     * @return  Aktualni nazev okna
     */
    public String getNazev()
    {
        return okno.getTitle();
    }


    /***************************************************************************
     * Nastavi nazev v titulkove liste okna platna.
     *
     * @param nazev  Nastavovany nazev
     */
    public void setNazev( String nazev )
    {
        okno.setTitle( this.nazev = nazev );
    }


    /***************************************************************************
     * Vrati vodorovnou soradnici aplikacniho okna platna.
     *
     * @return Pozice leveho horniho rohu aplikacniho okna platna.
     */
    public Pozice getPozice()
    {
        return new Pozice( okno.getX(), okno.getY() );
    }


    /***************************************************************************
     * Nastavi pozici aplikacniho okna aktivniho platna na obrazovce.
     *
     * @param x  Vodorovna soradnice aplikacniho okna platna.
     * @param y  Svisla soradnice aplikacniho okna platna.
     */
    public void setPozice( int x, int y )
    {
        okno.setLocation( x,  y );
        pozice = new Point( x, y );
    }


    /***************************************************************************
     * Nastavi pozici aplikacniho okna aktivniho platna na obrazovce.
     *
     * @param pozice  Pozadovana pozice aplikacniho okna platna na obrazovce.
     */
    public void setPozice( Pozice pozice )
    {
        okno.setLocation( pozice.getX(), pozice.getY() );
    }


//    /***************************************************************************
//     * Vrati instanci tridy <code>Obrazek</code> zobrazujici zadany vyrez
//     * AktivnihoPlatna.
//     * @param x     Vodorovna pozice pozadovaneho vyrezu
//     * @param y     Svisla pozice pozadovaneho vyrezu
//     * @param sirka Sirka pozadovaneho vyrezu v bodech
//     * @param vyska Vyska pozadovaneho vyrezu v bodech
//     * @return Instance tridy <code>Obrazek</code> zobrazujici zadany vyrez
//     */
//    public Obrazek getObrazek( int x, int y, int sirka, int vyska )
//    {
//        BufferedImage bim = getBufferedImage( x, y, sirka, vyska );
//        return new Obrazek( 0, 0, bim );
//    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return getClass().getName() + "(krok=" + krok +
                ", sirka=" + sloupcu + ", vyska=" + radku +
                ", pozadi=" + barvaPozadi + ")";
    }


    /***************************************************************************
     * Vykresli vsechny elementy.
     */
    public synchronized void prekresli()
    {
        if( kreslim ) { //Prave prekresluji - volam neprimo sam sebe
            return;
        }
        if( (nekreslit == 0)  &&  isViditelne() )   //Mam kreslit a je proc
        {
            kreslim = true;
            kreslitko.vyplnRam( 0, 0, sirkaBodu, vyskaBodu,
                                barvaPozadi );
            if( mrizka  &&  (barvaCar != barvaPozadi) )
            {
                //Budeme kreslit mrizku -- bude pod obrazci
                for( int i=0;   i < sloupcu;   ) {
                    svisla[i++].nakresli(kreslitko);
                }
                for( int i=0;   i < radku;   ) {
                    vodorovna[i++].nakresli(kreslitko);
                }
            }
            for( IKresleny predmet : predmety ) {
                predmet.nakresli( kreslitko );
            }
            platno.repaint();       //Prekresli se aktualizovane platno
            kreslim = false;        //Uz nekreslim
        }
    }


    /***************************************************************************
     * Potlaci prekreslovani platna, presneji zvysi hladinu potlaceni
     * prekreslovani o jednicku. Navratu do stavu pred volanim teto metody
     * se dosahne zavolanim metody <code>vratKresli()</code>.</p>
     * <p>
     * Metody <code>nekresli()</code> a <code>vratKresli()</code>
     * se tak chovaji obdobne jako zavorky, mezi nimiz je vykreslovani
     * potlaceno.</p>
     */
    public synchronized void nekresli()
    {
        nekreslit++;
    }


    /***************************************************************************
     * Vrati prekreslovani do stavu pred poslednim volanim metody
     * <code>nekresli()</code>. Predchazelo-li proto vice volani metody
     * <code>nekresli()</code>, zacne se prekreslovat az po odpovidajim poctu
     * zavolani metody <code>vratKresli()</code>.
     *
     * @throws IllegalStateException
     *         Je-li metoda volana aniz by predchazelo odpovidajici volani
     *         <code>nekresli()</code>.
     */
    public synchronized void vratKresli()
    {
        if( nekreslit == 0 ) {
            throw new IllegalStateException(
                "Vraceni do stavu kresleni musi prechazet zakaz!" );
        }
        nekreslit--;
        if( nekreslit == 0 ) {
            prekresli();
        }
    }


    /***************************************************************************
     * Odstrani zadany obrazec ze seznamu malovanych.
     * Byl-li obrazec v seznamu, prekresli platno.
     *
     * @param obrazec  Odstranovany obrazec
     *
     * @return  true v pripade, kdyz obrazec v seznamu byl,
     *          false v pripade, kdyz nebylo co odstranovat
     */
    public synchronized boolean odstran(IKresleny obrazec)
    {
        boolean ret = predmety.remove(obrazec);
        if(ret) {
            prekresli();
        }
        return ret;
    }


    /***************************************************************************
     * Vycisti platno, tj. vyprazdni seznam malovanych
     * (odstrani z nej vsechny obrazce).
     */
    public synchronized void odstranVse()
    {
        //nekresli();
        ListIterator it = predmety.listIterator();
        while( it.hasNext() ) {
            it.next();
            it.remove();
        }
        prekresli();
        //vratKresli();
    }


    /***************************************************************************
     * Neni-li zadany obrazec v seznamu malovanych, prida jej na konec
     * (bude se kreslit jako posledni, tj. na vrchu.
     * Byl-li obrazec opravdu pridan, prekresli platno.
     * Objekty budou vzdy kresleny v poradi, v nemz byly pridany do spravy,
     * tj. v seznamu parametru zleva doprava
     * a drive zaregistrovane objekty pred objekty zaregistrovanymi pozdeji.
     *
     * @param  obrazec  Pridavane obrazce
     * @return  Pocet skutecne pridanych obrazcu
     */
    public synchronized int pridej(IKresleny... obrazec)
    {
        int pocet = 0;
        nekresli(); {
            for( IKresleny ik : obrazec )
            {
                if( ! predmety.contains(ik) ) {
                    predmety.add(ik);
                    pocet++;
                }
            }
        } vratKresli();
        return pocet;
    }


    /***************************************************************************
     * Prida obrazec do seznamu malovanych tak, aby byl kreslen
     * nad zadanym obrazcem.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  soucasny  Obrazec, ktery ma byt pri kresleni pod
     *                    pridavanym obrazcem
     * @param  pridany   Pridavany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejNad(IKresleny soucasny, IKresleny pridany )
    {
        boolean nebyl = ! predmety.remove(pridany);
        int kam = predmety.indexOf( soucasny );
        if( kam < 0 )
        {
            throw new IllegalArgumentException(
                "Referencni objekt neni na platne zobrazovan!" );
        }
        predmety.add( kam+1, pridany );
        prekresli();
        return nebyl;
    }


    /***************************************************************************
     * Prida obrazec do seznamu malovanych tak, aby byl kreslen
     * pod zadanym obrazcem.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  soucasny  Obrazec, ktery ma byt pri kresleni nad
     *                   pridavanym obrazcem
     * @param  pridany   Pridavany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejPod(IKresleny soucasny, IKresleny pridany)
    {
        boolean nebyl = ! predmety.remove(pridany);
        int kam = predmety.indexOf( soucasny );
        if( kam < 0 )
        {
            throw new IllegalArgumentException(
                "Referencni objekt neni na platne zobrazovan!" );
        }
        predmety.add( kam, pridany );
        prekresli();
        return nebyl;
    }


    /***************************************************************************
     * Prida obrazec do seznamu malovanych tak, aby byl kreslen
     * nad vsemi obrazci.
     * Pokud jiz v seznamu byl, jenom jej presune do pozadovane pozice.
     *
     * @param  pridany   Pridavany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejNavrch( IKresleny pridany)
    {
        boolean nebyl = ! predmety.remove(pridany);
        predmety.add( pridany );
        prekresli();
        return nebyl;
    }


    /***************************************************************************
     * Prida obrazec do seznamu malovanych tak, aby byl kreslen
     * pod zadanym obrazcem.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  pridany   Pridavany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejDospod( IKresleny pridany)
    {
        boolean nebyl = ! predmety.remove(pridany);
        predmety.add( 0, pridany );
        prekresli();
        return nebyl;
    }


    /***************************************************************************
     * Vrati poradi zadaneho prvku v seznamu kreslenych prvku.
     * Prvky se pritom kresli v rostoucim poradi, takze obrazec
     * s vetsim poradim je kreslen nad obrazcem s mensim poradim.
     * Neni-li zadany obrazec mezi kreslenymi, vrati -1.
     *
     * @param  obrazec  Objekt, na jehoz kreslici poradi se dotazujeme
     *
     * @return  Poradi obrazce; prvy kresleny obrazec ma poradi 0.
     *          Neni-li zadany obrazec mezi kreslenymi, vrati -1.
     */
    public synchronized int poradi(IKresleny obrazec)
    {
        return predmety.indexOf( obrazec );
    }


    /***************************************************************************
     * Vrati nemodifikovatelny seznam vsech spravovanych obrazku.
     *
     * @return  Pozadovany seznam
     */
    public List<IKresleny> seznamKreslenych()
    {
        return Collections.unmodifiableList( predmety );
    }


//    /***************************************************************************
//     * Prihlasi posluchace zmeny velikosti pole.
//     *
//     * @param  posluchac  Prihlasovany posluchac
//     * @return  Informace o tom, byl-li polsuchac doopravdy pridan -
//     *          false oznamuje, ze posluchac uz byl prihlasen
//     *          a nebylo jej proto treba pridavat.
//     */
//    public boolean prihlasPrizpusobivy( IPrizpusobivy posluchac )
//    {
//        return prizpusobivi.add( posluchac );
//    }


//    /***************************************************************************
//     * Odhlasi posluchace zmeny velikosti pole.
//     *
//     * @param  posluchac  Odhlasovany posluchac
//     * @return  Informace o tom, byl-li polsuchac doopravdy odebran -
//     *          false oznamuje, ze posluchac uz nebyl prihlasen
//     *          a nebylo jej proto treba odebirat.
//     */
//    public boolean odhlasPrizpusobivy( IPrizpusobivy posluchac )
//    {
//        return prizpusobivi.remove( posluchac );
//    }


//    /***************************************************************************
//     * Nastavi, zda se maji prihlasenym posluchacum hlasit zmeny
//     * velikosti kroku a vrati puvodni nastaveni.
//     *
//     * @param hlasit  Pozadovane nastaveni (true=hlasit, false=nehlasit).
//     * @return Puvodni nastaveni
//     */
//    public boolean hlasitZmenyRozmeru( boolean hlasit )
//    {
//        boolean ret = hlasitZmenyRozmeru;
//        hlasitZmenyRozmeru = hlasit;
//        return ret;
//    }


    /***************************************************************************
     * Prihlasi posluchace udalosti klavesnice.
     *
     * @param posluchac  Prihlasovany posluchac
     */
    public void prihlasKlavesnici( KeyListener posluchac )
    {
        okno.addKeyListener( posluchac );
    }


    /***************************************************************************
     * Odhlasi posluchace klavesnice.
     *
     * @param posluchac  Odhlasovany posluchac
     */
    public void odhlasKlavesnici( KeyListener posluchac )
    {
        okno.removeKeyListener( posluchac );
    }


    /***************************************************************************
     * Prihlasi posluchace udalosti mysi.
     *
     * @param posluchac  Prihlasovany posluchac
     */
    public void prihlasMys( MouseListener posluchac )
    {
        okno.addMouseListener( posluchac );
    }


    /***************************************************************************
     * Odhlasi posluchace mysi.
     *
     * @param posluchac  Odhlasovany posluchac
     */
    public void odhlasMys( MouseListener posluchac )
    {
        okno.removeMouseListener( posluchac );
    }


//    /***************************************************************************
//     * Ulozi obraz aktivniho platna do zadaneho souboru
//     * @param soubor Soubor, do nejz se ma obraz platna ulozit
//     */
//    public void ulozJakoObrazek( File soubor )
//    {
//        BufferedImage bim = getBufferedImage();
//        try {
//            ImageIO.write( bim, "PNG", soubor );
//        } catch( IOException exc )  {
//            throw new RuntimeException(
//            	"\nObraz aktivniho platna se nepodarilo ulozit do souboru " +
//                soubor,  exc );
//        }
//    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

//    /***************************************************************************
//     * Vrati obrazek na aktivnim platne.
//     * @return Obsah platna jako obrazek
//     */
//    private BufferedImage getBufferedImage()
//    {
//        if( obrazPlatna instanceof BufferedImage ) {
//            return (BufferedImage) obrazPlatna;         //==========>
//        }
//        else {
//            return getBufferedImage(0, 0, sirkaBodu, vyskaBodu);
//        }
//    }


//    /***************************************************************************
//     * Vrati obrazek vyrezu na aktivnim platne.
//     * @return Vyrez obsahu platna jako obrazek
//     */
//    private BufferedImage getBufferedImage( int x, int y, int sirka, int vyska )
//    {
//        BufferedImage ret = new BufferedImage( sirka, vyska,
//                                               BufferedImage.TYPE_INT_ARGB );
//        Graphics2D g2d = (Graphics2D)ret.getGraphics();
//        g2d.drawImage( obrazPlatna, -x, -y, Kreslitko.OBRAZOR );
//        return ret;
//    }


    /***************************************************************************
     * Inicializuje nektere parametry z konfiguracniho souboru.
     * Tento soubor je umisten v domovskem adresari uzivatele
     * ve slozce {@code .rup} v souboru {@code bluej.properties}.
     */
    private void inicializace() {
        Properties sysProp = System.getProperties();
        String     userDir = sysProp.getProperty("user.home");
        File       rupFile = new File( userDir, ".rup/bluej.properties");
        Properties rupProp = new Properties();
        try {
            Reader reader = new FileReader(rupFile);
            rupProp.load(reader);
            reader.close();
            String sx = rupProp.getProperty("canvas.x");
            String sy = rupProp.getProperty("canvas.y");
            int x = Integer.parseInt(rupProp.getProperty("canvas.x"));
            int y = Integer.parseInt(rupProp.getProperty("canvas.y"));
            pozice = new Point( x, y );
        }catch( Exception e )  {
            pozice = new Point( 0, 0 );
        }
        okno.setLocation(pozice);
    }


    /***************************************************************************
     * Pripravi cary vyznacujici jednotliva pole aktivniho platna.
     * Pokud se cary kreslit nemaji, vyprazdni odkazy na ne.
     */
    private void pripravCary()
    {
        if( mrizka  &&  (krok > 1) )
        {
            if( (svisla == null)  ||  (svisla.length != sloupcu) ) {
                svisla = new Cara[sloupcu];
            }
            if( (vodorovna == null)  ||  (vodorovna.length != radku) ) {
                vodorovna = new Cara[radku];
            }
            for( int i=0, x=krok;   i < sloupcu;      i++, x+=krok ) {
                svisla[i] = new Cara(x, 0, x, vyskaBodu, barvaCar);
            }
            for( int i=0, y=krok;   i < radku;   i++, y+=krok ) {
                vodorovna[i] = new Cara(0, y, sirkaBodu, y, barvaCar);
            }
        }
        else
        {
            //Uvolneni doposud pouzivanych instanci
            svisla    = null;
            vodorovna = null;
            mrizka    = false;
        }
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

//    private static void w(){try{
//        Thread.sleep(10);}catch(InterruptedException e){}
//    }

//    /***************************************************************************
//     * Testovaci metoda
//     */
//    public static void test() {
//        SP.pridej( new Obdelnik() );                    w();
//        SP.pridej( new Elipsa(), new Trojuhelnik() );   w();
//        IO.zprava("Vychozi obrazek - budu vyjimat vyrez");
//        Obrazek obr = SP.getObrazek( 50, 0, 75, 75 );   w();
//        SP.pridej( obr );                               w();
//        SP.setBarvaPozadi( Barva.CERNA );               w();
//        IO.zprava( "Obrazek pridany?" );
//        obr.setPozice( 100, 50 );                       w();
//        IO.zprava( "Posunuty?" );
//        obr.setRozmer(150, 150);                        w();
//        IO.zprava( "Zvetseny?" );
//        SP.setKrokRozmer( 50, 5, 2 );
////        SP.setKrokRozmer( 1, 50, 50 );
////        SP.ulozJakoObrazek( new File( "D:/SMAZAT.PNG" ) );
//
//        System.exit( 0 );
//    }
//    /** @param args Paremtry prikazoveho radku - nepouzite  */
//    public static void main(String[] args) { test(); } /**/
}

