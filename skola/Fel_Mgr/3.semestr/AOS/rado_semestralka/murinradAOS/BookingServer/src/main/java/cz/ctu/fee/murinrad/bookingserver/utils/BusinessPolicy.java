/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.bookingserver.utils;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Flight;
import cz.ctu.fee.murinrad.aos.common.datacontainers.Ticket;
import java.util.Calendar;
import java.util.Date;

/**
 * A class for business policy methods
 * @author Radovan Murin
 */
public class BusinessPolicy {
    private static Integer MAX_DAYS_BEFORE_DEPARTURE_FOR_CANCELLATION = 3;

    
    
    /**
     * Calculates the total fare of a flight
     * @param f
     * @param discount
     * @return  the total fare after business logic has been applied.
     */
    static Double getTotalFare(Flight f, Integer discount) {
        Date d = new Date(f.getDeparture());
        Calendar c = Calendar.getInstance();
        
        Double ret = f.getBaseFare()*1.0;
        ret = ret * 1.2 * discount;
        return ret;
    }
    
    
    
    private BusinessPolicy(){};
    /**
     * Method to find out if ticket is cancelable
     * @param t
     * @param f
     * 
     * @return code 
     *  0 - ticket cancelable
     *  1 - ticket not cancelable - already paid
     *  2 - ticket not cancelable - time
     */
    public static Integer isCancelable(Ticket t,Flight f) {
        Integer ret = 0;
        System.out.println(t);
        if(t.getStatus()==1) {
            System.out.println("isCancelable "+1);
            return 1;
        }
        Date d = new Date(f.getDeparture());
        Calendar c = Calendar.getInstance();
        Date today = c.getTime();
        today.setTime(today.getTime()+259200*MAX_DAYS_BEFORE_DEPARTURE_FOR_CANCELLATION);
        
        if(!today.before(d)) {
            ret = 2;
        }
        System.out.println("isCancelable "+ret);
        return ret;
    }
    
}
