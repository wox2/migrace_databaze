/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dmasemestralka;


/**
 *
 * @author woxie
 */
public class HillovaSifra {

    private static void jordanEli(int[][] matrix, int i, int modul) {
        for (int j = i - 1; j >= 0; j--) {
            int komplement = modul - matrix[j][i];
            for (int k = i; k < matrix[0].length; k++) {
                matrix[j][k] = (matrix[j][k] + matrix[i][k] * komplement) % modul;
            }
            print(matrix, modul);
        }
    }

    private static void gaussEli(int[][] matrix, int i, int modul) {
        for (int j = i + 1; j < matrix.length; j++) {
            int komplement = modul - matrix[j][i];
            for (int k = i; k < matrix[0].length; k++) {
                matrix[j][k] = (matrix[j][k] + matrix[i][k] * komplement) % modul;
            }
            print(matrix, modul);
        }
    }

    private static void switchRows(int[][] matrix, int i, int j) {
        int[] help = matrix[j];
        matrix[j] = matrix[i];
        matrix[i] = help;
    }

    public static void normalize(int[][] matrix, int i, int modul) {
        int inverse = Gcd.getInversion(matrix[i][i], modul);
        for (int j = 0; j < matrix[i].length; j++) {
            matrix[i][j] = matrix[i][j] * inverse % modul;
        }
    }

    public static void print(int[][] matrix, int modul) {
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (Integer.toString(matrix[i][j]).length() < Integer.toString(modul).length()) {
                    for (int k = 0; k < Integer.toString(modul).length() - Integer.toString(matrix[i][j]).length(); k++) {
                        System.out.print(" ");
                    }
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("--------------------------------------------------");
    }

    static int[][] dostanRozsirenouMatici(int[][] matrix) {
        int[][] help = new int[matrix.length][matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                help[i][j] = matrix[i][j];
            }
            help[i][i + matrix.length] = 1;
        }
        return help;
    }

    public static int[][] getInverseMatrix(int[][] matrix, int modul) {
        int[][] help = dostanRozsirenouMatici(matrix);
        for (int i = 0; i < help.length; i++) {
            if (Gcd.getGcd(modul, help[i][i]) != 1) {
                for (int j = i + 1; j < help.length; j++) {
                    if (Gcd.getGcd(modul, help[j][i]) == 1) {
                        switchRows(help, i, j);
                    }
                }
            }
            normalize(help, i, modul);
            gaussEli(help, i, modul);
        }
        for (int i = help.length-1; i > 0; i--) {
            jordanEli(help, i, modul);
        }
         return orez(help);
    }

    private static int[][] orez(int [][]matrix){
        int [][]help=new int[matrix.length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for(int j=matrix.length;j<matrix[0].length;j++){
                help[i][j-matrix.length]=matrix[i][j];
            }
        }
        return help;
    }

    public static int [][] nasobeniMatic(int[][] m1,int [][]m2, int modul){
        int [][] matrix = new int [m1.length][m2[0].length];
        for(int i=0;i<m1.length;i++){
            for(int j=0;j< m2[0].length ;j++){
                for (int k = 0;k<m1[0].length;k++){
                    matrix[i][j]=(matrix[i][j]+m1[i][k]*m2[k][j])%modul;
                }
            }
        }
        return matrix;
    }
}
