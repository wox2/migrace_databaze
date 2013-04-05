package alg13;

import java.util.*;

public class Morseovka {
  public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
   System.out.println("Zadejte text v morseovce zakonèený prázdným øádkem");
    String text = sc.nextLine();
    while (!text.equals("")) {
      System.out.println(dekoduj(text+" "));
      text = sc.nextLine();
    }
  }

  static MUzel koren = strom();

  static String dekoduj(String s) {
    MUzel aktualni = koren;
    String vysl = "";
    for (int i=0; i<s.length(); i++) {
      char z = s.charAt(i);
      if (aktualni!=null)
        if (z=='.') aktualni = aktualni.tecka;
                   else if (z=='-') aktualni = aktualni.carka;
                          else {
                               vysl = vysl + aktualni.znak;
                                  aktualni = koren;
                                }
      else {
        vysl = vysl + '?';
        aktualni = koren;
      }
    }
    return vysl;
  }

  static MUzel strom() {
    return
      new MUzel(' ',
        new MUzel('E',         // .
          new MUzel('I',        // ..
            new MUzel('S',       // ...
              new MUzel('H'),     // ....
              new MUzel('V')      // ...-
            ),
            new MUzel('U',       // ..-
              new MUzel('F'),     // ..-.
              null                // ..--
            )
          ),
          new MUzel('A',        // .-
            new MUzel('R',       // .-.
              new MUzel('L'),     // .-..
              null                // .-.-
            ),
            new MUzel('W',       // .--
              new MUzel('P'),     // .--.
              new MUzel('J')      // .---
            )
          )
        ),
        new MUzel('T',         // -
          new MUzel('N',        // -.
            new MUzel('D',       // -..
              new MUzel('B'),     // -...
              new MUzel('X')      // -..-
            ),
            new MUzel('K',       // -.-
              new MUzel('C'),     // -.-.
              new MUzel('Y')      // -.--
            )
          ),
          new MUzel('M',        // --
            new MUzel('G',       // --.
              new MUzel('Z'),     // --..
              new MUzel('Q')      // --.-
            ),
            new MUzel('O',       // ---
              null,               // ---.
              null                // ----
            )
          )
        )
      );
  }
}

class MUzel {
  char znak;
  MUzel tecka, carka;

  public MUzel(char z) {
    znak = z; tecka = null; carka = null;
  }

  public MUzel(char z, MUzel t, MUzel c) {
    znak = z; tecka = t; carka = c;
  }
}