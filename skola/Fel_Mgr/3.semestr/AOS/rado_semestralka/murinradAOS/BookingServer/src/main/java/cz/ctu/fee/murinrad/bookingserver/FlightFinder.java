/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Flight;
import cz.ctu.fee.murinrad.aos.common.datacontainers.FlightCollection;
import cz.ctu.fee.murinrad.aos.common.exceptions.DatabaseException;
import cz.ctu.fee.murinrad.aos.common.exceptions.FlightDoesNotExistException;
import cz.ctu.fee.murinrad.aos.common.exceptions.MalformedParameterException;
import cz.ctu.fee.murinrad.bookingserver.datacontainers.FindFlightsByFlightDataAnswer;
import cz.ctu.fee.murinrad.bookingserver.datacontainers.FindFlightsByPassengerIDAnswer;
import cz.ctu.fee.murinrad.bookingserver.datacontainers.FindFlightsByPassengerNameAnswer;
import cz.ctu.fee.murinrad.bookingserver.utils.DatabaseOperations;
import java.sql.Date;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;

/**
 *
 * @author Radovan Murin
 */
@WebService(endpointInterface = "cz.ctu.fee.murinrad.bookingserver.FlightFinderIface")
public class FlightFinder implements FlightFinderIface {

    @Override
    public FlightCollection listAllFlights() {
        Date d = new Date(2012-1900, 11, 12);
        String depart = "KSC";
        String arrival = "PRG";
        DatabaseOperations dataOp = new DatabaseOperations();

        Collection<Flight> lafr = null;
        try {
            lafr = dataOp.getFlightByDate(depart, arrival, d.getTime(), null);
        } catch (DatabaseException ex) {
            Logger.getLogger(FlightFinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedParameterException ex) {
            Logger.getLogger(FlightFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        FlightCollection fc = new FlightCollection(lafr);
        System.out.println(lafr);
        return fc;
    }

    @Override
    public Flight findFlightByID(Integer id) throws DatabaseException,MalformedParameterException,FlightDoesNotExistException {
        if (id==null) throw new MalformedParameterException("A flight ID is required", new RuntimeException());
        Flight ret;
        DatabaseOperations dataOp = new DatabaseOperations();
        ret = dataOp.getFlightByID(id); 
        if(ret==null) throw new FlightDoesNotExistException("The flight does not exist.", new RuntimeException());
        dataOp.close();
        return ret;
    }

    @Override
    public FindFlightsByPassengerIDAnswer findFlightsByPassengerID(Integer id) throws DatabaseException,MalformedParameterException {
        if(id ==null) throw new MalformedParameterException("A passenger ID is required", new RuntimeException());
       DatabaseOperations dataOp = new DatabaseOperations();
       FindFlightsByPassengerIDAnswer ret = new FindFlightsByPassengerIDAnswer(new FlightCollection(dataOp.getFlightsForPassenger(id)));
       dataOp.close();
       return ret;
    }
   
    @Override
    public FindFlightsByFlightDataAnswer findFlightsByFlightData(String arrivalAPT, String departureAPT, Long arrivalTime, Long departureTime) throws DatabaseException,MalformedParameterException {
        if(departureAPT==null || arrivalAPT==null) throw new MalformedParameterException("Both airports must be specified", new RuntimeException());
        DatabaseOperations dataOp = new DatabaseOperations();
        FindFlightsByFlightDataAnswer ret = new FindFlightsByFlightDataAnswer(new FlightCollection(dataOp.getFlightByDate(departureAPT, arrivalAPT, arrivalTime, departureTime)));
        System.out.println("Im here");
        System.out.println(ret);
        dataOp.close();
        return ret; 
    }
}
