package alg7;

import java.util.ArrayList;
import java.util.*;


public class KontejnerRadku {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList radky = new ArrayList();
    System.out.println("zadejte øádky zakonèené prázdným øádkem");
    String radek = sc.nextLine();
    while (!radek.equals("")) {
      radky.add(radek);
      radek = sc.nextLine();
    }
    System.out.println("výpis øádkù v opaèném poøadí");
    for (int i=radky.size()-1; i>=0; i--)
      System.out.println(radky.get(i));
    String prvni = (String)radky.get(0);
  }
}
