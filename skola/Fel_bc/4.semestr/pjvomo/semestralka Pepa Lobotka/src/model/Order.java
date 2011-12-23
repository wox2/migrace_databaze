package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Contains full order
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Order implements Conservable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains ID used in DB table "orders".
 */
    private int orderID;


/*******************************************************************************
 * Instance of Good class.
 */
    private Good good;


/*******************************************************************************
 * Instance of Customer class.
 *
 */
    private Customer customer;


/*******************************************************************************
 * Contains date, when order was made.
 */
    private Date ordered;


/*******************************************************************************
 * Contains date, when customer payed for order.
 */
    private Date payed;


/*******************************************************************************
 * Instance of Purchase class.
 */
    private Purchase purchase;


/*******************************************************************************
 * Contains date, when order was sent to customer.
 */
    private Date sent;


/*******************************************************************************
 * Contains all costs connected with order.
 */
    private int costs;


/*******************************************************************************
 * Specifies state of order by index number.
 */
    private int state;


/*******************************************************************************
 * contains notation.
 */
    private String notation;


/*******************************************************************************
 * Stores items of order in order to make some action in the future. It's impor-
 * tant to use EXACT order !
 *
 */
    private Conservable[] listener = new Conservable[8];

    
//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Contains info about whole order.
 *
 * @param good Instance containing info about good.
 * @param customer Instance containing info about customer.
 * @param orderedDate Date of order's made.
 * @param payedDate Date of payment for order.
 * @param purchase Instance containing info about distributition.
 * @param sentDate Date when order was sent to customer.
 * @param costs All costs connected with order.
 * @param notation Notation.
 * @throws SQLException
 */
    public Order(Good good, Customer customer, Date orderedDate, Date payedDate,
                Purchase purchase, Date sentDate, int costs, String notation) throws SQLException{
        this.orderID = BusinessFacade.getInstance().lastIDInDB("orders") + 1;

        this.good = good;
        this.customer = customer;
        this.ordered = orderedDate;
        this.payed = payedDate;
        this.purchase = purchase;
        this.sent = sentDate;
        this.costs = costs;
        this.notation = notation;

        this.state = 1;

        addListeners();

        customer.setOrder(this);
        good.setOrder(this);
    }


    public Order (int orderID) throws SQLException{
        int goodID = 0, customerID = 0, purchaseID = 0;
        this.orderID = orderID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.orders " +
                       "WHERE orders_id = " + orderID);
        while(rs.next()){
            goodID = rs.getInt(2);
            customerID = rs.getInt(3);
            purchaseID = rs.getInt(4);
            this.ordered = new Date("" + rs.getDate(5));
            this.payed = new Date("" + rs.getDate(6));
            this.sent = new Date("" + rs.getDate(7));
            this.costs = rs.getInt(8);
            this.state = rs.getInt(9);
            this.notation = rs.getString(10);
        }
        this.good = new Good(goodID);
        this.customer = new Customer(customerID);
        this.purchase = new Purchase(purchaseID);
        addListeners();
        this.customer.setOrder(this);
        this.good.setOrder(this);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Gets actual ID.
 *
 * @return ID as a number.
 */
    public int getID(){
        return this.orderID;
    }


/*******************************************************************************
 * Retrieves customer's full name.
 *
 * @return Customer's full name
 */
    public String getFullName(){
        return this.customer.getFullName();
    }


/*******************************************************************************
 * Gets Customer's nick name if he has one.
 *
 * @return Customer's nick name.
 */
    public String getNick(){
        return this.customer.getNick();
    }


/*******************************************************************************
 * Gets street name and street number of order's delivery address.
 *
 * @return Street name and street number
 */
    public String getStreet(){
        return this.customer.getStreet();
    }

    
/*******************************************************************************
 * Gets city name of order's delivery address.
 *
 * @return City name.
 */
    public String getCity(){
        return this.customer.getCity();
    }


/*******************************************************************************
 * Gets city post code of order's delivery address.
 *
 * @return City postcode.
 */
    public int getPostCode(){
        return this.customer.getPostcode();
    }


/*******************************************************************************
 * Gets a name of seller / provider / distributor.
 *
 * @return Name of seller / provider / distributor.
 */
    public String getSeller(){
        return this.purchase.getSeller();
    }


/*******************************************************************************
 * Sets new date when Good was bought.
 *
 * @param newBoughtDate New date.
 */
    public String getBoughtDate(){
        return this.purchase.getBoughtDate();
    }


/*******************************************************************************
 * Returns price payed for bought good.
 *
 * @return Price payed for bought good.
 */
    public int getPurchaseCost(){
        return purchase.getBoughtPrice();
    }


/*******************************************************************************
 * Gets all costs connected with order.
 *
 * @return Costs connected with order.
 */
    public int getCosts(){
        return this.costs;
    }


/*******************************************************************************
 * Retrieves notation for this order.
 *
 * @return This order's notation.
 */
    public String getNotation(){
        return this.notation;
    }


/*******************************************************************************
 * Returns date of Delivery to shop.
 *
 * @return Date of Delivery to shop.
 */
    public String getDeliveredDate(){
        return this.purchase.getDeliveredDate();
    }


/*******************************************************************************
 * Gets type of shipping.
 *
 * @return Text of shipping's type.
 */
    public String getShipping(){
        return good.getShipping();
    }


/*******************************************************************************
 * Gets cost of shipping.
 *
 * @return Cost for shipping.
 */
    public int getPostCost(){
        return good.getPostCost();
    }


/*******************************************************************************
 * Gets name of product.
 *
 * @return name of product.
 */
    public String getProductName(){
        return this.good.getProductName();
    }


/*******************************************************************************
 * Gets type of product specification.
 *
 * @return Type specification.
 */
    public String getTypeSpecification(){
        return this.good.getTypeSpecification();
    }


/*******************************************************************************
 * Gets cost of product.
 *
 * @return Cost of product.
 */
    public int getProductPrice(){
        return good.getProductPrice();
    }


/*******************************************************************************
 * Gets date when order was ordered.
 *
 * @return Date of order.
 */
    public String getOrderedDate(){
        if (this.ordered.isEmpty()){
            return "-";
        } else{
            return "" + this.ordered;
        }
    }


/*******************************************************************************
 * Returns date when customer payed.
 *
 * @return Date of customer's payment.
 */
    public String getPayedDate(){
        if (this.payed.isEmpty()){
            return "-";
        } else{
            return "" + this.payed;
        }
    }

/*******************************************************************************
 * Returns date when customer payed.
 *
 * @return Date when customer payed.
 */

    public String getSentDate(){
        if (this.sent.isEmpty()){
            return "-";
        } else{
            return "" + this.sent;
        }
    }


/*******************************************************************************
 * Gest order's state.
 *
 * @return Order's state.
 */
    public String getState(){
        switch(this.state){
            case 1 : return "Nevyřízeno";
            case 2 : return "Částečně vyřízeno";
            case 3 : return "Vyřízeno";
            default : return null;
        }
    }


/*******************************************************************************
 * Gest order's state index.
 *
 * @return Order's state index.
 */
    public int getIntState(){
        return this.state;
    }


/*******************************************************************************
 * Gets array of action listeners.
 *
 * @return Array of action listeners.
 */
    public Conservable[] getListener(){
        return this.listener;
    }


/*******************************************************************************
 * Changes customer's full name.
 *
 * @param newName Customer's new first name.
 * @param newSurename Customer's new surename.
 */
    public void setFullName(String newFirstName, String newSureName){
        customer.setName(newFirstName, newSureName);
    }


/*******************************************************************************
 * Changes customer's nick name.
 *
 * @param newNick Customer's new nick name.
 */
    public void setNick(String newNick){
        customer.setNick(newNick);
    }


/*******************************************************************************
 * Changes name and code of street where order should be delivered.
 *
 * @param newStreet New Name and number of street.
 */
    public void setStreet(String newStreet){
        customer.setStreet(newStreet);
    }


/*******************************************************************************
 * Changes city where order should be delivered.
 *
 * @param newCity New name of city.
 */
    public void setCity(String newCity){
        customer.setCity(newCity);
    }


/*******************************************************************************
 * Sets new postcode of city where order should be delivered
 *
 * @param newPostcode New city's postcode.
 */
    public void setPostCode(int newPostCode){
        customer.setPostcode(newPostCode);
    }


/*******************************************************************************
 * Changes Product's name.
 *
 * @param newProduct New name of product.
 */
    public void setProduct(String newProduct){
        good.setProductName(newProduct);
    }


/*******************************************************************************
 * Changes Type specification of product
 *
 * @param newProductType New Product's type specification.
 */
    public void setProductType(String newProductType){
        good.setProductType(newProductType);
    }


 /*******************************************************************************
 * Changes cost of Product.
 *
 * @param newPrice New cost of product.
 */
    public void setPrice(int newPrice){
        good.setPrice(newPrice);
    }


/*******************************************************************************
 * Changes name of seller / provider / distributor.
 *
 * @param newSeller New seller's name.
 */
    public void setSeller(String newSeller){
        this.purchase.setSeller(newSeller);
    }


/*******************************************************************************
 * Sets new date when Good was bought.
 *
 * @param newBoughtDate New date.
 */
    public void setBoughtDate(Date newBoughtDate){
        this.purchase.setBoughtDate(newBoughtDate);
    }


/*******************************************************************************
 * Changes price for bought Good.
 *
 * @param newPurchaseCost New price for bought Good.
 */
    public void setPurchaseCost(int newPurchaseCost){
        this.purchase.setBoughtPrice(newPurchaseCost);
    }


/*******************************************************************************
 * Changes total costs for current Order.
 *
 * @param newCosts New costs for order.
 */
    public void setCosts(int newCosts){
        this.costs = newCosts;
    }


/*******************************************************************************
 * Changes description of shipping.
 *
 * @param newShipping New description of shipping.
 */
    public void setShipping(String newShipping){
        this.good.setShipping(newShipping);
    }


/*******************************************************************************
 * Sets new cost of shipping.
 *
 * @param newPostCost New cost of shipping.
 */
    public void setPostCost(int newPostCost){
        this.good.setPostCost(newPostCost);
    }


/*******************************************************************************
 * Changes notation.
 *
 * @param newNotation New Notation.
 */
    public void setNotation(String newNotation){
        this.notation = newNotation;
    }


/*******************************************************************************
 * Sets new date of order creation.
 *
 * @param newOrderedDate New date of order creation.
 */
    public void setOrderedDate(Date newOrderedDate){
        this.ordered = newOrderedDate;
    }


/*******************************************************************************
 * Sets new date of payment from customer.
 *
 * @param newPayedDate New date of payment from customer.
 */
    public void setPayedDate(Date newPayedDate){
        this.payed = newPayedDate;
    }


/*******************************************************************************
 * Changes Date of delivery from seller to shop.
 *
 * @param newDeliveredDate Date of delivery from seller to shop.
 */
    public void setDeliveredDate(Date newDeliveredDate){
        this.purchase.setDeliveredDate(newDeliveredDate);
    }


/*******************************************************************************
 * Sets date when order was sent to customer.
 *
 * @param newSentDate New date of sent.
 */
    public void setSentDate(Date newSentDate){
        this.sent = newSentDate;
    }


/*******************************************************************************
 * Changes index state of order.
 *
 * @param newState New index state (1 - 3).
 */
    public void setState(int newState){
        this.state = newState;
    }

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "orders" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException{
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("UPDATE ngaccount.orders SET goods_id = " +
                    good.getID() + ", customers_id = " + customer.getID() + ", purchase_id = "
                    + purchase.getID() + ", orders_made = '" + this.ordered.getFormatForDB() +
                    "', orders_payed = '" + this.payed.getFormatForDB() + "', orders_sent = '"
                    + this.sent.getFormatForDB() + "', orders_costs = " + this.costs +
                    ", orders_state = " + this.state + ", orders_note = '" + 
                    this.notation + "' WHERE orders.orders_id = " + this.orderID);
    }


/*******************************************************************************
 * Inserts data into DB and table "orders".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException{
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

        int check = BusinessFacade.getInstance().checkIDinDB(this.good.getID(),
                this.customer.getID(), this.purchase.getID(), this.ordered.getFormatForDB(),
                this.payed.getFormatForDB(), this.sent.getFormatForDB(), this.costs, 
                this.notation, "orders", "goods_id", "customers_id", "purchase_id",
                "orders_made", "orders_payed", "orders_sent", "orders_costs", "orders_note");
        
        if (check == -1){
        stmtIn.executeUpdate("INSERT INTO ngaccount.orders (orders_id , goods_id, customers_id, " +
                "purchase_id, orders_made, orders_payed, orders_sent, orders_costs, orders_state, " +
                "orders_note) VALUES ('" + this.orderID + "', '" + good.getID() + "', '" + customer.getID() + "', '" +
                purchase.getID() + "', '" + this.ordered.getFormatForDB() + "', '" + this.payed.getFormatForDB() + "', '" +
                this.sent.getFormatForDB() + "', '" + this.costs + "', '" + this.state + "', '" + this.notation + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "orders".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException{
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        stmtIn.executeUpdate("DELETE FROM ngaccount.orders WHERE orders.orders_id = " +
                this.orderID + " LIMIT 1;");
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Adds childs listeners and itself into listeners field list.
 */
    private void addListeners() throws SQLException {
        Conservable[] listenerFromGood = this.good.getListener();
        this.listener[0] = listenerFromGood[0];
        this.listener[1] = listenerFromGood[1];
        this.listener[2] = listenerFromGood[2];
        this.listener[3] = listenerFromGood[3];
        this.listener[4] = listenerFromGood[4];
        this.listener[5] = this.customer;
        this.listener[6] = this.purchase;
        this.listener[7] = this;
    }


    
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
