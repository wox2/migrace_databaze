/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver;

import cz.ctu.fee.murinrad.bookingserver.datacontainers.TicketPackage;
import cz.ctu.fee.murinrad.bookingserver.datacontainers.GetTicketAnswer;
import cz.ctu.fee.murinrad.aos.common.exceptions.DatabaseException;
import cz.ctu.fee.murinrad.aos.common.exceptions.SeatIsAlreadyReservedException;
import cz.ctu.fee.murinrad.aos.common.exceptions.PassengerDoesNotExistException;
import cz.ctu.fee.murinrad.aos.common.exceptions.ChangeTicketException;
import cz.ctu.fee.murinrad.aos.common.exceptions.TicketIsNotCancellableException;
import cz.ctu.fee.murinrad.aos.common.exceptions.TicketDoesNotExistException;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Seat;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Passenger;
import cz.ctu.fee.murinrad.aos.common.datacontainers.SeatCollection;
import cz.ctu.fee.murinrad.aos.common.exceptions.TimeSpaceException;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 * Flight Booker Service This service provides the interface server access to
 * the booking database and enables bookings of flights.
 * @author Radovan Murin
 */
@WebService
public interface FlightBookerIface {

    /**
     * Returns all free seats on a flight
     *
     * @param flightID the flight ID
     * @return a collection of seats that are free
     * @throws DatabaseException Thrown if the connection to the database has
     * been lost or an error occurred during the transaction
     */
    public SeatCollection getFreeSeats(@WebParam(name = "flightID") Integer flightID) throws DatabaseException;

    /**
     * Books a seat for the passenger.
     *
     * @param passenger the passenger that is reserving the seat
     * @param seatID the seat ID to be reserved
     * @param flightID the flight ID to be reserver
     * @return the ticket-reservation for the flight.
     * @throws DatabaseException Thrown if the connection to the database has
     * been lost or an error occurred during the transaction
     * @throws SeatIsAlreadyReservedException Thrown when the specific seat has
     * been already reserved.
     */
    public Ticket bookFlight(@WebParam(name = "Passenger") @XmlElement(required = true) Passenger passenger, @WebParam(name = "seatID") @XmlElement(required = true) Integer seatID, @WebParam(name = "flightID") Integer flightID) throws DatabaseException, SeatIsAlreadyReservedException,TimeSpaceException;

    /**
     * Cancels the specific reservation. The user needs their confidential ID
     * document number and the flight ID.
     *
     * @param passengerIDDocNumber a string of an ID document
     * @param flightID the flight ID that is to be canceled.
     * @throws DatabaseException Thrown if the connection to the database has
     * been lost or an error occurred during the transaction
     * @throws TicketDoesNotExistException
     * @throws PassengerDoesNotExistException
     * @throws TicketIsNotCancellableException
     */
    public void cancelReservation(@WebParam(name = "passengerIDDoc") String passengerIDDocNumber, @WebParam(name = "flightID") Integer flightID) throws DatabaseException, TicketDoesNotExistException, PassengerDoesNotExistException, TicketIsNotCancellableException;

    /**
     *Returns all the tickets that belong to the specified passenger. If the passenger has no tickets, it returns an empty collection.
     * @param passengerIDDocNumber the passenger ID number.
     * @return A package that contains tickets
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     * @throws PassengerDoesNotExistException Thrown if the passenger with the specified ID doc number does NOT exist.
     */
    public TicketPackage getTicketsForPassenger(@WebParam(name = "passengerIDDoc") String passengerIDDocNumber) throws DatabaseException, PassengerDoesNotExistException;

    /**
     * Changes and unpaid reservation.
     * @param ticketID the ticket ID to be changed
     * @param newFlightID the new flight ID
     * @param newSeatNumber the new seat ID
     * @return the altered ticket
     * @throws TicketDoesNotExistException thrown if a ticket with the specific ID does not exist.
     * @throws SeatIsAlreadyReservedException thrown if the new seat has been already reserved
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     * @throws TicketIsNotCancellableException thrown if the ticket cannot be changed due to the fact that the time limit was exceeded.
     * @throws ChangeTicketException Thrown if the ticked cannot be changed.
     */
    public Ticket ChangeReservation(@WebParam(name = "ticketID") Integer ticketID,
            @WebParam(name = "newFlightID") Integer newFlightID,
            @WebParam(name = "newSeatNumber") Integer newSeatNumber)
            throws TicketDoesNotExistException, SeatIsAlreadyReservedException, DatabaseException, TicketIsNotCancellableException, ChangeTicketException;

    /**
     * Sets a flag for the ticket that it has been paid/is to be paid by bank transfer.
     * @param TicketID the ticket ID
     * @throws TicketDoesNotExistException thrown if the ticket does not exist
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     */
    public void confirmTicketPaid(@WebParam(name = "ticketID") Integer TicketID) throws TicketDoesNotExistException, DatabaseException;

    /**
     * Returns the specific ticket with the specific ticket number.
     * @param ticketID the ticket ID
     * @return a ticket
     * @throws TicketDoesNotExistException
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     */
    public GetTicketAnswer getSpecificTicket(@WebParam(name = "ticketID") Integer ticketID) throws TicketDoesNotExistException, DatabaseException;

    /**
     * Returns the owner of the ticket
     * @param ticketID the ticket ID
     * @return Passenger data
     * @throws TicketDoesNotExistException thrown if the ticket does not exist.
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     */
    public Passenger getPassengerDataForATicket(@WebParam(name = "ticketID") Integer ticketID) throws TicketDoesNotExistException, DatabaseException;
}
