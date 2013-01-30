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
import cz.ctu.fee.murinrad.paymentserver.utils.DatabaseOperations;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author murinr
 */
@WebService(endpointInterface="cz.ctu.fee.murinrad.paymentserver.PaymentServiceIface")
public class PaymentService implements PaymentServiceIface{

    @Override
    public CashPayment performCashTransaction(CashPayment cp) throws DatabaseException, PaymentException {
        DatabaseOperations dbo = new DatabaseOperations();
        Integer transactionId = dbo.writePayment(cp, 1);
        cp.setTransactionID(transactionId);
        cp.setConfirmed(Boolean.TRUE);
        
        return cp;
        
    }

    @Override
    public BankPayment performBankTransaction(BankPayment bp) throws DatabaseException, PaymentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CardPayment performCardTransaction(CardPayment cp) throws DatabaseException, PaymentException {
        DatabaseOperations dbo = new DatabaseOperations();
        Integer transactionId = dbo.writePayment(cp, 1);
        cp.setAmountDue(0.0);
        cp.setAmountOver(0.0);
        //tu by mal byt kod realnej transakcie ale kedze niesme realny system tak to jednoducho potvrdzujem
        cp.setTransactionID(transactionId);
        cp.setConfirmed(Boolean.TRUE);
        return cp;
    }


}
