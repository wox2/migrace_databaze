/* Soubor Kap13\03\grafika\GO.java
 * Podobný jako v Kap09\05, tøída GO je ale
 * abstraktní a implementuje rozhraní kontrola.Kontrola, Cloneable a Serializable
 * Tato rozhraní pak dìdí i odvozené tøídy.
 * Serializaci není tøeba implementovat
 */
package grafika;

import kontrola.*;
import java.io.*;

public abstract class GO implements Kontrola, Cloneable, Serializable {
  private int barva;

  public int getBarva() { return barva;}
  public void setBarva(int _barva){ barva = _barva;}
  public GO() {}
  public GO(int _barva){setBarva(_barva);}
  public boolean zkontroluj() {return barva > 0;}
  public abstract void nakresli();
  public Object clone() throws CloneNotSupportedException {return super.clone();}
}