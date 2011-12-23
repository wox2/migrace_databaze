/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sachy2;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Administrator
 */
public class Main {


    /**
     * @param args the command line arguments
     */

        public static void main(String[] args) {
        JFrame okno = new JFrame();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        okno.setLayout(new FlowLayout());
        Dimension velikostOkna = new Dimension();
        velikostOkna.height = 320;
        velikostOkna.width = 320;
        okno.setSize(velikostOkna);
        for (int i=0; i<65;i++)
        {
            okno.getContentPane().add(tlacitka(i));        
        }
        okno.setVisible(true);
    }
    
    static JPanel tlacitka (int i)
    {
        JPanel tlacitko=new JPanel();
        tlacitko.setLocation((i * 21), 20);
        tlacitko.setBackground(Color.BLACK);
        
        return tlacitko;
    }

}
