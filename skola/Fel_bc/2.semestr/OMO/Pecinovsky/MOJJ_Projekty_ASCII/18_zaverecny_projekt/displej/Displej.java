package displej;

import java.util.Timer;
import java.util.TimerTask;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Barva;


/*******************************************************************************
 * Trida realizuje nekolikamistny displej ze sedmisegmentovych cislic.
 * Pri vytvareni displeje muzeme zadat pocet cislic, velikost modulu
 * urcujicicho vyslednou velikost cislic, barvu pozadi a barvu cislic.
 * <p>
 * Trida nabizi jedinou metodu, ktera je urcena k vykreslenizadaneho cisla
 * na vytvorenem displeji.
 *
 * @author Rudolf Pecinovsky
 */
public class Displej
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Pocet cislic zobrazovanych displejem. */
    final int CISLIC;   //Pocet cislic displeje

    /** Sirka segmentu v bodech. */
    final int MODUL;    
    
    /** Delka segmentu bez krajich trojuhelniku jako nasobek jeho sirky. */
    final int DELKA;
    
    /** Barva popredi, tj. barva sviticich sebmentu. */
    final Barva POPREDI;
    
    /** Barva pozadi a zhasnutych segmentu. */
    final Barva POZADI; 
    
    /** Zaklad ciselne soutavy, ve ktere display zobrazuje. */
    final int SOUSTAVA;  
    
    /** Maximalne zobrazitelne cislo. */
    final int MAX;
    
    /** Sirka jedne zobrazovane cislice v bodech. */
    final int SIRKA_CISLICE;

    /** Jednotlive zobrazovane cislice = oblasti na displeji. */
    private final Cislice[] cislice;



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori displej o zadanem poctu sedmisegmentovych cislic s definovanou
     * barvou pozadi a popredi. Upravi rozmery platna tak, aby prave pojalo
     * pozadovany displej se zadanou velikosti modulu.
     *
     * @param cislic   Pocet cislic displeje.
     * @param modul    Bodova velikost bloku, z nichz jsou vytvorena segmenty,
     *                 Velikost modulu je soucasne sirkou segmentu.
     * @param delka    Delka tela segmentu bez okrajovych trojuhelniku
     *                 zadana jako nasobek jeho sirky (=velikosti modulu).
     * @param podklad  Barva pozadi.
     * @param znaky    Barva popredi.
     * @param soustava Ciselna soustava, ve ktere bude displej
     *                 zobrazovat zadane hodnoty.
     */
    public Displej( int cislic, int modul, int delka, 
                    Barva podklad, Barva znaky, int soustava)
    {
        this.CISLIC   = cislic;
        this.MODUL    = modul;
        this.DELKA    = delka;
        this.POPREDI  = znaky;
        this.POZADI   = podklad;
        this.SOUSTAVA = soustava;
        SIRKA_CISLICE = MODUL * (2 + DELKA);
        
        int max = 1;
        for( int i=cislic;   i-- > 0;   max = soustava*max );
        this.MAX = max-1;
        
        SP.setKrokRozmer(MODUL, CISLIC*(DELKA + 3)-1, 2*DELKA + 3);
        SP.setBarvaPozadi(podklad);
        SP.setMrizka(false);
        
        Segment.nastavKonstanty( MODUL, DELKA, POPREDI, POZADI );
        cislice = new Cislice[cislic];
        for( int i=0;   i < cislic;   i++ )
            cislice[i] = new Cislice( i*(SIRKA_CISLICE+MODUL), 0 );
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== OSTATNI PREKRYTE METODY RODICOVSKE TRIDY ==================================
//== OSTATNI METODY INSTANCI ===================================================

    /***************************************************************************
     * Zobrazi zadane cislo na dipleji. Ma-li cislo mensi pocet cislic,
     * nez kolik je mist na displeji, zobrazi se pred nim vedouci nuly.
     *
     * @param cislo  Zobrazene cislo.
     */
    public void zobraz( int cislo )
    {
        if( (cislo < 0)  ||  (MAX < cislo) )
            throw new IllegalArgumentException(
                "\nDislej zobrazuje pouze cisla od 0 do " + MAX + 
                ", zadano cislo " + cislo );
        
        SP.nekresli(); {
        CISLICE:
            for( int i=CISLIC;   --i >= 0;  )
            {
                int zobrazit = cislo % SOUSTAVA;
                cislice[i].zobraz(zobrazit );
                cislo /= SOUSTAVA;
                if( cislo == 0 )
                {
                    for( int j=i-1;   j >= 0;   j--)
                        cislice[j].zhasni();
                    break CISLICE;
                }
            }
        } SP.vratKresli();
    }


    /***************************************************************************
     * Zhasne displej -- platno bude pokryto pouze barvou pozadi.
     *
     * @param cislo  Zobrazene cislo.
     */
    public void zhasni()
    {
        SP.nekresli(); {
            for( int i=CISLIC;   --i >= 0;   )
            {
                cislice[i].zhasni();
            }
        } SP.vratKresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

