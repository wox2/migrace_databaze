package core.http.response;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

public class HttpResponseErrorTest {

	protected HttpResponseError res;
	
	@Before
	public void setUp() {
		res = new HttpResponseError() {
			protected int getStatusCode() {
				return 123;
			}
			protected String getReasonPhrase() {
				return "XXX XXX XXX";
			}
		};
	}
	
	@Test
	public void testGetBody() throws Exception {
		assertEquals(res.getBody(), "<html><body><h1>123 - XXX XXX XXX</h1></body></html>");
	}
	
	@Test
	public void testOut() throws Exception {

		OutputStream output = new OutputStream() {
			
			public String out = "";
			
			@Override
			public void write(int b) throws IOException {
				
				out += (char) b;
			}
			
			public String toString() {
				return out;
			}
		};
		
		res.out(output);
		
		assertEquals(output.toString(), "HTTP/1.0 123 XXX XXX XXX\r\nContent-Length: 52\r\nContent-Type: text/html\r\nServer: My Java Server\r\n\r\n<html><body><h1>123 - XXX XXX XXX</h1></body></html>\r\n");
	}
}
