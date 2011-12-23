package core;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;


public class ServerTest {
	
	protected boolean handlerCloned = false;
	protected boolean handlerSetSocket = false;
	protected boolean handlerRun = false;
	
	@Before
	public void setUp() {
		Logger.getLogger("").setLevel(Level.OFF);
	}
	
	@Test
	public void testIsAlive() {
		Server s = new Server(new Handler());
		
		assertFalse(s.isAlive());
		
		s.start();
		assertTrue(s.isAlive());
		
		s.stop();
		s.stop();
		
		assertFalse(s.isAlive());
	}
	
	@Test
	public void testSetIp() throws UnknownHostException {
		
		Configuration conf = Configuration.getInstance();
		conf.setIp("127.0.0.1");
		
		Server s = new Server(new Handler());
		s.start();
		
		assertEquals(conf.getIp(), s.serverSocket.getInetAddress());
	}
	
	@Test
	public void testRun() throws IOException, InterruptedException {
		Handler h = new Handler() {
			
			@Override
			public void setSocket(Socket socket) throws IOException {
				handlerSetSocket = true;
				super.setSocket(socket);
			}
			
			@Override
			public void run() {
				handlerRun = true;
				super.run();
			}
			
			@Override
			public Handler clone() {
				handlerCloned = true;
				return super.clone();
			}
		};
		
		Server s = new Server(h);
		s.start();
		
		assertFalse(handlerCloned);
		assertFalse(handlerRun);
		assertFalse(handlerSetSocket);
		
		// vytvorim socket a pockat na accept
		new Socket(s.serverSocket.getInetAddress(), s.serverSocket.getLocalPort());
		Thread.sleep(1000);
		
		assertTrue(handlerCloned);
		assertTrue(handlerRun);
		assertTrue(handlerSetSocket);
		
		s.stop();
	}
	
	@Test
	public void testStartOnTakenPort() throws UnknownHostException {
		
		Configuration conf = Configuration.getInstance();
		conf.setIp("127.0.0.1");
		
		Server s = new Server(new Handler());
		
		assertFalse(s.isAlive());
		assertTrue(s.start());
		
		// set same port
		conf.setPort(s.serverSocket.getLocalPort());
		
		assertFalse(s.start());
		assertTrue(s.isAlive());
		
		s.stop();
		
		assertFalse(s.isAlive());
	}
}
