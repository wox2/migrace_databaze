/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import javax.xml.bind.annotation.XmlElement;

/**
 * A seat on a specific plane
 * @author Radovan Murin
 */
public class Seat {
    Integer seatNo;
    Integer flightID;

    public Seat(Integer seatNo, Integer flightID) {
        this.seatNo = seatNo;
        this.flightID = flightID;
    }

    public Seat() {
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }
@XmlElement(required = true)
    public Integer getFlightID() {
        return flightID;
    }
@XmlElement(required = true)
    public Integer getSeatNo() {
        return seatNo;
    }
    
    
    
    
    
    
    @Override
    public boolean equals(Object s) {
        if (s instanceof Seat){
            Seat se = (Seat) s;
        if(se.flightID==flightID && se.seatNo==seatNo) {
            return true;
        }
        return false;}
        return super.equals(s);
    }
    
    @Override
    public String toString() {
        return flightID+" "+seatNo+"\n";
    }
    
    
    
}
