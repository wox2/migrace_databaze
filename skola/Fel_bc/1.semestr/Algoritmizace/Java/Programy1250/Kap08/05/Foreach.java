// Soubor Kap08\05\Foreach.java
// Pøíklad použití nového tvaru pøíkazu for
// ke zpracování všech prvkù kontejneru

// Jen v JDK 5

import java.util.ArrayList;

public class Foreach
{
  // Výpis pole pomocí for-each
  public static void vypisPole(int[] a)
  {
     for(long x: a)
       System.out.println(x);
  }

  // Výpis kontejneru pomocí for-each
  public static void vypisKontejneru(ArrayList a)
  {
     for(Object x: a)
       System.out.println(x);
  }
  
  /* Mùže nahradit obì pøedchozí metody

  public static void vypisKontejneru(Iterable a)
  {
     for(Object x: a)
       System.out.println(x);
  }
 */

  public static void main(String[] arg)
  {
     int[] A = {1, 2, 3};
     vypisPole(A);

     ArrayList al = new ArrayList();
     for(int i = 0; i < 10; i++) al.add(i);
     vypisKontejneru(al);
  }
}