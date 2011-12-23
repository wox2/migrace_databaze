/* Soubor Kap11\02\Test.java
 * testovací program k pøíkladu na rozhraní
 * podobný jako v kap11\01, navíc implementuje klonování
 ---------------------------------------------------------
 * vytvoøí kontejner, uloží do nìj nìkolik grafických objektù
 * a jednu instanci tøídy retezec
 * Všechny tøídy implementují rozkraní kontrola.Kontrola,
 * které obsahuje metodu zkontroluj().
 * Metoda kontrol tøídy Test projde kontejner,
 * odkaz na každý z objektù pøetypuje na odkaz na rozhraní Kontrola a zavolá
 * metodu kontrola. Tak zkontroluje všechny objekty bez ohledu na to, zda jde o
 * øetìzce nebo grafické objekty. Výsledek kontrolu kontejnery vypíše.
 */

import grafika.*;
import kontrola.*;
import retezec.*;
import java.util.*;


public class Test {
  ArrayList seznam = new ArrayList();

  public Test() { }


  public boolean kontrol(ArrayList s){		// Zkontroluj obsah kontejneru
    try{					// pomocí metody kontrola() z
      for(Iterator i = s.iterator(); i.hasNext();) // rozhraní Kontrola
      {
        Kontrola k = (Kontrola)i.next();
        System.out.println(k);
	if(!(k.zkontroluj())) return false;
      }
      return true;
    }
    catch(Exception e){
     e.printStackTrace();
     return false;
    }
  }

  public void beh() // Naplní kontejner nìjakými objekty
  {
    GO g = new Bod(5,6,11);
    seznam.add(g);
    g = new Usecka(1,2,3,4,5);
    seznam.add(g);
    g = new Kruznice(8, 9, 10, 98);
    seznam.add(g);
    Retezec s = new Retezec();
    s.pridej("Nejaky Text");
    seznam.add(s);

    System.out.println(seznam);	
    ArrayList seznam1 = new ArrayList();	// Vytvoø nový seznam
    for(Iterator i = seznam.iterator(); i.hasNext();)// a naplò ho kopiemi
    {						// objektù z pøedchozího seznamu:
      try {
        g = (GO)i.next();			// Vezmi další objekt
        g = (GO)g.clone();			// Klonuj ho
        g.setBarva(123);			// Zmìò barvu kopie
        seznam1.add(g);				// Ulož ho do nového seznamu
      }
      catch(Exception e)			// Když se nìco nepodaøí, nic se nedìje
      {}
    }
    System.out.println(seznam1);		// Vypiš výsledek
  }



  public static void main(String[] args) {
    Test t = new Test();
    t.beh();
  }
}