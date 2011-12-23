package view;

import controller.BusinessFacade;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.xml.parsers.ParserConfigurationException;
import model.Date;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Order;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.xml.sax.SAXException;

/*******************************************************************************
 * Main GUI Window.
 *
 * @author NeoGenet1c
 * @version 0.9
 */
public class Window extends JFrame implements Runnable{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    // Business facade
    private BusinessFacade facade = BusinessFacade.getInstance();

    // Scroll window
    private JScrollPane scrollPane = new JScrollPane();

    // Main table for orders from DB
    private JTable mainTable = new JTable();

    // Containers
    private JPanel higherContainer = new JPanel();
    private JPanel mainContainer = new JPanel();
    private JPanel newOrderContainer = new JPanel();
    private JPanel lowerContainer = new JPanel();
    private JPanel editOrderContainer = new JPanel();
    private JPanel showOrderInfoContainer = new JPanel();
    private JPanel editOrderButtonsContainer;
    private JPanel newOrderButtonsContainer;
    private JPanel showOrderButtonsContainer;

    // Main system info labels
    private JLabel labelSystemMSG = new JLabel();
    private JLabel labelValidityError = new JLabel();
    private JLabel labelValidityErrorEdit = new JLabel();
    private JLabel labelShowOrderForm = new JLabel();

    // Selector box
    private JComboBox stateChange;

    // Textfields
    JTextField  firstName = new JTextField();
    JTextField  sureName = new JTextField();
    JTextField  nick = new JTextField();
    JTextField  street = new JTextField();
    JTextField  streetCode = new JTextField();
    JTextField  city = new JTextField();
    JTextField  postCode = new JTextField();
    JTextField  product = new JTextField();
    JTextField  productType = new JTextField();
    JTextField  price = new JTextField();
    JTextField  seller = new JTextField();
    JTextField  bought = new JTextField();
    JTextField  purchaseCost = new JTextField();
    JTextField  shipping = new JTextField();
    JTextField  postCost = new JTextField();
    JTextField  costs = new JTextField();
    JTextField  notation = new JTextField();
    JTextField  ordered = new JTextField();
    JTextField  payed = new JTextField();
    JTextField  delivered = new JTextField();
    JTextField  sent = new JTextField();

    // Buttons
    private JButton buttonShow = new JButton("Show order details..");
    private JButton buttonLoad = new JButton("Load all orders");
    private JButton buttonEdit = new JButton("Edit order");
    private JButton buttonMake = new JButton("Make new order");
    private JButton buttonDelete = new JButton("Delete order");
    private JButton buttonNewOrderOK = new JButton("Create new order");
    private JButton buttonNewOrderCancel = new JButton("Storno order");
    private JButton buttonEditOrderSubmit = new JButton("Change order");
    private JButton buttonEditOrderReset = new JButton("Remove changes");
    private JButton buttonEditOrderCancel = new JButton("Cancel editation");
    private JButton buttonShowOK = new JButton("Go back");
    private JButton buttonShowEdit = new JButton("Edit order");

    // Menubar + Menus
    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuFile = new JMenu("File");
    private JMenu menuSettings = new JMenu("Options");
    private JMenu menuAbout = new JMenu("About");

    // Submenus
    private JMenuItem menuItemConnect = new JMenuItem("Load all orders...");
    private JMenuItem menuItemAllSettings = new JMenuItem("Connection settings...");
    private JMenuItem menuItemTruncate = new JMenuItem("Delete all orders");
    private JMenuItem menuItemCreate = new JMenuItem("Create new order...");
    private JMenuItem menuItemImportXML = new JMenuItem("Import orders from XML");
    private JMenuItem menuItemExit = new JMenuItem("Exit");
    private JMenuItem menuItemAbout = new JMenuItem("About");

    // Orders data for table
    private ArrayList<String[]> tableData = new ArrayList<String[]>();
    private String[] tableHeader = {"VS", "Jméno[nick]", "Product", "Type", "Price", "Ordered",
    "Received", "Payed", "Sent", "State", "Info"};

    // GUI Settings signs
    private boolean isTableOn = false, isAEDButtonsOn = false, isMainContainerOn = true,
                    isAEDButtonsEnabled = false, isMainButtonsOn = true, isEmpty = false,
                    isLenghtOK = true, isDate = true, isInteger = true;

    // Additional Settings
    int orderIDFromTable = -1;
    int sysMSG = 0;
    int runIndex = -1;
    String validationErrorMessage;
    Border defaultJTextBorder = new JTextField().getBorder();


//== CLASS GETTERS & SETTERS ===================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== CONSTRUCTORS & FACTORY METHODS ============================================


    public Window() throws SQLException{
        // Sets main windows parameters
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 550));
        this.setSize(1000, 700);
        this.setTitle("Power shopper v0.9");
        this.setResizable(true);

        // Centralise the main window
        int windowLocationX = getToolkit().getScreenSize().width/2 - this.getWidth()/2;
        int windowLocationY = getToolkit().getScreenSize().height/2 - this.getHeight()/2;

        this.setLocation(windowLocationX, windowLocationY);        

        // Adds components, panels and listeners
        addPanels();
        setLayouts();
        setComponents();
        addComponents();
        setMenu();
        addListenersToComponents();
        updateAll();

        pack();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS & SETTERS ================================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
    
    
    public void run() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        switch(runIndex){
            case 1: runLoadSysMSG("Loading...");
                    this.doLoadAllOrders();
                    break;
            case 2: this.doSaveOrder();
                    break;
            case 3: runLoadSysMSG("Deleting...");
                    this.doDeleteOrder();
                    break;
            case 4: runLoadSysMSG("Creating...");
                    this.doCreateOrder();
                    break;
            case 5: this.doOrderEditCancel();
                    break;
            case 6: runLoadSysMSG("Importing XML file... Please be patient!");
                    this.importFromXML();
                    break;
            case 7: runLoadSysMSG("Loading for editation...");
                    this.doEditOrder();
                    break;
            case 8: runLoadSysMSG("Deleting all orders...");
                    this.truncateCurrentDB();
                    break;
            default:break;
        }        
        
        this.setCursor(Cursor.getDefaultCursor());
    }



//== PRIVATE & HELPFUL CLASS METHODS ===========================================
//== GUI ACTIVE ITEMS LISTENERS ================================================


/*******************************************************************************
 * Intializes listeners to GUI components.
 *
 */
    private void addListenersToComponents(){       

        menuItemConnect.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(1);
		}
	});


        buttonLoad.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(1);
		}
	});

        buttonMake.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    Window.this.doNewOrder();
		}
	});

        menuItemCreate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
                {
                    Window.this.doNewOrder();
		}
	});

        menuItemExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    exitApplication();
		}
	});

        menuItemImportXML.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(6);
		}
	});

        menuItemAllSettings.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    showSettings();
		}
	});


        menuItemTruncate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    deleteAllOrders();
		}
	});


        menuItemAbout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    showAbout();
		}
	});

        addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e)
                {
                    exitApplication();
                }
        });

        buttonNewOrderOK.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(4);
		}
	});

        buttonEditOrderSubmit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(2);
		}
	});

        buttonEditOrderCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    runThread(5);
		}
	});

        buttonEditOrderReset.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    editReset();
		}
	});

        buttonNewOrderCancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
                    createOrderCancel();
		}
	});

        buttonShow.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e)
                {
                    showOrder();
                }
        });

        buttonEdit.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e)
		{
                    runThread(7);
                }
        });

        buttonShowEdit.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e)
		{
                    runThread(7);
                }
        });

        buttonShowOK.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e)
		{
                    runThread(1);
                }
        });

        buttonDelete.addActionListener(new ActionListener()  {
                public void actionPerformed(ActionEvent e)
		{
                    runThread(3);
		}
	});


        mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e)
                {
                int selectedColumn = mainTable.getSelectedRow();
                if (selectedColumn >= 0) {
                orderIDFromTable = Integer.valueOf("" + mainTable.getValueAt(selectedColumn, 0)) - 1623890000;
                isMainContainerOn = false; isAEDButtonsEnabled = true; sysMSG = 3;
                updateAll();
                isAEDButtonsEnabled = false;
                }
        }});
    }


//== PRIVATE & HELPFUL INSTANCE METHODS ========================================


/*******************************************************************************
 * Sets up Menu bar, menus and submenus.
 *
 */
    private void setMenu(){
    menuFile.add(menuItemCreate);
    menuFile.add(menuItemConnect);
    menuFile.add(menuItemImportXML);
    menuFile.add(menuItemExit);

    menuSettings.add(menuItemAllSettings);
    menuSettings.add(menuItemTruncate);

    menuAbout.add(menuItemAbout);

    menuBar.add(menuFile);
    menuBar.add(menuSettings);
    menuBar.add(menuAbout);

    this.setJMenuBar(menuBar);
    }


/*******************************************************************************
 * Sets Graphic layouts to main components.
 *
 */
    private void setLayouts(){
        GroupLayout layout = new GroupLayout(this.getContentPane());

        // Sets main window layout
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(higherContainer, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE).addComponent(lowerContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(higherContainer, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lowerContainer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE).addContainerGap()));
        higherContainer.setLayout(new GridLayout());
        newOrderContainer.setLayout(new AbsoluteLayout());
        showOrderInfoContainer.setLayout(new AbsoluteLayout());
        editOrderContainer.setLayout(new AbsoluteLayout());
        lowerContainer.setLayout(new BoxLayout(lowerContainer, BoxLayout.LINE_AXIS));
    }


/*******************************************************************************
 * Adds main panels.
 *
 */
    private void addPanels(){
        // Makes upper, classic panel
        higherContainer.setSize(150, 30);
        higherContainer.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
        // Makes middle, scrollable panel
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        // Makes lower, classic pannel
        lowerContainer.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
    }


/*******************************************************************************
 * Sets main component's properties.
 *
 */
    private void setComponents(){
        // Label settings
        labelSystemMSG.setHorizontalAlignment(SwingConstants.CENTER);
        labelSystemMSG.setFont(new Font("Tahoma", 1, 11));
        // Table settings
        mainTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        mainTable.getAccessibleContext().setAccessibleParent(scrollPane);
        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainTable.setAutoCreateRowSorter(true);
        // ComboBox
        editOrderContainer.add(stateChange = new JComboBox(), new AbsoluteConstraints(150, 283, 140, -1)); //.. State ComboBox
        stateChange.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nevyřízena", "Částečně vyřízena", "Vyřízena"}));
    }


/*******************************************************************************
 * Adds components into scrollPanes.
 *
 */
    private void addComponents(){
        // in upper Panel
        higherContainer.add(labelSystemMSG);
        // in Scroll Panel

        scrollPane.setViewportView(mainContainer);
        
        // Welcome image load
        String filename = null;
        try {
            filename = new java.io.File(".").getCanonicalPath() + "/logo.png";
        } catch (IOException ex){
            System.out.println("Could not get path to current folder. Logo will not be loaded.");
            ex.printStackTrace();
        }

        Image image = Toolkit.getDefaultToolkit().getImage(filename);
        JComponent picture = null;
        try {
            picture = new JScrollPane(new ImageComponent(image));
        } catch (InterruptedException ex) {
            System.out.println("Unable to load main logo picture.");
            ex.printStackTrace();
        }
        mainContainer.add(picture);

        // in bottom Panel
            lowerContainer.add(Box.createRigidArea(new Dimension(10, 0)));
        lowerContainer.add(buttonLoad);
            lowerContainer.add(Box.createRigidArea(new Dimension(6, 0)));
        lowerContainer.add(buttonMake);
            lowerContainer.add(Box.createRigidArea(new Dimension(6, 0)));
        lowerContainer.add(buttonShow);
            lowerContainer.add(Box.createRigidArea(new Dimension(6, 0)));
        lowerContainer.add(buttonEdit);
            lowerContainer.add(Box.createRigidArea(new Dimension(6, 0)));
        lowerContainer.add(buttonDelete);
    }


/*******************************************************************************
 * Updates application environment (messages, layout, etc.).
 *
 */
    private void updateAll(){
        // Empty main container
        if (this.isMainContainerOn){

            scrollPane.setViewportView(mainContainer);
            mainContainer.setVisible(true);
            isMainButtonsOn = true;
            buttonMake.setEnabled(true); menuItemCreate.setEnabled(true);
        } else {
            mainContainer.setVisible(false);
        }

        // Main table
        if (this.isTableOn) {

            isAEDButtonsOn = true;
            scrollPane.setViewportView(mainTable);
            mainTable.setVisible(true);
            isMainButtonsOn = true;
            isAEDButtonsOn = true;
            buttonMake.setEnabled(true); menuItemCreate.setEnabled(true);
        } else {
            mainTable.setVisible(false);
            isAEDButtonsOn = false;
        }

        // System message label
        switch(sysMSG){
            case 0: labelSystemMSG.setVisible(false);
                    break;
            case 1: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Orders loaded properly.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 2: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Order has been succesfully created.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 3: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("You've selected order with ID " + orderIDFromTable +
                                           ".");
                    labelSystemMSG.setVisible(true);
                    break;
            case 4: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Order ID " + orderIDFromTable +
                                           " has been successfully deleted.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 5: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Order ID " + orderIDFromTable +
                                           " has been successfully edited and saved.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 6: labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Details order ID " + orderIDFromTable +
                                           " has been successfully loaded.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 7:labelSystemMSG.setForeground(new Color(0, 153, 0));
                    labelSystemMSG.setText("Orders XML file was sucessfully imported");
                    labelSystemMSG.setVisible(true);
                    break;
            case 11:labelSystemMSG.setForeground(new Color(255, 0, 0));
                    labelSystemMSG.setText("Order creation has been canceled. No order will be made.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 12:labelSystemMSG.setForeground(new Color(255, 0, 0));
                    labelSystemMSG.setText("Order editation has been canceled. Changes will not be saved.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 13:labelSystemMSG.setForeground(new Color(255, 0, 0));
                    labelSystemMSG.setText("Changes has been removed.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 14:labelSystemMSG.setForeground(new Color(255, 0, 0));
                    labelSystemMSG.setText("All orders were deleted.");
                    labelSystemMSG.setVisible(true);
                    break;
            case 15:labelSystemMSG.setForeground(new Color(255, 0, 0));
                    labelSystemMSG.setText("Error occured.");
                    labelSystemMSG.setVisible(true);
                    break;
            default:break;
        }

        // Add, edit, delete buttons
        if (this.isAEDButtonsOn){
            buttonShow.setVisible(true);
            buttonMake.setVisible(true);
            buttonLoad.setVisible(true);
            buttonEdit.setVisible(true);
            buttonDelete.setVisible(true);
        } else {
            buttonShow.setVisible(false);
            buttonMake.setVisible(true);
            buttonLoad.setVisible(true);
            buttonEdit.setVisible(false);
            buttonDelete.setVisible(false);
        }

        // Main operational buttons
        if (this.isAEDButtonsEnabled){
            buttonShow.setEnabled(true);
            buttonEdit.setEnabled(true);
            buttonDelete.setEnabled(true);
        } else {
            buttonShow.setEnabled(false);
            buttonEdit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }

        if (this.isMainButtonsOn){
            buttonLoad.setVisible(true);
            buttonMake.setVisible(true);
        } else {
            buttonLoad.setVisible(false);
            buttonMake.setVisible(false);
        }
    }


/*******************************************************************************
 * Loads data model for GUI table.
 *
 * @throws SQLException
 */
    private void loadDataModel() throws SQLException{
        tableData = BusinessFacade.getInstance().loadOrdersFromDB();
        String[][] stringTableData = tableData.toArray(new String[tableData.size()][12]);
        mainTable.setModel(new DefaultTableModel(stringTableData, tableHeader) {
           
           @Override
           public boolean isCellEditable(int rowIndex, int columnIndex) {
           return false;
           }
        });

        mainTable.getColumnModel().getColumn(0).setPreferredWidth(45);
        mainTable.getColumnModel().getColumn(1).setPreferredWidth(115);
        mainTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        mainTable.getColumnModel().getColumn(3).setPreferredWidth(130);
        mainTable.getColumnModel().getColumn(4).setPreferredWidth(35);
        mainTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        mainTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        mainTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        mainTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        mainTable.getColumnModel().getColumn(9).setPreferredWidth(60);
        mainTable.getColumnModel().getColumn(10).setPreferredWidth(20);
    }


/*******************************************************************************
 * Intializes form for editing order.
 *
 */
    private void initEditForm(){
        // Edit order form set-up. AbsoluteConstrains parameters - FROM LEFT, FROM TOP, WIDTH, HEIGHT.
        scrollPane.setViewportView(editOrderContainer);

        editOrderContainer.add(labelValidityErrorEdit, new AbsoluteConstraints(25, 358, 750, -1));
        labelValidityErrorEdit.setText("");
        labelValidityError.setText("");
        labelValidityErrorEdit.setVerticalAlignment(SwingConstants.TOP);

        editOrderContainer.add(new LabelBold("Jméno:"), new AbsoluteConstraints(20, 11, 100, 15));
        editOrderContainer.add(new LabelBold("Příjmení:"), new AbsoluteConstraints(235, 11, 100, 15));
        editOrderContainer.add(new LabelBold("Přezdívka:"), new AbsoluteConstraints(450, 11, 100, 15));

        editOrderContainer.add(new LabelBold("Ulice:"), new AbsoluteConstraints(20, 53, 100, 15));
        editOrderContainer.add(new LabelBold("Číslo domu:"), new AbsoluteConstraints(235, 53, 100, 15));
        editOrderContainer.add(new LabelBold("Město:"), new AbsoluteConstraints(20, 74, 100, 15));
        editOrderContainer.add(new LabelBold("PSČ:"), new AbsoluteConstraints(235, 74, 100, 15));

        editOrderContainer.add(new LabelBold("Produkt:"), new AbsoluteConstraints(20, 116, 100, 15));
        editOrderContainer.add(new LabelBold("Typ:"), new AbsoluteConstraints(235, 116, 100, 15));
        editOrderContainer.add(new LabelBold("Cena:"), new AbsoluteConstraints(558, 116, 100, 15));

        editOrderContainer.add(new LabelBold("Obchodník:"), new AbsoluteConstraints(20, 137, 100, 15));
        editOrderContainer.add(new LabelBold("Odkoupeno:"), new AbsoluteConstraints(235, 137, 100, 15));
        editOrderContainer.add(new LabelBold("Nákupka:"), new AbsoluteConstraints(420, 137, 100, 15));

        editOrderContainer.add(new LabelBold("Doprava:"), new AbsoluteConstraints(20, 179, 100, 15));
        editOrderContainer.add(new LabelBold("Poštovné:"), new AbsoluteConstraints(235, 179, 100, 15));
        editOrderContainer.add(new LabelBold("Náklady:"), new AbsoluteConstraints(20, 200, 100, 15));

        editOrderContainer.add(new LabelBold("Poznámka:"), new AbsoluteConstraints(20, 221, 100, 15));

        editOrderContainer.add(new LabelBold("Objednáno:"), new AbsoluteConstraints(20, 242, 100, 15));
        editOrderContainer.add(new LabelBold("Zaplaceno:"), new AbsoluteConstraints(200, 242, 100, 15));
        editOrderContainer.add(new LabelBold("Doručeno:"), new AbsoluteConstraints(380, 242, 100, 15));
        editOrderContainer.add(new LabelBold("Odesláno:"), new AbsoluteConstraints(560, 242, 100, 15));

        editOrderContainer.add(new LabelBold("Stav objednávky:"), new AbsoluteConstraints(20, 284, 100, 15));

        editOrderContainer.add(firstName, new AbsoluteConstraints(100, 10, 100, 17)); // Field for Name
        editOrderContainer.add(sureName, new AbsoluteConstraints(315, 10, 100, 17)); // .. for SureName
        editOrderContainer.add(nick, new AbsoluteConstraints(530, 10, 100, 17)); // .. for Nick

        editOrderContainer.add(street, new AbsoluteConstraints(100, 52, 100, 17)); //.. for Street
        editOrderContainer.add(streetCode, new AbsoluteConstraints(315, 52, 35, 17)); //.. for Street code
        editOrderContainer.add(city, new AbsoluteConstraints(100, 73, 100, 17)); //.. for City
        editOrderContainer.add(postCode, new AbsoluteConstraints(315, 73, 55, 17)); //.. for PostCode

        editOrderContainer.add(product, new AbsoluteConstraints(100, 115, 120, 17)); //.. for Product
        editOrderContainer.add(productType, new AbsoluteConstraints(315, 115, 200, 17)); //.. for Product type
        editOrderContainer.add(price, new AbsoluteConstraints(600, 115, 50, 17)); //.. Product's price

        editOrderContainer.add(seller, new AbsoluteConstraints(100, 136, 120, 17)); //.. for Provider
        editOrderContainer.add(bought, new AbsoluteConstraints(315, 136, 60, 17)); //.. for Bought day
        editOrderContainer.add(purchaseCost, new AbsoluteConstraints(480, 136, 50, 17)); //.. purchase

        editOrderContainer.add(shipping, new AbsoluteConstraints(100, 178, 100, 17)); //.. Shipping
        editOrderContainer.add(postCost, new AbsoluteConstraints(315, 178, 50, 17)); //.. post price
        editOrderContainer.add(costs, new AbsoluteConstraints(100, 199, 50, 17)); //.. costs

        editOrderContainer.add(notation, new AbsoluteConstraints(100, 220, 300, 17)); //.. notation

        editOrderContainer.add(ordered, new AbsoluteConstraints(100, 241, 65, 17)); //.. ordered date
        editOrderContainer.add(payed, new AbsoluteConstraints(280, 241, 65, 17)); //.. payed date
        editOrderContainer.add(delivered, new AbsoluteConstraints(460, 241, 65, 17)); //.. delivered date
        editOrderContainer.add(sent, new AbsoluteConstraints(630, 241, 65, 17)); //.. sent date

        editOrderContainer.add(stateChange, new AbsoluteConstraints(150, 283, 140, -1)); //.. State ComboBox

        editOrderButtonsContainer = new JPanel();
        editOrderButtonsContainer.setLayout(new BoxLayout(editOrderButtonsContainer, BoxLayout.LINE_AXIS));

        editOrderContainer.add(editOrderButtonsContainer, new AbsoluteConstraints(25, 325, -1, -1));

            editOrderButtonsContainer.add(buttonEditOrderSubmit); //.. OK Button
                editOrderButtonsContainer.add(Box.createRigidArea(new Dimension(6, 0)));
            editOrderButtonsContainer.add(buttonEditOrderReset); //.. Reset Button
                editOrderButtonsContainer.add(Box.createRigidArea(new Dimension(6, 0)));
            editOrderButtonsContainer.add(buttonEditOrderCancel); //.. Cancel Button
    }


/*******************************************************************************
 * Intializes form for creating new order.
 *
 */
    private void initOrderForm(){

        // New order set-up. AbsoluteConstrains parameters - FROM LEFT, FROM TOP, WIDTH, HEIGHT.
        scrollPane.setViewportView(newOrderContainer);

        newOrderContainer.add(labelValidityError, new AbsoluteConstraints(25, 298, 750, -1));
        labelValidityError.setText("");
        labelValidityError.setVerticalAlignment(SwingConstants.TOP);
                
        newOrderContainer.add(new LabelBold("Jméno:"), new AbsoluteConstraints(20, 11, 100, 15));
        newOrderContainer.add(new LabelBold("Příjmení:"), new AbsoluteConstraints(235, 11, 100, 15));
        newOrderContainer.add(new LabelBold("Přezdívka:"), new AbsoluteConstraints(450, 11, 100, 15));

        newOrderContainer.add(new LabelBold("Ulice:"), new AbsoluteConstraints(20, 53, 100, 15));
        newOrderContainer.add(new LabelBold("Číslo domu:"), new AbsoluteConstraints(235, 53, 100, 15));
        newOrderContainer.add(new LabelBold("Město:"), new AbsoluteConstraints(20, 74, 100, 15));
        newOrderContainer.add(new LabelBold("PSČ:"), new AbsoluteConstraints(235, 74, 100, 15));

        newOrderContainer.add(new LabelBold("Produkt:"), new AbsoluteConstraints(20, 116, 100, 15));
        newOrderContainer.add(new LabelBold("Typ:"), new AbsoluteConstraints(235, 116, 100, 15));
        newOrderContainer.add(new LabelBold("Cena:"), new AbsoluteConstraints(558, 116, 100, 15));

        newOrderContainer.add(new LabelBold("Obchodník:"), new AbsoluteConstraints(20, 137, 100, 15));
        newOrderContainer.add(new LabelBold("Odkoupeno:"), new AbsoluteConstraints(235, 137, 100, 15));
        newOrderContainer.add(new LabelBold("Nákupka:"), new AbsoluteConstraints(420, 137, 100, 15));

        newOrderContainer.add(new LabelBold("Doprava:"), new AbsoluteConstraints(20, 179, 100, 15));
        newOrderContainer.add(new LabelBold("Poštovné:"), new AbsoluteConstraints(235, 179, 100, 15));
        newOrderContainer.add(new LabelBold("Náklady:"), new AbsoluteConstraints(20, 200, 100, 15));

        newOrderContainer.add(new LabelBold("Poznámka:"), new AbsoluteConstraints(20, 221, 100, 15));

        newOrderContainer.add(firstName, new AbsoluteConstraints(100, 10, 100, 17)); // Field for Name
        newOrderContainer.add(sureName, new AbsoluteConstraints(315, 10, 100, 17)); // .. for SureName
        newOrderContainer.add(nick, new AbsoluteConstraints(530, 10, 100, 17)); // .. for Nick

        newOrderContainer.add(street, new AbsoluteConstraints(100, 52, 100, 17)); //.. for Street
        newOrderContainer.add(streetCode, new AbsoluteConstraints(315, 52, 35, 17)); //.. for Street code
        newOrderContainer.add(city, new AbsoluteConstraints(100, 73, 100, 17)); //.. for City
        newOrderContainer.add(postCode, new AbsoluteConstraints(315, 73, 55, 17)); //.. for PostCode

        newOrderContainer.add(product, new AbsoluteConstraints(100, 115, 120, 17)); //.. for Product
        newOrderContainer.add(productType, new AbsoluteConstraints(315, 115, 200, 17)); //.. for Product type
        newOrderContainer.add(price, new AbsoluteConstraints(600, 115, 50, 17)); //.. Product's price

        newOrderContainer.add(seller, new AbsoluteConstraints(100, 136, 120, 17)); //.. for Provider
        newOrderContainer.add(bought, new AbsoluteConstraints(315, 136, 60, 17)); //.. for Bought day
        newOrderContainer.add(purchaseCost, new AbsoluteConstraints(480, 136, 50, 17)); //.. purchase

        newOrderContainer.add(shipping, new AbsoluteConstraints(100, 178, 100, 17)); //.. Shipping
        newOrderContainer.add(postCost, new AbsoluteConstraints(315, 178, 50, 17)); //.. post price
        newOrderContainer.add(costs, new AbsoluteConstraints(100, 199, 50, 17)); //.. costs

        newOrderContainer.add(notation, new AbsoluteConstraints(100, 220, 300, 17)); //.. notation

        newOrderButtonsContainer = new JPanel();
        newOrderButtonsContainer.setLayout(new BoxLayout(newOrderButtonsContainer, BoxLayout.LINE_AXIS));
        
        newOrderContainer.add(newOrderButtonsContainer, new AbsoluteConstraints(25, 265, -1, -1));

            newOrderButtonsContainer.add(buttonNewOrderOK); //.. OK Button
                newOrderButtonsContainer.add(Box.createRigidArea(new Dimension(6, 0)));
            newOrderButtonsContainer.add(buttonNewOrderCancel); //.. Cancel Button

    }


/*******************************************************************************
 * Makes new order and inserts it into database.
 *
 * @throws SQLException
 */
    private void makeNewOrder() throws SQLException{
        BusinessFacade.getInstance().makeNewOrder(firstName.getText(), sureName.getText(),
                nick.getText(), street.getText() + " " + streetCode.getText(), city.getText(),
                Integer.valueOf(postCode.getText()), product.getText(), productType.getText(),
                shipping.getText(), new Date(), new Date(), new Date(true), new Date(true), formatDate(reformatDate(bought.getText())), 1,
                seller.getText(), Integer.valueOf(purchaseCost.getText()), Integer.valueOf(postCost.getText()),
                Integer.valueOf(price.getText()), Integer.valueOf(costs.getText()), notation.getText());
    }


/*******************************************************************************
 * Initializes form for creating new order and informs user about this action.
 *
 * @throws SQLException
 */
    private void doNewOrder(){
            clearTextFields();
            initOrderForm();
            isTableOn = false; isMainContainerOn = false;
            sysMSG = 0;
            buttonLoad.setEnabled(true);
            buttonMake.setEnabled(false); menuItemCreate.setEnabled(false);
            updateAll();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Create new order:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
    }


/*******************************************************************************
 * Asks user if he wants to close application and (doesn't) closes it.
 *
 * @throws SQLException
 */
    private void exitApplication(){
    int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to QUIT ? Unsaved data will be lost!",
            "Quit PowerSeller", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    if (dialogResult == 0){
        System.exit(0);
    }
    }


/*******************************************************************************
 * Validates all basic textfields and checks if given data are valid.
 *
 * @return True - valid data in form's textboxes.
 *         False - Invalid data in form's textboxes.
 */
    private boolean checkValidityBase(){

        isEmpty = false; isLenghtOK = true; isDate = true; isInteger = true;
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");
        // FirstName validation
        if (firstName.getText().length() == 0){
            changeTextField(firstName, false);
            isEmpty = true;
        } else if (firstName.getText().length() > 10){
            changeTextField(firstName, false);
            isLenghtOK = false;
            sb.append(" Křestní <b>jméno</b> může mít maximálně <b>10 znaků</b><br>");
        } else{
            changeTextField(firstName, true);
        }
        // SureName Validation
        if (sureName.getText().length() == 0){
            changeTextField(sureName, false);
            isEmpty = true;
        } else if(sureName.getText().length() > 20){
            changeTextField(sureName, false);
            isLenghtOK = false;
            sb.append(" <b>Příjmení</b> může mít maximálně <b>20 znaků</b><br>");
        } else{
            changeTextField(sureName, true);
        }
        // Nickname validation
        if(nick.getText().length() > 20){
            changeTextField(nick, false);
            isLenghtOK = false;
            sb.append(" <b>Nick</b> může mít maximálně <b>20 znaků</b><br>");
        } else{
            changeTextField(nick, true);
        }
        // Street validation
        if (street.getText().length() == 0){
            changeTextField(street, false);
            isEmpty = true;
        }else if(street.getText().length() > 35){
            changeTextField(street, false);
            isLenghtOK = false;
            sb.append(" <b>Ulice</b> může mít maximálně <b>25 znaků</b><br>");
        } else{
            changeTextField(street, true);
        }
        // Street number validation
        if (streetCode.getText().length() == 0){
            changeTextField(streetCode, false);
            isEmpty = true;
        }else if(streetCode.getText().length() > 10){
            changeTextField(streetCode, false);
            isLenghtOK = false;
            sb.append(" <b>Číslo popisné</b> může mít maximálně <b>10 znaků</b><br>");
        } else{
            changeTextField(streetCode, true);
        }
        // City validation
        if (city.getText().length() == 0){
            changeTextField(city, false);
            isEmpty = true;
        } else if(city.getText().length() > 30){
            changeTextField(city, false);
            isLenghtOK = false;
            sb.append(" <b>Město</b> může mít maximálně <b>30 znaků</b><br>");
        } else{
            changeTextField(city, true);
        }
        // Post code validation
        if(postCode.getText().length() != 5){
            changeTextField(postCode, false);
            isLenghtOK = false;
            sb.append(" <b>Směrovací číslo</b> musí mít <b>5 čísel</b><br>");
        } else if (!isIntegerValidation(postCode.getText())){
            changeTextField(postCode, false);
            isInteger = false;
        } else{
            changeTextField(postCode, true);
        }
        // Product name validation
        if (product.getText().length() == 0){
            changeTextField(product, false);
            isEmpty = true;
        }else if(product.getText().length() > 100){
            changeTextField(product, false);
            isLenghtOK = false;
            sb.append(" <b>Produkt</b> může mít maximálně <b>100 znaků</b><br>");
        } else{
            changeTextField(product, true);
        }
        // Product-type specification
        if (productType.getText().length() == 0){
            changeTextField(productType, false);
            isEmpty = true;
        }else if(productType.getText().length() > 100){
            changeTextField(productType, false);
            isLenghtOK = false;
            sb.append(" <b>Typ produktu</b> může mít maximálně <b>100 znaků</b><br>");
        } else{
            changeTextField(productType, true);
        }
        // Product price validation
        if (price.getText().length() == 0){
            changeTextField(price, false);
            isEmpty = true;
        }else if(price.getText().length() > 6){
            changeTextField(price, false);
            isLenghtOK = false;
            sb.append(" <b>Cena produktu</b> může mít maximálně <b>6 čísel</b><br>");
        } else if(!isIntegerValidation(price.getText())){
            changeTextField(price, false);
            isInteger = false;
        } else{
            changeTextField(price, true);
        }
        // Seller's name validation
        if (seller.getText().length() == 0){
            changeTextField(seller, false);
            isEmpty = true;
        }else if(seller.getText().length() > 30){
            changeTextField(seller, false);
            isLenghtOK = false;
            sb.append(" Jméno <b>Obchodníka</b> může mít maximálně <b>30 znaků</b><br>");
        } else{
            changeTextField(seller, true);
        }
        // Date of purchase validation
        if(!isDateValidation(bought.getText()) && (bought.getText().length() != 0)){
            changeTextField(bought, false);
            isDate = false;
            sb.append(" Do <b>Odkoupeno</b> zadávejte pouze <b>datum</b> ve formátu : " +
                    "<b>dd.mm.yyyy</b> nebo <b>d.m.yyyy</b> <br>");
        } else{
            changeTextField(bought, true);
        }
        // Purchase cost validation
        if(purchaseCost.getText().length() > 6){
            changeTextField(purchaseCost, false);
            isLenghtOK = false;
            sb.append(" <b>Nákupní cena</b> může mít maximálně <b>6 čísel</b><br>");
        } else if (!isIntegerValidation(purchaseCost.getText()) && (purchaseCost.getText().length() != 0)){
            changeTextField(purchaseCost, false);
            isInteger = false;
        } else{
            changeTextField(purchaseCost, true);
        }
        // Shipping type validation
        if (shipping.getText().length() == 0){
            changeTextField(shipping, false);
            isEmpty = true;
        }else if(shipping.getText().length() > 30){
            changeTextField(shipping, false);
            isLenghtOK = false;
            sb.append("Specifikace <b>dopravy</b> může mít maximálně <b>30 znaků</b><br>");
        } else{
            changeTextField(shipping, true);
        }
        // Cost of shipping validation
        if (postCost.getText().length() == 0){
            changeTextField(postCost, false);
            isEmpty = true;
        }else if(postCost.getText().length() > 4){
            changeTextField(postCost, false);
            isLenghtOK = false;
            sb.append(" <b>Cena poštovného</b> může mít maximálně <b>4 číslice</b><br>");
        }else if (!isIntegerValidation(postCost.getText())){
            changeTextField(postCost, false);
            isInteger = false;
        }else{
            changeTextField(postCost, true);
        }
        // Costs validation
        if(costs.getText().length() > 6){
            changeTextField(costs, false);
            isLenghtOK = false;
            sb.append(" Celkové  <b>náklady</b> mohou mít maximálně <b>6 číslic</b><br>");
        } else if(!isIntegerValidation(costs.getText()) && (costs.getText().length() != 0)){
            changeTextField(costs, false);
            isInteger = false;
        }else{
            changeTextField(costs, true);
        }
        // Notation validation
        if(notation.getText().length() > 999){
            changeTextField(notation, false);
            isLenghtOK = false;
            sb.append(" <b>Poznámka</b> k objednávce může mít maximálně <b>1000 znaků</b><br>");
        } else{
            changeTextField(notation, true);
        }

        sb.append("</html>");

        StringBuffer validationMessageBuffer = new StringBuffer();
        // Error messages compilation
        if(isEmpty){validationMessageBuffer.append(" Některé z povinných polí nebylo vyplněno.");}
        if(!isLenghtOK){validationMessageBuffer.append(" Neplatná délka řetězce / čísla.");}
        if(!isDate){validationMessageBuffer.append(" Neplatný formát data.");}
        if(!isInteger){validationMessageBuffer.append(" Některá z polí vyžadují zadání číselné hodnoty.");}

        validationErrorMessage = validationMessageBuffer.toString();

        if (!isEmpty && isLenghtOK && isDate && isInteger){
            sysMSG = 0;
            return true;
        }else {
            labelValidityError.setText(sb.toString());
            return false;
        }
    }


/*******************************************************************************
 * Imports .xml orders file choosen by user into database.
 *
 */
    private void importFromXML(){
        String xmlName;
        
        try {
            xmlName = new java.io.File(".").getCanonicalPath();
            JFileChooser fc = new JFileChooser(new File(xmlName));

            // Show open dialog; this method does not return until the dialog is closed
            int returnVal = fc.showOpenDialog(this);

            File xmlFile = fc.getSelectedFile();

            // Imports into DB
            if (xmlFile != null && returnVal == JFileChooser.APPROVE_OPTION) {
                facade.importFromXMLtoDB(xmlFile);
                sysMSG = 7;
            } else {
                sysMSG = 0;
            }
            
            isTableOn = false;
            isMainContainerOn = true;
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));

            updateAll();
            
    }
        catch (ParserConfigurationException ex) {
             exceptionState("XML Parser plugin Error. Could not import XML.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        } catch (SAXException ex) {
             exceptionState("XML SAX Parser plugin Error. Could not import XML.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        } catch (IOException ex) {
             exceptionState("File error. Could not import XML from given file.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        } catch (SQLException ex) {
             exceptionState("Database error. Could not import orders to database.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Validates all textfields in form and checks if given data are valid.
 *
 * @return True - valid data in form's textboxes.
 *         False - Invalid data in form's textboxes.
 */
    private boolean checkValidityAll(){
        boolean firstValidation = checkValidityBase();

        isDate = true;
        StringBuffer sb = new StringBuffer();

        sb.append("<html>");

        if(!isDateValidation(ordered.getText()) && (ordered.getText().length() != 0)){
            changeTextField(ordered, false);
            isDate = false;
            sb.append(" Do <b>Objednáno</b> zadávejte pouze <b>datum</b> ve formátu : " +
                    "<b>dd.mm.yyyy</b> nebo <b>d.m.yyyy</b> <br>");
        } else{
            changeTextField(ordered, true);
        }

        if(!isDateValidation(payed.getText()) && (payed.getText().length() != 0)){
            changeTextField(payed, false);
            isDate = false;
            sb.append(" Do <b>Zaplaceno</b> zadávejte pouze <b>datum</b> ve formátu : " +
                    "<b>dd.mm.yyyy</b> nebo <b>d.m.yyyy</b> <br>");
        } else{
            changeTextField(payed, true);
        }

        if(!isDateValidation(delivered.getText()) && (delivered.getText().length() != 0)){
            changeTextField(delivered, false);
            isDate = false;
            sb.append(" Do <b>Doručeno</b> zadávejte pouze <b>datum</b> ve formátu : " +
                    "<b>dd.mm.yyyy</b> nebo <b>d.m.yyyy</b> <br>");
        } else{
            changeTextField(delivered, true);
        }

        if(!isDateValidation(sent.getText()) && (sent.getText().length() != 0)){
            changeTextField(sent, false);
            isDate = false;
            sb.append(" Do <b>Odesláno</b> zadávejte pouze <b>datum</b> ve formátu : " +
                    "<b>dd.mm.yyyy</b> nebo <b>d.m.yyyy</b> <br>");
        } else{
            changeTextField(sent, true);
        }

        sb.append("</html>");

        if (firstValidation && isDate){
            return true;
        } else if (firstValidation  && !isDate){
            validationErrorMessage += "Neplatný formát data.";
            labelValidityErrorEdit.setText(labelValidityError.getText() + sb.toString());

            return false;
        } else {
            labelValidityErrorEdit.setText(labelValidityError.getText() + sb.toString());
            return false;
        }
    }


/*******************************************************************************
 * Clears all Textboxes in form.
 *
 */
    private void clearTextFields(){
        firstName.setText(""); firstName.setBorder(defaultJTextBorder);
        sureName.setText(""); sureName.setBorder(defaultJTextBorder);
        nick.setText(""); nick.setBorder(defaultJTextBorder);
        street.setText(""); street.setBorder(defaultJTextBorder);
        streetCode.setText(""); streetCode.setBorder(defaultJTextBorder);
        city.setText(""); city.setBorder(defaultJTextBorder);
        postCode.setText(""); postCode.setBorder(defaultJTextBorder);
        product.setText(""); product.setBorder(defaultJTextBorder);
        productType.setText(""); productType.setBorder(defaultJTextBorder);
        price.setText(""); price.setBorder(defaultJTextBorder);
        seller.setText(""); seller.setBorder(defaultJTextBorder);
        bought.setText(""); bought.setBorder(defaultJTextBorder);
        purchaseCost.setText(""); purchaseCost.setBorder(defaultJTextBorder);
        shipping.setText(""); shipping.setBorder(defaultJTextBorder);
        postCost.setText(""); postCost.setBorder(defaultJTextBorder);
        costs.setText(""); costs.setBorder(defaultJTextBorder);
        notation.setText(""); notation.setBorder(defaultJTextBorder);
        ordered.setText(""); ordered.setBorder(defaultJTextBorder);
        payed.setText(""); payed.setBorder(defaultJTextBorder);
        delivered.setText(""); delivered.setBorder(defaultJTextBorder);
        sent.setText(""); sent.setBorder(defaultJTextBorder);
    }


/*******************************************************************************
 * Highlights textfield if it contains invalid data.
 *
 * @param textField Textfield to highlight.
 * @param isValid If is valid check.
 */
    private void changeTextField(JTextField textField, boolean isValid){
        if (isValid){
            textField.setBorder(defaultJTextBorder);
        } else{
            textField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
        }
    }


/*******************************************************************************
 * Checks if string is valid Integer or not.
 *
 * @param stringToValidate String to check.
 * @return True - Is valid integer.
 *         False - Is not valid integer.
 */
    private boolean isIntegerValidation(String stringToValidate){
        try{
            Integer.valueOf(stringToValidate);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }


/*******************************************************************************
 * Checks if string is valid Date or not.
 *
 * @param stringToValidate String to check.
 * @return True - Is valid date.
 *         False - Is not valid date.
 */
    private boolean isDateValidation(String stringToValidate){
        String[] items = reformatDate(stringToValidate).split("-");
        if (items.length == 3 && isIntegerValidation(items[0]) && (items[0].length() == 2 || items[0].length() == 1)
                && isIntegerValidation(items[1]) && (items[1].length() == 2 || items[1].length() == 1)
                && isIntegerValidation(items[2]) && items[2].length() == 4){
            if (Integer.valueOf(items[0]) > 0 && Integer.valueOf(items[0]) <= 31
                    && Integer.valueOf(items[1]) > 0 && Integer.valueOf(items[1]) <= 12){
                return true;
            } else return false;
        } else {
            return false;
        }
    }


/*******************************************************************************
 * Reformatts date to classic czech date format.
 *
 * @param dateToReformat Unformatted date string.
 * @return Formatted date.
 */
    private String reformatDate(String dateToReformat){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<dateToReformat.length() ; i++){
            if (dateToReformat.charAt(i) == '.'){
                sb.append('-');
            }else{
                sb.append(dateToReformat.charAt(i));
            }
        }
        return sb.toString();
    }


/*******************************************************************************
 * Returns Date instance from unformatted date.
 * @param unformattedDate Unformatted date string.
 * @return Date instance.
 */
    private Date formatDate(String unformattedDate){
        if (unformattedDate.length() != 0 ){
            String[] items = unformattedDate.split("-");
            Date formattedDate = new Date(Integer.valueOf(items[0]),Integer.valueOf(items[1]),Integer.valueOf(items[2]));
            // System.out.println("here was 2 : " + unformattedDate);
            return formattedDate;
        } else {
            // System.out.println("here was : " + unformattedDate);
            return new Date(true);
        }
    }


/*******************************************************************************
 * Fills textfields with order's data loaded from DB.
 *
 * @param orderID ID of order which should be loaded.
 * @throws SQLException
 */
    private void fillTextFieldsFromDB(int orderID) throws SQLException{
        Order order = facade.getOrderFromDB(orderID);
        // Divides address and address number
        StringBuffer sbn = new StringBuffer();
        String[] nameItems = order.getFullName().split(" ");
        for (int i = 0; i<nameItems.length ; i++){
            if (i == nameItems.length - 1) {
                sureName.setText(nameItems[i]);
            }else if(i == 0){
                sbn.append(nameItems[i]);
            } else {
                sbn.append(" " + nameItems[i]);
            }
        }
        firstName.setText(sbn.toString());

        nick.setText(order.getNick());
        
        // Divides address and address number
        StringBuffer sbs = new StringBuffer();
        String[] streetItems = order.getStreet().split(" ");
        for (int i = 0; i<streetItems.length ; i++){
            if (i == streetItems.length - 1) {
                streetCode.setText(streetItems[i]);
            } else if(i == 0){
                sbs.append(streetItems[i]);
            } else {
                sbs.append(" " + streetItems[i]);
            }
        }
        street.setText(sbs.toString());
        
        city.setText(order.getCity());
        postCode.setText("" + order.getPostCode());
        product.setText(order.getProductName());
        productType.setText(order.getTypeSpecification());
        price.setText("" + order.getProductPrice());
        seller.setText(order.getSeller());
        bought.setText(getDateForField(order.getBoughtDate()));
        purchaseCost.setText("" + order.getPurchaseCost());
        shipping.setText(order.getShipping());
        postCost.setText("" + order.getPostCost());
        costs.setText("" + order.getCosts());
        notation.setText(order.getNotation());
        ordered.setText(getDateForField(order.getOrderedDate()));
        payed.setText(getDateForField(order.getPayedDate()));
        delivered.setText(getDateForField(order.getDeliveredDate()));
        sent.setText(getDateForField(order.getSentDate()));

        stateChange.setSelectedIndex(order.getIntState()-1);
    }


/*******************************************************************************
 * Loads all orders and fills them into data table.
 *
 */
    private void doLoadAllOrders(){
        try {
            isTableOn = true; isMainContainerOn = false; sysMSG = 1;
            loadDataModel();
            updateAll();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Orders overview:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        } catch (SQLException ex) {
            exceptionState("Database Error. Could not load orders from database.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Changes order's properties and updates it in database.
 *
 * @throws SQLException
 */
    private void editOrder() throws SQLException{
        Order order = facade.getOrderFromDB(this.orderIDFromTable);

        order.setFullName(firstName.getText(), sureName.getText());
        order.setNick(nick.getText());
        order.setStreet(street.getText() + " " + streetCode.getText());
        order.setCity(city.getText());
        order.setPostCode(Integer.valueOf(postCode.getText()));
        order.setProduct(product.getText());
        order.setProductType(productType.getText());
        order.setPrice(Integer.valueOf(price.getText()));
        order.setSeller(seller.getText());
        order.setBoughtDate(formatDate(reformatDate(bought.getText())));
        order.setPurchaseCost(Integer.valueOf(purchaseCost.getText()));
        order.setCosts(Integer.valueOf(costs.getText()));
        order.setShipping(shipping.getText());
        order.setPostCost(Integer.valueOf(postCost.getText()));
        order.setNotation(notation.getText());
        order.setOrderedDate(formatDate(reformatDate(ordered.getText())));
        order.setPayedDate(formatDate(reformatDate(payed.getText())));
        order.setDeliveredDate(formatDate(reformatDate(delivered.getText())));
        order.setSentDate(formatDate(reformatDate(sent.getText())));
        order.setState(stateChange.getSelectedIndex() + 1);

        facade.updateOrder(order);

    }


/*******************************************************************************
 * Gets date in format for GUI fields.
 *
 * @param dateInString Date in string format.
 * @return Date in format for GUI fields.
 */
    private String getDateForField(String dateInString){
        if (dateInString.length() > 10 || dateInString.length() < 8){
            return "";
        } else {
            return dateInString;
        }
    }


/*******************************************************************************
 * Shows whole info about selected order.
 *
 * @throws SQLException
 */
    private void showOrderInfo() throws SQLException{
    scrollPane.setViewportView(showOrderInfoContainer);

    Order order = facade.getOrderFromDB(this.orderIDFromTable);
    
    // New order set-up. AbsoluteConstrains parameters - FROM LEFT, FROM TOP, WIDTH, HEIGHT.
    labelShowOrderForm.setVerticalAlignment(SwingConstants.TOP);
    StringBuilder sb = new StringBuilder();

    sb.append("<html>");
    // 1 Append == 1 ROW in Show order details
    sb.append("ID/VS Objednávky: <b>" + (order.getID() + 1623890000) + makeGap(12) + "</b>" +
              "Objednánka vytvořena dne: <b>" + order.getOrderedDate() + makeGap(12) + "</b>" +
              "Status: <b>" + order.getState() + "</b><br /><hr /><br />");
    sb.append("Jméno zákazníka: <b>" + order.getFullName() + makeGap(7) + "</b>" +
              "Nick: " + order.getNick() + "<br />");
    sb.append("Adresa: " + order.getStreet() + ", " + order.getPostCode() +
              " " + order.getCity() + "<br /><br />");
    sb.append("Produkt, typ: <b>" + order.getProductName() + "</b> - <b>" + order.getTypeSpecification() +
              makeGap(12) + "</b>Cena: <b>" + order.getProductPrice() + "</b>,-<br />");
    sb.append("Doprava: " + order.getShipping() + "</b>" + makeGap(9) + "Náklady na dopravu: <b>" +
              order.getPostCost() + "</b>,-<br />" );
    sb.append("Celkem k platbě: <font color='#347db7'><b>" + (order.getPostCost()
              + order.getProductPrice()) + "</b></font>,-" + makeGap(9) + "Částka zaplacena: " +
              order.getPayedDate() + "</b><br /><br />");
    sb.append("Odkoupeno: " + order.getBoughtDate() + makeGap(9) + "Od obchodníka: " +
              order.getSeller() + makeGap(9) + "Nákupní cena: <b><font color='red'>" + order.getPurchaseCost() +
              "</font></b>,- <br />");
    sb.append("Zboží přijato: " + order.getDeliveredDate() + makeGap(9) + "Zboží odesláno: " +
              order.getSentDate() + makeGap(9) + "Dodatečné náklady:<b><font color='red'> " +
              order.getCosts() + "</font></b>,-<br /><br />");
    
    int profit = order.getProductPrice() + order.getPostCost() - order.getPurchaseCost() - order.getCosts();
    if (profit>0){
        sb.append("Čistý zisk z prodeje: <b><font color='green'>" + profit + "</font></b>,-<br /><br />");
    } else {
        sb.append("Čistý zisk z prodeje: <b><font color='red'>" + profit + "</font></b>,-<br /><br />");
    }
    
    sb.append("Poznámka: <font color='#777777'>" + order.getNotation() + "</font><br /><br />");
    
    sb.append("</html>");
    
    labelShowOrderForm.setText(sb.toString());
    showOrderInfoContainer.add(labelShowOrderForm, new AbsoluteConstraints(20, 11, 600, -1));

    showOrderButtonsContainer = new JPanel();
    showOrderButtonsContainer.setLayout(new BoxLayout(showOrderButtonsContainer, BoxLayout.LINE_AXIS));

    showOrderInfoContainer.add(showOrderButtonsContainer, new AbsoluteConstraints(25, 285, -1, -1));

    showOrderButtonsContainer.add(buttonShowOK); //.. OK Button
        showOrderButtonsContainer.add(Box.createRigidArea(new Dimension(6, 0)));
    showOrderButtonsContainer.add(buttonShowEdit); //.. Edit Button

    }


/*******************************************************************************
 * Makes number of whitespaces for HTML.
 *
 * @param gapWidth Number of whitespaces.
 * @return Generated whitespaces string.
 */
    private String makeGap(int gapWidth){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < gapWidth ; i++){
            sb.append("&nbsp;");
        }
        return sb.toString();
    }


/*******************************************************************************
 * Initializes edit form and informs user about this action.
 *
 */
    private void doEditOrder(){
        try {
            isTableOn = false; sysMSG = 0; isMainContainerOn = false;
            fillTextFieldsFromDB(orderIDFromTable);
            initEditForm();
            updateAll();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Order editing: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        }catch (SQLException ex) {
             exceptionState("Database Error. Could not load order to edit it.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Runs new thread and displays message based on given number.
 *
 * @param runIndexNumber Number of Thread branch.
 */
    private void runThread(int runIndexNumber){
        this.runIndex = runIndexNumber;
        Thread t = new Thread(this);
        t.start();
    }


/*******************************************************************************
 * Displays loading information message.
 * 
 * @param text Information message text.
 */
    private void runLoadSysMSG(String text){
        labelSystemMSG.setForeground(new Color(0, 102, 153));
        labelSystemMSG.setText(text);
        labelSystemMSG.setVisible(true);
    }


/*******************************************************************************
 * Deletes selected order and informs user about this action.
 *
 */
    private void doDeleteOrder(){
        try {
            int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to DELETE order ID " + orderIDFromTable + "?", "Delete order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (dialogResult == 0) {
                isTableOn = true;
                isMainContainerOn = false;
                sysMSG = 4;
                facade.deleteOrder(facade.getOrderFromDB(orderIDFromTable));
                loadDataModel();
                updateAll();
                scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Orders overview: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
            }
        } catch (Exception ex) {
             exceptionState("Database Error. Could not delete order.\n" +
                    ex.getMessage());
             ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Creates (and saves into database) new order and informs user about this action.
 *
 */
    private void doCreateOrder() {
        try {
            if (checkValidityBase()) {
                makeNewOrder();
                isTableOn = false;
                isMainContainerOn = true;
                sysMSG = 2;
                updateAll();
                scrollPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
                clearTextFields();
            } else {
                labelSystemMSG.setForeground(new Color(255, 0, 0));
                labelSystemMSG.setText(validationErrorMessage);
                labelSystemMSG.setVisible(true);
            }
        } catch (Exception ex) {
            exceptionState("Database Error. Could not create new order.\n" +
                            ex.getMessage());
            ex.printStackTrace();
        }
    }

/*******************************************************************************
 * Saves changes, updates order in databasenew order and informs user about this action.
 *
 */
    private void doSaveOrder() {
        try {
            if (checkValidityAll()) {
                runLoadSysMSG("Saving...");
                editOrder();
                isTableOn = true;
                isMainContainerOn = false;
                sysMSG = 5;
                loadDataModel();
                updateAll();
                scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Order overview:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
                clearTextFields();
            } else {
                labelSystemMSG.setForeground(new Color(255, 0, 0));
                labelSystemMSG.setText(validationErrorMessage);
                labelSystemMSG.setVisible(true);
            }
        } catch (Exception ex) {
            exceptionState("Database Error. Could not save order.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Cancels order editation and informs user about this action.
 *
 */
    private void doOrderEditCancel() {
        try {
            int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to CANCEL ? Changes in this order will not be saved !",
                    "Cancel order editation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (dialogResult == 0) {
                runLoadSysMSG("Cancelling...");
                isTableOn = true;
                isMainContainerOn = false;
                sysMSG = 12;
                loadDataModel();
                updateAll();
                clearTextFields();
                scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Orders overview:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
            }
        } catch (SQLException ex) {
             exceptionState("Database Error. Could not load orders.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Shows settings dialog.
 *
 */
    private void showSettings(){
        Settings set = new Settings(this);
        set.setVisible(true);
    }


/*******************************************************************************
 * Shows about dialog.
 *
 */
    private void showAbout(){
        About ab = new About(this);
        ab.setVisible(true);
    }


/*******************************************************************************
 * Deletes all records in database.
 * 
 */
    private void deleteAllOrders(){
        int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to DELETE all orders from database?!",
                    "Clear database?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (dialogResult == 0) {
            runThread(8);
        }
    }


/*******************************************************************************
 * Truncates all order tables in database.
 *
 */
    private void truncateCurrentDB(){
        try {
            facade.emptyDB();
            isTableOn = false;
            isMainContainerOn = true;
            sysMSG = 14;
            updateAll();
        } catch (SQLException ex) {
            exceptionState("Database Error. Could not clear database.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Shows info about error and sets application environment to startup.
 *
 * @param msg Info message.
 */
    private void exceptionState(String msg){
        JOptionPane.showMessageDialog(this, msg);
        isTableOn = false;
        isMainContainerOn = true;
        sysMSG = 15;
        updateAll();
    }


/*******************************************************************************
 * Cancels order editation and informs user about this action.
 *
 */
    private void showOrder(){
        try {
            showOrderInfo();
            isTableOn = false; isMainContainerOn = false; sysMSG = 6;
            isMainButtonsOn = false;
            updateAll();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Order details:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        } catch (SQLException ex) {
            exceptionState("Database Error. Could not load order from database.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


/*******************************************************************************
 * Cancels order creation and informs user about this action.
 *
 */
    private void createOrderCancel(){
        int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to CANCEL ? Data in this Order will not be saved !",
                "Cancel new order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (dialogResult == 0){
            isTableOn = false; isMainContainerOn = true; sysMSG = 11;
            updateAll();
            clearTextFields();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));
        }
    }


/*******************************************************************************
 * Resets textfields in editation and informs user about this action.
 *
 */
    private void editReset(){
        try {
        int dialogResult = JOptionPane.showOptionDialog(Window.this, "Are you sure you want to RESET ? Changes you have made will not be saved !",
            "Reset order editation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (dialogResult == 0){
            isTableOn = false;  isMainContainerOn = false;
            fillTextFieldsFromDB(orderIDFromTable);
            checkValidityAll();
            initEditForm();
            sysMSG = 13;
            updateAll();
            scrollPane.setBorder(BorderFactory.createTitledBorder(null, "Order editing: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 0, 0)));}
        } catch (SQLException ex) {
            exceptionState("Database Error. Could not load order from database.\n" +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }


//== NESTING & INNER CLASSES ===================================================
//== MAIN METHOD ===============================================================
    
}
