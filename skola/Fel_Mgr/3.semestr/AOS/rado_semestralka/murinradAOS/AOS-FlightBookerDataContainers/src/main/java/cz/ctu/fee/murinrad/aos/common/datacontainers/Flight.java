/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import java.sql.Date;
import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a flight.
 * Contains all data that are relevant to booking.
 * @author Radovan Murin
 */
public class Flight {
    private String departAPT,arrivalAPT,fNumber;
    private Long departure,arrival;
    private Integer id,baseFare;

    public Flight(String departAPT, String arrivalAPT, String fNumber, Date departure, Date arrival, int id,int baseFare) {
        this.departAPT = departAPT;
        this.arrivalAPT = arrivalAPT;
        this.fNumber = fNumber;
        this.departure = departure.getTime();
        this.arrival = arrival.getTime();
        this.id = id;
        this.baseFare = baseFare;
    }

    public Flight() {
    }
    
    
    
    
    @Override
    public String toString() {
        return "*******************************\n"+
                "ID:"+id+"\n"+
                "Departure:"+departAPT+","+departure.toString()+"\n"+
                "Arrival:"+arrivalAPT+","+arrival.toString()+"\n"+
                "Flight number:"+fNumber+
                "Base fare:"+baseFare;
        
    }
    
    
    @XmlElement(required = true)
    public String getDepartAPT() {
        return departAPT;
    }
    @XmlElement(required = true)
    public String getArrivalAPT() {
        return arrivalAPT;
    }
    @XmlElement(required = true)
    public String getfNumber() {
        return fNumber;
    }
    @XmlElement(required = true)
    public Long getDeparture() {
        return departure;
    }
    @XmlElement(required = true)
    public Long getArrival() {
        return arrival;
    }
    @XmlElement(required = true)
    public Integer getId() {
        return id;
    }

    public void setDepartAPT(String departAPT) {
        this.departAPT = departAPT;
    }

    public void setArrivalAPT(String arrivalAPT) {
        this.arrivalAPT = arrivalAPT;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public void setDeparture(Long departure) {
        this.departure = departure;
    }

    public void setArrival(Long arrival) {
        this.arrival = arrival;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @XmlElement(required = true)
    public Integer getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Integer baseFare) {
        this.baseFare = baseFare;
    }
    
    
    
    
    
    
    
    
    
    
    
}
