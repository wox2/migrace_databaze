/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CORE.DataException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author sprinkler
 */
public class ChessSquare extends JButton implements ActionListener {

    private int[] position;
    private GUIGate gate;

    public ChessSquare(int x, int y, GUIGate gate) {
        this.gate = gate;
        position = new int[]{x, y};
        setBackground();
        setSize(50, 50);
        setLocation(20 + (x * 50), 20 + (y * 50));
        addActionListener(this);
    }

    public void setBackground() {
        if (((position[0] % 2 == 0) && (position[1] % 2 == 0)) || ((position[0] % 2 == 1) && (position[1] % 2 == 1))) {
            setBackground(new Color(204, 153, 0));
        } else {
            setBackground(new Color(255, 204, 102));
        }
    }

    public void setImage() {
        try {
            if (gate.getData().get(position[0], position[1]) != null) {
                ImageIcon image = new ImageIcon(gate.getData().get(position[0], position[1]).getImage());
                setIcon(image);
            } else {
                setIcon(null);
            }
        } catch (DataException ex) {
            System.out.println(ex);
        }
    }

    public void setSelectedSquareBackground() {
        setBackground(Color.red);
    }

    public void actionPerformed(ActionEvent e) {
        gate.setMove(position);
        gate.repaintChessboard();
    }
}
