package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Contains information about purchase from supplier.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Purchase implements Conservable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains ID used in DB table "purchase".
 */
    private int purchaseID;


/*******************************************************************************
 * contains date, when good was bought.
 */
    private Date bought;


/*******************************************************************************
 * Name of seller / provider.
 */
    private String seller;


/*******************************************************************************
 * Price for good we bought from seller / provider / distributor.
 */
    private int price;


/*******************************************************************************
 * Contains date, when good was delivered to shop.
 */
    private Date delivered;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Constructor makes brand new Purchase and generates ID in order to make new
 * record in DB.
 *
 * @param boughtDate When good was bought.
 * @param seller Name of seller / provider / distributor.
 * @param price Price payed for good to seller.
 * @param deliveryDate Date of delivery to shop.
 * @throws SQLException
 */
    public Purchase(Date boughtDate, String seller, int price, Date deliveryDate) throws SQLException{
        this.purchaseID = BusinessFacade.getInstance().lastIDInDB("purchase") + 1;
        this.bought = boughtDate;
        this.seller = seller;
        this.price = price;
        this.delivered = deliveryDate;
    }


/*******************************************************************************
 * Retrieves information about Purchase from DB based on given purchaseID.
 *
 * @param purchaseID purchase ID in DB table "purchase" from which new Purchase
 *                   instance will be made.
 * @throws SQLException
 */
    public Purchase(int purchaseID) throws SQLException{
        this.purchaseID = purchaseID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.purchase " +
                "WHERE purchase_id = " + purchaseID);
        while (rs.next()) {
            this.bought = new Date("" + rs.getDate(2));
            this.seller = rs.getString(3);
            this.price = rs.getInt(4);
            this.delivered = new Date("" + rs.getDate(5));
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
        return this.purchaseID;
    }


/*******************************************************************************
 * Gets Date of bought.
 *
 * @return Date of bought as a String.
 */
    public String getBoughtDate(){
        if (this.bought.isEmpty()){
            return "-";
        } else{
            return "" + this.bought;
        }
    }


/*******************************************************************************
 * Gets a name of seller / provider / distributor.
 *
 * @return Name of seller / provider / distributor.
 */
    public String getSeller(){
        return this.seller;
    }


/*******************************************************************************
 * Returns price payed for bought good.
 *
 * @return Price payed for bought good.
 */
    public int getBoughtPrice(){
        return this.price;
    }


/*******************************************************************************
 * Returns date of Delivery to shop.
 *
 * @return Date of Delivery to shop.
 */
    public String getDeliveredDate(){
        if (this.delivered.isEmpty()) {
            return "-";
        } else {
            return "" + this.delivered;
        }
    }


/*******************************************************************************
 * Sets new date when Good was bought.
 *
 * @param newBoughtDate New date.
 */
    public void setBoughtDate(Date newBoughtDate){
        this.bought = newBoughtDate;
    }


/*******************************************************************************
 * Changes name of seller / provider / distributor.
 *
 * @param newSeller New seller's name.
 */
    public void setSeller(String newSeller){
        this.seller = newSeller;
    }


/*******************************************************************************
 * Changes price for bought Good.
 *
 * @param newBoughtPrice New price for bought Good.
 */
    public void setBoughtPrice(int newBoughtPrice){
        this.price = newBoughtPrice;
    }


/*******************************************************************************
 * Sets new Date when good was delivered to shop.
 *
 * @param newDeliveredDate New Date when good was delivered to shop.
 */
    public void setDeliveredDate(Date newDeliveredDate){
        this.delivered = newDeliveredDate;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "purchase" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        stmtIn.executeUpdate("UPDATE ngaccount.purchase SET purchase_bought = '" +
                this.bought.getFormatForDB() + "', purchase_seller = '" + this.seller +
                "', purchase_price = '" + this.price + "', purchase_delivered = '" +
                this.delivered.getFormatForDB() + "' WHERE purchase.purchase_id = " + this.purchaseID);
    }


/*******************************************************************************
 * Inserts data into DB and table "purchase".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        int check = BusinessFacade.getInstance().checkIDinDB(this.bought.getFormatForDB(),
                this.seller, this.delivered.getFormatForDB(), this.price, "purchase",
                "purchase_bought", "purchase_seller", "purchase_delivered", "purchase_price");
        if (check == -1){
        stmtIn.executeUpdate("INSERT INTO ngaccount.purchase (purchase_id , purchase_bought, " +
                "purchase_seller, purchase_price, purchase_delivered) " +
                "VALUES ('" + this.purchaseID + "', '" + this.bought.getFormatForDB() +
                "', '" + this.seller + "', '" + this.price + "', '" + this.delivered.getFormatForDB() + "');");
        } else {
            this.purchaseID = check;
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "purchase".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.purchaseID,
                "orders", "purchase_id");
        if (safeCheck == -1) {
        stmtIn.executeUpdate("DELETE FROM ngaccount.purchase WHERE purchase.purchase_id = " +
                this.purchaseID + " LIMIT 1;");
        }
    }


//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
