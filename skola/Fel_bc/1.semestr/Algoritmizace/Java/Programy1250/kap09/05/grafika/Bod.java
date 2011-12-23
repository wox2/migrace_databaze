/* Soubor Kap09\05\grafika\Bod.java */
package grafika;


public class Bod extends GO {
  private int x, y;

  public int getX(){return x;}
  public void setX(int _x){ x = _x;}
  public int getY(){return y;}
  public void setY(int _y){ y = _y;}

  public void nakresli(){
    System.out.println("Kreslim Bod (" + x + ", "+ y + "), barva " + getBarva());
  }
  public Bod(int _x, int _y, int _barva) {
    super(_barva);
    setX(_x);
    setY(_y);
  }
}