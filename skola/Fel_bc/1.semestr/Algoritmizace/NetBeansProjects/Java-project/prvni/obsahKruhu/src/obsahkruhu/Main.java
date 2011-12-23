/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obsahKruhu;

import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Main {

    static double obsahKruhu(double polomer) {
        double obsahKruhu = kvadrat(polomer) * Math.PI;
        return obsahKruhu;

    }

    static double kvadrat(double hodnota) {
        return hodnota * hodnota;
    }

    static void test() {
        double polomer = new Scanner(System.in).useLocale(Locale.US).nextDouble();
        double znamyObsahKruhu = polomer * polomer * Math.PI;
        double vypocetObsahuKruhu = obsahKruhu(polomer);
        if (znamyObsahKruhu == vypocetObsahuKruhu) {
            System.out.println("spravne "+Math.round(vypocetObsahuKruhu));
        } else {
            System.out.println("špatné");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test();
    }
}
