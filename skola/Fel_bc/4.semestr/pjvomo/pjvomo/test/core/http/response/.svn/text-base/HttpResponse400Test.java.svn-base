package core.http.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HttpResponse400Test {
	
	protected HttpResponse400 res;
	
	@Before
	public void setUp() {
		res = new HttpResponse400();
	}
	
	@Test
	public void testGetReasonPhrase() throws Exception {
		assertEquals(res.getReasonPhrase(), "Bad Request");
	}
	
	@Test
	public void testGetStatusCode() throws Exception {
		assertEquals(res.getStatusCode(), 400);
	}
}
