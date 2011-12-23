package core.http;

import java.io.IOException;
import java.util.logging.Logger;

import core.Handler;
import core.http.response.HttpResponsible;


/**
 * Zpracovava pozadavek a vraci klientovi odpoved
 * @author quick
 *
 */
public class HttpHandler extends Handler {

	/**
	 * Pozadavek je zpracovan jako samostatne vlakno
	 */
	@Override
	public void run() {
		super.run();
		
		HttpRequest request = new HttpRequest(input);
		HttpResponsible response = HttpResponseFactory.getResponse(request);
		
		try {
			response.out(output);
			output.flush();
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			Logger.getLogger("webserver.core.http").severe(e.getMessage());
		}
	}
}
