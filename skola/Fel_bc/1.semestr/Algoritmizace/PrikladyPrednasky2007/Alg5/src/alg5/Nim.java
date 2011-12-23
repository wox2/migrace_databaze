package alg5;
import java.util.*;

public class Nim {
  static int pocet;     // aktuální poèet zápalek
  static boolean stroj; // =true znamená, že bere poèítaè

  public static void main(String[] args) {
    zadaniPoctu();
    stroj = false;  // zacina hrac
    do {
      if (stroj) bereStroj(); else bereHrac();
      stroj = !stroj;
    } while (pocet>0);
    if (stroj)
      System.out.println("vyhrál jsem");
    else
      System.out.println("vyhrál jste, gratuluji");
  }

  static void zadaniPoctu() {
       Scanner sc = new Scanner(System.in);
      do {
      System.out.println("zadejte poèet zápalek (od 15 do 35)");
      pocet = sc.nextInt();
    } while (pocet<15 || pocet>30);
  }

  static void bereHrac() {
    Scanner sc = new Scanner(System.in);
      int x;
    boolean chyba;
    do {
      chyba = false;
      System.out.println("poèet zápalek "+pocet);
      System.out.println("kolik odeberete");
      x = sc.nextInt();
      if (x<1) {
        System.out.println("prilis malo");
        chyba = true;
      }
      else
      if (x>3 || x>pocet) {
        System.out.println("prilis mnoho");
        chyba = true;
      }
    } while (chyba);
    pocet -= x;
  }

  static void bereStroj() {
    System.out.println("poèet zápalek "+pocet);
    int x = (pocet-1) % 4;
    if (x==0) x = 1;
    System.out.println("odebírám "+x);
    pocet -= x;
  }

}