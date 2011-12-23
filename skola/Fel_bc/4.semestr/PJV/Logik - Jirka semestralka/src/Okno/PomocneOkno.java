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

abstract class PomocneOkno extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    JButton tlacitkoVlevo = new JButton();
    JButton tlacitkoVpravo = new JButton();
    int zarovnani;
    
    
    Label l1 = new Label();
    String text = "Doba hrani";
    Label l2 = new Label();
    String text2 = "Nejlepsi cas";
    Label l3 = new Label();
    String text3 = "Odehrane hry"; 
    Label l4 = new Label();
    String text4 = "Vyhrane hry";
    
    
    Label vysledky1 = new Label();
    String dobaHrani;
    Label vysledky2 = new Label();
    String  nejCas;
    Label v = new Label();
    String vitezPoraz;
    Label vysledky3 = new Label();
    String odehraneHry;
    Label vysledky4 = new Label();
    String vyhraneHry;
    
    public PomocneOkno(String dobaHrani, String vitezPoraz, String nejCas,String odehraneHry,String vyhraneHry, int zarovnani) {
        this.odehraneHry = odehraneHry;
        this.nejCas = nejCas;
        this.zarovnani = zarovnani;
        this.dobaHrani = dobaHrani;
        this.vitezPoraz = vitezPoraz;
        this.vyhraneHry = vyhraneHry;
        tlacitkoVlevo = new JButton("Nova hra");
        tlacitkoVpravo = new JButton("Konec");
        nastaveni();       
    }
    
    //pro statistiku
    public PomocneOkno(  String vynuluj, String zavri, int zarovnani){
        //String nejCas, String odehraneHry, String vyhrane, String uspesnost,
     //   this.odehraneHry = odehraneHry;
      //  this.nejCas = nejCas;
        this.zarovnani = zarovnani;               
        tlacitkoVpravo = new JButton(zavri);
        tlacitkoVlevo = new JButton(vynuluj);
        nastaveni();
    }
    

    
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
    panel2.add(tlacitkoVlevo);
    panel2.add(tlacitkoVpravo);                           
    tlacitkoVlevo.setBounds(new Rectangle(50, 25, 100,30));    
    tlacitkoVpravo.setBounds(new Rectangle(240, 25, 140, 30));

    
    panel1.setLayout(null);
    panel1.add(l1);
    panel1.add(l2);
    panel1.add(l3);
    panel1.add(l4);
    
    l1.setText(text);
    l2.setText(text2);
    l3.setText(text3);
    l4.setText(text4);
    
    l1.setBounds(new Rectangle(20, 60, 90,30));
    l2.setBounds(new Rectangle(20, 90, 90,30));
    l3.setBounds(new Rectangle(20, 120, 90,30));
    l4.setBounds(new Rectangle(20, 150, 90,30));
    
    panel1.add(v);
    v.setText(vitezPoraz);
    v.setBounds(new Rectangle(zarovnani, 20, 250,30));
    
    panel1.add(vysledky1);
    vysledky1.setText(dobaHrani);
    vysledky1.setBounds(new Rectangle(110, 60, 80,30));
    
    panel1.add(vysledky2);
    vysledky2.setText(nejCas);
    vysledky2.setBounds(new Rectangle(110, 90, 80,30));
    
    panel1.add(vysledky3);
    vysledky3.setText(odehraneHry);
    vysledky3.setBounds(new Rectangle(110, 120, 80,30));

    panel1.add(vysledky4);
    vysledky4.setText(vyhraneHry);   
    vysledky4.setBounds(new Rectangle(110, 150, 80,30));    
    setVisible(true);
    
    }
    abstract void donastaveni();
    abstract void tlacitkoVlevo();
    abstract void tlacitkoVpravo();
    
}
