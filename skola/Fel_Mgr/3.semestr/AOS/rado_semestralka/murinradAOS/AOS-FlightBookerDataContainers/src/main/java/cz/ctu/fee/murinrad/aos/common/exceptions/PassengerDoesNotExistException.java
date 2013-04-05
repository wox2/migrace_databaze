/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.exceptions;

/**
 *
 * @author murinr
 */
public class PassengerDoesNotExistException extends Exception {

    public PassengerDoesNotExistException() {
    }

    public PassengerDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(message);
    }
    
    
    
}
