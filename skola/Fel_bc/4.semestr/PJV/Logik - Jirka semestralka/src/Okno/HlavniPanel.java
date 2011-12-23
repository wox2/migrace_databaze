
package Okno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.element.Element;
import javax.swing.*;
import logikhra.Main;


public class HlavniPanel extends JFrame {  
    Okenko o;
    JMenuBar menuBar = new JMenuBar();
    JMenu hra = new JMenu("Hra");
    JMenu napoveda = new JMenu("Napověda");
    JMenuItem nova = new JMenuItem("Nova");
    JMenuItem nastaveni = new JMenuItem("Nastaveni");
    JMenuItem statistika = new JMenuItem("Statistika");
    JMenuItem konec = new JMenuItem("Konec");
    JMenuItem napoved = new JMenuItem("Napoveda");
    final String titulek = "Logik ";
    boolean pokus = true;
    
    
    public Object nabydka(){
        setJMenuBar(menuBar);
        menuBar.add(hra);
        menuBar.add(napoveda);
        hra.add(nova);
        hra.add(nastaveni);
        hra.add(statistika);
        hra.add(konec);
        napoveda.add(napoved);        
        
        konec.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){hraKonec_Akce(e);}
        });        
        nova.addActionListener(new ActionListener(){
            @SuppressWarnings("static-access")
            public void actionPerformed(ActionEvent e) {
                setOkenko();
                Main m = new Main();
         
               // m.setViditelny(false);
              //  setDefaultCloseOperation(HIDE_ON_CLOSE);
             //   isShowing()
               // Okenko oo = new Okenko();
                 
//                if(getOkenko() != null){
//                    o.setVisible(false);
//                    setOkenko();
//                }

             //   o.setVisible(false); 
                
               //  o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               //o.repaint();
        }
 
                    
        });
        statistika.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

//                    FileReader fr = new FileReader("hodnoceni.txt");
//                    BufferedReader br = new BufferedReader(fr);
//                    String radka;
//                    int d = 0;
//                    int[] pole = new int[100];
//                    while ((radka = br.readLine()) != null) {
//                        String[] cisla = radka.split(" ");
//                        int[] pole1 = new int[cisla.length];
//                        for (String cislo : cisla) {
//                            pole[d] = Integer.parseInt(cislo);
//                            d++;  
//                        }
//                        System.arraycopy(pole, 0, pole1, 0, pole1.length);
//                    }
                    Soubor soub = new Soubor();
                    Statistika s = new Statistika(soub.getCas() + " s", soub.odeHry()+ "", soub.vyhraneHry() + "", (int)((((double) soub.vyhraneHry()) / ((double) soub.odeHry())) * 100) + " %", "Vynuluj", "Zavri", 150);
                } 
                catch (IOException ex) {    
                    Logger.getLogger(HlavniPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });         
        return menuBar;
        
    } 
   
    private void setOkenko(){
        o = new Okenko(9);
    }
    private Object getOkenko(){
        return o;
    }

   protected void hraKonec_Akce(ActionEvent e){
        int i  = JOptionPane.showConfirmDialog(this, "Opravdu chces skoncit?", titulek, JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION){System.exit(0);}
            
   }
  
    
}
