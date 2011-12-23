package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;


public class ConfigurationTest {

	@Test
	public void testGetInstance() {
		Configuration conf1 = Configuration.getInstance();
		Configuration conf2 = Configuration.getInstance();
		
		assertEquals(conf1, conf2);
	}

	@Test
	public void testGetAndGetRoot() {
		
		String root1 = "someroot1";
		String root2 = "someroot2";
		Configuration conf = Configuration.getInstance();
		
		conf.setRoot(root1);
		assertEquals(root1, conf.getRoot());
		
		conf.setRoot(root2);
		assertEquals(root2, conf.getRoot());
	}

	@Test
	public void testSetAndGetIp() throws Exception {
		InetAddress adr1 = InetAddress.getByAddress(new byte[] {(byte) 127, (byte) 0, (byte) 0, (byte) 1});
		Configuration conf = Configuration.getInstance();
		
		conf.setIp(adr1);
		assertEquals(adr1, conf.getIp());
	}
	
	@Test
	public void testSetIpAsString() throws Exception {
		InetAddress adr1 = InetAddress.getByAddress(new byte[] {(byte) 127, (byte) 0, (byte) 0, (byte) 1});
		Configuration conf = Configuration.getInstance();
		
		conf.setIp("127.0.0.1");
		assertEquals(adr1, conf.getIp());
	}
	
	@Test
	public void testSetAndGetInvalidIp() {
		Configuration conf = Configuration.getInstance();
		
		try {
			conf.setIp("sadfds sdf sdf sdf sfas");
			conf.getIp();
			fail();
		} catch (UnknownHostException e) {
			// success
		}
	}

	@Test
	public void testSetAndGetPort() {
		int port1 = 10;
		int port2 = 80;
		Configuration conf = Configuration.getInstance();
		
		conf.setPort(port1);
		assertEquals(port1, conf.getPort());
		
		conf.setPort(port2);
		assertEquals(port2, conf.getPort());
	}
	
	@Test
	public void testSetPortAsString() {
		Configuration conf = Configuration.getInstance();
		
		conf.setPort("123");
		assertEquals(123, conf.getPort());
	}
}
