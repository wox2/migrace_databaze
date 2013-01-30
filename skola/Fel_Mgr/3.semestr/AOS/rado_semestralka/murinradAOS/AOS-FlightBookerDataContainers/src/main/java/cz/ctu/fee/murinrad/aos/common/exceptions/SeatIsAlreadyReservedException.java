/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.exceptions;

/**
 *
 * @author Radovan Murin
 */
public class SeatIsAlreadyReservedException extends Exception {

    public SeatIsAlreadyReservedException(String s, RuntimeException ex) {
        super(s,ex);
        System.out.println(s);
    }
    
}
