/* Soubor Kap13\03\grafika\Kruznice.java
 * Dìdìním od tøídy GO získala implementaci
 * rozhraní kontrola.Kontrola, Cloneable a Serializable
 */

package grafika;

public class Kruznice extends GO {
  Bod stred;
  int r;

  public int getR(){return r;}
  public void setR(int _r){r = _r;}
  public Bod getStred(){return stred;}
  public void setStred(Bod _stred){stred = _stred;}
  public boolean zkontroluj()
  {
    return super.zkontroluj() && stred.zkontroluj() && (r > 0);
  }
  public void nakresli(){
    System.out.println(this);
  }

  public String toString() {
    return  "Kruznice, barva " + getBarva()+ ", polomer " + getR() +
            ", stred: " + stred;
  }

  public Object clone () throws CloneNotSupportedException {
    Kruznice k = (Kruznice)super.clone();
    k.stred = (Bod)stred.clone();
    return k;
  }
  public Kruznice(int _x, int _y, int _r, int _barva) {
    super(_barva);
    stred = new Bod(_x, _y, _barva);
    r = _r;
  }

}