/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package stackque;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author já
 */
public class Stack2 {

    private List<Integer> contents=new ArrayList<Integer>(10);
    private int count;

    public void push (int e){
       contents.add(0, e);
       count++;
    }

    public Integer pop(){
    if(contents.isEmpty()) return null; 
    { 
     int returnedInt=contents.get(0);
     contents.remove(0);
     count--;
     return returnedInt;
    }
    
    
    }
}
