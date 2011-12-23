/**
 * 
 */
package core.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author quick
 *
 */
public class HttpRequestTest {
	
	/**
	 * Test method for {@link core.http.HttpRequest#getResponse()}.
	 */
	@Test
	public void testGetMethod()  throws Exception {
		String inputStr = "GET / HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		Assert.assertEquals("GET", request.getMethod());
	}

	/**
	 * Test method for {@link core.http.HttpRequest#getPath()}.
	 */
	@Test
	public void testGetPath() throws Exception {
		String inputStr = "GET / HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		Assert.assertEquals("/", request.getPath());
	}
	
	/**
	 * Test method for {@link core.http.HttpRequest#getPath()}.
	 */
	@Test
	public void testGetPathWithParameter() throws Exception {
		String inputStr = "GET /somepath/file.htm?param=444&param2=aa HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		Assert.assertEquals("/somepath/file.htm", request.getPath());
	}
	
	/**
	 * Test method for {@link core.http.HttpRequest#getPath()}.
	 */
	@Test
	public void testGetPathWithParameterInvalid() throws Exception {
		String inputStr = "GET /somepath/file.htm?param=444?param2=aa HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		Assert.assertEquals("/somepath/file.htm", request.getPath());
	}
	
	/**
	 * Test method for {@link core.http.HttpRequest#getVersion()}.
	 */
	@Test
	public void testGetVersion() throws Exception {
		String inputStr = "GET / HTTP/1.1\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		Assert.assertEquals("HTTP/1.1", request.getVersion());
	}

	/**
	 * Test method for {@link core.http.HttpRequest#getHeader()}.
	 */
	@Test
	public void testGetHeader() throws Exception {
		
		String inputStr = "GET / HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		
		Reader input = new StringReader(inputStr);
		HttpRequest request = new HttpRequest(input);
		
		try {
			request.parseInput();

			Assert.assertEquals("Keep-Alive", request.getHeader("Connection"));
			Assert.assertEquals("Mozilla/4.7 [Fr] (Win98; I)", request.getHeader("User-Agent"));
		} catch (HeaderNotExistException e) {
			fail("HeaderNotExist Exception");
		}
	}
	
	/**
	 * Test method for {@link core.http.HttpRequest#getPath()}. Test getting not existing header
	 */
	@Test
	public void testGetNotExistingHeader() throws Exception {
		String inputStr = "GET / HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		
		Reader input = new StringReader(inputStr);		
		HttpRequest request = new HttpRequest(input);
		
		try {
			request.parseInput();
			request.getHeader("something");
			fail("Not throws exception");
		} catch (HeaderNotExistException e) {
			// success
		}
	}
	
	/**
	 * Test invalid request first line
	 */
	@Test
	public void testInvalidFirstLine1() throws Exception {
		String inputStr = "GETs / FTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		try {
			HttpRequest request = new HttpRequest(input);
			request.parseInput();
			fail();
		} catch (InvalidRequestException e) {
			// success
		}
	}
	
	/**
	 * Test invalid request first line
	 */
	@Test
	public void testInvalidFirstLine2() throws Exception {
		String inputStr = "GETs  HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		try {
			HttpRequest request = new HttpRequest(input);
			request.parseInput();
			fail();
		} catch (InvalidRequestException e) {
			// success
		}
	}
	
	/**
	 * Test invalid request first line
	 */
	@Test
	public void testInvalidFirstLine3() throws Exception {
		String inputStr = "GET  HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		try {
			HttpRequest request = new HttpRequest(input);
			request.parseInput();
			fail();
		} catch (InvalidRequestException e) {
			// success
		}
	}
	
	/**
	 * Test na cestu s mezerou
	 */
	@Test
	public void testPathWithSpace() throws Exception {
		String inputStr = "GET nejakacesta pokracovani HTTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		
		HttpRequest request = new HttpRequest(input);
		request.parseInput();
		
		assertEquals(request.getPath(), "nejakacesta pokracovani");
	}
	
	/**
	 * Test invalid header
	 */
	@Test
	public void testInvalidHeader() throws Exception {
		String inputStr = "GET / HTTP/1.0\nConnection: Keep-Alive\nUser-Agent Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		try {
			HttpRequest request = new HttpRequest(input);
			request.parseInput();
			
			fail();
		} catch (InvalidRequestException e) {
			// success
		}
	}
	
	/**
	 * Test empty request
	 * @throws Exception
	 */
	@Test
	public void testEmptyInput() throws Exception {
		String inputStr = "";
		Reader input = new StringReader(inputStr);
		
		try {
			HttpRequest request = new HttpRequest(input);
			request.parseInput();
			
			fail();
		} catch (EmptyRequestException e) {
			// success
		}
	}
	
	/**
	 * Otestovat zda je vyhozena vyjjimka, pokud neni jeste rozparsovan pozadavek
	 * @throws Exception 
	 */
	@Test
	public void requestIsNotParsed() throws Exception {
		String inputStr = "GETs / FTP/1.0\nConnection: Keep-Alive\nUser-Agent: Mozilla/4.7 [Fr] (Win98; I)\n\n";
		Reader input = new StringReader(inputStr);
		
		HttpRequest request = new HttpRequest(input);
		
		try {
			request.getMethod();
			fail();
		} catch (HttpRequestIsNotParsedException e) {
			// success
		}
		
		try {
			request.getVersion();
			fail();
		} catch (HttpRequestIsNotParsedException e) {
			// success
		}
		
		try {
			request.getPath();
			fail();
		} catch (HttpRequestIsNotParsedException e) {
			// success
		}
		
		try {
			request.getHeader("neco");
			fail();
		} catch (HttpRequestIsNotParsedException e) {
			// success
		}
	}
}
