/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.awt.Color;
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
@NamedQueries({
@NamedQuery(name = Link.Q_GET_ALL_LINKS, query = "SELECT l FROM Link l"),
@NamedQuery(name = Link.Q_GET_BY_DEPARTURE_TIME, query = "SELECT l FROM Link l WHERE l.departureTime = :departureTime")})
public class Link implements Serializable {

    @Transient
    public static final String Q_GET_ALL_LINKS = "Link.getAll";
    @Transient
    public static final String Q_GET_BY_DEPARTURE_TIME = "Link.getByDepartureTime";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date departureTime;

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    private Integer linkNumber;

    @ManyToOne
    private Route route;

    @ManyToMany
    private List<Note> notes;
    @ManyToOne
    private LinkType linkType;

    @ManyToMany(mappedBy = "links")
    private List<Ticket> tickets;

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Integer getLinkNumber() {
        return linkNumber;
    }

    public void setLinkNumber(Integer linkNumber) {
        this.linkNumber = linkNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timetable.model.Connection[id=" + id + "]";
    }

}