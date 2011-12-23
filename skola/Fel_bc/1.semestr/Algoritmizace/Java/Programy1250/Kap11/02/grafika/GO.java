/* Soubor Kap11\02\grafika\GO.java
 * Podobný jako v Kap09\05, tøída GO je ale
 * abstraktní a implementuje rozhraní kontrola.Kontrola a Cloneable
 * Tato rozhraní pak dìdí i odvozené tøídy.
 *
 * Metoda clone() prostì zavolá zdìdìnou od tøídy Object,
 * nebo nepotøebujeme nic víc než binární kopii instance.
 * Museli jsme ji však pøedefinovat, nebo ji chceme zveøejnit.
 */
package grafika;

import kontrola.*;

public abstract class GO implements Kontrola, Cloneable {
  private int barva;

  public int getBarva() { return barva;}
  public void setBarva(int _barva){ barva = _barva;}
  public GO() {}
  public GO(int _barva){setBarva(_barva);}
  public boolean zkontroluj() {return barva > 0;}
  public abstract void nakresli();
  public Object clone() throws CloneNotSupportedException {return super.clone();}
}