/* Soubor Kap13\03\grafika\Bod.java
 * Dìdìním od tøídy GO získala implementaci
 * rozhraní kontrola.Kontrola, Cloneable a Serializable
 *
 * Metodu clone() není tøeba pøedefinovat, nebo plnì vyhovuje
 * zdìdìná metoda.
 *
 * Ve srovnání s pøedchozí verzí (Kap11\01) je zde navíc nová
 * metoda toString(), která vrátí text "Bod " a údaje o souøadnicích a barvì.
 * Je také zmìnìna metoda nakresli(), která využije metodu toString().
 */
package grafika;

public class Bod extends GO {
  private int x, y;

  public int getX(){return x;}
  public void setX(int _x){ x = _x;}
  public int getY(){return y;}
  public void setY(int _y){ y = _y;}
  public boolean zkontroluj() {
	return super.zkontroluj() && (x > 0) && (y > 0);
  }
  public String toString(){return "Bod (" + x + ", " + y + "), barva " + getBarva(); }
  public void nakresli(){
    System.out.println(this);
  }
  public Bod(int _x, int _y, int _barva) {
    super(_barva);
    setX(_x);
    setY(_y);
  }
//  public Object clone(){return super.clone();}
}