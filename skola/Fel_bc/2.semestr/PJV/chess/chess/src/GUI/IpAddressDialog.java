/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

/**
 *
 * @author sprinkler
 */
public class IpAddressDialog extends JDialog implements ActionListener {

    private IpAddressPanel ipAddressPanel = new IpAddressPanel(10, 50);
        private GUIGate gate;

    public IpAddressDialog(GUIGate gate) {
        this.gate = gate;
        setTitle("Pøipojit se ke høe");
        setSize(200, 150);
        setLocation((500 - this.getWidth()) / 2, (500 - this.getHeight()) / 2);
        setVisible(true);
        setLayout(null);
        add(ipAddressPanel);
        add(description());
        add(send());
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
        description.setText("Zadejte ip adresu poèítaèe, kde je vytvoøena hra");
        return description;
    }

    private JButton send() {
        JButton send = new JButton();
        send.setSize(100, 20);
        send.setLocation(45, 80);
        send.setText("Pøipojit se");
        send.addActionListener(this);
        return send;
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        gate.joinGame(ipAddressPanel.getText());
    }
}
