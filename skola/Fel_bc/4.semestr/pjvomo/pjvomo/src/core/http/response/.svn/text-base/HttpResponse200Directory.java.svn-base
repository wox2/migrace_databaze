package core.http.response;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import core.http.HttpRequest;

/**
 * Klient pozaduje adresar, tedy je mu vracen obsah adresare
 * @author quick
 *
 */
public class HttpResponse200Directory extends HttpResponse200 {
	
	/**
	 * Telo odpovedi serveru
	 */
	protected String body;
	
	public HttpResponse200Directory(HttpRequest request, File dir) {
		super(request, dir);
	}
	
	/**
	 * Vraci telo odpovedi
	 * @return
	 */
	protected String getBody() {
		if (body == null) {
			body = "<html>";
			body += "<body>";
			body += "<h1>Directory "+ request.getPath() +"</h1>\r\n";
			body += "<table>\r\n";
			
			String path = request.getPath();
			if (!request.getPath().endsWith("/")) {
				path += "/";
			}
			
			if (!path.equals("/")) {
				body += "<tr>\r\n";
				body += "<td>Dir</td>\r\n";
				body += "<td><a href='"+ path +"..'>..</a></td>\r\n";
				body += "</tr>\r\n";
			}
			
			try {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					body += "<tr>\r\n";
					body += "<td>"+ (files[i].isDirectory() ? "Dir" : "File") +"</td>\r\n";
					body += "<td><a href='"+ path + files[i].getName() +"'>"+ files[i].getName() +"</a></td>\r\n";
					body += "</tr>\r\n";
				}
				
			} catch (NullPointerException e) {
				body = new HttpResponse404().getBody();
				return body;
			}
			
			body += "</table>\r\n";
			body += "</body>";
			body += "</html>";
		}
		
		return body;
	}

	/**
	 * Vypise odpoved do vystupniho streamu
	 */
	public void out(OutputStream output) throws IOException {
		write(output, getStatusLine());
		write(output, getHeaderContentLength(getBody().length()));
		write(output, getHeaderContentType("text/html"));
		write(output, getHeaderServer());
		write(output, "");
		write(output, getBody());
	}
}
