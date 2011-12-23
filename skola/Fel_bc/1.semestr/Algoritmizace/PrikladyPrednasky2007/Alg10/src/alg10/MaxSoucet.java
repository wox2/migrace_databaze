package alg10;



public class MaxSoucet {
  static int prvni;
  static int posledni;


// øešení s kubickou složitostí
  static int maxSoucet(int[] a) {
    int maxSum = 0;
    for (int i=0; i<a.length; i++)
      for (int j=i; j<a.length; j++) {
        int sum = 0;
        for (int k=i; k<=j; k++)
          sum += a[k];
        if (sum>maxSum) {
          maxSum = sum;
          prvni = i;
          posledni = j;
        }
      }
    return maxSum;
  }

/*
// øešení s kvadratickou složitostí
  static int maxSoucet(int[] a) {
    int maxSum = 0;
    for (int i=0; i<a.length; i++) {
      int sum = 0;
      for (int j=i; j<a.length; j++) {
        sum += a[j];
        if (sum>maxSum) {
          maxSum = sum;
          prvni = i;
          posledni = j;
        }
      }
    }
    return maxSum;
  }
*/
/*
// øešení s lineární složitostí
  static int maxSoucet(int[] a) {
    int maxSum = 0;
    int sum = 0, i = 0;
    for (int j=0; j<a.length; j++) {
      sum += a[j];
      if (sum>maxSum) {
        maxSum = sum;
        prvni = i;
        posledni = j;
      }
      else if (sum<0) {
        i = j + 1;
        sum = 0;
      }
    }
    return maxSum;
  }
*/

  static void najdiReseni(int[] pole) {
    prvni = 0;
    posledni = 0;
    int soucet = maxSoucet(pole);
    if (soucet>0) {
      prvni++; posledni++;
    }
    System.out.println("i="+prvni+" j="+posledni+" soucet="+soucet);
  }

  public static void main(String[] args) {
    int[] pole1 = {-2, 11, -4, 13, -5, 2};
    int[] pole2 = {1, -3, 4, -2, -1, 6};
    int[] pole3 = {-1, -2, -5, -3};
    najdiReseni(pole1);
    najdiReseni(pole2);
    najdiReseni(pole3);
 }

}