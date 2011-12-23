package alg6;
import java.util.*;

public class ObratPole1 {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.println("zadejte poèet èísel");
    int[] pole = new int[sc.nextInt()];
    System.out.println("zadejte "+pole.length+" èísel");
    for (int i=0; i<pole.length; i++)
      pole[i] = sc.nextInt();
    System.out.println("výpis èísel v obráceném poøadí");
    for (int i=pole.length-1; i>=0; i--)
      System.out.println(pole[i]);
  }
}
