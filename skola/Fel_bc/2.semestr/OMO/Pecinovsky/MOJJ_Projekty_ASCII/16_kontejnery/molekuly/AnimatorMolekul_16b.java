package molekuly;

import rup.cesky.tvary.SpravcePlatna;

import java.util.Timer;
import java.util.TimerTask;

/*******************************************************************************
 * Trida AnimatorMolekul_16b definuje objekt, ktery zabezpeci
 * animaci molekul ulozenych ve statickem atributu <code>molekuly</code> 
 * tridy </code>Molekula_16b</code>.
 * Animator by mel spravne byt bud jedinacek, nebo alespon kontrolovat,
 * ze se druhy nespusti drive, nez prvni dobehne. 
 * Vzhledem k tomu, ze je urcen pouze pro testovaci ucely a bude jej
 * pouzivat pouze trida <code>Molekula_16b</code>, dopustime se maleho hrichu
 * a tyto kontroly do kodu nezahrneme. 
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
//==============================================================================

class AnimatorMolekul_16b extends TimerTask
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** SP od tridy Molekula_13d je nedosazitelne, protoze ta je dedi od 
     *  rodicovske tridy trida APosuvny, jez je deklarovala jako protected. */
    SpravcePlatna SP = SpravcePlatna.getInstance();
    

//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novy animator molekul, ktery bude s frekvenci zadanou ve tride 
     * Molekula_16b zadat moelkuly o aktulizaci jejich pozice 
     * a nasledne nechavat prekreslit platno.
     */     
    public AnimatorMolekul_16b()
    {
        new Timer().schedule( this, 0, 1000 / Molekula_16b.FREKVENCE );
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================

    /***************************************************************************
     * Metoda pozadovana tridou {@link TimerTask}. Bude spoustena casovacem
     * s pozadovanou frekvenci. Tato metoda ma na starosti vlastni animaci.
     */     
    public void run()
    {
        SP.nekresli(); {
            for( Molekula_16b m : Molekula_16b.molekuly )
            {
                m.popojed();
            }
        } SP.vratKresli();
    }



//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

