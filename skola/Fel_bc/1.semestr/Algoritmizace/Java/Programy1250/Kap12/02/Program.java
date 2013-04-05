// Soubor Kap12\02\Program.java
// Ukázka použití parametrizovaných metod
// Jen JDK 5

public class Program
{
  public static void main(String [] s)
  {
    Integer[] A = {5, 3, 4, 2, 1};
    System.out.println(Pomoc.median(A));
    System.out.println(Pomoc.<Integer>median(A));
    for(Integer n: A)System.out.print(n+" ");
    System.out.println();
    Pomoc.<Integer>sort(A);
    Pomoc.sort(A);
    for(Integer n: A)
    System.out.print(n+" "); 
  }
}