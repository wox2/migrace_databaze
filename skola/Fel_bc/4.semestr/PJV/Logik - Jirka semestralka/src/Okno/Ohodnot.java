/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

import java.awt.Color;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import logikhra.Main;

/**
 *
 * @author mary
 */

// to bz asi neslo, nebo jen velmi spatne

public class Ohodnot {
    static int[] poc = new int[4];    
    PolickaPocitac poPo = new PolickaPocitac();
    int[] barvyNasazene = new int[4];
    int co= 0;
    Color c;
    JButton[][] policka3 = new JButton[4][10]; 
    int jej = 9;
    private int bi = 3;
    Cas cas = new Cas();
    public boolean ukazPolicka = false;
    
    public Ohodnot() throws IOException{
        ohodnot1();
    }
    
     public void ohodnot1() throws IOException{          
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
                    Date dd = new Date();
                    Cas.setCasKonec(dd.getTime());           
                    System.out.println(cas.getCasKonec() + " konec "+ dd.toString());     
                    ukazPolicka = true;                                              
                    ukazPolicka(4);
                    Soubor soub = new Soubor();  
                    VitezneOkno o = new VitezneOkno(cas.getSpocitejCas(), vytez, cas.getNejlepsiCas(),soub.odeHry()+"", soub.vyhraneHry() + "" , 130);                    
                }                   
            }            
        }                
        
       private void pomocRozhodni(int a, int[] baPom, int[] poc2){
            policka3[bi][jej+1].setBackground(Color.blue); 
            baPom[poc2[a]] = -1;
            bi--;                                    
       } 
    private void setBarva(int a){
            Barvy ba = new Barvy();
            c = ba.getColor(a);
            int b = ba.f+1;
            barvyNasazene[co++] =  b;
           // co = ba.f+1;                     
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
}
