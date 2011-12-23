package tvary1;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import java.lang.reflect.Method;
import javax.swing.JOptionPane;


/*******************************************************************************
 * Knihovni trida {@code IO} obsahuje sadu metod
 * pro jednoduchy vstup a vystup prostrednictvim dialogovyach oken
 * spolu s metodou zastavujici beh proramu na dany pocet milisekund
 * a metodu prevadejici texty na ASCII jednoduchym odstranenim diakritiky.
 *
 * @author   Rudolf PECINOVSKY
 * @version  3.00.001
 */
public final class IO
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pozice dialogovych oken. */
    private static Point poziceOken = new Point(0,0);


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

    /***************************************************************************
     * Pocka zadany pocet milisekund.
     * Na preruseni nijak zvlast nereaguje - pouze skonci driv.
     * Pred tim vsak nastavi priznak, aby volajici metoda poznala,
     * ze vlakno bylo zadano o preruseni.
     *
     * @param milisekund   Pocet milisekund, po nez se ma cekat.
     */
    public static void cekej( int milisekund )
    {
        try {
            Thread.sleep( milisekund);
        }catch( InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /***************************************************************************
     * Zbavi zadany text diakritickych znamenek; soucasne ale odstrani take
     * vsechny dalsi znaky nespadajici do tabulky ASCII.
     *
     * @param text Text urceny k "odhackovani"
     * @return  "Odhackovany" text
     */
    public static String odhackuj( String text )
    {
        return Odhackuj.text(text);
    }


    /***************************************************************************
     * Nastavi pozici pristiho dialogoveho okna.
     *
     * @param x  Vodorovna souradnice
     * @param y  Svisla souradnice
     */
    public static void oknaNa( int x, int y )
    {
        poziceOken = new Point( x, y );
    }


    /***************************************************************************
     * Zobrazi dialogove okno se zpravou a umozni uzivateli odpovedet
     * ANO nebo NE. Vrati informaci o tom, jak uzivatel odpovedel.
     * Neodpovi-li a zavre dialog, ukonci program.
     *
     * @param dotaz   Zobrazovany text otazky.
     * @return <b>{@code true}</b> Odpovedel-li uzivatel <b>ANO</b>,
     *         <b>{@code false}</b> odpovedel-li <b>NE</b>
     */
    public static boolean souhlas( Object dotaz )
    {
        JOptionPane jop = new JOptionPane(
                                dotaz,
                                JOptionPane.QUESTION_MESSAGE,   //Message type
                                JOptionPane.YES_NO_OPTION       //Option type
                                );
        processJOP( jop );
        int answer = (Integer)jop.getValue();
        return (answer == JOptionPane.YES_OPTION);
    }


    /***************************************************************************
     * Zobrazi dialogove okno s vyzvou k zadani realne hodoty;
     * pri zavreni okna zaviracim tlacitkem ukonci aplikaci.
     *
     * @param vyzva        Text, ktery se uzivateli zobrazi.
     * @param doubleImpl   Implicitni hodnota.
     * @return Uzivatelem zadana hodnota, resp. potvrzena implicitni hodnota.
     */
    public static double zadej( Object vyzva, double doubleImpl )
    {
        return Double.parseDouble( zadej( vyzva, ""+doubleImpl ).trim() );
    }


    /***************************************************************************
     * Zobrazi dialogove okno s vyzvou k zadani celociselne hodoty;
     * pri zavreni okna nebo stisku tlacitka Cancel ukonci aplikaci.
     *
     * @param vyzva     Text, ktery se uzivateli zobrazi.
     * @param intImpl   Implicitni hodnota.
     * @return Uzivatelem zadana hodnota, resp. potvrzena implicitni hodnota.
     */
    public static int zadej( Object vyzva, int intImpl )
    {
        return Integer.parseInt( zadej( vyzva, ""+intImpl ).trim() );
    }


    /***************************************************************************
     * Zobrazi dialogove okno s vyzvou k zadani textove hodoty;
     * pri zavreni okna nebo stisku tlacitka Cancel ukonci aplikaci.
     *
     * @param vyzva        Text, ktery se uzivateli zobrazi.
     * @param stringImpl   Implicitni hodnota.
     * @return Uzivatelem zadana hodnota, resp. potvrzena implicitni hodnota.
     */
    public static String zadej( Object vyzva, String stringImpl )
    {
        JOptionPane jop = new JOptionPane(
                              vyzva,
                              JOptionPane.QUESTION_MESSAGE,   //Message type
                              JOptionPane.DEFAULT_OPTION  //Option type - OK
                              );
        jop.setWantsInput(true);
        jop.setInitialSelectionValue(stringImpl);
        processJOP(jop);
        String answer = jop.getInputValue().toString();
        return answer;
    }


    /***************************************************************************
     * Zobrazi dialogove okno se zpravou a pocka, az je uzivatel odklepne;
     * pri zavreni okna ukonci aplikaci.
     *
     * @param text   Zobrazovany text.
     */
    public static void zprava( Object text )
    {
        JOptionPane jop = new JOptionPane(
                          text,                            //Sended message
                          JOptionPane.INFORMATION_MESSAGE  //Message type
                          );
        processJOP( jop );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Trida IO je knihovni tridou a proto neni urcena k tomu,
     * aby mela nejake instance.
     */
    private IO() {}


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================

    /***************************************************************************
     * Creates a dialog from the given {@link JOptionPane}, makes it non-modal
     * and waits for its closing leaving the entered value in the parameter's
     * attribute {@code value}. If the user closed the dialog
     * from the window's system menu, exit the whole application.
     */
    private static void processJOP( JOptionPane jop )
    {
        final int WAITING=0, CANCELLED=1;
        final Boolean[] USER = {true, false};

        final JDialog jd = jop.createDialog((JDialog)null, "Information"  );

        jd.addWindowListener( new WindowAdapter()
        {
            /** Set the information about closing the window from its
             *  systme menu - the application will be cancelled. */
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized( USER ) {
                    USER[CANCELLED] = true;
                    System.exit( 1 );
                }
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                poziceOken = jd.getLocation();
                if( jd.isShowing() ) {
                    return;
                }else{
                    jd.dispose();
                    synchronized( USER ) {
                        USER[WAITING] = false;
                        USER.notifyAll();
                    }
                }
            }
         });

        jd.setModal( false );
        jd.setVisible( true );
        jd.setLocation( poziceOken  );

        //Waiting until the user answers or closes the dialog
        synchronized( USER ) {
            while( USER[WAITING] ) {
                try {
                    USER.wait();
                } catch (InterruptedException ie ) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }



//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================

    /***************************************************************************
     *
     */
    private static class Odhackuj
    {
    //== KONSTANTNI ATRIBUTY TRIDY =============================================

        /** Do objektu zabalena metoda, ktera umi zadny text zbavit diakritiky.*/
        private static final INormalizer normalizer;

        static {
            INormalizer in = null;
            try { //Verze 6.0
                in = normalizer_6();
//                System.err.println("Prosla Java 6");
            }catch( ClassNotFoundException cnfe ) {
                try { //Verze 6.0 neprosla, zkousim 5.0
                    in = normalizer_5();
//                    System.err.println("Prosla Java 5");
                }catch( Exception ex ) {
                    throw new RuntimeException(
                            "\nNepodarilo se pripravit odhackovani pro 5", ex );
                }
            }catch( Exception ex ) {
                throw new RuntimeException(
                        "\nNepodarilo se pripravit odhackovani pro 6", ex );
            }finally {
                normalizer = in;
            }
        }


    //== PROMENNE ATRIBUTY TRIDY ===============================================
    //== KONSTANTNI ATRIBUTY INSTANCI ==========================================
    //== PROMENNE ATRIBUTY INSTANCI ============================================
    //== PRISTUPOVE METODY VLASTNOSTI TRIDY ====================================
    //== OSTATNI NESOUKROME METODY TRIDY =======================================

        /***********************************************************************
         * Zbavi zadany text diakritickych znamenek - <b>POZOR</b> -
         * Spolu s nimi odstrani take vsechny znaky s kodem vetsim nez 127.
         *
         * @param text Text urceny k "odhackovani"
         * @return  "Odhackovany" text
         */
        public static String text( String text )
        {
    //        //Verze 5.0
    //        String normalizovany = sun.text.Normalizer.normalize(text,
    //                                        sun.text.Normalizer.DECOMP, 0);
    //        //Od verze 6.0
    //        String normalizovany = java.text.Normalizer.normalize( text,
    //                                         java.text.Normalizer.Form.NFD);
            String normalizovany = normalizer.normalize(text);
            String odhackovany = normalizovany.replaceAll("[^\\p{ASCII}]", "");
            return odhackovany;
        }


    //##########################################################################
    //== KONSTRUKTORY A TOVARNI METODY =========================================

       /** Soukromy konstruktor branici vytvoreni instance. */
        private Odhackuj() {}


    //== ABSTRAKTNI METODY =====================================================
    //== PRISTUPOVE METODY VLASTNOSTI INSTANCI =================================
    //== OSTATNI NESOUKROME METODY INSTANCI ====================================
    //== SOUKROME A POMOCNE METODY TRIDY =======================================

        /***********************************************************************
         * Nastavi normalizacni metodu pro Javu 5.
         *
         * @return Pozadovana metoda
         * @throws ClassNotFoundException Nenalezeni tridy indikuje
         *                                           jinou verzi Javy
         */
        @SuppressWarnings({"unchecked"})
        private static INormalizer normalizer_5() throws ClassNotFoundException {
            final Class cClass = String.class;
            final Class nClass = Class.forName("sun.text.Normalizer");

            final Class  fClass;
            final Method nMethod;
            final Object fValue;
            try {
                fClass  = Class.forName("sun.text.Normalizer$Mode");
                fValue  = nClass.getField("DECOMP").get(null);
                nMethod = nClass.getMethod("normalize", cClass, fClass, int.class);
            } catch (Exception ex) {
                throw new RuntimeException(
                                    "\nNepodarilo se pripravit metodu pro 5", ex);
            }

            return new INormalizer() {
                @Override
                public String normalize(String s) {
                    try {
                        return (String) nMethod.invoke(null,
                                                       s, fClass.cast(fValue), 0 );
                    } catch (Exception ex) {
                        throw new RuntimeException(
                                      "\nNepodarilo se vyvolat metodu pro 5 ", ex);
                    }
                }
            };
        }


        /***********************************************************************
         * Nastavi normalizacni metodu pro Javu 6.
         *
         * @return Pozadovana metoda
         * @throws ClassNotFoundException Nenalezeni tridy indikuje
         *                                jinou verzi Javy
         */
        @SuppressWarnings({"unchecked", "unchecked"})
        private static INormalizer normalizer_6() throws ClassNotFoundException {
            final Class cClass = CharSequence.class;
            final Class nClass = Class.forName("java.text.Normalizer");

            final Class  fClass;
            final Method nMethod;
            final Object fValue;
            try {
                fClass  = Class.forName("java.text.Normalizer$Form");
                fValue  = fClass.getField("NFD").get(null);
                nMethod = nClass.getMethod("normalize", cClass, fClass);
            } catch (Exception ex) {
                throw new RuntimeException(
                                    "\nNepodarilo se pripravit metodu pro 6", ex);
            }

            return new INormalizer() {
                @Override
                public String normalize(String s) {
                    try {
                        return (String) nMethod.invoke(null,
                                                       s, fClass.cast(fValue) );
                    } catch (Exception ex) {
                        throw new RuntimeException(
                                "\nNepodarilo se vyvolat metodu pro 6", ex);
                    }
                }
            };
        }


    //== SOUKROME A POMOCNE METODY INSTANCI ====================================
    //== VNORENE A VNITRNI TRIDY ===============================================

        private static interface INormalizer {
            public String normalize( String s );
        }


    //== TESTY A METODA MAIN ===================================================
    }



//== TESTY A METODA MAIN =======================================================
////+ main
//
//    /***************************************************************************
//     * Overuje moznost umistit rodice dialogoveho okna (a tim i dialogove
//     * okno) na pozadovane misto na obrazovce.
//     */
//    public static void test()
//    {
//        String txt = "Prilis zlutoucky kun upel dabelske ody.[aeiouy](--)";
//         rup.cesky.tvary.SpravcePlatna cm =
//                 rup.cesky.tvary.SpravcePlatna.getInstance();
////        SpravcePlatna cm = SpravcePlatna.getInstance();
//        String bhc = Odhackuj.text( txt );
//        IO.zprava(txt + "\n" + bhc);
//        oknaNa( -1000, 500 );
//        zprava( "Druhy pokus: [-1000;500]" );
//
//         String ss;
//         boolean c = souhlas( "Do you agree?" );
//         ss = "Souhlas:  " + c;
//
////         setDialogsPosition( 500,  500 );
//         String s = zadej( ss, "ABC" );
//         ss = "String: " + s;
//
////         setDialogsPosition( 1000, 500 );
//         double d = zadej(ss, 12.34);
//         ss = "Double: " + d;
//         zprava( ss );
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main( String[] args ) {
//        test();
//    }
////- main
}

