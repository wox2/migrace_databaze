/* Soubor Kap09\05\grafika\Usecka.java */
package grafika;

public class Usecka extends GO {
  private Bod a, b;
  public Bod getA(){ return a;}
  public void setA(Bod _a){ a = _a;}
  public Bod getB(){ return b;}
  public void setB(Bod _b){ b = _b;}

  public void nakresli(){
    System.out.println("Kreslim Usecku, barva " +
                    getBarva()+", krajní body:");
    a.nakresli();
    b.nakresli();
  }

  public Usecka(int _x1, int _y1, int _x2, int _y2, int _barva) {
    super(_barva);
    a = new Bod(_x1, _y1, _barva);
    b = new Bod(_x2, _y2, _barva);
  }
}