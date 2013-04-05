// Soubor Kap09\06\Max.java
// Pøíklad metody, u ní pøedem neznáme 
// poèet skuteènıch parametrù.
//
// Metoda max() najde maximum z pøedem 
// neurèeného poètu celıch èísel

// Øešení pro všechny verze JDK: Pouijeme pole

public class Max
{
  public static int max(int[] x)
  {                              // res obsahuje budoucí vısledek
    int res = Integer.MIN_VALUE; // Zaèínáme nejmenší monou hodnotou
    if(x != null)                // Je-li parametr null, konec
    {                            // Projdi pole a najdi nejvìtší prvek
      for(int i = 0; i < x.length; i++)
        if(x[i] > res) res = x[i];
    }
    return res;                  // a vra ho
  }



  public static void main(String[] s)
  {
   int a=1, b=2, c=3;
   // Pouití: vytvoø ze zkoumanıch hodnot pole
   System.out.println(max(new int[]{a, b, c}));
  } 
}
