/* Soubor Kap10\01\Fakt10.java

 * Opìt faktoriál (to tu už dlouho nebylo), 
 * ale tentokrát nejprve testuje, zda leží parametr
 * v rozmezí 0 - 20, jinak vyvolá výjimku.
 * (Pro n < 0 není faktoriál definován, pro n > 20 
 * se výsledek nevejde do rozsahu typu long.) 
 * Metoda je volána se špatným parametrem, a proto skonèí
 * výjimkou.
 */

public class Fakt10 {
  public static long fakt(int n){
    if((n < 0) || (n > 20)) throw new ArithmeticException();
    int s = 1;
    while(n > 1) s *= n--;
    return s;
  }
  public static void main(String[] args) {
    System.out.print(fakt(-5));
  }
}