/* Soubor Kap06\01\Konvertor.java
 * Tøída pro konverzi celého èísla na znakovı øetìzec 
 * umoòuje vytvoøit èíselnou reprezentaci v èíselné soustavì se základem 2 - 36
 * Èíslice jsou vyjádøeny znaky 0, 1, ... ,9, A, B, ... , Z
 *
 * Základ soustavy je parametrem konstruktoru, 
 * konvertované èíslo typu long je parametrem metody konverze.
 * Pouití - konverze èísla x do šestnáctkové soustavy:
 *
 * Konvertor k = new Konvertor(16);
 * string s = k.konverze(x)
 */

public class Konvertor {
  private long zaklad;  // Základ èíselné soustavy
  static String cislice = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Øetìzec obsahující všechny èíslice, které se mohou vyskytnout v soustavách o základu od 2 do 36
  public String konverze(long n){
    StringBuffer s = new StringBuffer(""); // Zde budeme vytváøet znakovou reprezentaci èísla
    boolean zaporne = false;
    if(n==0)
    {                   // Je-li to nula, vra øetìzec "0" (je stejnı ve všech soustavách)
      return "0";
    }
    if (n < 0)
    {
      zaporne = true;
      n = -n;
    };
    while(n != 0){
      s.append(cislice.charAt((int)(n%zaklad)));
      n /= zaklad;
    }
    if(zaporne)s.append('-');
    return new String(s.reverse());
  }

  public Konvertor(int baze) { // Parametrem je základ soustavy
	zaklad = baze;  
  }
  public static void main(String[] args) {
    //Konvertor k = new Konvertor(16);
    //System.out.println(k.konverze(-0xFFFFFFFFL));
    System.out.println((new Konvertor(2)).konverze(15));
  }
}