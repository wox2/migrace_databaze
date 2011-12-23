/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.sb;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import timetable.model.*;

/**
 *
 * @author janf
 */
@Local
public interface TimetableCenterLocal {

    public static String INACTIVE_PREFIX = "inactive_";

    public Date addTimes(Date time1, Date time2);
    public Date subTimes(Date time1, Date time2);


    /**
     * Routes where station1 lies closer to the route beginning than station2
     */
    List<Route> getRoutesConnectingStations(Station station1, Station station2);
    /**
     * Links which depart from specified station at specified time or later
     */
    List<Link> getLinksByDepartureTime(Route route, Station station, Date time);
    /**
     * Combination of getRoutesConnectingStations and getLinksByDepartureTime
     * Links which are useable for journey from station1 to station2 and depart
     * from station1 at specified time or later
     */
    List<Link> getLinksForJourney(Station station1, Station station2, Date time);
    /**
     * Returns journey price for selected link between station1 and station2
     */
    int getJourneyPrice(Link link, Station station1, Station station2);
    /**
     * Returns departure time of the link in the station
     */
    Time getTimeByLinkAndStation(Link link, Station station);
    /**
     * Returns the time at which the link stops at the station.
     */
    public Date getDepartureTime(Link link, Station station);

    Position getPositionByRouteAndStation(Station station, Route route);

    /**
     * Returns list ordered by time positions of the stations on the route.
     */
    public List<Position> getPositionsByLink(Link link);

    /**
     * Deletes the InactivePassanger passed as argument
     * and replaces it with an Passanger with same properties.
     */
    void activatePassanger(InactivePassanger inactive);

    /**
     * Returns distance between station1 and station2 measured on the route
     * which belongs to the specified link.
     */
    public int getDistance(Link link, Station station1, Station station2);
    /**
     * Returns journey time between station1 and station2 measured on the route
     * which belongs to the specified link.
     */
    public Date getJourneyTime(Link link, Station station1, Station station2);

    /* GET ALL */
    public List<Route> getAllRoutes();
    List<Link> getAllLinks();
    List<LinkType> getAllLinkTypes();
    List<Note> getAllNotes();
    List<Position> getAllPositions();
    List<Station> getAllStations();
    List<InactivePassanger> getAllInactives();
    List<Passanger> getAllPassangers();


    /* GET BY ID */
    LinkType getLinkTypeById(int id);
    Note getNoteById(int id);
    Route getRouteById(long id);
    Station getStationById(int id);
    public Passanger getPassangerByUsername(String username);

    /* UPDATE */
    void updateLink(Link link);
    void updateLinkType(LinkType linkType);
    void updateNote(Note note);
    void updatePosition(Position position);
    void updateRoute(Route route);
    void updateStation(Station station);
    void updateInactive(InactivePassanger passanger);
    void updatePassanger(Passanger passanger);

    /* REMOVE */
    void removeLink(Link link);
    void removeLinkType(LinkType linkType);
    void removeNote(Note note);
    void removePosition(Position position);
    void removeRoute(Route route);
    void removeStation(Station station);
    void removeInactive(InactivePassanger passanger);
    void removePassanger(Passanger passanger);
}
