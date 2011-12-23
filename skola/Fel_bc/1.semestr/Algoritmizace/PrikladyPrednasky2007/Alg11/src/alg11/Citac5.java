package alg11;

public class Citac5 {
  public static void main(String[] args) {
    Citac citac = new Citac(0);
    String[] nabidka = new String[5];
    MenuX menu = new MenuX(nabidka, "Vaše volba: ", 5);
    menu.pridejPolozkuDoMenu("0) Konec");
    menu.pridejPolozkuDoMenu("1) Zvìtšit");
    menu.pridejPolozkuDoMenu("2) Zmenšit");
    menu.pridejPolozkuDoMenu("3) Nastavit");
    menu.pridejPolozkuDoMenu("4) nove");
    menu.pridejVyzvu("Vaše volba: ");
    do {
      System.out.println("Hodnota = "+citac.hodnota());
      switch (menu.vyber()) {
        case 1: citac.zvetsit(); break;
        case 2: citac.zmensit(); break;
        case 3: citac.nastavit(); break;
        case 4: System.out.println("nic"); break;
      }
    } while (menu.volba()!=0);
    System.out.println("Konec");
  }}
