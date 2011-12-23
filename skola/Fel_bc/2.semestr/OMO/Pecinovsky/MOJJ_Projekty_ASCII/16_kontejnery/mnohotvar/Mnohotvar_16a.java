package mnohotvar;

import java.util.ArrayList;
import java.util.List;

import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.IHybaci;

import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;


/*******************************************************************************
 * Trida {@code Mnohotvar_16a} slouzi k demonstraci prace se seznamy.
 * Mnohotvar je postupne skladan z rady jednodussich tvaru, ktere museji byt
 * instancemi rozhrani {@code IHybaci}. Jine pozadavky na ne kladeny nejsou.
 * Pri sestavovani mnohotvar automaticky meni svoji pozici a rozmer tak,
 * aby pozice byla neustale v levem rohu opsaneho obdelnika a rozmer mnohotvaru
 * odpovidal rozmeru tohoto obdelnika.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Mnohotvar_16a extends AHybaci
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
    public Mnohotvar_16a()
    {
        this( "" );
    }


    /***************************************************************************
     * Definuje prazdny mnohotvar se zadanym nazvem.
     *
     * @param nazev  Nazev vytvareneho mnohotvaru.
     */
    public Mnohotvar_16a(String nazev)
    {
        super( 0, 0, 0, 0 );
        if( ! nazev.equals("") ) {
            this.nazev = nazev;
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
        for( IHybaci ih : seznam )
        {
            ih.nakresli( kreslitko );
        }
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek a prislusne upravi novou
     * pozici a velikost mnohotvaru.
     *
     * @param ih  Pridavany hybaci tvar
     */
    public void pridej( IHybaci ih )
    {
        if( seznam.contains( ih ) ) {
            throw new IllegalArgumentException( "V obrazci " + this.nazev +
                " je pridavany prvek jiz zahrnut.\n   Pridavano: " + ih);
        }
        Oblast oih = Oblast.get(ih);
        SP.nekresli(); {
            if( seznam.isEmpty() )
            {
                setOblast( oih );
            }
            else  //Pridavame dalsi
            {
                if( oih.x < getX() )    
                {   //Pridavany tvar je vic vlevo
                    super.setRozmer( getSirka() + getX() - oih.x,  getVyska() );
                    super.setPozice( oih.x, getY() );
                }
                if( oih.y < getY() )    
                {   //Pridavany tvar je vic nahore
                    super.setRozmer( getSirka(),  getVyska() + getY() - oih.y );
                    super.setPozice( getX(), oih.y );
                }
                if( (getX() + getSirka()) < (oih.x + oih.sirka) ) 
                {   //Pridavany tvar zasahuje vic vpravo
                    super.setRozmer( oih.x + oih.sirka - getX(),  getVyska() );
                }
                if( (getY() + getVyska()) < (oih.y + oih.vyska) ) 
                {   //Pridavany tvar zasahuje vic dolu
                    super.setRozmer( getSirka(),  oih.y + oih.vyska - getY() );
                }
            }
            SP.odstran( ih );   //Pro pripad, ze by byl jiz zobrazovan
            seznam.add( ih );
        } SP.vratKresli();
    }



//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

