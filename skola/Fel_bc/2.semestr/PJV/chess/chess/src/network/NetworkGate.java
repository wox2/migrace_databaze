/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import controler.Builder;

/**
 *
 * @author sprinkler
 */
public class NetworkGate {

    private Client client = new Client();
    private Server server = new Server();

    public boolean sendData(Object data) {
        return client.sendData(data);
    }

    public boolean sendData(Object data, String ipAddress) {
        client.setAddress(ipAddress);
        return client.sendData(data);
    }

    public Object acceptData() {
       return server.acceptData();
    }

    public void setAsClient(){
        client.setAsClient();
        server.setAsClient();
    }

    public void setAsServer(){
        client.setAsServer();
        server.setAsServer();
    }
}
