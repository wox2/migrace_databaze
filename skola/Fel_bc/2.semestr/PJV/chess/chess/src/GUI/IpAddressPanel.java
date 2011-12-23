/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author sprinkler
 */
public class IpAddressPanel extends JPanel {

    JTextField[] ipAddress = new JTextField[4];

    public IpAddressPanel(int x, int y) {
        setSize(180, 20);
        setLocation(x, y);
        setLayout(null);
        createIpTextField();
    }

    private void createIpTextField() {
        for (int i = 0; i < 4; i++) {
            ipAddress[i] = textField(i);
            add(ipAddress[i]);
            if (i != 0) {
                add(dot(i));
            }
        }
    }

    private JTextField textField(int i) {
        JTextField text = new JTextField();
        text.setSize(40, 20);
        text.setLocation((45 * i), 0);
        return text;
    }

    public String getText() {
        StringBuffer text = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            text.append(ipAddress[i].getText());
            if (i != 3) {
                text.append(".");
            }
        }
        return text.toString();
    }

    private JLabel dot(int i) {
        JLabel dot = new JLabel();
        dot.setText(".");
        dot.setSize(6, 20);
        dot.setLocation((45 * i) - 4, 2);
        return dot;
    }
}
