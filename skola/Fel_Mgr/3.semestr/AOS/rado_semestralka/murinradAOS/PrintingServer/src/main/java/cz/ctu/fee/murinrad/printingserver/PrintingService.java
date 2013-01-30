/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.printingserver;


import cz.ctu.fee.murinrad.aos.common.datacontainers.Passenger;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;


/**
 *
 * @author Radovan Murin
 */
@WebService(endpointInterface="cz.ctu.fee.murinrad.printingserver.PrintingServiceIface")
public class PrintingService implements PrintingServiceIface {

    @Override
    public DataHandler printTicket(Ticket t,Passenger p) {
        String s = t.toString();
        s = s.concat("\n").concat(p.toString());
        ByteArrayDataSource bads = new ByteArrayDataSource(s.getBytes(), "text/plain");
        return new DataHandler(bads);
    }
    
}
