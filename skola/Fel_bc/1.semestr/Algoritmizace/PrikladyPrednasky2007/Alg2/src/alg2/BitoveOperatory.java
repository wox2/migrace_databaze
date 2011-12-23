package alg2;



public class BitoveOperatory {
  public static void main(String[] args) {
    System.out.println("9&3 = "+(9&3));       // 1
     System.out.println("9|3 = "+(9|3));       // 11
     System.out.println("~0 = "+(~0));         // -1
     System.out.println("9>>1 = "+(9>>1));     // 4
     System.out.println("9>>0 = "+(9>>0));     // 9
     System.out.println("9<<1 = "+(9<<1));     // 18
     System.out.println("9<<0 = "+(9<<0));     // 9
     System.out.println("-9>>1 = "+(-9>>1));   // -5
     System.out.println("-9>>>1 = "+(-9>>>1)); // 2147483643
  }
}