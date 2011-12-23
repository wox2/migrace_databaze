/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;





/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
 
public static void main(String args[]) {

 //   ContainerExample.createExample();
 //   MujHashSet.createExample();
 //   MujSortedSet.createExample();
 //   MujArraylist.createExample();
 //     MujHashMap.createExample();
    List l= new LinkedList();
        l.add(3);
        l.add(45);
        l.add(3);
        l.add("ddd");
        Iterator it=l.iterator();
        while(it.hasNext()){System.out.println(it.next());}
  }
}