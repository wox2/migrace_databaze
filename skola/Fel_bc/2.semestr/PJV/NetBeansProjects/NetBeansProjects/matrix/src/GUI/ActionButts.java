/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import NavrhovyVzorState.AMinusB;
import NavrhovyVzorState.AMultipleB;
import NavrhovyVzorState.AMultipleConstant;
import NavrhovyVzorState.APlusB;
import NavrhovyVzorState.ATranspose;
import NavrhovyVzorState.BMinusA;
import NavrhovyVzorState.BMultipleA;
import NavrhovyVzorState.BMultipleConstant;
import NavrhovyVzorState.BTranspose;
import NavrhovyVzorState.Choice;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author já
 */
public class ActionButts extends Container {

    private Win window;
    private Choice chosen = new Choice();
    private float constant = 0.0F;
    JTextField tf = new JTextField();

    public ActionButts(Win w) {
        ButtonGroup group = new ButtonGroup();
        window = w;
        setLayout(new FlowLayout());
        JRadioButton plus = new JRadioButton("   A+B   ");
        plus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new APlusB());
            }
        });
        plus.setSelected(true);

        JRadioButton subtract = new JRadioButton("   A-B   ");
        subtract.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new AMinusB());
            }
        });

        JRadioButton subtract2 = new JRadioButton("   B-A   ");
        subtract2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new BMinusA());
            }
        });

        JRadioButton multiple = new JRadioButton("   A*B   ");
        multiple.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new AMultipleB());
            }
        });

        JRadioButton multiple2 = new JRadioButton("   B*A   ");
        multiple2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new BMultipleA());
            }
        });

        JRadioButton transpose = new JRadioButton("transpose A");
        transpose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new ATranspose());
            }
        });

        JRadioButton transpose2 = new JRadioButton("transpose B");
        transpose2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new BTranspose());
            }
        });

        JRadioButton multipleByConst = new JRadioButton("c*A (násobení konstantou)");
        multipleByConst.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new AMultipleConstant());
            }
        });

        JRadioButton multipleByConst2 = new JRadioButton("c*B (násobení konstantou)");
        multipleByConst2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                chosen.setChosenCommand(new BMultipleConstant());
            }
        });

        tf.setText("constanta k násobení matice=0.0");
        tf.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                String s = ((JTextField) e.getSource()).getText();
                try {
                    constant = Float.parseFloat(s);
                } catch (Exception ex) {
                    JOptionPane.showConfirmDialog(window, "Musíte zadat číslo");
                    tf.setText("constanta k násobení matice=0.0");
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                tf.setText("");
            }
        });
        tf.setPreferredSize(new Dimension(200, 25));
        add(tf);

        add(plus);
        add(subtract);
        add(subtract2);
        add(multiple);
        add(multiple2);
        add(transpose);
        add(transpose2);
        add(multipleByConst);
        add(multipleByConst2);

        group.add(plus);
        group.add(subtract);
        group.add(subtract2);
        group.add(multiple);
        group.add(multiple2);
        group.add(transpose);
        group.add(transpose2);
        group.add(multipleByConst);
        group.add(multipleByConst2);

        JButton equal = new JButton("=");
        equal.setPreferredSize(new Dimension(90, 25));
        add(equal);
        equal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                chosen.count(window.getMatrixA(), window.getMatrixB(), constant, window.getResults());
            }
        });
    }
}
