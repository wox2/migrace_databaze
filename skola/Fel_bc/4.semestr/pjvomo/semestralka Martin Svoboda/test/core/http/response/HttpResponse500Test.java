package core.http.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HttpResponse500Test {

	protected HttpResponse500 res;
	
	@Before
	public void setUp() {
		res = new HttpResponse500();
	}
	
	@Test
	public void testGetReasonPhrase() throws Exception {
		assertEquals(res.getReasonPhrase(), "Internal Server Error");
	}
	
	@Test
	public void testGetStatusCode() throws Exception {
		assertEquals(res.getStatusCode(), 500);
	}
}
