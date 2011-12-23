/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import timetable.model.Link;
import timetable.model.Passanger;
import timetable.model.Station;
import timetable.model.Ticket;
import timetable.sb.TicketSessionLocal;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author woxie
 */
@ManagedBean(name="order")
@SessionScoped
public class OrderBean {



    @EJB
    private TicketSessionLocal ps;


    @EJB
    private TimetableCenterLocal tc;


    /** Creates a new instance of SearchingBean */
    public OrderBean() {
    }

    public static <T> T getManagedBean(String beanName, Class<T> clazz) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
         ValueExpression valueExpression = expressionFactory.createValueExpression(facesContext.getELContext(), "#{" + beanName + "}", clazz);
         return (T) valueExpression.getValue(facesContext.getELContext());
     }

    public String addToTicket(Link link, Station departing, Station arriving, Date date) {
        String uname = getPassangerUsername();
        if (uname == null) return "index";
        Passanger passanger = tc.getPassangerByUsername(uname);
        try {
            ps.addLinkToTicket(link, departing, arriving, passanger, date);
            SearchingBean sb = getManagedBean("searching",SearchingBean.class);
            sb.setDepartureTime(sb.arrivalTime(link));
        } catch (Exception ex) {
            return "fail";
        }
        return "order";
    }

    public String getPassangerUsername() {
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null
                || !isPassangerLoggedIn())
            return null;
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public boolean isPassangerLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("passanger");
    }

    public boolean isTicketReadOnly() {
        return ps.isReadOnly();
    }

    public String openForRead(Ticket ticket) {
        if (ps.openForRead(ticket)) {
            return "ticket";
        } else {
            return "failed";
        }
    }

    public boolean isTicketEditable() {
        if (getTicket() == null) return false;
        return !isTicketReadOnly();
    }

    public Integer distance(Link link) {
        return ps.getDistanceByLink(link);
    }

    public Date time(Link link) {
        return ps.getJourneyTimeByLink(link);
    }

    public Date getTime() {
        return ps.getTotalJourneyTime();
    }

    public Integer getDistance() {
        return ps.getTotalDistance();
    }

    public Integer getCost() {
        return ps.getTotalPrice();
    }

    public Station getArriving() {
        return ps.getCurrent().getToStation();
    }

    public Station getDeparting() {
        return ps.getCurrent().getFromStation();
    }

    public Station arrivingByLink(Link link) {
        return ps.getArrivingStationByLink(link);
    }

    public Station departingByLink(Link link) {
        return ps.getDepartingStationByLink(link);
    }

    public Date arrivalTimeByLink(Link link) {
        return tc.getDepartureTime(link, ps.getArrivingStationByLink(link));
    }

    public Date departureTimeByLink(Link link) {
        return tc.getDepartureTime(link, ps.getDepartingStationByLink(link));
    }

    public List<Link> getLinks() {
        return ps.getCurrent().getLinks();
    }

    public Ticket getTicket() {
        return ps.getCurrent();
    }

    public String submit() {
        SearchingBean sb = getManagedBean("searching",SearchingBean.class);
        sb.setDepartureTime(new Date(0,0,0,12,0,0));
        try {
            ps.save();
            return "ordered";
        } catch (Exception ex) {
            return "failed";
        }
    }

    public String cancel() {
        SearchingBean sb = getManagedBean("searching",SearchingBean.class);
        sb.setDepartureTime(new Date(0,0,0,12,0,0));
        ps.discard();
        return "index";
    }

    public List<Ticket> getPassangersTickets() {
        return  tc.getPassangerByUsername(getPassangerUsername()).getTickets();
    }

    
}
