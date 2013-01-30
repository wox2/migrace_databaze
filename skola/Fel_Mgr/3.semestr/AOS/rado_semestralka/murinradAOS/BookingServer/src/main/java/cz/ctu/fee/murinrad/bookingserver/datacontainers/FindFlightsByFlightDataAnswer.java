/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver.datacontainers;

import cz.ctu.fee.murinrad.aos.common.datacontainers.FlightCollection;
import javax.xml.bind.annotation.XmlType;

/**
 * A helper class to contain a SOAP answer.
 * @author Radovan Murin
 */

public class FindFlightsByFlightDataAnswer {
    FlightCollection data;
    public FindFlightsByFlightDataAnswer(FlightCollection content) {
        this.data = content;
    }

    public void setContent(FlightCollection content) {
        this.data = content;
    }

    public FlightCollection getContent() {
        return data;
    }

    public FindFlightsByFlightDataAnswer() {
    }
    
    
    
}
