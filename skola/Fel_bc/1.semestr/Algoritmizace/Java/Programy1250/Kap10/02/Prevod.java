/* Soubor Kap09\02\konvertor\Prevod.java
 * UPRAVENÁ VERZE VYUŽÍVAJÍCÍ VÝJIMEK, POPRVÉ

 * Pøevod desítkového celého èísla do jiné èíselné soustavy
 * program se spouští pøíkazem
        java Prevod èíslo základ
 * tedy napø.
	java Prevod 8 2
 * když chceme pøevést èíslo 8 do dvojkové soustavy.
 * Pak vypíše 1000, což je zápis 8 ve dvojkové soustavì.

 * Využívá služem tøídy Konvertor
 */



 public class Prevod {

  public Prevod() {
  }
  public static void main(String[] args) {
    try{
      int cislo = Integer.parseInt(args[0]);
      int zaklad = Integer.parseInt(args[1]);
      System.out.println(new konvertor.Konvertor(zaklad).konverze(cislo));
    }
    catch(RuntimeException e)
    {
        System.out.println("Pouziti:\n"+
              "java Prevod cislo cilova_soustava\n"+
              "cilova soustava musi byt v rozmezi 2 - 36");
        e.printStackTrace();
        System.exit(1);
    }
  }
}