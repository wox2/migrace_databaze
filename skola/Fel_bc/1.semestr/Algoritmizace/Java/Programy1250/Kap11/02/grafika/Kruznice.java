/* Soubor Kap11\01\grafika\Kruznice.java
 * Ve srovnání s Kap09\05 je zde navíc metoda zkontroluj()
 * Dìdìním od tøídy GO získala také implementaci
 * rozhraní kontrola.Kontrola a rozhrani Cloneable

 * metoda clone()  nejprve pomocí metody pøedka vytvoøí
 * binární kopii instance, pak vytvoøí kopii støedu, protože
 * "klonovaná" kružnice musí mít svuj støed, který pùjde zmìnit,
 * aniž bychom zmìnili støed pùvodní kružnice.
 *
 * Ve srovnání s pøedchozí verzí (Kap11\01) je zde navíc nová 
 * metoda toString(), která vrátí text "Kruzice " a údaje o barvì, polomìru a støedu.
 * Je také zmìnìna metoda nakresli(), která využije metodu toString().
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