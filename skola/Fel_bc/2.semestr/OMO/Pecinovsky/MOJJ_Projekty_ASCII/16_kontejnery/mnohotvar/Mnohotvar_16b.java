package mnohotvar;

import java.util.ArrayList;
import java.util.List;

import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.IHybaci;

import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Rozmer;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;


/*******************************************************************************
 * Trida {@code Mnohotvar_16b} slouzi k demonstraci prace se seznamy.
 * Mnohotvar je postupne skladan z rady jednodussich tvaru, ktere museji byt
 * instancemi rozhrani {@code IHybaci}. Jine pozadavky na ne kladeny nejsou.
 * Pri sestavovani mnohotvar automaticky meni svoji pozici a rozmer tak,
 * aby pozice byla neustale v levem rohu opsaneho obdelnika a rozmer mnohotvaru
 * odpovidal rozmeru tohoto obdelnika.
 *
 * Oproti tride Mnohotvar_16a se zmenilo:
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Mnohotvar_16b extends AHybaci
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
    public Mnohotvar_16b()
    {
        this( "" );
    }


    /***************************************************************************
     * Definuje prazdny mnohotvar se zadanym nazvem.
     *
     * @param nazev  Nazev vytvareneho mnohotvaru.
     */
    public Mnohotvar_16b(String nazev)
    {
        super( 0, 0, 0, 0 );
        if( ! nazev.equals("") ) {
            this.nazev = nazev;
        }
    }



//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Premisti cely mnohotvar na zadanou pozici.
     * Vsechny soucasti instance se premistuji jako celek
     *
     * @param x  Nastavovana vodorovna souradnice
     * @param y  Nastavovana nastavovana svisla souradnice
     */
    @Override
    public void setPozice( int x, int y )
    {
        int dx = x - getX();
        int dy = y - getY();
        SP.nekresli(); {
            for( IHybaci ih : seznam )
            {
                Pozice  pos = ih.getPozice();
                ih.setPozice( dx + pos.x, dy + pos.y );
            }
            super.setPozice( x, y );    //Nastavuji hodnoty pro cely tvar
        } SP.vratKresli();
    }


    /***************************************************************************
     * Nastavi novy rozmer mnohotvaru. Upravi rozmery a pozice vsech jeho
     * sourasti tak, aby vysledny mnohotvar i pri novem rozmeru stejny vzhled.
     *
     * @param sirka  Nastavovana sirka
     * @param vyska  Nastavovana vyska
     */
    @Override
    public void setRozmer( int sirka, int vyska )
    {
        //Zapamatuj si pomery zmeny sirky a vysky celeho obrazce
        double ss = (double)sirka / getSirka();
        double vv = (double)vyska / getVyska();

        //Uprav velikosti a pozice jednotlivych casti
        SP.nekresli(); {
            for( IHybaci ih : seznam )
            {
                Rozmer roz = ih.getRozmer();
                //Objekty s nulovymi rozmery svou velikost nemeni
                if( (roz.sirka == 0)  ||  (roz.vyska == 0) ) {
                    continue;
                }
                ih.setRozmer( (int)(ss * roz.sirka),
                              (int)(vv * roz.vyska) );
                //Pri zmene velikosti se podobjekt take posune
                Pozice poz = ih.getPozice();
                int dhx = (int)((poz.x - getX()) * ss);
                int dhy = (int)((poz.y - getY()) * vv);
                ih.setPozice( (getX() + dhx), (getX() + dhy));
            }
            super.setRozmer( sirka, vyska );  //Nastavuji hodnoty pro cely tvar
        } SP.vratKresli();
    }



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

