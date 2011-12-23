package mnohotvar;

import java.util.ArrayList;
import java.util.List;

import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.IHybaci;

import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Oblast;


/*******************************************************************************
 * Trida {@code Mnohotvar_16d} slouzi k demonstraci prace se seznamy.
 * Mnohotvar je postupne skladan z rady jednodussich tvaru, ktere museji byt
 * instancemi rozhrani IHybaci. Jine pozadavky na ne kladeny nejsou.
 * Pri sestavovani mnohotvar automaticky meni svoji pozici a rozmer tak,
 * aby pozice byla neustale v levem rohu opsaneho obdelnika a rozmer mnohotvaru
 * odpovidal rozmeru tohoto obdelnika.
 *
 * Oproti tride Mnohotvar_16c se zmenilo:
 * - V souboru pribyla vnorena trida Cast, ktera slouzi jako prepravka
 *   pro dodatecne informace o tvarech, z nichz je mnohotvar sestavovan.
 * - Zmenil se typ prvku seznamu - nyni jsou jeho prvky instance tridy Cast
 * - Metoda setRozmer(int,int) nyni vyuziva dodatecne informace z prepravek.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Mnohotvar_16d extends AHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Seznam prvku, z nichz se mnohotvar sklada. */
    private final List<Cast> seznam = new ArrayList<Cast>();



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori prazdny mnohotvar,
     * ktery prevezme nazev generovany rodicovskou tridou     .
     */
    public Mnohotvar_16d()
    {
        this( "" );
    }


    /***************************************************************************
     * Definuje prazdny mnohotvar se zadanym nazvem.
     *
     * @param nazev  Nazev vytvareneho mnohotvaru.
     */
    public Mnohotvar_16d(String nazev)
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
            for( Cast c : seznam )
            {
                IHybaci cih = c.ih;
                Pozice  pos = cih.getPozice();
                cih.setPozice( dx + pos.x, dy + pos.y );
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
        Cast.pripravKonstanty( getX(), getY(), getSirka(), getVyska(), 
                               getX(), getY(), sirka,      vyska );
        //Uprav velikosti a pozice jednotlivych casti
        SP.nekresli(); {
            for( Cast c : seznam ) {
                c.poNafouknuti();
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
        for( Cast c : seznam )
        {
            c.ih.nakresli( kreslitko );
        }
    }


    /***************************************************************************
     * Vrati index zadaneho prvku v mnohotvaru. Prvek, ktery je zcela vespod
     * ma index 0, prvek ktery je zcela navrchu ma index o jednicku mensi,
     * nez je pocet prvku mnohotvaru.
     *
     * @param prvek  Pridavany hybaci tvar
     * 
     * @return    Index zadaneho prvku v mnohotvaru. 
     *            Neni-li prvek soucasti mnohotvaru, vrati -1.
     */
    public int index( IHybaci prvek )
    {
        int kde = 0;
        for( Cast c : seznam )
        {
            if( c.ih == prvek ) {
                return kde;         //==========>
            }
            kde++;
        }
        return -1;
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek a prislusne upravi novou
     * pozici a velikost mnohotvaru.
     *
     * @param ih  Pridavany hybaci tvar
     */
    public void pridej( IHybaci ih )
    {
        if( index( ih ) >= 0 ) {
            throw new IllegalArgumentException( "V obrazci " + this.nazev +
                " je pridavany prvek jiz zahrnut.\n   Pridavano: " + ih);
        }
        Oblast oih = Oblast.get(ih);
        SP.nekresli(); {
            if( seznam.isEmpty() )
            {
                super.setOblast( oih );
                Cast.pripravKonstanty( 0, 0, 0, 0, 
                                       getX(), getY(), getSirka(), getVyska() );
            }
            else  //Pridavame dalsi
            {
                Oblast omn = getOblast();
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

                Cast.pripravKonstanty( omn.x,  omn.y,  omn.sirka,  omn.vyska, 
                                       getX(), getY(), getSirka(), getVyska() );
                for( Cast c : seznam ) {
                    c.poPridani();
                }
            }
            SP.odstran( ih );
            seznam.add( new Cast( ih ) );
        } SP.vratKresli();
    }


//== VNORENE A VNITRNI TRIDY ===================================================

    /*******************************************************************************
     * Trida slouzi jako prepravka pro uchovavani pomocnych informaci
     * pro co nelepsi zmenu velikosti mnohotvaru.
     *
     * @author    Rudolf Pecinovsky
     * @version   0.00.000,  0.0.2003
     */
    private static final class Cast
    {
    //== KONSTANTNI ATRIBUTY TRIDY =================================================
    //== PROMENNE ATRIBUTY TRIDY ===================================================
    
        /** Stare a nove souradnice a rozmery celeho mnohotvaru. */
        static double smx, smy, sms, smv,
                      nmx, nmy, nms, nmv;
    
    //== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    //== PROMENNE ATRIBUTY INSTANCI ================================================
    
        /** Odkaz na cast mnohotvaru. */
        IHybaci ih;
    
        /** Podil odstupu od leveho kraje mnohotvaru na jeho celkove sirce. */
        double dx;
    
        /** Podil odstupu od horniho kraje mnohotvaru na jeho celkove vysce. */
        double dy;
    
        /** Podil sirky casti k celkove sirce mnohotvaru. */
        double ds;
    
        /** Podil vysky casti k celkove vysce mnohotvaru. */
        double dv;
    
    
    
    //== OSTATNI NESOUKROME METODY TRIDY ===========================================    
    
        /***************************************************************************
         * Pripravuje podklady pro naslednou aktualizaci uchovavanych pomeru
         * klicovych rozmeru jednotlivych soucasti mnohotvaru.
         * Tyto konstanty jsou pro vsechny soucasti mnohotvaru spolecne.
         * 
         * @param sx  Puvodni (stare) x.
         * @param sy  Puvodni (stare) y.
         * @param ss  Puvodni (stara) sirka.
         * @param sv  Puvodni (stara) vyska.
         * @param nx  Nove x.
         * @param ny  Nove y.
         * @param ns  Nova sirka.
         * @param nv  Nova vyska.
         */
        static void pripravKonstanty( int sx, int sy, int ss, int sv,
                                      int nx, int ny, int ns, int nv )
        {
            smx = sx;   smy = sy;   sms = ss;   smv = sv;
            nmx = nx;   nmy = ny;   nms = ns;   nmv = nv;
        }
    
    //##############################################################################
    //== KONSTRUKTORY A TOVARNI METODY =============================================
    
        /***************************************************************************
         * Vytvori prepravku a zapamatuje si aktualni stav nekterych pomeru
         * vuci soucasne porobe multitvaru.
         */
        Cast( IHybaci ih )
        {
            this.ih = ih;
            Oblast o = Oblast.get(ih);
            dx = (o.x - nmx) / nms;
            dy = (o.y - nmy) / nmv;
            ds = o.sirka / nms;
            dv = o.vyska / nmv;
        }
    
    
    //== OSTATNI NESOUKROME METODY INSTANCI ========================================
    
        /***************************************************************************
         * Aktiualizuje uchovavane pomery klicovych rozmeru tvaru vuci mnohotvaru.
         * Ocekava, ze metoda priprav nastavila hodnoty potrebnych konstant.
         */
        void poPridani()
        {
            //Souradnice se mohou pouze zmensovat
            dx = (smx - nmx + dx*sms) / nms;
            dy = (smy - nmy + dy*smv) / nmv;
            
            ds = ds * sms / nms;
            dv = dv * smv / nmv;
        }
    
    
        /***************************************************************************
         * Aktualizuje uchovavane pomery klicovych rozmeru a vykresli dany tvar
         * v aktualni pozici a rozmeru.
         */
        void poNafouknuti()
        {
            ih.setPozice( (int)Math.round( nmx + dx*nms ),
                          (int)Math.round( nmy + dy*nmv )  );
            ih.setRozmer( (int)Math.round( nms*ds ),  
                          (int)Math.round( nmv*dv ) );
        }
    }

//== TESTY A METODA MAIN =======================================================
}

