
package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*******************************************************************************
 * About this app - information unmodal window.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class About extends JDialog {
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


    // Containers
    Container mainCont;



//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


    public About(JFrame parent) {
        super(parent, "Power shopper - About", false);

        this.setSize(300, 200);
        // Centralise the main window
        int windowLocationX = getToolkit().getScreenSize().width/2 - this.getWidth()/2;
        int windowLocationY = getToolkit().getScreenSize().height/2 - this.getHeight()/2;

        this.setLocation(windowLocationX, windowLocationY);

        this.setResizable(true);

        getInfo();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Gets information about PowerShopper app.
 */
    private void getInfo(){
        mainCont = this.getContentPane();
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        
        mainCont.setLayout(fl);

        JLabel info = new JLabel();

        info.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        info.setText("<html>" +
                "<head></head>" +
                "<body>" +
                "<u><b>PowerShopper Application v 0.9</u></b>" +
                "<br /><br />" +
                "<b>Version</b>: <i>0.9519 RC</i>" +
                "<br />" +
                "<b>Creator</b>: Josef Lobotka" +
                "<br />" +
                "<b>Contact mail</b>: ng.inornate@gmail.com" +
                "<br />" +
                "<b>Requirements</b>: " +
                "<br />" +
                "- Java Runtime Environment" +
                "<br />" +
                "- Usable Operation System" +
                "<br />" +
                "- Internet Connection." +
                "</body>" +
                "</html>");

        mainCont.add(info);
    }



//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================

}
