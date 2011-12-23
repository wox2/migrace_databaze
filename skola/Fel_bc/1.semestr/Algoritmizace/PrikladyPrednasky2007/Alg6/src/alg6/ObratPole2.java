package alg6;
import java.util.*;

public class ObratPole2 {
  public static void main(String[] args) {
    int[] vstupniPole = ctiPole();
    int[] vystupniPole = obratPole(vstupniPole);
    vypisPole(vystupniPole);
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

  static int[] obratPole(int[] pole) {
    int[] novePole = new int[pole.length];
    for (int i=0; i<pole.length; i++)
      novePole[i] = pole[pole.length-1-i];
    return novePole;
  }

  static void vypisPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      System.out.println(pole[i]);
  }

}