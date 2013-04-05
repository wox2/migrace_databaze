import grafika.*;

public class Test {

  public Test() {
  }
  public static void main(String[] args) {
    GO g = new Bod(5,6,11);
    g.nakresli();
    g = new Usecka(1,2,3,4,5);
    g.nakresli();
    g = new Kruznice(8, 9, 10, 98);
    g.nakresli();
  }
}