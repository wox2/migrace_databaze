   
package logikhra;

       
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Okno.Okenko;
import Okno.Soubor;

  
                     
public class Main {         
                       
//    public static int cas;   
//    public static int odehraneHry;
//    public static int vyhraneHry;
    public int[] pole = new int[3];
    static Okenko o;
    
    public Main(){
       // o = new Okenko(9);  
    }                   
    
    
     
    public static void main(String[] args) throws IOException {   
            
                    
     
//             FileReader fr = new FileReader("hodnoceni.txt");
//             BufferedReader br = new BufferedReader(fr);
//  
//             String radka;
//             int d = 0;     
//             int[] pole = new int[100];    
//             pole[0]=1000;   
//             while((radka = br.readLine()) != null){ 
//                 String[] cisla = radka.split(" ");                            
//                 int[] pole1 = new int[cisla.length];        
//                 for(String cislo : cisla){  
//                 pole[d] = Integer.parseInt(cislo); 
//                 d++;
//                 } 
//             System.arraycopy(pole, 0, pole1, 0, pole1.length);   
//             }
//             cas = pole[0];  
//             odehraneHry = pole[1]+1;   
//             vyhraneHry = pole[2]+1;      
//             br.close();
          //   Pok po = new Pok("ahoj");
        Soubor s = new Soubor();  
             o = new Okenko(9);                 
                                                                                  
    }                           
    
    public void spust(){
                     
    }
//    public int odeHry(){     
//        return odehraneHry;   
//    }
                               
    public void setViditelny(boolean b) {
         o.setVisible(b);    
    }        
           
//    public String vyhraneHry(){
//        return vyhraneHry + "";         
//    }                                                      
//    public int getCas(){
//        return cas;
//    }
                                                                                                            
}                                                                                                                      
                                                                                                                       
                                                          