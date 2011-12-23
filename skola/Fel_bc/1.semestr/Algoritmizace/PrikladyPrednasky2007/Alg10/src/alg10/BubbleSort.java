package alg10;



public class BubbleSort {
  static void naplnPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      pole[i] = (int)(Math.random()*100);
  }

  static void vypisPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      System.out.print(pole[i]+" ");
    System.out.println();
  }

  public static void main(String[] args) {
    int[] pole = new int[20];
    naplnPole(pole);
    System.out.println("neseøazené pole");
    vypisPole(pole);
    bubbleSort2(pole);
    System.out.println("seøazené pole");
    vypisPole(pole);
  }

  static void bubbleSort(int[] pole) {
    int pom;
    for (int n=pole.length-1; n>0; n--)
      for (int i=0; i<n; i++)
        if (pole[i]>pole[i+1]) {
          pom = pole[i]; pole[i] = pole[i+1]; pole[i+1] = pom;
        }
  }

  static void bubbleSort2(int[] pole) {
    int pom, pruchod = 0;
    int n = pole.length-1;
    boolean vymena;
    do {
      vymena = false;
      for (int i=0; i<n; i++)
        if (pole[i]>pole[i+1]) {
          pom = pole[i]; pole[i] = pole[i+1]; pole[i+1] = pom;
          vymena = true;
        }
      pruchod++;
    } while (vymena);
    System.out.println(pruchod);
  }

}