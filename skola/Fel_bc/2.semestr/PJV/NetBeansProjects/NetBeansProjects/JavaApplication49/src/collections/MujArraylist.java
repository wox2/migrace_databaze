/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author já
 */
public class MujArraylist {
protected static void createExample(){

List l = new ArrayList();     // vytvoření seznamu pro 3 položky
l.add(0, "");                  // vložení prvků
l.add(1, "abcd");
l.add(2, "123w");

// přidání dalších prvků - 1. varianta (vhodná pro přidání více prvků)
 //l.ensureCapacity(6); zvětšíme kapacitu seznamu (zde na 6)
l.add(3, "tohle JDE");    // přidáme prvek
l.add(4, "pokračujeme");  // přidáme prvek...

// přidání dalších prvků - 2. varianta (vhodná pro jednotlivé prvky)
l.add("tohle také JDE");  // přidáme prvek
l.add("pokračujeme");     // přidáme prvek...
}

}
