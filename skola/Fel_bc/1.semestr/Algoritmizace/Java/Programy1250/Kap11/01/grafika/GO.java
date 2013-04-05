/* Soubor Kap11\01\grafika\GO.java
 * Podobnı jako v Kap09\05, tøída GO je ale
 * abstraktní a implementuje rozhraní kontrola.Kontrola .
 * Toto rozhraní pak dìdí i odvozené tøídy.
 */
package grafika;

import kontrola.*;

public abstract class GO implements Kontrola {
  private int barva;

  public int getBarva() { return barva;}
  public void setBarva(int _barva){ barva = _barva;}
  public GO() {}
  public GO(int _barva){setBarva(_barva);}
  public boolean zkontroluj() {return barva > 0;}
  public abstract void nakresli();
}