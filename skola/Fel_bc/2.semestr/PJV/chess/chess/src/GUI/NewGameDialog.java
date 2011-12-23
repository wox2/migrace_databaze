/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author sprinkler
 */
public class NewGameDialog extends JDialog implements ActionListener {

    private GUIGate gate;

    public NewGameDialog(GUIGate gate) {
        this.gate = gate;
        setTitle("Zaèít novou hru");
        setSize(200, 150);
        setLocation((500 - this.getWidth()) / 2, (500 - this.getHeight()) / 2);
        setVisible(true);
        setLayout(null);
        add(description());
        add(blackButton());
        add(whiteButton());
        repaint();
    }

    private JTextArea description() {
        JTextArea description = new JTextArea();
        description.setLineWrap(true);
        description.setEditable(false);
        description.setBackground(null);
        description.setWrapStyleWord(true);
        description.setSize(175, 40);
        description.setLocation(10, 10);
        description.setText("Vyberte si barvu");
        return description;
    }

    private JButton blackButton() {
        JButton blackButton = new JButton();
        blackButton.setSize(50, 50);
        blackButton.setBackground(null);
        blackButton.setLocation(30, 50);
        blackButton.setIcon(getImage("black"));
        blackButton.setActionCommand("black");
        blackButton.addActionListener(this);
        return blackButton;
    }

    private JButton whiteButton() {
        JButton whiteButton = new JButton();
        whiteButton.setSize(50, 50);
        whiteButton.setBackground(null);
        whiteButton.setLocation(110, 50);
        whiteButton.setIcon(getImage("white"));
        whiteButton.setActionCommand("white");
        whiteButton.addActionListener(this);
        return whiteButton;
    }

    private ImageIcon getImage(String color) {
        return new ImageIcon("image\\" + color + "King.gif");
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        gate.startNewGame(e.getActionCommand());
    }
}
