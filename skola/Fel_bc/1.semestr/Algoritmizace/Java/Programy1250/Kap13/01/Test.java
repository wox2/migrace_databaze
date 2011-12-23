/* Soubor Kap13\01\Test.java
 * Ukázka použití tøídy File. Metoda vytvorNovyPrazdnySoubor()
 * dostane jméno souboru (pokud neobsahuje cestu, pak jde o soubor
 * v aktuálním adresáøi).
 * Metoda si nejprve vytvoøí instanci tøídy File a jejímu konstruktoru
 * pøedá dané jméno. Pak zjistí, zda daný soubor existuje.
 * Pokud ano, smaže ho. Pak vytvoøí nový, prázdný.
 * V pøípadì neúspìchu (napø. pokud neexistuje adresáø, ve kterém
 * má soubor být) vyvolá výjimku.
 */

import java.io.*;
public class Test {

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