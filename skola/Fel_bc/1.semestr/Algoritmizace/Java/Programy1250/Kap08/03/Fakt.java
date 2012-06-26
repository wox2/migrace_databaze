/* Soubor Kap07\03\Fakt.java
   Výpoèet faktoriálu, tentokrát pomoci for
   samostatná funkce pro výpoèet faktoriálu
   Využívá složeb tøídy MojeIO, takže je tøeba mít v adresáøi,
   do kterého ukazuje CLASSPATH, soubor MojeIO.class

   Neobsahuje žádné kontroly správnosti vstupu
*/

public class Fakt {
  public static int faktorial(int n)
  {
	int s = 1;		// Promìnná s bude obsahovat výsledek
        for(int i = n; i > 0; i--) s *= i;
 	return s;
  }
  public static void main(String[] arg)
  {
    	System.out.print("Zadej cele cislo: ");
	int n = MojeIO.inInt();	// Vstup hodnoty
	System.out.println("Jeho faktorial je " + faktorial(n));
  }
}