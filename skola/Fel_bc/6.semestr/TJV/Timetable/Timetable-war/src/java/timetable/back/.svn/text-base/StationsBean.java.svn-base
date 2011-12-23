/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.back;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import timetable.model.Station;
import timetable.sb.TimetableCenterLocal;

/**
 *
 * @author janf
 */
@ManagedBean(name="stations")
@SessionScoped
public class StationsBean {

    @EJB
    private TimetableCenterLocal tc;

    private Station station = null;

    /** Creates a new instance of StationBean */
    public StationsBean() {
    }

    public String newStation() {
        station = new Station();
        return "station";
    }

    public List<Station> getAllStations() {
        return tc.getAllStations();
    }


    public String saveStation() {
        tc.updateStation(station);
        return "stations";
    }

    public String editStation(Station station) {
        this.station = station;
        return "station";
    }

    public void removeStation(Station station) {
        tc.removeStation(station);
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    
}
