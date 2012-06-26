/* Soubor Realna.java
   Chybné porovnání reálných èísel
   Program skonèí v nekoneèném cyklu a 
   je tøeba ho zastavit Ctrl+C
*/

public class Realna
{
  public static void main(String [] s)
  {
    double d = 0.0;
    while(d !=  1.0)   // Zde je zakopám pes
   {
 	System.out.println(d);
	d += 0.1;
   }
  }
}