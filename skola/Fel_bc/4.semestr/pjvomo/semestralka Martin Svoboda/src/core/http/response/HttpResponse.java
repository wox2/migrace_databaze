package core.http.response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Abstraktni trida zajistujici zakladni funkcionality pro obslouzeni klienta
 * @author quick
 *
 */
abstract public class HttpResponse implements HttpResponsible {
	
	/**
	 * Jmeno serveru
	 */
	protected static final String SERVER_NAME = "My Java Server";
	
	/**
	 * Vraci prvni radek odpovedi
	 * @return
	 */
	abstract protected String getStatusLine();
	
	/**
	 * Vraci hlavicku pro urceni jmena serveru
	 * @return
	 */
	protected String getHeaderServer() {
		return "Server: "+ SERVER_NAME;
	}
	
	/**
	 * Vraci hlavicku odpovedi, ktera urcuje jake data se prenaseji
	 * @param type
	 * @return
	 */
	protected String getHeaderContentType(String type) {
		return "Content-Type: "+ type;
	}
	
	/**
	 * Vraci hlavicku odpovedi urcujici delku tela odpovedi
	 * @param length
	 * @return
	 */
	protected String getHeaderContentLength(long length) {
		return "Content-Length: "+ length;
	}

	/**
	 * Vypise do outputu textu, na konec prida ukonceni radku \r\n
	 * @param output vystup do ktereho se bude zapisovat
	 * @param content co se bude do proudu zapisovat
	 * @throws IOException
	 */
	protected void write(OutputStream output, String content) throws IOException {
		output.write((content +"\r\n").getBytes());
	}
}
