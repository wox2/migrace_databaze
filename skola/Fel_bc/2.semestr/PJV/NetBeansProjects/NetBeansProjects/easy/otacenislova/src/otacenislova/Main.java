/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package otacenislova;

/**
 *
 * @author já
 */

public class Main {

  static String otaceniSlova(String slovo){
   String p="";
   int i;
   for (i=0;i<slovo.length();i++){
    p=p+(char)slovo.charAt(slovo.length()-(i+1));
    }
 return p;
}
  static String zakodovaniSlova (String slovo){
    String p="";
    int i=0;
    for (;i<slovo.length();i++){
    p=p+(char)(slovo.charAt(i)+3);
    }
   return p;
  }

    public static void main(String[] args) {
   String slovo="barbucha";
        System.out.println("Otocene slovo je " + otaceniSlova(slovo));
        System.out.println("Zakodovane slovo je " + zakodovaniSlova(slovo));
    }

}
