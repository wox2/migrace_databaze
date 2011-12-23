package alg4;

import java.util.*;

public class StatickePromenne {
  
  static int x, y;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("zadejte dvì celá èísla");
    x = sc.nextInt();
    y = sc.nextInt();
    vypisSoucet();
  }

  static void vypisSoucet() {
     System.out.println("souèet èísel je "+(x+y));
  }

}
