/* Soubor Kap09\01\Pokus.java
 * pøedvádí, že se parametry primitivních typù pøedávají hodnotou,
 * ale parametry objektových typù odkazem
 * (pøesnìji, hodnotou se pøedává odkaz na objekt nebo pole)
 *
 * Tøída Pokus obsahuje dvì metody f(). Obì poèítají faktoriál 
 * a obì pøitom mìní hodnotu formálního parametru.
 *
 * Jedna má parametr typu int (zde nedojde ke zmìnì skuteèného parametru),
 * druhá má parametr typu MujInt, což je objektový typ zapouzdøující
 * int. Jsou pro nìj definovány metody, které umožòují operace násobení, -- atd.
 * Pøi volání této metody se skuteèný parametr zmìní.
 */

class MujInt {                  // Tøída MujInt je objektový typ zapouzdøující int a operace s ním
  private int i;                // Uložené celé èíslo
  MujInt(int x){ i = x; }       // Konstruktor
  int getInt(){ return i; }     // Vrátí uložené celé èíslo
  int minusMinus(){ return --i; }               // --
  boolean vetsiNez(int x){ return i > x; }      // Porovnání s celým èíslem
  void setInt(int x){i = x;}                    // Zmìna uložené hodnoty
  void kratRovnaSe(MujInt n){ i *= n.getInt(); }// Operátor *=
}

public class Pokus {		
  static MujInt f(MujInt n)     // Výpoèet faktoriálu pro typ MujInt
  {                             // Témìø stejný jako výpoèet pro int v následující
    MujInt s;                   // metodì f(int)
    for(s = new MujInt(1); n.vetsiNez(0); n.minusMinus())
      s.kratRovnaSe(n);
    return s;
  }

  static int f(int n)           // Výpoèet faktoriálu pro typ int
  {
    int s;
    for(s = 1; n > 0; n--) s *= n;
    return s;
  }

  public static void main(String[] args) {       // pokusy s pøedáváním parametrù
    int x = 5, y = f(x);
    System.out.println("faktorial " + x + " je " + y);
    MujInt xx = new MujInt(5), yy = f(xx);
    System.out.println("faktorial " + xx.getInt() + " je " + yy.getInt());
  }
}