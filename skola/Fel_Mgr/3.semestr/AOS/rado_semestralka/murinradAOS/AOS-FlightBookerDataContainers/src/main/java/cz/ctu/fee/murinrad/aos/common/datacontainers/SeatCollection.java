/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;

/**
 * A helper class to contain a SOAP answer.
 * @author Radovan Murin
 */
public class SeatCollection {

    Collection<Seat> content;
    public SeatCollection(Collection<Seat> freeSeats) {
        content = freeSeats;
    }

    public SeatCollection() {
    }
@XmlElement(required = true)
    public Collection<Seat> getContent() {
        return content;
    }

    public void setContent(Collection<Seat> content) {
        this.content = content;
    }
    
    
    
}
