/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cykly;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int soucetAritmetickeRady(int odkud, int kam) {
        int soucetAritmetickeRady = 0;
        for (int i = 0; i <= kam - odkud; i++) {
            soucetAritmetickeRady += kam - i;
        }
        return soucetAritmetickeRady;
    }

    static int soucetAritmetickeRadyRekurzi(int odkud, int kam) {
        int soucetAritmetickeRady;
        if (odkud < kam) {
            soucetAritmetickeRady = odkud + soucetAritmetickeRadyRekurzi(odkud + 1, kam);
        } else {
            soucetAritmetickeRady = odkud;
        }
        return soucetAritmetickeRady;
    }

    static double nMocnina(double cislo, int exponent) {
        double mocnina = 1;
        for (int n = 0; n < exponent; n++) {
            mocnina *= cislo;
        }
        return mocnina;
    }

    static long Faktorial(int cislo) {
        long faktorial = 1;
        if (cislo > 0) {
            faktorial = cislo * Faktorial(cislo - 1);
        }
        return faktorial;
    }

    public static void main(String[] args) {
        //int odkud = new Scanner(System.in).nextInt();
        //int kam = new Scanner(System.in).nextInt();
        //System.out.println(soucetAritmetickeRady(odkud, kam));
        //System.out.println(soucetAritmetickeRadyRekurzi(odkud, kam));
        //double cislo = new Scanner(System.in).nextDouble();
        int exponent = new Scanner(System.in).nextInt();
        System.out.println(Faktorial(exponent));
        //System.out.println(nMocnina(cislo, exponent));
    }
}



