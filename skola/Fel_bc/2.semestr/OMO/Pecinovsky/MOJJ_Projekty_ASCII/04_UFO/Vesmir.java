import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/*******************************************************************************
 * Trida Vesmir slouzi jako zdroj pomocnych trid pro hru UFO.
 * Sama o sobe pak predstavuje ekvivalent platna.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, cerven 2004
 */
public class Vesmir
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni sirka platna v bodech. */
    public static final int SIRKA_0 = 300;

    /** Pocatecni vyska platna v bodech. */
    public static final int VYSKA_0 = 300;

    /** Pocatecni barva pozadi platna. */
    public static final Barva BARVA_POZADI = Barva.CERNA;

    /** Titulek v zahlavi okna platna. */
    private static final String TITULEK  = "Vesmir s UFO";

    /** Jedina instance tridy Platno. */
    public static final Vesmir V = new Vesmir();



//== PROMENNE ATRIBUTY TRIDY ===================================================
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

//        private Image[] obraz   = new Image[2];
//        private int     aktivni = 0;

        /** Kreslitko ziskane od obrazu platna, na nejz se vlastne kresli. */
        private Graphics2D kreslitko;


    //Primo ovlivnitelne atributy
        private int sirka;
        private int vyska;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Jedina metoda umoznujici ziskat odkaz na instanci platna.
     * Vraci vsak pokazde odkaz na stejnou instanci, protoze tato instance
     * je V. Pokud instance pri volani metody jeste neexistuje,
     * metoda instanci vytvori.
     *
     * @return Odkaz na instanci tridy Platno.
     */
    public static Vesmir getVesmir()
    {
        V.okno.setVisible(true);
        return V;
    }


    /***************************************************************************
     * Implicitni (a jediny) konstruktor - je volan pouze jednou.
     */
    private Vesmir()
    {
        okno  = new JFrame();          //Vytvori nove aplikacni okno
        okno.setTitle(TITULEK);
        okno.setResizable( false );    //Neni mozne menit rozmer mysi
        okno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        okno.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        vlastniPlatno = new JPanel()
        {
            /** Povinne prekryvana abstraktni metoda tridy JPanel. */
            @Override
            public void paint(Graphics g)
            {
                g.drawImage(obrazPlatna, 0, 0, null);
            }

        };
        okno.setContentPane(vlastniPlatno);
        setRozmer(SIRKA_0, VYSKA_0);        //Pripravi a vykresli prazdne platno
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Nastavi viditelnost okna s vesmirem.
     */
    public void setViditelny()
    {
        okno.setVisible( true );
        okno.toFront();
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
        kreslitko.setBackground( BARVA_POZADI.getColor() );
        okno.setResizable(false);
        okno.setVisible(true);
        okno.toFront();
        pripravObrazek();
        smaz();
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vypise text aktualnim pismem a aktualni barvou pozadi.
     *
     * @param  text   Zobrazovany text
     * @param  x      x-ova souradnice textu
     * @param  y      y-ova souradnice textu
     */
    public void kresliString(String text, int x, int y)
    {
        kreslitko.setColor( BARVA_POZADI.getColor() );
        kreslitko.drawString( text, x, y );
        //vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Nakresli zadany obrazec a vybarvi jej barvou popredi platna.
     *
     * @param  obrazec  Kresleny obrazec
     */
    public void zapln( Shape obrazec )
    {
        kreslitko.fill( obrazec );
        //vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Prekresli platno.
     */
    public void prekresli()
    {
        vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Smaze platno, presneji smaze vsechny obrazce na platne.
     * Tato metoda by mela byr definovana jako metodoa instance,
     * protoze je instance jedinacek,
     * byla metoda pro snazsi dostupnost definovana jako metoda tridy.
     * Jinak by totiz bylo potreba vytvorit pred smazanim platna jeho instanci.
     */
    public void smaz()
    {
        if( V == null ) {
            return;
        }
        Color original = kreslitko.getColor();
        kreslitko.setColor( BARVA_POZADI.getColor() );
        Shape sh = new Rectangle2D.Double(0, 0, V.sirka, V.vyska);
        kreslitko.fill( sh );
        kreslitko.setColor( original );
        //vlastniPlatno.repaint();
    }


    /***************************************************************************
     * Prihlasi posluchace udalosti klavesnice.
     * 
     * @param posluchac Prihlasovany posluchac
     */
    public void prihlasKlavesnici( KeyListener posluchac )
    {
        okno.addKeyListener( posluchac );
    }



    /***************************************************************************
     * Odhlasi posluchace klavesnice
     * 
     * @param posluchac Odhlasovany posluchac
     */
    public void odhlasKlavesnici( KeyListener posluchac )
    {
        okno.removeKeyListener( posluchac );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Nastavi viditelnost platna.
     * Pri nastaveni viditelneho platna je presune do popredi.
     * Lze ji proto pouzit i pro presun jiz viditelneho platna do popredi.
     *
     * @param visible logicka hodnota pozadovane viditelnost (true=viditelne)
     */
    private void pripravObrazek()
    {
        obrazPlatna = vlastniPlatno.createImage(sirka, vyska);
        kreslitko = (Graphics2D)obrazPlatna.getGraphics();
        kreslitko.setColor(BARVA_POZADI.getColor());
        kreslitko.fillRect(0, 0, sirka, vyska);
        kreslitko.setColor(Color.black);
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

