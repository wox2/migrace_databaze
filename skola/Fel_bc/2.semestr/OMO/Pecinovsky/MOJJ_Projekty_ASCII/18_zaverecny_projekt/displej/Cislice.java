package displej;

/*******************************************************************************
 * Instance tridy predstavuji jednotlive pozice na displeji.
 * Cislice vi, kde se na displeji nachazi a ktere segmenty k ni patri.
 * Pri svem vzniku sve segmenty ihned prihlasi do spravy animacniho platna.
 * Pri zadosti o zobrazeni cislice se pak jiz jen podiva (do vektoru,
 * ktery je statickym atributem tridy), ze kterych segmentu se sklada
 * a necha je rozsvitit ci zhansnout.
 *
 * @author Rudolf Pecinovsky
 */
public class Cislice
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    public static final int SEGMENTU = 7;

    /***************************************************************************
     * Mnozina segmentu, z nichz je cislice slozena. Informace je ulozena
     * prostrednctvim nahozeni prislusnych bitu v celociselnem priznaku.
     * Vahy jednotlivych segmentu jsou nasledujici:
     * . 0 .  == 1
     * 1   2  == 2, 4
     * . 3 .  == 8
     * 4   5  == 0x10, 0x20
     * . 6 .  == 0x40
     */
    private static final boolean[][] rozsvitit = 
        //    0      1      2      3      4      5      6
        { { true,  true,  true,  false, true,  true,  true  },   //0
          { false, false, true,  false, false, true,  false },   //1
          { true,  false, true,  true,  true,  false, true  },   //2
          { true,  false, true,  true,  false, true,  true  },   //3
          { false, true,  true,  true,  false, true,  false },   //4
          { true,  true,  false, true,  false, true,  true  },   //5
          { true,  true,  false, true,  true,  true,  true  },   //6
          { true,  false, true,  false, false, true,  false },   //7
          { true,  true,  true,  true,  true,  true,  true  },   //8
          { true,  true,  true,  true,  false, true,  true  } }; //9 

    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Vektor segmentu tvoricich danou pozici displeje. */
    private final Segment[] segment;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vtvori instanci cislice, ktera bude na disleji zacinat {@code posunX}
     * poli od leveho okraje displeje. Necha vytvorit vsechny sve segmenty
     * pro zacatek v barve pozadi platna a hned je prihlasi do spravy
     * animacniho platna.
     *
     * @param posunX Vodorovna pozice vytvarene cislice
     * @param posunY Svisla pozice vytvarene cislice
     */
    public Cislice( int posunX, int posunY )
    {
        segment = Segment.getSedmicka( posunX, posunY );
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== OSTATNI METODY INSTANCI ===================================================

    /***************************************************************************
     * Zobrazi prislusnou cislici na zadane polickove pozici displeje.
     * Na zaklade informace o sviticich segmentech pri zobrazeni dane cislice,
     * kterou ziska z prislusne slozky pole <code>rozsvitit</code>,
     * probehne vsechny sve segmenty a priradi jim potrebnou barvu,
     * tj. rozsviti je nebo zhasne.
     *
     * @param hodnota  Hodnota (cislice), kterou je treba zobrazit
     */
    public void zobraz( int hodnota )
    {
        boolean[] svitit = rozsvitit[ hodnota ];
        for( int i=0;   i < SEGMENTU;   i++ )      
        {
            segment[i].setBarva( svitit[i] ); 
        }
    }


    /***************************************************************************
     * Zhanse cislici na dispoleji.
     */
    public void zhasni()
    {
        for( Segment s : segment )      
        {
            s.setBarva( false );
        }
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

