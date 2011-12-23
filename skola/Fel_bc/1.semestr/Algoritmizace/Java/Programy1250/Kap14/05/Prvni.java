/* Soubor Kap14\05\Prvni.java
 * Hlavní program, který vytvoøí okno, nic víc (jako pøedtím)
 */

import javax.swing.UIManager;
import javax.swing.JFrame;

public class Prvni
{
	public static void main(String[] a)
	{
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		vokno.Vokno okno = new vokno.Vokno();
		okno.setVisible(true);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}