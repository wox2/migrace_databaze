/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.interfaceserver;

import cz.ctu.fee.murinrad.interfaceserver.exceptions.SearchException;
import cz.ctu.fee.murinrad.bookingserver.DatabaseException;
import cz.ctu.fee.murinrad.bookingserver.FlightCollection;
import cz.ctu.fee.murinrad.bookingserver.Passenger;
import cz.ctu.fee.murinrad.bookingserver.SeatCollection;
import cz.ctu.fee.murinrad.bookingserver.Ticket;
import cz.ctu.fee.murinrad.bookingserver.TicketPackage;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.CancelReservationException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.ChangeReservationException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.PaymentException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.PrintingException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.ReservationException;
import cz.ctu.fee.murinrad.paymentserver.CardPayment;
import cz.ctu.fee.murinrad.paymentserver.CashPayment;
import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A frontier for booking, searching, paying printing of a glorious booking system.
 * @author Radovan Murin
 */

@WebService
public interface InterfaceServiceIface {
    
    
     /**
     * Main flight method. Finds a flight according to the data given in the params.
     * WARNING arrivalTime and departure time must not be present at the same time.
     * @param arrivalAPT the IATA code of the arrival airport.
     * @param departureAPT the IATA code of the departure airport.
     * @param arrivalTime the arrival time as a unix timestamp.
     * @param departureTime the departure time as a unix timestamp.
     * @throws SearchException thrown if backend is unavailable or the backend threw an exception that was not handled.
     * @return A collection of flights that adhere to the params.
     */
    public FlightCollection findFlight(@WebParam(name="arrivalAPT") String arrivalAPT,@WebParam(name="departureAPT")String departureAPT,
            @WebParam(name="arrivalTime")Long arrivalTime,@WebParam(name="departureTime")Long departureTime) throws SearchException;
    
    /**
     * Makes a reservation for a specific passenger, specific flight and specific seat. If no seat number is specified then 
     * the system gives the first free seat it comes across.
     * @param passenger the Passenger object representing a passenger. The passenger can have just an ID number in which 
     * case it will be looked up by the backend, or everything but the id number if a new passenger is to be created.
     * @param seatNumber the flight ID, obtained by looking for the specific flight
     * @param flightID the seat number, can be null
     * @return A reservation confirmation with information for the flight
     * @throws ReservationException thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public Ticket bookFlight(
            @WebParam(name = "passenger") Passenger passenger, 
            @WebParam(name = "seatNumber") Integer seatNumber, 
            @WebParam(name = "flightID") Integer flightID) throws ReservationException;
    
    /**
     * Changes the reservation of a ticket if possible. Tickets cant be changed once paid for, or there are less than 3 days to the flight.
     * @param ticketID the ticket ID 
     * @param newFlightID the new flight ID
     * @param newSeatID the new seat, does not need to be specified, if not specified the system assigns a seat that it firs encounters.
     * @return
     * @throws ChangeReservationException thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public Ticket changeReservation(@WebParam(name = "ticketID") Integer ticketID, 
            @WebParam(name = "newFlightID") Integer newFlightID, @WebParam(name = "newSeatID") Integer newSeatID) throws ChangeReservationException;
    
    
    /**
     * 
     * @param passengerIDDocNumber
     * @param TicketID
     * @throws CancelReservationException  thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public void cancelReservation(@WebParam(name = "passengerIDDocNumber") String passengerIDDocNumber, 
            @WebParam(name = "TicketID") Integer TicketID) throws CancelReservationException;
    
    /**
     * Creates a printable version of th ticket.
     * Note: the ticket must be paid.
     * @param TicketID the ticket ID
     * @return a file that contains the ticket to be printed.
     * @throws TicketDoesNotExistException Throws if ticket does not exist.
     * @throws DatabaseException  Thrown if the backends encounter problems.
     * @throws  PrintingException thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public DataHandler printTicket(@WebParam(name = "TicketID") Integer TicketID) throws PrintingException;
    
    /**
     * Performs a payment transaction for a specific ticket.
     * @param ticketID the ticket ID
     * @param payment the payment method
     * @return the paid ticket
     * @throws DatabaseException thrown when the backend database is unavailable or performed a bad querry.
     * @throws PaymentException thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public CashPayment payForAReservationCash(
            @WebParam(name = "ticketID") Integer ticketID, 
            @WebParam(name = "amountDue") Double amountDue) throws PaymentException;
    
    /**
     * Performs a payment transaction for a specific ticket.
     * @param ticketID the ticket ID
     * @param payment the payment method
     * @return the paid ticket
     * @throws DatabaseException thrown when the backend database is unavailable or performed a bad querry.
     * @throws  PaymentException thrown if backend is unavailable or the backend threw an exception that was not handled.
     */
    public CardPayment payForAReservationCard(
            @WebParam(name = "ticketID") Integer ticketID, 
            @WebParam(name = "amountDue") Double amountDue,
            @WebParam(name = "cardData") CardData cardData) throws PaymentException;
    
    /**
     * Returns free seats for a flight
     * @param flightID the specific flight ID
     * @return a collection of seats
     * @throws SearchException thrown if backend is unavailable or the backend threw an exception.
     */
    public SeatCollection getFreeSeatsForFlight(@WebParam(name = "flightID") Integer flightID) throws SearchException;
    
    /**
     * Returns all tickets for the specific passenger.
     * @param idDocument a string representing the ID number of a document - passport...
     * @return returns a collection of tickets
     * @throws SearchException thrown if backend is unavailable or the backend threw an exception.
     */
    public TicketPackage getTicketsPerPassenger(@WebParam(name = "passengerIDDocument") String idDocument) throws SearchException; 
}
