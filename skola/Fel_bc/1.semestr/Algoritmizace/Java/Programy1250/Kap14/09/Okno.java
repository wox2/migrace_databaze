/* Soubor Kap14\09\Okno.java
 * Okno se tøemi tlaèítky a textovým polem pro testování polohu poèátku
 * souøadnic v oknì
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Okno extends JFrame {
	int x = 20, y = 20;
	JButton tlPlus = new JButton();
	JButton tlMinus = new JButton();
	JButton tl3 = new JButton();
	JTextField souradnice = new JTextField();
	
	JPanel obsah = (JPanel)getContentPane();
	
	public Okno()
	{
		try{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	private void jbInit()
	{
		Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ro = new Dimension();
		ro.height = obrazovka.height/2;
		ro.width = obrazovka.width/2;
		setSize(ro);
		setLocation(ro.width/2,ro.height/2);	

		obsah.setLayout(null);
		tlPlus.setText("+");
		tlPlus.setBounds(new Rectangle(10, ro.height-100, ro.width-25, 25));
		obsah.add(tlPlus);

		tlMinus.setText("-");
		tlMinus.setBounds(new Rectangle(10, ro.height-60, ro.width-25, 25));
		obsah.add(tlMinus);
		
		tl3.setText("Posunovací tlaèítko");
		tl3.setBounds(new Rectangle(x, y, 150, 40));
		obsah.add(tl3);

		souradnice.setText(""+x+", "+y);
		souradnice.setBounds(10, ro.height/2, ro.width-25, 30);		
		obsah.add(souradnice);
		
		tlPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				++x; ++y;
				souradnice.setText(""+x+", "+y);
				tl3.setBounds(new Rectangle(x, y, 150, 40));
			}
		});

		tlMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				--x; --y;
				souradnice.setText(""+x+", "+y);
				tl3.setBounds(new Rectangle(x, y, 150, 40));
			}
		});
	}
}