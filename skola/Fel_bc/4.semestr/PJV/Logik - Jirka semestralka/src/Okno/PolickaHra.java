//
//package Okno;
//
//import java.awt.Color;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//public class PolickaHra {
//    JPanel panel2 = new JPanel();
//    JButton[][] policka = new JButton[4][10];
//    Color c;
//    
//    
//    PolickaHra(){
//        try { nandejHraciPolicka();}
//        catch(Exception e){
//        e.printStackTrace();}
//    }
//    
//    public JPanel nandejHraciPolicka(){
//            for(int i = 0; i < 4; i++) 
//            for(int j = 0; j < 10; j++){
//               final JButton b = new JButton();
//               
//               b.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        NabidkaBarev nabBar = new NabidkaBarev();
//                       // nanBarvy.obarvy(); 
//                       // nastav(); 
//                        b.setBackground(nabBar.getColor());
//                    }
//               }); 
//               
//               policka[i][j] = b;
//               policka[i][j].setLayout(null);
//               policka[i][j].setBounds(new Rectangle(40 + i*30, 20+j*30,20 ,20 ));
//               panel2.add(b);
//            }
//        return panel2;
//    }
//    
////    public void setColor(int i) {
////        Barvy barvy = new Barvy();
////        c = barvy.getColor(i);             
////        }
////    public Color getColor(){
////        
////        return c;
////    }
////            private Color getColor(ActionEvent e){
////                NandejBarvy naBarev = new NandejBarvy();
////              //  naBarev.nandejFci();  
////            return naBarev.getColor(e);  
////        }
//            
//}
