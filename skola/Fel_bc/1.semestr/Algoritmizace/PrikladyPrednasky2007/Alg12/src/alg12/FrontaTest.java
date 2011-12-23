package alg12;

class FrontaTest {

  public static void main(String[] args) {
     FrontaCisel f= new FrontaCisel();
    f.vloz('5');f.vloz('8');f.vloz('9');
    System.out.println((char)f.znakNaCele());
    f.odeber();
    System.out.println((char)f.znakNaCele());
    f.odeber();
    System.out.println((char)f.znakNaCele());
  }

}