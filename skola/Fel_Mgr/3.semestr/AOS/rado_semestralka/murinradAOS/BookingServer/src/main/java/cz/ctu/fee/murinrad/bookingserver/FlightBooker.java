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
import cz.ctu.fee.murinrad.aos.common.datacontainers.Passenger;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Seat;
import cz.ctu.fee.murinrad.aos.common.datacontainers.SeatCollection;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import cz.ctu.fee.murinrad.aos.common.exceptions.TimeSpaceException;
import cz.ctu.fee.murinrad.bookingserver.utils.DatabaseOperations;
import javax.jws.WebService;

/**
 *
 * @author Radovan Murin
 */


@WebService(endpointInterface="cz.ctu.fee.murinrad.bookingserver.FlightBookerIface")
public class FlightBooker implements FlightBookerIface {

    @Override
    public SeatCollection getFreeSeats(Integer flightID) throws DatabaseException{
        DatabaseOperations dbo = new DatabaseOperations();
        
        SeatCollection ret = new SeatCollection(dbo.getFreeSeats(flightID));
        dbo.close();
        return ret;
    }
    
    

    @Override
    public Ticket bookFlight(Passenger passenger, Integer seatID, Integer flightID) throws DatabaseException,SeatIsAlreadyReservedException,TimeSpaceException{
       DatabaseOperations dbo = new DatabaseOperations();
       Ticket t;
       Integer passengerID = dbo.passengerExists(passenger.getIdDocNumber()); 
       if(passengerID==-1) {
           passenger =  dbo.createPassenger(passenger);
       } else {
           passenger.setId(passengerID);
       }
       t = dbo.CreateTicket(passenger.getId(), flightID, seatID);
        dbo.close();
       return t;
    }

    @Override
    public void cancelReservation(String passengerIDDocNumber,Integer flightID)throws DatabaseException,TicketDoesNotExistException,PassengerDoesNotExistException,TicketIsNotCancellableException {
        DatabaseOperations dbo = new DatabaseOperations();
        Integer i =  dbo.cancelReservation(passengerIDDocNumber,flightID);
        if (i!=0) {
            if(i==1) throw new TicketIsNotCancellableException("Reservation cannot be canceled as it has been paid",new RuntimeException());
            if(i==2) throw new TicketIsNotCancellableException("Ticket cannot be cancelled, time is too short",new RuntimeException());
        }
         dbo.close();
    }

    @Override
    public TicketPackage getTicketsForPassenger(String passengerIDDocNumber) throws DatabaseException,PassengerDoesNotExistException {
        DatabaseOperations dbo = new DatabaseOperations();
        Integer passengerID = dbo.passengerExists(passengerIDDocNumber);
        if(passengerID == -1) throw new PassengerDoesNotExistException("The passenger with the given ID document does not exist",new RuntimeException());
        TicketPackage ret = new TicketPackage(dbo.getTicketsForPassenger(passengerID));
         dbo.close();
        return ret;
    }

    @Override
    public Ticket ChangeReservation(Integer ticketID, Integer newFlightID, Integer newSeatNumber) throws 
            TicketDoesNotExistException, SeatIsAlreadyReservedException,DatabaseException,
    TicketIsNotCancellableException,ChangeTicketException {
        DatabaseOperations dbo = new DatabaseOperations();
        if(dbo.isCancellable(ticketID)!=0) throw new TicketIsNotCancellableException("The ticket is not cancelable",new RuntimeException());
        Ticket t = dbo.changeTicket(ticketID, newFlightID,newSeatNumber);
                 dbo.close();
       return  t;
    }

    @Override
    public void confirmTicketPaid(Integer TicketID) throws TicketDoesNotExistException, DatabaseException {
        DatabaseOperations dbo = new DatabaseOperations();
        Ticket t = dbo.getSpecificTicketByID(TicketID);
        if (t==null) throw new TicketDoesNotExistException("The specific ticket is not in the system.", new RuntimeException());
        dbo.confirmPaid(TicketID);
          dbo.close();
        
    }

    @Override
    public GetTicketAnswer getSpecificTicket(Integer ticketID) throws TicketDoesNotExistException, DatabaseException {
        DatabaseOperations dbo = new DatabaseOperations();
        Ticket t = dbo.getSpecificTicketByID(ticketID);
        if (t==null) throw new TicketDoesNotExistException("The specific ticket is not in the system.", new RuntimeException());
        GetTicketAnswer ret = new GetTicketAnswer(t);
          dbo.close();
        return ret;
    }

    @Override
    public Passenger getPassengerDataForATicket(Integer ticketID) throws TicketDoesNotExistException, DatabaseException {
        DatabaseOperations dbo = new DatabaseOperations();
        Passenger ret = dbo.getPassengerByTicket(ticketID);
        dbo.close();
        return ret;
    }
    
}
