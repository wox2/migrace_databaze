package alg11;

import java.util.*;

public class Citac3 {
  static int menu() {
      Scanner sc = new Scanner(System.in);
      int volba;
    do {
      System.out.println("0) Konec");
      System.out.println("1) Zvìtšit");
      System.out.println("2) Zmenšit");
      System.out.println("3) Nastavit");
      System.out.print("Vaše volba: ");
      volba = sc.nextInt();
      if (volba<0 || volba >4) {
        System.out.println("Nedovolená volba");
        volba = -1;
      }
    } while (volba<0);
    return volba;
  }

  public static void main(String[] args) {
    int volba;
    Citac citac = new Citac(0);
        Citac citac1 = new Citac(3);
    do {
      System.out.println("Hodnota = "+citac1.hodnota());
      volba = menu();
      switch (volba) {
        case 1: citac1.zvetsit(); break;
        case 2: citac1.zmensit(); break;
        case 3: citac1.nastavit(); break;
           case 4: System.out.println("nic"); break;
      }
    } while (volba>0);
    System.out.println("Konec");
  }
}
