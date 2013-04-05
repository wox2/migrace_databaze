/* Soubor Kap14\04\Prvni.java
 * Vytvoøí okno a nastaví vzhled ("Look and Feel")
 * Tøída Vokno ukazuje vytvoøení tlaèítek (JButton) a jejich umístìní 
 * s použitím správce umístìní (Layout Manager).
 */ 

//import java.awt.*;
import javax.swing.*;

public class Prvni
{
	public static void main(String[] a)
	{
		try {
			//UIManager.setLookAndFeel("javax.swing.plaf.motif.MotifLookAndFeel");
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