package model;

import controller.BusinessFacade;
import controller.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*******************************************************************************
 * Info about delivery costs and shipment
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Post implements Conservable {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

 
 /*******************************************************************************
  * contains parent Object "Good".
  */
    private Good good;


/*******************************************************************************
 * contains ID used in DB table "post".
 */
    private int postID;


/*******************************************************************************
 * Holds type of shipping.
 */
    private String type;


/*******************************************************************************
 * Cost of certain shipping.
 */
    private int cost;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Constructs instance with shipping type and cost for shipping. In addition to
 * this, constructor generates ID and avoids duplicity of ID's.
 *
 * @param type Type of shipping.
 * @param cost Cost of shipping.
 * @throws SQLException
 */
    public Post(String type, int cost) throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(type, cost, "post", "post_type", "post_cost");
        if (check != -1) {
            this.postID = check;
        } else {
            this.postID = BusinessFacade.getInstance().lastIDInDB("post") + 1;
        }
        this.cost = cost;
        this.type = type;
    }


/*******************************************************************************
 * Retrieves information about Shipping from DB based on given Post ID.
 *
 * @param postID Post ID in DB table "post" from which new Post instance will be
 *               made.
 * @throws SQLException
 */
    public Post(int postID) throws SQLException{
        this.postID = postID;
        Statement stmtOut = DatabaseConnector.getInstance().getStatementOut();
        ResultSet rs = stmtOut.executeQuery("SELECT * FROM ngaccount.post " +
                "WHERE post_id = " + postID);
        while (rs.next()) {
            this.type = rs.getString(2);
            this.cost = rs.getInt(3);
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
        return this.postID;
    }


/*******************************************************************************
 * Gets type of shipping.
 *
 * @return Text of shipping's type.
 */
    public String getType(){
        return this.type;
    }


/*******************************************************************************
 * Gets cost of shipping.
 *
 * @return Cost for shipping.
 */
    public int getCost(){
        return this.cost;
    }


/*******************************************************************************
 * Changes type of shipping.
 *
 * @param newType New type of shipping.
 */
    public void setType(String newType){
        this.type = newType;
    }


/*******************************************************************************
 * Changes cost of shipping.
 *
 * @param newCost New cost of shipping.
 */
    public void setCost(int newCost){
        this.cost = newCost;
    }

    
/*******************************************************************************
 * Sets parent's Object "Good".
 *
 * @param Good Parent's object.
 */
    public void setGood(Good good){
        this.good = good;
    }
    


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Updates table "post" in DB and make appropriate changes.
 *
 * @throws SQLException
 */
    public void update() throws SQLException{
        int check = BusinessFacade.getInstance().checkIDinDB(this.type, this.cost,
                "post", "post_type", "post_cost");
        int safeCheck = BusinessFacade.getInstance().checkChildsIDinDB(this.postID,
                this.good.getID(), "goods", "post_id", "goods_id");
        if (check != -1 && safeCheck == -1) {
            if (check != this.postID) {
                Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
                int oldPostID = this.postID;
                this.postID = check;
                good.update();
                stmtIn.executeUpdate("DELETE FROM ngaccount.post WHERE post.post_id = " +
                        oldPostID + " LIMIT 1;");
            }
        } else if (check != -1 && safeCheck != -1) {
            this.postID = check;
        } else if (check == -1 && safeCheck == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("UPDATE ngaccount.post SET post_type = '" +
                    this.type + "', post_cost = " + this.cost +
                    " WHERE post.post_id = " + this.postID);
        } else {
            this.postID = BusinessFacade.getInstance().lastIDInDB("post") + 1;
            this.insert();
        }
    }


/*******************************************************************************
 * Inserts data into DB and table "post".
 *
 * @throws SQLException
 */
    public void insert() throws SQLException{
        //Find out if this record is in DB. If it is, it will change ID
        int check = BusinessFacade.getInstance().checkIDinDB(this.type, this.cost, "post", "post_type", "post_cost");
        if (check == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("INSERT INTO ngaccount.post (post_id , post_type, post_cost) " +
                    "VALUES ('" + this.postID + "', '" + this.type + "', '" + this.cost + "');");
        }
    }


/*******************************************************************************
 * Safely deletes record at DB in table "post".
 *
 * @throws SQLException
 */
    public void delete() throws SQLException{
        int safeCheck = BusinessFacade.getInstance().checkIDinDB(this.postID,
                "goods", "post_id");
        if (safeCheck == -1) {
            Statement stmtIn = DatabaseConnector.getInstance().getStatementIn();
            stmtIn.executeUpdate("DELETE FROM ngaccount.post WHERE post.post_id = " +
                    this.postID + " LIMIT 1;");
        }
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
