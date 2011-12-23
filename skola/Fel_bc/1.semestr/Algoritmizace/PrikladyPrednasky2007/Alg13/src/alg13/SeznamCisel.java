package alg13;
import java.util.*;

public class SeznamCisel {
/* seznam s odkazem na volný prvek */
  Prvek prvni;
  Prvek volny;

  public SeznamCisel() {
    prvni = new Prvek();
    volny = prvni;
  }

  public void vlozNaZacatek(int x) {
    prvni = new Prvek(x, prvni);
  }

  public void vlozNaKonec(int x) {
    volny.hodn = x;
    volny.dalsi = new Prvek();
    volny = volny.dalsi;
  }

  public boolean jePrvkem(int x) {
    volny.hodn = x;
    Prvek pom = prvni;
    while (pom.hodn!=x) pom = pom.dalsi;
    return pom!=volny;
  }

  public void vypis() {
    Prvek pom = prvni;
    while (pom!=volny) {
      System.out.print(pom.hodn+" ");
      pom = pom.dalsi;
    }
    System.out.println();
  }

/* seznam s odkazem na poslední prvek */
/*
  Prvek prvni;
  Prvek posledni;

  public SeznamCisel() {
    prvni = null;
    posledni = null;
  }

  public void vlozNaZacatek(int x) {
    prvni = new Prvek(x, prvni);
    if (posledni==null) posledni = prvni;
  }

  public void vlozNaKonec(int x) {
    Prvek pom = new Prvek(x, null);
    if (posledni==null) {
      posledni = pom;
      prvni = pom;
    } else {
      posledni.dalsi = pom;
      posledni = pom;
    }
  }

  public boolean jePrvkem(int x) {
    Prvek pom = prvni;
    while (pom!=null && pom.hodn!=x) pom = pom.dalsi;
    return pom!=null;
  }

  public void vypis() {
    Prvek pom = prvni;
    while (pom!=null) {
      System.out.print(pom.hodn+" ");
      pom = pom.dalsi;
    }
    System.out.println();
  }
*/

  public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     SeznamCisel obracena = new SeznamCisel();
    SeznamCisel ruzna = new SeznamCisel();
    System.out.println("zadejte øadu èísel zakonèenou nulou");
    int x = sc.nextInt();
    while (x!=0) {
      obracena.vlozNaZacatek(x);
      if (!ruzna.jePrvkem(x))
        ruzna.vlozNaKonec(x);
      x = sc.nextInt();
    }
    System.out.println("èísla v opaèném poøadí");
    obracena.vypis();
    System.out.println("seznam rùzných èísel");
    ruzna.vypis();
  }
}
