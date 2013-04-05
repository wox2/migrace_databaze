/* Soubor Kap04\02\Analyzer.java
   Program je témìø stejný jako Kap04\01\Analyzer.java, 
   pouze do kontejneru ukládá 
   pøímo instance tøídy StringBuffer, a proto nefunguje 
   správnì (nepozná stejná slova).

   použití: java Analyzer < jménoSouboru

   Neumí èíst pøímo data z konzoly.
   Opìt použitelné ve všech verzích JDK poèínaje 1.2
*/

// Hlavní a jediná tøída programu
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

  void analyzuj(String radek, java.util.ArrayList sezn) // radek: analyzovaný øádek, sezn: Kontejner, do kterho se majíé slova ukládat
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
      // Ulož slovo do slovníku ******** zde je zmìna oproti Kap04\01\Analyzer.java
      if(sezn.indexOf(slovo)==-1)sezn.add(slovo);
      i = preskocOddelovace(radek, i);
    }
  }

  void beh() throws Exception		// Vlastní bìh programu
  {
  	java.util.ArrayList slovnik = new java.util.ArrayList();
        String radek = MojeIO.inStr();	// Pøeèti 1. øádek

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