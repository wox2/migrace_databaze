package core.http.response;

import java.io.File;

import core.http.HttpRequest;

/**
 * Odpoved serveru 200 - OK
 * 
 * @author quick
 *
 */
abstract public class HttpResponse200 extends HttpResponse {
	/**
	 * Request na ktery se odpovida
	 */
	protected HttpRequest request;
	
	/**
	 * Soubor nebo adresar, ktery je pozadovan
	 */
	protected File file;
	
	public HttpResponse200(HttpRequest request, File file) {
		this.request = request;
		this.file = file;
	}
	
	/**
	 * Prvni radka odpovedi serveru ve formatu:
	 * HTTP/1.(0|1) 200 OK
	 */
	protected String getStatusLine() {
		return request.getVersion() +" 200 OK";
	}
}
