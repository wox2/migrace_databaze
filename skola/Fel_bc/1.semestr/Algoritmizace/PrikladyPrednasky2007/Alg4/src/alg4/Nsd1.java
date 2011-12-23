package alg4;
import java.util.*;

public class Nsd1 {
  public static void main(String[] args) {
    int x, y, vysl;
    x = Faktorial.ctiPrirozene();
    y = Faktorial.ctiPrirozene();
    vysl = nsd(x,y);
     System.out.println("nsd("+x+","+y+")="+nsd(x,y));
  }

  static int nsd(int x, int y) {
    int d = 0;
    if (x<y) d=x; else d=y;
    while (x%d!=0 || y%d!=0) d--;
    return d;
  }

}