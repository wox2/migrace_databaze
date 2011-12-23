package alg3;

import java.util.*;

public class Den {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
      System.out.println("zadejete den, mìsíc a rok");
    int den = sc.nextInt();
    int mesic = sc.nextInt();
    int rok = sc.nextInt();
    int n = 0;
    switch (mesic) {
      case 1: n = den; break;
      case 2: n = 31+den; break;
      case 3: n = 59+den; break;
      case 4: n = 90+den; break;
      case 5: n = 120+den; break;
      case 6: n = 151+den; break;
      case 7: n = 181+den; break;
      case 8: n = 212+den; break;
      case 9: n = 243+den; break;
      case 10: n = 273+den; break;
      case 11: n = 304+den; break;
      case 12: n = 334+den; break;
//      default: System.out.println("nedovoleny mesic"); System.exit(0);
    }
    if (mesic>2 && rok%4==0 && (rok%100!=0 || rok%1000==0))
      n = n+1;
    System.out.println(den+"."+mesic+"."+rok+" je "+n+". den v roce");
  }

}