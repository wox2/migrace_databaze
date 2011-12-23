package doprava;

import rup.cesky.utility.IO;

import rup.cesky.tvary.Barva;
import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.Elipsa;
import rup.cesky.tvary.IPosuvny;
import rup.cesky.tvary.IMultiposuvny;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Multipresouvac;
import rup.cesky.tvary.Pozice;
import rup.cesky.tvary.Text;


/*******************************************************************************
 * Instance tridy Kabina_9a predstavuji kabiny pohybujici se po linkach.
 *
 * @author  Rudolf Pecinovsky
 * @version 1.00,  29.02.2004
 */
public class Kabina_9a implements IMultiposuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final Barva BARVA   = Barva.CERNA;
    private static final Barva BARVAX  = Barva.ZLUTA;
    private static final int   PRUMER  = 20;            
    private static final int   POLOMER = PRUMER / 2;
    private static final int   XPOSUN  = 6;             
    
    private static final SpravcePlatna  SP = SpravcePlatna.getInstance();
    private static final Multipresouvac mp = Multipresouvac.getInstance();
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int pocet = 0;


//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;
    
    /** Linka, po niz se kabina pohybuje - 
     *  od ni si pri zadosti o presun zjistuje svoji rychlost
     *  a v zastavkach pak dobu cekani. */
    private final Linka linka;
     
    /** Obrazec, ktery predstavuje kabinu. 
     *  Obecne to muze byt libovolny posuvny obrazec */
    private final IPosuvny kabina;
    
    private final Text text;
    


//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    /** Zastavka, na kterou se kabina presouva. Az tam dojede, zjisti si od ni,
     *  kdo je jeji naslednik a ke k tomu se pak presune. */
    private IZastavka dalsi;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori kruhovou kabinu, ktera se bude pohybovat zadanou rychlosti
     * po zadane lince.
     * 
     * @param linka    Linka, po ktere se kabina pohybuje.
     */
    public Kabina_9a(Linka linka)
    {
        this.linka = linka;
        
        //Souradnice jeste nezname => vytvorime kruh napr. v pocatku
        kabina = new Elipsa( 0, 0, PRUMER, PRUMER, BARVA );
        text   = new Text( ""+poradi, 0, 0, BARVAX );
        
        //Kruh umistime na souradnice prvni zastavky linky
        dalsi = linka.getPrvni();
        Pozice pozice = dalsi.getPozice();
        setPozice( pozice.x, pozice.y );
        
        //Kdyz je kabina umistena, muzeme ji zobrazit
        //(i kdyz bychom to mohli nechat na multipresouvaci)
        SpravcePlatna.getInstance().pridej( this );

        //Nechame kabinu presunout na dalsi zastavku
        presunuto();
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        Pozice p = kabina.getPozice();
        return new Pozice( p.x + POLOMER,  p.y + POLOMER );
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice( int x, int y )
    {
        SP.nekresli(); {
            kabina.setPozice( x-POLOMER,   y-POLOMER   );
            text.setPozice  ( x-POLOMER/2, y-POLOMER/2 );
        } SP.vratKresli();
    }
    

    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param pozice   Nova pozice instance
     */
    public void setPozice(Pozice pozice)
    {
        setPozice( pozice.x, pozice.y );
    }

    

//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================

    /***************************************************************************
     * Za pomoci dodaneho kreslitka vykresli obraz sve instance
     * na animacni platno.
     *  
     * @param kreslitko   Kreslitko, kterym se instance nakresli na platno.     
     */
    public void nakresli( Kreslitko kreslitko )
    {
        kabina.nakresli( kreslitko );
        text.  nakresli( kreslitko );
    }


    /***************************************************************************
     * Metoda vyzadovana rozhranim IMultiposuvny: definuje akci, 
     * ktera se ma provest v okamziku, 
     * kdy je objekt doveden do pozadovane cilove pozicie.
     */
    public void presunuto()
    {
        //Zastavka, kam jsme dorazili, musi znat sveho nasledovnika
        dalsi = dalsi.getNasledujici();
        
        IO.cekej( linka.getCekani() );
        
        //Nechame kabinu presunout na dalsi zastavku
        mp.presun( this,  dalsi.getPozice(),  linka.getRychlost() );
    }



//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "_" + poradi;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
    
    /***************************************************************************
     * 
     */
    public void skonci()
    {
        mp.zastav( this );
        SP.odstran( this );
    }
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

