package alg10;



public class MergeSort2 {
  static void naplnPole(int[] pole) {
    for (int i=0; i<pole.length; i++)
      pole[i] = (int)(Math.random()*90+10);
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
    System.out.println(levy+" "+poslLevy+" "+pravy+" "+poslPravy);
    while (levy<=poslLevy && pravy<=poslPravy)
      if (a[levy]<a[pravy])
        b[i++] = a[levy++];
      else
        b[i++] = a[pravy++];
    while (levy<=poslLevy) b[i++] = a[levy++];
    while (pravy<=poslPravy) b[i++] = a[pravy++];
  }

  public static void mergeSort(int[] a) {
    int[] pom = new int[a.length];
    int[] odkud = a;
    int[] kam = pom;
    int delkaUseku = 1;
    int posl = a.length-1;
    while (delkaUseku<a.length) {
      System.out.println("delka useku = "+delkaUseku);
      int levy = 0;
      while (levy<=posl) {
        int pravy = levy+delkaUseku;
        merge(odkud, kam, levy, Math.min(pravy, a.length), Math.min(pravy+delkaUseku-1, posl));
        levy = levy+2*delkaUseku;
      }
      vypisPole(kam);
      delkaUseku = 2*delkaUseku;
      int[] p = odkud; odkud = kam; kam = p;
    }
    if (odkud!=a)
      for (int i=0; i<a.length; i++) a[i] = pom[i];
  }
}