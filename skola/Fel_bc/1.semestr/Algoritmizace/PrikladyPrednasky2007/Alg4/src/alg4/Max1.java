package alg4;



public class Max1 {
  static int max(int x, int y) {
    if (x>y) return x; else return y;
  }

  public static void main(String[] args) {
    int a = 10, b = 20;
    System.out.println(max(a+20, b));	// O.K.
//  System.out.println(max(1.1, b));	// Chyba pøi pøekladu
  }
}