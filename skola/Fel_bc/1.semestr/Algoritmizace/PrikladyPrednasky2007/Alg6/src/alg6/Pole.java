package alg6;
import java.util.*;
class Pole {
public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
int n=sc.nextInt();
if (n > 9)n = 9;
int pole1[] = new int[n];
for (int i = 0; i < pole1.length; i++)        	pole1[i]=sc.nextInt();
int pole2[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};   	System.out.println(soucin(pole1, pole2));
  }
  static int soucin(int[] p1, int[] p2){
  int souc=0;
   for (int i = 0; i < p1.length; i++)
     souc=souc + p1[i]*p2[i];
   return souc;
  }
}
