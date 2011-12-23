/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

import java.io.FileWriter;
import java.io.IOException;
import logikhra.Main;

/**
 * 
 * @author mary  
 */
public class Cas {
    private static long casZacatek;
    private static long casKonec;
    public int nejCas;
    
    private String spocitejCas(){
            long vysledek =getCasKonec() - getZacatek();
            nejCas = (int) vysledek/1000;
            System.out.println("vysledek casu " +vysledek + " "+ nejCas);
            return nejCas +" s";
    }
    public String getSpocitejCas(){
        return spocitejCas();
    }
    
    public int setNejlepsiCas() throws IOException {
            Soubor soub = new Soubor();     
            FileWriter f4 = new FileWriter("hodnoceni.txt"); 
            if (soub.getCas()<nejCas){      
                 f4.write(soub.getCas()+" " + soub.odeHry() + " "+ soub.vyhraneHry());                
                 f4.close();      
                return soub.getCas();               
            }
            else{       
                 f4.write(nejCas+" " + soub.odeHry());                
                 f4.close();
                return nejCas;
            }
        }
 
        public String getNejlepsiCas() throws IOException {             
            return setNejlepsiCas()+" s";
        }
        
    static void setZacatek(long time) {
        casZacatek = time;
    }
    long getZacatek(){
        return casZacatek;
    }    
    
    
    static void setCasKonec(long time) {
        casKonec = time;
    }
    long getCasKonec(){
        return casKonec;
    }
    
}
