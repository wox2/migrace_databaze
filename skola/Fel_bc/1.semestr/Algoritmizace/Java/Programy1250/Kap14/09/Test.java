/* Soubor Kap14\09\Test.java
 * Testuje, jak je to se souøadnicemi v oknì
 * Zobrazí okno, ve kterém jsou tøi tlaèítka
 * a textové pole.
 * Okno má správce rozložení null.
 * Stisknutí níže položeného tlaèítka zpùsobí 
 * zmenšení hodnot promìnných x a y,
 * pøesunutí levého horního rohu druhého tlaèítka do bodu (x, y)
 * a výpis tìchto souøadnic v textovém poli.
 * Tøetí tlaèítko funguje podobnì, pouze zvìtšuje x a y
 * Neodpovídá žádnému pøíkladu z textu knihy
 */

import javax.swing.*;

public class Test {
	Test()
	{
		Okno okno = new Okno();
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
	}
	public static void main(String[] s)
	{
		new Test();
	}
}