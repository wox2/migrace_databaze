/* Soubor Kap10\01\Fakt10a.java
 * Ještì jednou výpoèet faktoriálu, 
 * tentokrát výjimku zachytíme a ošetøíme v metodì main()
 *
 * Vyžaduje použití tøídy MojeIO pro vstup celých èísel
 * (najdete ji v Kap03\io) 
 */

public class Fakt10a {
  public static long fakt(int n){
    if((n < 0) || (n > 20)) throw new ArithmeticException();
    int s = 1;
    while(n > 1) s *= n--;
    return s;
  }
  public static void main(String[] args) {
    try {
     System.out.print("Zadej cele cislo: ");
     int i = MojeIO.inInt();
     System.out.println("Jeho faktorial je "+ fakt(i));
     System.out.println("Vypocet probehl bez problemu");
    }
    catch(ArithmeticException e){
      System.out.println("Cislo musi lezet v rozmezi od 0 do 20");
      e.printStackTrace();
    }
    System.out.println("Ferda Mravenec, prace vseho druhu");  }
}