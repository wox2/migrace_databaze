package alg4;



public class Zastineni {
  static int a = 10;
  public static void main(String[] args) {
    f();
    System.out.println(a);
  }
  static void f() {
    int a = 20;
    System.out.println(a);
   System.out.println(Zastineni.a);
  }
}
