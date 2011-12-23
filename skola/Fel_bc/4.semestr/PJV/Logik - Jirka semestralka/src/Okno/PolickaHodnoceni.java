package Okno;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PolickaHodnoceni {
    JPanel panel4 = new JPanel();
    public JButton[][] policka = new JButton[4][10];

    PolickaHodnoceni(){
        try { nandejPolickaHodnoceni();}
        catch(Exception e){
        e.printStackTrace();}
    }
    
   public JPanel nandejPolickaHodnoceni(){ 
            for(int i = 0; i < 4; i++)
            for(int j = 0; j < 10; j++){
               JButton b = new JButton();
               policka[i][j] = b;
               policka[i][j].setLayout(null);
               policka[i][j].setBounds(new Rectangle(50 + i*30, 20+j*30,20 ,20 ));
               policka[i][j].setBackground(new Color(133,133,133));
               panel4.add(b);
            } 
 
        return panel4;
    }
        
}
