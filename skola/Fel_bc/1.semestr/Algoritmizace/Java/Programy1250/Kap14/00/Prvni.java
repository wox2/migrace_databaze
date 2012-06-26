// Soubor Kap14\00\Prvni.java
// Deklaruje tøídu okna, která je 
// potomken JFrame, vytvoøí instanci a zobrazí ji.
// Program vytvoøí minimální okno
// a po uzavøení okna neskonèí, program nutno
// ukonèit v konzolovém oknì klávesovou
// kombinací Ctrl+C


import javax.swing.*;
class Vokno extends JFrame
{
	Vokno(){}		// Volá implicitní konstruktor pøedka, nic víc
}

public class Prvni
{
	public static void main(String[] a)
	{
		Vokno okno = new Vokno();	// Vytvoø okno
		okno.setVisible(true);						// a zobraz ho
	}
}
