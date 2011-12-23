package pascal;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class Pascal_17c
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    private static final String mezery = "                                    ";
    private static final int delkaBloku = mezery.length();
        
    
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
    
    int[][] trojuhelnik;
//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * 
     * @param n Pocet radkpu vytvareneho trojuhelniku
     */
    public Pascal_17c(int n)
    {
        if( n <= 0 ) {
            throw new IllegalArgumentException(
                "Trojuhelnik musi mit kladny pocet radku - zadano " + n );
        }
        
        trojuhelnik = new int[n][];
        trojuhelnik[0] = new int[] { 1 };
        if( n == 1 )  { return; }     //====================>
        
        trojuhelnik[1] = new int[] { 1, 1 };
        for( int r = 2;   r < n;   r++ )
        {
            int[] radek = new int[r+1];
            radek[0] = radek[r] = 1;
            for( int s=1;   s < r;   s++ ) {
                radek[s] = trojuhelnik[r - 1][s - 1] + trojuhelnik[r - 1][s];
            }
            trojuhelnik[r] = radek;
        }
    }

    

//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================
//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================
    
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer( 
                          2 * trojuhelnik.length*trojuhelnik.length );
        sb.append("START\n");
        int radku    = trojuhelnik.length;
        int maxCifer = cifer( trojuhelnik[radku - 1][radku/2] ) + 1;
        if( (maxCifer&1) != 0 ) {
            maxCifer++;
        }
        int poslZnaku = radku * maxCifer;
        
        for( int r=0;   r < radku;   r++ )
        {
            int pocMezer = (poslZnaku - r*maxCifer) / 2;
            pridejMezer( pocMezer, sb );
            int[] radek = trojuhelnik[ r ];
            for( int hodnota : radek ) {
                sb.append(mezery.substring(
                          delkaBloku - (maxCifer - cifer(hodnota))))
                  .append(hodnota);
            }
            sb.append( "\n" );
        }
        sb.append( "KONEC" );
        return sb.toString();
    }
    
    
    
//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
    
    private int cifer( int cislo )
    {
        int cislic = 1;
        while( (cislo /= 10) > 0 ) {
            cislic++;
        }
        return cislic;
    }
    
    private void pridejMezer( int pocet, StringBuffer sb )
    {
        while( delkaBloku < pocet )
        {
            sb.append( mezery );
            pocet -= delkaBloku;
        }
        if( pocet > 0 ) {
            sb.append(mezery.substring(delkaBloku - pocet));
        }
    }
    
    
    
    
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

    public static void test( int n )
    {
        System.out.println( new Pascal_17c( n )        );
    }
    
    public static void test6()
    {
        test( 6 );
    }
}

