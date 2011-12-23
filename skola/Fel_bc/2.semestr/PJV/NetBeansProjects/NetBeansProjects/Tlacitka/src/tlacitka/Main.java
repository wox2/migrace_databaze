/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tlacitka;


import javax.swing.SwingUtilities;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {



        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                MyDlg dlg2=new MyDlg();
                MyDlg dlg = new MyDlg();
                dlg2.setVisible(true);
                dlg.setVisible(true);
                
                
            }
        });

    }
}