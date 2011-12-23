import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;
 
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/*******************************************************************************
 * Trida {@code Platno} slouzi k jednoduchemu kresleni na virtualni platno.
 * <p>
 * Trida neposkytuje verejny konstruktor, protoze chce, aby jeji instance
 * byla jedinacek, tj. aby se vsechno kreslilo na jedno a to same platno.
 * Jedinym zpusobem, jak ziskat odkaz na instanci tridy Platno,
 * je volani staticke metody {@link getInstance()}.</p>.
 * <p>
 * Aby bylo mozno na platno obycejne kreslit a nebylo nutno kreslene objekty
 * prihlasovat, odmazane casti obrazcu se automaticky neobnovuji.
 * Je-li proto pri smazani nektereho obrazce odmazana cast jineho obrazce,
 * je treba prislusny obrazec explicitne prekreslit.</p>
 *
 * @author   Rudolf PECINOVSKY
 * @version  3.00.002
 */
public final class Platno
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Titulek v zahlavi okna platna. */
    private static final String TITULEK  = "Jednoduche platno";
    
    /** Pocatecni sirka platna v bodech. */
    private static final int SIRKA_0 = 300;

    /** Pocatecni vyska platna v bodech. */
    private static final int VYSKA_0 = 300;

    /** Pocatecni barva pozadi platna. */
    private static final Barva POZADI_0 = Barva.KREMOVA;



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Jedina instance tridy Platno. */
    private static Platno jedinacek = new Platno();     //Jedina instance 



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    //Z venku neovlivnitelne Atributy pro zobrazeni platna v aplikacnim okne

        /** Aplikacni okno animacniho platna. */
        private JFrame okno;

        /** Instance lokalni tridy, ktera je zrizena proto, aby odstinila
         *  metody sveho rodice JPanel. */
        private JPanel vlastniPlatno;

        /** Vse se kresli na obraz - ten se snadneji prekresli. */
        private Image obrazPlatna;

        /** Kreslitko ziskane od obrazu platna, na nejz se vlastne kresli. */
        private Graphics2D kreslitko;


    //Primo ovlivnitelne atributy
        private Barva barvaPozadi;
        private int sirka;
        private int vyska;

        /** Pozice platna na obrazovace - pri pouzivani vice obrazovek
         *  je obcas treba ji po zviditelneni obnovit. */
        Point pozice = new Point(0, 0);



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Smaze platno, presneji smaze vsechny obrazce na platne.
     * Tato metoda by mela byr definovana jako metodoa instance,
     * protoze je instance jedinacek,
     * byla metoda pro snazsi dostupnost definovana jako metoda tridy.
     * Jinak by totiz bylo potreba vytvorit pred smazanim platna jeho instanci.
     */
    public static void smazPlatno()
    {
        jedinacek.smaz();
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Jedina metoda umoznujici ziskat odkaz na instanci platna.
     * Vraci vsak pokazde odkaz na stejnou instanci, protoze tato instance
     * je jedinacek. Pokud instance pri volani metody jeste neexistuje,
     * metoda instanci vytvori.
     *
     * @return Odkaz na instanci tridy Platno.
     */
    public static Platno getInstance()
    {
        jedinacek.setViditelne(true);
        return jedinacek;
    }


    /***************************************************************************
     * Implicitni (a jediny) konstruktor - je volan pouze jednou.
     */
    private Platno()
    {
        inicializace();
        okno  = new JFrame();          //Vytvori nove aplikacni okno
        okno.setLocation( pozice );
        okno.setTitle(TITULEK);
        
        //Zavrenim okna se zavre cela aplikace
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okno.setResizable( false );    //Neni mozne menit rozmer mysi
        vlastniPlatno = new JPanel()
        {   /** Povinne prekryvana abstraktni metoda tridy JPanel. */
            @Override
            public void paint(Graphics g) {
                g.drawImage(obrazPlatna, 0, 0, null);
            }
        };
        okno.setContentPane(vlastniPlatno);
        barvaPozadi = POZADI_0;
        setRozmer(SIRKA_0, VYSKA_0);        //Pripravi a vykresli prazdne platno
        IO.oknaNa(pozice.x, pozice.y + okno.getSize().height);
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Poskytuje informaci o aktualni viditelnosti okna.
     *
     * @return Je-li okno viditelne, vraci <b>true</b>, jinak vraci <b>false</b>
     */
    public boolean isViditelne()
    {
        return okno.isVisible();
    }


    /***************************************************************************
     * Nastavi viditelnost platna.
     *
     * @param viditelne {@code true} ma-li byt platno viditelne, 
     *                  {@code false} ma-li naopak prestat byt viditelne
     */
    public void setViditelne(boolean viditelne)
    {
        boolean zmena = (isViditelne() != viditelne);
        if( zmena ) {
            pozice = okno.getLocation();
            okno.setVisible(viditelne);
            if( viditelne )
            {
                //Pri vice obrazovkach po zviaditelneni blbne =>
                okno.setLocation(pozice);   //je treba znovu nastavit pozici
                okno.setAlwaysOnTop(true);
                okno.toFront();
                okno.setAlwaysOnTop(false);
            }
        }
    }


    /***************************************************************************
     * Vrati aktualni barvu pozadi.
     *
     * @return   Nastavena barva pozadi
     */
    public Barva getBarvaPozadi()
    {
        return barvaPozadi;
    }


    /***************************************************************************
     * Nastavi pro platno barvu pozadi.
     *
     * @param barva  Nastavovana barva pozadi
     */
    public void setBarvaPozadi(Barva barva)
    {
        barvaPozadi = barva;
        kreslitko.setBackground( barvaPozadi.getColor() );
        smaz();
    }


    /***************************************************************************
     * Nastavi pro platno barvu popredi.
     *
     * @param  barva  Nastavovana barva popredi
     */
    public void setBarvaPopredi(Barva barva)
    {
        kreslitko.setColor( barva.getColor() );
    }


    /***************************************************************************
     * Vrati sirku platna.
     *
     * @return  Aktualni sirka platna v bodech
     */
    public int getSirka()
    {
        return sirka;
    }


    /***************************************************************************
     * Vrati vysku platna.
     *
     * @return  Aktualni vyska platna v bodech
     */
    public int getVyska()
    {
        return vyska;
    }


    /***************************************************************************
     * Nastavi novy rozmer platna zadanim jeho vysky a sirky.
     *
     * @param  sirka  Nova sirka platna v bodech
     * @param  vyska  Nova vyska platna v bodech
     */
    public void setRozmer(int sirka, int vyska)
    {
        boolean upravit;
        do
        {
            this.sirka = sirka;
            this.vyska = vyska;
            okno.setResizable(true);
            vlastniPlatno.setPreferredSize( new Dimension(sirka, vyska) );
            okno.setMaximizedBounds( new Rectangle (sirka, vyska) );
            okno.pack();
            java.awt.Dimension dim = okno.getSize( null );
            java.awt.Insets    ins = okno.getInsets();
//            IO.zprava(
//                   "Nastavuju: sirka=" + sirka + ", vyska=" + vyska +
//                 "\nMam: width=" + dim.width + ", height=" + dim.height +
//                 "\nleft=" + ins.left + ", right=" + ins.right +
//                 "\n top=" + ins.top + ", bottom=" + ins.bottom );
            upravit = false;
            if( sirka < (dim.width - ins.left - ins.right) )
            {
                sirka = dim.width - ins.left - ins.right + 2;
                upravit = true;
            }
            if( vyska < (dim.height - ins.top - ins.bottom) )
            {
                vyska = dim.height - ins.top - ins.bottom;
                upravit = true;
            }
        }while( upravit );
        obrazPlatna = vlastniPlatno.createImage(sirka+2, vyska+2);
        kreslitko = (Graphics2D)obrazPlatna.getGraphics();
        kreslitko.setBackground( barvaPozadi.getColor() );
        okno.setResizable(false);
        setViditelne(true);
        pripravObrazek();
        smaz();
    }



//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return this.getClass().getName() +
            "(" + sirka + "" + vyska +
            " bodu, barvaPozadi=" + barvaPozadi + ")";
    }


    /***************************************************************************
     * Nakresli zadany obrazec a vybarvi jej barvou popredi platna.
     *
     * @param  obrazec  Kresleny obrazec
     */
    public void zapln(Shape obrazec)
    {
        kreslitko.fill(obrazec);
        vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Smaze platno, presneji smaze vsechny obrazce na platne.
     */
    public void smaz()
    {
        smaz( new Rectangle2D.Double(0, 0, sirka, vyska) );
    }


    /***************************************************************************
     * Smaze zadany obrazec na platne; obrazec vsak stale existuje,
     * jenom neni videt. Smaze se totiz tak, ze se nakresli barvou pozadi.
     *
     * @param  obrazec   Obrazec, ktery ma byt smazan
     */
    public void smaz(Shape obrazec)
    {
        Color original = kreslitko.getColor();
        kreslitko.setColor(barvaPozadi.getColor());
        kreslitko.fill(obrazec);       //Smaze jej vyplnenim barvou pozadi
        kreslitko.setColor(original);
        vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Vypise na platno text aktualnim pismem a aktualni barvou popredi.
     *
     * @param text   Zobrazovany text
     * @param x      x-ova souradnice textu
     * @param y      y-ova souradnice textu
     * @param barva  Barva, kterou se zadany text vypise
     */
    public void kresliString(String text, int x, int y, Barva barva)
    {
        setBarvaPopredi(barva);
        kreslitko.drawString(text, x, y);
        vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Nakresli na platno usecku se zadanymi krajnimi body.
     * Usedku vykresli aktualni barvou popredi.
     *
     * @param  x1    x-ova souradnice pocatku
     * @param  y1    y-ova souradnice pocatku
     * @param  x2    x-ova souradnice konce
     * @param  y2    x-ova souradnice konce
     * @param  barva Barva usecky
     */
    public void kresliCaru(int x1, int y1, int x2, int y2, Barva barva)
    {
        setBarvaPopredi(barva);
        kreslitko.drawLine(x1, y1, x2, y2);
        vlastniPlatno.repaint();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

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
    }


    /***************************************************************************
     * Pripravi obrazek, do nejz se budou vsechny tvary kreslit.
     */
    private void pripravObrazek()
    {
        obrazPlatna = vlastniPlatno.createImage(sirka, vyska);
        kreslitko = (Graphics2D)obrazPlatna.getGraphics();
        kreslitko.setColor(barvaPozadi.getColor());
        kreslitko.fillRect(0, 0, sirka, vyska);
        kreslitko.setColor(Color.black);
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTOVACI TRIDY A METODY ==================================================
}

