/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author sprinkler
 */
public class Communication {

    private int clientPort = 6015;
    private int serverPort = 6018;
    static private InetAddress address = null;

    public void setAsServer() {
        clientPort = 6015;
        serverPort = 6018;
    }

    public void setAsClient() {
        clientPort = 6018;
        serverPort = 6015;
    }

    public InetAddress getAddress() throws NetworkException{
        if (address == null){
            throw new NetworkException("IP address is not set");
        }
        return address;
    }

    public int getClientPort() {
        return clientPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setAddress(InetAddress adress) {
        address = adress;
    }

    public void setAddress(String adress) {
        try {
            address = InetAddress.getByName(adress);
        } catch (UnknownHostException ex) {
            System.out.println(ex);
        }
    }
}
