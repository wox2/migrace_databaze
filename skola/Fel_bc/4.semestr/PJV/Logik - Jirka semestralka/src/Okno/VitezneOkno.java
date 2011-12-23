/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

 
import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author mary
 */

class VitezneOkno extends PomocneOkno {
//    JPanel panel1 = new JPanel();
//    JPanel panel2 = new JPanel();
//    JButton tlacitko = new JButton("Nova hra");
//    JButton tlacitko2 = new JButton("Konec");
//    int zarovnani;    
//    
//    
//    Label la = new Label();
//    String text = "Doba hrani";
//    Label la2 = new Label();
//    String text2 = "Odehrane hry";
//    Label la3 = new Label();
//    String text3 = "Vyhrane hry";
//    Label la4 = new Label();
//    String text4 = "Nejlepsi cas";
//    
//    Label vysledky = new Label();
//    String dobaHrani;
//    Label vysledky2 = new Label();
//    String vitezPoraz;
//    Label vysledky3 = new Label();
//    String nejCas;
//    Label vysledky4 = new Label();
//    String odehraneHry;
    
    public VitezneOkno(String dobaHrani, String vitezPoraz, String nejCas,String odehraneHry,String vyhraneHry, int zarovnani) {
            super(dobaHrani, vitezPoraz, nejCas, odehraneHry,vyhraneHry, zarovnani);
 //           tlacitko = new JButton("Nova hra");
//            tlacitko2 = new JButton("Konec");
//        this.odehraneHry = odehraneHry;
//        this.nejCas = nejCas;
//        this.zarovnani = zarovnani;
//        this.dobaHrani = dobaHrani;
//        this.vitezPoraz = vitezPoraz;
//        nastaveni();
        tlacitkoVlevo();
        tlacitkoVpravo();
    }

    @Override
    void tlacitkoVlevo() {
    tlacitkoVlevo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               // Okenko o = new Okenko();
            }        
        });         
    }

    @Override
    void tlacitkoVpravo() {
            tlacitkoVpravo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    void donastaveni() {
        
    }
    /*
    public void nastaveni(){
    
    setTitle("okno");
    setBounds(500,250,415,323);
    
    getContentPane().setLayout(null);
    getContentPane().add(panel1); 
    panel1.setBounds(new Rectangle(0, 0, 400, 200));
    panel1.setBackground(Color.white);
    getContentPane().add(panel2);
    panel2.setBounds(new Rectangle(0, 200, 400, 100));
    panel2.setBackground(new Color(90,210,250));    
    
    panel2.setLayout(null); 
    panel2.add(tlacitko);
    panel2.add(tlacitko2);                           
    tlacitko.setBounds(new Rectangle(50, 25, 100,30));
    tlacitko.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               // Okenko o = new Okenko();
            }
        });
    
    tlacitko2.setBounds(new Rectangle(250, 25, 140, 30));
    tlacitko2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    
    panel1.setLayout(null);
    panel1.add(la);
    panel1.add(la2);
    panel1.add(la3);
    panel1.add(la4);
    
    la.setText(text);
    la2.setText(text2);
    la3.setText(text3);
    la4.setText(text4);
    
    la.setBounds(new Rectangle(20, 60, 90,30));
    la2.setBounds(new Rectangle(20, 90, 90,30));
    la3.setBounds(new Rectangle(20, 120, 90,30));
    la4.setBounds(new Rectangle(20, 150, 90,30));
    
    panel1.add(vysledky);
    vysledky.setText(dobaHrani);
    vysledky.setBounds(new Rectangle(110, 60, 80,30));
    
    panel1.add(vysledky2);
    vysledky2.setText(vitezPoraz);
    vysledky2.setBounds(new Rectangle(zarovnani, 20, 250,30));
    
    panel1.add(vysledky3);
    vysledky3.setText(nejCas);
    vysledky3.setBounds(new Rectangle(110, 150, 80,30));

    panel1.add(vysledky4);
    vysledky4.setText(odehraneHry);
    vysledky4.setBounds(new Rectangle(110, 90, 80,30));    
    setVisible(true);
    
    }
    
    */
}