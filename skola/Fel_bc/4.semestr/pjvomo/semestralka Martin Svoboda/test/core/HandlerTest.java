package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.junit.Test;

public class HandlerTest {

	@Test
	public void testClone() throws IOException {
		Handler h1 = new Handler();
		
		Socket s = new Socket() {
			@Override
			public InputStream getInputStream() throws IOException {
				return new ByteArrayInputStream("some input".getBytes());
			}
			
			@Override
			public OutputStream getOutputStream() throws IOException {
				return new ByteArrayOutputStream();
			}
			
		};
		
		h1.setSocket(s);
		Handler h2 = h1.clone();
		
		assertNotSame(h2, h1);
		assertEquals(h1.socket, s);
		assertEquals(h2.socket, null);
		
		try {
			h2.run();
			fail();
		} catch (RuntimeException e) {
			// success
		}
	}
}
