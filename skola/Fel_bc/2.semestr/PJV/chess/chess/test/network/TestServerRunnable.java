/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package network;

/**
 *
 * @author sprinkler
 */
public class TestServerRunnable implements Runnable{

    public String data;
    private Server server = new Server();

    public void run() {
        server.setAsServer();
        data = (String) server.acceptData();
    }

}
