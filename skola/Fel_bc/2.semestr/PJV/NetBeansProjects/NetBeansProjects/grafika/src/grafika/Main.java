/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafika;

/**
 *
 * @author já
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Demeterovo pravidlo - Law of demeter
         * http://blog.vyvojar.cz/pbouda/archive/2008/07/22/muzeme-se-obejit-bez-dedicnosti.aspx
         * http://en.wikipedia.org/wiki/Law_of_Demeter
         *
         * -mluv pouze s nejblizsimi
         *  cile volani
         *      -this
         *      -objekt, ktery je parametrem
         *      -vlastni atributy
         *      -nove objekty
         *      -na globalni objekty
         * -nedodrzeni pravidla - priznaky  - get.get.get.get...
         *                                  - import moc veci - radsi pouzivat jen importy bez hvezdicek
         *
         *
         */
    }

}
