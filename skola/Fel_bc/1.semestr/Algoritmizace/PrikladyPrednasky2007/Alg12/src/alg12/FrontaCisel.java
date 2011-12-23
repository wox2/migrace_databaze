package alg12;

// Fronta celých èísel implementovaná spojovým seznamem

class PrvekFronty {
  PrvekFronty dalsi;
  int hodn;
}

public class FrontaCisel {
  private PrvekFronty celo;
  private PrvekFronty volny;

  public FrontaCisel() {
    celo = new PrvekFronty();
    volny = celo;
  }

  public void vloz(int x) {
    volny.hodn = x;
    volny.dalsi = new PrvekFronty();
    volny = volny.dalsi;
  }

  public int odeber() {
    if (jePrazdna())
      throw new RuntimeException("odebrání z prázdné fronty");
    int vysl = celo.hodn;
    celo = celo.dalsi;
    return vysl;
  }

  public boolean jePrazdna() {
    return celo == volny;
  }

  public int znakNaCele()
  {return celo.hodn;}

}