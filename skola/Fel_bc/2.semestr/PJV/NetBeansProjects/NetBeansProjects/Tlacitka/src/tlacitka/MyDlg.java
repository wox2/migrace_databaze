/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tlacitka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author já
 */
public class MyDlg extends JDialog implements ActionListener {
    private JLabel lab = new JLabel("text");
    private JButton but = new JButton("Konec");
    private JButton but2=new JButton("super");
public MyDlg() {
        super();
        setSize(200, 200);
        setLocation(100, 100);
        setResizable(true);
        setModal(true);
        setTitle("První GUI");

        but.setSize(80, 30);
        but.setLocation(60, 85);


        getContentPane().setLayout(null);
        getContentPane().add(but);

        but.addActionListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
 public void actionPerformed(ActionEvent e) {
        dispose();
    }
    @Override
     public void paint(Graphics g) {
        Dimension d = getSize();

        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(0, 0, d.width, d.height, true);

        g.setColor(Color.BLACK);
        String s = "dfdsfds";            // odněkud získáme řetězec
        Rectangle2D r = g.getFontMetrics().getStringBounds(s, g);
        g.drawString(s, (int) ((d.width - r.getWidth()) / 2),
                (int) ((d.height - r.getHeight()) / 2));
    }


}
