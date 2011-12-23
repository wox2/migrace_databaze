/* Soubor Kap14\08\Piskorky.java
 * Hlavní tøída programu Piškorky
 * Okno tohoto programu slouží jako hrací plocha, složená z políèek, na které se 
 * kliknutím myší zaznamenávají koleèka nebo køížky. Hráèi se v tazích støídají, 
 * cílem je vytvoøit "piškorku" - pìt stejných symbolù za sebou v øadì, 
 * sloupci nebo na diagonále.
 *
 * Hlavní program pouze vytvoøí okno a umístí ho doprostøed obrazovky, mic víc.
 */

import javax.swing.UIManager;
import java.awt.*;
import vokno.Vokno;

public class Piskorky
{
	public Piskorky()
	{
		Vokno okno = new Vokno();

		Dimension obr = Toolkit.getDefaultToolkit().getScreenSize();
		okno.setSize(new Dimension(obr.width/2, obr.height/2+50));
		okno.setLocation(obr.width/4, obr.height/4);

		okno.setVisible(true);
	}

	public static void main(String[] a)
	{	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Piskorky p = new Piskorky();
	}
}