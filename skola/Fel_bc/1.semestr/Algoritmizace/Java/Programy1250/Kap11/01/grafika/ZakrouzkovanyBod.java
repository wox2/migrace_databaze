/* Soubor Kap11\01\grafika\ZakrouzkovanyBod.java
 * Metoda zkontroluj() je stejna jako v pøedkovi, není tedy tøeba ji mìnit
 * Dìdìním od tøídy GO získala také implementaci
 * rozhraní kontrola.Kontrola
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
  public void nakresli(){
    super.nakresli();
    nakresliKrouzek();
  }
}