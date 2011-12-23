/* Soubor Kap13\05\Test.java
 * Pøíklad vstupu z textového souboru
 * lze použít ve všech verzích JDK
 */

import java.io.*;

public class Test {

  public Test() {  }
  public static int maxVSouboru(String jmeno)  throws Exception
  {
    File f = new File(jmeno);
    if(!f.exists()) throw new IOException("soubor neexistuje");
    int max = Integer.MIN_VALUE;           // Doèasné maximum

    FileReader fr = new FileReader(f);
    BufferedReader bw = new BufferedReader(fr);

    String cisla;
    while ((cisla = bw.readLine()) != null)
    {
      // analýza øádku
      java.util.StringTokenizer st = new java.util.StringTokenizer(cisla);
      while(st.hasMoreTokens())
      {
        String s = st.nextToken();
        int i = Integer.parseInt(s);
        if(i > max) max = i;
      }
    }
    bw.close();
    return max;
  }
  public static void main(String[] args) throws Exception
  {
    System.out.print("Nejvìtší èíslo v souboru: " + maxVSouboru("data1.dta"));
  }
}