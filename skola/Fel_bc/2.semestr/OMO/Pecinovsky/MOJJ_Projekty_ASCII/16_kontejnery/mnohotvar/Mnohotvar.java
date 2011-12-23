package mnohotvar;

import java.util.ArrayList;
import java.util.List;

import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Kreslitko;


/*******************************************************************************
 * Trida {@code Mnohotvar} slouzi k demonstraci prace se seznamy.
 * Mnohotvar je postupne skladan z rady jednodussich tvaru, ktere museji byt
 * instancemi rozhrani {@code IHybaci}. Jine pozadavky na ne kladeny nejsou.
 * Pri sestavovani mnohotvar automaticky meni svoji pozici a rozmer tak,
 * aby pozice byla neustale v levem rohu opsaneho obdelnika a rozmer mnohotvaru
 * odpovidal rozmeru tohoto obdelnika.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class Mnohotvar extends AHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Seznam prvku, z nichz se mnohotvar sklada. */
    private final List<IHybaci> seznam = new ArrayList<IHybaci>();


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori prazdny mnohotvar,
     * ktery prevezme nazev generovany rodicovskou tridou     .
     */
    public Mnohotvar()
    {
        this( "" );
    }


    /***************************************************************************
     * Definuje prazdny mnohotvar se zadanym nazvem.
     *
     * @param nazev  Nazev vytvareneho mnohotvaru.
     */
    public Mnohotvar(String nazev)
    {
        super( 0, 0, 0, 0 );
        if( ! nazev.equals( "" ) ) {
            setNazev( nazev );
        }
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================

    /***************************************************************************
     * Nakresli mnohotvar pomoci dodaneho kreslitka.
     *
     * @param kreslitko   Kreslitko dodane aktivnim platnem
     */
    public void nakresli(Kreslitko kreslitko)
    {
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek a prislusne upravi novou
     * pozici a velikost mnohotvaru.
     *
     * @param ih  Pridavany hybaci tvar
     */
    public void pridej( IHybaci ih )
    {        
    }
    
            
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
//
//    /***************************************************************************
//     * Testovaci metoda.
//     */
//    public static void test()
//    {
//        Mnohotvar Mnohotvar = new Mnohotvar();
//    }
//    /** @param args Parametry prikazoveho radku - nepouzivane. */
//    public static void main( String[] args )  {  test();  }
}

