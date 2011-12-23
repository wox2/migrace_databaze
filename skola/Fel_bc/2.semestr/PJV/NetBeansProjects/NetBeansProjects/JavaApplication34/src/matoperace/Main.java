/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package matoperace;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int [][] pokus={{3,4,3},{2,44,22},{3,45,67}};
        Increasement inc1=new Increasement(pokus);
        inc1.solve();
        
    }

}
