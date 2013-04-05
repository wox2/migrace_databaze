/* Soubor Kap15\01\Aplet1.java
 * Jednoduchý aplet, který podle stisknutého pøepínaèe
 * zobrazuje buï citát nebo jeho autora.
 * Oba texty ète z parametrù apletu v HTML stránce.
 * Pokud se parametry nepodaøí naèíst, zobrazí implicitní text
 * "Chaos vládne i bez ministrù." a jako autora uvede
 * "Bobbyho pøesvìdèení".
 * Citát v parametrech apletu pochází novoroèního pøípitku Miloše Formana 
 * (ÈT, silvestr 2000), 
 * citát použitý v apletu jako implicitní je z knihy
 * Artura Blocha "Murphyho zákon", vydané nakladatelstvím Svoboda - Libertas
 * v Praze r. 1993.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Aplet1 extends JApplet {
  String citat = "Chaos vládne i bez ministrù.";
  String autor = "Bobbyho pøesvìdèení";
  JLabel napis = new JLabel();
  JPanel pomocny = new JPanel();
  ButtonGroup bg = new ButtonGroup();
  JRadioButton zobrazCitat = new JRadioButton();
  JRadioButton zobrazAutora = new JRadioButton();

  // Pøeète hodnotu parametru, a pokud neuspìje, vrátí implicitní hodnotu
  public String getParameter(String key, String def) {
      return (getParameter(key) != null ? getParameter(key) : def);
  }

  public Aplet1() {  // Konstruktor
  }

  public void init() { // Inicializace
    try {
      String _citat = getParameter("VYROK", citat);
      String _autor =  getParameter("AUTOR", autor);
      citat = _citat;
      autor = _autor;
    }
    catch(Exception e) {}
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {   // Inicializace komponent
    this.setSize(new Dimension(400,300));
    JPanel panel = (JPanel)getContentPane();

    napis.setText(citat);
    napis.setFont(new java.awt.Font("Serif", Font.ITALIC | Font.BOLD, 32));
    napis.setForeground(Color.red);
    napis.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(napis, BorderLayout.CENTER);

    panel.add(pomocny, BorderLayout.SOUTH); 

    bg.add(zobrazCitat);		// Pøidej pøepínaèe do skupiny
    bg.add(zobrazAutora);
    pomocny.add(zobrazCitat);		// a vlož je na spodní panel
    pomocny.add(zobrazAutora);
    zobrazCitat.setText("Citát");
    zobrazCitat.setSelected(true);
    zobrazAutora.setText("Autor");
    ActionListener prijemce = new ActionListener() {// Instance pøíjemce události
        public void actionPerformed(ActionEvent e) {
            stiskPrepinace(e);
        };
    };
    zobrazCitat.addActionListener(prijemce);
    zobrazAutora.addActionListener(prijemce);
  }

  public String getAppletInfo() {// Informace o apletu
    return "Mùj první aplet";
  }

  protected void stiskPrepinace(ActionEvent e) { // Handler pro stisknutí
    if(e.getSource().equals(zobrazCitat))     // pøepínaèe citát - autor
      napis.setText(citat);
    else
      napis.setText(autor);
  }
}