/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author sprinkler
 */
public class Window extends JFrame {

    private JPanel center = new JPanel();
    private BorderLayout layout = new BorderLayout();
    GUIGate gate;

    public Window(GUIGate gate) {
        this.gate = gate;
        setName("window");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        Menu menu = new Menu(gate);
        center.setLayout(new GridLayout());
        getContentPane().add(menu, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        //setResizable(false);
        setTitle("Šachy");
        setVisible(true);
    }

    public void addCenter(Component component){
       center.add(component);
       center.repaint();
       repaint();
    }

    public void removeCenter(){
        center.removeAll();
        center.repaint();
        repaint();
    }
}
