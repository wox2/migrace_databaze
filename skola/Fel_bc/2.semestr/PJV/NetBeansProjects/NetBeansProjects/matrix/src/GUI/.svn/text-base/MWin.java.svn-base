/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import Core.Matrix;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author já
 */
public class MWin extends JFrame {

    private MCell[][] cells;
    private Matrix matrix = new Matrix();
    private JButton butt = new JButton("Upravit");
    private JMenuBar mb;
    private MField targetMField;
    private JTextField rozmerSloupce;
    private JTextField rozmerRadky;
    private int constant;

    public MWin(String title, int x, int y, Matrix matrix2, MField target) {
        targetMField = target;
        setTitle(title);
        setSize(500, 500);
        setLocation(x, y);

        setLayout(new GridBagLayout());
        cells = new MCell[matrix.getNumberRows()][matrix.getNumberColumns()];
        insertMatrix(matrix2);
        setResizable(true);

        JMenu matice = new JMenu("Soubor");
        JMenuItem load = new JMenuItem("Nahrát ze souboru", KeyEvent.VK_L);
        load.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogType(JFileChooser.OPEN_DIALOG);
                if (fc.showOpenDialog(getOwner()) == JFileChooser.APPROVE_OPTION) {
                    File filename = fc.getSelectedFile();
                    try {
                        FileInputStream fis = new FileInputStream(filename);
                        ObjectInputStream in = new ObjectInputStream(fis);
                        matrix = (Matrix) in.readObject();
                        in.close();
                        insertMatrix(matrix);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(getOwner(), "Soubor nelze Nahrát.",
                                "Chyba", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex);
                    }
                }
            }
        });

        JMenuItem save = new JMenuItem("Ulozit", KeyEvent.VK_S);
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogType(JFileChooser.SAVE_DIALOG);
                if (fc.showSaveDialog(getOwner()) == JFileChooser.APPROVE_OPTION) {
                    File filename = fc.getSelectedFile();
                    try {
                        FileOutputStream fos = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(fos);
                        out.writeObject(matrix);
                        out.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(getOwner(), "Soubor nelze uložit.",
                                "Chyba", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex);
                    }
                }
            }
        });

        JMenuItem saveXML = new JMenuItem("Ulozit v formatu XML", KeyEvent.VK_X);
        saveXML.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogType(JFileChooser.SAVE_DIALOG);
                if (fc.showSaveDialog(getOwner()) == JFileChooser.APPROVE_OPTION) {
                    File fileName = fc.getSelectedFile();
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                        bw.write(matrix.toXML());
                        bw.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(getOwner(), "Soubor nelze uložit.",
                                "Chyba", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex);
                    }

                }
            }
        });

        matice.add(load);
        matice.add(save);
        matice.add(saveXML);

        mb = new JMenuBar();
        mb.add(matice);
        setJMenuBar(mb);
    }

    private void insertMatrix(Matrix insertedMatrix) {
        matrix = insertedMatrix;
        GridBagConstraints c = new GridBagConstraints();
        Container cp = getContentPane();
        cp.removeAll();
        for (int i = 0; i < matrix.getNumberRows(); i++) {
            for (int j = 0; j < matrix.getNumberColumns(); j++) {
                cells[i][j] = new MCell(i, j);
                cells[i][j].setText(matrix.getField(i, j) + "");
                cells[i][j].addFocusListener(new FocusAdapter() {

                    @Override
                    public void focusLost(FocusEvent e) {
                        int x = ((MCell) e.getSource()).getXPosition();
                        int y = ((MCell) e.getSource()).getYPosition();
                        String s = ((MCell) e.getSource()).getText();

                        try {
                            matrix.setField(x, y, Float.parseFloat(s));
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(getOwner(), "Musíte zadat číslo - např. 0, 5.6, 7.5553...");
                            cells[x][y].setText("" + matrix.getField(x, y));
                        }
                    }
                });
                cells[i][j].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        int x = ((MCell) e.getSource()).getXPosition();
                        int y = ((MCell) e.getSource()).getYPosition();
                        String s = ((MCell) e.getSource()).getText();
                        try {
                            matrix.setField(x, y, Float.parseFloat(s));
                        } catch (NumberFormatException exception) {
                            JOptionPane.showMessageDialog(getOwner(), "Musíte zadat číslo - např. 0, 5.6, 7.5553...");
                        }
                    }
                });
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5/j;
                c.weighty=0.5/i;
                c.gridx = j+1;
                c.gridy = i+1;
                cp.add(cells[i][j], c);
            }
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        cp.add(butt, c);
        butt.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                targetMField.fill(matrix);
                setVisible(false);
            }
        });

        rozmerSloupce = new JTextField("počet sloupců matice="+matrix.getNumberColumns());
        rozmerSloupce.setBackground(Color.cyan);
        rozmerSloupce.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                String s = ((JTextField) e.getSource()).getText();
                try {
                    constant = Integer.parseInt(s);
                    checkNrColumns(constant);
                } catch (Exception ex) {
                    rozmerSloupce.setText("počet sloupců matice="+matrix.getNumberColumns());
                }
            }

            public void focusGained(FocusEvent e) {
                rozmerSloupce.setText("");
            }
        });
        rozmerRadky = new JTextField("počet řádků matice="+matrix.getNumberRows());
        rozmerRadky.setBackground(Color.cyan);


        rozmerRadky.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                String s = ((JTextField) e.getSource()).getText();
                try {
                    constant = Integer.parseInt(s);
                    checkNrRows(constant);
                } catch (Exception ex) {
                    rozmerRadky.setText("počet řádků matice="+matrix.getNumberRows());
                }
            }

            public void focusGained(FocusEvent e) {
                rozmerRadky.setText("");
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx =0;
        c.gridy = 1;
        cp.add(rozmerRadky, c);
        c.gridy = 2;
        cp.add(rozmerSloupce, c);
        

    }

    public Matrix getMatrix() {
        return matrix;
    }

    public MWin getInstance() {
        return this;
    }

    public void checkNrColumns(int i) {
          while(matrix.getNumberColumns()>i){
            matrix.removeCollumn();
          }
          while(matrix.getNumberColumns()<i){
            matrix.addColumn();
          }
          insertMatrix(matrix);

    }

    public void checkNrRows(int i) {
     while(matrix.getNumberRows()>i){
            matrix.removeRow();
          }
          while(matrix.getNumberRows()<i){
            matrix.addRow();
          }
          insertMatrix(matrix);
    }

}