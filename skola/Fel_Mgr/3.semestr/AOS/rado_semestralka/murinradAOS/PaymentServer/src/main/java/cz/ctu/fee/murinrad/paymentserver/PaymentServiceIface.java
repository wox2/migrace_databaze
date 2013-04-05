/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.paymentserver;

import cz.ctu.fee.murinrad.aos.common.datacontainers.BankPayment;
import cz.ctu.fee.murinrad.aos.common.datacontainers.CardPayment;
import cz.ctu.fee.murinrad.aos.common.datacontainers.CashPayment;
import cz.ctu.fee.murinrad.aos.common.exceptions.DatabaseException;
import cz.ctu.fee.murinrad.aos.common.exceptions.PaymentException;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Interface for a payment service
 * @author murinr
 */

@WebService
public interface PaymentServiceIface {
    /**
     * Performs a cash transaction.
     * @param cp a object that represents the transaction
     * @return a object that represents the transaction after the backend has dealt with it
     * @throws DatabaseException  thrown if the payment DB has issues
     * @throws PaymentException  thrown if the payment has encountered an error - not enough money..etc
     */
    public CashPayment performCashTransaction(@WebParam(name="paymentData") CashPayment cp) throws DatabaseException,PaymentException;
    /**
     *  performs a Bank transaction
     * @param bp a object that represents the transaction
     * @return a object that represents the transaction after the backend has dealt with it
     * @throws DatabaseException   thrown if the payment DB has issues
     * @throws PaymentException    thrown if the payment has encountered an error - not enough money..etc
     */
    public BankPayment performBankTransaction(@WebParam(name="paymentData") BankPayment bp) throws DatabaseException,PaymentException;
    /**
     *  performs a Card transaction
     * @param cp a object that represents the transaction
     * @return a object that represents the transaction after the backend has dealt with it
     * @throws DatabaseException thrown if the payment DB has issues
     * @throws PaymentException  thrown if the payment has encountered an error - not enough money..etc
     */
    public CardPayment performCardTransaction(@WebParam(name="paymentData") CardPayment cp) throws DatabaseException,PaymentException;
    
    
    
    
    
    
}
