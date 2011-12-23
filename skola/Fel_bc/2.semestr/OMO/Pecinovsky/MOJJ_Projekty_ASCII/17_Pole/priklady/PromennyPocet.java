package priklady;

/*******************************************************************************
 * Trida Sbl_Trida slouzi k ...
 *
 * @author    Rudolf Pecinovsky
 * @version   0.00.000,  0.0.2003
 */
public class PromennyPocet
{
    public static void vypustka( String txt, int... ii )
    {
        System.out.print( txt );
        for( int i : ii ) {
            System.out.print(" - " + i);
        }
        System.out.println();
    }


    public static void vektor( String txt, int[] ii )
    {
        System.out.print( txt );
        for( int i : ii ) {
            System.out.print(" - " + i);
        }
        System.out.println();
    }
    
    
    public static void test()
    {
        vypustka( "...", 1, 3, 5, 7 );
        vypustka( "...[]", new int[]{ 1, 3, 5, 7 } );
        
        vektor  ( "[] ", new int[]{ 1, 3, 5, 7 } );
        vypustka( "...[]", new int[]{ 1, 3, 5, 7 } );
//        vektor  ( "[]... ", 1, 3, 5, 7  );    
    }

    private PromennyPocet() {}

}

