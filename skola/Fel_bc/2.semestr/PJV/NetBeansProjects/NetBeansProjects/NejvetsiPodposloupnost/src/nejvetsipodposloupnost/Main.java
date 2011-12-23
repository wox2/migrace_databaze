/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nejvetsipodposloupnost;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ArrayList<Integer> action(ArrayList<Integer> pole) {
        ArrayList<Integer> rostouci = new ArrayList<Integer>();
        Integer clen=Integer.MIN_VALUE;
        for (int i = 1; i < pole.size(); i++) {

            if (clen < pole.get(i)) {
                rostouci.add(pole.get(i));
                clen=pole.get(i);
            }
        }
        return rostouci;
    }

    public static ArrayList<Integer> nPP(ArrayList<Integer> pole) {

        ArrayList longest = new ArrayList<Integer>();
        longest = action(pole);
        ArrayList<Integer> copy = new ArrayList();
        for (int i = 0; i < pole.size(); i++) {
            copy.set(i, pole.get(i + 1));
        }

        ArrayList<Integer> possibleLongest = new ArrayList<Integer>();
        for (int i = 0; i < copy.size(); i++) {
            possibleLongest=action(copy);
          if (possibleLongest.size()>longest.size()) { longest=possibleLongest;}
        }


            return longest;
        }


    public static void print(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        System.out.println("Zadej pocet clenu posloupnosti:");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        ArrayList posloupnost = new ArrayList();
        for (int i = 0; i < a; i++) {
            System.out.println("Zadejte " + (i + 1) + " clen posloupnosti:");
            posloupnost.add(sc.nextInt());
        }
        System.out.print("Zadana posloupnost: ");
        print(posloupnost);
        System.out.println("---Vysledky---");
        print(action(posloupnost));
    }
}

