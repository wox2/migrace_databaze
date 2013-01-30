/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import javax.xml.bind.annotation.XmlElement;

/**
 * Abstract class that represents a payment.
 * A payment has a transactionID, amountDue and amountOver - to be returned when a cash transaction is in place.
 * confirmed is a flag whether the bank confirmed the payment.
 * @author murinr
 */
public abstract class Payment {
    Double amountDue,amountOver;
    Boolean confirmed = false;
    Integer transactionID,ticketID;
    

    public Payment() {
    }

    public Payment(Double amountDue, Integer ticketID) {
        this.amountDue = amountDue;
        this.ticketID = ticketID;
    }
    @XmlElement(required = true)
    public Double getAmountDue() {
        return amountDue;
    }
@XmlElement(required = true)
    public Integer getTicketID() {
        return ticketID;
    }
@XmlElement(required = true)
    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public void setAmountOver(Double amountOver) {
        this.amountOver = amountOver;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }
@XmlElement(required = true)
    public Double getAmountOver() {
        return amountOver;
    }
@XmlElement(required = true)
    public Boolean getConfirmed() {
        return confirmed;
    }
    
    
    
    
    
    

    
    
    
}
