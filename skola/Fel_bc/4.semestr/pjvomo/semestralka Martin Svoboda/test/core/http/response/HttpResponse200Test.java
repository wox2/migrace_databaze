package core.http.response;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import core.http.HttpRequest;

public class HttpResponse200Test {
	
	protected HttpResponse200 res;
	
	@Before
	public void setUp() {
		HttpRequest mockRequest = new HttpRequest(new StringReader("test")) {
			
			public String getMethod() {
				return "GET";
			}
			
			public String getPath() {
				return "/mydir/";
			}
			
			public String getVersion() {
				return "Some/1.0";
			}
		};
		
		
		File mockFile = new File("test/tmp/mydir");
		
		res = new HttpResponse200(mockRequest, mockFile) {

			@Override
			public void out(OutputStream output) throws IOException {
				
			}
		};
	}
	
	@Test
	public void testGetStatusLine() {
		assertEquals(res.getStatusLine(), "Some/1.0 200 OK");
	}
}
