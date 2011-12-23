package alg13;

import java.util.*;

public class ObraceniCisel {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("zadejte øadu èísel zakonèených nulou");
    Prvek prvni = null;
    int cislo = sc.nextInt();
    while (cislo!=0) {
      prvni = new Prvek(cislo, prvni);
      cislo = sc.nextInt();
    }
    System.out.println("èísla v opaèném poøadí");
    Prvek pom = prvni;
    while (pom!=null) {
      System.out.print(pom.hodn()+" ");
      pom = pom.dalsi();
    }
    System.out.println();
  }
}

class Prvek {
  int hodn;
  Prvek dalsi;

  public Prvek() {
    hodn = 0; dalsi = null;
  }

  public Prvek(int h, Prvek d) {
    hodn = h; dalsi = d;
  }

  public int hodn() {
    return hodn;
  }

  public Prvek dalsi() {
    return dalsi;
  }
}
