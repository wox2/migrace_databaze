/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.io.Serializable;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author woxie
 */
@Entity

@DiscriminatorValue("passanger")
@NamedQuery(name = Passanger.Q_GET_ALL_PASSENGERS, query = "SELECT p FROM Passanger p")
public class Passanger extends Person implements Serializable {

    @OneToMany(mappedBy = "passanger")
    private List<Ticket> tickets;

    @Transient
    public static final String Q_GET_ALL_PASSENGERS = "Passanger.getAll";

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    


}