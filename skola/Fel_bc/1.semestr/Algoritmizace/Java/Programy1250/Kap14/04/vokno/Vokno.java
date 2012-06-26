/* Soubor Kap14\04\vokno\Vokno.java 
 * Ukazuje vytvoøení komponenty (tlaèítka) a 
 * jeho umístìní do okna pomocí správce umístìní Borderlayout.
 * Lze snadno upravit a použít jiné správce umístìní (tak vznikly 
 * odpovídající obrázky v knize).
 * Pro všechny verze JDK 
 */
package vokno;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Vokno extends JFrame {

	BorderLayout bl = new BorderLayout(20, 10);
	JButton tlacitko1 = new JButton();
	JButton tlacitko2 = new JButton();
	JButton tlacitko3 = new JButton();
	JButton tlacitko4 = new JButton();
	JButton tlacitko5 = new JButton();
  
	public Vokno() {		
		// enableEvents(AWTEvent.WINDOW_EVENT_MASK); // JDK 1.0
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ro = new Dimension();
		ro.height = obrazovka.height/2;
		ro.width = obrazovka.width/2;
		setSize(ro);
		setLocation(ro.width/2, ro.height/2);
		setTitle("Naše první okno s tlaèítky, BorderLayout");
		tlacitko1.setText("Šlus");
		tlacitko2.setText("Skoè do pole");
		tlacitko3.setText("Zmodrej");
		tlacitko4.setText("Zezelenej");
		tlacitko5.setText("Ty mnì taky");
		getContentPane().setLayout(bl);
		getContentPane().add(tlacitko1, BorderLayout.CENTER);
		getContentPane().add(tlacitko2, BorderLayout.NORTH);
		getContentPane().add(tlacitko3, BorderLayout.WEST);
		getContentPane().add(tlacitko4, BorderLayout.SOUTH);
		getContentPane().add(tlacitko5, BorderLayout.EAST);
	} 
	
}
