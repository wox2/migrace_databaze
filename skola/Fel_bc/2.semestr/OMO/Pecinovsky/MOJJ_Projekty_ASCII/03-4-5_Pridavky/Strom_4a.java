/*******************************************************************************
 * Trida {@code Strom_4a} obsahuje definice ze 4. kapitoly 
 * vytvorenych do podkapitoly "Definujeme vlastni metodu" vcetne.
 * 
 * Oproti tride {@link Strom_4a}:
 * + Pribyly metody nakresli(), smaz(), 
 * + Pribylo zjistovani a nastavovani pozice spolu s pounovymi metodami
 * + Pribylo zjistovani a nastavovani barvy koruny
 * + Pribyla metoda getVyska() zjistujici vysku stromu
 * 
 * Nejsou zde jeste definovany metody, jejichz vytvoreni dostali ctenari za ukol
 * a v definici je chyba, ktera je pozdeji v textu vysvetlena a opravena.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_4a
{
    public  Elipsa   koruna;
    private Obdelnik kmen;


    public Strom_4a()
    {
        this( 0, 0 );
    }

    public Strom_4a(int x, int y)
    {
        this( x, y, 100, 150 );
    }

    public Strom_4a(int x, int y, int sirka, int vyska)
    {
        koruna = new Elipsa  ( x, y, sirka, 2*vyska/3, Barva.ZELENA  );
        kmen   = new Obdelnik( x+9*sirka/20, y+2*vyska/3,
                               sirka/10, vyska/3, Barva.HNEDA );
    }

    public void nakresli()
    {
        koruna.nakresli();
        kmen  .nakresli();
    }

    public void smaz()
    {
        koruna.smaz();
        kmen  .smaz();
    }


    public void posunVpravo(int n)
    {
        koruna.posunVpravo( n );
        kmen  .posunVpravo( n );
    }

    public void posunDolu( int n )
    {
        koruna.posunDolu( n );
        kmen  .posunDolu( n );
        koruna.nakresli();
    }

    public void setPozice( int x, int y )
    {
        koruna.setPozice( x, y );
        kmen  .setPozice( x  +  (koruna.getSirka() - kmen.getSirka()) / 2,
                          y  +   koruna.getVyska()   );
        koruna.nakresli();
    }

    public void posunVpravo()
    {
        koruna.posunVpravo();
        kmen  .posunVpravo();
    }

    public void posunVlevo()
    {
        koruna.posunVlevo();
        kmen  .posunVlevo();
    }

    public void posunVzhuru()
    {
        koruna.posunVzhuru();
        kmen  .posunVzhuru();
    }

    public void posunDolu()
    {
        koruna.posunDolu();
        kmen  .posunDolu();
    }

    public int getX()
    {
        return koruna.getX();
    }

    public int getVyska()
    {
        return koruna.getVyska() + kmen.getVyska();
    }

    public Barva getBarvaKoruny()
    {
        return koruna.getBarva();
    }

    public void setBarvaKoruny( Barva nova )
    {
        koruna.setBarva( nova );
    }

}

