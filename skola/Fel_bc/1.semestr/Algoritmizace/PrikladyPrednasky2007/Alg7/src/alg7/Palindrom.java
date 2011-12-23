package alg7;

import java.util.*;

public class Palindrom {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Zadejte jeden øádek");
    String radek = sc.nextLine();
    String vysl;
    if (jePalindrom(radek))
      vysl = "je";
    else
      vysl = "není";
    System.out.println("Na øádku "+vysl+" palindrom");
  }

  static boolean jePalindrom(String str) {
    int i = 0, j = str.length()-1;
    while (i<=j) {
      while (str.charAt(i)==' ') i++;
      while (str.charAt(j)==' ') j--;
      if (str.charAt(i)!=str.charAt(j))
        return false;
      i++; j--;
    }
    return true;
  }

}