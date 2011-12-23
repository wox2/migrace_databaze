package alg6;

import java.util.*;
public class Teploty {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    int teploty[] = {2 ,3,4,2,3,4,3}; // vytvoøení pole o 7mi prvcích
   // for (int i=0; i<7; i++)
   //   teploty[i] = Sys.readInt();
    int prumer = 0;
    for (int i=0; i<7; i++)
      prumer = prumer + teploty[i];
    prumer = prumer / 7;
    for (int i=0; i<teploty.length; i++)
      System.out.println(teploty[i]-prumer);
  }
}
