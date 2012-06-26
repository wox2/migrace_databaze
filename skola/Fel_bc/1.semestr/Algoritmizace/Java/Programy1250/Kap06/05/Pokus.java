/* Soubor Kao06\04\Pokus.java
 * Pøíklad na použití pole
 * Metoda pocetDnu() tøídy Kalendar vypoète poøadové èíslo dne v roce
 *
 * Tøída Test slouží k testování
 */

class Kalendar {
 int mesice[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // Poèty dnù v mìsící
 int dny[];		// Poèty dnù od poèátku roku na konci pøedchozího mìsíce
 Kalendar() {		// Konstruktor	
  dny = new int[12];	// Vytvoøí pole dny
  int i = 1;		
  dny[0] = 0;		// Vypoète hodnoty v nìm: Poèet dn¡u do poèátku ledna je 0, do poèátku února je 31 atd
  while(i < dny.length)
  {
    dny[i] = dny[i-1]+mesice[i-1];
    i++;
  }
 }

 boolean prestupny(int rok) 	// Zjistí, zda je rok pøestupný
 {				// Tj. buï dìlitelný 4 a ne 100  nebo dìlitelný 400
    if(((rok%4 == 0) && (rok % 100)!=0) || (rok % 400 == 0)) return true;
    else return false;
 }

 int cisloDne(int den, int mesic, int rok)	// Vypoète poøadové èíslo dne v roce
 {
    int d = dny[mesic-1]+den;			// Poèet dnù do poèátku mìsíce + èíslo dne v mìsíci (datum v mìsíci)
    if(prestupny(rok) && mesic>2) d++;		// Je=li rok pøestupný, nutno od 1.3. pøidat 1 za 29. únor
    return d;
 }
}

public class Pokus {

  public Pokus() {
  }
  public static void main(String[] args) {
    Pokus pokus1 = new Pokus();
    Kalendar kal = new Kalendar();
    System.out.println("" + kal.cisloDne(1,3,2001));
  }
}