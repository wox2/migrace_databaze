/* Soubor Kap09\05\grafika\ZakrouzkovanyBod.java */

package grafika;

public class ZakrouzkovanyBod extends Bod {

  public ZakrouzkovanyBod(int _x, int _y, int _barva) {
   super(_x, _y, _barva);
  }
  private void nakresliKrouzek()
  {
    System.out.println("Kreslim krouzek");
  }
  public void nakresli(){
    super.nakresli();
    nakresliKrouzek();
  }
}