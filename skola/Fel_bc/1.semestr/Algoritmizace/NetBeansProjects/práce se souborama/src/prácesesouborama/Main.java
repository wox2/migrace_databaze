/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prácesesouborama;

import java.io.*;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try {
            BufferedWriter b = null;
            FileInputStream fis = new FileInputStream("E:\\Java-project\\test\\muj.txt");
            b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\Java-project\\test\\muj1.txt")));
            
            int k = 0, m = 0, l = 0;
            int [][] pocetZnaku = new int[26][2];
            for (char i='a'; i<='z'; i++){
               pocetZnaku[l][0] = i;
               pocetZnaku[l][1] = 0;
               //System.out.print((char) pocetZnaku[l][0]);
               l++;
            }
            while ((k = fis.read()) != -1) {
                //System.out.print(k);
                for (int j=0;j<26;j++)
                {
                    if (k == pocetZnaku[j][0])
                    {
                       pocetZnaku[j][1]++; 
                    }
                }
                m++;
            }
            
            for (int j=0;j<26;j++)
                {
                    System.out.println((char)pocetZnaku[j][0]+" "+((double)pocetZnaku[j][1] * 100 / m)+"%");
                    int procento = ((int)pocetZnaku[j][1] * 100 / m);
                    b.write((char)pocetZnaku[j][0]+" "+((double)pocetZnaku[j][1] * 100 / m)+"% \n ");
            } 
             b.close();
            fis.close();
//            System.out.print(m);
        } catch (Exception ex) {
            System.out.print(ex);
        } 
    }

}
