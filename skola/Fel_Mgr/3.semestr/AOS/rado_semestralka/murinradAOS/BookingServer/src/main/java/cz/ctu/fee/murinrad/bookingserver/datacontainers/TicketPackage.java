/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver.datacontainers;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import java.util.Collection;

/**
 * A helper class to contain a SOAP answer.
 * @author murinr
 */
public class TicketPackage {
    
    Collection<Ticket> content;

    public TicketPackage() {
    }

    public TicketPackage(Collection<Ticket> content) {
        this.content = content;
    }

    public Collection<Ticket> getContent() {
        return content;
    }

    public void setContent(Collection<Ticket> content) {
        this.content = content;
    }
    
    
    
    
    
}
