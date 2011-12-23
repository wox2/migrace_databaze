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
//public class NandejBarvy {
//    JPanel panel3 = new JPanel();
//    JPanel panel2 = new JPanel();
//    JButton[][] policka2 = new JButton[1][7];
//    JButton[][] policka = new JButton[4][10];    
//    Color c;
//
//    
//    NandejBarvy(){
//        try { nandejHraciPolicka();
//            polickaNaVyber();
//        }
//        catch(Exception e){
//        e.printStackTrace();} 
//    }
//    
//        public JPanel nandejHraciPolicka(){
//            for(int i = 0; i < 4; i++)
//            for(int j = 0; j < 10; j++){
//            final JButton b = new JButton();
//            b.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    b.setBackground(getColor());
//                }
//            } );
//               
//               policka[i][j] = b;
//               policka[i][j].setLayout(null);
//               policka[i][j].setBounds(new Rectangle(40 + i*30, 20+j*30,20 ,20 ));
//               panel2.add(b);
//              
//            }
//        return panel2;
//    } 
//        private Color getColor(){
//            return c;
//        }
//    
//    private void setColor(int i) {
//        Barvy barvy = new Barvy(); 
//        c = barvy.getColor(i);      
//    }    
//        
//    public JPanel polickaNaVyber(){
//            Barvy barvy = new Barvy();
//            for(int j = 0; j < 7; j++){
//               JButton b = new JButton();               
//               policka2[0][j] = b;
//               policka2[0][j].setLayout(null);
//               policka2[0][j].setBounds(new Rectangle( 80+j*30,25 ,20 ,20 ));               
//               policka2[0][j].setBackground(barvy.getColor(j+1));
//               panel3.add(b); 
//            }                      
//            
//             policka2[0][0].addActionListener(new ActionListener(){
//                public void actionPerformed(ActionEvent a) {
//                    setColor(1);
//                    System.out.println("ahoj");
//                }
//            });
//               policka2[0][1].addActionListener(new ActionListener(){
//
//                public void actionPerformed(ActionEvent c) {
//                    setColor(2); 
//                    System.out.println("cerna"); 
//                }
//            });
//               policka2[0][2].addActionListener(new ActionListener(){
//                public void actionPerformed(ActionEvent a) {
//                    setColor(3);
//                }
//            });
//               policka2[0][3].addActionListener(new ActionListener(){
//
//                public void actionPerformed(ActionEvent c) {
//                    setColor(4);
//                }
//            });
//            policka2[0][4].addActionListener(new ActionListener(){
//                public void actionPerformed(ActionEvent a) {
//                    setColor(5);
//                }
//            });
//               policka2[0][5].addActionListener(new ActionListener(){
//
//                public void actionPerformed(ActionEvent c) {
//                    setColor(6);
//                }
//            });
//                policka2[0][6].addActionListener(new ActionListener(){
//
//                public void actionPerformed(ActionEvent c) {
//                    setColor(7);
//                }
//            });
//        return panel3;        
//   
//    }
//    
////    public void setColor(int i) {
////        Barvy barvy = new Barvy();
////        c = barvy.getColor(i);            
////    }
////    public Color getColor(){
////        return cc;  
////    }
//}
