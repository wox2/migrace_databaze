package retezec;

/* Soubor Kap11\02\Retezec.java
 * Stejný jako Kap11\01\Retezec.java - tato tøída
 *
 * Implementace mìnitelného øetìzce s možností kontroly.
 * Protože tøídy String i StringBuffer jsou koneèné,
 * nelze je použít jako pøedka. Proto zapouzdøíme instanci tøídy
 * StringBuffer do instance tøídy Retezec a obalíme vlastními metodami.

 * Implementuje rozhraní kontrola.Kontrola,
 * ale NEIMPLEMENTUJE rozhraní Cloneable
 */

import kontrola.*;

public class Retezec implements Kontrola  {
  StringBuffer str = new StringBuffer("");
  public Retezec() { }

  public String toString()
  {
    return str.toString();
  }

  String getString() { return str.toString(); }

  public void pridej(String s) {
    str.append(s);
  }

  public boolean zkontroluj() {
      return str != null;
  }

}