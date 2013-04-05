/* Soubor Kap14\01-1_0\vokno\Vokno.java
 * První okno
 * aby se program choval pøi uzavøení okna korektnì (aby skonèil),
 * je tøeba upravit reakci na událost, která nastane pøi
 * uzavøení okna.
 * Program ukazuje øešení použitelné ve všech verzích Javy:
 * povoluje v konstruktoru okna "okenní" události a pøedefinovává
 * chránìnou metodu processWindowEvent().
 */

package vokno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vokno extends JFrame
{
	// Øešení pro JDK 1.0 a pozdìjší
	public Vokno()	
	{
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	}
	
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);	
		}	
	}
}