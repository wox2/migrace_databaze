package alg2;



public class ByteOperand {
  public static void main(String[] args) {
    byte b1 = 32, b2 = 16, b3;
    int i;
    i = b1 + b2;
    b3 = (byte)(b1 + b2);
   System.out.println(i+" "+b3);    // 48 48
//  b3 = b1 + b2;   chyba pøi pøekladu
    i = b1 * b2;
    b3 = (byte)(b1 * b2);
   System.out.println(i+" "+b3);    // 512 0
  }
}