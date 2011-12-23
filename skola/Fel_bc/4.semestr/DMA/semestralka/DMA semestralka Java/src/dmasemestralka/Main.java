/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dmasemestralka;

import java.math.BigInteger;

/**
 *
 * @author woxie
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static BigInteger toBig(int i) {
        return new BigInteger(Integer.toString(i));
    }

    public static void main(String[] args) {

        //System.out.println(Gcd.getInversion(2, 5));
        System.out.println("priklad 1");
        int[] vysledky = {57, 2, 39, 8, 24};
        int[] moduly = {61, 49, 41, 39, 38};
        int a1 = (moduly[1] * moduly[2] * moduly[3] * moduly[4]);
        int a2 = (moduly[0] * moduly[2] * moduly[3] * moduly[4]);
        int a3 = (moduly[0] * moduly[1] * moduly[3] * moduly[4]);
        int a4 = (moduly[0] * moduly[1] * moduly[2] * moduly[4]);
        int a5 = (moduly[0] * moduly[1] * moduly[2] * moduly[3]);
        System.out.println("Mame rovnice: x= " + vysledky[0] + " mod(" + moduly[0] + ")");
        System.out.println("Mame rovnice: x= " + vysledky[1] + " mod(" + moduly[1] + ")");
        System.out.println("Mame rovnice: x= " + vysledky[2] + " mod(" + moduly[2] + ")");
        System.out.println("Mame rovnice: x= " + vysledky[3] + " mod(" + moduly[3] + ")");
        System.out.println("Mame rovnice: x= " + vysledky[4] + " mod(" + moduly[4] + ")");
        System.out.println("");
        System.out.println("Overime, jestli gcd dvojic modulu je 1");

        int[] pole = new int[]{61, 49, 41, 39, 38};

        for (int i = 0; i < pole.length; i++) {
            for (int j = i + 1; j < pole.length; j++) {
                System.out.println("GCD(" + moduly[i] + "," + moduly[j] + ")=" + Gcd.getGcd(pole[i], pole[j]));
            }
        }
        BigInteger modulM = toBig(moduly[0] * a1);
        System.out.println("");
        System.out.println("ModulM: " + modulM + " je modulem soustavy, ve ktere existuje podle cinske vety jednoznacne reseni. ");
        System.out.println("Pomoci vzorce pro cinskou vetu: x = M/m1 * (N1)+ M/m2 * (N2) + .. + M/m4 * (N4)");
        System.out.println("Inverze- N1=" + a1 + ", N2=" + a2 + ", N3=" + a3 + ", N4=" + a4 + ", N5=" + a5);
        System.out.println("");
        BigInteger x = toBig(vysledky[0]).multiply(toBig(a1)).multiply(toBig(Gcd.getInversion(a1, moduly[0])));
        x = x.add(toBig(vysledky[1]).multiply(toBig(a2)).multiply(toBig(Gcd.getInversion(a2, moduly[1]))));
        x = x.add(toBig(vysledky[2]).multiply(toBig(a3)).multiply(toBig(Gcd.getInversion(a3, moduly[2]))));
        x = x.add(toBig(vysledky[3]).multiply(toBig(a4)).multiply(toBig(Gcd.getInversion(a4, moduly[3]))));
        x = x.add(toBig(vysledky[4]).multiply(toBig(a5)).multiply(toBig(Gcd.getInversion(a5, moduly[4]))));

        System.out.println("x=" + x + " mod(" + modulM + ")");


        while (-1 < x.compareTo(modulM)) {
            x = x.subtract(modulM);
            //     System.out.println(x + " odecteno "+ ++pokusu + " krat");
        }
        BigInteger y=x;
        x = x.add(modulM).add(modulM).add(modulM).add(modulM);
        System.out.println("Hledame pate nejmensi cislo, ktere vyhovuje temto podminkam, nejmensi takove je " + y
                + " takze pate nejmensi ziskame prictenim 4*modulM = " + x);


        int b0, b1, b2, b3, b4;
        String str = x.toString();
        while (str.length() < 10) {
            str = "0" + str;
        }


        b4 = (int) (str.charAt(9) - '0') + (int) (str.charAt(8) - '0') * 10;
        b3 = (int) (str.charAt(7) - ('0')) + ((int) str.charAt(6) - ('0')) * 10;
        b2 = (int) (str.charAt(5) - ('0')) + ((int) str.charAt(4) - ('0')) * 10;
        b1 = (int) (str.charAt(3) - ('0')) + ((int) str.charAt(2) - ('0')) * 10;
        b0 = (int) (str.charAt(1) - ('0')) + ((int) str.charAt(0) - ('0')) * 10;

        System.out.println(b0+ "  " + b1+ "  " + b2+ "  " + b3 + "  " + b4);

        /*






        int[][] matrixHillCipher = {{23, 37, b0, 20, 37}, {b1, 3, 22, 14, 24}, {30, 22, 16, 37, b2,}, {23, b3, 4, 3, 15}, {5, 20, 35, b4, 19}};
        System.out.println("");
        System.out.println("Pocitame 2. priklad:");
        HillovaSifra.print(matrixHillCipher, 38);
        System.out.println("Hledame inverzni matici k tomuto sifrovacimu klici.");
        int[][] inverse = HillovaSifra.getInverseMatrix(matrixHillCipher, 38);
        HillovaSifra.print(inverse, 38);

        final int POCET_PISMEN = 38;
        int[][] zasifrovanaZprava = {{4}, {3}, {9}, {5}, {5}};
        int[][] desifrovanaZprava = HillovaSifra.nasobeniMatic(inverse, zasifrovanaZprava, POCET_PISMEN);
        System.out.println("Desifrovana zprava");
        HillovaSifra.print(desifrovanaZprava, POCET_PISMEN);
        System.out.println("Desifrovana prvni zprava:");
        for (int i = 0; i < desifrovanaZprava.length; i++) {
            for (int j = 0; j < desifrovanaZprava[0].length; j++) {
                System.out.print((char) ((int) 'a' + desifrovanaZprava[i][j] - 1));
            }
        }
        System.out.println("");
        System.out.println("--------------------------------------------------------------");
        System.out.println("");
        System.out.println("Data pro dalsi vypocet:");
        int [][]zasifrovanaZprava2={{10},{3},{1},{20},{5}};
        int [][] desifrovanaZprava2 = HillovaSifra.nasobeniMatic(inverse, zasifrovanaZprava2, POCET_PISMEN);
        HillovaSifra.print(desifrovanaZprava2, POCET_PISMEN);
        */
    }
}
