/* Soubor Kap06\06\SvStr.java
   Ukázka deklarace výètového typu 
   a základních operací s ním

   JEN V JDK 5
 */

enum Strany {SEVER, JIH, VYCHOD, ZAPAD;}

public class SvStr
{
  public static void main(String[] arg)
  {
     Strany[] s = Strany.values();
     int i = 0;
     while(i < s.length)
     {
       System.out.print(s[i].name());
       if(s[i] == Strany.JIH) System.out.println("!");
       else System.out.println();
       i++;
     }
  }
}