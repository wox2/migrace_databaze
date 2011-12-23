package core.http.response;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.http.HttpRequest;

public class HttpResponse200DirectoryTest {
	
	protected HttpRequest mockRequest;
	protected OutputStream output;
	protected HttpResponse200Directory res;
	protected String resBody;
	protected File myDir;
	
	@Before
	public void setUp() throws Exception {
		new File("test/tmp").mkdirs();
		
		mockRequest = new HttpRequest(new StringReader("test")) {
			
			public String getMethod() {
				return "GET";
			}
			
			public String getPath() {
				return "/mydir";
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
			
			public String toString() {
				return out;
			}
			
			public void flush() {
				out = "";
			}
		};
		
		
		myDir = new File("test/tmp/mydir");
		myDir.mkdir();
		
		new File(myDir, "subdir1").mkdir();
		new File(myDir, "subdir2").mkdir();
		new File(myDir, "file1.txt").createNewFile();
		new File(myDir, "file2.htm").createNewFile();
		new File(myDir, "file3.jpg").createNewFile();
		new File(myDir, "file4.png").createNewFile();
		
		res = new HttpResponse200Directory(mockRequest, myDir);
	}
	
	@After
	public void tearDown() {
		File[] files = myDir.listFiles();
		for(int i = 0; i < files.length; i++) {
			files[i].delete();
		}
		
		myDir.delete();
		new File("test/tmp").delete();
        new File("test").delete();
	}
	
	/**
	 * Vypsani obsahu adresare
	 * @throws Exception
	 */
	@Test
	public void testGetBody() throws Exception {
		// nekontroluji presne poradi vypsanych adresaru a souboru
		assertTrue(res.getBody().contains("<h1>Directory /mydir</h1>"));
		assertTrue(res.getBody().contains("<a href='/mydir/..'>..</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/subdir1'>subdir1</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/subdir2'>subdir2</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/file1.txt'>file1.txt</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/file2.htm'>file2.htm</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/file3.jpg'>file3.jpg</a>"));
		assertTrue(res.getBody().contains("<a href='/mydir/file4.png'>file4.png</a>"));
	}
	
	/**
	 * Vraceni odpoved, http hlavicka + telo odpovedi
	 * @throws Exception
	 */
	@Test
	public void testOut() throws Exception {
		res.out(output);
		
		String resHeader;
		resHeader = "HTTP/1.1 200 OK\r\n";
		resHeader += "Content-Length: 600\r\n";
		resHeader += "Content-Type: text/html\r\n";
		resHeader += "Server: My Java Server\r\n\r\n";
		
		assertTrue(output.toString().startsWith(resHeader));
	}
	
	/**
	 * Pokud adresar neexistuje je vracena odpoved HttpResponse404
	 * @throws Exception
	 */
	@Test
	public void testNotExistingDir() throws Exception {
		File file = new File("test/tmp/notexisting.txt");
		file.delete();
		
		HttpResponse200Directory res = new HttpResponse200Directory(mockRequest, file);
		
		output.flush();
		res.out(output);
		
		assertTrue(output.toString().contains("404 - Not Found"));
	}
}
