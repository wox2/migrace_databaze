/* Soubor Kap09\02\Test.java
 * Tøída Bod a její použití ve tøídì Test
 * Použití statických a nestatických atributù a metod
 */

class Bod{
	private int x, y;               // Soukromé datové složky
	private int barva;
	private static int pocet;       // Poèet instancí
	public Bod(){ pocet++;}         // Konstruktor
	public void setX(int _x){ x = _x; }
	public int getX(){ return x; }
	public void setY(int _y){ y = _y; }
	public int getY(){ return y; }
	public void setBarva(int _barva){ barva = _barva; }
	public int getBarva(){ return barva; }
	void nakresli(){
		System.out.println("kreslim bod (" + x + ", " +
			y+") ");
	}
	public static int getPocet() { return pocet; }
}

public class Test {

  public Test() {
  }
  public static void main(String[] args) {
    System.out.println(Bod.getPocet());
    final int N = 10;
    Bod[] body = new Bod[N];
    for(int i = 0; i < N; i++) body[i] = new Bod();
    System.out.println(Bod.getPocet());
    System.out.println(body[5].getBarva());
    for(int i = 0; i < N; i++)body[i].nakresli();
  }
}