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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author janf
 */
@Entity
@NamedQueries(
@NamedQuery(name = Note.Q_GET_ALL_NOTES, query = "SELECT n FROM Note n"))
public class Note implements Serializable {

    @Transient
    public static final String Q_GET_ALL_NOTES = "Note.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String explanation;
    
    @ManyToMany(mappedBy = "notes")
    private List<Link> links;

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return explanation;
    }

}