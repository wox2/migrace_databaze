/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author já
 */
public class CheckBoxy extends JFrame {

    private JCheckBox c2;
    private JCheckBox c1;
    private JLabel l1,  l2;

    public CheckBoxy(int vyska, int sirka) {
        this.setSize(vyska, sirka);
        this.setTitle("Checkboxy");
        c1 = new JCheckBox("První");
        c2 = new JCheckBox("Druhy");
        l1 = new JLabel(c1.getText());
        l2 = new JLabel(c2.getText());
        this.getContentPane().add(c1);
        this.getContentPane().add(c2);
        this.getContentPane().add(l1);
        this.getContentPane().add(l2);
        this.setLayout(new FlowLayout());
        c1.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                l1Changed(e);
            }
        });
        c2.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                l2Changed(e);
            }
        });
    }

    public void l1Changed(ItemEvent e) {
        if (c1.isSelected()) {
            l1.setText("Zaškrtnuto");
        } else {
            l1.setText("Odškrtnuto");
        }
    }

    public void l2Changed(ItemEvent e) {
        if (c2.isSelected()) {
            l2.setText("Zaškrtnuto");
        } else {
            l2.setText("Odškrtnuto");
        }
    }
}
