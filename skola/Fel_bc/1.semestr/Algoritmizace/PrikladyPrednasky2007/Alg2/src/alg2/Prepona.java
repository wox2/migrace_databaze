package alg2;

import java.util.*;



public class Prepona {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
   System.out.println("zadejte délku odvìsen pravoúhlého trojúhelníka");
    double x = sc.nextDouble();
    double y = sc.nextDouble();
    double z = Math.sqrt(x*x+y*y);
   System.out.println("délka pøepony je "+z);
  }
}