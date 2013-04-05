/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver.datacontainers;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;

/**
 * A helper class to contain a SOAP answer.
 * @author Radovan Murin
 */
public class GetTicketAnswer {
    Ticket content;

    public GetTicketAnswer() {
    }

    public GetTicketAnswer(Ticket content) {
        this.content = content;
    }

    public void setContent(Ticket content) {
        this.content = content;
    }

    public Ticket getContent() {
        return content;
    }
    
    
    
}
