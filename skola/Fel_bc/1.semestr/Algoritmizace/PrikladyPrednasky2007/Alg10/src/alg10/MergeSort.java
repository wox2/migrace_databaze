package alg10;



public class MergeSort {
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
    int[] pole = new int[19];
    naplnPole(pole);
    System.out.println("neseøazené pole");
    vypisPole(pole);
    mergeSort(pole);
    System.out.println("seøazené pole");
    vypisPole(pole);
  }

  private static void merge(int[] a, int[] b,
                            int levy, int pravy, int poslPravy) {
    int poslLevy = pravy-1;
    int i = levy;
    int prvniLevy = levy;
    while (levy<=poslLevy && pravy<=poslPravy)
      if (a[levy]<a[pravy])
        b[i++] = a[levy++];
      else
        b[i++] = a[pravy++];
    while (levy<=poslLevy) b[i++] = a[levy++];
    while (pravy<=poslPravy) b[i++] = a[pravy++];
  }

  private static void mergeSort(int[] a, int[] pom, int prvni, int posl) {
    if (prvni<posl) {
      int stred = (prvni+posl)/2;
      mergeSort(a, pom, prvni, stred);
      mergeSort(a, pom, stred+1, posl);
      merge(a, pom, prvni, stred+1, posl);
      for (int i=prvni; i<=posl; i++) a[i] = pom[i];
    }
  }

  public static void mergeSort(int[] a) {
    int[] pom = new int[a.length];
    mergeSort(a, pom, 0, a.length-1);
  }
}