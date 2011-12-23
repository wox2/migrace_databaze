package core.http.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.http.HttpRequest;

public class HttpResponse200FileTest {
	
	protected HttpRequest mockRequest;
	protected OutputStream output;
	
	@After
    public void tearDown() {
        new File("test/tmp").delete();
        new File("test").delete();
    }
	
	@Before
	public void setUp() throws Exception {
		new File("test/tmp").mkdirs();
		
		mockRequest = new HttpRequest(new StringReader("test")) {
			
			public String getMethod() {
				return "GET";
			}
			
			public String getPath() {
				return "/test.htm";
			}
			
			public String getVersion() {
				return "HTTP/1.1";
			}
		};
		
		output = new OutputStream() {
			public String out = "";
			
			@Override
			public void write(int b) throws IOException {
				out += (char) b;
			}
			
			@Override
			public String toString() {
				return out;
			}
			
			@Override
			public void flush() {
				out = "";
			}
		};
	}
	
	/**
	 * Test hlavicky content type - typ souboru htm
	 * @throws Exception
	 */
	@Test
	public void testGetContentTypeHtml() throws Exception {
		File file = new File("test/tmp/test.htm");
		file.createNewFile();
		
		HttpResponse200File res = new HttpResponse200File(mockRequest, file);
		
		assertEquals("text/html", res.getContentType());
		
		file.delete();
	}
	
	/**
	 * Test hlavicky content type - Neznamy typ souboru (chybi pripona)
	 * @throws Exception
	 */
	@Test
	public void testGetNotExistingContentType() throws Exception {
		File file = new File("test/tmp/test");
		file.createNewFile();
		
		HttpResponse200File res = new HttpResponse200File(mockRequest, file);
		
		assertEquals("content/unknown", res.getContentType());
		
		file.delete();
	}
	
	/**
	 * Test hlavicky content type - Neznamy typ souboru (neznama pripona)
	 * @throws Exception
	 */
	@Test
	public void testGetNotUnknownContentType() throws Exception {
		File file = new File("test/tmp/test.xsk");
		file.createNewFile();
		
		HttpResponse200File res = new HttpResponse200File(mockRequest, file);
		
		assertEquals("content/unknown", res.getContentType());
		
		file.delete();
	}
	
	/**
	 * Testovani vypsani http hlavicky a obsahu souboru
	 * @throws Exception
	 */
	@Test
	public void testOut() throws Exception {
		
		String resHeader;
		resHeader = "HTTP/1.1 200 OK\r\n";
		resHeader += "Server: My Java Server\r\n";
		resHeader += "Content-Length: 42\r\n";
		resHeader += "Content-Type: text/plain\r\n\r\n";
		
		String resBody;
		resBody = "Nejaky obsah souboru\r\n";
		resBody += "Obsah na druhe radce";
		
		File file = new File("test/tmp/soubor.txt");
		file.createNewFile();
		
		FileWriter fis = new FileWriter(file);
		fis.write(resBody);
		fis.close();
		
		HttpResponse200File res = new HttpResponse200File(mockRequest, file);
		
		output.flush();
		res.out(output);
		
		assertEquals(resHeader + resBody, output.toString());
		
		file.delete();
	}
	
	/**
	 * Pokud soubor neexistuje je vracena odpoved HttpResponse404
	 * @throws Exception
	 */
	@Test
	public void testNotExistingFile() throws Exception {
		
		File file = new File("test/tmp/notexisting.txt");
		file.createNewFile();
		
		file.delete();
		
		HttpResponse200File res = new HttpResponse200File(mockRequest, file);
		
		output.flush();
		res.out(output);
		
		assertTrue(output.toString().contains("404 - Not Found"));
	}
}
