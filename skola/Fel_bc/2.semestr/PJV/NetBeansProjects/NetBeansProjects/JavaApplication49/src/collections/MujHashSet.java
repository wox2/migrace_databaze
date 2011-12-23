/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author já
 */
public class MujHashSet{
protected static void createExample(){
    Set s = new HashSet();    // vytvoření nové množiny
s.add(new Integer(15));   // vložíme postupně 3 prvky
s.add(new Integer(-2));
s.add(new Integer(123));
s.add(null);              // vložíme null - je to povoleno
s.add(new Integer(-2));   // -2 už v množině je, podruhé se nevloží

System.out.println(s.size());  // vypíše počet prvků, tedy 4 (včetně hodnoty null)



}

}
