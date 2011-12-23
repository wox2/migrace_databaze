/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication57;

import javax.swing.SwingUtilities;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
   /* collection coll=new collection();
    coll.add(66);
    coll.add(66);
    coll.add(66);
    coll.add(634);
    coll.print();

    collection bol=new collection();

    bol.add(66);
    bol.add(66);
    bol.add(66);
    bol.add(634);
    bol.print();
   */
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            NewJFrame m = new NewJFrame();
            m.setVisible(true);
       }
   });
}


    

}
