package alg6;

import java.util.*;
public class Matice {
  public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     System.out.println("zadejte poèet øádkù a poèet sloupcù matice");
    int r = sc.nextInt();
    int s = sc.nextInt();
    int[][] m1 = ctiMatici(r, s);
    int[][] m2 = ctiMatici(r, s);
    int[][] m3 = soucetMatic(m1, m2);
    System.out.println("souèet matic");
    vypisMatice(m3);
  }

  static int[][] ctiMatici(int r, int s) {
    Scanner sc = new Scanner(System.in);
    int[][] m = new int[r][s];
    System.out.println("zadejte celoèíslenou matici "+r+"x"+s);
    for (int i=0; i<r; i++)
      for (int j=0; j<s; j++)
        m[i][j] = sc.nextInt();
    return m;
  }

  static int[][] soucetMatic(int[][] m1, int[][] m2) {
    int r = m1.length;
    int s = m1[0].length;
    int[][] m = new int[r][s];
    for (int i=0; i<r; i++)
      for (int j=0; j<s; j++)
        m[i][j] = m1[i][j]+m2[i][j];
    return m;
  }

  static void vypisMatice(int[][] m) {
    for (int i=0; i<m.length; i++) {
      for (int j=0; j<m[i].length; j++)
       System.out.print(m[i][j]+" ");
      System.out.println();
    }
  }

}
