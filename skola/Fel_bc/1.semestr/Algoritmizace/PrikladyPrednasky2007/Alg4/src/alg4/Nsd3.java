package alg4;
import java.util.*;

public class Nsd3 {
  public static void main(String[] args) {
    int x, y, vysl;
    x = Faktorial.ctiPrirozene();
    y = Faktorial.ctiPrirozene();
    vysl = nsd(x,y);
     System.out.println("nsd("+x+","+y+")="+nsd(x,y));
  }

  static int nsd(int x, int y) {
    while (x!=y) {
      while (x>y) x = x-y;
      while (y>x) y = y-x;
    }
    return x;
  }
}