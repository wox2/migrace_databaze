/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vypsaniprvnivety;

/**
 *
 * @author já
 */
public class Main {
   static String prvniVetaVRetezci(String veta){
    String prvniVeta="";
    int i=0;
    for (;i<veta.length();i++){
      if (veta.charAt(i)!=(int)'.') {prvniVeta=prvniVeta+(char)veta.charAt(i);}
      else {prvniVeta=prvniVeta+".";
      i=veta.length();
      }

    }
     return prvniVeta;
   }

   static void prvniVetaPomociDoWhile(String veta){
  int i=0;
  do{
     if (veta.charAt(i)==(int)('.')) {System.out.println(".");i=0;} else {
         System.out.print(veta.charAt(i));
     ++i;}
  }
  while (i!=0 && i<veta.length());
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      String testovaciVeta="Dnes je krasny den Ja si programuju a je to super";
        System.out.println(prvniVetaVRetezci(testovaciVeta));
        prvniVetaPomociDoWhile(testovaciVeta);
    }

}

