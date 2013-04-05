/* Soubor Kap13\06\Hlod.java
 * Pøíklad za zápis èeského textu do souboru v kódování Latin 2
 * Citovaný text pochází z vysílání Èeské televize o vánocích r. 2000
 */

import java.io.*;

public class Hlod{

  public static void main(String[] args) throws Exception{
    File f = new File("data.dta");
    if(!f.exists()) f.createNewFile();

    FileOutputStream fos = new FileOutputStream(f);
    OutputStreamWriter osw = new OutputStreamWriter(fos, "Cp852");
    PrintWriter pw = new PrintWriter(osw);
    pw.println("Generální øeditel ÈT Jiøí Hodaè se obrátil\n"+
		"na Radu Èeské republiky pro rozhlasové \na televizní " +
		"vysílání se žádostí o rozhodnutí, \nkterý program ÈT " +
		"je legálním a autorizovaným \nprogramem v souladu se zákonem " +
		"o Èeské \ntelevizi a který nikoli. Do rozhodnutí Rady \nvysílá ÈT " +
		"jako svùj program toto sdìlení.");

    pw.close();
  }
}