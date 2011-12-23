/*******************************************************************************
 * Trida Kompresor slouzi ke zmene velikosti objektu
 * implementujicich rozhrani INafukovaci.
 * Trida NENI vlaknove bezpecna (thread-safe). Nepredpoklada, 
 * ze jeji instance boudou volany simultanne z ruznych vlaken.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.01, 10.3.2003
 */
public class Kompresor
{
//== VEREJNE KONSTANTY =========================================================
//== SOUKROME KONSTANTY ========================================================

    /** Doba mezi jednotlivymi "stouchy".*/
    private static int CEKANI = 30;

    /** Tento atribut je tu pouze pro zjednoduseni psani. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();



//== ATRIBUTY TRIDY ============================================================
//== ATRIBUTY INSTANCI =========================================================

    /** Specifikuje silu "nafukovani" objektu danou instanci kompresoru,
     * tj. miru jeho prifouknuti. */
    private int sila;



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor kompresoru se silou nafukovani 1 (sila nafukovani definuje
     * pocet bodu, o nez se zvetsi rozmer objektu po jednom "stouchu").     
     */
    public Kompresor()
    {
        this( 1 );
    }


    /***************************************************************************
     * Konstruktor kompresoru se zadanou silou nafukovani (sila nafukovani
     * definuje pocet bodu, o nez se zvetsi rozmer objektu po jednom "stouchu").
     *
     * @param sila  Sila nafukovani vytvareneho kompresoru
     */
    public Kompresor( int sila )
    {
        this.sila = sila;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Metoda zvetsi zadanou instanci o zadany nasobek sily sveho kompresoru.
     * Pri zmene velikosti se zachovava pomer stran.     
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho     Zvetsovany objekt
     * @param stouchu  Pocet prifouknuti o silu kompresoru
     */
    public void prifoukni(INafukovaci koho, int stouchu )
    {
        Rozmer r = koho.getRozmer();
        int x = r.sirka;
        int y = r.vyska;
        double d = delka( x, y );
        foukej( koho, stouchu, sila*x/d, sila*y/d );
    }


    /***************************************************************************
     * Metoda zvetsi zadanou instanci o petinasobek sily sveho kompresoru.
     * Pri zmene velikosti se zachovava pomer stran.     
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho  Zvetsovany objekt
     */
    public void prifoukni(INafukovaci koho )
    {
        prifoukni( koho, 5 );
    }


    /***************************************************************************
     * Metoda zmensi zadanou instanci o zadany nasobek sily sveho kompresoru.
     * Pri zmene velikosti se zachovava pomer stran.     
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho      Zmensovany objekt
     * @param stouchu   Pocet ufouknuti o silu kompresoru
     */
    public void ufoukni(INafukovaci koho, int stouchu )
    {
        sila = -sila;
        prifoukni( koho, stouchu );
        sila = -sila;
    }


    /***************************************************************************
     * Metoda zmensi zadanou instanci o petinasobek sily sveho kompresoru.
     * Pri zmene velikosti se zachovava pomer stran.     
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho  Zmensovany objekt
     */
    public void ufoukni(INafukovaci koho)
    {
        ufoukni( koho, 5 );
    }



    /***************************************************************************
     * Nafoukne ci vyfoukne zadany objekt na pozadovanou velikost.
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho    Na(vy)fukovany objekt
     * @param sirka   Pozadovana vysledna sirka objektu
     * @param vyska   Pozadovana vysledna vyska objektu
     */
    public void nafoukniNa( INafukovaci koho, int sirka, int vyska )
    {
        Rozmer r = koho.getRozmer();
        int    vodorovne  = sirka - r.sirka;
        int    svisle     = vyska - r.vyska;
        int    kroku      = (int)(delka(vodorovne,  svisle) / sila);
        double dx = (double)vodorovne / kroku;
        double dy = (double)svisle    / kroku;
        foukej( koho, kroku, dx, dy );
    }



    /***************************************************************************
     * Nafoukne ci vyfoukne zadany objekt na pozadovanou velikost.
     * Nejprve ale zabezpeci, aby byl objekt zobrazen na platne.     
     *
     * @param koho    Na(vy)fukovany objekt
     * @param rozmer  Pozadovany rozmer objektu
     */
    public void nafoukniNa( INafukovaci koho, Rozmer rozmer )
    {
        nafoukniNa( koho, rozmer.sirka, rozmer.vyska );
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================

    /***************************************************************************
     *  Spocte delku prepony pravouhleho trojuelniku se zadanymi odvesnami. 
     * 
     * @param x   Delka prvni odvesny
     * @param y   Delka druhe odvesny
     * 
     * @return    Delka prepony
     */
    private static double delka( int x, int y )
    {
        return Math.sqrt(x*x + y*y);
    }



//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Vykonna metoda, ktera zaridi vlastni nafouknuti, resp. vyfouknuti
     * zadaneho objektu na zaklade pripravenych parametru.
     *       
     * @param koho     Objekt, jehoz velikost menime.
     * @param stouchu  Pocet kroku, v nichz velikost objektu zmenime.
     * @param dx       Zvetseni sirky objektu v jednom kroku.
     * @param dy       Zvetseni vysky objektu v jednom kroku.              
     */              
    private void foukej( INafukovaci koho, int stouchu, double dx, double dy )
    {
        if( ! (koho instanceof IKresleny) ) {
            throw new IllegalArgumentException(
                    "Nafukovat se smi jen kreslitelne objekty");
        }
        IKresleny ik = (IKresleny)koho;
        SP.pridej( ik );
        //Konstatnu pripocitavame proto, aby skoky byly vyrovnanejsi
        Rozmer r = koho.getRozmer();
        double sirka = r.sirka + .4;
        double vyska = r.vyska + .4;
        while( stouchu-- > 0 )
        {
            IO.cekej(CEKANI);
            sirka += dx;
            vyska += dy;
            koho.setRozmer( (int)sirka, (int)vyska );
        }
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

