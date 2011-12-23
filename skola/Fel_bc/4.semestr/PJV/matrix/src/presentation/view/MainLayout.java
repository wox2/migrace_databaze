/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.view;

import Core.Matrix;
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
		/*JFrame frame = new MainLayout();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
       
		//frame.pack();
		//frame.setResizable(false);
		frame.setVisible(true);*/
             //   Win win = GUI.Win.getWin();
        float[][] a=new float[][]{{2,36,37,22,8},{17,3,319,29},{19,19,35,16,33},{30,33,5,32,4},{31,21,25,23,30}};
            float[][] b=new float[][]{{16},{28},{17},{5},{20}};
            Matrix m1= new Matrix(a);
            Matrix m2 = new Matrix(b);
            Matrix m3 = m1.multiple(m2);
            for(int i=0;i<m3.getNumberRows();i++){
                for(int j=0;j<m3.getNumberColumns();j++){
                    System.out.print(m3.getField(i, j)%26+" ");
                }
                System.out.println("");
            }

	}

    public MainLayout(){
        super("Okno aplikace");


    }

}
