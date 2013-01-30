/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.exceptions;

/**
 *
 * @author murinr
 */
public class TicketDoesNotExistException extends Exception {

    public TicketDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
        System.out.println(message);
    }
    
    
}
