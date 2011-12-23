package alg12;

// Zásobník znakù implementovaný rozšiøitelným polem

public class ZasobnikZnaku2 {
  private char[] pole;
  private int vrchol;

  public ZasobnikZnaku2() {
    pole = new char[2];
    vrchol = -1;
  }

  public void vloz(char z) {
    if (vrchol==pole.length-1) {
      char[] nove = new char[2*pole.length];
      System.arraycopy(pole,0,nove,0,pole.length);
      pole = nove;
    }
    pole[++vrchol] = z;
  }

  public char odeber() {
    if (jePrazdny())
      throw new RuntimeException("odebrání z prázdného zásobníku");
    return pole[vrchol--];
  }

  public boolean jePrazdny() {
    return vrchol<0;
  }
}
