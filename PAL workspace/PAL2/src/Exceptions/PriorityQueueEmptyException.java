/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 * Tato trida je potomkem tridy Exception, je vyvolana v pripade, ze se uzivatel snazi
 * o vyprazdneni prazdne prioritni fronty pomoci metody dequeue()
 * @author woxie
 */
public class PriorityQueueEmptyException extends Exception{
    /**
     * Konstruktor, vypise zpravu o pokusu o vyprazdneni jiz prazdne prioritni fronty.
     */
    public PriorityQueueEmptyException (){
            System.out.println("Trying to dequeue empty priority queue");
    }
}
