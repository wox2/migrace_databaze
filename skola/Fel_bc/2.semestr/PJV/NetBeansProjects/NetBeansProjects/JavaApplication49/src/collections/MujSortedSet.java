/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author já
 */
public class MujSortedSet {

    protected static void createExample() {
        SortedSet s = new TreeSet();  // vytvoření množiny
        s.add(new Double(12.3));      // vložíme 3 prvky
        s.add(new Double(100.456));
        s.add(new Double(-0.001));

        System.out.println("První prvek je: " + s.first()); // vypíše první prvek

        Iterator it = s.iterator();       // iterujeme přes všechny prvky
        while (it.hasNext()) {            // budou se vypisovat ve vzestupném pořadí
            System.out.println(it.next());


        }
    }
}
