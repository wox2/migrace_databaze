/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proseminar;

import java.io.Serializable;

/**
 *
 * @author já
 */
public class Id implements Serializable, Comparable { // Serializable - da se schovat do souboru
   int id;
    public int compareTo(Object o) {

        return id-((Id)o).id;
    }
    public boolean eaquals (Object o){
    if(o==null) return true;
    if(!(getClass().equals(o.getClass()))) return false;
    return compareTo(o)==0;
    }

    int hashcode(){
    return id*7;


    }
}
