
package model;

/*******************************************************************************
 * Contains full, personal name (surename + name)
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Name {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * Contains customer's firstname.
 */
    private String name;


/*******************************************************************************
 * Contains customer's surename.
 */
    private String surename;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================

/*******************************************************************************
 * Makes instance representing customer's name.
 *
 * @param name Customer's first name.
 * @param surename Customer's surename.
 */
    public Name(String name, String surename){
        this.name = name;
        this.surename = surename;
    }


/*******************************************************************************
 * From String representing full, customer's name makes instance with first
 * name and surename attributes.
 *
 * @param fullName Customer's full name
 */
    public Name(String fullName){
        String[] items = fullName.split(" ");
        this.name = items[0];
        this.surename = items[1];
    }


    
//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Retrieves customer's first name.
 *
 * @return Customer's first name.
 */
    public String getName(){
        return this.name;
    }


/*******************************************************************************
 * Retrieves customer's sure name.
 *
 * @return Customer's sure name.
 */
    public String getSurename(){
        return this.surename;
    }


/*******************************************************************************
 * Changes customer's first name.
 *
 * @param newName New customer's first name.
 */
    public void setName (String newName){
        this.name = newName;
    }


/*******************************************************************************
 * Changes customer's sure name.
 *
 * @param newName New customer's sure name.
 */
    public void setSurename (String newSurename){
        this.surename = newSurename;
    }


    
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

/*******************************************************************************
 * Converse attributes first name and surename to full, customer's name.
 *
 * @return full customer's name.
 */
    @Override
    public String toString(){
        return this.name + " " + this.surename;
    }


    
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
}
