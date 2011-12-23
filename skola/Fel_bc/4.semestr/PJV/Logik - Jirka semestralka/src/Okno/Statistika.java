/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mary
 */
 class Statistika extends PomocneOkno { 
    
    public Statistika(String nejCas, String odehrane, String vyhrane, String uspesnost, String vynuluj, String zavri, int g ){
        super(vynuluj,zavri,g );
        //nejCas,odehrane,vyhrane,uspesnost,
        vysledky1.setText(nejCas);
        vysledky2.setText(odehrane);
        vysledky3.setText(vyhrane);    
        vysledky4.setText(uspesnost);        
        donastaveni();
        tlacitkoVpravo();
        tlacitkoVlevo();
    }
    
    @Override
    public void tlacitkoVpravo(){
        tlacitkoVpravo.addActionListener(new ActionListener() {
  
            public void actionPerformed(ActionEvent e) {
            //    setDefaultCloseOperation(HIDE_ON_CLOSE);
            }
        });
        
    } 
    @Override
    public void tlacitkoVlevo(){
        tlacitkoVlevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Ahojky");
                    FileWriter fw = new FileWriter("hodnoceni.txt");
                    fw.write(1000+" " + 0 + " "+ 0);                
                    fw.close();
                    donastaveni();
                    repaint();
                } catch (IOException ex) {
                    Logger.getLogger(Statistika.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }   
        });
    }

    @Override
    void donastaveni() {
         v.setText("Statistika");
         l1.setText(text2);
         l2.setText(text3);
         l3.setText(text4);
         l4.setText("Uspestnost ");
         
                 
    }
    
}
