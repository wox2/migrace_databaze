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
import javax.servlet.http.HttpSession;
import timetable.model.Link;
import timetable.model.Station;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author janf
 */
@ManagedBean(name="searching")
@SessionScoped
public class SearchingBean {

    @EJB
    private TimetableCenterLocal tc;

    private List<Link> results;

    private Station departing = null;
    private Station arriving = null;
    private Date departureTime = null;
    private Date date = null;

    /** Creates a new instance of SearchingBean */
    public SearchingBean() {
    }

    public static <T> T getManagedBean(String beanName, Class<T> clazz) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
         ValueExpression valueExpression = expressionFactory.createValueExpression(facesContext.getELContext(), "#{" + beanName + "}", clazz);
         return (T) valueExpression.getValue(facesContext.getELContext());
     }

    public String findResults() {
        OrderBean ob = getManagedBean("order",OrderBean.class);
        if (ob.isTicketEditable()) {
            departing = ob.getArriving();
        }
        try {
            results = tc.getLinksForJourney(departing, arriving, departureTime);
        } catch (RuntimeException ex) {
            return "failed";
        }
        return "results";
    }

    public Date departureTime(Link link) {
        return tc.getDepartureTime(link, departing);
    }

    public Date arrivalTime(Link link) {
        return tc.getDepartureTime(link, arriving);
    }

    public Integer distance(Link link) {
        return tc.getDistance(link, departing, arriving);
    }

    public Date journeyTime(Link link) {
        return tc.getJourneyTime(link, departing, arriving);
    }

    public boolean canBeOrdered() {
        Date act = new Date();
        if (!isPassangerLoggedIn()) return false;
        if (date.getYear() < act.getYear()) return false;
        if (date.getYear() == act.getYear()) {
            if (date.getMonth() < act.getMonth()) return false;
            /*if (date.getMonth() == act.getMonth() && date.getDay() <= act.getDay()) {
                return false;
            }
             * Problematicka funkcnost
             */
        }
        return true;
    }

    public Date travelTime(Link link) {
        return new Date(tc.getPositionByRouteAndStation(arriving, link.getRoute()).getTimePosition().getTime() - tc.getPositionByRouteAndStation(departing, link.getRoute()).getTimePosition().getTime());
    }

    public int journeyPrice(Link link) {
        return tc.getJourneyPrice(link, departing, arriving);
    }


    public List<Link> getResults() {
        return results;
    }


    public int getResultCount() {
        return results.size();
    }

    public Station getArriving() {
        return arriving;
    }

    public void setArriving(Station arriving) {
        this.arriving = arriving;
    }

    public Station getDeparting() {
        return departing;
    }

    public void setDeparting(Station departing) {
        this.departing = departing;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
            this.departureTime = departureTime;
    }

    public boolean isAdminLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("administrator");
    }

    // TODO duplicitni
    public boolean isPassangerLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("passanger");
    }

    public boolean isLoggedIn() {
        return isAdminLoggedIn() || isPassangerLoggedIn();
    }

    public boolean isLoggedOut() {
        return !isLoggedIn();
    }

    public String getUserName() {
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null)
            return null;
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public String logout() {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        sess.invalidate();
        return "index";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
