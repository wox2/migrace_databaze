package core.http.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HttpResponse404Test {

	protected HttpResponse404 res;
	
	@Before
	public void setUp() {
		res = new HttpResponse404();
	}
	
	@Test
	public void testGetReasonPhrase() throws Exception {
		assertEquals(res.getReasonPhrase(), "Not Found");
	}
	
	@Test
	public void testGetStatusCode() throws Exception {
		assertEquals(res.getStatusCode(), 404);
	}
}
