package alg4;

import java.util.*;

public class VypisZnaku {
  static void vypisZnak(char z, int n) {
    for (int i=1; i<n; i++)  System.out.print(' ');
     System.out.print(z);
  }

  public static void main(String[] args) {
    vypisZnak('a',10);
  }
}