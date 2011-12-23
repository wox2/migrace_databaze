/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 * Vyjimka, pokud nebyl nalezen Node, ktery nebyl nalezen. V teto verzi nebyla tato trida pouzita
 * @author woxie
 */
public class NodeNotFoundException extends Exception {
    /**
     * Konstruktor vytvori vyjimku a vypise text o nenalezeni Nodu s danym jmenem.
     */
    public NodeNotFoundException(int id){
        System.out.println("Node s id \"" + id +"\" nebyl nalezen");
    }
}
