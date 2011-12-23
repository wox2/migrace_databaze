/* Soubor Kap06\02\Fakt.java
   výpoèet faktoriálu - ukázka pøeteèení
   samostatná funkce pro výpoèet faktoriálu
   v cyklu ète celá èísla z konzoly a vypisuje jejich faktoriály.
   Skonèí, zadáme-li zápornou hodnotu.
*/

public class Fakt {
  public static int faktorial(int n)
  {
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
 	return s;
  }

  public static long faktorial(long n)
  {
	long s = 1;		// Promìnná s bude obsahovat výsledek
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
 	return s;
  }
  public static void main(String[] arg)
  {
    	
	int i;
	i = 'A';
	System.out.print("Zadej cele cislo: ");
	int n = MojeIO.inInt();	// Vstup hodnoty
	while(n >= 0) {
	  System.out.println("Jeho faktorial je " + faktorial((long)n));
	  System.out.print("Zadej cele cislo: ");
	  n = MojeIO.inInt();	// Vstup hodnoty
	}
  }
}