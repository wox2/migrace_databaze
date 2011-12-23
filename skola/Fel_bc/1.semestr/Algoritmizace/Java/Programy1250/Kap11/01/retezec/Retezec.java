package retezec;

/* Soubor Kap11\01\Retezec.java
 * Implementace mìnitelného øetìzce s možností kontroly.
 * Protože tøídy String i StringBuffer jsou koneèné (final),
 * nelze je použít jako pøedka. Proto zapouzdøíme instanci tøídy
 * StringBuffer do instance tøídy Retezec a obalíme vlastními metodami.

 * Implementuje rozhraní kontrola.Kontrola.
 */

import kontrola.*;

public class Retezec implements Kontrola  {
  StringBuffer str = new StringBuffer("");
  public Retezec() { }

  String getString() { return str.toString(); }

  public void pridej(String s) {
    str.append(s);
  }

  public boolean zkontroluj() {
      return str != null;
  }

}