/* Soubor Kap09\05\grafika\Kruznice.java */
package grafika;

public class Kruznice extends GO {
  Bod stred;
  int r;

  public int getR(){return r;}
  public void setR(int _r){r = _r;}
  public Bod getStred(){return stred;}
  public void setStred(Bod _stred){stred = _stred;}

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