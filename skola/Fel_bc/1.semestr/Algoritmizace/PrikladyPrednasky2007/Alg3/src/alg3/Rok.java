package alg3;

import java.util.*;
public class Rok {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int rok;
    System.out.println("zadejte rok");
    rok = sc.nextInt();
    System.out.println("rok "+rok);
    if (rok%4==0 && (rok%100!=0 || rok%1000==0))
      System.out.println(" je pøestupný");
    else
      System.out.println(" není pøestupný");
  }

}
