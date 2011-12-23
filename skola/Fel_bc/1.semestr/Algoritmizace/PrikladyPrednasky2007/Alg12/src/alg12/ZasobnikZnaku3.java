package alg12;

/* zásobník implementovaný spojovým seznamem */

class Prvek {
  char hodn;
  Prvek dalsi;

  public Prvek(char h, Prvek p) {
    hodn = h; dalsi = p;
  }
}

public class ZasobnikZnaku3 {
  private Prvek vrchol;

  public ZasobnikZnaku3() {
    vrchol = null;
  }

  public void vloz(char z) {
    vrchol = new Prvek(z, vrchol);
  }

  public char odeber() {
    if (jePrazdny())
      throw new RuntimeException("odebrání z prázdného zásobníku");
    char vysl = vrchol.hodn;
    vrchol = vrchol.dalsi;
    return vysl;
  }

  public boolean jePrazdny() {
    return vrchol==null;
  }

  public char znakNaVrcholu()
  {return vrchol.hodn;}
}
