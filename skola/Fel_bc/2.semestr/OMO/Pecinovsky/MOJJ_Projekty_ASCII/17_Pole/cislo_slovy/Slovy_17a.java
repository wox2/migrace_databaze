package cislo_slovy;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public final class Slovy_17a
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Nejvetsi cislo, ktere umi program prevest na text. */
    public static final long MAX = 999L;

    private static String jednotky[] = new String[]{
        "",        "jedna",    "dva",       "tri",      "ctyri",
        "pet",     "sest",     "sedm",      "osm",      "devet",
        "deset",   "jedenact", "dvanact",   "trinact",  "ctrnact",
        "patnact", "sestnact", "sedmnact",  "osmnact",  "devatenact"
    };

    private static String desitky[] = new String[]{
        "",        "",         "dvacet",    "tricet",   "ctyricet",
        "padesat", "sedesat",  "sedmdesat", "osmdesat", "devadesat"
    };

    private static String stovky[] = new String[]{
        "",        "sto",      "dve ste",   "tri sta",  "ctyri sta",
        "pet set", "sest set", "sedm set",  "osm set",  "devet set"
    };

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Metoda vrati retezec predstavujici slovni vyjadreni zadaneho cisla.
     *
     * @param cislo  Cislo, ktere cheme vyjaidrit slovy.
     *
     * @return Slovni vyjadreni zadaneho cisla
     * @throws IllegalArgumentException je-li cislo prilis male nebo velike.
     */
    public static String cislo( int cislo )
    {
        if( cislo == 0 ) {
            return "nula"; //====================>
        }
        if( (cislo < 0)  ||  (MAX < cislo) ) {
            throw new IllegalArgumentException(
                "Lze prevadet pouze nezaporna cisla do " + MAX );
        }
        return stovky( cislo );
    }

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Tato trida bude knihovni, bude obsahovat pouze staticke metody,
     * takze od ni zakazeme vytvaret instance.
     */
    private Slovy_17a()    {}

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================

    /***************************************************************************
     * Metoda vrati retezec predstavujici slovni vyjadreni zadaneho cisla.
     *
     * @param cislo  Cislo, ktere cheme vyjaidrit slovy.
     *
     * @return Slovni vyjadreni zadaneho cisla
     * @throws IllegalArgumentException je-li cislo prilis male nebo velike.
     */
    private static String stovky( int cislo )
    {
        if( cislo < 20 ) {
            return jednotky[cislo];
        }
        int jednotek = cislo % 10;
        int desitek  = cislo / 10;
        int stovek   = desitek / 10;
            desitek  = desitek % 10;
        return stovky[stovek] + " " +
              ((desitek < 2)  ?  jednotky[ 10*desitek + jednotek ]
                              :  desitky [ desitek ] + " " +
                                 jednotky[ jednotek ]);
    }


//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

