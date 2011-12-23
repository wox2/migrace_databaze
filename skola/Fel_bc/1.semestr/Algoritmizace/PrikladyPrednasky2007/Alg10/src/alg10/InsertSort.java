package alg10;


public class InsertSort {
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
    insertSort(pole);
    System.out.println("seøazené pole");
    vypisPole(pole);
  }

  static void vloz(int[] pole, int n, int x) {
    int i;
    for (i=n-1; i>=0 && pole[i]>x; i--)
      pole[i+1] = pole[i];
    pole[i+1] = x;
  }

  static void insertSort(int[] pole) {
    for (int n=1; n<pole.length ; n++)
      vloz(pole, n, pole[n]);
  }
}