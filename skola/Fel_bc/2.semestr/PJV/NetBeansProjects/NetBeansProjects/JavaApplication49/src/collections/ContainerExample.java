/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

/**
 *
 * @author já
 */
import java.util.Vector;
import java.util.Iterator;
public class ContainerExample {



/**
  * Vitejte v prvnim ukazkovem zdrojovem souboru k serialu o jazyku Java.
  * Podobne budou vypadat i soubory v nasledujicich dilech. Nejprve
  * nekolik dulezitych informaci:
  *
  * 1. Ve zdrojovych souborem nebudu pouzivat diakritiku.
  * 2. Veskere komentare v kodu (vcetne tohoto) budou ve formatu
  *    pro Javadoc, dokumentace vygenerovana ze souboru by vypadala
  *    jako standardni dokumentace k Jave (o nastroji Javadoc a tvorbe
  *    dokumentace k programum bude rec pozdeji - ted to uvadim jen
  *    pro informaci).
  * 3. Vsechny ukazkove soubory by mely jit zkompilovat a spustit
  *    prakticky na libovolne platforme s verzi JDK aspon 1.4.2.
  *    Soubor se zkompiluje pomoci "javac NazevSouboru" (tj. pro
  *    tento soubor to bude "javac ContainerExample.java"), spustit
  *    pak "java NazevTridy" (tento soubor: "java ContainerExample").
  * 4. Muze se stat, ze neco nebude fungovat - nikdo neni neomylny.
  *    V takovem pripade prosim o zachovani chladne hlavy a nasledne
  *    klidne ohlaseni chyby. Postaram se o co nejrychlejsi opravu.
  *
  *    Lukas Jelinek
  *
  */


// import nutnych objektu


/**
  * Trida obsahuje dva jednoduche priklady na praci s kontejnerem Vector.
  *
  */


  /**
    * Vektor jako promenna objektu - hned ho inicializujeme.
    */
  private Vector vector = new Vector();

  /**
    * Konstruktor - v tomto pripade prazdny
    */
  public ContainerExample() {

  }

  /**
    * Staticka metoda - provadi kod jednoducheho prikladu
    */
  public static void showSimpleExample() {
    Vector v = new Vector(); // vytvori instanci s vychozimi parametry

    v.add("abcd");           // vlozi textovy retezec
    v.add(new Integer(6));   // vlozi cele číslo
    v.add(1, "efgh");        // vlozi textovy retezec na pozici 1

    System.out.println("Na pozici 1 je: " + v.elementAt(1));

    System.out.println("Obsah vektoru:");
    Iterator it = v.iterator();
    while (it.hasNext()) {            // opakuj, dokud jsou polozky
      System.out.println(it.next());  // tisk dalsi polozky
    }

    v.clear();             // smazani obsahu

    System.out.println();  // odradkovani (pro prehlednost)
  }

  /**
    * Naplni vektor daty
    */
  public void fillVector() {
    for (int i=0; i<6; i++) {
      vector.add("Polozka [" + i + "]");
    }
  }

  /**
    * Zmeni data ve vektoru
    */
  public void changeVector() {
    vector.remove(2);   // odstrani prvek s indexem 2 - nasledujici prvky se posunou dolu
    vector.setSize(7);  // nastavi novou velikost - neobsazene pozice jsou nastaveny na "null"
    vector.set(6, "nova polozka");  // na pozici 6 vlozime novou polozku

    // po provedenych operacich by vektor mel mit 7 prvku, na pozici 5 by mel by "null"
  }

  /**
    * Vytiskne pocet prvku a obsah vektoru
    */
  public void printVector() {

    // zjistime pocet prvku
    int cnt = vector.size();
    System.out.println("Vektor obsahuje " + cnt + " prvku");

    // sekvencni pristup bez pouziti iteratoru
    for (int i=0; i<cnt; i++) {
      System.out.println(vector.elementAt(i)); // tisk obsahu prvku
    }

    System.out.println(); // odradkovani (pro prehlednost)
  }

  public static void createExample(){
     // jednoduchy priklad z clanku
    ContainerExample.showSimpleExample();

    // slozitejsi priklad - naplneni, tisk, provedeni zmen, opet tisk
    ContainerExample ce = new ContainerExample();  // vytvorime objekt
    ce.fillVector();    // naplnime vektor
    ce.printVector();   // vytiskneme obsah
    ce.changeVector();  // provedeme zmeny
    ce.printVector();   // opet vytiskneme obsah
  }

  


}