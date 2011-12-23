package alg11;

public class Citac {
  private int hodn;
  private int pocHodn;

  public Citac(int ph) {
    pocHodn = ph;
    hodn = ph;
  }

  public void zvetsit() {hodn++;}

  public void zmensit() {hodn--;}

  public void nastavit() {hodn = pocHodn;}

  public int hodnota() {return hodn;}

}