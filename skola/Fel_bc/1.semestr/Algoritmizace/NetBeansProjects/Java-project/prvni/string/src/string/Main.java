/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package string;

import java.util.Scanner;


/**
 *
 * @author Administrator
 */
public class Main {

    static final int ID = 1,  CHAR = 2,  NUM = 3,  EOL = 0;
    static String program;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        program = new Scanner(System.in).nextLine();
        print();
    }

    static int[] pocetZnaku() {
        int[] pocetZnaku = {0, 0, 0};
        int delkaProgramu = program.length();
        int iPredchozi = 0;
        int druhZnaku;
        for (int i = 0; i < delkaProgramu; i++)
        {
            druhZnaku = druhZnaku();
            if (ID == druhZnaku) {
                if (iPredchozi != ID){
                    pocetZnaku[0]++;    
                }
                iPredchozi = ID;
            }
            else if (NUM == druhZnaku && iPredchozi !=CHAR) {
                if (iPredchozi != NUM){
                    pocetZnaku[1]++;    
                }
                iPredchozi = NUM;
            }
            else if (CHAR == druhZnaku || (iPredchozi == CHAR && NUM == druhZnaku)) {
                if (iPredchozi != CHAR){
                    pocetZnaku[2]++;    
                }
                iPredchozi = CHAR;
            }
            else
            {
                iPredchozi = EOL;
            }
        }
        return pocetZnaku;
    }
    
    static void print()
    {
        int[] pocetZnaku = pocetZnaku();
        String [] druhZnaku = {"operator" ,"číslo", "identifikator"}; 
        for (int i = 0; i< 3;i++){
            System.out.println(druhZnaku[i]+"-"+pocetZnaku[i]);
        }
    }

    static int druhZnaku() {
        String programPomocny = "";
        int druhZnaku = 0;

        if ((int)program.charAt(0) >= 48 && (int)program.charAt(0) < 58)
        {
           druhZnaku = NUM;
        }
        else if (program.charAt(0) ==' '){
             druhZnaku = EOL;
        }
        else if (((int)program.charAt(0) >= 65 &&  (int)program.charAt(0) <= 122) || ((int)program.charAt(0) >= 40 && (int)program.charAt(0) <= 41))
        {
           druhZnaku = CHAR;            
        }
        else
        {
          druhZnaku = ID;  
        }
        for (int i = 1; i < program.length(); i++) {
            programPomocny += program.charAt(i);
        }
        program = programPomocny;
        return druhZnaku;
    }
}
