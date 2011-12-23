/* Soubor Kap03\05\Fakt3.java
   výpoèet faktoriálu, tøetí pokus 
   samostatná funkce pro výpoèet faktoriálu.
   Verze pro JDK 5 -- používá metodu printf().
   Ve starších verzích jazyka je tøeba odstranit pøíkaz 
   System.out.printf("Jeho...);
   nahradit ho pøíkazem
   // System.out.println("Jeho..);
   který je za ním uzavøen v komentáøi
*/
public class Fakt3 {
  public static int faktorial(int n)
  {
		int s = 1;	// Promìnná s bude obsahovat výsledek
    	if(n < 0)
    	{
			System.out.println("Neni definovan");
    		System.exit(0);
    	} else {		// Cyklus, ve kterém se faktoriál vypoète
			while(n > 1)
			{
				s *= n--;
			}
		}
 		return s;
  }
  public static void main(String[] arg)
  {
   	System.out.print("Zadej cele cislo: ");
	int m = MojeIO.inInt();	// Vstup hodnoty
	System.out.printf("Jeho faktorial je %d\n", faktorial(m));
        // Ve starších verzích JDK tento pøíkaz 
        // nahradíme následujícím:
	// System.out.println("Jeho faktorial je " + faktorial(m));
  }
}
