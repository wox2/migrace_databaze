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
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Rado
 */
@WebService
public interface FlightFinderIface {
    
    public FlightCollection listAllFlights();
    
    /**
     * Finds the flight by its ID number
     * @param id the flight ID
     * @return the flight you were looking for
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     * @throws MalformedParameterException Thrown if the flight ID was null
     */
    public Flight findFlightByID(Integer id) throws DatabaseException,MalformedParameterException,FlightDoesNotExistException;
    public FindFlightsByPassengerIDAnswer findFlightsByPassengerID(@WebParam(name="passengerID") Integer id) throws DatabaseException,MalformedParameterException;
    /**
     * Main flight method. Finds a flight according to the data given in the params.
     * WARNING arrivalTime and departure time must not be present at the same time.
     * @param arrivalAPT the IATA code of the arrival airport.
     * @param departureAPT the IATA code of the departure airport.
     * @param arrivalTime the arrival time as a unix timestamp.
     * @param departureTime the departure time as a unix timestamp.
     * @return A collection of flights that adhere to the params.
     * @throws DatabaseException Thrown if the connection to the database has been lost or an error occurred during the transaction
     * @throws MalformedParameterException Thrown if the parameters are bad.
     */
    public FindFlightsByFlightDataAnswer findFlightsByFlightData(@WebParam(name="arrivalAPT") String arrivalAPT,@WebParam(name="departureAPT") String departureAPT,
            @WebParam(name="arrivalTime") Long arrivalTime,@WebParam(name="departureTime") Long departureTime) throws DatabaseException,MalformedParameterException;
    
    
}
