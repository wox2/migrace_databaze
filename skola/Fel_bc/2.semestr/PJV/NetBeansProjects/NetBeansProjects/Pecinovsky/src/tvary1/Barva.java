package tvary1;

import java.awt.Color;

import java.text.Collator;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/*******************************************************************************
 *  Trida {@code Barva} definuje skupinu zakladnich barev pro pouziti
 *  pri kresleni tvaru pred zavedenim balicku.
 *  Neni definovana jako vyctovy typ, aby si uzivatel mohl libovolne pridavat
 *  vlastni barvy.
 *
 * @author   Rudolf PECINOVSKY
 * @version  3.00.001
 */
public class Barva
{
    /** Pocet pojmenovanych barev se pri konstrukci nasledujicich instanci
     *  nacita, a proto musi byt deklarovan pred nimi. */
    private static int pocet = 0;


//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Mapa vsech doposud vytvorenych barev klicovana jejich nazvy. */
    private static final Map<String,Barva> NAZVY =
                                             new LinkedHashMap<String,Barva>();
    /** Mapa vsech doposud vytvorenych barev klicovana jejich nazvy
     *  s odstranenou diakritikou. */
    private static final Map<String,Barva> NAZVY_BHC =
                                             new LinkedHashMap<String,Barva>();
    private static final Map<Color,Barva>  COLOR =
                                             new LinkedHashMap<Color,Barva>();
    private static final List<Barva> BARVY = new ArrayList<Barva>( 32 );
    

    /** Cerna = RGBA( 0, 0, 0, 255); */         public static final Barva
    CERNA   = new Barva( Color.BLACK,           "cerna"   );

    /** Modra = RGBA( 0, 0, 255, 255); */       public static final Barva
    MODRA   = new Barva( Color.BLUE,            "modra"   );

    /** Cervena = RGBA( 255, 0, 0, 255); */     public static final Barva
    CERVENA = new Barva( Color.RED,             "cervena" );

    /** Fialova = RGBA( 255, 0, 255, 255); */   public static final Barva
    FIALOVA = new Barva( Color.MAGENTA,         "fialova" );

    /** Zelena = RGBA( 0, 255, 0, 255); */      public static final Barva
    ZELENA  = new Barva( Color.GREEN,           "zelena"  );

    /** Azurova = RGBA( 0, 255, 255, 255); <br> Lze pro ni pouzit i 
     *  textovy nazev "blankytna". */           public static final Barva
    AZUROVA = new Barva( Color.CYAN,            "azurova" );

    /** Zluta = RGBA( 255, 255, 0, 255); */     public static final Barva
    ZLUTA   = new Barva( Color.YELLOW,          "zluta"   );

    /** Bila = RGBA( 255, 255, 255, 255); */    public static final Barva
    BILA    = new Barva( Color.WHITE,           "bila"    );

    /** Svetleseda = RGBA( 192,192,192,255 ); */public static final Barva
    SVETLESEDA = new Barva( Color.LIGHT_GRAY,   "svetleseda");  //128 = 0x80

    /** Seda = RGBA( 128, 128, 128, 255); */    public static final Barva
    SEDA    = new Barva( Color.GRAY,            "seda"    );

    /** Tmavoseda = RGBA(  64,  64,  64, 255);*/public static final Barva
    TMAVOSEDA = new Barva( Color.DARK_GRAY,     "tmavoseda" );  //64 = 0x40

    /** Cerna = RGBA( 255, 175, 175, 255); */   public static final Barva
    RUZOVA  = new Barva( Color.PINK,            "ruzova"  );    //175 = 0xAF

    /** Oranzova = RGBA( 255, 200, 0, 255); */  public static final Barva
    ORANZOVA= new Barva( Color.ORANGE,          "oranzova");

    //=== Barvy bez ekvivalentnich konstant in java.awt.Color
        
    /** Stribrna = RGBA( 216, 216, 216, 255); */public static final Barva      
    STRIBRNA = new Barva( 0xD8, 0xD8, 0xD8, 0xFF, "stribrna" );
        
    /** Zlata = RGBA( 255, 224,  0, 255); */    public static final Barva      
    ZLATA = new Barva( 0xFF, 0xE0, 0x00, 0xFF,  "zlata" );

    /** Cihlova = RGBA( 255, 102, 0, 255); */   public static final Barva
    CIHLOVA = new Barva( 0xFF, 0x66, 0, 0xFF,   "cihlova" );

    /** Hneda = RGBA( 153, 51, 0, 255); */      public static final Barva
    HNEDA = new Barva( 0x99, 0x33, 0x00, 0xFF,  "hneda"   );

    /** Kremova = RGBA( 255, 255, 204, 255); */ public static final Barva
    KREMOVA = new Barva( 0xFF, 0xFF, 0xCC, 0xFF,"kremova" );

    /** Khaki = RGBA( 153, 153, 0, 255); */     public static final Barva
    KHAKI = new Barva( 0x99, 0x99, 0x00, 0xFF,  "khaki"   );

    /** Ocelova = RGBA( 0, 153, 204, 255); */   public static final Barva
    OCELOVA = new Barva( 0x00, 0x99, 0xCC, 0xFF,"ocelova" );

    /** Okrova = RGBA( 255, 153, 0, 255); */    public static final Barva
    OKROVA = new Barva( 0xFF, 0x99, 0x00, 0xFF, "okrova"   );

    //=== Prusvitne barvy

    /** Mlecna=RGBA( 255, 255, 255, 128 ) - polovicne prusvitna bila. */
    public static final Barva
    MLECNA  = new Barva( 0xFF, 0xFF, 0xFF, 0x80, "mlecna");

    /** Kourova = RGBA( 128, 128, 128, 128 ) - polovicne prusvitna seda. */
    public static final Barva
    KOUROVA = new Barva( 0x80, 0x80, 0x80, 0x80, "kourova");

    /** Zadna = RGBA( 0,0,0,0) - PRUHLEDNA! */   public static final Barva
    ZADNA  = new Barva( 0, 0, 0, 0, "zadna");

    //Alternativni nazvy nekterych barev
    static
    {
        AZUROVA .pridejNazev( "blankytna" );
    }



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Priznak velikosti pismen, jimiz se vypisuji nazvy barev. */
    private static boolean velkymi = false;
    
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final String nazev;     //Nazev barvy zadavany konstruktoru
    private final Color  color;     //Barva ze standardni knihovny
    private final int    index = pocet++;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================

    /***************************************************************************
     * Vrati vektor doposud definovanych barev.
     *
     * @return  Vektor doposud definovanych barev
     */
    public static Barva[] getZnameBarvy()
    {
        return BARVY.toArray( new Barva[BARVY.size()] );
    }


    /***************************************************************************
     * Vrati vektor retezcu s dopsud definovanymi nazvy barev. 
     * Nazvu barev je vice nez definovanych barev, protoze barvy mohou mit
     * vice nazvu (a nektere jich opravdu maji nekolik).
     * 
     * @return  Vektor retezcu s dopsud definovanymi nazvy barev
     */
    public static String[] getZnameNazvy()
    {
        String[] nazvy = NAZVY.keySet().toArray( new String[ NAZVY.size() ] );
        Arrays.sort(nazvy, Collator.getInstance());
        if( velkymi ) {
            for (int i = 0;   i < nazvy.length;   i++) {
                nazvy[i] = nazvy[i].toUpperCase();
            }
        }
        return nazvy;
    }

    
    /***************************************************************************
     * Nastavi, zda se budou nazvy barev vypisovat velkymi pismeny.
     * 
     * @param velkymi {@code true} maji-li se nazvy vypisovat velkymi pismeny,
     *                jinak {@code false}
     * @return Puvdoni nastaveni
     */
    public static boolean setVelkymi( boolean velkymi ) 
    {
        boolean puvodni = Barva.velkymi;
        Barva.velkymi = velkymi;
        return puvodni;
    }
    

//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Otevre dialogove okno, v nemz vypise vsechny doposud definovane 
     * nazvy barev. Jmena jsou lexikograficky serazena.
     */
    public static void vypisZnameNazvy()
    {
        final int MAX_V_RADKU = 64;
        String[] nazvy = getZnameNazvy();
        StringBuilder sb = new StringBuilder();
        for( int i=0, vRadku=0;   i < nazvy.length;   i++ ) {
            String text = nazvy[i];
            int textLength = text.length();
            if( (vRadku + textLength)  >=  MAX_V_RADKU ) {
                sb.append('\n');
                vRadku = 0;
            }
            sb.append(text);
            vRadku += textLength + 2;
            if( i < nazvy.length ) {
                sb.append(", ");
            }
        }
//        System.out.println("Barvy:\n" + sb);
        IO.zprava(sb);
    }


    /***************************************************************************
     * Vrati kolekci doposud definovanych barev.
     *
     * @return  Vektor doposud definovanych barev
     */
    public static Collection<Barva> znameBarvy()
    {
        return Collections.unmodifiableList( BARVY );
    }


    /***************************************************************************
     * Vrati kolekci retezcu doposud definovanych nazvu barev.      
     * Nazvu barev je vice nez definovanych barev, protoze barvy mohou mit
     * vice nazvu (a nektere jich opravdu maji nekolik).
     *
     * @return  Vektor retezcu s dopsud definovanymi nazvy barev
     */
    public static Collection<String> znameNazvy()
    {
        return Arrays.asList( getZnameNazvy() );
    }


//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci barvy se zadanou velikosti barevnych slozek
     * a hladinou pruhlednosti nastavovanou v kanale alfa
     * a se zadanym ceskym nazvem a nazvem bez diakritiky.
     *
     * @param red       Velikost cervene slozky
     * @param green     Velikost zelene slozky
     * @param blue      Velikost modre slozky
     * @param alpha     Hladina pruhlednosti: 0=pruhledna, 255=nepruhledna
     * @param nazev     Nazev vytvorene barvy
     * @param nazevBHC  Nazev bez hacku a carek
     */
    private Barva( int red, int green, int blue, int alpha, String nazev )
    {
        this( new Color(red, green, blue, alpha),  nazev );
    }
    

    /***************************************************************************
     * Vytvori barvu ekvivalentni zadane instanci tridy 
     * {@link java.awt.Color} se zadanym ceskym nazvem.
     *
     * @param c      Instance tridy {@link java.awt.Color} pozadovane barvy
     * @param nazev  Nazev vytvarene barvy; {@code null} oznaucje, 
     *               ze se ma pro barvu vytvorit genericky nazev
     */
    private Barva( Color c, String nazev )
    {
        this.color = c;
        this.nazev = nazev.toLowerCase();
        
        if( NAZVY.containsKey( nazev )   ||
            COLOR.containsKey( c )  )
        {
            throw new IllegalArgumentException(
                "\nInterni chyba - barva " + getNazev() + " a/nebo " +
                getCharakteristikaDec() + " jiz existuji" );
        }

        NAZVY.put( nazev, this );
        COLOR.put( color, this );
        BARVY.add( this );
        
        pridejNazevBezDiakritiky();
    }

//        if( (nazev == "")  ||  (nazev == null)  )
//            throw new IllegalArgumentException(
//                "Nazev barvy musi byt zadan" );
//        nazev = nazev.toLowerCase();
//        color = new Color( red, green, blue, alpha );
//        if( NAZVY.containsKey( nazev )  ||  
//            COLOR.containsKey( color )  )
//        {
//            throw new IllegalArgumentException(
//                "\nBarvu nelze vytvorit - barva " + getCharakteristikaDec() + 
//                " jiz existuje" );
//        }
//        BARVY.add( index, this );
//        this.nazev = nazev;
//        NAZVY.put( nazev, this );
//        COLOR.put( color, this );
//        String bhc = odhackuj( nazev );
//        if( ! nazev.equals(bhc) )
//            NAZVY.put( bhc, this );
//    }


    /***************************************************************************
     * Prevede nazev barvy na prislusny objekt typu Barva.
     *
     * @param nazevBarvy  Nazev pozadovane barvy -- seznam znamych nazvu
     *                    je mozno ziskat zavolanim metody getZnameNazvy()
     *
     * @return Instance tridy Barva reprezentujici zadanou barvu
     *
     * @throws IllegalArgumentException neni-li barva (nazev) mezi znamymi
     */
    public static Barva getBarva( String nazevBarvy )
    {
        Barva barva = NAZVY.get( nazevBarvy.toLowerCase() );
        if( barva != null ) {
            return barva;
        } 
        else {
            throw new IllegalArgumentException("Takto pojmenovanou barvu neznam.");
        }
    }


    /***************************************************************************
     * Vytvori instanci barvy se zadanou velikosti barevnych slozeka.
     *
     * @param red   Velikost cervene slozky
     * @param green Velikost zelene slozky
     * @param blue  Velikost modre slozky
     * @return Barva se zadanymi velikostmi jednotlivych slozek
     */
    public static Barva getBarva( int red, int green, int blue )
    {
        return getBarva( red, green, blue, 0xFF );
    }


    /***************************************************************************
     * Vytvori instanci nepojmenovane barvy se zadanou velikosti barevnych
     * slozeka hladinou pruhlednosti nastavovanou v kanale alfa.
     *
     * @param red    Velikost cervene slozky
     * @param green  Velikost zelene slozky
     * @param blue   Velikost modre slozky
     * @param alpha  Hladina pruhlednosti: 0=pruhledna, 255=nepruhledna
     * @return Barva se zadanymi velikostmi jednotlivych slozek
     */
    public static Barva getBarva( int red, int green, int blue, int alpha )
    {
        Color color = new Color( red, green, blue, alpha );
        Barva barva = COLOR.get( color );
        if( barva != null ) {
            return barva;
        }
        String nazev = "Barva(r=" + red + ",g=" + green +
                       ",b=" + blue + ",a=" + alpha + ")" ;
        return getBarva( red, green, blue, alpha, nazev );
    }


    /***************************************************************************
     * Existuje-li zadana barva mezi znamymi, vrati ji; v opacnem pripade
     * vytvori novou barvu se zadanymi parametry a vrati odkaz na ni.
     *
     * @param red       Velikost cervene slozky
     * @param green     Velikost zelene slozky
     * @param blue      Velikost modre slozky
     * @param nazev     Nazev vytvorene barvy
     *
     * @return Barva se zadanym nazvem a velikostmi jednotlivych slozek
     * @throws IllegalArgumentException ma-li nektere ze znamych barev nektery
     *         ze zadanych nazvu a pritom ma jine nastaveni barevnych slozek
     *         nebo ma jiny druhy nazev.
     */
    public static Barva getBarva( int red, int green, int blue, String nazev )
    {
        return getBarva( red, green, blue, 0xFF, nazev );
    }


    /***************************************************************************
     * Existuje-li zadana barva mezi znamymi, vrati ji; v opacnem pripade
     * vytvori novou barvu se zadanymi parametry a vrati odkaz na ni.
     *
     * @param red       Velikost cervene slozky
     * @param green     Velikost zelene slozky
     * @param blue      Velikost modre slozky
     * @param alpha     Hladina pruhlednosti: 0=pruhledna, 255=nepruhledna
     * @param nazev     Nazev vytvorene barvy
     *
     * @return Instance tridy Barva reprezentujici zadanou barvu.
     *
     * @throws IllegalArgumentException ma-li nektere ze znamych barev nektery
     *         ze zadanych nazvu a pritom ma jine nastaveni barevnych slozek
     *         nebo ma jiny druhy nazev.
     */
    public static Barva getBarva( int red, int green, int blue, int alpha,
                                  String nazev )
    {
        nazev = nazev.trim().toLowerCase();
        if( (nazev == null)  ||  nazev.equals("") )  {
            throw new IllegalArgumentException(
                "Barva musi mit zadan neprazdny nazev" );
        }
        Color color = new Color( red, green, blue, alpha );
        Barva barvaN = NAZVY.get( nazev );
        Barva barvaC = COLOR.get( color );

        if( (barvaN != null)  &&  (barvaN == barvaC) ) {
            //Je pozadovana jiz existujici barva
            return barvaN;
        }
        if( (barvaN == null)  &&  (barvaC == null) ) {
            //Je pozadovana dosud neexistujici barva
            return new Barva(red, green, blue, alpha, nazev);
        }
        //Zjistime, s kterou existujici barvou pozadavky koliduji
        Barva b = (barvaC != null)  ?  barvaC  :  barvaN;
        Color c = b.color;
        throw new IllegalArgumentException(
            "\nZadane parametry barvy koliduji s parametry existujici barvy "+
            "[existujici  zadana]:" +
            "\nnazev:          " + b.getNazev()  + "  " + nazev +
            "\ncervena slozka: " + c.getRed()    + "  " + red   +
            "\nzelena  slozka: " + c.getGreen()  + "  " + green +
            "\nmodra   slozka: " + c.getBlue()   + "  " + blue  +
            "\npruhlednost:    " + c.getAlpha()  + "  " + alpha );
    }



//== NESOUKROME METODY INSTANCI ================================================

    /***************************************************************************
     * Prevede nami pouzivanou barvu na typ pouzivany kreslitkem.
     *
     * @return Instance tridy Color reprezentujici zadanou barvu
     */
    public Color getColor()
    {
        return color;
    }


    /***************************************************************************
     * Prevede nami pouzivanou barvu na typ pouzivany kreslitkem.
     *
     * @return Instance tridy Color reprezentujici zadanou barvu
     */
    public String getCharakteristikaDec()
    {
        return String.format( "%s(dec:R=%d,G=%d,B=%d,A=%d)", nazev,
            color.getRed(), color.getGreen(), color.getBlue(),
            color.getAlpha());
    }


    /***************************************************************************
     * Prevede nami pouzivanou barvu na typ pouzivany kreslitkem.
     *
     * @return Instance tridy Color reprezentujici zadanou barvu
     */
    public String getCharakteristikaHex()
    {
        return String.format( "%s(hex:R=%02X,G=%02X,B=%02X,A=%02X)", nazev,
            color.getRed(), color.getGreen(), color.getBlue(),
            color.getAlpha());
    }


    /***************************************************************************
     * Vrati nazev barvy.
     *
     * @return Retezec definujici zadanou barvu.
     */
    public String getNazev()
    {
        return (velkymi  ?  nazev.toUpperCase()  :  nazev);
    }


    /***************************************************************************
     * Vrati vektor nazvu dane barvy.
     *
     * @return Vektor navzu barvy
     */
    public String[] getNazvy()
    {
        Collection<String> nazvy = nazvy();
        return nazvy.toArray(new String[nazvy.size()]);
    }


    /***************************************************************************
     * Vrati kolekci nazvu dane barvy.
     *
     * @return Kolekce navzu barvy
     */
    public Collection<String> nazvy()
    {
        Collection<String> nazvy = new ArrayList<String>();
        for( Map.Entry<String, Barva> entry : NAZVY.entrySet() ) {
            if(entry.getValue() == this) {
                nazvy.add( (velkymi  ?  entry.getKey().toUpperCase()
                                     :  entry.getKey() ) );
            }
        }
        return nazvy;
    }

    
    /***************************************************************************
     * Prida barve dalsi nazev - prezdivku.
     *
     * @param dalsiNazev Pridavany nazev, ktery se musi lisit od vsech
     *                   doposud zavedenych nazvu, jejichz seznam lze ziskat
     *                   zavolanim metody {@link #getZnameNazvy()}
     * @throws IllegalArgumentException je-li zadany nazev jiz pouzit
     */
    public void pridejNazev( String dalsiNazev ) {
        dalsiNazev = dalsiNazev.toLowerCase();
        Barva b = barvaSNazvem( dalsiNazev );
        if( b == null ) {
            NAZVY.put(dalsiNazev, this);
        }
        else {
            throw new IllegalArgumentException("\nJmeno musi byt jedinecne. " + 
                    "Barva s nazvem " + dalsiNazev + " je jiz definovana.");
        }
    }
    

    /***************************************************************************
     * Vrati nazev barvy.
     *
     * @return  Nazev barvy
     */
    @Override
    public String toString()
    {
        return nazev;
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
    
    /***************************************************************************
     * Vrati barvu s danym nazvem pricem je schopen ignorovat diakritiku.
     */
    private static Barva barvaSNazvem( String nazev ) {
        nazev = nazev.toLowerCase();
        Barva barva = NAZVY.get( nazev );
        if( barva == null ) {
            barva = NAZVY_BHC.get(nazev);
        }
        return barva;
    }
    
    
    /***************************************************************************
     * Obsahuje-li nazev diakritiku, ulozi do prislusne mapy 
     * jeho verzi bez diakritiky.
     */
    private void pridejNazevBezDiakritiky() {
        String bhc = IO.odhackuj( nazev );
        if( ! nazev.equals(bhc) ) {
            NAZVY_BHC.put(bhc, this);
        }
    }
    
    
//     /***************************************************************************
//      * Vytvori ze zadanych slozek cele cislo a zabali je do typu Integer.
//      *
//      * @param s retezec urceny ke konverzi
//      * @return Integer z barevnych slozek
//      */
//     private static Integer slozky( int r, int g, int b, int a )
//     {
//         int i = ((a & 0xFF) << 24)  |
//                 ((r & 0xFF) << 16)  |
//                 ((g & 0xFF) <<  8)  |
//                 ((b & 0xFF) <<  0);
//         return Integer.valueOf( i );
//     }
    
    

//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
//
//    /***************************************************************************
//     * Testovaci metoda
//     */
//    public static void test()
//    {
//        vypisZnameNazvy();
//        System.out.println( "Nazvy: " + Arrays.asList(getZnameNazvy()) );
//        System.out.println( "Barvy: " + Arrays.asList(getZnameBarvy()) );
//        for( Barva b : getZnameBarvy() )
//        {
//            System.out.println();
//            System.out.println( b.getCharakteristikaDec() );
//            System.out.println( b.getCharakteristikaHex() );
//        }
//        Barva divna = new Barva( 1, 2, 3, 255, "divna" );
//        System.out.println("\nDivna: "     + divna.getCharakteristikaDec() );
//        Barva prusvitna = getBarva( 255, 0, 0, 128, "prusvitna" );
//        System.out.println("\nPrusvitna: "+prusvitna.getCharakteristikaDec());
//        try
//        {
//            System.out.println();
//            getBarva( 1, 2, 3, 5, "divna" );
//        }catch( IllegalArgumentException iae ) {
//            System.out.println("Vyjimka: " + iae );
//            System.out.println("Vyjimka na existujici nazev vyhozena spravne");
//        }
//        try
//        {
//            System.out.println();
//            getBarva( 1, 2, 3, 255, "podivna" );
//        }catch( IllegalArgumentException iae ) {
//            System.out.println("Vyjimka: " + iae );
//            System.out.println("Vyjimka na existujici color vyhozena spravne");
//        }
//        vypisZnameNazvy();
//    }
//    /** @param ppr Paremtry prikazoveho radku - nepouzite  */
//    public static void main( String[]ppr ) {test();}  /**/
}

