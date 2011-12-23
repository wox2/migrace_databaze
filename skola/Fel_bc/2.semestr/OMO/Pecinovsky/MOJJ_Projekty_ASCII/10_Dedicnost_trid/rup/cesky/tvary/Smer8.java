package rup.cesky.tvary;

import rup.cesky.utility.IO;

import java.util.HashMap;
import java.util.Map;

//Import pro test
//import java.util.ArrayList;
//import java.util.List;


/*******************************************************************************
 * Trida {@code Smer8} slouzi jako vyctovy typ pro 8 hlavnich svetovych stran.
 *
 * @author   Rudolf PECINOVSKY
 * @version  3.00.002
 */
public enum Smer8
{
//== HODNOTY VYCTOVEHO TYPU ====================================================

    VYCHOD      (  1,  0, "S"  ),
    SEVEROVYCHOD(  1, -1, "SV" ),
    SEVER       (  0, -1, "S"  ),
    SEVEROZAPAD ( -1, -1, "SZ" ),
    ZAPAD       ( -1,  0, "Z"  ),
    JIHOZAPAD   ( -1,  1, "JZ" ),
    JIH         (  0,  1, "J"  ),
    JIHOVYCHOD  (  1,  1, "JV" ),
    ZADNY       (  0,  0, "@"  ),
    ;


//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Celkovy pocet definovanych smeru. */
    public  static final int SMERU = 9;

    /** Maska pro deleni modulo. */
    private static final int MASKA = 7;
    
    /** Odmocnina z jedne poloviny. */
    private static final double SQR = Math.sqrt( 0.5 );

    /** Vsechny pouzitelne nazvy smeru. */
    private static final Map<String,Smer8> nazvy =
                                        new HashMap<String,Smer8>( SMERU*3 );

    /** Vektor jednotlivych smeru. */
    private static final Smer8[] SMERY = values();

    
    //Inicializace statickych atributu je nerealizovatelna pred
    //definici jednotlivych hodnot ==> je ji proto potreba realizovat dodatecne
    static
    {
        for( Smer8 s : SMERY )
        {
            nazvy.put( s.prepravka.zkratka, s );
            nazvy.put( s.name(),            s );
            String bhc = IO.odhackuj( s.name() );   //bhc = bez hacku a carek
            if( ! s.name().equals( bhc ) ) {
                nazvy.put(bhc, s);
            }
            s.prepravka = null;            //Prepravka uz nebude potreba
        }
    }

//== PROMENNE ATRIBUTY TRIDY ===================================================
    
    /** Priznak povoleni operaci se smerem ZADNY. */
    private static boolean zadnyZakazan = false;
    
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Velikost zmeny prislusne slozky souradnic po presunu 
     *  na sousedni policko v danem smeru. */
    private final int dx, dy;
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================

    /**************************************************************************
     * Prepravka slouzi k docasnemu uchovani hodnot parametru konstruktoru
     * do doby, nez jimi budou moci byt inicializovany staticke atributy.     
     */
    private static class Prepravka
    {
        String zkratka;
    }
    Prepravka prepravka;


//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================
    
    /***************************************************************************
     * Nastavi, zda budou povoleny operace se smerem zadny. 
     * Nejsou-li operace povoleny, vyhazuji u tohoto smeru vyjimku
     * {@link java.lang.iIllegalStateException}.
     * Jsou-li operace povoleny, pak objekt natoceny do smeru {@link #ZADNY}
     * zustava v romto "smeru" po jakemkoliv otoceni 
     * a pri jakekmkoliv presunu zustava na miste.
     * @param zakazat {@code true} maji-li se operace zakazat,
     *                {@code false} maji-li se povolit
     * @return Puvodni nastaveni tohoto priznaku
     */
    public static boolean zakazatZadny(boolean zakazat) {
        boolean puvodni = ! zadnyZakazan;
        zadnyZakazan = zakazat;
        return puvodni;
    }
    
    
//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================
    
    /**************************************************************************
     * Vrati smer se zadanym nazvem nebo zkratkou.
     * Bohuzel neni mozno pouzit definovat jako valueOf(), 
     * protoze tuto metodu definuje prekladac v teto tride
     * takze ji neni mozno prekryt vlastni verzi.
     * 
     * @param nazev Nazev pozadovaneho smeru nebo jeho zkratka
     * @return Pozadovany smer
     */
    public static Smer8 getSmer8( String nazev )
    {
       Smer8 ret = nazvy.get(nazev.toUpperCase() );
       if( ret == null ) {
            throw new IllegalArgumentException("Nepovoleny nazev smeru");
        }
       return ret;
    }
    

    /**************************************************************************
     * Vytvori novy smer a zapamatuje si zkratku jeho nazvu
     * spolu se zmenami souradnic pri pohybu v danem smeru.
     *
     * @param dx    Zmena vodorovne souradnice 
     *              pri presunu na sousedni policko v danem smeru.
     * @param dy    Zmena svisle souradnice 
     *              pri presunu na sousedni policko v danem smeru.
     * @param zkratka   Jedno- ci dvoj-pismenna zkratka oznacujici dany smer.
     */
    private Smer8( int dx, int dy, String zkratka )
    {
        this.dx = dx;
        this.dy = dy;
        prepravka = new Prepravka();
        prepravka.zkratka = zkratka;
    }


//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== VLASTNI METODY INSTANCI ===================================================

    /**************************************************************************
     * Vrati pozici sousedniho policka v danem smeru.
     *
     * @param pozice  Pozice stavajiciho policka
     *
     * @return Pozice sousedniho policka v danem smeru
     */
    public Pozice dalsiPozice( Pozice pozice )
    {
        overPlatny();
        return new Pozice( pozice.getX() + dx,
                           pozice.getY() + dy );
    }


    /**************************************************************************
     * Vrati x-vou souradnici sousedniho policka v danem smeru.
     *
     * @param x x-ova souradnice stavajiciho policka.
     * @return x-ova souradnice policka po presunu o jedno pole v danem smeru.
     */
    public int dalsiX( int x )
    {
        overPlatny();
        return x + dx;
    }


    /**************************************************************************
     * Vrati x-ovou (vodorovnou) souradnici policka
     * vzdaleneho v danem smeru o zadanou vzdalenost.
     *
     * @param x            x-ova souradnice stavajiciho policka
     * @param vzdalenost   Vzdalenost policka v danem smeru
     * @return x-ova souradnice vzdaleneho policka
     */
    public double dalsiX( int x, int vzdalenost )
    {
        overPlatny();
        if( (dx != 0)  &&  (dy != 0) ) {
            return x + SQR*dx*vzdalenost;
        } else {
            return x + dx*vzdalenost;
        }
    }


    /**************************************************************************
     * Vrati y-vou souradnici sousedniho policka v danem smeru.
     *
     * @param y y-ova souradnice stavajiciho policka
     *
     * @return y-ova souradnice sousedniho policka v danem smeru
     */
    public int dalsiY( int y )
    {
        overPlatny();
        return y + dy;
    }


    /**************************************************************************
     * Vrati y-ovou (svislou) souradnici policka
     * vzdaleneho v danem smeru o zadanou vzdalenost.
     *
     * @param y            x-ova souradnice stavajiciho policka
     * @param vzdalenost   Vzdalenost policka v danem smeru
     * @return y-ova souradnice vzdaleneho policka
     */
    public double dalsiY( int y, int vzdalenost )
    {
        overPlatny();
        if( (dx != 0)  &&  (dy != 0) ) {
            return y + SQR*dy*vzdalenost;
        } else {
            return y + dy*vzdalenost;
        }
    }


    /**************************************************************************
     * Vrati zmenu x-ove souradnice pri presunu na sousedni pole v danem smeru.
     *
     * @return Zmena x-ove souradnice pri presunu o jedno pole v danem smeru
     */
    public int dx()
    {
        overPlatny();
        return dx;
    }


    /**************************************************************************
     * Vrati zmenu y-ove souradnice pri presunu na sousedni pole v danem smeru.
     *
     * @return Zmena y-ove souradnice pri presunu o jedno pole v danem smeru
     */
    public int dy()
    {
        overPlatny();
        return dy;
    }


    /**************************************************************************
     * Vrati smer otoceny o 45 vlevo.
     *
     * @return Smer objektu po vyplneni prikazu nalevo vpric.
     */
    public Smer8 nalevoVpric()
    {
        overPlatny();
        if( this == ZADNY ) {
            return this;
        } else {
            return SMERY[MASKA & (1+ordinal())];
        }
    }


    /**************************************************************************
     * Vrati smer otoceny o 45 vpravo.
     *
     * @return Smer objektu po vyplneni prikazu napravo vpric.
     */
    public Smer8 napravoVpric()
    {
        overPlatny();
        if( this == ZADNY ) {
            return this;
        } else {
            return SMERY[MASKA & (-1+ordinal())];
        }
    }


    /**************************************************************************
     * Vrati smer otoceny o 90 vlevo.
     *
     * @return Smer objektu po vyplneni prikazu vlevo v bok.
     */
    public Smer8 vlevoVbok()
    {
        overPlatny();
        if( this == ZADNY ) {
            return this;
        } else {
            return SMERY[MASKA & (2+ordinal())];
        }
    }


    /**************************************************************************
     * Vrati smer otoceny o 90 vpravo.
     *
     * @return Smer objektu po vyplneni prikazu vpravo v bok
     */
    public Smer8 vpravoVbok()
    {
        overPlatny();
        if( this == ZADNY ) {
            return this;
        } else {
            return SMERY[MASKA & (-2+ordinal())];
        }
    }


    /**************************************************************************
     * Vrati smer otoceny o 180.
     *
     * @return Smer objektu po vyplneni prikazu celem vzad.
     */
    public Smer8 celemVzad()
    {
        overPlatny();
        if( this == ZADNY ) {
            return this;
        } else {
            return SMERY[MASKA & (4+ordinal())];
        }
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================

    /***************************************************************************
     * Overi, ze se nejedna o operaci, 
     * kterou neni mozno provadet se smerem ZADNY.
     * 
     * @throws IllegalStateException - Jedna se o operaci zakazanou 
     *                                 pro smer ZADNY.          
     */
    private void overPlatny()
    {
        if( zadnyZakazan  &&  (this == ZADNY) ) {
            throw new IllegalStateException(
                    "Operaci neni mozno provadet nad smerem ZADNY" );
        }
    }



//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
////+ main    
////Potrebuje import
////import java.util.ArrayList;
////import java.util.List;
//    
//    public static void main( String[] args )
//    {
//        List  smery = new ArrayList();
//        for( Smer8 s : Smer8.values() )
//        {
//            System.out.println(s + " - vlevo:"  + s.vlevoVbok()   +
//                                   " - vpravo:" + s.vpravoVbok()  +
//                                   " - vzad:"   + s.celemVzad()   + 
//                                   " - nale:"   + s.nalevoVpric() +
//                                   " - napra:"  + s.napravoVpric());
//            System.out.println("       dx:"           + s.dx()   +
//                                   " - dy:"           + s.dy()   +
//                                   " - dalsiX(0):"    + s.dalsiX(0)   + 
//                                   " - dalsiY(0):"    + s.dalsiY(0)   + 
//                                   " - dalsiX(0,10):" + s.dalsiX(0,10)  + 
//                                   " - dalsiY(0,10):" + s.dalsiY(0,10)  );
//        }
//    }
////- main    
}


