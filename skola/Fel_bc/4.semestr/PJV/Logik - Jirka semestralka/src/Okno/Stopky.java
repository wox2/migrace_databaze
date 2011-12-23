/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Okno;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mary
 */
class Stopky extends Thread{
    Thread t;
    String s;
    static String cas = "0 s";
    private boolean run; 
   
    
    Stopky(String jmeno){
        super(jmeno);
        t = new Thread(jmeno);
        s = jmeno;   
    }
    
    @Override
    @SuppressWarnings("static-access")
    public void run(){
        System.out.println(" bezim ");
        int a = 1;
        while (run){
            try {
               // System.out.println("Cekam..." );
                t.sleep(1000);
                a++;
                Okenko o = new Okenko();
                setCas(a);
                System.out.println("Vykonano! " + a);
                o.la2.setText(cas);
                o.la2.repaint();
           }
            catch (InterruptedException ex) {
                Logger.getLogger(Stopky.class.getName()).log(Level.SEVERE, null, ex);
           }       
        }     
    }
    public void setCas(int a){
        cas = a +" s";    
    }
    public String getCas(){
        return cas;
    }

    void start(boolean b) {
        
        if(t.isAlive()){
            System.out.println("Uz ziju...");
        }
        else{
            System.out.println(s);
            t = new Thread(this);
        t.start();
        
        run=b;
        }
        
    }
   
    void stop(boolean b) {
        System.out.println(s);
        t = null;
        run = b;
    }
    public boolean jsemZive(){
        if(t.isAlive()){
            
            System.out.println("Jsem ziveee...");
            return true;
        }
        else {System.out.println("jeste neziju.");
        return false;
        }
    }
}
