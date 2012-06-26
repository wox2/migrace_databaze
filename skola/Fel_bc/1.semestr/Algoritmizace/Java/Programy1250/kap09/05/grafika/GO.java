/* Soubor Kap09\05\grafika\GO.java */
package grafika;

public class GO {
  private int barva;

  public int getBarva() { return barva;}
  public void setBarva(int _barva){ barva = _barva;}
  public GO() {}
  public GO(int _barva){setBarva(_barva);}
  public void nakresli()
  {
    System.out.println("Kreslim GO, barva " + barva);
  }
}