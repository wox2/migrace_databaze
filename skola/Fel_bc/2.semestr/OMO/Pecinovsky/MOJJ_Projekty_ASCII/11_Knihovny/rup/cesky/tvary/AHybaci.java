package rup.cesky.tvary;

/*******************************************************************************
 * Trida AHybaci je spolecnou rodicovskou tridou trid implementujicich
 * rozhrani IHybaci. 
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public abstract class AHybaci extends APosuvny 
       implements IHybaci, IPrizpusobivy
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int sirka;   //sirka v bodech
    private int vyska;   //Vyska v bodech



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori na zadanych souradnicich
     * instanci sirokou 100 bodu, vysokou 150 bodu
     * s kmenem zabirajicim 1/3 vyska a 1/10 sirky stromu.
     *
     * @param x      x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y      y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka  Sirka vytvarene instance,  sirka >= 0
     * @param vyska  Vyska vytvarene instance,  vyska >= 0
     */
    public AHybaci( int x, int y, int sirka, int vyska )
    {
        super( x, y );
        //Test platnosti parametru
        if( (sirka<0) || (vyska<0) ) {
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: sirka="
                                                + sirka + ", vyska=" + vyska );
        }
        //Parametry akceptovany --> muzeme tvorit
        this.sirka = sirka;
        this.vyska = vyska;
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati sirku instance.
     *
     * @return  sirka instance v bodech
     */
     public int getSirka()
     {
         return sirka;
     }


    /***************************************************************************
     * Vrati vysku instance.
     *
     * @return  vyska instance v bodech
     */
     public int getVyska()
     {
         return vyska;
     }


    /***************************************************************************
     * Nastavi novy vodorovny rozmer instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>=0
     */
    public void setSirka(int sirka)
    {
        setRozmer(sirka, getVyska());
    }


    /***************************************************************************
     * Nastavi novy svisly rozmer instance.
     *
     * @param vyska    Nove nastavovana vyska; vyska>=0
     */
    public void setVyska(int vyska)
    {
        setRozmer(getSirka(), vyska);
    }


    /***************************************************************************
     * Vrati instanci tridy Rozmer s rozmery instance.
     *
     * @return   Rozmer s rozmery instance.
     */
    public Rozmer getRozmer()
    {
        return new Rozmer( getSirka(), getVyska() );
    }


    /***************************************************************************
     * Nastavi novy "ctvercovy" rozmer instance -
     * na zadany rozmer se nastavi vyska i sirka.
     *
     * @param rozmer  Nove nastavovany rozmer v obou smerech; rozmer>0
     */
    public void setRozmer(int rozmer)
    {
        setRozmer( rozmer, rozmer );
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>=0
     * @param vyska    Nove nastavovana vyska; vyska>=0
     */
    public void setRozmer(int sirka, int vyska)
    {
        if( (sirka != this.sirka) || (vyska != this.vyska) ) {
            if( (sirka < 0) || (vyska < 0) ) {
                throw new IllegalArgumentException(
                    "Rozmery musi byt nezaporne: sirka=" + sirka + 
                                              ", vyska=" + vyska );
            }
            this.sirka = sirka;
            this.vyska = vyska;
            SP.prekresli();
        }
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param rozmer    Nove nastavovany rozmer.
     */
    public void setRozmer(Rozmer rozmer)
    {
        setRozmer( rozmer.sirka, rozmer.vyska );
    }


    /***************************************************************************
     * Vrati instanci tridy Oblast s informacemi o pozici a rozmerech instance.
     *
     * @return   Oblast s informacemi o pozici a rozmere instance.
     */
    public Oblast getOblast()
    {
        return new Oblast( getX(), getY(), getSirka(), getVyska() );
    }


    /***************************************************************************
     * Nastavi novou polohu a rozmery instance.
     *
     * @param o    Nove nastavovana oblast zaujimana instanci.
     */
    public void setOblast(Oblast o)
    {
        SP.nekresli(); {
            setPozice( o.x,     o.y     );
            setRozmer( o.sirka, o.vyska );
        } SP.vratKresli();
    }




//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
    
    public void krokZmenen( int stary, int novy )
    {
        double pomer = (double)novy / stary;
        SP.nekresli(); {
            setPozice( (int)Math.round(pomer*getX()), 
                       (int)Math.round(pomer*getY()) );
            setRozmer( (int)Math.round(pomer*getSirka()), 
                       (int)Math.round(pomer*getVyska()) );
        } SP.vratKresli();
    }

    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vraci textovou reprezentaci dane instance
     * pouzivanou predevsim k ladicim ucelum.
     *      
     * @return Nazev instance nasledovnay jejimi souradnicemi.
     */
    @Override
    public String toString()
    {
        return super.toString() + 
               ", sirka=" + getSirka() + ", vyska=" + getVyska();
    }


//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}


