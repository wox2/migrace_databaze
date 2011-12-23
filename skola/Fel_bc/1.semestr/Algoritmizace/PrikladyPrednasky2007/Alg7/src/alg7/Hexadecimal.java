package alg7;

public class Hexadecimal {
  final static String hexa = "0123456789abcdef";

  static String hexadecimal(int x) {
    if (x==0) return "0";
    char[] znaky = new char[9];
    int y;
    if (x<0) y=-x; else y=x;
    int prvni = 9;
    do {
      prvni--;
      znaky[prvni] = hexa.charAt(y%16);
      y = y / 16;
    } while (y>0);
    if (x<0) {
      prvni--; znaky[prvni] = '-';
    }
    return new String(znaky, prvni, 9-prvni);
  }

  public static void main(String[] args) {
    int x = 60;
    System.out.println(x+" hexadecimálnì je "+hexadecimal(x));
  }
}