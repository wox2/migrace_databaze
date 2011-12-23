
package Okno;

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;   
import logikhra.*;

public class Okenko extends JFrame implements Serializable {
 //   JComboBox kombo = new JComboBox();
    JButton tlacitko = new JButton("Ohodnoť");
    JButton tlacitko2 = new JButton("Napoveda");
    JPanel panel1 = new JPanel(); 
    
    Stopky s = new Stopky("Stopky");

    Label la = new Label();
    String text = "Doba hrani";
    JTextArea la2 = new JTextArea();
    String text2 = "cas hrani"; 
   
    Color c;
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JButton[][] policka = new JButton[4][10];
    JButton[][] policka2 = new JButton[1][7];    
    JButton[][] policka3 = new JButton[4][10];

    int poz=0;  
    int jej;
    public Color[] col = new Color[4];
    int co= 0;
    int[] barvyNasazene = new int[4];
    PolickaPocitac poPo;
    static int[] poc = new int[4];      
    Date d ;
    Cas cas = new Cas();
    public boolean ukazPolicka = false;
    private boolean spust = false;
    
    //indexy
    private int bi = 3;
    private int iUkazPolicka = 1;
      
 
    public Okenko(int jej){
        this.jej = jej; 
        try { parametry();  
        setVisible(true);  
        }  
        catch(Exception e){
        e.printStackTrace();}
    }   
    public Okenko(){   
             
    }
        
    @SuppressWarnings("static-access")
    public void parametry() {        
           
        Dimension obrazovka = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ro = new Dimension();
        ro.height = obrazovka.height/2+200;
        ro.width = obrazovka.width/2+50;  
        setSize(ro);
        setLocation(ro.width/2 ,ro.height/2-200);                  
        setTitle("Logik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HlavniPanel hlavniPan = new HlavniPanel();   
        setJMenuBar((JMenuBar)hlavniPan.nabydka());     
          
                             
        //ty dve tlacitka v levo nahore 
        getContentPane().setLayout(null);
        getContentPane().add(panel1);    
        panel1.setBackground(new Color(0,255,0));  
        panel1.setBounds(new Rectangle(0, 0, 140, 240));
        panel1.setLayout(null);     
        tlacitko.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) { 
                try {
                    if (jej > 0) {
                        jej--;
                        System.out.println("Pocet kliknuti: " + jej);
                        nandejHraciPolicka().repaint();
                        nandejPolickaHodnoceni().repaint();
                    } else {
                        System.out.println("Uz nemas pole na hrani");
                        jej--;
                        System.out.println("jej " + jej);
                    }
                    poz = 0;
                    ohodnot();  
                      
                    System.out.println("Ohodnotil jsem...");
                    bi = 3;
                    co = 0;
                    for (int i = 0; i < 4; i++) {
                        barvyNasazene[i] = 0;
                    }
     
                    if (jej == -1) {
                        for (int i = 0; i < 4; i++) {
                            if (policka3[i][jej + 1].getBackground() != Color.green) {
                                String porazeny = "Bohuzel jsi prohral, zkus to znovu.";
                                System.out.println("prohral si");
                                Date dd = new Date();
                                Cas.setCasKonec(dd.getTime());
                                ukazPolicka = true;
                                ukazPolicka(4);   
                                Soubor soub = new Soubor();                          
                                    FileWriter f4 = new FileWriter("hodnoceni.txt"); 
                                    f4.write(soub.getCas()+" " + soub.odeHry() + " "+ soub.vyhraneHry());                
                                    f4.close();                               
                                System.out.println(cas.getCasKonec() + " konec " + dd.toString());
                                VitezneOkno o = new VitezneOkno(cas.getSpocitejCas(), porazeny,soub.getCas()+" s" ,soub.odeHry()+"",soub.vyhraneHry() + "",  110);
                                break;
                            }
                        }     
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Okenko.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });
       
        tlacitko2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(iUkazPolicka < 5){
                    ukazPolicka = true; 
                    ukazPolicka(iUkazPolicka);
                    iUkazPolicka++;
                }
            }
        });
            
        
        panel1.add(tlacitko);    
        panel1.add(tlacitko2);     
        panel1.add(la);
        panel1.add(la2);
        la.setText(text);
        
        la.setBounds(new Rectangle(20, 110, 90,30));
        la2.setBounds(new Rectangle(20, 140, 90,30));
        tlacitko.setBounds(new Rectangle(0, 0, 100,50));
        tlacitko2.setBounds(new Rectangle(0, 55, 120, 50));
        
        
//        //nanda policka na hru
//     //   PolickaHra polHra = new PolickaHra();
//        NandejBarvy polHra = new NandejBarvy();
//        getContentPane().add(polHra.panel2);
//        polHra.panel2.setBounds(new Rectangle(150, 60, 200, 350));
//        polHra.panel2.setLayout(null); 
//        polHra.panel2.setBackground(new Color(200,180,182));           
//        polHra.nandejHraciPolicka(); 
         
        //nanda policka na kontrolu 
       // PolickaHodnoceni polickaHodno = new PolickaHodnoceni();         
        //getContentPane().add(polickaHodno.panel4);
//        polickaHodno.panel4.setBounds(new Rectangle(380, 60, 200, 350));
//        polickaHodno.panel4.setLayout(null); 
//        polickaHodno.panel4.setBackground(new Color(105,200,195));   
        
        getContentPane().add(panel4);
        panel4.setBounds(new Rectangle(380, 60, 200, 350));
        panel4.setLayout(null); 
        panel4.setBackground(new Color(185,180,180));        
        nandejPolickaHodnoceni();
        
        //nanda policka pro pocitac    
        poPo = new PolickaPocitac();        
        getContentPane().add(poPo.polickaProPocitac());        
        poPo.panel5.setBounds(new Rectangle(150, 0, 200, 55));
        poPo.panel5.setLayout(null); 
        poPo.panel5.setBackground(new Color(185,180,180));  
        ukazPolicka(0);
       
              
        //this.getContentPane().add((JPanel)polickaHodno.panel);
      //  polickaHodno.panel();
        
                  
        //nanda policka na vyber barvy
//        NabidkaBarev nabidkaBar = new NabidkaBarev();
//       // NandejBarvy nabidkaBar = new NandejBarvy();
//        getContentPane().add(nabidkaBar.panel3);  
//        nabidkaBar.panel3.setBounds(new Rectangle(75, 420, 550, 75));
//        nabidkaBar.panel3.setLayout(null);
//        nabidkaBar.polickaNaVyber().setBackground(new Color(185,180,180)); 
//        this.casZacatek =  nabidkaBar.getCasZacatek();
        
           
        // policka nabidky
        getContentPane().add(panel3); 
        panel3.setBounds(new Rectangle(75, 420, 550, 75));
        panel3.setLayout(null);
        panel3.setBackground(new Color(185,180,180)); 
        polickaNaVyber();
        
        
                //policka hry  
        getContentPane().add(panel2);
        panel2.setBounds(new Rectangle(150, 60, 200, 350));
        panel2.setLayout(null); 
        panel2.setBackground(new Color(185,180,180));        
        nandejHraciPolicka();
        
         
    }  
    
    @SuppressWarnings("static-access")
    public void ukazPolicka(int a){
        for(int i = 0; i<4; i++){
            poPo.policka4[i].setVisible(false); 
        }
        for(int i = 0; i<a; i++){
            if( ukazPolicka == true){
                poPo.policka4[i].setVisible(true);
            }        
        }
    }
          
        public void ohodnot() throws IOException{          
         //  PolickaHodnoceni ph = new PolickaHodnoceni();
     //    for(int i = 0; i<4; i++){  
           poc = poPo.getCisla();      
           System.out.println("co "+barvyNasazene[0] + " " +barvyNasazene[1] + " " +barvyNasazene[2] + " " +barvyNasazene[3]); 
            System.out.println("poc "+poc[0] +" " +poc[1]+" "+poc[2]+" "+poc[3]);
          
            int bb = 0;
            int[] poleIndexu = {-1,-1,-1,-1};
            int aa= 0;
            
           // int b = 3;
            for(int index = 0 ;index <4; index++){
                switch(poc[index]){
                    case 0: poc[index] = 1;
                    case 8: poc[index] = 1;
                    case 9: poc[index] = 1;                     
                }
                if (poc[index] == barvyNasazene[index] ){       
                    System.out.println("stejna barva na pozici " + index);    
                    policka3[aa][jej+1].setBackground(Color.green);
                    aa++;                                      
                }   
                else {
                    poleIndexu[bb]= index;
                    bb++;
                }               
            }   
           
            int kk=0;
            for(int i = 0; i<4; i++){
                System.out.print(" "+ poleIndexu[i]);
                if(poleIndexu[i] >-1){
                   kk++; 
                }
            }
            int[] poc2 = new int[kk];
            System.arraycopy(poleIndexu, 0, poc2, 0, poc2.length);
            System.out.println(" kk "+ kk);
          //  for(int i = 0; i<kk; i++){
         //       System.out.print(" "+ poc2[i]);
                             
                //    int a = poc2[i];
    /*
       kdyby to prestalo fungovat, tak tam vrat:       
            policka3[b][jej+1].setBackground(Color.blue); 
                   baPom[poc2[0]] = -1;
                   b--;
        misto pomocRozhodni()
        a taky si vytvor b a bi v akci tlacitka zrus
     */       
            int[] baPom = new int[4];
            System.arraycopy(poc, 0, baPom, 0, poc.length);
                    switch(kk){                        
                        case(2):
                            for(int i = 0; i<2; i++){
                                int a = poc2[i];
                                if(barvyNasazene[a]==poc[poc2[0]]){
                                    policka3[bi][jej+1].setBackground(Color.blue); 
                                    bi--;
                                    continue;
                                }    
                                if(barvyNasazene[a]==poc[poc2[1]]){
                                    policka3[bi][jej+1].setBackground(Color.blue); 
                                    bi--;
                                    continue; 
                                }
                            }   
                            break;
                        case(3): 
                            for(int i = 0; i<3; i++){
                                int a = poc2[i];
                                if(barvyNasazene[a]==baPom[poc2[0]]){
                                    pomocRozhodni(0, baPom,poc2);
                                    continue;
                                }    
                                if(barvyNasazene[a]==baPom[poc2[1]]){
                                    pomocRozhodni(1, baPom,poc2);
                                    continue;
                                }
                                if(barvyNasazene[a]==baPom[poc2[2]]){
                                    pomocRozhodni(2, baPom,poc2);
                                    continue;
                                }  
                            }   
                            break;
                        case(4):          
                            for(int i = 0; i<4; i++){
                                int a = poc2[i];
                                if(barvyNasazene[a]==baPom[poc2[0]]){
                                    pomocRozhodni(0, baPom,poc2);
                                    continue;
                                }    
                                if(barvyNasazene[a]==baPom[poc2[1]]){
                                    pomocRozhodni(1,baPom,poc2);
                                    continue;
                                }                            
                                if(barvyNasazene[a]==baPom[poc2[2]]){
                                    pomocRozhodni(2, baPom,poc2);
                                    continue;
                                }    
                                if(barvyNasazene[a]==baPom[poc2[3]]){
                                    pomocRozhodni(3, baPom,poc2);
                                    continue;
                                } 
                            }        
                            break;
                    }
            
            int k = 0;
            for(int i = 0; i<4; i++){
                if(policka3[i][jej+1].getBackground()==Color.green){
                    k++;
                }
                if(k==4){ 
                    String vytez = "Blahopreji, vyhral jsi.";
                    System.out.println("vyhral jsi");
                  //  Stopky s = new Stopky("sstopkyy");
                    s.stop(false); 
                    spust = false;
                    Date dd = new Date();
                    Cas.setCasKonec(dd.getTime());           
                    System.out.println(cas.getCasKonec() + " konec "+ dd.toString());     
                    ukazPolicka = true;                                              
                    ukazPolicka(4);
                    Soubor soub = new Soubor();  
                    VitezneOkno o = new VitezneOkno(cas.getSpocitejCas(), vytez, cas.getNejlepsiCas(),soub.odeHry()+"" , soub.vyhraneHry() + "", 130);                    
                }                   
            }            
        }

    
        
       private void pomocRozhodni(int a, int[] baPom, int[] poc2){
            policka3[bi][jej+1].setBackground(Color.blue); 
            baPom[poc2[a]] = -1;
            bi--;                                    
       } 
        
       public JPanel nandejPolickaHodnoceni(){     
            for(int i = 0; i < 4; i++){           
               JButton b = new JButton();       

               policka3[i][jej] = b;
               policka3[i][jej].setLayout(null);
               policka3[i][jej].setBounds(new Rectangle(50 + i*30, 20+jej*30,20 ,20 ));
               panel4.add(b);
            }                    
        return panel4;
    }
       
      public JPanel nandejHraciPolicka(){        
            for(int i = 0; i < 4; i++){  
                
            final JButton b = new JButton();
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    b.setBackground(getColor());                    
                    
                    System.out.println("Prave jsem ulozil " + poz + " barvu");
                    if(poz<4){
                        col[poz++] = getColor();                    
                    }
                    else col[3] = getColor();
                }
            } ); 
               policka[i][jej] = b;              
               policka[i][jej].setLayout(null);
               policka[i][jej].setBounds(new Rectangle(40 + i*30, 20+jej*30,20 ,20 ));                
               panel2.add(b);                          
            }       
        return panel2; 
    } 
     
        private void setBarva(int a){
            Barvy ba = new Barvy();
            c = ba.getColor(a);
            int b = ba.f+1;
            if(co<4){
                barvyNasazene[co++] =  b;
            }
            else barvyNasazene[3] = b;                              
        }

        private Color getColor(){
            return c;
        }
                       
         public JPanel polickaNaVyber(){ 
            Barvy barvy = new Barvy(); 
            d = new Date();
            for(int j = 0; j < 7; j++){
               final JButton b = new JButton();                             
               policka2[0][j] = b;    
               policka2[0][j].setLayout(null);
               policka2[0][j].setBounds(new Rectangle( 70+j*35,25 ,25 ,25 ));               
               policka2[0][j].setBackground(barvy.getColor(j+1));                              
               panel3.add(b);
            }   
            for(int i = 0; i<7; i++){
                
                priradAkci(i);
            }
            return panel3;
//          ukazka kodu jaky tu byl, kdyby to nefungovalo...
//          byl tam i kod pro vyzsi cisla nez jen 7  
//          policka2[0][6].addActionListener(new ActionListener(){
//                public void actionPerformed(ActionEvent c) {
//                    setBarva(7);
//                    casZacatek = d.getTime();
//                    System.out.println("cas zacatek " + casZacatek + "  "+ d.toString());
//                }
//            });                                
    }
         
         private void priradAkci(final int a){
            policka2[0][a].addActionListener(new ActionListener() {                            
                public void actionPerformed(ActionEvent e) {
                    setBarva(a+1);  
                    Cas.setZacatek(d.getTime());   
                    uplynulyCas();
                    
                    la2.setText(nastav());  
                    
                    System.out.println("cas zacatek " + cas.getZacatek() + "  "+ d.toString());
                } 
            });       
         }

    @SuppressWarnings("static-access")
    private String uplynulyCas() {
      //  Stopky s = new Stopky("Stopky"); 
        if(s.jsemZive() == false){
        s.start(true);      
        }       
        spust = true;
        System.out.println("ddd");
       // nastav();
        System.out.println("jsem sem se u6 nedostal");
        la.repaint();
        return s.cas;    
    }    
         public String nastav(){                
//            while(spust){
//                
//                la2.setText();
//                la2.repaint();
//            }
             Stopky sss = new Stopky("Stopky");
             return sss.getCas();
             
         }
}


    
