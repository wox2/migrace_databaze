/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication28;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {
    static void loam(){
    moron();
    }
static void moron(){
        try {
            int nadTimZustavaRozumStat = new Scanner(System.in).nextInt();
        }catch (InputMismatchException ChybaVstupu) {
            System.out.println("Musite" +
                    " zadat cislo a ne jiny znak!!");
            loam();
        }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
loam();
    }
}
