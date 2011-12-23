/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.Link;
import timetable.model.Position;
import timetable.model.Station;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author woxie
 */
@ManagedBean(name="links")
@SessionScoped
public class LinksBean {

    @EJB
    private TimetableCenterLocal tc;

    private Link link = null;

    /** Creates a new instance of LinkBean */
    public LinksBean() {
    }

    public String newLink() {
        link = new Link();
        return "link";
    }

    public List<Link> getAllLinks() {
        return tc.getAllLinks();
    }


    public String saveLink() {
        tc.updateLink(link);
        return "links";
    }

    public String editLink(Link Link) {
        this.link = Link;
        return "link";
    }

    public void removeLink(Link Link) {
        tc.removeLink(Link);
    }

    public List<Position> getPositions() {
        return tc.getPositionsByLink(link);
    }

    public Link getLink() {
        return link;
    }

    public boolean hasNotes() {
        return !link.getNotes().isEmpty();
    }

    public Date departureTime(Station station) {
        return tc.getDepartureTime(link, station);
    }

    public void setLink(Link Link) {
        this.link = Link;
    }

    public String openLink(Link Link) {
        this.link = Link;
        return "link";
    }

    
}
