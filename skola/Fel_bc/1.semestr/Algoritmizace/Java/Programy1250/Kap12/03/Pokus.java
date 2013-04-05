// Soubor Kap12\01\Pokus.java
// Ukázka použití žolíku
// jako typového parametru.

import java.util.*;

public class Pokus
{

 // Univerzální metoda pro tisk jakékoli kolekce
 // Verze pro starší JDK
 public static void tiskPoStaru(java.util.Collection c)
 {
  java.util.Iterator it = c.iterator(); // Získáme iterátor
  while(it.hasNext())                   // a projdeme kolekci
    System.out.println(it.next());      // prvek po prvku
 }
 

/* Nefunguje -- kolekce parametrizovaná typem Object 
   je prostì jiný typ než kolekce parametrizovaná napø. 
   typem String
 */
/*
 public static void tisk(Collection<Object> c)
 {
   for(Object x: c) 
     System.out.println(x);
 }
*/

 // Univerzální metoda pro tisk jakékoli kolekce
 // Verze pro JDK 5
 public static void tisk(Collection<?> c)
 {
   for(Object x: c) 
     System.out.println(x);
 }


 public static void main(String [] s)
 {
   ArrayList<String> als = new ArrayList<String>();
   als.add("Ahoj");
   als.add("lidi");
   //tiskPoStaru(als);  // To lze
   tisk(als);
 }
}
