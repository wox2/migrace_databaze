package core.http.response;

/**
 * Odpoved serveru - Not Implemented
 * @author quick
 *
 */
public class HttpResponse501 extends HttpResponseError {
	
	protected String getReasonPhrase() {
		return "Not Implemented";
	}

	protected int getStatusCode() {
		return 501;
	}
}