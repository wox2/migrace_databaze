package core.http.response;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;


public class HttpResponseTest {
	
	protected HttpResponse res;
	
	@Before
	public void setUp() {
		res = new HttpResponse() {
			
			@Override
			public void out(OutputStream output) throws IOException {
				
			}

			@Override
			protected String getStatusLine() {
				return "";
			}
		};
	}
	
	@Test
	public void testGetHeaderContentType() {
		assertEquals(res.getHeaderContentType("sometype/blabla"), "Content-Type: sometype/blabla");
	}
	
	@Test
	public void testGetHeaderContentLength() {
		assertEquals(res.getHeaderContentLength(456321), "Content-Length: 456321");
	}
	
	@Test
	public void testGetHeaderServer() {
		assertEquals(res.getHeaderServer(), "Server: My Java Server");
	}
	
	@Test
	public void testWrite() throws Exception {
		
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
		
		res.write(output, "Some content");
		assertEquals(output.toString(), "Some content\r\n");
	}
}
