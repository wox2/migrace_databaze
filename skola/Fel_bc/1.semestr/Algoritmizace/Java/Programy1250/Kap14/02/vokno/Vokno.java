/* Soubor Kap14\02\vokno\Vokno.java
 * Nastavení rozmìrù, polohy a titulku okna
 */
package vokno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vokno extends JFrame
{
	public Vokno()	
	{
		//enableEvents(AWTEvent.WINDOW_EVENT_MASK); /* JDK 1.0 */
		Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension rozm = new Dimension();
		rozm.height = obrazovka.height/2;
		rozm.width = obrazovka.width/2;
		setSize(rozm);
		setLocation(rozm.width/2,rozm.height/2);
		setTitle("Naše první okno");
		// Ošetøení konce programu - JDK 1.2+
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{ System.exit(0); }
		});
	}
	
	// JDK 1.0
	/* protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);	
		}	
	}
	*/

}