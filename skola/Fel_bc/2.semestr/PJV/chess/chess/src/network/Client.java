/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sprinkler
 */
public class Client extends Communication{

    public boolean sendData(Object data){
        try {
            System.out.println(getClientPort());
            Socket socket = new Socket(getAddress(), getClientPort());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            writer.writeObject(data);
            writer.flush();
            return true;
        } catch (NetworkException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
}
