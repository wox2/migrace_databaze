/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import java.util.Collection;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlElement;

/**
 * A helper class to contain a SOAP answer.
 * @author Radovan Murin
 */
public class FlightCollection {
    private Collection <Flight> flights;

    public FlightCollection(Collection<Flight> flights) {
        this.flights = flights;
    }

    public FlightCollection() {
    }
    
    
    @XmlElement(required = true)
    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }
    
    
    public void addFlight(Flight f) {
        flights.add(f);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Flight> itF = flights.iterator();
        while(itF.hasNext()) {
            sb.append(itF.next().toString());
        }
        return sb.toString();
        
    }
    
    
    
    
}
