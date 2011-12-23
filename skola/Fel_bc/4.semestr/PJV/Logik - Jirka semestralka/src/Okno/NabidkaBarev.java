//
//package Okno;
//
//import java.awt.Color;
//import java.awt.Rectangle;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Date;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//
//
//public class NabidkaBarev{
//    JPanel panel3 = new JPanel();
//    JButton[][] policka2 = new JButton[1][7];
//    Color c;
//    static long casZacatek = 0;
//    Date d ;
// 
//       
//    NabidkaBarev(){
//        try { polickaNaVyber();
//        casZacatek = 0;
//        }
//        catch(Exception e){
//        e.printStackTrace();} 
//    }
//    
//    public JPanel polickaNaVyber(){
//            Barvy barvy = new Barvy();
//            for(int j = 0; j < 7; j++){
//               JButton b = new JButton();               
//               policka2[0][j] = b;
//               policka2[0][j].setLayout(null);
//               policka2[0][j].setBounds(new Rectangle( 70+j*35,25 ,25 ,25 ));               
//               policka2[0][j].setBackground(barvy.getColor(j+1));
//               panel3.add(b); 
//            }            
//            for(int i = 0; i<7; i++){
//                priradAkci(i);
//            }                         
//        return panel3;        
//   
//    }
//    private void priradAkci(final int a){
//            policka2[0][a].addActionListener(new ActionListener() {                
//                public void actionPerformed(ActionEvent e) {
//                    setColor(a+1);
//                    setCasZacatek();
//                    //casZacatek = d.getTime();
//                    System.out.println("cas zacatek " + casZacatek + "  "+ d.toString());
//                }
//            });
//         }
//    
//        private void setColor(int i) {
//        Barvy barvy = new Barvy();
//        c = barvy.getColor(i);            
//        }
//    public Color getColor(){
//        return c; 
//    }
//    public void setCasZacatek(){
//        casZacatek = d.getTime();
//    }
//    public long getCasZacatek(){
//        return casZacatek;
//    }
//    
////    private void setBarva(int a){
////            Barvy ba = new Barvy();
////            c = ba.getColor(a);
////            int b = ba.f+1;
////            barvyNasazene[co++] =  b;
////           // co = ba.f+1;                     
////    }
//  
//       
////    public void setBarva1(ActionEvent e) {
////                    c = new Color(255,0,0);
//////                    policka[3][2].addActionListener(this);
//////                    policka[1][1].setBackground(new Color(0,0,0));
////                   
////                }
////    public void setBarva2(ActionEvent e) {
////                    c = new Color(0,0,0);
//////                    policka[3][2].addActionListener(this);
//////                    policka[1][1].setBackground(new Color(0,0,0));
////                   
////                }
//}
