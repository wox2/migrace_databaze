package core.http.response;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import core.http.HttpRequest;

/**
 * Vraci klientovi soubor
 * @author quick
 *
 */
public class HttpResponse200File extends HttpResponse200 {
	
	protected String header = "";
	protected static Map<String,String> extensions = new HashMap<String, String>();
	
	static {
		extensions.put("", "content/unknown");
		extensions.put(".uu", "application/octet-stream");
		extensions.put(".exe", "application/octet-stream");
	    extensions.put(".ps", "application/postscript");
	    extensions.put(".zip", "application/zip");
	    extensions.put(".sh", "application/x-shar");
	    extensions.put(".tar", "application/x-tar");
	    extensions.put(".snd", "audio/basic");
	    extensions.put(".au", "audio/basic");
	    extensions.put(".wav", "audio/x-wav");
	    extensions.put(".gif", "image/gif");
	    extensions.put(".jpg", "image/jpeg");
	    extensions.put(".jpeg", "image/jpeg");
	    extensions.put(".htm", "text/html");
	    extensions.put(".html", "text/html");
	    extensions.put(".text", "text/plain");
	    extensions.put(".c", "text/plain");
	    extensions.put(".cc", "text/plain");
	    extensions.put(".c++", "text/plain");
	    extensions.put(".h", "text/plain");
	    extensions.put(".pl", "text/plain");
	    extensions.put(".txt", "text/plain");
	    extensions.put(".java", "text/plain");
	}
	
	public HttpResponse200File(HttpRequest request, File file) {
		super(request, file);
	}
	
	/**
	 * Podle koncovky souboru vraci prislusny contentType
	 * Koncovka neni v seznamu, vraci content/unknown 
	 * @return
	 */
	public String getContentType() {
		int pos = file.getName().lastIndexOf(".");
		String ext = "";
		
		if (pos != -1) {
			ext = file.getName().substring(pos);
		}
		
		
		if (extensions.containsKey(ext)) {
			return extensions.get(ext);
		}
		
		return extensions.get("");
	}
	
	
	
	protected void appendHeader(String line) {
		header += line + "\r\n";
	}
	
	/**
	 * Zapise odpoved do vystupniho streamu
	 */
	public void out(OutputStream output) throws IOException {
		try {
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
			
			appendHeader(getStatusLine());
			appendHeader(getHeaderServer());
			appendHeader(getHeaderContentLength(file.length()));
			appendHeader(getHeaderContentType(getContentType()));
			appendHeader("");
			
			byte[] buffer = new byte[1024];
			int bytes = 0;
			while ((bytes = fis.read(buffer)) != -1) {
				
				if (header != null) {
					output.write(header.getBytes());
					header = null;
				}
				
				output.write(buffer, 0, bytes);
			}
		} catch (IOException e) {
			new HttpResponse404().out(output);
		}
	}
}