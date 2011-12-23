
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Newapp.java
 *
 * Created on 15.11.2011, 15:56:36
 */
/**
 *
 * @author woxie
 */
public class Newapp extends javax.swing.JFrame {
    /** Creates new form Newapp */
    private static final int STATE_PAINTING_LINE_CHOSEN = 0;
    
    private static final int STATE_CHANGING_COLOR = 1;
    
    private static final int STATE_CREATING_SHAPE = 2;
    
    private static final int STATE_CHANGING_COORDINATES = 3;
        
    private static final int STATE_START_CHOSEN = 5;
    
    private static final int STATE_CHOOSE_FLOOR = 7;
    
    private static final int STATE_CHOOSE_BUILDING = 6;

    private static final int STATE_CHOOSE_ROOM = 8;
    
    private static final Dimension BUTTON_DIMENSION = new Dimension(120, 38);
    private static final String TITLE_COLOR_CHOOSING_WIN_FILL_COLOR = "Choose inner color";
    private static final String TITLE_COLOR_CHOOSING_WIN_BORDER_COLOR = "Choose border color";
    
    
    private int state;
    private int xLineStart;
    private int yLineStart;
    private Dimension clickPlace;
    private List<GraphicObject> objectsOnScreen;
    private GraphicObject movedObject;
    
    
    public Newapp() {
        initComponents();
        winSetCoordinates.setVisible(false);
        buttonShapeState.setVisible(false);
        buttonBarellState.setVisible(false);
        buttonBrushstate.setVisible(false);
        panelShapeList.setVisible(false);
        buttonCoordinateState.setVisible(false);
        mainPanel.setVisible(false);
        winChoose.setVisible(false);
        panelBorderColor.setVisible(false);
        objectsOnScreen = new ArrayList<GraphicObject>();
        startDemo();
        objectsOnScreen.clear();
    }

    private void initChooseWin(){
        winChoose.setModal(true);
        winChoose.setBounds(400, 400, 420, 320);
        winChoose.setResizable(false);
        
        buttonOkChooseWin.setVisible(false);
        buttonBackChooseWin.setVisible(false);
        buttonCancelChooseWin.setVisible(true);
        buttonNextChooseWin.setBounds(buttonOkChooseWin.getX(), buttonOkChooseWin.getY(), buttonOkChooseWin.getWidth(), buttonOkChooseWin.getHeight());
        buttonNextChooseWin.setVisible(true);
        groupRoomsChooseWin.add(radioButtFirstFloorChooseWin);
        groupRoomsChooseWin.add(radioButtSecondFloorChooseWin);
        groupRoomsChooseWin.add(radioButtThirdFloorChooseWin);
        radioButtFirstFloorChooseWin.setVisible(false);
        radioButtSecondFloorChooseWin.setVisible(false);
        radioButtThirdFloorChooseWin.setVisible(false);
        labelBuildingsChooseWin.setVisible(true);
        labelRoomsChooseWin.setVisible(false);
        
        boxRoomsChooseWin.setVisible(false);
        labelFloorChooseWin.setVisible(false);
    }
   
    private void performStateActions(){
        if(state == STATE_PAINTING_LINE_CHOSEN){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            System.out.println("PAINTING LINE");
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
        
        if(state == STATE_CREATING_SHAPE){
            buttonShapeOval.setEnabled(true);
            buttonShapeRectangle.setEnabled(true);
            buttonShapePolygon.setEnabled(true);
            buttonShapeOval.setSelected(true);
            System.out.println("CREATING SHAPES");
        } else{
            buttonShapeOval.setEnabled(false);
            buttonShapeRectangle.setEnabled(false);
            buttonShapePolygon.setEnabled(false);            
        }
    }
    
    private void startDemo(){
        state = STATE_CHANGING_COORDINATES;
        
        buttonBarellState.setVisible(true);
        buttonBrushstate.setVisible(true);
        buttonShapeState.setVisible(true);
        buttonCoordinateState.setVisible(true);
        buttonCoordinateState.setSelected(true);
        
        panelShapeList.setVisible(true);
        groupShapes.add(buttonShapeOval);
        groupShapes.add(buttonShapePolygon);
        groupShapes.add(buttonShapeRectangle);
        
        groupStates.add(buttonBarellState);
        groupStates.add(buttonBrushstate);
        groupStates.add(buttonCoordinateState);
        groupStates.add(buttonShapeState);
        
        mainPanel.setVisible(true);
        panelBorderColor.setVisible(true);
        Rectangle rectangle = new Rectangle(200, 200, 200, 250);
        rectangle.setBorderColor(Color.yellow);
        rectangle.setInnerColor(Color.BLUE);
        rectangle.draw(mainPanel.getGraphics());
        objectsOnScreen.add(rectangle);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        winSetCoordinates = new javax.swing.JDialog();
        buttonOkCoordinatesWin = new javax.swing.JButton();
        textXCoordinatesWin = new javax.swing.JTextField();
        textYCoordinatesWin = new javax.swing.JTextField();
        labelXCoordinatesWin = new javax.swing.JLabel();
        labelYCoordinatesWin = new javax.swing.JLabel();
        buttonCancelCoordinatesWin = new javax.swing.JButton();
        labelWidthSetCoordinatesWin = new javax.swing.JLabel();
        labelHeightSetCoordinatesWin = new javax.swing.JLabel();
        textHeightCoordinatesWin = new javax.swing.JTextField();
        textWidthCoordinatesWin = new javax.swing.JTextField();
        winChoose = new javax.swing.JDialog();
        buttonCancelChooseWin = new javax.swing.JButton();
        buttonOkChooseWin = new javax.swing.JButton();
        labelBuildingsChooseWin = new javax.swing.JLabel();
        buttonNextChooseWin = new javax.swing.JButton();
        boxBuildingsChooseWin = new javax.swing.JComboBox();
        boxRoomsChooseWin = new javax.swing.JComboBox();
        buttonBackChooseWin = new javax.swing.JButton();
        labelRoomsChooseWin = new javax.swing.JLabel();
        radioButtFirstFloorChooseWin = new javax.swing.JRadioButton();
        radioButtSecondFloorChooseWin = new javax.swing.JRadioButton();
        radioButtThirdFloorChooseWin = new javax.swing.JRadioButton();
        labelFloorChooseWin = new javax.swing.JLabel();
        groupRoomsChooseWin = new javax.swing.ButtonGroup();
        groupShapes = new javax.swing.ButtonGroup();
        groupStates = new javax.swing.ButtonGroup();
        winColorChoosing = new javax.swing.JDialog();
        buttonOkWinColorChoosing = new javax.swing.JButton();
        buttonCancelWinColorChoosing = new javax.swing.JButton();
        colorChooser = new javax.swing.JColorChooser();
        winOvalCreating = new javax.swing.JDialog();
        buttonOkOvalCreated = new javax.swing.JButton();
        textHeightOvalWin = new javax.swing.JTextField();
        textWidthOvalWin = new javax.swing.JTextField();
        labelHeightOvalWin = new javax.swing.JLabel();
        labelWidthOvalWin = new javax.swing.JLabel();
        buttonCancelOvalCreatingWin = new javax.swing.JButton();
        winRectangleCreating = new javax.swing.JDialog();
        buttonOkWinRectangleCreating = new javax.swing.JButton();
        buttonCancelWinRectangleCreating = new javax.swing.JButton();
        textAreaWidthWinRectangleCreating = new javax.swing.JTextField();
        textAreaHeightWinRectangleCreating = new javax.swing.JTextField();
        labelWidthWinRectangleCreating = new javax.swing.JLabel();
        labelHeightWinRectangleCreating = new javax.swing.JLabel();
        winPolygonCreating = new javax.swing.JDialog();
        winAboutProgram = new javax.swing.JDialog();
        buttonOkWinAboutProgram = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        mainPanel = new MyJPanel(){
            public void repaint(){
                Graphics gr = getGraphics();
                if(gr == null || objectsOnScreen == null){
                    return;
                }
                clear();
                for(GraphicObject grOb: objectsOnScreen ){
                    grOb.draw(gr);
                }
            }

            public void clear(){
                Graphics gr = getGraphics();
                gr.setColor(getBackground());
                gr.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        ;
        jScrollPane1 = new javax.swing.JScrollPane();
        panelShapeList = new javax.swing.JPanel();
        buttonShapeRectangle = new javax.swing.JToggleButton();
        buttonShapeOval = new javax.swing.JToggleButton();
        buttonShapePolygon = new javax.swing.JToggleButton();
        buttonCoordinateState = new javax.swing.JToggleButton();
        buttonShapeState = new javax.swing.JToggleButton();
        buttonBrushstate = new javax.swing.JToggleButton();
        buttonBarellState = new javax.swing.JToggleButton();
        panelBorderColor = new javax.swing.JPanel();
        panelFillColor = new javax.swing.JPanel();
        textFieldBorderColor = new javax.swing.JTextField();
        TextFieldInnerColor = new javax.swing.JTextField();
        mainMenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuFileNewItem = new javax.swing.JMenuItem();
        menuFileOpenItem = new javax.swing.JMenuItem();
        menuFileSaveItem = new javax.swing.JMenuItem();
        menuFileExitItem = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuItemAbout = new javax.swing.JMenuItem();

        winSetCoordinates.setTitle("Nastavení souřadnic a rozměrů");

        buttonOkCoordinatesWin.setText("OK");
        buttonOkCoordinatesWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkCoordinatesWinActionPerformed(evt);
            }
        });

        textXCoordinatesWin.setText("0");
        textXCoordinatesWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textXCoordinatesWinActionPerformed(evt);
            }
        });

        textYCoordinatesWin.setText("0");

        labelXCoordinatesWin.setText("souřadnice x");

        labelYCoordinatesWin.setText("souřadnice y");

        buttonCancelCoordinatesWin.setText("Cancel");
        buttonCancelCoordinatesWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelCoordinatesWinActionPerformed(evt);
            }
        });

        labelWidthSetCoordinatesWin.setText("šířka");

        labelHeightSetCoordinatesWin.setText("výška");

        textHeightCoordinatesWin.setText("0");

        textWidthCoordinatesWin.setText("0");
        textWidthCoordinatesWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textWidthCoordinatesWinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout winSetCoordinatesLayout = new javax.swing.GroupLayout(winSetCoordinates.getContentPane());
        winSetCoordinates.getContentPane().setLayout(winSetCoordinatesLayout);
        winSetCoordinatesLayout.setHorizontalGroup(
            winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(winSetCoordinatesLayout.createSequentialGroup()
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(winSetCoordinatesLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(buttonOkCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancelCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(winSetCoordinatesLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelXCoordinatesWin)
                            .addComponent(textXCoordinatesWin, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(labelWidthSetCoordinatesWin)
                            .addComponent(textWidthCoordinatesWin))
                        .addGap(18, 18, 18)
                        .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelYCoordinatesWin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textYCoordinatesWin, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(labelHeightSetCoordinatesWin)
                            .addComponent(textHeightCoordinatesWin))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        winSetCoordinatesLayout.setVerticalGroup(
            winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, winSetCoordinatesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeightSetCoordinatesWin)
                    .addComponent(labelWidthSetCoordinatesWin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textWidthCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textHeightCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelYCoordinatesWin)
                    .addComponent(labelXCoordinatesWin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textXCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textYCoordinatesWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(winSetCoordinatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOkCoordinatesWin)
                    .addComponent(buttonCancelCoordinatesWin))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        winChoose.setMinimumSize(new java.awt.Dimension(200, 270));
        winChoose.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonCancelChooseWin.setText("Cancel");
        buttonCancelChooseWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelChooseWinActionPerformed(evt);
            }
        });
        winChoose.getContentPane().add(buttonCancelChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 120, 38));

        buttonOkChooseWin.setText("OK");
        buttonOkChooseWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkChooseWinActionPerformed(evt);
            }
        });
        winChoose.getContentPane().add(buttonOkChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 38));

        labelBuildingsChooseWin.setText("Budovy");
        winChoose.getContentPane().add(labelBuildingsChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 86, 20));

        buttonNextChooseWin.setText("Next");
        buttonNextChooseWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextChooseWinActionPerformed(evt);
            }
        });
        winChoose.getContentPane().add(buttonNextChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 120, 38));

        boxBuildingsChooseWin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BB E", "BB Filadelfie", "Centrum Chodov", "OC Cerna Ruze" }));
        winChoose.getContentPane().add(boxBuildingsChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        boxRoomsChooseWin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "234\t", "123", "11", "1024", "54", "2", "3454", "232", "5", "322", "323", "221", "565", "333" }));
        winChoose.getContentPane().add(boxRoomsChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 74, -1));

        buttonBackChooseWin.setText("Back");
        buttonBackChooseWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackChooseWinActionPerformed(evt);
            }
        });
        winChoose.getContentPane().add(buttonBackChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 120, 38));

        labelRoomsChooseWin.setText("Místnosti");
        winChoose.getContentPane().add(labelRoomsChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 60, 11));

        radioButtFirstFloorChooseWin.setText("První patro");
        winChoose.getContentPane().add(radioButtFirstFloorChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 75, 100, -1));

        radioButtSecondFloorChooseWin.setText("Druhé patro");
        winChoose.getContentPane().add(radioButtSecondFloorChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 98, 110, -1));

        radioButtThirdFloorChooseWin.setText("Třetí patro");
        winChoose.getContentPane().add(radioButtThirdFloorChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 124, 90, -1));

        labelFloorChooseWin.setText("Patra");
        winChoose.getContentPane().add(labelFloorChooseWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 125, 23));

        winColorChoosing.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        winColorChoosing.setPreferredSize(new Dimension(200, 400));
        winColorChoosing.setBounds(200, 400, 500, 430);
        winColorChoosing.setResizable(false);

        buttonOkWinColorChoosing.setText("Ok");
        buttonOkWinColorChoosing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkWinColorChoosingActionPerformed(evt);
            }
        });
        winColorChoosing.getContentPane().add(buttonOkWinColorChoosing, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 140, 38));

        buttonCancelWinColorChoosing.setText("Cancel");
        buttonCancelWinColorChoosing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelWinColorChoosingActionPerformed(evt);
            }
        });
        winColorChoosing.getContentPane().add(buttonCancelWinColorChoosing, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 150, 38));
        winColorChoosing.getContentPane().add(colorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        winOvalCreating.setTitle("Vytvoření oválu");
        winOvalCreating.setResizable(false);
        winOvalCreating.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonOkOvalCreated.setText("Ok");
        buttonOkOvalCreated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkOvalCreatedActionPerformed(evt);
            }
        });
        winOvalCreating.getContentPane().add(buttonOkOvalCreated, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, 38));

        textHeightOvalWin.setText("0");
        textHeightOvalWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHeightOvalWinActionPerformed(evt);
            }
        });
        winOvalCreating.getContentPane().add(textHeightOvalWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 60, 20));

        textWidthOvalWin.setText("0");
        winOvalCreating.getContentPane().add(textWidthOvalWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 60, -1));

        labelHeightOvalWin.setText("Výška");
        winOvalCreating.getContentPane().add(labelHeightOvalWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        labelWidthOvalWin.setText("Šířka");
        winOvalCreating.getContentPane().add(labelWidthOvalWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        buttonCancelOvalCreatingWin.setText("Cancel");
        buttonCancelOvalCreatingWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelOvalCreatingWinActionPerformed(evt);
            }
        });
        winOvalCreating.getContentPane().add(buttonCancelOvalCreatingWin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 120, 38));

        winRectangleCreating.setTitle("Vytvoření obdélníku");
        winRectangleCreating.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonOkWinRectangleCreating.setText("Ok");
        buttonOkWinRectangleCreating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkWinRectangleCreatingActionPerformed(evt);
            }
        });
        winRectangleCreating.getContentPane().add(buttonOkWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 120, 38));

        buttonCancelWinRectangleCreating.setText("Cancel");
        buttonCancelWinRectangleCreating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelWinRectangleCreatingActionPerformed(evt);
            }
        });
        winRectangleCreating.getContentPane().add(buttonCancelWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 120, 38));

        textAreaWidthWinRectangleCreating.setText("0");
        winRectangleCreating.getContentPane().add(textAreaWidthWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 70, 20));

        textAreaHeightWinRectangleCreating.setText("0");
        textAreaHeightWinRectangleCreating.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textAreaHeightWinRectangleCreatingPropertyChange(evt);
            }
        });
        winRectangleCreating.getContentPane().add(textAreaHeightWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 70, -1));

        labelWidthWinRectangleCreating.setText("šířka");
        winRectangleCreating.getContentPane().add(labelWidthWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        labelHeightWinRectangleCreating.setText("výška");
        winRectangleCreating.getContentPane().add(labelHeightWinRectangleCreating, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        javax.swing.GroupLayout winPolygonCreatingLayout = new javax.swing.GroupLayout(winPolygonCreating.getContentPane());
        winPolygonCreating.getContentPane().setLayout(winPolygonCreatingLayout);
        winPolygonCreatingLayout.setHorizontalGroup(
            winPolygonCreatingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        winPolygonCreatingLayout.setVerticalGroup(
            winPolygonCreatingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        winAboutProgram.setTitle("O programu");
        winAboutProgram.setResizable(false);
        winAboutProgram.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonOkWinAboutProgram.setText("Ok");
        buttonOkWinAboutProgram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkWinAboutProgramActionPerformed(evt);
            }
        });
        winAboutProgram.getContentPane().add(buttonOkWinAboutProgram, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 78, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("      Tento program byl vytvořen v roce 2011 jako semestrální práce Martina Lukeše v rámci předmětu Návrh Uživatelského Rozhraní (A4M39NUR)");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleParent(winAboutProgram);

        winAboutProgram.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jednoduchý/Komplexní grafický editor");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusTraversalPolicyProvider(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainPanelMouseReleased(evt);
            }
        });
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, -1, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 960, 460));

        panelShapeList.setBackground(new java.awt.Color(255, 255, 255));
        panelShapeList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonShapeRectangle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Rectangle.png"))); // NOI18N
        panelShapeList.add(buttonShapeRectangle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 34));

        buttonShapeOval.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Circle.png"))); // NOI18N
        panelShapeList.add(buttonShapeOval, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 51, 50, -1));

        buttonShapePolygon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Polygon.png"))); // NOI18N
        buttonShapePolygon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShapePolygonActionPerformed(evt);
            }
        });
        panelShapeList.add(buttonShapePolygon, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 50, 34));

        getContentPane().add(panelShapeList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 120, 90));

        buttonCoordinateState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/default-grid.png"))); // NOI18N
        buttonCoordinateState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonCoordinateStateItemStateChanged(evt);
            }
        });
        getContentPane().add(buttonCoordinateState, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, 48));

        buttonShapeState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/display.png"))); // NOI18N
        buttonShapeState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonShapeStateItemStateChanged(evt);
            }
        });
        buttonShapeState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonShapeStateActionPerformed(evt);
            }
        });
        getContentPane().add(buttonShapeState, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 120, 50));

        buttonBrushstate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folders-brushes-22.png"))); // NOI18N
        buttonBrushstate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonBrushstateItemStateChanged(evt);
            }
        });
        buttonBrushstate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBrushstateActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBrushstate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, 40));

        buttonBarellState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folders-patterns-22.png"))); // NOI18N
        buttonBarellState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                buttonBarellStateItemStateChanged(evt);
            }
        });
        getContentPane().add(buttonBarellState, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, 40));

        panelBorderColor.setBackground(new java.awt.Color(255, 51, 51));
        panelBorderColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBorderColorMouseClicked(evt);
            }
        });
        panelBorderColor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelBorderColorPropertyChange(evt);
            }
        });

        panelFillColor.setBackground(new java.awt.Color(0, 0, 255));
        panelFillColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelFillColorMouseClicked(evt);
            }
        });
        panelFillColor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                panelFillColorPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout panelFillColorLayout = new javax.swing.GroupLayout(panelFillColor);
        panelFillColor.setLayout(panelFillColorLayout);
        panelFillColorLayout.setHorizontalGroup(
            panelFillColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelFillColorLayout.setVerticalGroup(
            panelFillColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelBorderColorLayout = new javax.swing.GroupLayout(panelBorderColor);
        panelBorderColor.setLayout(panelBorderColorLayout);
        panelBorderColorLayout.setHorizontalGroup(
            panelBorderColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderColorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelFillColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelBorderColorLayout.setVerticalGroup(
            panelBorderColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderColorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panelFillColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(panelBorderColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 100, 90));

        textFieldBorderColor.setEditable(false);
        textFieldBorderColor.setForeground(new java.awt.Color(255, 0, 51));
        textFieldBorderColor.setText("Barva okraje");
        textFieldBorderColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBorderColorActionPerformed(evt);
            }
        });
        getContentPane().add(textFieldBorderColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 80, -1));

        TextFieldInnerColor.setEditable(false);
        TextFieldInnerColor.setForeground(new java.awt.Color(51, 0, 255));
        TextFieldInnerColor.setText("Vnitřní barva");
        TextFieldInnerColor.setCaretColor(new java.awt.Color(204, 0, 255));
        getContentPane().add(TextFieldInnerColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 347, 80, -1));

        menuFile.setText("Soubor");

        menuFileNewItem.setText("Nový plán");
        menuFileNewItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileNewItemActionPerformed(evt);
            }
        });
        menuFile.add(menuFileNewItem);

        menuFileOpenItem.setText("Otevřít plán");
        menuFileOpenItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileOpenItemActionPerformed(evt);
            }
        });
        menuFile.add(menuFileOpenItem);

        menuFileSaveItem.setText("Uložit plán");
        menuFile.add(menuFileSaveItem);

        menuFileExitItem.setText("Konec");
        menuFileExitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileExitItemActionPerformed(evt);
            }
        });
        menuFile.add(menuFileExitItem);

        mainMenuBar.add(menuFile);

        menuHelp.setText("Nápověda");

        menuItemAbout.setText("O aplikaci");
        menuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(menuItemAbout);

        mainMenuBar.add(menuHelp);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCoordinateStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonCoordinateStateItemStateChanged
        buttonGroupChanged((JToggleButton)evt.getItem(), STATE_CHANGING_COORDINATES);
    }//GEN-LAST:event_buttonCoordinateStateItemStateChanged

    private void buttonGroupChanged(JToggleButton button, int buttonState){
         if(button.isSelected()){
            state = buttonState;
            performStateActions();
         }
    }
    
    private void buttonShapeStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonShapeStateItemStateChanged
         buttonGroupChanged((JToggleButton)evt.getItem(), STATE_CREATING_SHAPE);
    }//GEN-LAST:event_buttonShapeStateItemStateChanged

    private void buttonBrushstateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonBrushstateItemStateChanged
        buttonGroupChanged((JToggleButton)evt.getItem(), STATE_PAINTING_LINE_CHOSEN);
    }//GEN-LAST:event_buttonBrushstateItemStateChanged

    private void buttonBarellStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_buttonBarellStateItemStateChanged
         buttonGroupChanged((JToggleButton)evt.getItem(), STATE_CHANGING_COLOR);
    }//GEN-LAST:event_buttonBarellStateItemStateChanged

    private void mainPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseReleased
        if(state == STATE_START_CHOSEN){
            int xLineEnd = evt.getX();
            int yLineEnd = evt.getY();
            Graphics graphics = mainPanel.getGraphics();
            Line line = new Line(xLineStart, yLineStart, xLineEnd, yLineEnd);
            line.setInnerColor(panelFillColor.getBackground());
            line.setBorderColor(panelBorderColor.getBackground());
            objectsOnScreen.add(line);
            line.draw(graphics);
            objectsOnScreen.add(line);
            state = STATE_PAINTING_LINE_CHOSEN;
        }
    }//GEN-LAST:event_mainPanelMouseReleased

    private void mainPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMousePressed
        if(state == STATE_PAINTING_LINE_CHOSEN){
            xLineStart = evt.getX();
            yLineStart = evt.getY();
            state = STATE_START_CHOSEN;
        } else if(state == STATE_CREATING_SHAPE){
            GraphicObject shape = getShape();
            System.out.println(panelBorderColor.getBackground());
            System.out.println(panelFillColor.getBackground());
            shape.setBorderColor(panelBorderColor.getBackground());
            shape.setInnerColor(panelFillColor.getBackground());
            clickPlace = new Dimension(evt.getX(), evt.getY());
            if(shape instanceof Oval){
                winOvalCreating.setBounds(200, 200, 290, 187);
                winOvalCreating.setVisible(true);
            } else if(shape instanceof Rectangle){
                winRectangleCreating.setBounds(200, 200, 290, 187);
                winRectangleCreating.setVisible(true);
            } else if(shape instanceof Polygon){
                
            }
            objectsOnScreen.add(shape);
        }
    }//GEN-LAST:event_mainPanelMousePressed

    private void paintStateChooseFloor(){
        buttonCancelChooseWin.setVisible(true);
        buttonBackChooseWin.setVisible(true);
        buttonNextChooseWin.setVisible(true);
        buttonOkChooseWin.setVisible(false);
        
        radioButtFirstFloorChooseWin.setVisible(true);
        radioButtSecondFloorChooseWin.setVisible(true);
        radioButtThirdFloorChooseWin.setVisible(true);

        labelBuildingsChooseWin.setVisible(false);
        labelRoomsChooseWin.setVisible(false);
        boxBuildingsChooseWin.setVisible(false);
        boxRoomsChooseWin.setVisible(false);
        
        radioButtFirstFloorChooseWin.setSelected(true);
        labelFloorChooseWin.setVisible(true);
    }
    
    
    private void paintStateChooseRoom(){
        //buttonOkChooseWin.setAlignmentX(20);
        //buttonOkChooseWin.setAlignmentY(170);
        
        buttonOkChooseWin.setVisible(true);
        buttonCancelChooseWin.setVisible(true);
        buttonBackChooseWin.setVisible(true);
        buttonNextChooseWin.setVisible(false);
        
        radioButtFirstFloorChooseWin.setVisible(false);
        radioButtSecondFloorChooseWin.setVisible(false);
        radioButtThirdFloorChooseWin.setVisible(false);

        labelBuildingsChooseWin.setVisible(false);
        labelFloorChooseWin.setVisible(false);
        radioButtFirstFloorChooseWin.setSelected(true);
        boxBuildingsChooseWin.setVisible(false);
        
        boxRoomsChooseWin.setVisible(true);
        labelRoomsChooseWin.setVisible(true);
        
    }
    
    private void paintStateChooseBuilding(){
        buttonOkChooseWin.setVisible(false);
        buttonCancelChooseWin.setVisible(true);
        buttonBackChooseWin.setVisible(false);
        buttonNextChooseWin.setVisible(true);
        radioButtFirstFloorChooseWin.setVisible(false);
        radioButtSecondFloorChooseWin.setVisible(false);
        radioButtThirdFloorChooseWin.setVisible(false);

        labelBuildingsChooseWin.setVisible(true);
        labelRoomsChooseWin.setVisible(false);
        labelFloorChooseWin.setVisible(false);
        boxBuildingsChooseWin.setVisible(true);
        boxRoomsChooseWin.setVisible(false);
    }
    
    private void buttonNextChooseWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextChooseWinActionPerformed
        if(state == STATE_CHOOSE_BUILDING){
            state = STATE_CHOOSE_FLOOR;
            paintStateChooseFloor();
            System.out.println("building -> FLOOR" );
        } else if (state == STATE_CHOOSE_FLOOR){
            System.out.println("FLOOR -> ROOM" );
            state = STATE_CHOOSE_ROOM;
            paintStateChooseRoom();    
        }
    }//GEN-LAST:event_buttonNextChooseWinActionPerformed

    private void buttonCancelCoordinatesWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelCoordinatesWinActionPerformed
        winSetCoordinates.setVisible(false);
    }//GEN-LAST:event_buttonCancelCoordinatesWinActionPerformed

    private void buttonCancelChooseWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelChooseWinActionPerformed
        winChoose.setVisible(false);
    }//GEN-LAST:event_buttonCancelChooseWinActionPerformed

    private void buttonBackChooseWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackChooseWinActionPerformed
        if(state == STATE_CHOOSE_FLOOR){
           state = STATE_CHOOSE_BUILDING;
           paintStateChooseBuilding();
           System.out.println("FLOOR -> building" );
        } else if(state == STATE_CHOOSE_ROOM){
            state = STATE_CHOOSE_FLOOR;
            paintStateChooseFloor();
            System.out.println("room -> floor" );
        }
    }//GEN-LAST:event_buttonBackChooseWinActionPerformed

    private void buttonOkChooseWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkChooseWinActionPerformed
        winChoose.setVisible(false);
        ((MyJPanel)mainPanel).clear();
        startDemo();
    }//GEN-LAST:event_buttonOkChooseWinActionPerformed

    private void buttonOkCoordinatesWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkCoordinatesWinActionPerformed
        try{
            int x = Integer.parseInt(textXCoordinatesWin.getText());
            int y = Integer.parseInt(textYCoordinatesWin.getText());
            int width = Integer.parseInt(textWidthCoordinatesWin.getText());
            int height = Integer.parseInt(textHeightCoordinatesWin.getText());
            movedObject.xCenter = x;
            movedObject.yCenter = y;
            movedObject.width = width;
            movedObject.height = height;
            movedObject.clean(mainPanel.getGraphics());
            Graphics gr = mainPanel.getGraphics();
            gr.setColor(mainPanel.getBackground());
            mainPanel.repaint();
            winSetCoordinates.setVisible(false);                    
        }catch (NumberFormatException ex){
            
        }

    }//GEN-LAST:event_buttonOkCoordinatesWinActionPerformed

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked
        if(state == STATE_CHANGING_COLOR){
            mainPanel.setBackground(panelFillColor.getBackground());
        } else if (state == STATE_CHANGING_COORDINATES){
            GraphicObject grObject = null;
            int i = objectsOnScreen.size() - 1 ;
            for(; i >= 0 ; i--){
                grObject = objectsOnScreen.get(i);
                if(grObject.isIn(evt.getX(), evt.getY())){
                    movedObject= grObject;
                    break;
                }
            }
            if(movedObject != null){
//                GraphicObject temp = objectsOnScreen.get(objectsOnScreen.size()-1);
//                objectsOnScreen.set(objectsOnScreen.size()-1, movedObject);
//                for(int j = i. ; ){
//                    
//                }
                winSetCoordinates.setBounds(100, 200, 300, 250);
                winSetCoordinates.setVisible(true);
            }
        }
    }//GEN-LAST:event_mainPanelMouseClicked

    private void buttonCancelOvalCreatingWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelOvalCreatingWinActionPerformed
        winOvalCreating.setVisible(false);
    }//GEN-LAST:event_buttonCancelOvalCreatingWinActionPerformed

    private void buttonOkOvalCreatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkOvalCreatedActionPerformed
        Oval oval = (Oval)getShape();
        try{
            int width = Integer.parseInt(textWidthOvalWin.getText());
            int height = Integer.parseInt(textHeightOvalWin.getText());
            oval.setHeight(height);
            oval.setWidth(width);
            oval.setXCenter(clickPlace.width);
            oval.setYCenter(clickPlace.height);
            winOvalCreating.setVisible(false);
            Graphics graphics = mainPanel.getGraphics();
            oval.draw(graphics);
            objectsOnScreen.add(oval);
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonOkOvalCreatedActionPerformed

private void menuFileExitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileExitItemActionPerformed
    System.exit(0);
}//GEN-LAST:event_menuFileExitItemActionPerformed

private void menuFileOpenItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileOpenItemActionPerformed
   state = STATE_CHOOSE_BUILDING;
   initChooseWin();
   winChoose.setVisible(true);
}//GEN-LAST:event_menuFileOpenItemActionPerformed

private void panelFillColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFillColorMouseClicked
    winColorChoosing.setTitle(TITLE_COLOR_CHOOSING_WIN_FILL_COLOR);
    winColorChoosing.setVisible(true);
}//GEN-LAST:event_panelFillColorMouseClicked

private void panelBorderColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorderColorMouseClicked
    winColorChoosing.setTitle(TITLE_COLOR_CHOOSING_WIN_BORDER_COLOR);
    winColorChoosing.setVisible(true);
}//GEN-LAST:event_panelBorderColorMouseClicked

private void buttonCancelWinColorChoosingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelWinColorChoosingActionPerformed
    winColorChoosing.setVisible(false);
}//GEN-LAST:event_buttonCancelWinColorChoosingActionPerformed

private void buttonOkWinColorChoosingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkWinColorChoosingActionPerformed
    if(winColorChoosing.getTitle().equals(TITLE_COLOR_CHOOSING_WIN_BORDER_COLOR)){
        panelBorderColor.setBackground(colorChooser.getColor());
    } else{
        panelFillColor.setBackground(colorChooser.getColor());
    }
    winColorChoosing.setVisible(false);
}//GEN-LAST:event_buttonOkWinColorChoosingActionPerformed

private void buttonOkWinRectangleCreatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkWinRectangleCreatingActionPerformed
   Rectangle rectangle = (Rectangle)getShape();
        try{
            int width = Integer.parseInt(textAreaWidthWinRectangleCreating.getText());
            int height = Integer.parseInt(textAreaHeightWinRectangleCreating.getText());
            rectangle.setHeight(height);
            rectangle.setWidth(width);
            rectangle.setXCenter(clickPlace.width);
            rectangle.setYCenter(clickPlace.height);
            winRectangleCreating.setVisible(false);
            Graphics graphics = mainPanel.getGraphics();
            rectangle.draw(graphics);
            objectsOnScreen.add(rectangle);
        }catch(NumberFormatException ex){
            ex.printStackTrace();
        }
}//GEN-LAST:event_buttonOkWinRectangleCreatingActionPerformed

private void textAreaHeightWinRectangleCreatingPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textAreaHeightWinRectangleCreatingPropertyChange
    
}//GEN-LAST:event_textAreaHeightWinRectangleCreatingPropertyChange

private void buttonCancelWinRectangleCreatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelWinRectangleCreatingActionPerformed
    winRectangleCreating.setVisible(false);
}//GEN-LAST:event_buttonCancelWinRectangleCreatingActionPerformed

private void clearPaintingSpace(){
    objectsOnScreen.clear();
    mainPanel.setBackground(Color.WHITE);
    ((MyJPanel)mainPanel).clear();
}

private void menuFileNewItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileNewItemActionPerformed
   clearPaintingSpace();
}//GEN-LAST:event_menuFileNewItemActionPerformed

private void menuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAboutActionPerformed
    winAboutProgram.setBounds(200, 200, 370, 200);
    winAboutProgram.setVisible(true);
}//GEN-LAST:event_menuItemAboutActionPerformed

private void buttonOkWinAboutProgramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkWinAboutProgramActionPerformed
    winAboutProgram.setVisible(false);
}//GEN-LAST:event_buttonOkWinAboutProgramActionPerformed

private void panelBorderColorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelBorderColorPropertyChange
    if(evt.getPropertyName().equals("background")){
        textFieldBorderColor.setForeground((Color)evt.getNewValue());
    }
}//GEN-LAST:event_panelBorderColorPropertyChange

private void panelFillColorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_panelFillColorPropertyChange
if(evt.getPropertyName().equals("background")){
        TextFieldInnerColor.setForeground((Color)evt.getNewValue());
    }
}//GEN-LAST:event_panelFillColorPropertyChange

private void textWidthCoordinatesWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textWidthCoordinatesWinActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_textWidthCoordinatesWinActionPerformed

private void buttonShapePolygonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShapePolygonActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_buttonShapePolygonActionPerformed

private void textXCoordinatesWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textXCoordinatesWinActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_textXCoordinatesWinActionPerformed

private void textHeightOvalWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHeightOvalWinActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_textHeightOvalWinActionPerformed

private void buttonShapeStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonShapeStateActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_buttonShapeStateActionPerformed

private void buttonBrushstateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBrushstateActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_buttonBrushstateActionPerformed

private void textFieldBorderColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBorderColorActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_textFieldBorderColorActionPerformed

    public GraphicObject getShape(){
        GraphicObject graphicObject = null;
        if(buttonShapeRectangle.isSelected()){
            graphicObject = new Rectangle();
        } else
        if(buttonShapeOval.isSelected()){
            graphicObject = new Oval();
        } else
        if(buttonShapePolygon.isSelected()){
            graphicObject = new Polygon();
        }
        graphicObject.setBorderColor(panelBorderColor.getBackground());
        graphicObject.setInnerColor(panelFillColor.getBackground());
        return graphicObject;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Newapp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextFieldInnerColor;
    private javax.swing.JComboBox boxBuildingsChooseWin;
    private javax.swing.JComboBox boxRoomsChooseWin;
    private javax.swing.JButton buttonBackChooseWin;
    private javax.swing.JToggleButton buttonBarellState;
    private javax.swing.JToggleButton buttonBrushstate;
    private javax.swing.JButton buttonCancelChooseWin;
    private javax.swing.JButton buttonCancelCoordinatesWin;
    private javax.swing.JButton buttonCancelOvalCreatingWin;
    private javax.swing.JButton buttonCancelWinColorChoosing;
    private javax.swing.JButton buttonCancelWinRectangleCreating;
    private javax.swing.JToggleButton buttonCoordinateState;
    private javax.swing.JButton buttonNextChooseWin;
    private javax.swing.JButton buttonOkChooseWin;
    private javax.swing.JButton buttonOkCoordinatesWin;
    private javax.swing.JButton buttonOkOvalCreated;
    private javax.swing.JButton buttonOkWinAboutProgram;
    private javax.swing.JButton buttonOkWinColorChoosing;
    private javax.swing.JButton buttonOkWinRectangleCreating;
    private javax.swing.JToggleButton buttonShapeOval;
    private javax.swing.JToggleButton buttonShapePolygon;
    private javax.swing.JToggleButton buttonShapeRectangle;
    private javax.swing.JToggleButton buttonShapeState;
    private javax.swing.JColorChooser colorChooser;
    private javax.swing.ButtonGroup groupRoomsChooseWin;
    private javax.swing.ButtonGroup groupShapes;
    private javax.swing.ButtonGroup groupStates;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelBuildingsChooseWin;
    private javax.swing.JLabel labelFloorChooseWin;
    private javax.swing.JLabel labelHeightOvalWin;
    private javax.swing.JLabel labelHeightSetCoordinatesWin;
    private javax.swing.JLabel labelHeightWinRectangleCreating;
    private javax.swing.JLabel labelRoomsChooseWin;
    private javax.swing.JLabel labelWidthOvalWin;
    private javax.swing.JLabel labelWidthSetCoordinatesWin;
    private javax.swing.JLabel labelWidthWinRectangleCreating;
    private javax.swing.JLabel labelXCoordinatesWin;
    private javax.swing.JLabel labelYCoordinatesWin;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileExitItem;
    private javax.swing.JMenuItem menuFileNewItem;
    private javax.swing.JMenuItem menuFileOpenItem;
    private javax.swing.JMenuItem menuFileSaveItem;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JPanel panelBorderColor;
    private javax.swing.JPanel panelFillColor;
    private javax.swing.JPanel panelShapeList;
    private javax.swing.JRadioButton radioButtFirstFloorChooseWin;
    private javax.swing.JRadioButton radioButtSecondFloorChooseWin;
    private javax.swing.JRadioButton radioButtThirdFloorChooseWin;
    private javax.swing.JTextField textAreaHeightWinRectangleCreating;
    private javax.swing.JTextField textAreaWidthWinRectangleCreating;
    private javax.swing.JTextField textFieldBorderColor;
    private javax.swing.JTextField textHeightCoordinatesWin;
    private javax.swing.JTextField textHeightOvalWin;
    private javax.swing.JTextField textWidthCoordinatesWin;
    private javax.swing.JTextField textWidthOvalWin;
    private javax.swing.JTextField textXCoordinatesWin;
    private javax.swing.JTextField textYCoordinatesWin;
    private javax.swing.JDialog winAboutProgram;
    private javax.swing.JDialog winChoose;
    private javax.swing.JDialog winColorChoosing;
    private javax.swing.JDialog winOvalCreating;
    private javax.swing.JDialog winPolygonCreating;
    private javax.swing.JDialog winRectangleCreating;
    private javax.swing.JDialog winSetCoordinates;
    // End of variables declaration//GEN-END:variables
}

abstract class GraphicObject {
    protected Color innerColor;
    protected Color borderColor;
    protected int xCenter;
    protected int yCenter;
    protected int width;
    protected int height;

        public int getxCenter() {
        return xCenter;
    }

    public void setXCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public void setYCenter(int yCenter) {
        this.yCenter = yCenter;
    }
    
    public Color getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(Color color) {
        this.innerColor = color;
    }
    
    public Color getBorderColor(){
        return borderColor;
    }
    
    public void setBorderColor(Color color){
        borderColor = color;
    }
    
        public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    abstract public void clean(Graphics gr);
    
    // void move();
    
    public abstract void draw(Graphics gr);
    
    public abstract int countDistance(int x, int y);
    
    public abstract boolean isIn(int x, int y);
}




class Oval extends GraphicObject{
    /**
     * Dummy oval
     */
    public Oval() {
    }
    
    @Override
    public void draw(Graphics gr) {
       gr.setColor(borderColor);
       gr.fillOval(xCenter-2, yCenter-2, width+4, height+4);
       gr.setColor(innerColor);
       gr.fillOval(xCenter, yCenter, width, height);
    }

    @Override
    public int countDistance(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isIn(int x, int y) {
        return false;
    }

    @Override
   public void clean(Graphics gr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}




class Polygon extends GraphicObject{
    //private int 
    @Override
    public void draw(Graphics gr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countDistance(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isIn(int x, int y) {
        return false;
    }

    @Override
    public void clean(Graphics gr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}



class Rectangle extends GraphicObject{

    /**
     * Dummy rectangle
     */
    public Rectangle() {
    }
    
    
    public Rectangle(int x, int y, int height, int width) {
        this.xCenter = x;
        this.yCenter = y;
        this.height = height;
        this.width = width;
    }
    
    @Override
    public void draw(Graphics gr){
        gr.setColor(borderColor);
        gr.fillRect(xCenter-2, yCenter-2, width+4, height+4);
        gr.setColor(innerColor);
        gr.fillRect(xCenter, yCenter, width, height);
    }

    @Override
    public int countDistance(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isIn(int px, int py) {
        int ax = xCenter - width/2;
        int bx = xCenter + width/2;
        int ay = yCenter + height/2;
        int by = yCenter + height/2;
        
        int cx = xCenter + width/2;
        int dx = xCenter - width/2;
        int cy = yCenter - height/2;
        int dy = yCenter - height/2;
        int cross0 = (py-ay)*(bx-ax)-(px-ax)*(by-ay);
        int cross1 = (py-cy)*(ax-cx)-(px-cx)*(ay-cy);
        int cross4 = (py-dy)*(ax-dx)-(px-dx)*(ay-dy);
        
        return ((cross0*cross1 >= 0) && (((py-by)*(cx-bx)-(px-bx)*(cy-by))*cross1 >= 0)) || ((cross0*cross4 >= 0) && (((py-by)*(dx-bx)-(px-bx)*(dy-by))*cross4 >= 0));
    }

    @Override
    public void clean(Graphics gr) {
        gr.clearRect(xCenter-2, yCenter-2, width+4, height+4);
    }
}

class Line extends GraphicObject{
    private int xStart;
    private int yStart;
    private int xEnd;
    private int yEnd;
    
    public Line(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    
    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    @Override
    public void draw(Graphics gr) {
        gr.setColor(innerColor);
        gr.drawLine(xEnd, yEnd, xStart, yStart);
    }

    @Override
    public int countDistance(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isIn(int x, int y) {
        return false;
    }

    @Override
    public void clean(Graphics gr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

abstract class MyJPanel extends JPanel{
    public MyJPanel(){
        super();
    }
    
    abstract public void clear();
}