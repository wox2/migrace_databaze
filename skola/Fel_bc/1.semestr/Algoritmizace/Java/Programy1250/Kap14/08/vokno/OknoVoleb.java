/* Soubor Kap14\08\OknoVoleb.java
 * Okno voleb pro program Piškorky 2.0
 * Definuje konstanty udávající rozmezí poètu tlaèítek
 * Umožòuje zadat nejménì MINIMUM tlaèítek, nejvýše HORNI_MEZ
 * Kombinovaný seznam nabízí hodnoty od DOLNI_MEZ po HORNI_MEZ
 * Okno se po uzavøení nezruší, pouze skryje
 */

package vokno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OknoVoleb extends JDialog
{                                             // Tlaèítek je vždy n x n
  public static final int DOLNI_MEZ = 10;     // Dolní mez hodnot zobrazovaných v kombu
  public static final int HORNI_MEZ = 20;     // Horní mez hodnot zobrazovaných v kombu
  public static final int MINIMUM = 5;        // Ménì tlaèítek nemá smysl
  private boolean stiskOK = false;            // Uzavžel uživatel okno stiskem OK?
  Object[] volby = null;                      // Pole voleb
  Object[] rozmery = null;                    // Pole hodnot pro kombo
  JButton ok = new JButton();                 // Tlaèítka
  JButton storno = new JButton();
  JTextField obrazek1 = new JTextField();     // Editaèní pole
  JTextField obrazek2 = new JTextField();
  JComboBox kombo = null;                     // Kombo (kombinovaný seznam)
  JLabel canc1 = new JLabel();                // Nápisy u komponent
  JLabel canc2 = new JLabel();

  public OknoVoleb(Frame vlastnik, Object[] vol) // Konstruktor
	{
		super(vlastnik, "Volby", true);
                volby = vol;
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                    try {
                     jbInit();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
	}

  public OknoVoleb() {      // Konstruktor (nepoužívá se, pozùstatek prvního nápadu)
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public boolean getStiskOK(){return stiskOK;}  // Jak bylo okno uzavøeno

  private void jbInit() throws Exception {    // Inicializace komponent
    this.getContentPane().setLayout(null);
    this.setResizable(false);
    //this.setModal(true);
    this.setSize(new Dimension(448, 300));
    ok.setText("OK");
    storno.setText("Storno");
    ok.setBounds(new Rectangle(30, 230, 110, 30));
    ok.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ok_actionPerformed(e);
      }
    });
    storno.setBounds(new Rectangle(145, 230, 110, 30));
    storno.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        storno_actionPerformed(e);
      }
    });
    obrazek1.setBounds(new Rectangle(30, 35, 353, 30));
    obrazek1.setText((String)volby[0]);
    obrazek2.setText((String)volby[1]);
    obrazek2.setBounds(new Rectangle(30, 70, 353, 30));

    rozmery = new Object[HORNI_MEZ-DOLNI_MEZ+1];  // Pøíprava pole zobrazovaných hodnot pro kombo
    for(int i = DOLNI_MEZ; i <= HORNI_MEZ; i++){
      rozmery[i-DOLNI_MEZ] = new Integer(i);
    }

    kombo = new JComboBox(rozmery);
    kombo.setBounds(new Rectangle(30, 160, 87, 26));
    kombo.setEditable(true);

    kombo.setSelectedIndex(rozmery.length-1);

    canc1.setText("Soubor obsahující znak pro 1., resp. 2. hráèe:");
    canc1.setBounds(new Rectangle(30, 10, 354, 18));
    canc2.setText("Poèet øad a sloupcù hracích polí:");
    canc2.setBounds(new Rectangle(32, 126, 306, 17));
    this.getContentPane().add(ok, null);
    this.getContentPane().add(storno, null);
    this.getContentPane().add(obrazek1, null);
    this.getContentPane().add(obrazek2, null);
    this.getContentPane().add(kombo, null);
    this.getContentPane().add(canc1, null);
    this.getContentPane().add(canc2, null);
  }

  void storno_actionPerformed(ActionEvent e) {  // Odezva na stisk Storno
    stiskOK = false;                            // Nastav pøíznak
    obrazek1.setText((String)volby[0]);         // Obnov pùvodní volby
    obrazek2.setText((String)volby[1]);
    kombo.setSelectedIndex(rozmery.length-1);
    this.setVisible(false);      // Skryj okno
  }

  void ok_actionPerformed(ActionEvent e) {// Odezva na stisk OK
    volby[0] = obrazek1.getText();    // Pøenes volby
    volby[1] = obrazek2.getText();
    int n = Integer.parseInt(kombo.getSelectedItem().toString());
    n = Math.max(MINIMUM, n);// Poèet políèek musí být v rozmezí MINIMUM, HORNI_MEZ
    volby[2] = new Integer(Math.min(HORNI_MEZ, n));
    stiskOK = true;// Nastav pøíznak
    this.setVisible(false);// Skryj okno
  }
}