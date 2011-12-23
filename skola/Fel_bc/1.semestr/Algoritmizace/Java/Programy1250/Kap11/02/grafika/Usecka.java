/* Soubor Kap11\02\grafika\Usecka.java
 * Ve srovnání s Kap09\05 je zde navíc metoda zkontroluj()
 * Dìdìním od tøídy GO získala také implementaci
 * rozhraní kontrola.Kontrola a Cloneable
 *
 * metoda clone()  nejprve pomocí metody pøedka vytvoøí
 * binární kopii instance, pak vytvoøí kopie krajních bodù, protože
 * "klonovaná" úseèka musí mít své krajní body, které pùjde zmìnit,
 * aniž bychom zmìnili krajní body pùvodní úseèky.
 *
 * Ve srovnání s pøedchozí verzí (Kap11\01) je zde navíc nová
 * metoda toString(), která vrátí text "Kruzice " a údaje o polomìru a støedu.
 * Je také zmìnìna metoda nakresli(), která využije metodu toString().

 */

package grafika;

public class Usecka extends GO {
  private Bod a, b;
  public Bod getA(){ return a;}
  public void setA(Bod _a){ a = _a;}
  public Bod getB(){ return b;}
  public void setB(Bod _b){ b = _b;}
  public boolean zkontroluj()
  {
    return super.zkontroluj() && a.zkontroluj() && b.zkontroluj();
  }

  public Object clone() throws CloneNotSupportedException {
    Usecka u = (Usecka)super.clone();
    u.a = (Bod)a.clone();
    u.b = (Bod)b.clone();
    return u;
  }
  public void nakresli(){
    System.out.println(this);
  }

  public String toString() {
    return  "Usecka, barva " + getBarva()+ ", pocatek: " + a +
            ", konec: " + b;
  }


  public Usecka(int _x1, int _y1, int _x2, int _y2, int _barva) {
    super(_barva);
    a = new Bod(_x1, _y1, _barva);
    b = new Bod(_x2, _y2, _barva);
  }
}