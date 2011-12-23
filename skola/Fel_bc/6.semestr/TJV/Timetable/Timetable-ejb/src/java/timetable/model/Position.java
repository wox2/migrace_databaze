/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NamedQuery(name = Position.Q_GET_ALL_POSITIONS, query = "SELECT pos FROM Position pos"),
@NamedQuery(name = Position.Q_GET_BY_KM_POSITION, query = "SELECT pos FROM Position pos WHERE pos.kmPosition = :kmPosition"),
@NamedQuery(name = Position.Q_GET_BY_TIME_POSITION, query = "SELECT pos FROM Position pos WHERE pos.timePosition = :timePosition")})
public class Position implements Serializable {

    @Transient
    public static final String Q_GET_ALL_POSITIONS = "Position.getAll";
    @Transient
    public static final String Q_GET_BY_KM_POSITION = "Position.getByKmPosition";
    @Transient
    public static final String Q_GET_BY_TIME_POSITION = "Position.getByTimePosition";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer kmPosition;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date timePosition;

    @ManyToOne
    private Route route;
    @ManyToOne
    private Station station;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKmPosition() {
        return kmPosition;
    }

    public void setKmPosition(Integer kmPosition) {
        this.kmPosition = kmPosition;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Date getTimePosition() {
        return timePosition;
    }

    public void setTimePosition(Date timePosition) {
        this.timePosition = timePosition;
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
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "timetable.model.Position[id=" + id + "]";
    }

}
