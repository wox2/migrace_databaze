/*
   Soubor Kap4\07\Analyzer.java
   Opìt poèítání slov v textovém souboru,
   ovšem tentokrát s pomocí vlastní implementace 
   jednosmìrnì zøetìzeného seznamu založeného 
   na parametrizovaných tøídách.
   Použití stejné jako v pøedchozích pøípadech.
   Vhodné pro JDK 5 a vyšší
*/

/** Prvek seznamu jako parametrizovaná tøída*/
class Prvek<T>
{
  T data = null;            // Slovo uložené v prvku
  int pocet = 0;             // poèet výskytù
  Prvek<T> dalsi = null;        // Odkaz na další prvek seznamu
  Prvek(){}                  // Konstruktory
  Prvek(T s) {data = s; pocet = 1;}
  int obsahuje(T s) {return s.equals(data) ? pocet : 0;}
  void setDalsi(Prvek<T> q){dalsi = q;}
  Prvek<T> getDalsi(){return dalsi;}
  T getSlovo(){return data;}
  void zvetsiPocet(){pocet++;}
  int getPocet(){return pocet;}
}

/** Vlastní implementace seznamu jako parametrizované tøídy */
class Seznam<T>
{
  Prvek<T> hlava = null;
  void pridej(T slovo)
  {
    if(hlava == null)
    {
      hlava = new Prvek<T>(slovo);
    }
    else
    {
       Prvek<T> p = hlava, q = null;
       while(p != null)
       {
          if(p.obsahuje(slovo) > 0)
          {
            p.zvetsiPocet();
            return;
          }
          else
          {
            q = p;
            p = p.getDalsi();
          } // Konec if
       }    // konec while
       q.setDalsi(new Prvek<T>(slovo));
    }
  }

  void vypis()
  {
     Prvek<T> p = hlava;
     while(p != null)
     {
       System.out.println(p.getSlovo()+ " " + p.getPocet());
       p = p.getDalsi();
     }
  }
  
  void vyprazdni()
  {
	hlava = null;
  }

}

public class Analyzer
{
  static String oddelovace = " .,;!?";

  int preskocOddelovace(String rad, int od) // rad: analyzovaný øádek, od: index, od kterého se má zaèít
  {					    // Vrací: index, kde zaèíná následující slovo, nebo -1, jsme-li na konci øádku	
    if(od >= rad.length()) return -1;       // Jsme-li na konci øádku, vra -1
    while(oddelovace.indexOf(rad.charAt(od))>=0)
    {
     if(++od == rad.length()) return -1;
    }
    return od;
  }



  void analyzuj(String radek, Seznam<String> sezn) // radek: analyzovaný øádek, sezn: Kontejner, do kterho se majíé slova ukládat
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
      sezn.pridej(s);
      i = preskocOddelovace(radek, i);
    }
  }


  void beh() throws Exception		// Vlastní bìh programu
  {
    	Seznam<String> slovnik = new Seznam<String>();
        String radek = MojeIO.inStr();	// Pøeèti 1. øádek

        while(radek != null)		// Dokud se ètení daøí
        {
          radek = radek.trim();		// Odstraò okrajové mezery
          analyzuj(radek, slovnik);
	  radek = MojeIO.inStr();	// Èti další øádek
        }
        slovnik.vypis();    // Vypiš výsledek

  }

  public static void main(String[] s) throws Exception
  { // Vytvoøí instanci tøídy Analyzer a spustí pro ni metodu beh()
      Analyzer a = new Analyzer();
      a.beh();
  }
}