package alg4;

import java.util.*;

public class Rok {

  static boolean prestupny(int rok) {
    if (rok%4==0 && (rok%100!=0 || rok%1000==0))
      return true;
    else
      return false;
  }
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int rok;
     System.out.println("zadejte rok");
    rok = sc.nextInt();
    System.out.print("rok "+rok);
    if (prestupny(rok))
       System.out.println(" je pøestupný");
    else
       System.out.println(" není pøestupný");
  }
}
