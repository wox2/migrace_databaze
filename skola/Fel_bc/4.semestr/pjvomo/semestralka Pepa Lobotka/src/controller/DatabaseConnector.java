package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*******************************************************************************
 * Allows connection to DTB
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class DatabaseConnector {
//== CONSTANT CLASS ATTRIBUTES =================================================

    
    // Singleton
    private static final DatabaseConnector SINGLETON = new DatabaseConnector();


//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================


    // Database access data
    private String DATABASE_URI = "db4free.net:3306/";
    private String DATABASE_USER = "nouee" ;
    private String DATABASE_PASS = "nds1235";

    // DB pre and post settings
    private String POST_DB_TYPE = "ngaccount?characterEncoding=utf8";
    private String PRE_DB_TYPE = "jdbc:mysql://";



//== VARIABLE INSTANCE ATTRIBUTES ==============================================


    // Connection variables
    private Connection con;
    private Statement stmtIn;
    private Statement stmtOut;

    // Database access data
    private String password;
    private String user;
    private String uri;

    

//== CLASS GETTERS & SETTERS ===================================================


/*******************************************************************************
 * Singleton. Returns only one instance.
 *
 * @return Database Connector only instance.
 */
    public static DatabaseConnector getInstance(){
        return SINGLETON;
    }



//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Establishes connection and connects to default database.
 */
    private DatabaseConnector (){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            this.setDefaultConnection();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC Connector driver could not be found!");
            ex.printStackTrace();
            System.exit(255);
        } catch (SQLException ex) {
            System.out.println("Connection error has occured!");
            ex.printStackTrace();
            System.exit(255);
    }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Gets current connection In. statement object.
 *
 * @return Connection in. statement.
 */
    public Statement getStatementIn() {
        return stmtIn;
    }


/*******************************************************************************
 * Gets current connection Out. statement object.
 *
 * @return Connection out. statement.
 */
    public Statement getStatementOut() {
        return stmtOut;
    }


/*******************************************************************************
 * Gets current connection object.
 *
 * @return Current connection.
 */
    public Connection getConnection() {
        return con;
    }


/*******************************************************************************
 * Connects to default database using default access data.
 */
    public void setDefaultConnection() throws SQLException{
        
        uri = DATABASE_URI;
        user = DATABASE_USER;
        password = DATABASE_PASS;

        con = DriverManager.getConnection(PRE_DB_TYPE + DATABASE_URI + POST_DB_TYPE, DATABASE_USER, DATABASE_PASS);
        stmtIn = con.createStatement();
        stmtOut = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


/*******************************************************************************
 * Establishes new connection and connects to database based on given URI,
 * username and password.
 *
 * @param dbURI Database URI.
 * @param dbUser Account Username.
 * @param dbPass Account Password.
 * 
 * @throws SQLException
 */
    public void newConnection(String dbURI, String dbUser, String dbPass) throws SQLException{
         con = DriverManager.getConnection(PRE_DB_TYPE + dbURI + POST_DB_TYPE, dbUser, dbPass);
         stmtIn = con.createStatement();
         stmtOut = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


/*******************************************************************************
 * Returns password used to connect current database.
 *
 * @return Password.
 */
    public String getPass() {
        return password;
    }


/*******************************************************************************
 * Returns current database URI.
 *
 * @return current Database URI.
 */
    public String getURI() {
        return uri;
    }


/*******************************************************************************
 * Returns username used to connect current database.
 *
 * @return username.
 */
    public String getUser() {
        return user;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================


}

