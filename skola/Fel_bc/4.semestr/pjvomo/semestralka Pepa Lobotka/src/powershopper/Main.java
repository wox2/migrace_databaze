package powershopper;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import view.Window;


/*******************************************************************************
 * Main class
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Main {
    
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================
//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================
//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================


/*******************************************************************************
 * Main method.
 *
 * @param args Arguments from command line. Deprecated.
 * @throws SQLException
 */
public static void main(String[] args) throws SQLException {

        Window win = null ;
        
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        win = new Window();
        win.setVisible(true);
        } catch (Throwable ex) {
            JOptionPane.showMessageDialog(win, ex.getMessage());
        }
        
}

}

