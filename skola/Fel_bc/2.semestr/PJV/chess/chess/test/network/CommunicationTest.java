/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
public class CommunicationTest {

    public CommunicationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testSetAsServer() {
        Communication comunication = new Communication();
        comunication.setAsServer();
        assertTrue(comunication.getClientPort() == 6015);
        assertTrue(comunication.getServerPort() == 6018);
    }

    @Test
    public void testSetAsClient() {
        Communication comunication = new Communication();
        comunication.setAsClient();
        assertTrue(comunication.getClientPort() == 6018);
        assertTrue(comunication.getServerPort() == 6015);
    }

    @Test
    public void testGetAddress() {
        Communication comunication = new Communication();
        try {
            comunication.getAddress();
            fail();
        } catch (NetworkException ex) {
            System.err.println(ex);
        }
        comunication.setAddress("127.0.0.1");
        try {
            assertTrue(comunication.getAddress().equals(InetAddress.getByName("127.0.0.1")));
        } catch (NetworkException ex) {
            System.err.println(ex);
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testGetClientPort() {
        Communication communication = new Communication();
        communication.setAsServer();
        assertTrue(communication.getClientPort() == 6015);
        communication.setAsClient();
        assertTrue(communication.getClientPort() == 6018);
    }

    @Test
    public void testGetServerPort() {
        Communication communication = new Communication();
        communication.setAsServer();
        assertTrue(communication.getServerPort() == 6018);
        communication.setAsClient();
        assertTrue(communication.getServerPort() == 6015);
    }

    @Test
    public void testSetAddress_InetAddress() {
        try {
            Communication communication = new Communication();
            InetAddress adress = InetAddress.getByName("127.0.0.1");
            communication.setAddress(adress);
            assertTrue(communication.getAddress().equals(adress));
        } catch (NetworkException ex) {
            System.err.println(ex);
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
    }

    @Test
    public void testSetAddress_String() {
        Communication communication = new Communication();
        try {
            InetAddress adress = InetAddress.getByName("127.0.0.1");
            communication.setAddress("127.0.0.1");
            assertTrue(communication.getAddress().equals(adress));
        } catch (NetworkException ex) {
            System.err.println(ex);
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        communication.setAddress("chyba");
    }
}