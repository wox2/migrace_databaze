/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.Route;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author janf
 */
@ManagedBean(name="routes")
@SessionScoped
public class RoutesBean {

    @EJB
    private TimetableCenterLocal tc;

    private Route route = null;

    /** Creates a new instance of RouteBean */
    public RoutesBean() {
    }

    public String newRoute() {
        route = new Route();
        return "route";
    }

    public List<Route> getAllRoutes() {
        return tc.getAllRoutes();
    }


    public String saveRoute() {
        tc.updateRoute(route);
        return "routes";
    }

    public String editRoute(Route Route) {
        this.route = Route;
        return "route";
    }

    public void removeRoute(Route Route) {
        tc.removeRoute(Route);
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route Route) {
        this.route = Route;
    }

    
}
