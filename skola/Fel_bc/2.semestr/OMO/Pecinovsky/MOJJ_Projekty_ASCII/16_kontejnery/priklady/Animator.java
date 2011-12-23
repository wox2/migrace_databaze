package priklady;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rup.cesky.tvary.SpravcePlatna;
import rup.cesky.tvary.IKresleny;
import rup.cesky.tvary.Kreslitko;


/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public final class Animator extends TimerTask implements IKresleny
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    public static final int FREKVENCE = 30;
    
    public static final int PERIODA = 1000 / FREKVENCE;
    
    private static final Animator jedinacek = new Animator();
    
    private static final SpravcePlatna SP = SpravcePlatna.getInstance();
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    private final List<IAnimovany> animace = new ArrayList<IAnimovany>();
    
    private final Timer timer = new Timer( "Animator" );

    
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private boolean bezi = false;
    private long  minule;
    private long  uplynulo;
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     */
    private Animator()
    {
    }

    
    /***************************************************************************
     * 
     * @return Instance animatoru
     */
    public static Animator getInstance()
    {
        return jedinacek;
    }
    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
    
    public synchronized long getUplynulo()
    {
        return uplynulo;
    }
    
    
    public synchronized long getMinule()
    {
        return minule;
    }
    
    
    public synchronized long getCas()
    {
        return minule + uplynulo;
    }
    
    
    public synchronized boolean isBezi()
    {
        return bezi;
    }
    
    
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
    
    public synchronized void nakresli( Kreslitko kreslitko )
    {
        for( IAnimovany ia : animace )
        {
            ia.nakresli( kreslitko );
        }
    }
    
    
    
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
    

    /***************************************************************************
     * Metoda pozadovana tridou {@link TimerTask}. Bude spoustena casovacem
     * s pozadovanou frekvenci. Tato metoda ma na starosti vlastni animaci.
     */     
    public synchronized void run()
    {
        long cas = System.currentTimeMillis();
        uplynulo = cas - minule;
        minule = cas;
        SP.nekresli(); {
            for( Iterator<IAnimovany> it = animace.iterator();  it.hasNext();  )
            {
                IAnimovany ia = it.next();
                if( ia.popojed() ) {
                    it.remove();
                }
            }
        } SP.vratKresli();
    }
    
    
    
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
    
    /***************************************************************************
     * Ukonci multipresun a tim uvolni procesor.    
     */     
    public synchronized void stop()
    {
        if( ! bezi ) {
            return;
        }
        this.cancel();
        bezi = false;
    }
    
    
    /***************************************************************************
     * Spusti novy multipresun. 
     */     
    public synchronized void start()
    {
        if( ! bezi )
        {
            bezi = true;
            minule = System.currentTimeMillis();
            timer.schedule( this, PERIODA, PERIODA );
            //timer.scheduleAtFixedRate( this, PERIODA, PERIODA );
        }
    }
    
 
    /***************************************************************************
     * Neni-li zadany obrazec v seznamu animovanych, prida jej na konec
     * (bude se animovat jako posledni.
     *
     * @param  nova  Pridavany animovany objekt
     *
     * @return  true  v pripade, kdyz byl objekt opravdu pridan,
     *          false v pripade, kdyz jiz mezi animovanymi byl
     *                a pouze se presunul
     */
    public synchronized boolean pridej( IAnimovany nova )
    {
        if( animace.contains( nova ) )
        {
            return false;
        }
        else
        {
            animace.add( nova );
            return true;
        }
    }


    /***************************************************************************
     * Prida objekt do seznamu animovanych tak, aby byl animovan
     * pred zadanym objektem.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  stara  Objekt, ktery se ma animovat pred pridavanym objektem
     * @param  nova   Pridavany objekt
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejPred( IAnimovany stara, IAnimovany nova )
    {
        boolean nebyla = ! animace.remove( nova );
        int kam = animace.indexOf( stara );
        if( kam < 0 )
        {
            throw new IllegalArgumentException(
                "Referencni objekt neni v seznamu animovanych!" );
        }
        animace.add( kam+1, nova );
        return nebyla;
    }


    /***************************************************************************
     * Prida objekt do seznamu animovanych tak, aby byl animovan
     * po zadanem objektu.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  stara  Objekt, ktery se ma animovat po pridavanem objektu
     * @param  nova   Pridavany objekt
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejZa( IAnimovany stara, IAnimovany nova )
    {
        boolean nebyla = ! animace.remove( nova );
        int kam = animace.indexOf( stara );
        if( kam < 0 )
        {
            throw new IllegalArgumentException(
                "Referencni objekt neni v seznamu animovanych!" );
        }
        animace.add( kam, nova );
        return nebyla;
    }


    /***************************************************************************
     * Prida objekt do seznamu animovanych tak, aby byl animovan
     * jako posledni.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  nova   Pridavany objekt
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejNaKonec( IAnimovany nova )
    {
        boolean nebyla = ! animace.remove( nova );
        animace.add( nova );
        return nebyla;
    }


    /***************************************************************************
     * Prida obrazec do seznamu malovanych tak, aby byl kreslen
     * pod zadanym obrazcem.
     * Pokud jiz v seznamu byl, jenom jej presune do zadane pozice.
     *
     * @param  nova   Pridavany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu pridan,
     *          false v pripade, kdyz jiz mezi zobrazovanymi byl
     *                a pouze se presunul do jine urovne
     */
    public synchronized boolean pridejNaZacatek( IAnimovany nova )
    {
        boolean nebyla = ! animace.remove( nova );
        animace.add( 0, nova );
        return nebyla;
    }


    /***************************************************************************
     * Odebere objekt ze seznamu animovanych.
     *
     * @param  ia   Odebirany obrazec
     *
     * @return  true  v pripade, kdyz byl obrazec opravdu odebran,
     *          false v pripade, kdyz jiz mezi zobrazovanymi nebyl
     */
    public synchronized boolean odeber( IAnimovany ia )
    {
        boolean byla = animace.remove( ia );
        return byla;
    }


    /***************************************************************************
     * Vrati poradi zadaneho prvku v seznamu kreslenych prvku.
     * Prvky se pritom kresli v rostoucim poradi, takze obrazec
     * s vetsim poradim je kreslen nad obrazcem s mensim poradim.
     * Neni-li zadany obrazec mezi kreslenymi, vrati -1.
     *
     * @param  ia  Objekt, na jehoz kreslici poradi se dotazujeme
     *
     * @return  Poradi obrazce; prvy kresleny obrazec ma poradi 0.
     *          Neni-li zadany obrazec mezi kreslenymi, vrati -1.
     */
    public synchronized int poradi( IAnimovany ia )
    {
        return animace.indexOf( ia );
    }


    /***************************************************************************
     * Vrati nemodifikovatelny seznam vsech spravovanych obrazku.
     *
     * @return  Pozadovany seznam
     */
    public synchronized List<IAnimovany> getSeznam()
    {
        return Collections.unmodifiableList( animace );
    }

   
    
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

