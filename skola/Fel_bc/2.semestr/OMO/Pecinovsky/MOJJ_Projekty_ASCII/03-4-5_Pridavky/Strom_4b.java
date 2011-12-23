/*******************************************************************************
 * Trida {@code Strom_4b} obsahuje definice metod z treti kapitoly ucebnice
 * vytvorene pred pridanim komentaru.
 * 
 * Oproti tride {@link Strom_4a}:
 * + chodi vsechny posunove metody
 * + pribyly staticke metody vracejici a nastavujici hodnotu
 *   statickeho parametru krok 
 * + bezparametricke metody volaji sve parametricke verze a posouvaji
 *   se o hodnotu ulozenou ve statickem atributu krok
 * + pribyl konstruktor s definovanym podilem vysky koruny a stromu 
 *   a podilem sirky stromu a kmene
 * + pribyla metoda zaramuj() 
 * + pribyly staticke metody zaramuj(int,int)  a alej()
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Strom_4b 
{
    public static final int IMPLICITNI_POMER_SIRKY = 10;
    public static final int IMPLICITNI_POMER_VYSKY =  3;
    
    private static int krok = 50;
    
    private final Elipsa   koruna;
    private final Obdelnik kmen;
  
  
    public Strom_4b()
    {
        this( 0, 0 );
    }

    public Strom_4b( int x, int y )
    {
        this( x, y, 100, 150 );
    }

    public Strom_4b( int x, int y, int sirka, int vyska )
    {
        this( x, y, sirka, vyska, 
            IMPLICITNI_POMER_SIRKY, IMPLICITNI_POMER_VYSKY );
    }

//
//    public Strom_4b( int x, int y, int sirka, int vyska )
//    {
//        int v3 = vyska / 3;
//        koruna = new Elipsa  ( x, y, sirka, 2*v3, Barva.ZELENA  );
//        kmen   = new Obdelnik( x+9*sirka/20, y+2*v3, 
//                               sirka/10, v3, Barva.HNEDA );
//    }
//

    public Strom_4b( int x, int y, int sirka, int vyska, 
                  int podilSirkyKmene, int podilVyskyKmene )
    {
        int vyskaKmene  = vyska / podilVyskyKmene;
        int vyskaKoruny = vyska - vyskaKmene;
        int sirkaKmene  = sirka / podilSirkyKmene;
        int posunKmene  = ( sirka - sirkaKmene) / 2;
        koruna = new Elipsa  ( x, y, sirka, vyskaKoruny, Barva.ZELENA  );
        kmen   = new Obdelnik( x+posunKmene, y+vyskaKoruny, 
                               sirkaKmene, vyskaKmene, Barva.HNEDA );
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
                          y  +  koruna.getVyska()     ); 
        koruna.nakresli();
    }

    public void posunVpravo()
    {
        posunVpravo( krok );
    }

    public void posunVlevo()
    {
        posunVpravo( -krok );
    }
    
    public void posunVzhuru()
    {
        posunDolu( -krok );
    }
    
    public void posunDolu()
    {
        posunDolu( krok );
    }
    
    public int getX()
    {
        return koruna.getX();
    }

    public int getSirka()
    {
        return koruna.getSirka();
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

    public void zaramuj()
    {
        Platno.getInstance().setRozmer( getSirka(), getVyska() );
        setPozice( 0, 0 );
    }

    public static int getKrok()
    {
        return krok;
    }
    
    public static void setKrok( int krok )
    {
        Strom_4b.krok = krok;
    }
    
    public static void setKrok()
    {
        krok = IO.zadej( "Zadejte novou velikost kroku:", krok );
    }

    public static void zaramuj( int sirka, int vyska )
    {
        Platno.getInstance().setRozmer( sirka, vyska );
        new Strom_4b( 0, 0, sirka, vyska );
    }

    public static void alej()
    {
        Platno.getInstance().setRozmer( 400, 350 );
        new Strom_4b( 100,   0 );      new Strom_4b( 300,   0 );
        new Strom_4b(  50, 100 );      new Strom_4b( 250, 100 );
        new Strom_4b(   0, 200 );      new Strom_4b( 200, 200 );
    }
} 

