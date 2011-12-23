package alg4;
import java.util.*;

public class Nsd4 {
  public static void main(String[] args) {
    int x, y, vysl;
    x = Faktorial.ctiPrirozene();
    y = Faktorial.ctiPrirozene();
    vysl = nsd(x,y);
     System.out.println("nsd("+x+","+y+")="+nsd(x,y));
  }

  static int nsd(int x, int y) {
    int zbytek = x%y;
    while (zbytek!=0) {
      x = y; y = zbytek; zbytek = x%y;
    }
    return y;
  }
}