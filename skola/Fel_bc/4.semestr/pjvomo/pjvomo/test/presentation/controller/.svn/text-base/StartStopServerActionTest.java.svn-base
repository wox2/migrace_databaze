package presentation.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import presentation.view.MainLayout;
import core.Configuration;
import core.Handler;
import core.Server;

public class StartStopServerActionTest {
	
	protected StartStopServerAction sa;
	protected boolean isStarted;
	
	@Before
	public void setUp() {
		isStarted = false;
		
		sa = new StartStopServerAction(new MainLayout()) {
			@Override
			protected Server getServer() {
				return new Server(new Handler()) {
					
					@Override
					public boolean isAlive() {
						return isStarted;
					}
					
					@Override
					public boolean start() {
						isStarted = true;
						return true;
					}
					
					@Override
					public boolean stop() {
						isStarted = false;
						return true;
					}
				};
			}
		};
	}

	@Test
	public void testActionPerformed() {
		sa.layout.rootTF.setText("somepath");
		sa.layout.portTF.setText("80");
		sa.layout.ipTF.setText("10.0.0.127");
		
		// start server
		sa.actionPerformed(new ActionEvent(new Object(), 10, "message"));
		
		// je ulozena konfigurace ?
		Configuration conf = Configuration.getInstance();
		assertTrue(conf.getIp().toString().contains("10.0.0.127"));
		assertEquals(80, conf.getPort());
		assertEquals("somepath", conf.getRoot());
		
		// je server spusten ?
		assertTrue(isStarted);
		
		// stop server
		sa.actionPerformed(new ActionEvent(new Object(), 10, "message"));
		
		// je server zastaven ?
		assertFalse(isStarted);
	}
	
	@Test
	public void testStartErrors() throws Exception {
		java.util.logging.Handler h = new java.util.logging.Handler() {
			public String data = "";
			
			@Override
			public void close() throws SecurityException { }

			@Override
			public void flush() { }

			@Override
			public void publish(LogRecord record) {
				data += record.getMessage();
			}
			
			@Override
			public String toString() {
				String tmp = data;
				data = "";
				return tmp;
			}
		};
		
		Logger.getLogger("").addHandler(h);
		
		sa.layout.rootTF.setText("");
		sa.layout.portTF.setText("");
		sa.layout.ipTF.setText("");
		
		// start server
		sa.actionPerformed(new ActionEvent(new Object(), 10, "message"));
		assertEquals("Please input port field", h.toString());
		
		isStarted = false;
		
		sa.layout.portTF.setText("80");
		sa.actionPerformed(new ActionEvent(new Object(), 10, "message"));
		assertEquals("Please input web root field", h.toString());
	}
}
