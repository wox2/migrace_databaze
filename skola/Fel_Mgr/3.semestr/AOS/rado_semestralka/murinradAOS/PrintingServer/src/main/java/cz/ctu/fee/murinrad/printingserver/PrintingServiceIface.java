/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.printingserver;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Passenger;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Radovan Murin
 */
@WebService
public interface PrintingServiceIface {
    /**
     * Prints the ticket
     * @param t the ticket to be printed.
     * @param p the passenger
     * @return the printed ticket in byte form
     */
    public DataHandler printTicket(@WebParam(name="Ticket") Ticket t,@WebParam(name="Passenger") Passenger p);
    
    
}
