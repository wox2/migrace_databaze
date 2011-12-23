package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * contains info about kind of Product.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Product implements Conservable {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains parent Object "ProductTypeRelation".
 */
    private ProductTypeRelation productTypeRelation;

    

/*******************************************************************************
 * contains ID used in DB table "product".
 */
    private int productID;


/*******************************************************************************
 * Describes kind of product.
 */
    private String name;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Constructor generates ID and avoids duplicity of ID's.
 *
 * @param productName Names kind of product.
 * @throws SQLException
 */
    public Product(String productName) throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(productName, "product", "product_name");
        if (check != -1) {
            this.productID = check;
        } else {
            this.productID = BusinessFacade.getInstance().lastIDInDB("product") + 1;
        }
        this.name = productName;
    }


/*******************************************************************************
 * Retrieves information about Product from DB based on given Product ID.
 *
 * @param productID Product ID in DB table "product" from which new Product instance
 *                  will be made.
 * @throws SQLException
 */
    public Product(int productID) throws SQLException {
        this.productID = productID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.product " +
                "WHERE product_id = " + productID);
        while (rs.next()) {
            this.name = rs.getString(2);
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
        return this.productID;
    }


/*******************************************************************************
 * Gets name of product.
 *
 * @return name of product.
 */
    public String getName(){
        return this.name;
    }


/*******************************************************************************
 * Changes kind of product.
 *
 * @param newProductName New product's name.
 */
    public void setName(String newProductName){
        this.name = newProductName;
    }


/*******************************************************************************
 * Sets parent's Object "ProductRelationType".
 *
 * @param productRelationType Parent's object.
 */
   public void setProductRelationType(ProductTypeRelation productRelationType){
       this.productTypeRelation = productRelationType;
   }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "product" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(this.name,
                    "product", "product_name");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.productID,
                this.productTypeRelation.getID(), "product_type", "product_id", "product_type_id");

        if(check != -1 && safeCheck == -1){
            if (check != this.productID){
                int oldProductID = this.productID;
                this.productID = check;
                productTypeRelation.update();
                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
                stmtIn.executeUpdate("DELETE FROM ngaccount.product WHERE product_id = " +
                oldProductID + " LIMIT 1;");
            }
        } else if(check != -1 && safeCheck != -1){
            this.productID = check;
        } else if(check == -1 && safeCheck == -1){
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

            stmtIn.executeUpdate("UPDATE ngaccount.product SET product_name = '" +
                    this.name + "' WHERE product_id = " + this.productID);
        } else {
            this.productID = BusinessFacade.getInstance().lastIDInDB("product") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "product".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(this.name, "product", "product_name");
        if (check == -1){
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        stmtIn.executeUpdate("INSERT INTO ngaccount.product (product_id , product_name) " +
                "VALUES ('" + this.productID + "', '" + this.name + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "product".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException{
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.productID,
                "product_type", "product_id");
        if (safeCheck == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("DELETE FROM ngaccount.product WHERE product.product_id = " +
                    this.productID + " LIMIT 1;");
        }
    }


    
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
