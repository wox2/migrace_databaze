/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author sprinkler
 */
public class Server extends Communication {

    public Object acceptData() {
        Object data = null;
        try {
            System.out.println(getServerPort());
            ServerSocket sSocket = new ServerSocket(getServerPort());
            Socket socket = sSocket.accept();
            setAddress(socket.getInetAddress());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            data = reader.readObject();
            System.out.println(data);
            if (!sSocket.isClosed()) {
                sSocket.close();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (SocketException e) {
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
