/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.paymentserver.utils;

import cz.ctu.fee.murinrad.aos.common.datacontainers.Payment;
import cz.ctu.fee.murinrad.aos.common.exceptions.DatabaseException;
import cz.ctu.fee.murinrad.aos.common.utils.ConnectionToDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author murinr
 */
public class DatabaseOperations {
    ConnectionToDB connToDB;
    
    //Column labels
    
    private static final String PAYMENTS_PAYMENT_ID_C_LABEL = "paymentID";
    private static final String PAYMENTS_PAYMENT_AMOUNT_C_LABEL = "paymentAmount";
    private static final String PAYMENTS_TICKET_ID_C_LABEL ="TicketID";
    private static final String PAYMENTS_STATUS_C_LABEL ="PaymentStatus";
            
    

    public DatabaseOperations() {
        try {
            connToDB = new ConnectionToDB("/jdbc/paymentdb", "jdbc:mysql://localhost:3306/", "paymentdb", "root","admin");
        } catch (NamingException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Integer writePayment(Payment p,Integer status) throws DatabaseException {
        Statement s = null;
        try {
        s = connToDB.getStatement(); 
        s.execute("START TRANSACTION;");
        s.execute("INSERT INTO `payments` (`"+PAYMENTS_PAYMENT_AMOUNT_C_LABEL+"`,"
                + " `"+PAYMENTS_TICKET_ID_C_LABEL+"`,"
                + " `"+PAYMENTS_STATUS_C_LABEL+"`) VALUES"
                + " ('"+p.getAmountDue()+"', '"+p.getTicketID()+"', '"+status+"');");
        Integer ret = getLastInsertID();
        if(ret==-1) {
            s.execute("ROLLBACK");
            throw new DatabaseException("Error occured during transaction.", new RuntimeException());
        }
        s.execute("COMMIT");
        return ret;
        } catch (SQLException ex) {
            try {
                s.execute("ROLLBACK");
            } catch (SQLException ex1) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DatabaseException("DB went wonkers", ex);
            
        }
        
    }
    
    public Integer getPaidAmount(Integer ticketID) throws DatabaseException {
        Statement s = null;
        try {
            Integer total = 0;
            s = connToDB.getStatement();
            ResultSet rs = s.executeQuery("select "+PAYMENTS_PAYMENT_AMOUNT_C_LABEL+" from payments where '"+PAYMENTS_TICKET_ID_C_LABEL+"'=="+ticketID+" && '"+PAYMENTS_STATUS_C_LABEL+"'=1");
            while(rs.next()) {
                total+=rs.getInt(1);
            }
            return total;
            } catch (SQLException ex) {
                throw new DatabaseException("DB went wonkers.", ex);
        }
    }
    
    
    
    private Integer getLastInsertID() throws SQLException {
        Statement s = connToDB.getStatement();
        ResultSet rs = s.executeQuery("select LAST_INSERT_ID()");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;


    }
    
    
    
    
    
}
