/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tridy;

/**
 *
 * @author já
 */
class FunnyToto {

    FunnyToto vratSe() {
        return this;
    }

    void pozdrav() {
        System.out.println("Nazdar!!!");
    }
}

class Lepidlo {

    private int objem;
    private String nazev;

    /*
    Lepidlo() {
    nazev = "divne lepidlo";
    objem = 150;
    System.out.println(nazev + ", " + objem + " ml");
    }
     */
    Lepidlo() {
        //  System.out.println("Toto nefunguje");
        this("Divne Lepidlo", 22); // this musi byt na prvni radce konstruktoru
    }

    Lepidlo(String nazev, int objem) {
        System.out.println("nove lepidlo " + nazev + ", " + objem + " ml");
    }
}

public class Main {

    /*  public static void main(String[] args) {
    for (int i = 1; i < 5; i++) {
    new Lepidlo("Kanagon", i);
    }
    new Lepidlo();

    }
     */ public static void main(String[] args) {
        FunnyToto t = new FunnyToto();
        t.pozdrav();
        t.vratSe().pozdrav();
        t.vratSe().vratSe().pozdrav();
    }
}