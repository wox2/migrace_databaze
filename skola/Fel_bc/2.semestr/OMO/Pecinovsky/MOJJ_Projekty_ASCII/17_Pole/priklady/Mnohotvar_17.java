package priklady;

import java.util.ArrayList;
import java.util.List;

import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.IHybaci;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Rozmer;


/*******************************************************************************
 * Trida slouzi k demonstraci prace se seznamy.
 * Mnohotvar je postupne skladan z rady jednodussich tvaru, ktere museji byt
 * instancemi rozhrani IHybaci. Jine pozadavky na ne kladeny nejsou.
 * Pri sestavovani mnohotvar automaticky meni svoji pozici a rozmer tak,
 * aby pozice byla neustale v levem rohu opsaneho obdelnika a rozmer mnohotvaru
 * odpovidal rozmeru tohoto obdelnika.
 *
 * Oproti tride Mnohotvar_16b se zmenilo:
 * - Pribyly metody pridejPod, pridejNad, pridejDolu, pridejNahoru
 *   umoznujici umistit pridavany do definovane vrstvy.
 * - Pribyla metoda zarad umoznujici jednodusi definici privavacich metod.
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Mnohotvar_17 extends AHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Seznam prvku, z nichz se mnohotvar sklada. */
    private final List<Cast> seznam = new ArrayList<Cast>();



//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori prazdny mnohotvar,
     * ktery prevezme nazev generovany rodicovskou tridou     .
     */
    public Mnohotvar_17()
    {
        this( "" );
    }


    /***************************************************************************
     * Definuje prazdny mnohotvar se zadanym nazvem.
     *
     * @param nazev  Nazev vytvareneho mnohotvaru.
     */
    public Mnohotvar_17(String nazev)
    {
        super( 0, 0, 0, 0 );
        if( ! nazev.equals("") ) {
            this.nazev = nazev;
        }
    }


    public static Mnohotvar_17 getInstance( String nazev, IHybaci ... prvek)
    {
        Mnohotvar_17 mt = new Mnohotvar_17( nazev );
        for( IHybaci ih : prvek ) {
            mt.pridej(ih);
        }
        return mt;
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
            super.setPozice( x, y );
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

        Cast.pripravKonstanty( getX(), getY(), getSirka(), getVyska(),
                               getX(), getY(), sirka,      vyska );
        //Uprav velikosti a pozice jednotlivych casti
        SP.nekresli(); {
            for( Cast c : seznam ) {
                c.poNafouknuti();
            }
            super.setRozmer( sirka, vyska );
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
                return kde;     //==========>
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
        zarad( seznam.size(), ih );
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek tesne pod oznaceny prvek
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param stary  Tvar, ktery je soucasti mnohotvaru
     *               a pod nejz se novy prvek pridava
     * @param novy   Pridavany hybaci tvar
     * @return {@code true} byl-li dany prvek doopravdy pridan
     */
    public boolean pridejPod( IHybaci stary, IHybaci novy )
    {
        ZdaKde zk = priprav( stary, novy );
        zarad( zk.kde, novy );
        return zk.zda;
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek tesne nad oznaceny prvek
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param stary  Tvar, ktery je soucasti mnohotvaru
     *               a nad nejz se novy prvek pridava
     * @param novy   Pridavany hybaci tvar
     * @return {@code true} byl-li dany prvek doopravdy pridan
     */
    public boolean pridejNad( IHybaci stary, IHybaci novy )
    {
        ZdaKde zk = priprav( stary, novy );
        zarad( zk.kde+1, novy );
        return zk.zda;
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek pod vsechny prvky
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param novy   Pridavany hybaci tvar
     * @return {@code true} byl-li dany prvek doopravdy pridan
     */
    public boolean pridejDolu( IHybaci novy )
    {
        ZdaKde zk = priprav( null, novy );
        zarad( 0, novy );
        return zk.zda;
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek nad vsechny prvky
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param novy   Pridavany hybaci tvar
     * @return {@code true} byl-li dany prvek doopravdy pridan
     */
    public boolean pridejNahoru( IHybaci novy )
    {
        ZdaKde zk = priprav( null, novy );
        zarad( seznam.size(), novy );
        return zk.zda;
    }



    //== SOUKROME A POMOCNE METODY TRIDY ===========================================
    //== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Prida do mnohotvaru zadany prvek tesne nad oznaceny prvek
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param stary  Tvar, ktery je soucasti mnohotvaru
     *               a nad nejz se novy prvek pridava
     * @param novy   Pridavany hybaci tvar
     */
    private ZdaKde priprav( IHybaci stary, IHybaci novy )
    {
        ZdaKde zk = new ZdaKde();

        int in = index( novy );
        if( in >= 0 )
        {
            seznam.remove( in );
            zk.zda = false;
        }
        else {
            zk.zda = true;
        }

        if( stary == null )
        {
            return zk;          //==========>
        }

        int is = index( stary );
        if( is < 0 )
        {
            throw new IllegalArgumentException(
                "Referencni objekt neni soucasti mnohotvaru!" );
        }
        zk.kde = is;
        return zk;
    }


    /***************************************************************************
     * Prida do mnohotvaru zadany prvek na zadane misto v seznamu
     * a prislusne upravi novou pozici a velikost mnohotvaru.
     *
     * @param index  Pozice v seznamu, kam bude novy prvek zarazen.
     *               Puvodni prvek na teto pozici a nasledujici prvky
     *               budou posunuty o jenu pozici dale.
     * @param ih     Pridavany hybaci tvar
     */
    private void zarad( int index, IHybaci ih )
    {
        Pozice poz = ih.getPozice();
        Rozmer roz = ih.getRozmer();
        if( seznam.isEmpty() )  //Pridavame prvni
        {
            setX( poz.x );
            setY( poz.y );
            setSirka( roz.sirka );
            setVyska( roz.vyska );
            Cast.pripravKonstanty( 0, 0, 0, 0,
                                   getX(), getY(), getSirka(), getVyska() );
        }
        else                    //Pridavame dalsi
        {
            int mx = getX();
            int my = getY();
            int ms = getSirka();
            int mv = getVyska();

            int ihx = poz.x;
            int ihy = poz.y;
            int ihs = roz.sirka;
            int ihv = roz.vyska;

            if( ihx < getX() )
            {
                setSirka( getSirka() + getX() - ihx );
                setX( ihx );
            }
            if( ihy < getY() )
            {
                setVyska( getVyska() + getY() - ihy );
                setY( ihy );
            }
            if( (getX() + getSirka()) < (ihx + ihs) ) {
                setSirka( ihx + ihs - getX() );
            }
            if( (getY() + getVyska()) < (ihy + ihv) ) {
                setVyska( ihy + ihv - getY() );
            }
            Cast.pripravKonstanty( mx,     my,     ms,         mv,
                                   getX(), getY(), getSirka(), getVyska() );
            for( Cast c : seznam ) {
                c.poPridani();
            }
        }
        SP.nekresli(); {
            SP.odstran( ih );
            seznam.add( index, new Cast( ih ) );
        } SP.vratKresli();
    }


//== VNORENE A VNITRNI TRIDY ===================================================

    private static final class ZdaKde
    {
        boolean zda;
        int kde;
    }



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



    //== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
    //== OSTATNI METODY TRIDY ======================================================


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
            Pozice p = ih.getPozice();
            Rozmer r = ih.getRozmer();
            dx = (p.x - nmx) / nms;
            dy = (p.y - nmy) / nmv;
            ds = r.sirka / nms;
            dv = r.vyska / nmv;
        }


    //== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    //== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
    //== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
    //== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    //== NOVE ZAVEDENE METODY INSTANCI =============================================

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


    //== SOUKROME A POMOCNE METODY TRIDY ===========================================
    //== SOUKROME A POMOCNE METODY INSTANCI ========================================
    //== VNORENE A VNITRNI TRIDY ===================================================
    //== TESTY A METODA MAIN =======================================================
    }

//== TESTY A METODA MAIN =======================================================

    public static void test()
    {
        Mnohotvar_17 mt = getInstance( "Mix",
                     new rup.cesky.tvary.Obdelnik(),
                     new rup.cesky.tvary.Elipsa(),
                     new rup.cesky.tvary.Trojuhelnik()
                     );
        mt.zobraz();
    }

}

