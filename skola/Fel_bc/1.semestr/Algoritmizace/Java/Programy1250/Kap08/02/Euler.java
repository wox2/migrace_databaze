/* Soubor Kap08\02\Euler .java
 * Výpoèet Eulerova èísla s pøesnopstí eps
 * 
 * pøíklad na cyklus do-while
 * 
 * vytiskne vypoètenou hodnotu a 
 * správnou hodnotu uloženou v systémové konstantì Math.E
 */

public class Euler {

  public Euler() {
  }
  double pocitej(double eps)
  {
	double s = 2, d = 1;      // s je souèet, d je sèítanec
	int i = 2;          	  // Poèítá, kolikátý sèítanec
	do {
		d /= i++;         // Vypoèti další sèítanec
		s += d;           // a pøièti ho k souètu
	} while(d > eps);         // Je-li menší než eps, hotovo
	return s;
  }
  public static void main(String[] args) {
    Euler e = new Euler();
    System.out.println(e.pocitej(1e-5));
    System.out.println(Math.E);
  }
}