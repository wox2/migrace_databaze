package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/*******************************************************************************
 * Extends basic label and upgrades it's view.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class LabelBold extends JLabel{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


    public LabelBold(String text){
    this.setText(text);
    this.setFont(new Font("Tahoma", 1, 11));
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================


public static void main(String[] args) {

}



}
