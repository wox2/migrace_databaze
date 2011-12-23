package alg10;



public class SelectSort {
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
    selectSort(pole);
    System.out.println("seøazené pole");
    vypisPole(pole);
  }

  static void selectSort(int[] pole) {
    int i, j, imin, pom;
    for (i=0; i<pole.length-1; i++) {
      imin = i;
      for (j=i+1; j<pole.length; j++)
        if (pole[j]<pole[imin]) imin = j;
      if (imin!=i) {
        pom = pole[imin];
        pole[imin] = pole[i];
        pole[i] = pom;
      }
    }
  }
}