/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matice2;

/**
 *
 * @author já
 */
public class Main {

    static void vypisMatici(int[][] matice) {
        for (int i = 0; i < matice.length; i++) {
            for (int j = 0; j < matice[i].length; j++) {
                System.out.print(matice[i][j] + " ");
            }
            System.out.println();

        }
    }

    static int[][] scitaniMatic(int[][] scitanec1, int[][] scitanec2) {

        int[][] soucet = new int[2][4];
        for (int i = 0; i < scitanec1.length; i++) {
            for (int j = 0; j < scitanec1[i].length; j++) {
                soucet[i][j] = scitanec1[i][j] + scitanec2[i][j];
            }
        }
        return soucet;
    }

    public static void main(String[] args) {

        int[][] m1 = {{1, 2, 3, 4}, {1, 2, 3, 5}};
        int[][] m2 = {{1, 3, 4, 2}, {3, 4, 1, 0}};
        System.out.println("Prvni matice je: ");
        vypisMatici(m1);
        System.out.println("Druha matice je: ");
        vypisMatici(m2);
        System.out.println("Soucet obou matic je: ");
        vypisMatici(scitaniMatic(m1, m2));
    }
}

