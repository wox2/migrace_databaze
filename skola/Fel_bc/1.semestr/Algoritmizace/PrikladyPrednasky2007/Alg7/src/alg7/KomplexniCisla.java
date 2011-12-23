package alg7;



public class KomplexniCisla {
  public static void main(String[] args) {
    Complex c1 = new Complex(10);
    Complex c2 = new Complex(3,4);
    System.out.println("abs("+c1+")="+c1.abs());
    System.out.println("abs("+c2+")="+c2.abs());
    Complex c3 = c1.plus(c2);
    System.out.println(c1+"+"+c2+"="+c3);
  }
}