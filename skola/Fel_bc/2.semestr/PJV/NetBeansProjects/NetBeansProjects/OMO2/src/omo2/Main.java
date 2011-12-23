/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package omo2;

import java.util.*;

/**
 *
 * @author já
 */
public class Main {

    public static void main(String[] args) {
        HashSet names = new HashSet();

        names.add(new PersonalName("Semjon", "Ivanov"));
        names.add(new PersonalName("Volida", "Blabla"));
        names.add(new PersonalName("Semjon", "Ivanov"));


        for (Iterator i = names.iterator(); i.hasNext();) {
            
            
            
            System.out.println(names);
        }
// <PersonalName> genericky typ,


    /* ArrayList list = new ArrayList(10);
    list.add('c');
    list.add('c');
    list.remove(0);
    list.rremove(0);


    System.out.println(list.isEmpty()); */
    }
}
