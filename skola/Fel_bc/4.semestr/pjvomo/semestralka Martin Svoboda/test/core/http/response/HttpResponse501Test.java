package core.http.response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HttpResponse501Test {

	protected HttpResponse501 res;
	
	
	@Before
	public void setUp() {
		res = new HttpResponse501();
	}
	
	@Test
	public void testGetReasonPhrase() throws Exception {
		assertEquals(res.getReasonPhrase(), "Not Implemented");
	}
	
	@Test
	public void testGetStatusCode() throws Exception {
		assertEquals(res.getStatusCode(), 501);
	}
}
