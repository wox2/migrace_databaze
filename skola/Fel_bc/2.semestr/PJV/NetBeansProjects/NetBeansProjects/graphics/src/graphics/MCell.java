/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graphics;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.text.TextAction;

/**
 *
 * @author já
 */
public class MCell extends JTextField {
    private int [] position;
    private double value;
    public MCell(int x, int y) {
        super(6);
        position=new int[]{x,y};
        addActionListener(new TextAction(getText()) {

            public void actionPerformed(ActionEvent e) {
                try {value=Double.parseDouble(getText()) ;

                System.out.println(value);
                } catch( NumberFormatException exception){ }
                }
        });
    }
    public void getValue(){
           System.out.println(value);

    }
}
