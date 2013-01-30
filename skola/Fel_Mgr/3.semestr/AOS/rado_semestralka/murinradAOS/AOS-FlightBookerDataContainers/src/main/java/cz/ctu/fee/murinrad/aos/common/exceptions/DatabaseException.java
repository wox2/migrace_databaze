/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.exceptions;

/**
 *
 * @author Rado
 */
public class DatabaseException extends Exception{

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
        cause.printStackTrace();//@todo deleteme
    }
    
    
    
}
