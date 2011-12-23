package alg11;

import java.util.*;

public class Citac2 {
  final static int pocHodn = 0;
  static int hodn;

  public static void main(String[] args) {

    int volba;
    hodn = pocHodn;
    do {
      System.out.println("Hodnota = "+hodn);
      volba = menu();
      if (volba>0) operace(volba);
    } while (volba>0);
    System.out.println("Konec");
  }

  static int menu() {
      Scanner sc = new Scanner(System.in);
      int volba;
    do {
      System.out.println("0. Konec");
      System.out.println("1. Zvìtšit");
      System.out.println("2. Zmenšit");
      System.out.println("3. Nastavit");
      System.out.print("Vaše volba: ");
      volba = sc.nextInt();
      if (volba<0 || volba >3) {
        System.out.println("Nedovolená volba");
        volba = -1;
      }
    } while (volba<0);
    return volba;
  }

  static void operace(int op) {
    switch (op) {
      case 1: hodn++; break;
      case 2: hodn--; break;
      case 3: hodn = pocHodn; break;
    }
    if (hodn < 0)hodn=0;
  }

}
