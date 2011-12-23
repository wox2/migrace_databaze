package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Class containing info about Customer
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Customer implements Conservable {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains ID used in DB table "customers".
 */
    private int customerID;


/*******************************************************************************
 * contains parent Object "Order".
 */
    private Order order;


/*******************************************************************************
 * Contains customer's nick name.
 */
    private String nick;


/*******************************************************************************
 * Customer's full name.
 */
    private Name fullName;


/*******************************************************************************
 * Street and street number of order's delivery address.
 */
    private String street;


/*******************************************************************************
 * City of order's delivery address.
 */
    private String city;


/*******************************************************************************
 * city post code of order's delivery address.
 */
    private int postcode;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Contains info about customer and address of order's delivery and generates
 * unique ID for customer.
 *
 * @param nick Customer's nick name.
 * @param firstName First name.
 * @param sureName Sure name.
 * @param street Street of order's delivery.
 * @param city City of order's delivery.
 * @param postcode City Post code of order's delivery.
 * @throws SQLException
 */
    public Customer(String nick, String firstName, String sureName,
            String street, String city, int postcode) throws SQLException {
        this.fullName = new Name(firstName, sureName);
        int check = BusinessFacade.getInstance().checkIDinDB("" + this.fullName,
                street, city, postcode, "customers", "customers_name", "customers_street",
                "customers_city", "customers_postcode");
        if (check != -1) {
            this.customerID = check;
        } else {
            this.customerID = BusinessFacade.getInstance().lastIDInDB("customers") + 1;
        }
        this.nick = nick;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }


/*******************************************************************************
 * Retrieves information about Customer from DB based on given Customer ID.
 *
 * @param customerID Customer ID in DB table "customers" from which new Customer
 *                   instance will be made.
 * @throws SQLException
 */
    public Customer(int customerID) throws SQLException{
        this.customerID = customerID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.customers " +
                "WHERE customers_id = " + customerID);
        while (rs.next()) {
            this.nick = rs.getString(2);
            this.fullName = new Name(rs.getString(3));
            this.street = rs.getString(4);
            this.city = rs.getString(5);
            this.postcode = rs.getInt(6);
        }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================

    
/*******************************************************************************
 * Gets actual ID.
 *
 * @return ID as a number.
 */
    public int getID(){
        return this.customerID;
    }


/*******************************************************************************
 * Gets Customer's nick name if he has one.
 *
 * @return Customer's nick name.
 */
    public String getNick(){
        return this.nick;
    }


/*******************************************************************************
 * Retrieves customer's full name.
 *
 * @return Customer's full name
 */
    public String getFullName(){
        return "" + this.fullName;
    }


/*******************************************************************************
 * Gets street name and street number of order's delivery address.
 *
 * @return Street name and street number
 */
    public String getStreet(){
        return this.street;
    }


/*******************************************************************************
 * Gets city name of order's delivery address.
 *
 * @return City name.
 */
    public String getCity(){
        return this.city;
    }


/*******************************************************************************
 * Gets city post code of order's delivery address.
 *
 * @return City postcode.
 */
    public int getPostcode(){
        return this.postcode;
    }


/*******************************************************************************
 * Changes customer's nick name.
 *
 * @param newNick Customer's new nick name.
 */
    public void setNick(String newNick){
        this.nick = newNick;
    }


/*******************************************************************************
 * Changes customer's full name.
 *
 * @param newName Customer's new first name.
 * @param newSurename Customer's new surename.
 */
    public void setName(String newName, String newSurename){
        this.fullName.setName(newName);
        this.fullName.setSurename(newSurename);
    }


/*******************************************************************************
 * Changes name and code of street where order should be delivered.
 *
 * @param newStreet New Name and number of street.
 */
    public void setStreet(String newStreet){
        this.street = newStreet;
    }


/*******************************************************************************
 * Changes city where order should be delivered.
 *
 * @param newCity New name of city.
 */
    public void setCity(String newCity){
        this.city = newCity;
    }


/*******************************************************************************
 * Sets new postcode of city where order should be delivered
 *
 * @param newPostcode New city's postcode.
 */
    public void setPostcode(int newPostcode){
        this.postcode = newPostcode;
    }


/*******************************************************************************
 * Sets parent's Object "Order".
 *
 * @param Good Parent's object.
 */
    public void setOrder(Order order){
        this.order = order;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "customers" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException {
        int check = BusinessFacade.getInstance().checkIDinDB("" + this.fullName,
                this.street, this.city, this.postcode, "customers", "customers_name", "customers_street",
                "customers_city", "customers_postcode");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.customerID,
                this.order.getID(), "orders", "customers_id", "orders_id");
        if (check != -1 && safeCheck == -1) {
            if (check != this.customerID) {
                int oldCustomerID = this.customerID;
                this.customerID = check;
                this.order.update();
                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
                stmtIn.executeUpdate("DELETE FROM ngaccount.customers WHERE customers.customers_id = " +
                        oldCustomerID + " LIMIT 1;");
            }
        } else if (check != -1 && safeCheck != -1) {
            this.customerID = check;
        } else if (check == -1 && safeCheck == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("UPDATE ngaccount.customers SET customers_nick = '" +
                    this.nick + "', customers_name = '" + this.fullName + "', customers_street = '" +
                    this.street + "', customers_city = '" + this.city + "', customers_postcode = " +
                    this.postcode + " WHERE customers.customers_id = " + this.customerID);
        } else {
            this.customerID = BusinessFacade.getInstance().lastIDInDB("customers") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "customers".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB("" + this.fullName,
                this.street, this.city, this.postcode, "customers", "customers_name", "customers_street",
                "customers_city", "customers_postcode");
        if (check == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("INSERT INTO ngaccount.customers (customers_id , customers_nick, " +
                    "customers_name, customers_street, customers_city, customers_postcode) " +
                    "VALUES ('" + this.customerID + "', '" + this.nick + "', '" + this.fullName + "', '" +
                    this.street + "', '" + this.city + "', '" + this.postcode + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "customers".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException{
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.customerID,
                "orders", "customers_id");
        if (safeCheck == -1) {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        stmtIn.executeUpdate("DELETE FROM ngaccount.customers WHERE customers.customers_id = " +
                this.customerID + " LIMIT 1;");
        }
        
    }

//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
