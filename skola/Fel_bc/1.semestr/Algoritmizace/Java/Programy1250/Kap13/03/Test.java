/* Soubor Kap13\03\Test.java
 * testovací program k pøíkladu na serializaci
 * podobný jako v kap11\02, navíc implementuje serializaci
 ---------------------------------------------------------
 * vytvoøí kontejner, uloží do nìj nìkolik grafických objektù
 * a jednu instanci tøídy retezec
 * Všechny tøídy implementují rozkraní kontrola.Kontrola,
 * které obsahuje metodu zkontroluj(), rozhraní Cloneable, které umožòuje
 * klonování, a rozhraní Serializable, které umožòuje serializaci.
 *
 */

import grafika.*;
import kontrola.*;
import java.util.*;
import java.io.*;


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

  public void uloz(String soubor, Object co) throws IOException
  {
    File f = new File(soubor);
    if(!f.exists()) f.createNewFile();

    FileOutputStream fos = new FileOutputStream(f);
    ObjectOutputStream oos = new ObjectOutputStream(fos);

    oos.writeObject(co);
    oos.close();
  }

  public Object nacti(String soubor) throws IOException, ClassNotFoundException
  {
    File f = new File(soubor);
    if(!f.exists()) throw new IOException("soubor "+ soubor + " neexistuje");

    FileInputStream fis = new FileInputStream(f);
    ObjectInputStream ois = new ObjectInputStream(fis);

    return ois.readObject();
  }
  public void beh() // Naplní kontejner nìjakými objekty
  {
    GO g = new Bod(5,6,11);
    seznam.add(g);
    g = new Usecka(1,2,3,4,5);
    seznam.add(g);
    g = new Kruznice(8, 9, 10, 98);
    seznam.add(g);

    try {
     uloz("data.dta", seznam);
     System.out.println(seznam);
     seznam = null;
     seznam = (ArrayList) nacti("data.dta");
     System.out.println(seznam);
    }
    catch(Exception e)
    {
      System.out.println(e);
      System.out.println("Neco se nepovedlo");
    }
  }



  public static void main(String[] args) {
    Test t = new Test();
    t.beh();
  }
}