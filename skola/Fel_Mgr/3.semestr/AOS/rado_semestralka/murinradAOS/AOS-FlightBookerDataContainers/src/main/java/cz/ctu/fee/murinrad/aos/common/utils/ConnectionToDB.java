 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Helper class to connect to a database.
 * @author Rado
 */
public class ConnectionToDB {

    private Connection connToDB;
    private int maxConnAttempts = 2;
    private String JNDI, url, schemeName;
    private String uName, pass;

    public ConnectionToDB(String JNDI, String url, String schemeName, String uName, String pass) throws NamingException, SQLException {
        this.JNDI = JNDI;
        this.url = url;
        this.schemeName = schemeName;
        this.uName = uName;
        this.pass = pass;
        int connAtt = 0;
        try {
        while (connAtt < maxConnAttempts) {
            try {
                connToDB = getJNDIConnection();
                return;
            } catch (SQLException ex) {
                connAtt++;
            }
        }} catch(NamingException ex) {
            System.out.println("Problem finding JDNI");
        }
        connAtt = 0;
        while (connAtt < maxConnAttempts) {
            try {
                connToDB = getConnection();
                return;
            } catch (SQLException ex) {
                connAtt++;
                if (connAtt == maxConnAttempts) {
                    throw ex;
                }
            }
        }
    }

    public Connection getJNDIConnection() throws NamingException, SQLException {
        System.out.println("using JNDI");


        Connection result = null;

        Context initialContext = new InitialContext();
        if (initialContext == null) {
            log("JNDI problem. Cannot get InitialContext.");
        }
        DataSource datasource = (DataSource) initialContext.lookup(JNDI);
        if (datasource != null) {
            result = datasource.getConnection();
        } else {
            log("Failed to lookup datasource.");
        }

        return result;
    }

    private static void log(Object aObject) {
        //Logger.log(Level.SEVERE, aObject.toString());
    }

    public Connection getConnection() throws SQLException {
        System.out.println("Using normal connection");
        Connection conn = null;
        try {
            conn = null;
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + schemeName, uName, pass);
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ConnectionToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectionToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }
    
    public void closeConnection() {
        try {
            connToDB.close();
        } catch (SQLException ex) {
            System.out.println("DB conn left open");
            ex.printStackTrace();
        }
    }

    public Statement getStatement() throws SQLException {
        return connToDB.createStatement();
    }
}
