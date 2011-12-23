/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helloworld;

/**
 *
 * @author ssssss
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Zadej první sčítanec:");
        int i=MojeIO.inInt();
        System.out.println("Zadej druhý sčítanec");
        int j=MojeIO.inInt();
        System.out.println("Jejich soucet je "+(i+j));
    }

}
