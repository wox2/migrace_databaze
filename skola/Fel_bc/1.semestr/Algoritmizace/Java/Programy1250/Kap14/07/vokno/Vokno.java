/* Soubor Kap14\07\vokno\Vokno.java
 * Okno programu Piškorky 2.1
 *
 * Okno pøedstavuje hrací plochu, složenou z tlaèítek
 * položených na jednom z panelù
 * vylepšeno o nabídku, která obsahuje i volby
 */


package vokno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vokno extends JFrame
{
	final String titulek = "Piškorky 2.0";
	boolean tahneKruh;
	int n = OknoVoleb.HORNI_MEZ;
	String obr1 = "S_kriz2.gif";
	String obr2 = "S_kruh2.gif";
	Icon kriz = new ImageIcon(obr1);
	Icon kruh = new ImageIcon(obr2);
        JLabel textKdoTahne = new JLabel("Na tahu je  ");
        JLabel kdoTahne = new JLabel();

	JPanel okno;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton[][] policka = new JButton[n+1][n+1];

	JButton posledni = null;		// Pamatuje si poslední tah
        Object[] aktualniNastaveni = {obr1, obr2, new Integer(n)};
        vokno.OknoVoleb vol = new vokno.OknoVoleb(this, aktualniNastaveni);
	BorderLayout bl = new BorderLayout(1,1);


	JMenuBar pruhNabidky = new JMenuBar();
	JMenu nabidkaHra = new JMenu("Hra");
	JMenuItem hraNova = new JMenuItem("Nová", 'N');
	JMenuItem hraZpet = new JMenuItem("Zpìt", 'Z');
	JMenuItem hraKonec = new JMenuItem("Konec", 'K');

	JMenu nabidkaVolby = new JMenu("Volby");
	JMenuItem nastaveni = new JMenuItem("Nastavení...", 'N');

	JMenu nabidkaNapoveda = new JMenu("Nápovìda");
	JMenuItem oProg = new JMenuItem("O programu...", 'O');

	public Vokno()
	{
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

        private void pocatecniNapoveda()
        {
          tahneKruh = false;
          kdoTahne.setIcon(tahneKruh ? kruh : kriz);
        }

        private void zmenNapovedu()
        {
          tahneKruh = !tahneKruh;
          kdoTahne.setIcon(tahneKruh ? kruh : kriz);
        }

	private void hraciTlacitka()
	{
                panel2.setLayout(new GridLayout(n+1,n+1));
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

	private void nabidky()
	{
		setJMenuBar(pruhNabidky);	// Pøipoj nabídkový pruh k oknu

		pruhNabidky.add(nabidkaHra);	// Vlož do nìj hlavní nabídky
		pruhNabidky.add(nabidkaVolby);
		pruhNabidky.add(nabidkaNapoveda);

		nabidkaHra.add(hraNova);	// Vlož do jednotlivých nabídek jejich položky
		nabidkaHra.add(hraZpet);
		nabidkaHra.addSeparator();
		nabidkaHra.add(hraKonec);

		nabidkaVolby.add(nastaveni);
		nabidkaNapoveda.add(oProg);
		// Registrace posluchaèù událostí
		hraKonec.addActionListener(new ActionListener() {	// Konec
			public void actionPerformed(ActionEvent e) {
				nabidkaKonec_Akce(e);}
		});
		hraZpet.addActionListener(new ActionListener() {	// Zpìt
			public void actionPerformed(ActionEvent e) {
				nabidkaZpet_Akce(e);}
		});
		oProg.addActionListener(new ActionListener() {		// O programu
			public void actionPerformed(ActionEvent e) {
				nabidkaOProg_Akce(e);}
		});
		hraNova.addActionListener(new ActionListener() {	// Nová hra
			public void actionPerformed(ActionEvent e) {
				nabidkaNova_Akce(e);}
		});
		nastaveni.addActionListener(new ActionListener() {	// Volby
			public void actionPerformed(ActionEvent e) {
				nabidkaVolby_Akce(e);}
		});
	}


	private void jbInit() throws Exception
	{
		setTitle(titulek);
		okno = (JPanel)getContentPane();
		okno.setLayout(bl);
                pocatecniNapoveda();
		okno.add(panel1, BorderLayout.NORTH);
		okno.add(panel2, BorderLayout.CENTER);
		panel1.add(textKdoTahne, null);
		textKdoTahne.setFont(new java.awt.Font("Dialog", 0, 20));
		panel1.add(kdoTahne, null);
		panel1.setBackground(new Color(200, 200, 200));
		hraciTlacitka();
		nabidky();
	}

	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
			System.exit(0);
	}
	protected void tlacitkoNandejIkonu(ActionEvent e)
	{
                posledni = (JButton)e.getSource();
		posledni.setIcon(tahneKruh ? kruh : kriz);
		zmenNapovedu();
	}
	protected void nabidkaKonec_Akce(ActionEvent e)
	{
                int i = JOptionPane.showConfirmDialog(this, "Opravdu chceš skonèit?", titulek, JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) System.exit(0);
	}
	protected void nabidkaZpet_Akce(ActionEvent e)
	{
		if(posledni != null) {
			posledni.setIcon(null);
			posledni = null;
			zmenNapovedu();
		}
	}
	protected void nabidkaOProg_Akce(ActionEvent e)
	{
		JOptionPane.showMessageDialog(this, "Hrací plocha pro pišk(v)orky\n© Piškorky software 2001", "Piškorky 2.0", JOptionPane.PLAIN_MESSAGE, new ImageIcon("strom.gif"));
	}
	protected void nabidkaNova_Akce(ActionEvent e)
	{
		for(int i = 0; i < n; i++)
		for(int j = 0; j < n; j++)
			policka[i+1][j+1].setIcon(null);
		pocatecniNapoveda();
		posledni = null;
	}
	protected void nabidkaVolby_Akce(ActionEvent e)
	{
		vol.setVisible(true);
                vol.setLocation(this.getLocation());
                if(vol.getStiskOK())
                {
                  n = ((Integer)aktualniNastaveni[2]).intValue();
                  obr1 = (String)aktualniNastaveni[0];
                  obr2 = (String)aktualniNastaveni[1];
                  kriz = new ImageIcon(obr1);
	          kruh = new ImageIcon(obr2);
                  pocatecniNapoveda();
                  panel2.removeAll();
                  this.repaint();
                  hraciTlacitka();
                }
	}
}