/* Soubor Kap03\05\Fakt2.java
   výpoèet faktoriálu, druhý pokus 
*/

public class Fakt2 {
  public static void main(String[] arg)
  {
    	System.out.print("Zadej cele cislo: ");
	int n = MojeIO.inInt();	// Vstup hodnoty
	int s = 1;		// Promìnná s bude obsahovat výsledek
    	if(n < 0)
    	{
		System.out.println("Neni definovan");
    		System.exit(0);
    	} else {
		while(n > 1)	// Cyklus, ve kterém se faktoriál vypoète
		{
			s *= n--;
		}
	}
	System.out.println("Jeho faktorial je " + s);
  }
}