package alg6;

import java.util.*;

public class CetnostPismen {
  final static int POCET_PISMEN = 'Z'-'A'+1;

  public static void main(String[] args) {
    vypis(tabulka());
  }

  static int[] tabulka() {
       Scanner sc = new Scanner(System.in);
       int[] tab = new int[POCET_PISMEN];
    System.out.print("\nzadejte text\n");
       String sss = sc.nextLine();
       int i=0;
       char z = sss.charAt(0);
    
    while (z!='.') {
      if (jePismeno(z))
        tab[naVelke(z)-'A']++;
    sss = sc.nextLine();
      z = sss.charAt(0);
 
    }
    return tab;
  }

  static void vypis(int[] tab) {
    for (int i=0; i<POCET_PISMEN; i++)
      if (tab[i]!=0) {
        char z = (char)('A'+i);
        System.out.println(z+" "+tab[i]);
      }
  }

  static boolean jePismeno(char c) {
    return c>='A' && c<='Z' || c>='a' && c<='z';
  }

  static char naVelke(char c) {
    return (char)(c>='a' || c<='z' ? c-('a'-'A') : c);
  }

}