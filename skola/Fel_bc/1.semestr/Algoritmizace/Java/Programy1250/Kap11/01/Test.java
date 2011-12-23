/* Soubor Kap11\01\Test.java
 * testovací program k pøíkladu na rozhraní
 * vytvoøí kontejner, uloí do nìj nìkolik grafickıch objektù
 * a jednu instanci tøídy retezec
 * Všechny tøídy implementují rozkraní kontrola.Kontrola,
 * které obsahuje metodu zkontroluj().
 * Metoda kontrol tøídy Test projde kontejner,
 * odkaz na kadı z objektù pøetypuje na odkaz na rozhraní Kontrola a zavolá
 * metodu kontrola. Tak zkontroluje všechny objekty bez ohledu na to, zda jde o
 * øetìzce nebo grafické objekty. Vısledek kontrolu kontejnery vypíše.
 * Vhodné pro všechny verze JDK, nebo nevyuívá parametrizace kontejneru 
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

  public void beh() // Naplní kontejner nìjakımi objekty
  {
    GO g = new Bod(5,6,11);
    seznam.add(g);
    g = new Usecka(1,2,3,4,5);
    seznam.add(g);
    g = new Kruznice(8, 9, 10, 98);
    seznam.add(g);
    Retezec s = new Retezec();
    s.pridej("Nejaky Text");
    System.out.println(kontrol(seznam)?"OK":"Prusvih");
  }


  public static void main(String[] args) {
    Test t = new Test();
    t.beh();
  }
}