/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication61;

/**
 *
 * @author já
 */

public class Box {
     private int contents;
     public Box(int param) {
         contents = param;
     }
     public static void f(Box param) {
         param = new Box( 3);
         param.contents++;
     }
     public static void main(String[] args) {
         Box e = new Box( 1);
         f( e);
         System.out.println( e.contents);

}

}
