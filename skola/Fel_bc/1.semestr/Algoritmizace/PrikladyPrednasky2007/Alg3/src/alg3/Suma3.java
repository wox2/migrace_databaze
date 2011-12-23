package alg3;
import java.util.*;

public class Suma3 {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int dalsi, suma;
    System.out.println("zadejte posloupnost èísel zakonèenou nulou");
    suma = 0;
    dalsi = sc.nextInt();
    while (dalsi!=0) {
      suma = suma+dalsi;
      dalsi = sc.nextInt();
    }
    System.out.println("souèet je " + suma);
  }
}