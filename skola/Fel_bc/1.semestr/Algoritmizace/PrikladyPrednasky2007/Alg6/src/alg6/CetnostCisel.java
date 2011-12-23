package alg6;

import java.util.*;

public class CetnostCisel {

  final static int MIN = 1;
  final static int MAX = 100;

  public static void main(String[] args) {
    vypis(tabulka());
  }

  static int[] tabulka() {
    Scanner sc = new Scanner(System.in);
    int[] tab = new int[MAX-MIN+1];
    System.out.println("zadejte øadu celých èísel zakonèenou nulou");
    int cislo = sc.nextInt();
    while (cislo!=0) {
      if (cislo>=MIN && cislo<=MAX) tab[cislo-MIN]++;
      cislo = sc.nextInt();
    }
    return tab;
  }

  static void vypis(int[] tab) {
    for (int i=0; i<tab.length; i++)
      if (tab[i]!=0)
        System.out.println("èetnost èísla "+(i+MIN)+" je "+tab[i]);
  }
}