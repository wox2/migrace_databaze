package alg11;

import java.util.*;

public class Menu {
  String[] prvky;
  String vyzva;
  private int volba;

  public Menu(String[] prvky, String vyzva) {
    this.prvky = prvky;
    this.vyzva = vyzva;
  }

  public int vyber() {
      Scanner sc = new Scanner(System.in);
      do {
      for (int i=0; i<prvky.length; i++)
        System.out.println(prvky[i]);
      System.out.print(vyzva);
      volba = sc.nextInt();
      if (volba<0 || volba>=prvky.length) {
        System.out.println("Nedovolená volba");
        volba = -1;
      }
    } while (volba<0);
    return volba;
  }

  public int volba() {
    return volba;
  }

}