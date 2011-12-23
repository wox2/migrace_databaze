/* Soubor Kap14\03\Prvni.java
 * Prázdné okno s nastaveným vzhledem a s inicializací
 * okna v konstruktoru aplikace, nikoli v konstruktoru okna
 */

import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import vokno.*;

public class Prvni {

  public Prvni() {		// Konstruktor aplikace
    Vokno vokno = new Vokno();
    Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension ro = new Dimension();
    ro.height = obrazovka.height/2;
    ro.width = obrazovka.width/2;
    vokno.setSize(ro);
    vokno.setLocation(ro.width/2, ro.height/2);
    vokno.setTitle("Naše první okno");
    vokno.setVisible(true);
    vokno.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e)
	{ System.exit(0); }
    });

  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new Prvni();
  }
}