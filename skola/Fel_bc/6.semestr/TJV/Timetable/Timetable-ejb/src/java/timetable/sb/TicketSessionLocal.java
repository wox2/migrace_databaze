/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package timetable.sb;

import java.util.Date;
import javax.ejb.Local;
import timetable.model.Link;
import timetable.model.Passanger;
import timetable.model.Station;
import timetable.model.Ticket;

/**
 *
 * @author woxie
 */
@Local
public interface TicketSessionLocal {

    void init(Long ticketId, Passanger passanger);

    Ticket getCurrent();

    void save () throws Exception;

    void discard();

    public Integer getDistanceByLink(Link link);
    public Integer getTotalDistance();
    public Date getJourneyTimeByLink(Link link);
    public Date getTotalJourneyTime();

    public boolean isReadOnly();
    public boolean openForRead(Ticket ticket);

    public Integer getTotalPrice();

    public timetable.model.Station getDepartingStationByLink(timetable.model.Link link);
    public timetable.model.Station getArrivingStationByLink(timetable.model.Link link);

    public void addLinkToTicket(Link link, Station departing, Station arriving, Passanger passanger, Date date);
}
