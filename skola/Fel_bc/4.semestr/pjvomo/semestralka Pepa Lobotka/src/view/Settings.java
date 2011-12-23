package view;

import controller.DatabaseConnector;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

/*******************************************************************************
 * All settings modal window.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Settings extends JDialog {

//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================


    Container mainComp;

    // DB connector singleton
    private DatabaseConnector dbConn = DatabaseConnector.getInstance();

    // Buttons
    private JButton ok = new JButton("Save settings");
    private JButton reset = new JButton("Reset to default");
    private JButton cancel = new JButton("Cancel");

    // Textfields
    private JTextField dbURI = new JTextField(dbConn.getURI());
    private JTextField dbName = new JTextField(dbConn.getUser());
    private JPasswordField dbPass = new JPasswordField(dbConn.getPass());


//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================

    public Settings(JFrame parent){
        super(parent, "Power shopper - settings", true);

        this.setSize(400, 160);
        // Centralise the main window
        int windowLocationX = getToolkit().getScreenSize().width/2 - this.getWidth()/2;
        int windowLocationY = getToolkit().getScreenSize().height/2 - this.getHeight()/2;

        this.setLocation(windowLocationX, windowLocationY);

        this.setResizable(false);

        // Initialize graphics & components
        initialize();

        // Activate components listeners
        addListenersToComponents();
    }


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Initialize all dialog's GUI.
 */
    private void initialize(){
        // Main component
        mainComp = this.getContentPane();

        // Layout settings
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        mainComp.setLayout(fl);

        // Adding labels and textboxes
        mainComp.add(new JLabel("Database URI:"));
        dbURI.setColumns(20);
        mainComp.add(dbURI);
        mainComp.add(new JLabel("Database username:"));
        dbName.setColumns(10);
        mainComp.add(dbName);
        mainComp.add(new JLabel("Database password:"));
        dbPass.setColumns(10);
        mainComp.add(dbPass);
        mainComp.add(Box.createRigidArea(new Dimension(400, 20)));

        // Adding buttons
        mainComp.add(ok);
        mainComp.add(reset);
        mainComp.add(cancel);
        
    }


/*******************************************************************************
 * Saves changes made on this settings dialog.
 */
    private void saveSettings(){
        int dialogResult = JOptionPane.showOptionDialog(this, "Are you sure you want to change your default database settings?",
            "Save database settings", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (dialogResult == 0){
            try {
                dbConn.newConnection(dbURI.getText(), dbName.getText(), new String(dbPass.getPassword()));
                exit();
            } catch (SQLException ex) {
                try {
                    JOptionPane.showMessageDialog(this, "Could not connect to database. Check your database access settings again.");
                    ex.printStackTrace();
                    dbConn.setDefaultConnection();
                } catch (SQLException exSecond) {
                    JOptionPane.showMessageDialog(this, "Could not establish default connection.");
                    exSecond.printStackTrace();
                }
            }
        }
    }


/*******************************************************************************
 * Resets values of this settings dialog.
 */
    private void setDefaultSettings() throws SQLException{
        dbConn.setDefaultConnection();
        dbURI.setText(dbConn.getURI());
        dbPass.setText(dbConn.getPass());
        dbURI.setText(dbConn.getURI());
    }


/*******************************************************************************
 * Closes dialog.
 */
    private void exit(){
        this.setVisible(false);
    }

    
    
//== BUTTONS LISTENERS =========================================================

    private void addListenersToComponents(){
        cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    exit();
		}
	});

        ok.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    saveSettings();
		}
	});

        reset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                try {
                    setDefaultSettings();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(Settings.this, "Could not establish default connection.");
                    ex.printStackTrace();
                }
		}
	});
    }
}
