/* Soubor Kap13\03\grafika\ZakrouzkovanyBod.java
 * Dìdìním od tøídy GO získala implementaci
 * rozhraní kontrola.Kontrola, Cloneable a Serializable
 */
package grafika;

public class ZakrouzkovanyBod extends Bod {

  public ZakrouzkovanyBod(int _x, int _y, int _barva) {
   super(_x, _y, _barva);
  }
  private void nakresliKrouzek()
  {
    System.out.println("Kreslim krouzek");
  }
  public String toString(){
    return "ZakrouzkovanyBod (" + getX() + ", " + getY() + "), barva " + getBarva();
  }
  public void nakresli(){
    super.nakresli();
    nakresliKrouzek();
  }
}