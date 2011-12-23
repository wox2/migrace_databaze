package alg6;
import java.util.*;

public class ObratPole3 {
  public static void main(String[] args) {
    int[] vstupniPole = ctiPole();
    obratPole(vstupniPole);
    vypisPole(vstupniPole);
  }

  static int[] ctiPole() {
     Scanner sc = new Scanner(System.in);
     System.out.println("zadejte poèet èísel");
    int[] pole = new int[sc.nextInt()];
    System.out.println("zadejte "+pole.length+" èísel");
    for (int i=0; i<pole.length; i++)
      pole[i] = sc.nextInt();
    return pole;
  }

  static void obratPole(int[] pole) {
    int pom;
    for (int i=0; i<pole.length/2; i++) {
      pom = pole[i];
      pole[i] = pole[pole.length-1-i];
      pole[pole.length-1-i] = pom;
    }
  }

  static void vypisPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      System.out.println(pole[i]);
  }

}