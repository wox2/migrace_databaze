/* Soubor Kap03\06\Napis.java
   první pokusy s objekty
   Ve starších verzích JDK použijte pro výstup
   v metodì vystup() 
   pøíkaz skrytý v komentáøi
*/

class Text {
 private String text;
 public Text()
 {
   zmenText("Ahoj, lidi");
 }
 public Text(String s) {zmenText(s);}
 public void zmenText(String s) {text = s;}
 public void vypis() 
 { 
   System.out.printf("%s\n", text); // V JDK 5
   // System.out.prinln(text); // Ve starších JDK 
 }
}

class Napis
{
  public static void main(String[] arg)
  {
	Text n1 = new Text();
	Text n2 = new Text("Pozdrav z Marsu");
	n1.vypis();
	Text n3 = n1;
	n3.vypis();
	n2.vypis();
	n1.zmenText("Pozor, tunel");
	n1.vypis();
	n3.vypis();
  }
}