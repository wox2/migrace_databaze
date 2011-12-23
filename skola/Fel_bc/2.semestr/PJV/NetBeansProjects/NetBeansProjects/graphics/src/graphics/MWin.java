/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author já
 */
public class MWin extends JFrame{

    private MCell[][] cells;

    public MWin(int x, int y, int z, int w) {
        setSize(420, 420);
        setLocation(x, y);
        setLayout(new GridBagLayout());
        cells = new MCell[z][w];
        fill(z,w);
        setVisible(true);
    }

    private void fill(int z,int y) {
        for (int i = 0; i < z; i++) {
            for (int j = 0; j <y ; j++) {
                cells[i][j] = new MCell(i, j);
                add(cells[i][j]);
            }
        }
    }



    



}