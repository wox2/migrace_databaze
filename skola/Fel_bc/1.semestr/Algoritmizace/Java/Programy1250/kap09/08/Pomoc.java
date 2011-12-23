// Soubor Kap09\08\Pomoc.java
// Ukázka deklarace parametrizovaných metod
// Jen JDK 5


public class Pomoc
{
  // Najde prvek ležící uprostøed pole typu T
  public static <T> T median(T[] x)
  {
    assert (x != null && x.length != 0); 
    return x[x.length / 2];
  }
  public static <T> void swap(int i, int j, T[] x)
  {
   assert i < x.length && j < x.length;    
   T a = x[i];
   x[i] = x[j];
   x[j] =a;
  } 

  // Vypoète prùmìr z prvkù v daném poli
  public static <T extends Comparable> void sort(T[] x)
  {
    for(int i = 0; i < x.length; i++)
    {
      int min = i;
      for(int j = i+1; j < x.length; j++)
      {
         if(x[j].compareTo(x[min]) > 0) min = j;  // Najdi index nejmenšího
         if(min != i) swap(min, j, x);  // a prohoï ho s prvním v daném úseku
      }
    } 
  }
}