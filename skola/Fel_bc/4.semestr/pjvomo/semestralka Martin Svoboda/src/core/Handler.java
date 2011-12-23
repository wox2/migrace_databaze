package core;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

/**
 * Obsluha pozadavku na server
 * @author quick
 *
 */
public class Handler implements Runnable, Cloneable {
	protected Socket socket;
	protected Reader input;
	protected BufferedOutputStream output;
	
	/**
	 * Obslouzeni pozadavku ve vlaknu
	 */
	public void run() {
		if (socket == null) {
			throw new RuntimeException("No socket. First call method setSocket().");
		}
	}
	
	/**
	 * Pripravi handler pro obslouzeni pozadavku
	 * @param socket
	 * @throws IOException
	 */
	public void setSocket(Socket socket) throws IOException {
		this.socket = socket;
		
		input = new InputStreamReader(socket.getInputStream());
		output = new BufferedOutputStream(socket.getOutputStream());
	}
	
	/**
	 * Implementace klonovani
	 */
	public Handler clone() {
		try {
			Handler clone = (Handler)super.clone();
			
			clone.socket = null;
			clone.input = null;
			clone.output = null;
			
			return clone;
		} catch (CloneNotSupportedException e) {
			// Class implements Cloneable, so nothing to do
			return null;
		}
	}
}
