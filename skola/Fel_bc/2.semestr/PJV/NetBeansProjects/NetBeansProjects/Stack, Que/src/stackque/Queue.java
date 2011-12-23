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
public class Queue implements Container {

  private   List<Double> contents = new ArrayList<Double>();

    /** Vlozi prvek do teto fronty */
  public void insert(Double e) {
    contents.add( e); // vlozi e na konec contents
  }

  /** Vybere a vrati nejdriv vlozeny prvek z teto fronty */
  public Double remove() {
    if ( contents.isEmpty()) return null;
    Double e = contents.get( 0);
    contents.remove( 0); // smaze prvni prvek z contents
    return e;
  }
}
