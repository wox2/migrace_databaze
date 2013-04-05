// Soubor Kap09\06\Max5.java
// Pøíklad metody, u ní pøedem neznáme 
// poèet skuteènıch parametrù.
//
// Metoda max() najde maximum z pøedem 
// neurèeného poètu celıch èísel

// Øešení pouze pro JDK 5: Pouijeme vıpustku

public class Max5
{
// Øešení pro JDK 5: Pouijeme vıpustku
  public static int max(int ... x)
  {                              // res obsahuje budoucí vısledek
    int res = Integer.MIN_VALUE; // Zaèínáme nejmenší monou hodnotou
    if(x != null)                // Je-li parametr null, konec
    {                            // Projdi pole a najdi nejvìtší prvek
      for(int s: x)
        if(s > res) res = s;
    }
    return res;                  // a vra ho
  }
  public static void main(String[] s)
  {
   int a=1, b=2, c=3;
   // Pouití: vytvoø ze zkoumanıch hodnot pole
   System.out.println(max(a, b, c));
  } 
}
