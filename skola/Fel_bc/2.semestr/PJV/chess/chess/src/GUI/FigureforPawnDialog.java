/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author sprinkler
 */
public class FigureforPawnDialog extends JDialog implements ActionListener{

    GridLayout layout = new GridLayout();
    private String color;
        private GUIGate gate;

    public FigureforPawnDialog(String color, GUIGate gate) {
        this.color = color;
        this.gate = gate;
        setTitle("Zmìnit figurku");
        setSize(200, 100);
        setLocation((500 - this.getWidth()) / 2, (500 - this.getHeight()) / 2);
        setVisible(true);
        layout.setColumns(4);
        setLayout(layout);
        add(figureButton("Queen"));
        add(figureButton("Rook"));
        add(figureButton("Bishop"));
        add(figureButton("Knight"));
    }

    private JButton figureButton(String figureName){
        JButton button = new JButton();
        System.out.println("image\\" + color + figureName +".gif");
        button.setIcon(getImage(figureName));
        button.setActionCommand(figureName);
        button.addActionListener(this);
        return button;
    }

     private ImageIcon getImage(String figureName) {
        return new ImageIcon("image\\" + color + figureName +".gif");
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        gate.changeFigureForPawn(e.getActionCommand());
    }


}
