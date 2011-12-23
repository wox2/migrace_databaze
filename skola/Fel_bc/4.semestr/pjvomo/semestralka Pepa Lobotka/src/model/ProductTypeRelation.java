package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Holds info about relation of product and type.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class ProductTypeRelation implements Conservable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains parent Object "Good".
 */
    private Good good;


/*******************************************************************************
 * contains ID used in DB table "product_type".
 */
    private int productTypeRelationID;


/*******************************************************************************
 * Instance of Product's type specification.
 */
    private ProductType type;


/*******************************************************************************
 * Instance of Product's name.
 */
    private Product product;


/*******************************************************************************
 * Cost of Product.
 */
    private int cost;


/*******************************************************************************
 * Holds array of active objects using to make actions on these objects.
 */
    private Conservable[] listener = new Conservable[2];


//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Describes relation between cost, product and product's type and avoids dupli-
 * city of ID's or data in DB
 *
 * @param typeOfProduct Type of product.
 * @param product Product's name.
 * @param cost Product!s cost.
 * @throws SQLException
 */
    public ProductTypeRelation(ProductType typeOfProduct, Product product, int cost) throws SQLException{
        this.type = typeOfProduct;
        this.product = product;
        this.cost = cost;
        int check = BusinessFacade.getInstance().checkIDinDB(product.getID(),
                typeOfProduct.getID(), cost, "product_type", "product_id", "type_id", "product_type_price");
        if (check != -1) {
            this.productTypeRelationID = check;
        } else {
            this.productTypeRelationID = BusinessFacade.getInstance().lastIDInDB("product_type") + 1;
        }
        addListeners();
        product.setProductRelationType(this);
        typeOfProduct.setProductRelationType(this);
    }


/*******************************************************************************
 * Retrieves information about Product-type relation from DB based on given
 * ProductTypeRelation ID.
 *
 * @param productTypeRelationID Product-Type ID in DB table "product-type" from
 *                              which new ProductTypeRelation instance will be made.
 * @throws SQLException
 */
    public ProductTypeRelation(int productTypeRelationID) throws SQLException{
        int productID = 0, typeID = 0;
        this.productTypeRelationID = productTypeRelationID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.product_type " +
                "WHERE product_type_id = " + productTypeRelationID);
        while (rs.next()) {
            productID = rs.getInt(2);
            typeID = rs.getInt(3);
            this.cost = rs.getInt(4);
        }
        this.product = new Product(productID);
        this.type = new ProductType(typeID);
        addListeners();
        this.product.setProductRelationType(this);
        this.type.setProductRelationType(this);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Gets actual ID.
 *
 * @return ID as a number.
 */
    public int getID(){
        return this.productTypeRelationID;
    }


/*******************************************************************************
 * Gets cost of product.
 *
 * @return Cost of product.
 */
    public int getCost(){
        return this.cost;
    }


/*******************************************************************************
 * Gets name of product.
 *
 * @return Name of product.
 */
    public String getProductName(){
        return this.product.getName();
    }


/*******************************************************************************
 * Gets type of product specification.
 *
 * @return Type specification.
 */
    public String getTypeSpecification(){
        return this.type.getProductSpecification();
    }

    

/*******************************************************************************
 * Changes cost of Product.
 *
 * @param newCost New cost of product.
 */
    public void setCost(int newCost){
        this.cost = newCost;
    }


/*******************************************************************************
 * Changes Product name.
 *
 * @param newProductName New Product's name.
 */
    public void setProductName(String newProductName){
        product.setName(newProductName);
    }


/*******************************************************************************
 * Changes Product's type specification.
 *
 * @param newProductTypeSpecification  New product's type specification.
 */
    public void setProductTypeSpecification(String newProductTypeSpecification){
        type.setProductSpecification(newProductTypeSpecification);
    }


/*******************************************************************************
 * Sets parent's Object "Good".
 *
 * @param Good Parent's object.
 */
    public void setGood(Good good){
        this.good = good;
    }


/*******************************************************************************
 * Gets array of action listeners.
 *
 * @return Array of action listeners.
 */
    public Conservable[] getListener(){
        return listener;
    }

    

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "product_type" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(this.product.getID(),
                this.type.getID(), this.cost, "product_type", "product_id", "type_id", "product_type_price");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.productTypeRelationID,
                this.good.getID(), "goods", "product_type_id", "goods_id");

        if (check != -1 && safeCheck == -1){
            if (check != this.productTypeRelationID){
                int oldProductTypeRelationID = this.productTypeRelationID;
                this.productTypeRelationID = check;
                this.good.update();
                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

                stmtIn.executeUpdate("DELETE FROM ngaccount.product_type WHERE product_type.product_type_id = " +
                oldProductTypeRelationID + " LIMIT 1;");
            }
        } else if(check != -1 && safeCheck != -1) {
            this.productTypeRelationID = check;
        } else if(check == -1 && safeCheck == -1){
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

            stmtIn.executeUpdate("UPDATE ngaccount.product_type SET product_id = " +
                    this.product.getID() + ", type_id = " + this.type.getID() +
                    ", product_type_price = " + this.cost + " WHERE product_type.product_type_id = "
                    + this.productTypeRelationID);
        } else {
            this.productTypeRelationID = BusinessFacade.getInstance().lastIDInDB("product_type") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "product_type".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(this.product.getID(),
                this.type.getID(), this.cost, "product_type", "product_id", "type_id", "product_type_price");
        if (check == -1){
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
        
        stmtIn.executeUpdate("INSERT INTO ngaccount.product_type (product_type_id , product_id, type_id, product_type_price) " +
                "VALUES ('" + this.productTypeRelationID + "', '" + this.product.getID() +
                "', '" + this.type.getID() + "', '" + this.cost + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "product_type".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException{
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.productTypeRelationID,
                    "goods", "product_type_id");

        if (safeCheck == -1){
        Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

        stmtIn.executeUpdate("DELETE FROM ngaccount.product_type WHERE product_type.product_type_id = " +
        this.productTypeRelationID + " LIMIT 1;");
        }
    }


    
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Adds childs listeners into listeners field list.
 */
    private void addListeners(){
        listener[0] = this.product;
        listener[1] = this.type;
    }



//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
