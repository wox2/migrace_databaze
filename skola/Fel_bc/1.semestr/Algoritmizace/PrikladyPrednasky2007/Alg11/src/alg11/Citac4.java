package alg11;



public class Citac4 {
  public static void main(String[] args) {
    String[] nabidka = {
      "0) Konec", "1) +", "2) Zmenšit", "3) Nastavit"
    };
    Citac citac = new Citac(0);
    Menu menu = new Menu(nabidka, "Vaše volba: ");
    do {
      System.out.println("Hodnota = "+citac.hodnota());
      switch (menu.vyber()) {
        case 1: citac.zvetsit(); break;
        case 2: citac.zmensit(); break;
        case 3: citac.nastavit(); break;
      }
    } while (menu.volba()!=0);
    System.out.println("Konec");
  }
}
