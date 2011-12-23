/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author janf
 */
@Entity
@NamedQueries({
@NamedQuery(name = Station.Q_GET_ALL_STATIONS, query = "SELECT s FROM Station s ORDER BY s.name"),
@NamedQuery(name = Station.Q_GET_BY_NAME, query = "SELECT s FROM Station s WHERE s.name = :name")})
public class Station implements Serializable {

    @Transient
    public static final String Q_GET_ALL_STATIONS = "Station.getAll";
    @Transient
    public static final String Q_GET_BY_NAME = "Station.getByName";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    
    @OneToMany(mappedBy = "station")
    private List<Position> positions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
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
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}