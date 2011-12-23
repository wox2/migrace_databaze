package core.http.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Abstrakni rodicovska trida pro vsechny odpovedi serveru, ktere vyjadruji chybu 
 * @author quick
 *
 */
abstract public class HttpResponseError extends HttpResponse {
	
	/**
	 * Vraci http kod odpovedi
	 * @return
	 */
	abstract protected int getStatusCode();
	
	/**
	 * Vraci slovni http odpoved serveru
	 * @return
	 */
	abstract protected String getReasonPhrase();
	
	/**
	 * Vraci prvni radek odpovedi ve formatu:
	 * 	HTTP/1.0 numericky_kod slovni_odpoved
	 */
	protected String getStatusLine() {
		return "HTTP/1.0 "+ getStatusCode() + " "+ getReasonPhrase();
	}
	
	/**
	 * Vraci telo odpovedi
	 * @return
	 */
	protected String getBody() {
		return "<html><body><h1>"+ getStatusCode() +" - "+ getReasonPhrase() +"</h1></body></html>";
	}
	
	/**
	 * Do vystupniho streamu zapise odpoved serveru klientovi
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
