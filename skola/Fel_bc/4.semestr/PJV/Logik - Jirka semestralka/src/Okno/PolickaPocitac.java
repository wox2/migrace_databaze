
package Okno;

import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;
 
public class PolickaPocitac {
    
    JPanel panel5 = new JPanel();
    public static JButton[] policka4 = new JButton[4];
    public Color[] bar = new Color[4];
    public static int[] c = new int[4];
    static public int k;
    static public int k2;
    
    
    public JPanel polickaProPocitac(){
            Barvy barvy = new Barvy(); 
            for(int j = 0; j < 4; j++){
               k = (int)(Math.random()*10); 
               System.out.println(k);
               JButton b = new JButton();               
               policka4[j] = b;
               policka4[j].setLayout(null);
               policka4[j].setBounds(new Rectangle( 40+j*30,25 ,20 ,20 ));               
               policka4[j].setBackground(barvy.getColor(k));    
               System.out.println(barvy.getColor(k));               
               panel5.add(b);
               
               setCisla(k);
               c[j] = k2;
            }    
          return panel5;
        }
        
    private void setCisla(int l){  
        k2 = l;
    }
    
    public int[] getCisla(){
        return c;
    }
}
