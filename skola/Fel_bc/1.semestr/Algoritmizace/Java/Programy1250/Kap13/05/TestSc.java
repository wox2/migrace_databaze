// Soubor Kap13\05\TestSc.java
// Ukazuje použití tøídy java.util.Scanner
// pro ètení textového souboru
// 
// Pouze pro JDK 5
//
import java.util.Scanner;
import java.io.*;

public class TestSc {
  public static int maxVSouboru(String jmeno)  throws Exception
  {
    File f = new File(jmeno);
    if(!f.exists()) throw new IOException("soubor neexistuje");
    int max = Integer.MIN_VALUE;           // Doèasné maximum

    Scanner skan = new Scanner(f);         // Pøipojíme skener k souboru
    while (skan.hasNextInt())              // Èti, dokud je co
    {
      int n = skan.nextInt();
      if(n > max) max = n;
    }
    return max;
  }
  public static void main(String[] args) throws Exception
  {
    System.out.print("Nejvìtší èíslo v souboru: " + maxVSouboru("data1.dta"));
  }
}
