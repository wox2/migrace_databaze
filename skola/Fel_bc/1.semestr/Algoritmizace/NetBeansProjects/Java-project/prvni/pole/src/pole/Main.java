/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pole;

import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static int[] tabFact(int n) {
        n++;
        int[] tabFact = new int[n];
        tabFact[0] = 1;
        for (int i = 1; i < n; i++) {
            tabFact[i] = tabFact[i - 1] * i;
            if ((tabFact[i] / i != tabFact[i - 1]) || (tabFact[i - 1] == -1)) {
                tabFact[i] = -1;
            }
        }
        return tabFact;
    }

    static int fact(int n) {
        int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 2916800, 47001600};
        if (n > 12 || n < 0) {
            return -1;
        } else {
            return fact[n];
        }
    }

    public static void print(int[] p) {
        for (int i = 0; i < p.length; i++) {
            System.out.println(i + "! = " + p[i]);
        }
    }
    
    static void matrix()
    {
        int [][] m = {{1,2,3}, {3,2}, {5,6,6,7}};
        int [][] m2 = new int [3][4]; 
        m2[2][0] = 5;
        for (int i=0; i< m2.length; i++){
            for (int j=0; j< m2[i].length;j++)
            {
                System.out.print(m2[i][j]+" ");
            }
            System.out.println();
        }
              
    }

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        print(tabFact(n));
        System.out.println(fact(n));
        matrix();
    }
}
