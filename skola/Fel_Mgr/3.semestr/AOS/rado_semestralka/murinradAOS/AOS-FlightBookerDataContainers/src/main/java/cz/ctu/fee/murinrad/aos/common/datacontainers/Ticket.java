/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import javax.xml.bind.annotation.XmlElement;

/**
 * A ticket. A ticket contains data such as the price the flight ID and the number.
 * It also contains the status. 0 if not paid and 1 if paid.
 * @author Radovan Murin
 */
public class Ticket {
    Integer ticketNumber,flightID,status,seatNumber;
    Double totalPrice;

    public Ticket(Integer ticketNumber, Integer flightID, Integer status, Integer seatNumber, Double totalPrice) {
        this.ticketNumber = ticketNumber;
        this.flightID = flightID;
        this.status = status;
        this.seatNumber = seatNumber;
        this.totalPrice = totalPrice;
    }

    public Ticket() {
    }
    
    
    
    @XmlElement(required = true)
    public Integer getStatus() {
        return status;
    }
    @XmlElement(required = true)
    public Integer getFlightID() {
        return flightID;
    }
    @XmlElement(required = true)
    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
@XmlElement(required = true)
    public Double getTotalPrice() {
        return totalPrice;
    } 
@XmlElement(required = true)
    public Integer getSeatNumber() {
        return seatNumber;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*********************TICKET*********************\n")
                .append("Flight: "+flightID).append('\n')
                .append("Seat number: "+seatNumber).append('\n');
        return sb.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
