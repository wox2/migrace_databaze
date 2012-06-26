/* Soubor Kap12\04\Test852.java
 *  Pøíklad výstupu do textového souboru, èeština v kódové stránkce 852
 * Zdrojový text ale obsahuje èeštinu v kódové stránce 1250, takže 
 * pokud to budete pøekládsat v jiném prostøedí, nutno použít pøíkaz
 * javac -encoding Cp1250 Test852
 * Výstup bude v každém pøípadì obsahovat èeštinu v kódové stránce 852 (Latin 2)
 */

import java.io.*;

public class Test852 {

  public Test852() {  }

  public static void main(String[] args) throws Exception{
    Test852 t = new Test852();
    File f = new File("data.dta");
    if(!f.exists()) f.createNewFile();

    FileOutputStream fos = new FileOutputStream(f);
    OutputStreamWriter osw = new OutputStreamWriter(fos, "Cp852");
    PrintWriter pw = new PrintWriter(osw);
    for(int i = 0; i < 10; i++) pw.print(i+ " ");
    pw.println();
    pw.println(Math.PI);
    pw.println(Math.E);
    pw.println("Žluouèký kùò pøíšernì úpìl ïábelské ódy");

    pw.close();
  }
}