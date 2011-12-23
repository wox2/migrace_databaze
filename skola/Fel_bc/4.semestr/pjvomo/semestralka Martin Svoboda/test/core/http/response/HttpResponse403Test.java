package core.http.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HttpResponse403Test {

	protected HttpResponse403 res;
	
	@Before
	public void setUp() {
		res = new HttpResponse403();
	}
	
	@Test
	public void testGetReasonPhrase() throws Exception {
		assertEquals(res.getReasonPhrase(), "Forbidden");
	}
	
	@Test
	public void testGetStatusCode() throws Exception {
		assertEquals(res.getStatusCode(), 403);
	}
}
