/* Soubor Kap08\04\Break.java
   Pøíklad na break s návìštím v cyklu
   Metoda najdi vyhledá první výskyt zadaného znaku v poli øetìzcù
*/
	
public class Break {
  private int i, j;
  public boolean najdi(char c, String[] s)
  {
    Ven:
      for(i = 0; i < s.length; i++)
      for(j = 0; j < s[i].length(); j++)
      {
        if(s[i].charAt(j)=='u') break Ven;
      }
    if(i == s.length) return false;
    else return true;
  }

  int slovo(){return i+1;}
  int znak(){return j+1;}

  public static void main(String[] ss) {
    Break b = new Break();
    char x = 'u';
    String[] s = {"ahoj", "lidi", "to", "je", "hruza"};
    System.out.print("Zadane retezce:");
    for(int i = 0; i < s.length; i++) 
      System.out.print(s[i] + " ");
    System.out.println(); 
    if(b.najdi(x, s)) System.out.print("Znak " + x + " je " + b.znak() + ". v "+b.slovo()+". slovu");
    else System.out.print("Neni tam");
  }
}