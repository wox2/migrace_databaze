/* Sèítání dvou èísel - tøetí pokus

*  Využívá MojeIO.inInt()
*  Do adresáøe s programem (nebo do kteréhokoli
*  adresáøe, do kterého ukazuje promìnná CLASSPATH,
*  je tøeba nakopírovat soubor MojeIO.class.

*  soubor kap03\04\Soucet4.java
*/

public class Soucet4 {
  public static void main(String[] arg){
    System.out.print("Zadej prvni scitanec: ");
    int i = MojeIO.inInt(); // Èti první scitanec
    System.out.print("Zadej druhy scitanec: ");
    int j = MojeIO.inInt(); // Èti druhý sèítanec
    System.out.println("Jejich soucet je " + (i+j));
  }
};