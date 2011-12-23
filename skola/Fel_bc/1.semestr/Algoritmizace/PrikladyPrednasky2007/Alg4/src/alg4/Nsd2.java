package alg4;
import java.util.*;

public class Nsd2 {
  public static void main(String[] args) {
    int x, y, vysl;
    x = Faktorial.ctiPrirozene();
    y = Faktorial.ctiPrirozene();
    vysl = nsd(x,y);
     System.out.println("nsd("+x+","+y+")="+nsd(x,y));
  }

  static int nsd(int x, int y) {
    while (x!=y)
      if (x>y) x=x-y; else y=y-x;
    return x;
  }
}