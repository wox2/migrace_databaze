/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Core.Matrix;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author já
 */
public class ResultWin extends JFrame {

   private static ResultWin result = new ResultWin();
    private MCell[][] cells;

    public ResultWin() {
        setTitle("Výsledek");
        setSize(500, 500);
        setLocation(200, 200);

        setLayout(new GridBagLayout());
        setResizable(true);

        JMenu matice = new JMenu("Soubor");

        JMenuItem save = new JMenuItem("Ulozit", KeyEvent.VK_S);
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        JMenuItem saveXML = new JMenuItem("Ulozit v formatu XML", KeyEvent.VK_X);
        saveXML.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        matice.add(save);
        matice.add(saveXML);

        JMenuBar mb = new JMenuBar();
        mb.add(matice);
        setJMenuBar(mb);
    }

    public void insertMatrix(Matrix matrix) {
        cells = new MCell[matrix.getNumberRows()][matrix.getNumberColumns()];
        GridBagConstraints c = new GridBagConstraints();
        Container cp = getContentPane();
        cp.removeAll();
        for (int i = 0; i < matrix.getNumberRows(); i++) {
            for (int j = 0; j < matrix.getNumberColumns(); j++) {
                cells[i][j] = new MCell(i, j);
                cells[i][j].setText(matrix.getField(i, j) + "");
                cells[i][j].setEditable(false);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                c.gridx = j;
                c.gridy = i;
                cp.add(cells[i][j], c);
            }
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = matrix.getNumberColumns() + 1;
        c.gridy = matrix.getNumberRows();
    }

    public static ResultWin getResult() {
        return result;
    }

    public Matrix getMatrix() {
        Matrix matrix = new Matrix(cells.length, cells[0].length);
        for (int i=0;i<cells.length;i++)  {
            for (int j=0;j<cells[0].length;j++)  {
                matrix.setField(i, j, Float.parseFloat(cells[i][j].getText()));
            }
        }
        return matrix;
    }
}