/* Soubor Kap13\06\CtiHlod.java
 * Pøeète èeský text v kódování Latin 2 ze souboru a vypíše na konzolu.
 * Pøedpokládá existenci souboru data.dta v aktuálním adresáøi, který obsahuje
 * èeský text v kódování Latin 2 (CP 852).
 * Jako vstup lze použít napø. soubor vytvoøený programem Hlod.java, 
 * který najdete v tomto adresáøi.
 */

// Pouze pro JDK 5 a vyšší

import java.io.*;
import java.util.Scanner;

public class CtiHlod5 {

  public static void main(String[] args) throws Exception{
    File f = new File("data.dta");
    if(!f.exists()) throw new IOException("soubor neexistuje");

    Scanner skan = new Scanner(f, "Cp852");

    OutputStreamWriter osw = new OutputStreamWriter(System.out, "Cp852");
    PrintWriter pw = new PrintWriter(osw);

    while(skan.hasNextLine())
    {
      pw.println(skan.nextLine());
    }
    pw.close();
    skan.close();
  }
}