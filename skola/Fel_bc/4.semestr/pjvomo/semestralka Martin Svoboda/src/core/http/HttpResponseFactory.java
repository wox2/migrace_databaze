package core.http;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import core.Configuration;
import core.http.response.HttpResponse200Directory;
import core.http.response.HttpResponse200File;
import core.http.response.HttpResponse400;
import core.http.response.HttpResponse403;
import core.http.response.HttpResponse404;
import core.http.response.HttpResponse500;
import core.http.response.HttpResponse501;
import core.http.response.HttpResponsible;

/**
 * Stara se o vraceni spravne odpovedi na klientuv pozadavek
 * @author quick
 *
 */
public class HttpResponseFactory {
	/**
	 * Vraci odpoved na klientuv pozadavek
	 * @param input
	 * @return
	 */
	public static HttpResponsible getResponse(HttpRequest request) {
		try {
			request.parseInput();
			
			String method = request.getMethod();
			String path = request.getPath();
			
			Logger.getLogger("webserver.core.http").info("Request on "+ path);
			
			Configuration conf = Configuration.getInstance();
			String root = conf.getRoot();
			
			File file = new File(root, path);
			
			if (!method.equals("GET") && !method.equals("HEAD")) {
				// 501 - Not Implemented
				return new HttpResponse501();
			} else {
				if (!file.getCanonicalPath().startsWith(root)) {
					// 403 - Forbidden
					return new HttpResponse403();
				}
				
				if (!file.exists()) {
					// 404 - Not find
					return new HttpResponse404();
				}
				
				if (!file.canRead()) {
					// 403 - Forbidden
					return new HttpResponse403();
				}
				
				if (file.isDirectory()) {
					return new HttpResponse200Directory(request, file);
				} else {
					return new HttpResponse200File(request, file);
				}
			}
		} catch (IOException e) {
			return new HttpResponse500();
		} catch (InvalidRequestException e) {
			return new HttpResponse400();
		} catch (EmptyRequestException e) {
			return new HttpResponse400();
		}
	}
}
