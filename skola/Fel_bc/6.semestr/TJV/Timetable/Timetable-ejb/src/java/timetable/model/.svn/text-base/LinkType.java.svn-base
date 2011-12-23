
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
@NamedQueries(
@NamedQuery(name = LinkType.Q_GET_ALL_LINKTYPES, query = "SELECT lt FROM LinkType lt"))
public class LinkType implements Serializable {

    @Transient
    public static final String Q_GET_ALL_LINKTYPES = "LinkType.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private int costPerKM;
    private String shortcut;
    
    @OneToMany(mappedBy = "linkType")
    private List<Link> links;

    public int getCostPerKM() {
        return costPerKM;
    }

    public void setCostPerKM(int costPerKM) {
        this.costPerKM = costPerKM;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
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
        if (!(object instanceof LinkType)) {
            return false;
        }
        LinkType other = (LinkType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return shortcut;
    }

}