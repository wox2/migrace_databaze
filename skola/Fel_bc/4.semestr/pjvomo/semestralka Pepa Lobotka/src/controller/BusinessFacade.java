package controller;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import model.*;
import java.sql.*;
import model.Date;
import java.util.ArrayList;
import org.xml.sax.SAXException;

/*******************************************************************************
 * Business facade containing actions of business system. Singleton.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class BusinessFacade {
    
//== CONSTANT CLASS ATTRIBUTES =================================================


/*******************************************************************************
 * Singleton INSTANCE.
 */
    private static final BusinessFacade INSTANCE = new BusinessFacade();



//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Empty constructor.
 */
    private BusinessFacade(){
    }


/*******************************************************************************
 * Method to get only instance of this class.
 *
 * @return Singleton instance.
 */
    public static BusinessFacade getInstance(){
        return INSTANCE;
    }


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

/*******************************************************************************
 * Makes new order and save into DB.
 *
 * @param productType
 * @param product
 * @param postStyle
 * @param postCost
 * @param productCost
 * @return
 * @throws SQLException
 */
    public Order makeNewOrder(  String firstName,
                                String sureName,
                                String nick,
                                String street,
                                String city,
                                int postCode,
                                String product,
                                String productType,
                                String postStyle,
                                Date ordered,
                                Date payed,
                                Date delivered,
                                Date sent,
                                Date bought,
                                int state,
                                String seller,
                                int purchasePrice,
                                int postCost,
                                int productCost,
                                int Costs,
                                String notation     ) throws SQLException{

        // Good instance creation
        ProductType pt = new ProductType(productType);
        Product pro = new Product(product);
        Post ship = new Post(postStyle, postCost);
        ProductTypeRelation productTypeRelatio = new ProductTypeRelation(pt, pro, productCost);
        Good good = new Good(productTypeRelatio, ship);

        // Customer instance creation
        Customer customer = new Customer(nick, firstName, sureName, street, city, postCode);

        // Purchase instance creation
        Purchase purchase = new Purchase(bought, seller, purchasePrice, delivered);

        // Order instance creation
        Order order = new Order(good, customer, ordered, payed, purchase, sent, productCost, notation);
        
        // Inserting order sequentionally
        Conservable[] listener = order.getListener();
        for (int i = 0; i< listener.length; i++){
            listener[i].insert();
        }

        return order;
    }


/*******************************************************************************
 * Deletes certain order from DB.
 *
 * @param order Order to delete.
 * @throws SQLException
 */
    public void deleteOrder(Order order) throws SQLException{
        Conservable[] listener = order.getListener();

        for (int i = listener.length-1; i >= 0; i--){
            listener[i].delete();
        }
    }


/*******************************************************************************
 * Loads all orders from DB into ArrayList.
 * 
 * @return All Orders from DB.
 * @throws SQLException
 */
    public ArrayList<String[]> loadOrdersFromDB() throws SQLException{
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM orders");

        ArrayList<Integer> iDS = new ArrayList<Integer>();
        while (rs.next()){
            iDS.add(rs.getInt(1));
        }

        // Inserts orders into table model.
        ArrayList<String[]> orders = new ArrayList<String[]>();
        for (int i=0; i<iDS.size(); i++){
            Order order = this.getOrderFromDB(iDS.get(i).intValue());
            
            String[] loadedOrder = new String[12];
            loadedOrder[0] = String.valueOf((order.getID() + 1623890000));
            if (order.getNick().length() == 0) {
                loadedOrder[1] = String.valueOf("  " + order.getFullName() + "");
            } else{
                loadedOrder[1] = String.valueOf("  " + order.getFullName() + " [" + order.getNick() + "]");
            }
            loadedOrder[2] = String.valueOf("  " + order.getProductName());
            loadedOrder[3] = String.valueOf("  " + order.getTypeSpecification());
            loadedOrder[4] = String.valueOf("  " + order.getProductPrice());
            loadedOrder[5] = String.valueOf("  " + order.getOrderedDate());
            loadedOrder[6] = String.valueOf("  " + order.getDeliveredDate());
            loadedOrder[7] = String.valueOf("  " + order.getPayedDate());
            loadedOrder[8] = String.valueOf("  " + order.getSentDate());
            loadedOrder[9] = String.valueOf("  " + order.getState());
            orders.add(loadedOrder);
        }
        return orders;
    }


/*******************************************************************************
 * Clears tables in DB from records. For testing purposes only.
 *
 * @throws SQLException
 */
    public void emptyDB() throws SQLException{
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

        stmtIn.executeUpdate("TRUNCATE orders");
        stmtIn.executeUpdate("TRUNCATE customers");
        stmtIn.executeUpdate("TRUNCATE purchase");
        stmtIn.executeUpdate("TRUNCATE goods");
        stmtIn.executeUpdate("TRUNCATE product_type");
        stmtIn.executeUpdate("TRUNCATE product");
        stmtIn.executeUpdate("TRUNCATE type");
        stmtIn.executeUpdate("TRUNCATE post");
    }

/*******************************************************************************
 * Updates order and save changes into DB.
 *
 * @param order Order instance.
 * @throws SQLException
 */
    public void updateOrder(Order order) throws SQLException{
        Conservable[] listener = order.getListener();

        for (int i = 0; i< listener.length; i++){
            listener[i].update();
        }
    }


/*******************************************************************************
 * Gains and creates new order's instance from DB according to order's ID.
 *
 * @param orderID ID of searched order.
 * @return New order's instance.
 * @throws SQLException
 */
    public Order getOrderFromDB(int orderID) throws SQLException{
        return new Order(orderID);
    }


/*******************************************************************************
 * Finds out which ID in certain table is last (Ascendent order of ID's).
 *
 * @param table Table, where ID is searched for.
 * @return last ID in Table.
 * @throws SQLException
 */
    public int lastIDInDB(String table) throws SQLException{
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " ORDER BY " +
                table + "_id ASC");
        while (rs.next()){
            if (rs.isLast()){
                return rs.getInt(1);
            }
        }
        return -1;
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB(String item,
                              String table,
                              String column
                              ) throws SQLException{
        
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                column + " = '" + item + "'");
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB(int number,
                              String table,
                              String column
                              ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                column + " = '" + number + "'");
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB(String item,
                              int number,
                              String table,
                              String firstRow,
                              String secondRow
                              ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                firstRow + " = '" + item + "' AND " + secondRow + " = " + number);
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB(int firstNumber, 
                              int secondNumber,
                              String table,
                              String firstColumn,
                              String secondColumn
                              ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                firstColumn + " = " + firstNumber + " AND " + secondColumn + " = " +
                secondNumber);
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkChildsIDinDB(int searchedNumber, 
                                     int childsID,
                                     String table,
                                     String searchedColumn,
                                     String childsColumn
                                     ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                childsColumn + " != " + childsID + " AND " + searchedColumn + " = " +
                searchedNumber);
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }

    
/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB(int firstNumber,
                              int secondNumber,
                              int thirdNumber,
                              String table,
                              String firstColumn,
                              String secondColumn,
                              String thirdColumn
                              ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                firstColumn + " = " + firstNumber + " AND " + secondColumn + " = " +
                secondNumber + " AND " + thirdColumn + " = " + thirdNumber);
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB( String firstItem,
                            String secondItem,
                            String thirdItem,
                            int fourthNumber,
                            String table,
                            String firstColumn,
                            String secondColumn,
                            String thirdColumn,
                            String fourthColumn
                            ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                firstColumn + " = '" + firstItem + "' AND " + secondColumn + " = '" +
                secondItem + "' AND " + thirdColumn + " = '" + thirdItem + "' AND " +
                fourthColumn + " = " + fourthNumber);
        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Checks if certain data ARE in DB or not.
 * ...
 * @return number of ID, where record is kept.
 *         -1 when there's no record in this combination of parameters.
 * @throws SQLException
 */
    public int checkIDinDB( int firstNum,
                            int secondNum,
                            int thirdNum,
                            String fourthItem,
                            String fifthItem,
                            String sixthItem,
                            int seventhNum,
                            String eigthItem,
                            String table,
                            String firstColumn,
                            String secondColumn,
                            String thirdColumn,
                            String fourthColumn,
                            String fifthColumn,
                            String sixthColumn,
                            String seventhColumn,
                            String eightColumn
                            ) throws SQLException{

        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM " + table + " WHERE " +
                firstColumn + " = " + firstNum + " AND " + secondColumn + " = " +
                secondNum + " AND " + thirdColumn + " = " + thirdNum + " AND " +
                fourthColumn + " = '" + fourthItem + "' AND " + fifthColumn + " = '" +
                fifthItem + "' AND " + sixthColumn + " = '" + sixthItem + "' AND " +
                seventhColumn + " = " + seventhNum + " AND " + eightColumn + " = '" +
                eigthItem + "'");

        if(rs.next()){
            return rs.getInt(1);
        } else {
            return -1; // When did not find record in DB
        }
    }


/*******************************************************************************
 * Imports orders data from .xml file to database.
 *
 * @param xmlFile orders .xml file.
 */
    public void importFromXMLtoDB(File xmlFile) throws ParserConfigurationException, SAXException, IOException, SQLException {
            XMLToDB xmlParse = new XMLToDB(xmlFile);
            xmlParse.parseOrdersToDB();
    }


//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
