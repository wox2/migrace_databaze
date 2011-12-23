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
public class Stack implements Container {
   List<Double> contents = new ArrayList<Double>();

    public void insert(Double e) {
    push(e);
  }

  public Double remove() {
    return pop();
  }

  /* zbytek je nezmenen */


  /** Vlozi prvek do tohoto zasobniku */
  public void push(Double e) {
    contents.add( 0, e); // vlozi e na zacatek contents
  }

  /** Vybere a vrati naposledy vlozeny prvek z tohoto zasobniku */
  public Double pop() {
    if ( contents.isEmpty()) return null;
    Double e = contents.get( 0);
    contents.remove( 0); // smaze prvni prvek z contents
    return e;
  }
}
