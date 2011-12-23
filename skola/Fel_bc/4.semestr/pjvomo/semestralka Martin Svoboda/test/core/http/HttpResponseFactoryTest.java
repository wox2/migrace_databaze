package core.http;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import core.Configuration;
import core.http.response.HttpResponse200Directory;
import core.http.response.HttpResponse200File;
import core.http.response.HttpResponse400;
import core.http.response.HttpResponse403;
import core.http.response.HttpResponse404;
import core.http.response.HttpResponse500;
import core.http.response.HttpResponse501;
import core.http.response.HttpResponsible;
import org.junit.After;

public class HttpResponseFactoryTest {
	
	@Before
	public void setUp() {
		Logger.getLogger("").setLevel(Level.OFF);
        new File("test/tmp").mkdirs();
	}

    @After
    public void tearDown() {
        new File("test/tmp").delete();
        new File("test").delete();
    }

	@Test
	public void testGetResponseFile() throws IOException {
		String root = new File("test/tmp").getCanonicalPath();
		File file = new File(root + "/file.htm");
		file.createNewFile();
		
		Configuration conf = Configuration.getInstance();
		conf.setRoot(root);
		
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
			@Override
            public void parseInput() {
				isParsed = true;
				method = "GET";
				path = "/file.htm";
				version = "HTTP1.0";
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse200File);
		
		file.delete();
	}
	
	@Test
	public void testGetResponseDirectory() throws IOException {
		Configuration conf = Configuration.getInstance();
		conf.setRoot(new File("test/tmp").getCanonicalPath());
		
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() {
				isParsed = true;
				method = "GET";
				path = "/";  // directory
				version = "HTTP1.0";
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse200Directory);
	}
	
	@Test
	public void testGetResponseNotFound() throws IOException {
		
		// delete test file if exists
		new File("test/tests/tmp/non-exist.htm").delete();
		
		Configuration conf = Configuration.getInstance();
		conf.setRoot(new File("test/tests/tmp").getCanonicalPath());
		
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() {
				isParsed = true;
				method = "GET";
				path = "/non-exist.htm";  // not existing file
				version = "HTTP1.0";
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse404);
	}
	
	@Test
	public void testGetResponseForbidden() throws IOException {
		
		Configuration conf = Configuration.getInstance();
		conf.setRoot(new File("test/tests/tmp").getCanonicalPath());
		
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() {
				isParsed = true;
				method = "GET";
				path = "/somepath/../../";  // out of root
				version = "HTTP1.0";
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse403);
	}
	
	@Test
	public void testGetResponseForbiddenNoPermission() throws Exception {
		String root = new File("test/tmp").getCanonicalPath();
		
		File file = new File(root + "/file.htm");
		file.createNewFile();
        
        if (file.setReadable(false)) {
            Configuration conf = Configuration.getInstance();
            conf.setRoot(root);

            HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
                @Override
                public void parseInput() {
                    isParsed = true;
                    method = "GET";
                    path = "/file.htm";
                    version = "HTTP1.0";
                }
            };

            HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);

            System.out.println(response.getClass().getName());

            assertTrue(response instanceof HttpResponse403);
        }
		
		file.delete();
	}
	
	/**
	 * Test POST - Not implementet request
	 */
	@Test
	public void testGetResponseNotImplemented() {
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() {
				isParsed = true;
				method = "POST";
				path = "somepath";
				version = "HTTP1.0";
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse501);
	}
	
	/**
	 * Test Factory to return HttpResponse400 on InvalidRequestException
	 */
	@Test
	public void testGetResponseBadRequest() {
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
			public void parseInput() throws InvalidRequestException {
				throw new InvalidRequestException();
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse400);
	}
	
	/**
	 * Test Factory to return HttpResponse400 on EmptyRequestException
	 */
	@Test
	public void testGetResponseEmptyRequest() {
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() throws EmptyRequestException {
				throw new EmptyRequestException();
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse400);
	}
	
	/**
	 * Test Factory to return HttpResponse500 on IOException
	 */
	@Test
	public void testGetResponseInternalServerError() {
		
		HttpRequest mockRequest = new HttpRequest(new StringReader("aaa")) {
            @Override
			public void parseInput() throws IOException {
				throw new IOException();
			}
		};
		
		HttpResponsible response = HttpResponseFactory.getResponse(mockRequest);
		
		assertTrue(response instanceof HttpResponse500);
	}
}
