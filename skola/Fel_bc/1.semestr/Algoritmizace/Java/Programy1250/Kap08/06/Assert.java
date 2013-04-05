// Soubor Kap08\06\Assert.java
// OD JDK 1.4 VÝŠE
// Ukazuje použití aserce
//
// V JDK 1.4 nutno pøeložit pøíkazem
// javac -source 1.4 Assert.java 
// 
// V JDK 5 lze pøepínaè -source vynechat
//
// Pøi spuštìní je tøeba použít pøíkaz
// java -ea Assert
//
// Pøepínaè -ea je tøeba použít i v JDK 5

public class Assert {
  public static int faktorial(int n)
  {
    if (Pomoc.LADIM) 
      assert n >= 0: "Zaporny parametr faktorialu: " + n;
    int s = 1;      
    for(int i = n; i > 0; i--) 
                  s *= i;
    return s;
  }


  public static void main(String[] s) {
    System.out.println("Volam faktorial");
    int y = -1;
    int x = faktorial(-1);
    System.out.println("Faktorial " + y + " je " + x);
  }
}