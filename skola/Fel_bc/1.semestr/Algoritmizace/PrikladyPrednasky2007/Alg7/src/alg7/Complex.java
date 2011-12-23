package alg7;

public class Complex {
// datové složky  
  public double re; public double im;
// konstruktory
  public Complex() {}
  public Complex(double r) {this(r,0.0);}
  public Complex(double r, double i) {re=r; im=i;}
// metody (operace)
  public double abs() {
    return Math.sqrt(re*re+im*im);}
  public Complex plus(Complex c) {
    return new Complex(re+c.re, im+c.im);}
  public Complex minus(Complex c) {
    return new Complex(re-c.re, im-c.im);}
  public String toString() {
    return "["+re+", "+im+"]";
  }
}
