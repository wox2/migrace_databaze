/**
  * Priklad praktickeho pouziti kontejneru v Jave
  *
  * Program pracuje jako velice primitivni telefonni seznam.
  * Vsechno je jen v pameti, nic se nenacita ze souboru,
  * ani se nic nikam neuklada.
  *
  * Ovladani je z klavesnice pomoci ciselnych prikazu
  * a naslednych odpovedi na dotazy programu.
  *  
  */


// importujeme cele baliky - pouzije se vice trid/rozhrani a nehrozi kolize
import java.util.*;
import java.io.*;

/**
  * Trida obsahuje implementaci jednoducheho telefonniho seznamu
  */
public class PhoneBook {

  /**
    * Kontejner pro ulozeni dat
    */
  private Map book = new HashMap();
  
  /**
    * Vstupni textovy stream umoznujici radkovy vstup z klavesnice
    */
  private BufferedReader rdr = null;
  
  /**
    * Konstruktor bez parametru
    */
  public PhoneBook() {
    
    // uvitaci zprava
    System.out.println("Telefonni seznam - priklad pouziti kontejneru");
    
    // vytvoreni streamu pro radkovy vstup - bude vysvetleno nekdy pozdeji
    rdr = new BufferedReader(new InputStreamReader(System.in));
  }
  
  /**
    * Vypise seznam platnych prikazu
    */
  private void printCmds() {
    System.out.println();
    System.out.println("1 - vlozit cislo");
    System.out.println("2 - smazat cislo");
    System.out.println("3 - hledat podle celeho jmena");
    System.out.println("4 - hledat podle pocatecni casti jmena (NEEFEKTIVNI)");
    System.out.println("5 - hledat podle cisla (NEEFEKTIVNI)");
    System.out.println("6 - vypsat vsechno");
    System.out.println("0 - konec");
  }
  
  /**
    * Precte radek ze standardniho vstupu (klavesnice)
    *
    * @return precteny retezec, pri chybe null
    */
  private String readLine() {
    try {
      return rdr.readLine(); // metoda pro cteni radku vyhazuje IOException
    } catch (IOException e) {
      System.err.println("Fatalni chyba - standardni vstup nefunguje!!!");
      return null;
    }
  }
  
  /**
    * Ziska od uzivatele cislo prikazu
    *
    * @return cislo prikazu, pri chybe 0 (chyba vstupu) nebo -1 (neplatny prikaz)
    */
  private int getCmd() {
    try {
      String s = readLine();            // precte se radek
      if (s == null) return 0;          // pokud nastala chyba, ukonci se program
      else return Integer.parseInt(s);  // pokud jde ziskat cislo, vrati se
    } catch (NumberFormatException e) {
      System.out.println("Toto neni platny prikaz"); // neplatny format cisla
      return -1;
    }
  }
  
  /**
    * Prida polozku do seznamu
    */
  private void addItem() {
    System.out.print("Jmeno: ");
    String name = readLine();
    if (name == null) return;          // pokud dojde k chybe vstupu, nepokracuje se
    System.out.print("Tel. cislo: ");
    String num = readLine();
    if (num == null) return;
    book.put(name, num);
    System.out.println("Cislo uspesne vlozeno");
  }
  
  /**
    * Odebere polozku ze seznamu
    */
  private void removeItem() {
    System.out.print("Jmeno (presne): ");
    String name = readLine();
    if (name == null) return;
    
    // pokud se odebrani podarilo, metoda vrati klic, jinak vraci null
    if (book.remove(name) != null) {
      System.out.println("Polozka byla odebrana");
    }
    else {
      System.out.println("Jmeno nebylo nalezeno");
    }
  }
  
  /**
    * Hleda cislo podle celeho jmena
    */
  private void searchByName() {
    System.out.print("Jmeno (presne): ");
    String name = readLine();
    if (name == null) return;
    String num = (String) book.get(name); // rychle vyhledani podle klice
    if (num != null) {
      System.out.println("Cislo je: " + num);
    }
    else {
      System.out.println("Jmeno nebylo nalezeno");
    }
  }
  
  /**
    * Hleda cislo podle casti jmena
    */
  private void searchByNamePart() {
    System.out.print("Jmeno (aspon zacatek): ");
    String name = readLine();
    if (name == null) return;
    Iterator it = book.keySet().iterator(); // ziskame iterator klicu
    boolean found = false;
    while (it.hasNext()) {
      String n = (String) it.next();
      if (n.startsWith(name)) {  // pokud klic (jmeno) zacina danym retezcem, vypise se cislo
         found = true;
        System.out.println("Nalezeno jmeno: " + n + ", cislo je: " + book.get(n));
      }
    }
    if (!found) System.out.println("Zadne jmeno nebylo nalezeno");
  }
  
  /**
    * Hleda jmeno podle cisla
    */
  private void searchByNumber() {
    System.out.print("Tel. cislo (presne): ");
    String num = readLine();
    if (num == null) return;
    Iterator it = book.keySet().iterator();
    boolean found = false;
    while (it.hasNext()) {
      String name = (String) it.next();
      String n = (String) book.get(name);
      if (n.equals(num)) {
        found = true;
        System.out.println("Nalezeno jmeno: " + name);
      }
    }
    if (!found) System.out.println("Cislo nebylo nalezeno");
  }
  
  /**
    * Vypise cely seznam
    */
  private void listAll() {
    System.out.println();
    System.out.println("Vypis vsech cisel");
    Iterator it = book.keySet().iterator();
    while (it.hasNext()) {
      String name = (String) it.next();
      System.out.println(name + " ... " + book.get(name));
    }
  }

  /**
    * Vykonna cast programu (objektu)
    */
  public void run() {
    int cmd = -1;
    while (cmd != 0) {  // prikaz 0 ukoncuje program
      printCmds();
      cmd = getCmd();
      switch (cmd) {
        case 1: addItem();
	  break;
	case 2: removeItem();
	  break;
	case 3: searchByName();
	  break;
	case 4: searchByNamePart();
	  break;
	case 5: searchByNumber();
	  break;
	case 6: listAll();
	  break;
	default:
      }
    }
  }

  /**
    * Telo programu
    */
  public static void main(String args[]) {
  
    // velmi jednoduche (ani by nebylo nutne vytvaret promennou)...
    PhoneBook pb = new PhoneBook();
    pb.run();
  }
}

