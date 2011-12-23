/* Soubor Kap08\01\Switch1.java
 * použití pøíkazu switch - první, nepodaøený pokus
 * Program si vyžádá malé celé èíslo a podle jeho hodnoty vypíše pozdrav
*/

public class Switch1 {
  public static void main(String[] s){
    System.out.print("Zadej male cele cislo: ");
    int i = MojeIO.inInt();

    switch(i)
    {
      case 1: 
      case 5: 
	System.out.println("Ahoj");
      case 2:
      case 6: 
	System.out.println("Te pic");
      case 3:
      case 7: 
	System.out.println("Cau");
      default: 
	System.out.println("Nerozumim");
    }
  }
}