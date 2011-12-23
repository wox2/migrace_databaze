/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nejvetsispolecnydelitel;

import java.util.Scanner;

/**
 *
 * @author já
 */
public class Main {
   static int nejvetsiSpolecnyDelitel(int x,int y){
    int d;
       if (x < y) d=x; else d=y;
     while (x%d!=0 | y%d!=0)d--;
     return d;
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Zadejte cele cislo:");
        Scanner scanner = new Scanner(System.in);
        int x= scanner.nextInt()                ;
        System.out.println("Zadejte druhe cele cislo:");
        int y=scanner.nextInt();
        System.out.println("nejvetsi spolecny delitel cisel " +x +
                " a " + y + " je " + nejvetsiSpolecnyDelitel(x,y));
    }

}
