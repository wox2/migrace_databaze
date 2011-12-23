/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication61;

/**
 *
 * @author já
 */
public class Singleton {


        private static Singleton s;

        public Singleton get() {
            if (s == null) {
                s = new Singleton();
            }
            return s;
        }

        private Singleton() {
            /* kod */
        }


}
