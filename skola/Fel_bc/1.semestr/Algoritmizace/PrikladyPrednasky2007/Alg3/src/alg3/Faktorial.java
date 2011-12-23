package alg3;

import java.util.*;

public class Faktorial {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("zadejte pøirozené èíslo");
    int n = sc.nextInt();
    if (n<1) {
      System.out.println(n + " není pøirozené èíslo");
      System.exit(0);
    }
    int i = 1;
    int f = 1;
    while (i<n) {
      i = i+1;
      f = f * i;
    }
    System.out.println(n + "! = " + f);
  }
}