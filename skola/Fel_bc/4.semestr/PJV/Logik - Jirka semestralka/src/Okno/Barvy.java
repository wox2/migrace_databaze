
package Okno;

import java.awt.Color;

public class Barvy {
    Color c;
    public int f;
    
    public Color getColor(int a){
        switch(a){
            
            case 1:  f=0; return c = new Color(255,0,0); 
              
            case 2:  f=1; return c = new Color(0,0,0);
            
            case 3:  f=2; return c = new Color(0,255,0);
             
            case 4:  f=3; return c = new Color(0,0,255);
               
            case 5:  f=4; return c = new Color(255,0,255);
             
            case 6:  f=5; return c = new Color(0,255,255);
                
            case 7:  f=6; return c = new Color(255,255,0);        
            
//            case 8:  f=8; return c = new Color(255,255,255);
//                
//            case 9:  f=9; return c = Color.DARK_GRAY;   
//            
//            case 0:  f=0; return c = Color.PINK;
            
        } 
       
        return c = Color.red;
    }
}
