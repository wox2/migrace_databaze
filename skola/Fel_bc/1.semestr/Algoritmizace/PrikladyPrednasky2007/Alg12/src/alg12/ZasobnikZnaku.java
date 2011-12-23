package alg12;

// Zasobník znakù implementovaný pomocí pole

public class ZasobnikZnaku {
  static final int MAXINDEX = 99;
  private char[] pole;
  private int vrchol;

  public ZasobnikZnaku() {
    pole = new char[MAXINDEX+1];
    vrchol = -1;
  }

  public void vloz(char z) {
    if (vrchol==MAXINDEX)
      throw new RuntimeException("vložení do plného zásobníku");
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
