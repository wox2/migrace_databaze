/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

/**
 *
 * @author já
 */
public class TaskA extends Thread implements Runnable {

    protected static String staticName;
    protected static Object staticNameLock = new Object();
    protected String name = "noname";

    public void run() {
        for (int i = 1; i < 100; i++) {
            synchronized (staticNameLock) {
                staticName = Integer.toString(i);
                System.out.println("TaskA " + name + " " + staticName + " info: " + i);
            }
            try {
        Thread.sleep(100);
        } catch (InterruptedException e){

        }
        }
    }

    public TaskA(String name) {
        this.name = name;
    }

    public TaskA() {
        this("");
    }
}
