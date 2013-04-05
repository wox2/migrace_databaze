/* soubor Kap03\02\ZlutyKun2.java
 * Vıstup èeštiny do konzolového okna
 * správnì.
 * Poznamenejme, e v rozsáhlejších programech
 * bude tøeba pøipojit za pøíkaz
 * p.println("luouèkı ...");
 * ještì pøíkaz
 * p.flush();
 */

import java.io.*;

/** Jediná tøída prvního programu */
public class ZlutyKun2 {
  /** Jediná metoda. Pøedstavuje celı program. */
  public static void main(String[] arg) throws Exception
  {
    OutputStreamWriter osw = 
    new OutputStreamWriter(System.out, "Cp852");
     PrintWriter p = new PrintWriter(osw);
    p.println("luouèkı kùò pøíšernì úpìl ïábelské ódy.");
    p.close();
  }
}