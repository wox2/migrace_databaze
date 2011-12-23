/* Soubor Kap14\05\vokno\Vokno.java 
 * Reakce na stisknutí tlaèítka
 *
 ** Pro tlaèítko registrujeme posluchaèe - anonymní instanci
 *  tøídy implementující rozhraní ActionListener. Metoda ActionPerformed
 *  této instance zavolá tzv. handler.
 *
 ** Handler je zpravidla metoda okna. Provede, co je potøeba (zde ukonèí program).
 *
 ** Pro všechny verze JDK
 */
package vokno;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Vokno extends JFrame {

	GridLayout bl = new GridLayout(3, 3, 10, 20);
	JButton tlacitko1 = new JButton();
  
	public Vokno() {		
		// enableEvents(AWTEvent.WINDOW_EVENT_MASK); // jdk 1.0
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
		setTitle("Naše první okno s jedním tlaèítkem, null");
		tlacitko1.setText("Šlus");
		tlacitko1.setBounds(new Rectangle(100, 80, 150, 60));
		getContentPane().setLayout(null);
		getContentPane().add(tlacitko1, null);	
		tlacitko1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tlacitko1Akce(e);
			}
		});

	} 

	// jdk 1.0
/*  	protected void processWindowEvent(WindowEvent e) 
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}
*/
	void tlacitko1Akce(ActionEvent e) {
		System.exit(1);
	}
}
