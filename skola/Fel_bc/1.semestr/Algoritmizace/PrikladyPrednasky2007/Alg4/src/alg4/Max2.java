package alg4;

import java.util.*;

public class Max2 {
  static int max(int x, int y) {
    if (x>y) return x; else return y;
  }
  static int max(int x, int y, int z) {
    return max(x, max(y, z));
  }
  static double max(double x, double y) {
    if (x>y) return x; else return y;
  }
  public static void main(String[] args) {
     System.out.println(max(3,4));
     System.out.println(max(1,2,3));
     System.out.println(max(1.0,2.4));
  }
}