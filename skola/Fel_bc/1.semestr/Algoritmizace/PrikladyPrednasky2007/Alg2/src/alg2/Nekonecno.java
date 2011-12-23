

package alg2;


public class Nekonecno {
    public static void main(String[] args) {
     double nula = 0.0;
     double vysledek = +5.0 / nula;
     System.out.println(vysledek);
     if (Double.isInfinite(vysledek) == true)
      System.out.println("nekonecno");
     vysledek = -5.0 / nula;
     System.out.println(vysledek);
     if (Double.isInfinite(vysledek) == true)
      System.out.println(" -nekonecno");
     System.out.println("MAX = " + Float.MAX_VALUE + ", 2 * MAX = " + (2 * Float.MAX_VALUE));
     vysledek = nula / nula;
     System.out.println(vysledek);
     if (Double.isNaN(vysledek) == true)
       System.out.println("neni cislo");
 }
}
