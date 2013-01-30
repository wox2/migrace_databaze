/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.interfaceserver;

import cz.ctu.fee.murinrad.interfaceserver.exceptions.SearchException;
import cz.ctu.fee.murinrad.bookingserver.ChangeTicketException_Exception;
import cz.ctu.fee.murinrad.bookingserver.DatabaseException_Exception;
import cz.ctu.fee.murinrad.bookingserver.FindFlightsByFlightDataAnswer;
import cz.ctu.fee.murinrad.bookingserver.FindFlightsByFlightDataResponse;
import cz.ctu.fee.murinrad.bookingserver.FlightBookerIface;
import cz.ctu.fee.murinrad.bookingserver.FlightBookerService;
import cz.ctu.fee.murinrad.bookingserver.FlightCollection;
import cz.ctu.fee.murinrad.bookingserver.FlightFinderIface;
import cz.ctu.fee.murinrad.bookingserver.FlightFinderService;
import cz.ctu.fee.murinrad.bookingserver.GetTicketAnswer;
import cz.ctu.fee.murinrad.bookingserver.MalformedParameterException_Exception;
import cz.ctu.fee.murinrad.bookingserver.Passenger;
import cz.ctu.fee.murinrad.bookingserver.PassengerDoesNotExistException_Exception;
import cz.ctu.fee.murinrad.bookingserver.SeatCollection;
import cz.ctu.fee.murinrad.bookingserver.SeatIsAlreadyReservedException_Exception;
import cz.ctu.fee.murinrad.bookingserver.Ticket;
import cz.ctu.fee.murinrad.bookingserver.TicketDoesNotExistException_Exception;
import cz.ctu.fee.murinrad.bookingserver.TicketIsNotCancellableException_Exception;
import cz.ctu.fee.murinrad.bookingserver.TicketPackage;
import cz.ctu.fee.murinrad.bookingserver.TimeSpaceException_Exception;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.CancelReservationException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.ChangeReservationException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.PaymentException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.PrintingException;
import cz.ctu.fee.murinrad.interfaceserver.exceptions.ReservationException;
import cz.ctu.fee.murinrad.paymentserver.BankPayment;
import cz.ctu.fee.murinrad.paymentserver.CardPayment;
import cz.ctu.fee.murinrad.paymentserver.CashPayment;
import cz.ctu.fee.murinrad.paymentserver.Payment;
import cz.ctu.fee.murinrad.paymentserver.PaymentException_Exception;
import cz.ctu.fee.murinrad.paymentserver.PaymentServiceIface;
import cz.ctu.fee.murinrad.paymentserver.PaymentServiceService;
import cz.ctu.fee.murinrad.printingserver.PrintingServiceIface;
import cz.ctu.fee.murinrad.printingserver.PrintingServiceService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;

/**
 *
 * @author Radovan Murin
 */
@WebService(endpointInterface = "cz.ctu.fee.murinrad.interfaceserver.InterfaceServiceIface")
public class InterfaceService implements InterfaceServiceIface {

    FlightFinderService finder = new FlightFinderService();
    FlightBookerService booker = new FlightBookerService();
    PaymentServiceService payGate = new PaymentServiceService();
    PrintingServiceService printer = new PrintingServiceService();

    @Override
    public FlightCollection findFlight(String arrivalAPT, String departureAPT, Long arrivalTime, Long departureTime) {
        FindFlightsByFlightDataAnswer fc = null;
        try {

            FlightFinderIface ffi = finder.getFlightFinderPort();
            fc = ffi.findFlightsByFlightData(arrivalAPT, departureAPT, arrivalTime, departureTime);

        } catch (DatabaseException_Exception ex) {
            Logger.getLogger(InterfaceService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedParameterException_Exception ex) {
            Logger.getLogger(InterfaceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fc.getContent();
    }

    @Override
    public Ticket bookFlight(Passenger passenger, Integer flightID, Integer seatNumber) throws ReservationException {
        if(passenger==null || flightID==null || seatNumber==null || passenger.getIdDocNumber()==null) throw new ReservationException("Invalid message format", new RuntimeException());
        Ticket t = null;

        FlightBookerIface fbi = booker.getFlightBookerPort();
        try {
            t = fbi.bookFlight(passenger, seatNumber, flightID);
            return t;
        } catch (DatabaseException_Exception ex) {
            throw new ReservationException(ex.getLocalizedMessage(), ex);
        } catch (SeatIsAlreadyReservedException_Exception ex) {
            Logger.getLogger(InterfaceService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ReservationException(ex.getLocalizedMessage(), ex);
        } catch (TimeSpaceException_Exception ex) {
            throw new ReservationException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public Ticket changeReservation(Integer ticketID, Integer newFlightID, Integer newSeatID) throws ChangeReservationException {
        if(ticketID==null || newFlightID == null || newSeatID==null) throw new ChangeReservationException("Invalid message format", new RuntimeException());
        Ticket t = null;
        FlightBookerIface fbi = booker.getFlightBookerPort();
        try {
            t = fbi.changeReservation(ticketID, newFlightID, newSeatID);
        } catch (ChangeTicketException_Exception ex) {
            throw new ChangeReservationException(ex.getLocalizedMessage(), ex);
        } catch (DatabaseException_Exception ex) {
            throw new ChangeReservationException(ex.getLocalizedMessage(), ex);
        } catch (SeatIsAlreadyReservedException_Exception ex) {
            throw new ChangeReservationException(ex.getLocalizedMessage(), ex);
        } catch (TicketDoesNotExistException_Exception ex) {
            throw new ChangeReservationException(ex.getLocalizedMessage(), ex);
        } catch (TicketIsNotCancellableException_Exception ex) {
            throw new ChangeReservationException(ex.getLocalizedMessage(), ex);
        }
        return t;

    }

    @Override
    public void cancelReservation(String passengerIDDocNumber, Integer TicketID) throws CancelReservationException {
        if(passengerIDDocNumber == null || TicketID ==null) throw new CancelReservationException("Invalid message format", new RuntimeException());
        FlightBookerIface fbi = booker.getFlightBookerPort();
        try {
            fbi.cancelReservation(passengerIDDocNumber, TicketID);
        } catch (DatabaseException_Exception ex) {
            throw new CancelReservationException(ex.getLocalizedMessage(), ex);
        } catch (PassengerDoesNotExistException_Exception ex) {
            throw new CancelReservationException(ex.getLocalizedMessage(), ex);
        } catch (TicketDoesNotExistException_Exception ex) {
            throw new CancelReservationException(ex.getLocalizedMessage(), ex);
        } catch (TicketIsNotCancellableException_Exception ex) {
            throw new CancelReservationException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public DataHandler printTicket(Integer TicketID) throws PrintingException {
        if(TicketID ==null)throw new PrintingException("Invalid message format", new RuntimeException());
        try {
            PrintingServiceIface print = printer.getPrintingServicePort();
            FlightBookerIface book = booker.getFlightBookerPort();
            cz.ctu.fee.murinrad.printingserver.Ticket t = new cz.ctu.fee.murinrad.printingserver.Ticket();
            Ticket badTicket = book.getSpecificTicket(TicketID).getContent();
            if(badTicket.getStatus()==0) throw new PrintingException("Cannot print the ticket, it has not been paid for.", new RuntimeException());
            t.setFlightID(badTicket.getFlightID());
            t.setStatus(badTicket.getStatus());
            t.setTicketNumber(badTicket.getTicketNumber());
            t.setTotalPrice(badTicket.getTotalPrice());
            t.setSeatNumber(badTicket.getSeatNumber());
            cz.ctu.fee.murinrad.printingserver.Passenger p = new cz.ctu.fee.murinrad.printingserver.Passenger();
            Passenger p1 = book.getPassengerDataForATicket(TicketID);
            p.setId(p1.getId());
            p.setIdDocNumber(p1.getIdDocNumber());
            p.setName(p1.getName());
            p.setSex(p1.getSex());
            p.setSurname(p1.getSurname());
            p.setTitle(p1.getTitle());
            byte[] data = print.printTicket(t, p);
            DataHandler dh = new DataHandler(new ByteDataSource(data));
            return dh;
        } catch (DatabaseException_Exception ex) {
            throw new PrintingException(ex.getLocalizedMessage(), ex);
        } catch (TicketDoesNotExistException_Exception ex) {
            throw new PrintingException(ex.getLocalizedMessage(), ex);
        }




    }

    @Override
    public CashPayment payForAReservationCash(Integer ticketID, Double paidAmount) throws PaymentException {
        if(ticketID==null || paidAmount==null) throw new PaymentException("Invalid message format", new RuntimeException());
        PaymentServiceIface psi = payGate.getPaymentServicePort();
        FlightBookerIface fbi = booker.getFlightBookerPort();
        CashPayment cp = new CashPayment();
        cp.setAmountDue(paidAmount);
        cp.setTicketID(ticketID);
        
        


        try {
            GetTicketAnswer gta = fbi.getSpecificTicket(ticketID);
            Ticket t = gta.getContent();
            if (t == null) {
                throw new PaymentException("The ticket canot be found", new RuntimeException());
            }
            if (!t.getTotalPrice().equals(paidAmount)) {
                throw new PaymentException("Please pay exact", new RuntimeException());
            }
            cp = psi.performCashTransaction(cp);
            if (cp.getAmountDue() == 0) {

                fbi.confirmTicketPaid(ticketID);
                t = fbi.getSpecificTicket(ticketID).getContent();
            }



            return cp;

        } catch (cz.ctu.fee.murinrad.paymentserver.DatabaseException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (PaymentException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (DatabaseException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (TicketDoesNotExistException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public CardPayment payForAReservationCard(Integer ticketID, Double amountDue, CardData cardData) throws PaymentException {
        if(ticketID ==null || amountDue==null||cardData==null ||
                cardData.cvc==null || cardData.expiration==null || cardData.number==null || cardData==null)
            throw new PaymentException("Invalid message format", new RuntimeException());
        
        PaymentServiceIface psi = payGate.getPaymentServicePort();
        FlightBookerIface fbi = booker.getFlightBookerPort();
        CardPayment cp = new CardPayment();
        cp.setAmountDue(amountDue);
        cp.setTicketID(ticketID);
        cp.setCVC(cardData.cvc);
        cp.setCardNumber(cardData.getNumber());
        cp.setOwner(cardData.owner);




        try {
            GetTicketAnswer gta = fbi.getSpecificTicket(ticketID);
            Ticket t = gta.getContent();
            if (t == null) {
                throw new PaymentException("The ticket canot be found", new RuntimeException());
            }
            cp = psi.performCardTransaction(cp);
            if (cp.getAmountDue() == 0) {

                fbi.confirmTicketPaid(ticketID);
                t = fbi.getSpecificTicket(ticketID).getContent();
            }



            return cp;

        } catch (cz.ctu.fee.murinrad.paymentserver.DatabaseException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (PaymentException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (DatabaseException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        } catch (TicketDoesNotExistException_Exception ex) {
            throw new PaymentException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public SeatCollection getFreeSeatsForFlight(Integer flightID) throws SearchException {
        if(flightID ==null) throw new SearchException("Invalid message format", new RuntimeException());
        FlightBookerIface fbi = booker.getFlightBookerPort();
        try {
            return fbi.getFreeSeats(flightID);
        } catch (DatabaseException_Exception ex) {
           throw new SearchException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public TicketPackage getTicketsPerPassenger(String idDocument) throws SearchException {
        if(idDocument==null) throw new SearchException("Invalid message format", new RuntimeException());
        FlightBookerIface fbi = booker.getFlightBookerPort();
        TicketPackage tp;
        try {
            tp = fbi.getTicketsForPassenger(idDocument);
        } catch (DatabaseException_Exception ex) {
            throw new SearchException(ex.getLocalizedMessage(), ex);
        } catch (PassengerDoesNotExistException_Exception ex) {
            throw new SearchException(ex.getLocalizedMessage(), ex);
        }
        return tp;
    }
    
    
}
