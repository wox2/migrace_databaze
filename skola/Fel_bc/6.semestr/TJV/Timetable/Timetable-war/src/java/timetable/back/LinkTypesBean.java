/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.LinkType;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author woxie
 */
@ManagedBean(name="linktypes")
@SessionScoped
public class LinkTypesBean {

    private static int scnt;
    private int cnt;

    public int getCnt() {
        return cnt;
    }

    

    @EJB
    private TimetableCenterLocal tc;

    private LinkType linktype = null;

    /** Creates a new instance of LinkTypeBean */
    public LinkTypesBean() {
        cnt = scnt++;
    }

    public String newLinkType() {
        linktype = new LinkType();
        return "linktype";
    }

    public List<LinkType> getAllLinkTypes() {
        return tc.getAllLinkTypes();
    }


    public String saveLinkType() {
        tc.updateLinkType(linktype);
        return "linktypes";
    }

    public String editLinkType(LinkType LinkType) {
        this.linktype = LinkType;
        return "linktype";
    }

    public void removeLinkType(LinkType LinkType) {
        tc.removeLinkType(LinkType);
    }

    public LinkType getLinkType() {
        return linktype;
    }
    
    public void setLinkType(LinkType LinkType) {
        this.linktype = LinkType;
    }

    
}
