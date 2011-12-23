package alg12;

class ZasobnikTest {

  public static void main(String[] args) {
     ZasobnikZnaku3 z= new ZasobnikZnaku3();
     z.vloz('M');z.vloz('U');z.vloz('F');
    System.out.println(z.znakNaVrcholu());
    z.odeber();
    System.out.println(z.znakNaVrcholu());
    z.odeber();
    System.out.println(z.znakNaVrcholu());
  }

}
