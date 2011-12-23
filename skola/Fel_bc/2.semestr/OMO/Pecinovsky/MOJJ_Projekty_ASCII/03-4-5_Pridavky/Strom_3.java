/*******************************************************************************
 * Nasledujici definice ukazuje stav vyvoje tridy {@code Strom} 
 * na konci 3. kapitoly.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_3
{
    public Strom_3()
    {
        this( 0, 0 );
    }

    public Strom_3(int x, int y)
    {
        this( x, y, 100, 150 );
    }

    public Strom_3(int x, int y, int sirka, int vyska)
    {
        new Elipsa  ( x, y, sirka, 2*vyska/3, Barva.ZELENA  );
        new Obdelnik( x+9*sirka/20, y+2*vyska/3,
                               sirka/10, vyska/3, Barva.HNEDA );
    }

}

