/* Soubor Kap13\04\Test1250.java
 *  Pøíklad výstupu do textového souboru, èeština v kódové stránce 1250 (Windows)
 * Bude fungovat i v jiném prostøedí, nutno ale pøekládat pøíkazem
 * javac -encoding Cp1250 Test1250.java
 */

import java.io.*;

public class Test1250 {

  public Test1250() {  }

  public static void main(String[] args) throws Exception{
    Test1250 t = new Test1250();
    File f = new File("data.dta");
    if(!f.exists()) f.createNewFile();

    FileWriter fw = new FileWriter(f);
    PrintWriter pw = new PrintWriter(fw);
    for(int i = 0; i < 10; i++) pw.print(i+ " ");
    pw.println();
    pw.println(Math.PI);
    pw.println(Math.E);
    pw.println("Žluouèký kùò pøíšernì úpìl ïábelské ódy");

    pw.close();
  }
}