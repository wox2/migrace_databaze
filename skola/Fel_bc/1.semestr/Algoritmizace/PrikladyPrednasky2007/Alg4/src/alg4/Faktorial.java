package alg4;

import java.util.*;

public class Faktorial {
  static int ctiPrirozene() {
      Scanner sc = new Scanner(System.in);
      System.out.println("zadejte pøirozené èíslo");
    int n = sc.nextInt();
    if (n<1) {
      System.out.println(n + " není pøirozené èíslo");
      System.exit(0);
    }
    return n;
  }

  static int faktorial(int n) {
    int i = 1;
    int f = 1;
    while (i<n) {
      i = i+1;
      f = f * i;
    }
    return f;
  }
  public static void main(String[] args) {
    int n = ctiPrirozene();
    System.out.println(n + "! = " + faktorial(n));
  }
}