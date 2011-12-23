/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Testy;

/* Soubor Kap13\01\Test.java
 * Ukázka použití třídy File. Metoda vytvorNovyPrazdnySoubor()
 * dostane jméno souboru (pokud neobsahuje cestu, pak jde o soubor
 * v aktuálním adresáři).
 * Metoda si nejprve vytvoří instanci třídy File a jejímu konstruktoru
 * předá dané jméno. Pak zjistí, zda daný soubor existuje.
 * Pokud ano, smaže ho. Pak vytvoří nový, prázdný.
 * V případě neúspěchu (např. pokud neexistuje adresář, ve kterém
 * má soubor být) vyvolá výjimku.
 */

import java.io.*;
public class Main {

  public Test() {}
  public static File vytvorNovyPrazdnySoubor(String jmeno) throws java.io.IOException
  {
    File f = new File(jmeno);
    if(f.exists()) f.delete();
    f.createNewFile();
    return f;
  }
  public static void main(String[] a) throws java.io.IOException
  {
    String jmeno = "Data.dta";
    try {
      File f = vytvorNovyPrazdnySoubor(jmeno);
    }
    catch(java.io.IOException e)
    {
      System.out.print("Nepodarilo se vytvorit soubor " + jmeno);
      System.exit(1);
    }
  }

}