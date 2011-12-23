/*******************************************************************************
 * Trida VytahX simuluje vytah vozici pasazery
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class VytahX implements IKresleny, IPosuvny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    /** Aktivni platno, ktere dohlizi na spravne vykresleni instance. */
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet dosud vytvorenych instanci. */
    private static int pocet = 0;

    

//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;

    /** Sloupec platna, v nemz se vytah pohybuje. */
    private final int sloupec;
    
    /** Barva kabiny. */
    private final Barva barva;
    
    /** VytahX pouzije pro vsechny posuny jednoho soukromeho presouvace. */
    private final Presouvac presouvac;
    
    
    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    /** Patro, v nemz se vytah prave nachazi. */
    private int patro;
    
    private Obdelnik kabina;
    
    private IPosuvny pasazer;

    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Konstruktor definujici ve kterem sloupci bude vytah jezdit, 
     * jakou bude jezdit rychlosti a jakou bude mit barvu.
     * 
     * @param sloupec  Sloupec aktivniho platna, v nemz bude vytah jezdit.
     * @param rychlost Rychlost nastavena presouvaci, ktery bude mit dany vytah
     *                 na starosti.
     * @param barva    Barva vytahu.          
     */
    public VytahX( int sloupec, int rychlost, Barva barva )
    {
        this.sloupec = sloupec;
        this.presouvac = new Presouvac( rychlost );
        this.barva = barva;
        this.patro = 0;
        
        int krok = SP.getKrok();
        int x = sloupec * krok;
        int y = (SP.getRadku()-1) * krok;
        
        this.kabina = new Obdelnik( x, y, krok, krok, barva );
        
        SP.pridej( this );
    }


    /***************************************************************************
     * Konstruktor definujici ve kterem sloupci bude vytah jezdit, 
     * jakou bude jezdit rychlosti a jakou bude mit barvu.
     * 
     * @param sloupec  Sloupec aktivniho platna, v nemz bude vytah jezdit.
     */
    public VytahX( int sloupec )
    {
        this( sloupec, 2, Barva.CERNA );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================


    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public int getX() 
    {
        return kabina.getX();
    }    

    
    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public int getY() 
    {
        return kabina.getY();
    }


    /***************************************************************************
     * Vrati instanci tridy Pozice s pozici instance.
     *
     * @return   Pozice s pozici instance.
     */
    public Pozice getPozice()
    {
        return kabina.getPozice();
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        kabina. setPozice( x, y );
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
    public void nakresli(Kreslitko kreslitko) 
    {
        kabina .nakresli( kreslitko );
        //((IKresleny)pasazer).nakresli( kreslitko );
    }
    
    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec, v nemz bude uvedeno poradove cislo
     * vytahu, sloupec, v nemz jezdi a patro, v nemz se prave nachazi.
     *
     * @return Retezcova reprezentace dane instance.
     */
    @Override
    public String toString()
    {
        return "VytahX_" + poradi + ": sloupec=" + sloupec + ", patro=" + patro;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
     
    /***************************************************************************
     * Prijede do patra, ve kterem se nachazi pasazer.
     * odveze jej do pozadovaneho patra; pasazer zustava ve vytahu.
     * 
     * @param pasazer   Pasazer, do jehoz radku je trebaq prijet.
     */
    public void prijedK( IPosuvny pasazer )
    {
        int cil = SP.getRadku() - pasazer.getPozice().y/SP.getKrok() - 1;
        doPatra( cil  );
    }
    
   
    /***************************************************************************
     * Presune zadaneho pasazera z jeho vychozi pozice do vytahu.
     * V zajmu realnosti graficke simulace by se mel pasazer vejit do 
     * jednoho policka aktivniho platna.          
     *
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     */
    public void nastup( IPosuvny pasazer )
    {
        this.pasazer = pasazer;
        SP.pridejPod( this, (IKresleny)pasazer );
        presouvac.presunNa( pasazer, getX(), getY() );
        SP.odstran( (IKresleny) pasazer );
    }
    
   
    /***************************************************************************
     * Odjede s vytahem do zadaneho patra;
     * je-li nastoupeny pasazer, odjede i s nim.
     *
     * @param patro   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void doPatra( int patro )
    {
        presouvac.presunO( this, 0, (this.patro - patro)*SP.getKrok() );
        this.patro = patro;
    }
    
    
    /***************************************************************************
     * Presune pasazera vytahu na sousedni policko vpravo vedle vytahu.
     */
    public void vystupVpravo()
    {
        vystup( SP.getKrok() );
    }
    
    
    /***************************************************************************
     * Presune pasazera vytahu na sousedni policko vlevo vedle vytahu.
     */
    public void vystupVlevo()
    {
        vystup( -SP.getKrok() );
    }
    
     
    /***************************************************************************
     * Privola do pasazerova patra vytah, necha pasazera nastoupit,
     * odveze jej do pozadovaneho patra a tam jej necha vystoupit doprava.
     * 
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     * @param doPatra   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void odvezVpravo( IPosuvny pasazer, int doPatra )
    {
        odvez( pasazer, doPatra );
        vystupVpravo();
    }   
    
     
    /***************************************************************************
     * Privola do pasazerova patra vytah, necha pasazera nastoupit,
     * odveze jej do pozadovaneho patra a tam jej necha vystoupit doleva.
     * 
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     * @param doPatra   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    public void odvezVlevo( IPosuvny pasazer, int doPatra )
    {
        odvez( pasazer, doPatra );
        vystupVlevo();
    }   
    
    
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
    
    /***************************************************************************
     * Presune pasazera vytahu na sousedni policko vlevo vedle vytahu.
     */
    private void vystup( int posun )
    {
        int krok = SP.getKrok();
        pasazer.setPozice( getX(), getY() );
	    SP.pridejPod( this, (IKresleny) this.pasazer );
        presouvac.presunO( pasazer, posun,  0 );
        pasazer = null;
    }
    
     
    /***************************************************************************
     * Privola do pasazerova patra vytah, necha pasazera nastoupit,
     * odveze jej do pozadovaneho patra; pasazer zustava ve vytahu.
     * 
     * @param pasazer   Gemetricky tvar, ktery bude zastupovat pasazera.
     * @param doPatra   Poradove cislo ciloveho patra (prizemi ma 0).
     */
    private void odvez( IPosuvny pasazer, int doPatra )
    {
        prijedK( pasazer );
        nastup ( pasazer );
        doPatra( doPatra );
    }
    


//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

