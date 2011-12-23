import rup.cesky.tvary.AHybaci;
import rup.cesky.tvary.Kreslitko;
import rup.cesky.tvary.Cara;
import rup.cesky.tvary.IKresleny;

/*******************************************************************************
 * Instance tridy {@code Anonymni} ukazuji, jak je mozno definovat metodu
 * ktera nakresli obdelnik opsany plose daneho objektu.
 *
 * @author    Rudolf PECINOVSKY
 * @version   0.00.000
 */
public class Anonymni extends AHybaci
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private IKresleny obrys;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI NESOUKROME METODY TRIDY ===========================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Instance bez rozmeru - je pouze zakladem budouciho obrazce.
     */
    public Anonymni()
    {
        super(0, 0, 0, 0 );
    }


//== ABSTRAKTNI METODY =========================================================
//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== OSTATNI NESOUKROME METODY INSTANCI ========================================
    
    /***************************************************************************
     * Zobrazi obdelnik opsany plose zaujimane danym obrazcem.
     * 
     * @throws IllegalStateException Ma-li se obdelnik zobrazit pred tim,
     *                               nez byl predchozi smazan
     */
    public void ukazObrys() 
    {
        if( obrys != null ) {
            throw new IllegalStateException(
                    "Obrys plochy neni jeste odstranen");
        }
        int x0 = getX();
        int x1 = x0 + getSirka();
        int y0 = getY();
        int y1 = y0 + getVyska();
        final Cara horni = new Cara(x0, y0, x1, y0);
        final Cara dolni = new Cara(x0, y1, x1, y1);
        final Cara leva  = new Cara(x0, y0, x0, y1);
        final Cara prava = new Cara(x1, y0, x1, y1);
        
        obrys = new IKresleny() {
            public void nakresli(Kreslitko kreslitko) {
                horni.nakresli(kreslitko);
                dolni.nakresli(kreslitko);
                leva .nakresli(kreslitko);
                prava.nakresli(kreslitko);
            }
        };
        SP.pridej(obrys);
    }

    
    /***************************************************************************
     * Smazani zobrazeneho opsaneho obdelnika.
     */
    public void smazObrys() 
    {
        SP.odstran(obrys);
        obrys = null;
    }

    
    /** {@inheritDoc} */
    public void nakresli(Kreslitko kreslitko) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    /***************************************************************************
     * Testovaci metoda.
     */
    public static void test()
    {
        Anonymni a = new Anonymni();
        a.setRozmer(100, 200);
        a.setPozice(25, 25);
        a.ukazObrys();
    }
    /** @param args Parametry prikazoveho radku - nepouzivane. */
    public static void main( String[] args )  {  test();  }
}

