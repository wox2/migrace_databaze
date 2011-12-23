package core.http.response;

/**
 * Odpoved serveru - Not Found
 * @author quick
 *
 */
public class HttpResponse404 extends HttpResponseError {
	
	protected String getReasonPhrase() {
		return "Not Found";
	}

	protected int getStatusCode() {
		return 404;
	}
}
