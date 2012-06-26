/* Soubor Kap13\06\CtiHlod.java
 * Pøeète èeský text v kódování Latin 2 ze souboru a vypíše na konzolu.
 * Pøedpokládá existenci souboru data.dta v aktuálním adresáøi, který obsahuje
 * èeský text v kódování Latin 2 (CP 852).
 * Jako vstup lze použít napø. soubor vytvoøený programem Hlod.java, 
 * který najdete v tomto adresáøi.
 */

import java.io.*;

public class CtiHlod {

  public static void main(String[] args) throws Exception{
    File f = new File("data.dta");
    if(!f.exists()) throw new IOException("soubor neexistuje");

    FileInputStream fis = new FileInputStream(f);
    InputStreamReader isr = new InputStreamReader(fis, "Cp852");
    BufferedReader br = new BufferedReader(isr);

    OutputStreamWriter osw = new OutputStreamWriter(System.out, "Cp852");
    PrintWriter pw = new PrintWriter(osw);

    String text;
    while((text = br.readLine()) != null)
    {
    pw.println(text);
    }
    pw.close();
  }
}