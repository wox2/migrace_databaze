/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver.utils;

import cz.ctu.fee.murinrad.aos.common.utils.ConnectionToDB;
import cz.ctu.fee.murinrad.aos.common.exceptions.DatabaseException;
import cz.ctu.fee.murinrad.aos.common.exceptions.MalformedParameterException;
import cz.ctu.fee.murinrad.aos.common.exceptions.PassengerDoesNotExistException;
import cz.ctu.fee.murinrad.aos.common.exceptions.ChangeTicketException;
import cz.ctu.fee.murinrad.aos.common.exceptions.TicketDoesNotExistException;
import cz.ctu.fee.murinrad.aos.common.exceptions.SeatIsAlreadyReservedException;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Flight;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Passenger;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Seat;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import cz.ctu.fee.murinrad.aos.common.exceptions.TimeSpaceException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.apache.commons.lang.time.DateUtils;

/**
 * A class for database operations
 * All methods are pretty self explanatory
 * @author Radovan Murin
 */
public class DatabaseOperations {

    private ConnectionToDB connToDB;
    private boolean connected = false;
    private String SELECT_ALL_FLIGHTS_SQL = "Select * from flights";
    //Column labels
    private final String FLIGHT_DEPARTURE_APT_C_LABEL = "DepartureAirport";
    private final String FLIGHT_ARRIVAL_APT_C_LABEL = "ArrivalAirport";
    private final String FLIGHT_FLIGHT_NUMBER_C_LABEL = "FlightNumber";
    private final String FLIGHT_DEPARTURE_DATE_C_LABEL = "Departure";
    private final String FLIGHT_ARRIVAL_DATE_C_LABEL = "Arrival";
    private final String FLIGHT_BASE_FARE_C_LABEL = "BaseFare";
    private final String FLIGHT_ID_C_LABEL = "id";
    private final String SEATS_FLIGHTS_ID_C_LABEL = "Flights_id";
    private final String SEATS_SEAT_NUMBER_C_LABEL = "Seatnumber";
    private final String TICKET_TICKET_NUMBER_C_LABEL = "TicketNumber";
    private final String TICKET_FLIGHTS_ID_C_LABEL = "Flights_id";
    private final String TICKET_STATUS_C_LABEL = "Status";
    private final String TICKET_SEATS_SEATNUMBER_C_LABEL = "Seats_Seatnumber";
    private final String TICKET_TOTAL_PRICE_C_LABEL = "TotalPrice";
    private final String PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL = "Passengers_idPassengers";
    private final String PASSENGERSHASTICKET_TICKET_TICKET_NUMBER_C_LABEL = "Ticket_TicketNumber";
    private final String PASSENGERS_ID_DOC_NUMBER_C_LABEL = "IDdocNumber";
    private final String PASSENGERS_ID_C_LABEL = "idPassengers";
    private final String PASSENGERS_NAME_C_LABEL = "Name";
    private final String PASSENGERS_SURNAME_C_LABEL = "Surname";
    private final String PASSENGERS_SEX_C_LABEL = "Sex";
    private final String PASSENGERS_TITLE_C_LABEL = "Title";

    public DatabaseOperations() {
        try {
            connToDB = new ConnectionToDB("jdbc/bookingdb", "jdbc:mysql://localhost:3306/", "bookingdb", "root", "admin");
        } catch (NamingException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  connToDB = GetConnection.getConnection();
        connected = true;

    }

    /**
     * Lists all flights that are in the database.
     *
     * @return a collection of flights
     */
    public Collection<Flight> listAllFlights() {
        Collection<Flight> ret = new ArrayList<Flight>();
        try {
            Statement s = connToDB.getStatement();
            System.out.println("created statement");
            //  s.execute("use bookingdb");
            ResultSet rs = s.executeQuery(SELECT_ALL_FLIGHTS_SQL);
            System.out.println("executed");

            System.out.println("gone next");
            while (rs.next()) {
                Flight f = new Flight(rs.getString(FLIGHT_DEPARTURE_APT_C_LABEL),
                        rs.getString(FLIGHT_ARRIVAL_APT_C_LABEL), rs.getString(FLIGHT_FLIGHT_NUMBER_C_LABEL),
                        rs.getDate(FLIGHT_DEPARTURE_DATE_C_LABEL), rs.getDate(FLIGHT_ARRIVAL_DATE_C_LABEL),
                        rs.getInt(FLIGHT_ID_C_LABEL), rs.getInt(FLIGHT_BASE_FARE_C_LABEL));
                ret.add(f);
                System.out.println(f);

            }
            System.out.println("ended");
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;

    }

    public Collection<Seat> getFreeSeats(Integer FlightID) throws DatabaseException {
        Collection<Seat> ret = new ArrayList<Seat>();
        Statement s;
        try {
            s = connToDB.getStatement();
            String q = "select * from seats "
                    + "where (Flights_id='" + FlightID + "' && seatnumber not in "
                    + "(select Seats_seatnumber from ticket where Flights_id='" + FlightID + "'));"; 
            System.out.println("Free seat q = ");
            System.out.println(q);
            ResultSet rs = s.executeQuery(q);
            
           
            while (rs.next()) {
                Seat seat = new Seat(rs.getInt(SEATS_SEAT_NUMBER_C_LABEL), rs.getInt(SEATS_FLIGHTS_ID_C_LABEL));
                System.out.println(seat);
                ret.add(seat);

            }
            s.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Cannot connect to the DB", ex);
        }
        return ret;
    }

 
    public Collection<Flight> getFlightByDate(String departureAPT, String arrivalAPT, Long arrivalDate, Long departureDate)
            throws DatabaseException, MalformedParameterException {
        if (!(arrivalDate == null ^ departureDate == null)) {
            throw new MalformedParameterException("This method needs either an arrival Date of a departureDate", new Exception());
        }
        Date date;
        String timeRestriction = "";
        if (arrivalDate == null) {
            date = new Date(departureDate);
            timeRestriction = "&& DATE_FORMAT(Departure, \"%Y-%m-%d\")='" + date.toString() + "'";
        } else {
            date = new Date(arrivalDate);
            timeRestriction = "&& DATE_FORMAT(Arrival, \"%Y-%m-%d\")='" + date.toString() + "'";
        }
        Collection<Flight> ret = new ArrayList<Flight>();
        Statement s;
        try {
            s = connToDB.getStatement();


            String query = "select * from flights where DepartureAirport='" + departureAPT + "'&& ArrivalAirport='" + arrivalAPT + "' " + timeRestriction + ";";
            System.out.println("Big ass querry =" + query);
            ResultSet rs = s.executeQuery(query);
            System.out.println("I got a esult set");
            while (rs.next()) {
                System.out.println("1");
                Flight f = constructFlightFromRS(rs);
                ret.add(f);
            }
            System.out.println("2");
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DatabaseException("Cannot connect to the DB", ex);
        }
        return ret;
    }

    public Ticket CreateTicket(Integer passengerID, Integer flightID, Integer seatNumber) throws DatabaseException, SeatIsAlreadyReservedException,TimeSpaceException {
        Statement s;
        ResultSet rs;
        Integer ticketID;


        try {
            s = connToDB.getStatement();
        } catch (SQLException ex) {
            throw new DatabaseException("Connection to DB lost.", ex);
        }
        try {

            s.execute("START TRANSACTION;");
            Double price = calculatePrice(flightID, 1);
            if (!isSeatAvailable(flightID, seatNumber)) {
                throw new SeatIsAlreadyReservedException("The seat specified has been reserved", new RuntimeException());
            }
            Flight f = getFlightByID(flightID);
            Calendar c = Calendar.getInstance();
            c.clear();
            c.setTimeInMillis(f.getDeparture());
            Calendar today = Calendar.getInstance();
            Integer difference = (int)((c.getTimeInMillis()-today.getTimeInMillis())/(3600*24*1000));
            if(difference<0) throw new TimeSpaceException("One cannot reserve a flight from the past", new RuntimeException());
            s.execute("insert into ticket (Flights_id,Status,Seats_SeatNumber,TotalPrice) values ('" + flightID + "','0','" + seatNumber + "','" + price + "');");

            ticketID = getLastInsertID();
            if (ticketID == -1) {
                s.execute("ROLLBACK;");
                throw new DatabaseException("Error occured during processing, Transaction rollback ", new RuntimeException());
            }
            s.execute("insert into"
                    + " passengers_has_ticket (" + PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL + ","
                    + "" + PASSENGERSHASTICKET_TICKET_TICKET_NUMBER_C_LABEL + ") values ('"
                    + passengerID + "','" + ticketID + "');");
            
        } catch (SQLException ex) {
            try {
                s.execute("ROLLBACK;");
            } catch (SQLException ex1) {
                throw new DatabaseException("Connection to DB lost. Rollback unreliable", ex);
            }
            throw new DatabaseException("Error occured during processing, Transaction rollback", ex);
        }
        try {
            s.execute("COMMIT;");
            
        } catch (SQLException ex) {
            throw new DatabaseException("Error occured during processing,Transaction rollback", new RuntimeException());
        }
        try {
            rs = s.executeQuery("select * from ticket where Flights_id='" + flightID + "' && TicketNumber='" + ticketID + "';");
            
            Ticket t = constructTicketFromRS(rs);
            s.close();
            return t;
        } catch (SQLException ex) {
            throw new DatabaseException("DB connection lost", ex);
        }



    }

    private boolean isSeatAvailable(Integer flightID, Integer seatNo) throws DatabaseException {
        Collection<Seat> cs = getFreeSeats(flightID);
        Iterator<Seat> itS = cs.iterator();
        Seat a;
        Seat b = new Seat(seatNo, flightID);
        while (itS.hasNext()) {
            a = itS.next();
            if (a.equals(b)) {
                return true;
            }

        }

        return false;

    }

    public Collection<Ticket> getTicketsForPassenger(Integer passengerID) throws DatabaseException {
        Statement s;
        Collection<Ticket> ret = new ArrayList<Ticket>();
        try {
            s = connToDB.getStatement();
            ResultSet rs = s.executeQuery("select * from ticket where " + TICKET_TICKET_NUMBER_C_LABEL + " in "
                    + "(select " + PASSENGERSHASTICKET_TICKET_TICKET_NUMBER_C_LABEL + " from passengers_has_ticket "
                    + "where " + PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL + "='" + passengerID + "');");
            Ticket t;
            while (rs.next()) {
                t = constructTicketFromRS(rs);
                ret.add(t);
            }
            s.close();
        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }
        return ret;

    }

    public Ticket getTicketForAFlightForAPassenger(Integer passengerID, Integer ticketID) throws DatabaseException, TicketDoesNotExistException {
        Statement s;
        Ticket ret = null;
        try {
            s = connToDB.getStatement();
            String q = "select * from ticket where " + TICKET_TICKET_NUMBER_C_LABEL + ""
                    + " in (select " + PASSENGERSHASTICKET_TICKET_TICKET_NUMBER_C_LABEL + " from "
                    + "passengers_has_ticket where " + PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL + "='" + passengerID + "') && "+TICKET_TICKET_NUMBER_C_LABEL+"='"+ticketID+"' ";
            System.out.println(q);
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {
                ret = constructTicketFromRS(rs);
                

            }

            if (ret == null) {
                throw new TicketDoesNotExistException("The combination of FlightID and passengerID does not exist", new RuntimeException());
            }
            s.close();
            return ret;
        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }


    }

    public Ticket getSpecificTicketByID(Integer ticketID) throws DatabaseException, TicketDoesNotExistException {
        Statement s;
        Ticket t;
        try {
            s = connToDB.getStatement();
            String q = "select * from ticket where " + TICKET_TICKET_NUMBER_C_LABEL + " = '" + ticketID + "';";
            System.out.println("getSpecificTicketByID querry = "+q);
            ResultSet rs = s.executeQuery(q);
            t = constructTicketFromRS(rs);
            s.close();

        } catch (SQLException ex) {
            if(ex.getLocalizedMessage().contains(" Illegal operation on empty result set.")) {
                throw new TicketDoesNotExistException("The ticket with id "+ticketID+" does not exist.", new RuntimeException());
            }
            throw new DatabaseException("A database exception has occured.", ex);
        }
        if (t == null) {
            throw new TicketDoesNotExistException("A ticket with id='" + ticketID + "' does not exist", new RuntimeException());
        }

        return t;

    }

    public Double calculatePrice(Integer flightID, Integer discount) throws DatabaseException {
        Double ret = -1.0;
        System.out.println("Calculating price for flight ID=" + flightID);
        try {
            Statement s = connToDB.getStatement();
            System.out.println("calculating price for ID " + flightID);
            Flight f = getFlightByID(flightID);
            ret = BusinessPolicy.getTotalFare(f, discount);
            s.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Database connection lost", ex);
        }
        return ret;
    }

    private Flight constructFlightFromRS(ResultSet rs) throws SQLException {
        Flight f = new Flight(rs.getString(FLIGHT_DEPARTURE_APT_C_LABEL),
                rs.getString(FLIGHT_ARRIVAL_APT_C_LABEL), rs.getString(FLIGHT_FLIGHT_NUMBER_C_LABEL),
                rs.getDate(FLIGHT_DEPARTURE_DATE_C_LABEL), rs.getDate(FLIGHT_ARRIVAL_DATE_C_LABEL),
                rs.getInt(FLIGHT_ID_C_LABEL), rs.getInt(FLIGHT_BASE_FARE_C_LABEL));
        return f;

    }

    private Ticket constructTicketFromRS(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst()) {
            rs.next();
        }
        Ticket t = new Ticket(rs.getInt(TICKET_TICKET_NUMBER_C_LABEL), rs.getInt(TICKET_FLIGHTS_ID_C_LABEL),
                rs.getInt(TICKET_STATUS_C_LABEL), rs.getInt(TICKET_SEATS_SEATNUMBER_C_LABEL),
                rs.getDouble(TICKET_TOTAL_PRICE_C_LABEL));
        return t;
    }

    private Passenger constructPassengerFromRS(ResultSet rs) throws SQLException {
        if (rs.isBeforeFirst()) {
            rs.next();
        }
        Passenger p = new Passenger(rs.getString(PASSENGERS_NAME_C_LABEL),
                rs.getString(PASSENGERS_SURNAME_C_LABEL),
                rs.getString(PASSENGERS_ID_DOC_NUMBER_C_LABEL),
                rs.getString(PASSENGERS_SEX_C_LABEL),
                rs.getString(PASSENGERS_TITLE_C_LABEL),
                rs.getInt(PASSENGERS_ID_C_LABEL));
        return p;
    }

    public Integer passengerExists(String passengerIdentifier) {

        Statement s;
        try {
            s = connToDB.getStatement();
            ResultSet rs = s.executeQuery("select " + PASSENGERS_ID_C_LABEL + " from passengers where IDdocNumber='" + passengerIdentifier + "';");
            
            if (rs.next()) {
                return rs.getInt(PASSENGERS_ID_C_LABEL);
            }
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Creates a pasenger in the database, can return null but shouldnt
     *
     * @param p
     * @return the passenger that has been created
     * @throws DatabaseException
     */
    public Passenger createPassenger(Passenger p) throws DatabaseException {
        try {
            Statement s;
            s = connToDB.getStatement();
            s.execute("START TRANSACTION;");
            String insert = "insert into passengers (" + PASSENGERS_ID_DOC_NUMBER_C_LABEL + ","
                    + "" + PASSENGERS_NAME_C_LABEL + "," + PASSENGERS_SURNAME_C_LABEL + ""
                    + "," + PASSENGERS_SEX_C_LABEL + "," + PASSENGERS_TITLE_C_LABEL + ")"
                    + " values ('" + p.getIdDocNumber() + "','" + p.getName() + "','" + p.getSurname() + "','"
                    + p.getSex() + "','" + p.getTitle() + "');";
            System.out.println(insert);
            s.execute(insert);
            s.execute("COMMIT;");
            Integer generatedPassengerId = getLastInsertID();
            if (generatedPassengerId == -1) {
                throw new DatabaseException("Failed to create new passenger", new RuntimeException());
            }

            ResultSet rs = s.executeQuery("select * from passengers where " + PASSENGERS_ID_C_LABEL + "='" + generatedPassengerId + "';");
            
            p = constructPassengerFromRS(rs);
            s.close();
            return p;

        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);

        }
    }

    public Collection<Flight> getFlightsForPassenger(Integer id) throws DatabaseException {
        Statement s;
        Collection<Flight> ret = new ArrayList<Flight>();
        try {
            s = connToDB.getStatement();
            String query = "select * from"
                    + " (select  Flights_id from (select Ticket_TicketNumber from passengers_has_ticket where Passengers_idPassengers='" + id + "') as x "
                    + "join ticket on(x.Ticket_TicketNumber=ticket.TicketNumber)) as fIds "
                    + "join flights on(fIds.Flights_id=flights.id)";
            ResultSet rs = s.executeQuery(query);
            
            Flight f;
            while (rs.next()) {
                f = constructFlightFromRS(rs);
                ret.add(f);
            }
            s.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Database went wonkers", ex);
        }
        return ret;

    }

    public Flight getFlightByID(Integer id) throws DatabaseException {
        Statement s;
        Flight f = null;
        try {
            s = connToDB.getStatement();
            String querry = "Select * from flights where id='" + id + "'";
            ResultSet rs = s.executeQuery(querry);
            
            while (rs.next()) {
                f = constructFlightFromRS(rs);
            }
            s.close();
            return f;
        } catch (SQLException ex) {

            throw new DatabaseException("Database went wonkers", ex);
        }
    }

    public Integer cancelReservation(String passengerIDDocNumber, Integer flightID) throws DatabaseException,
            TicketDoesNotExistException, PassengerDoesNotExistException {
        Integer passengerID = passengerExists(passengerIDDocNumber);
        if (passengerID == -1) {
            throw new PassengerDoesNotExistException("The passenger with the specified ID document does not exist",
                    new RuntimeException());
        }
        Ticket ticket = getTicketForAFlightForAPassenger(passengerID, flightID);
        Integer ret = isCancellable(ticket);
        if (ret != 0) {
            System.out.println(ticket.getFlightID());
            System.out.println(ret);
            return ret;
        }
        Statement s = null;
        try {
            s = connToDB.getStatement();
            String querryDeleteAssociation = "Delete from passengers_has_ticket where Ticket_TicketNumber='" + ticket.getTicketNumber() + "'";
            String querryDeleteTicket = "Delete from ticket where TicketNumber='" + ticket.getTicketNumber() + "'";
            s.execute("START TRANSACTION;");
            s.execute(querryDeleteAssociation);
            System.out.println(querryDeleteAssociation);
            s.execute(querryDeleteTicket);
            System.out.println(querryDeleteTicket);
            s.execute("COMMIT;");
            s.close();
        } catch (SQLException ex) {
            try {
                if (s != null) {
                    s.execute("ROLLBACK");
                }
            } catch (SQLException ex1) {
                throw new DatabaseException("A database exception has occured., rollback failed", ex);
            }
            throw new DatabaseException("A database exception has occured.", ex);
        }
        return ret;
    }

    public Integer isCancellable(Integer ticket) throws DatabaseException, TicketDoesNotExistException {
        Ticket t = getSpecificTicketByID(ticket);
        Flight f = getFlightByID(t.getFlightID());
        return BusinessPolicy.isCancelable(t, f);
    }

    public Integer isCancellable(Ticket ticket) throws DatabaseException {
        Flight f = getFlightByID(ticket.getFlightID());
        return BusinessPolicy.isCancelable(ticket, f);
    }

    /*
     * public Passenger getPassenger(String idDocNumber) throws
     * DatabaseException{ try { Statement s = connToDB.getStatement(); ResultSet
     * rs = s.executeQuery("select * from passengers where
     * "+PASSENGERS_ID_DOC_NUMBER_C_LABEL+"='"+idDocNumber+"'"); Passenger p =
     * constructPassengerFromRS(rs); return p; } catch (SQLException ex) { throw
     * new DatabaseException("A database exception has occured.", ex); }
     */
    public Ticket changeTicket(Integer ticketID, Integer newFlightID, Integer newSeatNumber) throws DatabaseException, TicketDoesNotExistException, ChangeTicketException {
        Statement s;
        Ticket oldT, newT;
        try {
            s = connToDB.getStatement();
            oldT = getSpecificTicketByID(ticketID);
            if (oldT.getStatus() != 0) {
                throw new ChangeTicketException("Ticket cannot be changed once paid for", new RuntimeException());
            }
            Flight f = getFlightByID(ticketID);
            Double newPrice = calculatePrice(newFlightID, 1);
            String updateTicket = "UPDATE `ticket` SET "
                    + "`" + TICKET_FLIGHTS_ID_C_LABEL + "`='" + newFlightID + "'"
                    + ", `" + TICKET_SEATS_SEATNUMBER_C_LABEL + "`='" + newSeatNumber + "', "
                    + "`" + TICKET_TOTAL_PRICE_C_LABEL + "`='" + newPrice + "'"
                    + " WHERE `" + TICKET_TICKET_NUMBER_C_LABEL + "`='" + ticketID + "';";
            s.execute(updateTicket);
            newT = getSpecificTicketByID(ticketID);
            s.close();
            return newT;

        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }
        
    }

    private Integer getLastInsertID() throws SQLException {
        Statement s = connToDB.getStatement();
        ResultSet rs = s.executeQuery("select LAST_INSERT_ID()");
        
        if (rs.next()) {
            return rs.getInt(1);
        }
        s.close();
        return -1;


    }

    public void confirmPaid(Integer ticketID) throws DatabaseException {
        Statement s;
        try {
            s = connToDB.getStatement();
            s.execute("UPDATE `ticket` SET `" + TICKET_STATUS_C_LABEL + "`='1' WHERE `" + TICKET_TICKET_NUMBER_C_LABEL + "`='"+ticketID+"';");
            s.close();
        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }
    }

    public Passenger getPassengerByTicket(Integer ticketID) throws DatabaseException, TicketDoesNotExistException {
        Statement s;
        try {
            s = connToDB.getStatement();
            String q = "select " + PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL + " from passengers_has_ticket"
                    + " where " + PASSENGERSHASTICKET_TICKET_TICKET_NUMBER_C_LABEL + "='" + ticketID + "';";
            System.out.println("getOPassengerByTicketQ = "+q);
            ResultSet rs = s.executeQuery(q);
            
            if (rs.next()) {
                Integer passegerID = rs.getInt(PASSENGERSHASTICKET_PASSENGERS_IDPASSENGERS_C_LABEL);
                Passenger p;
                try {
                    p = getPassengerByID(passegerID);
                } catch (PassengerDoesNotExistException ex) {
                    throw new DatabaseException("A ticket has been found with an non existant passenger, IRRECOVERABLE ERROR", new RuntimeException());
                }
s.close();
                return p;
            } else {
                throw new TicketDoesNotExistException("The ticket does not exist", new RuntimeException());
            }
        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }
    }

    public Passenger getPassengerByID(Integer passengerID) throws DatabaseException, PassengerDoesNotExistException {
        Statement s;
        try {
            s = connToDB.getStatement();
            ResultSet rs = s.executeQuery("select * from passengers where " + PASSENGERS_ID_C_LABEL + "='" + passengerID + "'");
            
            if (rs.next()) {
                return constructPassengerFromRS(rs);
            } else {
                throw new PassengerDoesNotExistException("The passenger does not exist.", new RuntimeException());
            }
        } catch (SQLException ex) {
            throw new DatabaseException("A database exception has occured.", ex);
        }

    }

    public void close() {
        connToDB.closeConnection();
    }
}
