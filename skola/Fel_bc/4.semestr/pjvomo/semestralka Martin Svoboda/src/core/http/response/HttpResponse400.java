package core.http.response;

/**
 * Odpoved serveru - Bad Request
 * @author quick
 *
 */
public class HttpResponse400 extends HttpResponseError {
	protected String getReasonPhrase() {
		return "Bad Request";
	}

	protected int getStatusCode() {
		return 400;
	}
}
