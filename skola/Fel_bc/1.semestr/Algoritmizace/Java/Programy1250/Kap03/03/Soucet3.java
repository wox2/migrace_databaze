/* Sèítání dvou èísel - pøetí pokus
   pouitelnı pouze v JDK 5, nebo
   ve starších verzích chybí metoda printf
   soubor kap03\04\Soucet3.java
*/

public class Soucet3 {
  public static void main(String[] arg){
    int i = 12; // První sèítanec
    int j = 25; // Druhı sèítanec
    int k = i+j;// Souèet
    System.out.printf("Soucet cisel %d + %d je %d", i, j, k);
  }
};