import java.io.*;

/** Konzolový vstup */
public class MojeIO {
 private static String radek;   // Naètený øádek
 private static BufferedReader stdin = new BufferedReader(
     new InputStreamReader(System.in));
 // vstup øetìzce z konzole
 public static String inStr() throws IOException
 {
  try {
   return radek = stdin.readLine();
  }
  catch(Exception e){
   System.err.println("chyba v retezci");
   return "";
  }
 }

 private static void hledej()
 {
   try{
   while((radek = inStr()).equals(""));
   }
   catch(Exception e){System.err.println("Chyba na vstupu");}
 }

 public static long inLong()
 {
  try{
   hledej();
   return Long.valueOf(radek.trim()).longValue();
  }
  catch(Exception e)
  {
    System.err.println("To neni cele cislo, pouziji 0");
    return 0;
  }
 }

 public static int inInt()
 {
   return (int)inLong();
 }

 public static double inDouble() throws IOException
 {
  try{
   hledej();
   return Double.valueOf(radek.trim()).doubleValue();
  }
  catch(Exception e)
  {
    System.err.println("To neni realne cislo");
    return 0;
  }
 }

public static void main(String[] arg)
 {
  // testy funkènosti
   try {
/*   System.out.println("Zadej realne cislo:");
   double d = inDouble();
   System.out.println(d);

   System.out.println("Zadej cele cislo:");
   long l = inLong();
   System.out.println(l);

   System.out.println("Zadej radku:");
   String s = inStr();
   System.out.println(s);

   String s1 = inStr();
   String s2 = inStr();
   System.out.println(s1);
   System.out.println(s2);
*/
//   System.out.println("asdfghj");

  hledej();
  System.out.println(radek);
  hledej();
  System.out.println(radek);
  }
  catch(Exception e){
   System.err.println("chyba v retezci");
  }
 }
}