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

public class FindFlightsByPassengerIDAnswer {
    FlightCollection fc;

    public FindFlightsByPassengerIDAnswer() {
    }

    public FindFlightsByPassengerIDAnswer(FlightCollection content) {
        this.fc = content;
    }

    public FlightCollection getContent() {
        return fc;
    }

    public void setContent(FlightCollection content) {
        this.fc = content;
    }
    
    
    
}
