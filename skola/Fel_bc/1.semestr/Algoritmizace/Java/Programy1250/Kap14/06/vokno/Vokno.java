/* Soubor Kap13\06\vokno\Vokno.java
 * Okno programu Piškorky 1.0
 * 
 * Okno pøedstavuje hrací plochu, složenou z tlaèítek položených na jednom z panelù
 */


package vokno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vokno extends JFrame
{
	final String titulek = "Piškorky 1.0";
	boolean tahneKruh = false;
	int n = 10;
	Icon kriz = new ImageIcon("S_kriz.gif");
	Icon kruh = new ImageIcon("S_kruh.gif");
	JPanel okno;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel(new GridLayout(n+1,n+1));
	JLabel textKdoTahne = new JLabel("Na tahu je  ");
	JLabel kdoTahne = new JLabel(tahneKruh ? kruh : kriz);
	JButton[][] policka = new JButton[n+1][n+1];
	
	BorderLayout bl = new BorderLayout(1,1);

	public Vokno()
	{
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	private void hraciTlacitka()
	{
		Color barvaOkraje = new Color(0xA0,0xA0, 0xA0);
		for(int i = 0; i < n+1; i++)
			for(int j = 0; j < n+1; j++)
		{
			JButton b = new JButton();
			policka[i][j] = b;
			panel2.add(b, null);
			if(i != 0 && j != 0)b.addActionListener(
				new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tlacitkoNandejIkonu(e);
				}
			});
		}
		for(int i = 1; i < n+1; i++) 
		{
			policka[i][0].setBackground(barvaOkraje);
			policka[i][0].setText(""+i);
			policka[0][i].setBackground(barvaOkraje);
			policka[0][i].setText(""+(char)('A'+i-1));
		}
		policka[0][0].setBackground(barvaOkraje);
	}


	private void jbInit() throws Exception
	{
		setTitle(titulek);
		okno = (JPanel)getContentPane();
		okno.setLayout(bl);
		
		okno.add(panel1, BorderLayout.NORTH);
		okno.add(panel2, BorderLayout.CENTER);
		panel1.add(textKdoTahne, null);
		textKdoTahne.setFont(new java.awt.Font("Dialog", 0, 20));
		panel1.add(kdoTahne, null);
		panel1.setBackground(new Color(200, 200, 200));
		hraciTlacitka();
		
	}

	protected void tlacitkoNandejIkonu(ActionEvent e)
	{
		((JButton)e.getSource()).setIcon(tahneKruh ? kruh : kriz);
		tahneKruh = ! tahneKruh;
		kdoTahne.setIcon(tahneKruh ? kruh : kriz);
	}
}