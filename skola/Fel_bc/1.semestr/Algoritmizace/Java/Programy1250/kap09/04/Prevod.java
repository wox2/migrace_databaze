/* Soubor Kap09\04\Prevod.java
 * Pøevod desítkového celého èísla do jiné èíselné soustavy
 * program se spouští pøíkazem 
        java Prevod èíslo základ
 * tedy napø.
	java Prevod 8 2
 * když chceme pøevést èíslo 8 do dvojkoé soustavy. 
 * Pak vypíše 1000, což je zápis 8 ve dvojkové soustavì.

 * Využívá služeb tøídy Konvertor, kterou najdete v Kap06\01\Konvertor.java
 */

public class Prevod {

  public Prevod() {
  }
  public static void main(String[] args) {
    if(args.length != 2)
    {
      System.out.println("Pouziti:\n"+
            "java Prevod cislo cilova_soustava\n"+
            "cilova soustava musi byt v rozmezi 2 - 36");
      System.exit(1);
    }
    int cislo = Integer.parseInt(args[0]);
    int zaklad = Integer.parseInt(args[1]);
    System.out.println(new Konvertor(zaklad).konverze(cislo));
  }
}