/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import controler.DataServerRunnable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sprinkler
 */
public class ClientTest {

    Client client = new Client();
    TestServerRunnable testServerRunnable = new TestServerRunnable();
    Thread serverThread = new Thread(testServerRunnable);

    public ClientTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testSendData() {
        client.sendData("test");
        
        try {
            client.setAddress("127.0.0.1");
            client.setAsClient();
            serverThread.start();
            client.sendData("test");
            serverThread.join();
            assertTrue(testServerRunnable.data.equals("test"));
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
        client.sendData("test2");

    }
}