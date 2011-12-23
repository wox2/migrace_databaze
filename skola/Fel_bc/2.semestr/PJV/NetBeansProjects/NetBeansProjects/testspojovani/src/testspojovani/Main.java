/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testspojovani;



/**
 *
 * @author ssssss
 */
public class Main {
  static void testStringu1(int i, int j) { // metoda testStringu1 secte 
                                           // retezec + dve cisla 
      System.out.println("1+2="+ i+j );    // a vyjde retezec 12
  }
  static void testStringu2(int i, int j) { // metoda testStringu2 secte  
                                           // nejdriv cisla takze 1+2 je  
      System.out.println(i+j + "=1+2");    // cislo a pak teprv zretezi,  
  }                                        // ale soucet je na zacatku 
  static void testStringu3(int i, int j) { // metoda testStringu3 je 
      System.out.println("1+2="+(i+j));    // poupraveni testStringu1 a   
  }                                        // dela to, co jsme chteli 
                                           // diky zavorkam
  public static void main(String[] args) {
      System.out.println("Tezka matematika aneb kolik je jedna plus dve?");
   int prvniScitanec=1;
   int druhyScitanec=2;
    testStringu1(prvniScitanec,druhyScitanec);
    testStringu2 (prvniScitanec,druhyScitanec);
    testStringu3 (prvniScitanec,druhyScitanec); 
  }

}
