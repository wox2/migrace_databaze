/* Soubor Kap04\04\Analyzer.java

   Pouze pro JDK 5

   Program, který vypíše všechna rùzná slova, která se vyskytují
   v textovém souboru, a poèet jejich opakování.
   Ète standardní vstup. Využívá parametrizovaný typ ArrayList<>.

   použití: java Analyzer < jménoSouboru

   Neumí èíst pøímo data z konzoly.
*/

// Tøída, která slouží k ukládání dvojic slovo - poèet výskytù
class Dvojice 
{
  private int pocet;    // poèet výskytù
  private String slovo; // øetìzec (slovo)
  public void pridej(){pocet++;}	// Zvìtši poèet výskytù
  public Dvojice(String s, int i){pocet = i; slovo = s;}        // Konstruktory
  public Dvojice(String s){pocet = 1; slovo = s;}
  public boolean equals(Object x){return slovo.equals(((Dvojice)x).slovo);}// Pøedefinovaná metoda pro provnávání dvojic - porovnává podle øetìzce
  public String toString(){return slovo + "(" + pocet + ")";}   // Pøedefinovaná metoda pro konverzi na øetìzec
}

// Hlavní tøída programu
public class Analyzer
{
  static String oddelovace = " .,;!?";         // seznam znakù, které mohou oddìlovat slova v textu

  
  String odstraòOddìlovaèe(String slo)         // slo: slovo, ze kterého chceme odstranit oddìlovaèe
  {                                            // Vrací: Slovo bez oddìlovaèù	
    StringBuffer s = new StringBuffer();
    int i = 0;
    while(i < slo.length() && oddelovace.indexOf(slo.charAt(i)) == -1)
    {
	s.append(slo.charAt(i));
        i++;
    }
    return s.toString();
  }

  // Pøidání nového slova do seznamu 
  void pridej(String slovo, java.util.ArrayList<Dvojice> sezn) 
  {						     // nebo zvýšení poètu výskytù	
	Dvojice d = new Dvojice(slovo);			// Vytvoø dvojici
	int i = sezn.indexOf(d);			// Zkus ji najít v seznamu
	if(i<0) 					// Když tam není
	{sezn.add(d);}					// tak ji pøidej
	else						// jinak
	{
	 d = sezn.get(i);			        // si ji vyndej a 
	 d.pridej();					// zvìtši poèet výskytù
	}
  }
 

  void beh()		// Vlastní bìh programu
  {
        java.util.Scanner skan=new java.util.Scanner(System.in);
	String slovo;
	java.util.ArrayList<Dvojice> sezn = new java.util.ArrayList<Dvojice>();

	while(skan.hasNext())		// Dokud je co èíst
        {
          slovo = skan.next();		// Odstraò okrajové mezery
          pridej(odstraòOddìlovaèe(slovo), sezn);
        }
        System.out.println(sezn);        // Vypiš výsledek
  }

  public static void main(String[] s)
  { // Vytvoøí instanci tøídy Analyzer a spustí pro ni metodu beh()
      Analyzer a = new Analyzer();
      a.beh();
  }
}