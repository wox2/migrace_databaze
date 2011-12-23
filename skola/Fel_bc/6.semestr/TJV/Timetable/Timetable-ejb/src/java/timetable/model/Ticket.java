/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author woxie
 */
@Entity
@NamedQueries(
@NamedQuery(name = Ticket.Q_GET_ALL_TICKETS, query = "SELECT t FROM Ticket t"))
public class Ticket implements Serializable {

    @Transient
    public static final String Q_GET_ALL_TICKETS = "Ticket.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfValidity;

    @ManyToOne
    private Passanger passanger;
    @ManyToMany
    private List<Link> links;
    @ManyToOne
    private Station fromStation;
    @ManyToOne
    private Station toStation;

    public Date getDateOfValidity() {
        return dateOfValidity;
    }

    public void setDateOfValidity(Date dateOfValidity) {
        this.dateOfValidity = dateOfValidity;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public void setPassanger(Passanger passenger) {
        this.passanger = passenger;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ticket No. " + id;
    }

}