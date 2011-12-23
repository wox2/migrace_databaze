/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import GUI.Win;
/**
 * @author já
 */
public class MainLayout extends JFrame{

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
		JFrame frame = new MainLayout();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
       
		//frame.pack();
		//frame.setResizable(false);
		frame.setVisible(true);
                Win win = GUI.Win.getWin();
	}

    public MainLayout(){
        super("Okno aplikace");


    }

}
