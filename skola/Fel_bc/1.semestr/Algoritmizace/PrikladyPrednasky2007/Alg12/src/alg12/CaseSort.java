package alg12;

public class CaseSort {

  static void naplnPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      pole[i] = (int)(Math.random()*10000);
  }

  static void vypisPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      System.out.print(pole[i]+" ");
    System.out.println();
  }

  public static void main(String[] args) {
    int[] pole = new int[30];
    naplnPole(pole);
    System.out.println("neseøazené pole");
    vypisPole(pole);
    caseSort(pole);
    System.out.println("seøazené pole");
    vypisPole(pole);
  }

  static int hexCislice(int x, int r) {
    return (x >> 4*r) & 0xF;
  }

  static void caseSort(int[] pole) {
    FrontaCisel[] prihradky = new FrontaCisel[16];
    int r, j, c;
    for (c=0; c<16; c++)
      prihradky[c] = new FrontaCisel();
    for (r=0; r<8; r++) {
      for (j=0; j<pole.length; j++)
        prihradky[hexCislice(pole[j],r)].vloz(pole[j]);
      j = 0;
      for (c=0; c<16; c++)
        while (!prihradky[c].jePrazdna())
          pole[j++] = prihradky[c].odeber();
    }
  }

}