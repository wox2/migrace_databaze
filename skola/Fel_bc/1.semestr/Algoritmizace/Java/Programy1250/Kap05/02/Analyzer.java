/* Soubor Kap05\02\Analyzer.java 
   Vznikl úpravou souboru Kap04\03\Analyzer.java

   Jediný rozdíl: použití pøíkazu import umožní nepsat
   celé jméno java.util.ArrayList, ale jen ArrayList

   Program, který vypíše všechna rùzná slova, která se vyskytují
   v textovém souboru, a poèet jejich opakování.
   Ète standardní vstup. Využívá MojeIO, tzn. MojeIO.class musí být v nìkterém z adresáøù
   urèených systémovou promìnnou CLASSPATH.

   použití: java Analyzer < jménoSouboru

   Neumí èíst pøímo data z konzole.
*/

import java.util.ArrayList;

// Tøída, která slouží k ukládání dvojic slovo - poèet výskytù
class Dvojice 
{
  private int pocet;	// poèet výskytù
  private String slovo; // øetìzec (slovo)
  public void pridej(){pocet++;}	// Zvìtši poèet výskytù
  public Dvojice(String s, int i){pocet = i; slovo = s;}	// Konstruktory
  public Dvojice(String s){pocet = 1; slovo = s;}
  public boolean equals(Object x){return slovo.equals(((Dvojice)x).slovo);}// Pøedefinovaná metoda pro provnávání dvojic - porovnává podle øetìzce
  public String toString(){return slovo + "(" + pocet + ")";}	// Pøedefinovaná metoda pro konverzi na øetìzec
}

// Hlavní tøída programu
public class Analyzer
{
  static String oddelovace = " .,;!?";		// seznam znakù, které mohou oddìlovat slova v textu

  
  int preskocOddelovace(String rad, int od) // rad: analyzovaný øádek, od: index, od kterého se má zaèít
  {					    // Vrací: index, kde zaèíná následující slovo, nebo -1, jsme-li na konci øádku	
    if(od >= rad.length()) return -1;       // Jsme-li na konci øádku, vra -1
    while(oddelovace.indexOf(rad.charAt(od))>=0)
    {
     if(++od == rad.length()) return -1;
    }
    return od;
  }

  void pridej(String slovo, ArrayList sezn) // Pøidání nového slova do seznamu 
  {						     // nebo zvýšení poètu výskytù	
	Dvojice d = new Dvojice(slovo);			// Vytvoø dvojici
	int i = sezn.indexOf(d);			// Zkus jin najít v seznamu
	if(i<0) 					// Když tam není
	{sezn.add(d);}					// tak ji pøidej
	else						// jinak
	{
	 d = (Dvojice)sezn.get(i);			// si ji vyndej a 
	 d.pridej();					// zvìtši poèet výskytù
	}
  }
  void analyzuj(String radek, ArrayList sezn) // radek: analyzovaný øádek, sezn: Kontejner, do kterho se majíé slova ukládat
  {
    if(radek.equals(""))return;		// Prázdný øádek nás nezajímá
    int i = 0;				// Index znaku v øádku
    StringBuffer slovo = null;		// Sem uložíme získané slovo
    i = preskocOddelovace(radek, i);	// Najdi zaèátek dalšího slova (-1 == konec øádku)
    while(i >= 0)
    {
      slovo = new StringBuffer("");
      // Pøenes následující slovo do instance "slovo"
      while((i < radek.length()) && (oddelovace.indexOf(radek.charAt(i))==-1)) // Dokud to není oddìlovaè
      {
        slovo.append(radek.charAt(i++));
      }
      // Ulož slovo do slovníku
      String s = new String(slovo);
      pridej(s, sezn);
      i = preskocOddelovace(radek, i);
    }
  }

  void beh() throws Exception		// Vlastní bìh programu
  {
  	ArrayList slovnik = new ArrayList();
        String radek = inStr();	// Pøeèti 1. øádek

        while(radek != null)		// Dokud se ètení daøí
        {
          radek = radek.trim();		// Odstraò okrajové mezery
          analyzuj(radek, slovnik);
	  radek = MojeIO.inStr();	// Èti další øádek
        }
        System.out.println(slovnik);    // Vypiš výsledek
  }

  public static void main(String[] s) throws Exception
  { // Vytvoøí instanci tøídy Analyzer a spustí pro ni metodu beh()
      Analyzer a = new Analyzer();
      a.beh();
  }
}