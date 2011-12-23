/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mary
 */
public class Soubor {
    private int odehraneHry;
    private int cas;
    private int vyhraneHry;
    
    public Soubor() throws IOException{
        hodnotySouboru();
    }

    
    public void hodnotySouboru() throws IOException{
        FileReader fr = new FileReader("hodnoceni.txt");
        BufferedReader br = new BufferedReader(fr);
  
             String radka;
             int d = 0;     
             int[] pole = new int[100];    
             pole[0]=1000;   
             while((radka = br.readLine()) != null){ 
                 String[] cisla = radka.split(" ");                            
                 int[] pole1 = new int[cisla.length];        
                 for(String cislo : cisla){  
                 pole[d] = Integer.parseInt(cislo); 
                 d++;
                 } 
             System.arraycopy(pole, 0, pole1, 0, pole1.length);   
             }
             cas = pole[0];  
             odehraneHry = pole[1]+1;     
             vyhraneHry = pole[2]+1;      
             br.close();
     
    }
    public int vyhraneHry(){
        return vyhraneHry;         
    }                                                      
    public int getCas(){
        return cas;
    }
    public int odeHry(){     
        return odehraneHry;   
    }
}
    
