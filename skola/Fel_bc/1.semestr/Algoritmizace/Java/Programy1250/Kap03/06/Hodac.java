class Text {
 private String text;
 public Text()
 {
   text = "Vtipný text";
 }
 public Text(String s) {text = s;}
 public void zmenText(String s) {text = s;}
 public void vypis() { System.out.println(text);}
}

class Napis
{
  public static void main(String[] arg)
  {
	Text n1 = new Text();
	Text n2 = new Text("Veselé vánoce");
	n1.vypis();
	Text n3 = n1;
	n3.vypis();
	n2.vypis();
	n1.zmenText("Generální øeditel ÈT Jiøí Hodaè se obrátil"+
		" na Radu Èeské republiky pro rozhlasové a televizní " +
		"vysílání se žádostí o rozhodnutí, který program ÈT " +
		"je legálním a autorizovaným programem v souladu se zákonem " +
		"o Èeské televizi a který nikoli. Do rozhodnutí Rady vysílá ÈT " +
		"jako svùj program toto sdìlení.");

	n1.vypis();
	n3.vypis();
  }
}