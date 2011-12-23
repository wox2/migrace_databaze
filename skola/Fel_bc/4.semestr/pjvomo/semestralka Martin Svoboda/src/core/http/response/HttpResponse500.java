package core.http.response;

/**
 * Odpoved serveru - Internal Server Error
 * @author quick
 *
 */
public class HttpResponse500 extends HttpResponseError {
	
	protected String getReasonPhrase() {
		return "Internal Server Error";
	}

	protected int getStatusCode() {
		return 500;
	}
}
