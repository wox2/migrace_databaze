/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stackque;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  RedBlackStack rb=new RedBlackStack();
   rb.pushBlack(43292);
   rb.pushBlack(43292);
   rb.pushBlack(43292);
   rb.pushBlack(43292);
        System.out.println(rb.popRed());
        System.out.println(rb.popBlack());

    }
}
