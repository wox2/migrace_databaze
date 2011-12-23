package model;

import java.text.SimpleDateFormat;
import java.util.Locale;

/*******************************************************************************
 * Class specifying date format
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Date {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


/*******************************************************************************
 * Contains number of day in Date.
 */
    private int day;


/*******************************************************************************
 * Contains number of Month in Date.
 */
    private int month;


/*******************************************************************************
 * Contains number of Year in Date.
 */
    private int year;

    

//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


/*******************************************************************************
 * Constructs Date from number of day, month and year.
 *
 * @param day Number of day.
 * @param month Number of month.
 * @param year Number of year.
 */
    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }


/*******************************************************************************
 * Constructs date from Date String in form : yyyy-mm-dd.
 *
 * @param dateFormatFromDB String in form yyyy-mm-dd.
 */
    public Date(String dateFormatFromDB){
        String[] items = dateFormatFromDB.split("-");
        this.year = Integer.valueOf(items[0]);
        this.month = Integer.valueOf(items[1]);
        this.day = Integer.valueOf(items[2]);
    }


/*******************************************************************************
 * Sets today's Date.
 */
    public Date(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
        java.util.Date format = new java.util.Date();
        String fullDate = date.format(format);
        String[] items = fullDate.split("-");

        this.day = Integer.valueOf(items[0]);
        this.month = Integer.valueOf(items[1]);
        this.year = Integer.valueOf(items[2]);
    }


/*******************************************************************************
 * Construct's default date : 11.11.1111 used for empty date in forms.
 *
 * @param isEmpty True for default date.
 */
    public Date(boolean isEmpty){
        if (isEmpty){
            this.day = 11;
            this.month = 11;
            this.year = 1111;
        }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================


/*******************************************************************************
 * Retrieves day from date.
 *
 * @return Number of day from Date.
 */
    public int getDay(){
        return this.day;
    }


/*******************************************************************************
 * Retrieves month from date.
 *
 * @return Number of month from date.
 */
    public int getMonth(){
        return this.month;
    }


/*******************************************************************************
 * Gets year from date.
 *
 * @return Number of year from date.
 */
    public int getYear(){
        return this.year;
    }


/*******************************************************************************
 * Makes from default date format special format for MySQL DataBase.
 *
 * @return Special format for MySQL DataBase.
 */
    public String getFormatForDB(){
        String reformattedDay, reformattedMonth;
        if (this.day > 0 && this.day < 10){
            reformattedDay = "0" + this.day;
        } else {
            reformattedDay = "" + this.day;
        }
        if (this.month > 0 && this.month < 10){
            reformattedMonth = "0" + this.month;
        } else {
            reformattedMonth = "" + this.month;
        }
        return this.year + "-" + reformattedMonth + "-" + reformattedDay;
    }


/*******************************************************************************
 * Changes day in date.
 *
 * @param newDay New number of day in date.
 */
    public void setDay(int newDay){
        this.day = newDay;
    }


/*******************************************************************************
 * Changes month in date.
 *
 * @param newMonth New number of month in date.
 */
    public void setMonth(int newMonth){
        this.month = newMonth;
    }


/*******************************************************************************
 * Changes year in date.
 *
 * @param newYear New number of year in date.
 */
    public void setYear(int newYear){
        this.year = newYear;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


/*******************************************************************************
 * Casual format of date : dd.mm.yyyy
 *
 * @return Classic format of date in String.
 */
    @Override
    public String toString(){
        return this.day + "." + this.month + "." + this.year;
    }


/*******************************************************************************
 * Tests if date contains "empty date format" 11-11-1111.
 *
 * @return True if contains empty date format.
 */
    public boolean isEmpty(){
        if (this.day == 11 && this.month == 11 && this.year == 1111) {
            return true;
        } return false;
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================

}
