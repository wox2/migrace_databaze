/* Soubor Kap11\01\grafika\Kruznice.java
 * Ve srovnání s Kap09\05 je zde navíc metoda zkontroluj()
 * Dìdìním od tøídy GO získala také implementaci
 * rozhraní kontrola.Kontrola
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
    System.out.println("Kreslim Kruznici, barva " +
                    getBarva()+", polomer " + getR() + ", stred:");
    stred.nakresli();
  }

  public Kruznice(int _x, int _y, int _r, int _barva) {
    super(_barva);
    stred = new Bod(_x, _y, _barva);
    r = _r;
  }

}