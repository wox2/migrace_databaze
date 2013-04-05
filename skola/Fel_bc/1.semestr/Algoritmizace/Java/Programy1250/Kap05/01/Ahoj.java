/* Soubor Kap05\01\ahoj\Ahoj.java
 * Analogie prvního programu s použitím balíku
 * package musí být první pøíkaz, pøed ním smìjí být
 * jen prázdné øádky nebo komentáøe
 */

package pozdrav;

public class Ahoj {
  void text(){
	System.out.println("To uz tady bylo ... a uz je to tu zas.");
  }
  public static void main(String[] s)
  {
	Ahoj a = new Ahoj();
	a.text();
  }
}