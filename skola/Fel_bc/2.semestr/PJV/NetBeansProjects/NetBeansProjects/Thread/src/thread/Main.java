/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package thread;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*  Vlakna - maj jeden pametovej prostor, maj pristup k vsem vlaknum
         *           mechanismus synchronized, semafory - ridi planovac,
         *           problemy - muze techto kritickych mist byt hodne
         *                     - program furt zamyka a odemyka a je pomalej
         *                    - nedaj se ladit
         *           rada - co nejmin vlaken, co nejmin glob prom, ktere vlakna sdili
         *                -
         * Procesy - maj vlastni pamet, maj pristup jen k vlastnim datum
         *
         *
         */
        TaskA ta = new TaskA();
        ta.start();
        TaskA tab= new TaskA("Hozik");
        tab.start();

    }

}
