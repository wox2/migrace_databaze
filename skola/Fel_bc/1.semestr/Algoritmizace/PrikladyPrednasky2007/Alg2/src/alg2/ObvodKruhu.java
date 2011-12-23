package alg2;

import java.util.*;



public class ObvodKruhu {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("zadejte polomìr kruhu");
    double r = sc.nextInt();
    System.out.println("obvod kruhu je "+2*Math.PI*r);
  }
}