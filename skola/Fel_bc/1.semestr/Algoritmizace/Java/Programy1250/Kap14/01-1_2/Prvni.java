/* Soubor Kap14\01-1_2\Prvni.java
 * Vytvoøí okno a zobrazí ho
 * 
 * Ukonèení programu pøi uzavøení hlavního okna
 * V komentáøi je øešení spoleèné pro JDK 1.2 a novìjší
 * za ním následuje øešení, které lze použít jen v JDK 1.3 a novìjších
 *
 * Øešení v JDK 1.2 je založeno na  
 * anonymní instanci potomka tøídy WindowAdapter, 
 * která se zaregistruje jako
 * pøíjemce okenních událostí a pøi uzavøení okna zavolá System.exit().
 *
 * Øešení v JDK 1.3 spoèívá ve volání metody okna
 * setDefaultCloseOperation(okno.EXIT_ON_CLOSE)
 */
	
public class Prvni
{
	public static void main(String[] a)
	{
		vokno.Vokno okno = new vokno.Vokno();
		okno.setVisible(true);
		// Øešení pro JDK 1.2
		/*
		okno.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		*/
		okno.setDefaultCloseOperation(okno.EXIT_ON_CLOSE);
	}
}


