/* Soubor Kap08\01\Switch2.java
 * použití pøíkazu switch - druhý pokus (opraveno)
 * Program si vyžádá malé celé èíslo a podle jeho hodnoty vypíše pozdrav
 */

public class Switch2 {
  public static void main(String[] s){
    System.out.print("Zadej male cele cislo: ");
    int i = MojeIO.inInt();

    switch(i)
    {
      case 1: 
      case 5: 
	System.out.println("Ahoj");
	break;
      case 2:
      case 6: 
	System.out.println("Te pic");
	break;
      case 3:
      case 7: 
	System.out.println("Cau");
	break;
      default: 
	System.out.println("Nerozumim");
	break;
    }
  }
}