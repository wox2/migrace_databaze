/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controler;

/**
 *
 * @author sprinkler
 */
public class DataServerRunnable implements Runnable{

    Builder builder;

    public DataServerRunnable(Builder builder) {
        this.builder = builder;
    }

    public void run() {
        while(true){
            builder.waitForData();
        }
    }

}
