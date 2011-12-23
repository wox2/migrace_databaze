/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.sb;

import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import timetable.model.*;

/**
 *
 * @author woxie
 */
@Stateless
public class TimetableCenter implements TimetableCenterLocal {

    @PersistenceContext
    EntityManager em;

    public List<Route> getRoutesConnectingStations(Station station1, Station station2) {
        List<Route> list1 = (List<Route>) em.createQuery("SELECT r FROM Station s1 JOIN s1.positions p JOIN p.route r WHERE s1=:station1").setParameter("station1", station1).getResultList();
        List<Route> list2 = (List<Route>) em.createQuery("SELECT r FROM Station s2 JOIN s2.positions p JOIN p.route r WHERE s2=:station2").setParameter("station2", station2).getResultList();
        List<Route> res = new LinkedList<Route>();
        for (Route r : list1) {
            // list1 intersect list2
            if (list2.contains(r) && getPositionByRouteAndStation(station1, r).getTimePosition().getTime() < getPositionByRouteAndStation(station2, r).getTimePosition().getTime()) {
                res.add(r);
            }
        }
        return res;
    }

    public Date addTimes(Date time1, Date time2) {
        int hrs = (time1.getHours() + time2.getHours() + (time1.getMinutes() + time2.getMinutes()) / 60) % 24;
        int mins = (time1.getMinutes() + time2.getMinutes()) % 60;
        return new Date(0,0,0,hrs, mins, 0);
    }

    public Date subTimes(Date time1, Date time2) {
        int hrs = (time1.getHours() - time2.getHours() - ((time1.getMinutes() < time2.getMinutes()) ? 1 : 0)) % 24;
        int mins = (time1.getMinutes() - time2.getMinutes()) % 60;
        return new Date(0,0,0,hrs, mins, 0);
    }

    public List<Link> getLinksByDepartureTime(Route route, Station station, Date time) {
        Date timepos = getPositionByRouteAndStation(station, route).getTimePosition();
        Date queryTime = subTimes(time, timepos);
        return (List<Link>) em.createQuery("SELECT l FROM Route r JOIN r.links l "
                + "WHERE r=:route AND l.departureTime >= :time ORDER BY l.departureTime")
                .setParameter("route", route).setParameter("time", queryTime).getResultList();
    }

    public List<Link> getLinksForJourney(Station station1, Station station2, Date time) {
        if (station1.equals(station2)) throw new RuntimeException("Stations must not be equal.");
        List<Route> routes = getRoutesConnectingStations(station1, station2);
        List<Link> res = new LinkedList<Link>();
        for (Route r : routes) {
            res.addAll(getLinksByDepartureTime(r, station1, time));
        }
        return res;
    }

    public Position getPositionByRouteAndStation(Station station, Route route) {
        List<Position> list = (List<Position>) em.createQuery("SELECT p FROM Position p JOIN p.station s JOIN p.route r "
                + "WHERE s=:station AND r=:route").setParameter("station", station).setParameter("route", route)
                .getResultList();
        if (list.isEmpty()) return null;
        if (list.size() == 1) return list.get(0);
        else throw new RuntimeException("Database not ok.");
    }// expects that there is only one position for each route and station

    @Override
    public int getJourneyPrice(Link link, Station station1, Station station2) {
        LinkType type = link.getLinkType();
        Route route = link.getRoute();
        Position p1 = getPositionByRouteAndStation(station1, route);
        Position p2 = getPositionByRouteAndStation(station2, route);
        int distance = Math.abs(p2.getKmPosition() - p1.getKmPosition()); // Math.abs probably is not necessary
        return type.getCostPerKM() * distance;
    }

    public Date getDepartureTime(Link link, Station station) {
        Position p = getPositionByRouteAndStation(station, link.getRoute());
        if (p == null) return new Date();
        return addTimes(link.getDepartureTime(), getPositionByRouteAndStation(station, link.getRoute()).getTimePosition());
    }

    public int getDistance(Link link, Station station1, Station station2) {
        Position p1 = getPositionByRouteAndStation(station1, link.getRoute());
        Position p2 = getPositionByRouteAndStation(station2, link.getRoute());
        return p2.getKmPosition() - p1.getKmPosition();
    }

    public Date getJourneyTime(Link link, Station station1, Station station2) {
        Position p1 = getPositionByRouteAndStation(station1, link.getRoute());
        Position p2 = getPositionByRouteAndStation(station2, link.getRoute());
        return addTimes(p2.getTimePosition(), p1.getTimePosition());
    }



    public Time getTimeByLinkAndStation(Link link, Station station) {
        // TODO kdyz link nestavi ve station
        // link.departureTime + position[station, route[link]].time_position
        return (Time) em.createQuery("SELECT p.timePosition FROM Link l JOIN l.route r JOIN r.positions p WHERE p.station=:station AND l=:link").setParameter("station", station).setParameter("link", link).getSingleResult();
    }


    @Override
    @RolesAllowed({"administrator"})
    public void activatePassanger(InactivePassanger inactive) {
        Passanger p = new Passanger();
        String username = inactive.getUsername();
        if (!INACTIVE_PREFIX.equals(username.substring(0, INACTIVE_PREFIX.length()))) {
            throw new RuntimeException("Database malformed.");
        }
        username = username.substring(INACTIVE_PREFIX.length());
        p.setFirstName(inactive.getFirstName());
        p.setSurname(inactive.getSurname());
        p.setUsername(username);
        p.setPassword(inactive.getPassword());
        p.setCity(inactive.getCity());
        removeInactive(inactive);
        updatePassanger(p);
    }


    public LinkType getLinkTypeById(int id) {
        LinkType linkType = em.find(LinkType.class, id);
        return linkType;
    }

    @RolesAllowed({"administrator","passanger"})
    public Passanger getPassangerByUsername(String username) {
        Passanger passanger = em.find(Passanger.class, username);
        return passanger;
    }

    public Note getNoteById(int id) {
        Note note = em.find(Note.class, id);
        return note;
    }

    public Route getRouteById(long id) {
        Route route = em.find(Route.class, id);
        return route;
    }
    public Station getStationById(int id) {
        Station station = em.find(Station.class, id);
        return station;
    }

    @RolesAllowed({"administrator"})
    public void updateLink(Link link) {
        em.merge(link);
    }

    @RolesAllowed({"administrator"})
    public void updateLinkType(LinkType linkType) {
        em.merge(linkType);
    }

    @RolesAllowed({"administrator"})
    public void updateNote(Note note) {
        em.merge(note);
    }

    public void updateInactive(InactivePassanger passanger) {
        if (em.find(Passanger.class, passanger.getUsername()) != null || em.find(Administrator.class, passanger.getUsername()) != null) {
            throw new RuntimeException("Username already in use.");
        }
        if (passanger.getUsername().length() < INACTIVE_PREFIX.length()
                || !INACTIVE_PREFIX.equals(passanger.getUsername().substring(0, INACTIVE_PREFIX.length())))
            passanger.setUsername(INACTIVE_PREFIX + passanger.getUsername() );
        if (em.find(InactivePassanger.class, passanger.getUsername()) != null) {
            throw new RuntimeException("Username already in use.");
        }
        em.merge(passanger);
    }

    @RolesAllowed({"administrator"})
    public void updatePassanger(Passanger passanger) {
        em.merge(passanger);
    }

    @RolesAllowed({"administrator"})
    public void updatePosition(Position position) {
        em.merge(position);
    }

    @RolesAllowed({"administrator"})
    public void updateRoute(Route route) {
        em.merge(route);
    }

    @RolesAllowed({"administrator"})
    public void updateStation(Station station) {
        em.merge(station);
    }

    @RolesAllowed({"administrator"})
    public void removeLink(Link link) {
        link = em.merge(link);
        em.remove(link);
    }

    @RolesAllowed({"administrator"})
    public void removeLinkType(LinkType linkType) {
        linkType = em.merge(linkType);
        em.remove(linkType);
    }

    @RolesAllowed({"administrator"})
    public void removeNote(Note note) {
        note = em.merge(note);
        em.remove(note);
    }

    @RolesAllowed({"administrator"})
    public void removeInactive(InactivePassanger passanger) {
        passanger = em.merge(passanger);
        em.remove(passanger);
    }

    @RolesAllowed({"administrator"})
    public void removePassanger(Passanger passanger) {
        passanger = em.merge(passanger);
        List<Ticket> list = passanger.getTickets();
        for (Ticket t : list) {
            t = em.merge(t);
            em.remove(t);
        }
        em.remove(passanger);
    }

    public List<Position> getPositionsByLink(Link link) {
        return (List<Position>)
                em.createQuery("SELECT p FROM Link l JOIN l.route r JOIN r.positions p WHERE l=:link ORDER BY p.timePosition").setParameter("link", link).getResultList();
    }

    @RolesAllowed({"administrator"})
    public void removePosition(Position position) {
        position = em.merge(position);
        em.remove(position);
    }

    @RolesAllowed({"administrator"})
    public void removeRoute(Route route) {
        route = em.merge(route);
        em.remove(route);
    }

    @RolesAllowed({"administrator"})
    public void removeStation(Station station) {
        station = em.merge(station);
        em.remove(station);
    }

    public List<Link> getAllLinks() {
        return (List<Link>) em.createNamedQuery(Link.Q_GET_ALL_LINKS).getResultList();
    }

    public List<LinkType> getAllLinkTypes() {
        return (List<LinkType>) em.createNamedQuery(LinkType.Q_GET_ALL_LINKTYPES).getResultList();
    }

    public List<Note> getAllNotes() {
        return (List<Note>) em.createNamedQuery(Note.Q_GET_ALL_NOTES).getResultList();
    }

    public List<Passanger> getAllPassangers() {
        return (List<Passanger>) em.createNamedQuery(Passanger.Q_GET_ALL_PASSENGERS).getResultList();
    }

    public List<InactivePassanger> getAllInactives() {
        return (List<InactivePassanger>) em.createNamedQuery(InactivePassanger.Q_GET_ALL_INACTIVES).getResultList();
    }


    @Override
    public List<Position> getAllPositions() {
        return (List<Position>) em.createNamedQuery(Position.Q_GET_ALL_POSITIONS).getResultList();
    }

    public List<Route> getAllRoutes() {
        return (List<Route>) em.createNamedQuery(Route.Q_GET_ALL_ROUTES).getResultList();
     }

    public List<Station> getAllStations() {
        return (List<Station>) em.createNamedQuery(Station.Q_GET_ALL_STATIONS).getResultList();
    }
}
