/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.sb;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import timetable.model.Link;
import timetable.model.Passanger;
import timetable.model.Position;
import timetable.model.Route;
import timetable.model.Station;
import timetable.model.Ticket;

/**
 *
 * @author woxie
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class TicketSession implements TicketSessionLocal {

    private Ticket current = null;

    private boolean editable = false;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction utx;

    @Resource
    private SessionContext context;


    @Override
    public void init(Long ticketId, Passanger passanger) {
        try {
            if (utx.getStatus() == Status.STATUS_NO_TRANSACTION) {
                utx.begin();
            }
            if (ticketId == null) {
                current = new Ticket();
                editable = true;
                current.setPassanger(passanger);
            } else {
                current = (Ticket) em.find(Ticket.class, ticketId);
                if (!passanger.equals(current.getPassanger())) {
                    throw new RuntimeException("Access to this ticket forbidden.");
                }
                /* TODO if (current == null) {
                    throw new NullPointerException();
                }*/
            }
        } catch (javax.transaction.NotSupportedException ex) {
            Logger.getLogger(TicketSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(TicketSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isReadOnly() {
        return !editable;
    }

    public boolean openForRead(Ticket ticket) {
        if (current != null && editable) return false;
        current = ticket;
        return true;
    }

    @Override
    public Ticket getCurrent() {
        return current;
    }

    @Override
    public void save() throws Exception {
        if (!editable) throw new RuntimeException("The ticket is read only.");
        em.persist(current);
        Passanger passanger = current.getPassanger();
        passanger.getTickets().add(current);
        em.merge(passanger);
        utx.commit();
        editable = false;
        current = null;
    }

    @Override
    public void discard() {
        if (!editable) throw new RuntimeException("The ticket is read only.");
        try {
            utx.rollback();
            editable = false;
            current = null;
        } catch (IllegalStateException ex) {
            Logger.getLogger(TicketSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TicketSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(TicketSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Station getDepartingStationByLink(Link link) {
        if (current == null) return null;
        List<Link> links = current.getLinks();
        int idx = links.indexOf(link);
        if (idx < 0) return null;
        if (idx == 0) return current.getFromStation();
        else {
            Link prev = links.get(idx - 1);
            List<Station> common = getCommonStations(link.getRoute(), prev.getRoute());
            if (common.isEmpty()) return null;
            return common.get(0);
        }
    }

    @Override
    public Station getArrivingStationByLink(Link link) {
        if (current == null) return null;
        List<Link> links = current.getLinks();
        int idx = links.indexOf(link);
        if (idx < 0) return null;
        if (idx == links.size() - 1) return current.getToStation();
        else {
            Link next = links.get(idx + 1);
            List<Station> common = getCommonStations(link.getRoute(), next.getRoute());
            if (common.isEmpty()) return null;
            return common.get(0);
        }
    }

    public Integer getDistanceByLink(Link link) {
        TimetableCenterLocal tc = (TimetableCenterLocal) context.lookup("java:global/Timetable/Timetable-ejb/TimetableCenter!timetable.sb.TimetableCenterLocal");
        try {
            return tc.getDistance(link, getDepartingStationByLink(link), getArrivingStationByLink(link));
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public Integer getTotalDistance() {
        if (current == null) return null;
        int distance = 0;
        for (Link link : current.getLinks()) {
            distance += getDistanceByLink(link);
        }
        return distance;
    }

    public Date getJourneyTimeByLink(Link link) {
        TimetableCenterLocal tc = (TimetableCenterLocal) context.lookup("java:global/Timetable/Timetable-ejb/TimetableCenter!timetable.sb.TimetableCenterLocal");
        try {
            return tc.getJourneyTime(link, getDepartingStationByLink(link), getArrivingStationByLink(link));
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public Date getTotalJourneyTime() {
        TimetableCenterLocal tc = (TimetableCenterLocal) context.lookup("java:global/Timetable/Timetable-ejb/TimetableCenter!timetable.sb.TimetableCenterLocal");
        if (current == null) return null;
        List<Link> links = current.getLinks();
        if (links.isEmpty()) return new Date(0);
        Date pos1 = tc.getDepartureTime(links.get(0), current.getFromStation());
        Date pos2 = tc.getDepartureTime(links.get(links.size() - 1), current.getToStation());
        return tc.subTimes(pos2, pos1);
    }

    private List<Station> getCommonStations(Route route1, Route route2) {
        List<Position> list1 = route1.getPositions();
        List<Position> list2 = route2.getPositions();
        Route r = null;
        List<Station> res = new LinkedList<Station>();
        for (Position p : list1) {
            List<Position> pos = p.getStation().getPositions();
            for (Position px : pos) {
                if (list2.contains(px)) res.add(p.getStation());
            }
        }
        return res;
    }

    public Integer getTotalPrice() {
        if (current == null) return null;
        List<Link> list = current.getLinks();
        int price = 0;
        for (Link l : list) {
            price += getDistanceByLink(l) * l.getLinkType().getCostPerKM();
        }
        return price;
    }

    @Override
    public void addLinkToTicket(Link link, Station departing, Station arriving, Passanger passanger, Date date) {
        //if (!editable) throw new RuntimeException("The ticket is read only.");
        if (current == null || isReadOnly()) {
            init(null, passanger);
            current.setFromStation(departing);
            current.setDateOfValidity(date);
        } else if (current.getLinks().size() > 0 && !departing.equals(current.getToStation())) {
            throw new RuntimeException("Not a transfer connection: " + current.getToStation() + " expected, but " + departing + " got.");
        } else if (!date.equals(current.getDateOfValidity())) {
            throw new RuntimeException("Cannot change date of validity.");
        }
        List<Link> links = current.getLinks();
        if (links == null) {
            links = new ArrayList<Link>();
        }
        boolean remove = false;
        for (Link l : links) {
            remove = false;
            if (l.equals(link)) {
                remove = true;
            }
            for (Position p : l.getRoute().getPositions()) {
                if (arriving.equals(p.getStation())) {
                    remove = true;
                }
            }
            if (remove) {
                int idx = links.indexOf(l);
                for (int i = idx + 1; i < links.size(); i++) {
                    links.remove(i);
                }
                break;
            }
        }
        if (!remove) {
            links.add(link);
        }
        current.setLinks(links);
        current.setToStation(arriving);
    }
}
