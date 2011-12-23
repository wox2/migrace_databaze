package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * contains whole info about ordered GOOD
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Good implements Conservable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains parent Object "Order".
 */
    private Order order;


/*******************************************************************************
 * contains ID used in DB table "goods".
 */
    private int goodID;


/*******************************************************************************
 * Instance of ProductTypeRelation class.
 *
 */
    private ProductTypeRelation productRelation;


/*******************************************************************************
 * Instance of Post class.
 *
 */
    private Post post;

    

/*******************************************************************************
 * Stores items of order in order to make some action in the future. It's impor-
 * tant to use EXACT order !
 * 
 */
    private Conservable[] listener = new Conservable[5];



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Contains info about good and shipping for it and avoids dupli city of ID's
 * or data in DB.
 *
 * @param productRelation Instance of class containing info between Product name
 *                        and product type + it's price.
 * @param post Post instance with info about shipping and shipping price.
 * @throws SQLException
 */
    public Good(ProductTypeRelation productRelation, Post post) throws SQLException{
        this.post = post;
        this.productRelation = productRelation;
        int check = BusinessFacade.getInstance().checkIDinDB(productRelation.getID(),
                post.getID(), "goods", "product_type_id", "post_id");
        if (check != -1) {
            this.goodID = check;
        } else {
            this.goodID = BusinessFacade.getInstance().lastIDInDB("goods") + 1;
        }
        addListeners();
        this.productRelation.setGood(this);
        this.post.setGood(this);
    }

/*******************************************************************************
 * Retrieves information about good from DB based on given Good ID.
 *
 * @param goodID Good ID in DB table "goods" from which new Good instance will be
 *               made.
 * @throws SQLException
 */
    public Good (int goodID) throws SQLException{
        int postID = 0, productRelationID = 0;
        this.goodID = goodID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.goods " +
                "WHERE goods_id = " + goodID);
        while (rs.next()) {
            productRelationID = rs.getInt(2);
            postID = rs.getInt(3);
        }
        this.productRelation = new ProductTypeRelation(productRelationID);
        this.post = new Post(postID);
        addListeners();
        this.productRelation.setGood(this);
        this.post.setGood(this);
    }


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Gets actual ID.
 *
 * @return ID as a number.
 */
    public int getID(){
        return this.goodID;
    }


/*******************************************************************************
 * Gets name of product.
 *
 * @return Name of product.
 */
    public String getProductName(){
        return productRelation.getProductName();
    }


/*******************************************************************************
 * Gets type of product specification.
 *
 * @return Type specification.
 */
    public String getTypeSpecification(){
        return productRelation.getTypeSpecification();
    }


/*******************************************************************************
 * Gets cost of product.
 *
 * @return Cost of product.
 */
    public int getProductPrice(){
        return this.productRelation.getCost();
    }


/*******************************************************************************
 * Gets a cost of shipping.
 *
 * @return Exact cost of shipping.
 */
    public int getPostCost(){
        return this.post.getCost();
    }


/*******************************************************************************
 * Describes a type of shipping.
 *
 * @return Type of shipping.
 */
    public String getShipping(){
        return this.post.getType();
    }


/*******************************************************************************
 * Gets array of action listeners.
 *
 * @return Array of action listeners.
 */
    public Conservable[] getListener() throws SQLException{
        return listener;
    }


/*******************************************************************************
 * Changes Product's name.
 *
 * @param newProductName New name of product.
 */
    public void setProductName(String newProductName){
        productRelation.setProductName(newProductName);
    }


/*******************************************************************************
 * Changes Type specification of product
 *
 * @param newProductType New Product's type specification.
 */
    public void setProductType(String newProductType){
        productRelation.setProductTypeSpecification(newProductType);
    }


 /*******************************************************************************
 * Changes cost of Product.
 *
 * @param newPrice New cost of product.
 */
    public void setPrice(int newPrice){
        productRelation.setCost(newPrice);
    }


/*******************************************************************************
 * Changes description of shipping.
 *
 * @param newShipping New description of shipping.
 */
    public void setShipping(String newShipping){
        post.setType(newShipping);
    }


/*******************************************************************************
 * Sets new cost of shipping.
 *
 * @param newPostCost New cost of shipping.
 */
    public void setPostCost(int newPostCost){
        post.setCost(newPostCost);
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
 * Updates table "goods" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException {
        int check = BusinessFacade.getInstance().checkIDinDB(this.productRelation.getID(),
                this.post.getID(), "goods", "product_type_id", "post_id");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.goodID,
                this.order.getID(), "orders", "goods_id", "orders_id");

        if (check != -1 && safeCheck == -1) {
            if (check != this.goodID) {
                int oldGoodID = this.goodID;
                this.goodID = check;
                this.order.update();
                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

                stmtIn.executeUpdate("DELETE FROM ngaccount.goods WHERE goods.goods_id = " +
                        oldGoodID + " LIMIT 1;");
            }
        } else if (check != -1 && safeCheck != -1) {
            this.goodID = check;
        } else if (check == -1 && safeCheck == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("UPDATE ngaccount.goods SET product_type_id = " +
                    this.productRelation.getID() + ", post_id = " + this.post.getID() +
                    " WHERE goods.goods_id = " + this.goodID);
        } else {
            this.goodID = BusinessFacade.getInstance().lastIDInDB("product_type") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "goods".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

        int check = BusinessFacade.getInstance().checkIDinDB(this.productRelation.getID(),
                this.post.getID(), "goods", "product_type_id", "post_id");
        if (check == -1){
        stmtIn.executeUpdate("INSERT INTO ngaccount.goods (goods_id , product_type_id, post_id) " +
                "VALUES ('" + this.goodID + "', '" + this.productRelation.getID() +
                "', '" + this.post.getID() + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "goods".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException {
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.goodID,
                "orders", "goods_id");
        if (safeCheck == -1) {
            stmtIn.executeUpdate("DELETE FROM ngaccount.goods WHERE goods.goods_id = " +
                    this.goodID + " LIMIT 1;");
        }
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Adds childs listeners and itself into listeners field list.
 */
    private void addListeners(){
        Conservable[] listenerFromProductTypeRelation = this.productRelation.getListener();
        this.listener[0] = listenerFromProductTypeRelation[0];
        this.listener[1] = listenerFromProductTypeRelation[1];
        this.listener[2] = this.post;
        this.listener[3] = this.productRelation;
        this.listener[4] = this;
    }



//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
