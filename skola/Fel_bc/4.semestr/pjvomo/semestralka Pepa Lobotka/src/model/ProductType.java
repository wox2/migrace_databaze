package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * contains exact info of type about product.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class ProductType implements Conservable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * contains parent Object "ProductTypeRelation".
 */
    private ProductTypeRelation productTypeRelation;


/*******************************************************************************
 * contains ID used in DB table "type".
 */
    private int productTypeID;


/*******************************************************************************
 * Holds specify of product's type.
 */
    private String specification;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Constructor generates ID and avoids duplicity of ID's.
 *
 * @param productSpecification Specify of product's type.
 * @throws SQLException
 */
    public ProductType(String productSpecification) throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(productSpecification,
                "type", "type_name");
        //Find out if this record is in DB. If it is, it will set up ID correctly    
        if (check != -1) {
            this.productTypeID = check;
        } else {
            this.productTypeID = BusinessFacade.getInstance().lastIDInDB("type") + 1;
        }
        this.specification = productSpecification;
    }


/*******************************************************************************
 * Retrieves information about Type from DB based on given Type ID.
 *
 * @param typeID Type ID in DB table "type" from which new Type instance will be made.
 * @throws SQLException
 */
    public ProductType(int typeID) throws SQLException{
        this.productTypeID = typeID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.type " +
                "WHERE type_id = " + typeID);
        while (rs.next()) {
            this.specification = rs.getString(2);
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
        return this.productTypeID;
    }


/*******************************************************************************
 * Gets type of product specification.
 *
 * @return Type specification.
 */
    public String getProductSpecification(){
        return this.specification;
    }


/*******************************************************************************
 * Changes to new specification of product.
 *
 * @param newProductSpecification New product's type specification.
 */
    public void setProductSpecification(String newProductSpecification){
        this.specification = newProductSpecification;
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
 * Updates table "type" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException {
        int check = BusinessFacade.getInstance().checkIDinDB(this.specification,
                    "type", "type_name");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.productTypeID,
                this.productTypeRelation.getID(), "product_type", "type_id", "product_type_id");

        if (check != -1 && safeCheck == -1){

            if (check != this.productTypeID){
                int oldProductTypeID = this.productTypeID;
                this.productTypeID = check;
                productTypeRelation.update();

                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

                stmtIn.executeUpdate("DELETE FROM ngaccount.type WHERE type.type_id = " +
                        oldProductTypeID + " LIMIT 1;");
            }
        } else if(check != -1 && safeCheck != -1){
            this.productTypeID = check;
        } else if(check == -1 && safeCheck == -1){
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

            stmtIn.executeUpdate("UPDATE ngaccount.type SET type_name = '" +
                    this.specification + "' WHERE type.type_id = " + this.productTypeID);
        } else {
            this.productTypeID = BusinessFacade.getInstance().lastIDInDB("type") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "type".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException {
        int check = BusinessFacade.getInstance().checkIDinDB(this.specification,
                    "type", "type_name");
        //Insert only if record is not in DB.
        if (check == -1){
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

            stmtIn.executeUpdate("INSERT INTO ngaccount.type (type_id , type_name) " +
                "VALUES ('" + this.productTypeID + "', '" + this.specification + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "type".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException {
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.productTypeID,
                    "product_type", "type_id");
        
        if (safeCheck == -1){
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();

            stmtIn.executeUpdate("DELETE FROM ngaccount.type WHERE type.type_id = " +
                    this.productTypeID + " LIMIT 1;");
        }
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
