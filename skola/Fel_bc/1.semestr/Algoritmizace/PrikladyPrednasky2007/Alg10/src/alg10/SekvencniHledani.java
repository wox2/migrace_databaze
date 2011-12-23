package alg10;



public class SekvencniHledani {
  public static void main(String[] args) {
    int[] pole = {7, 4, 10, 67, 12, 55, 33,0};
    int x = 11;
    System.out.print(hledej(pole, x));
    System.out.print(hledejSeZarazkou(pole, 7, x)); 
  }

  static int hledej(int[] pole, int x) {
    for (int i=0; i<pole.length; i++)
      if (x==pole[i]) return i;
    return -1;
  }

  static int hledejSeZarazkou(int[] pole, int volny, int x) {
    int i = 0;
    pole[volny] = x; // uložení zarážky
    while (pole[i]!=x) i++;
    if (i<volny) // hodnota nalezena
      return i;
    else         // hodnota nenalezena
      return -1;
  }
}