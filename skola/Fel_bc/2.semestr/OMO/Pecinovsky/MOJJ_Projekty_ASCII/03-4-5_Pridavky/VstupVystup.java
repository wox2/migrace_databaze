/*******************************************************************************
 * Trida VstupVystup slouzi k demonstraci nekterych obratu spojenych 
 * s jednoduchymi okenimi vstupy a vystupy vyuzivajicimi metody tridy IO.
 *
 * @author     Rudolf Pecinovsky
 * @version    1.00, kveten 2004
 */
public class VstupVystup
{
    
    /***************************************************************************
     * Metoda demonstruje moznosti vstupu textovych dat prostrednictvim
     * jednoducheho dialogoveho okna.
     * 
     * @return Poskladane jmeno uzivastele
     */
    public static String jmeno()
    {
        String krestni = IO.zadej( "Zadej sve krestni jmeno:", "Pepa" );
        String prijmeni = IO.zadej( "Zadej sve prijmeni:", "" );
        String jmeno = krestni + " " + prijmeni;
        IO.zprava( "Jmenujes se:\n\n" + jmeno );
        return jmeno;
    }

    
    
    /***************************************************************************
     * Metoda demonstruje moznosti vstupu celociselnych dat prostrednictvim
     * jednoducheho dialogoveho okna; slouzi jako kalkulacka, ktera se zepta
     * na citatele a jmenovatele a vrati hodnotcu celociselneho podilu.
     * 
     * @return Cela cast podilu.               
     */
    public static int celociselneDeleni()
    {
        int delenec = IO.zadej( "Zadej delence (cele cislo):", 0 );
        int delitel = IO.zadej( "Zadej delitele (cele cislo):", 1 );
        int podil   = delenec / delitel;
        int zbytek  = delenec % delitel;
        IO.zprava( delenec + " : " + delitel + " = " + podil + 
                  ", zbyde " + zbytek );
        return podil;
    }

    
    
    /***************************************************************************
     * Metoda demonstruje moznosti vstupu realnych cisel prostrednictvim
     * jednoducheho dialogoveho okna; slouzi jako kalkulacka, ktera se zepta
     * na citatele a jmenovatele a vrati hodnotcu podilu.
     * 
     * @return Hodnota podilu.               
     */
    public static double realneDeleni()
    {
        double delenec = IO.zadej( "Zadej delence:", 0 );
        double delitel = IO.zadej( "Zadej delitele:", 1 );
        double podil   = delenec / delitel;
        double zbytek  = delenec % delitel;
        IO.zprava( delenec + " : " + delitel + " = " + podil + 
                  "\n\"zbytek\" = " + zbytek );
        return podil;
    }

    
    /***************************************************************************
     * Implicitni konstruktor tridy je definovan jako soukromy, 
     * a jiny definovan neni. Oblibeny obrat, jak zaridit, aby trida nikomu
     * nedovolila vytvaret jeji instance.
     */
    private VstupVystup()
    {
    }
    
    
}

