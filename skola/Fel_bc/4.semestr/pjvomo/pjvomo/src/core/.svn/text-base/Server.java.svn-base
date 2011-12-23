package core;

// import java.io.IOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

/**
 * Trida obsluhujici server. Server pouziva navrhovy vzor prototype
 * pro urceni objektu Handler, ktery bude na odpovidat na pozadavky.
 * 
 * Nastaveni serveru je ulozeno ve tride Configuration, ktera vyuziva navrhovy vzor singleton 
 * 
 * @author quick
 *
 */
public class Server implements Runnable {

	protected ServerSocket serverSocket;
	protected Handler handlerPrototype;
	protected Thread thread;
	
	/**
	 * Konstruktor prijima handler, ktery bude obsluhovat pozadavky
	 * @param handlerPrototype
	 */
	public Server(Handler handlerPrototype) {
		this.handlerPrototype = handlerPrototype;
	}
	
	/**
	 * Vraci true, kdyz je server zapnuty, jinak false
	 * @return
	 */
	public boolean isAlive() {
		if (serverSocket != null) {
			return !serverSocket.isClosed();
		}
		return false;
	}
	
	/**
	 * Zapina server, vraci true pokud se podarilo zapnout server, jinak false
	 * @return
	 */
	public boolean start() {
		try {
			Configuration conf = Configuration.getInstance();
			
			if (conf.getIp() == null) {
				serverSocket = new ServerSocket(conf.getPort());
			} else {
				serverSocket = new ServerSocket(conf.getPort(), 10, conf.getIp());
			}
			
			thread = new Thread(this);
			thread.start();
			
		} catch (IOException e) {
			Logger.getLogger("webserver.core").warning(e.getMessage());
			return false;
		}
		
		Logger.getLogger("webserver.core").info("Server started");
		return true;
	}
	
	/**
	 * Vypina server, vraci true pokud se podarilo zapnout server, jinak false
	 * @return
	 */
	public boolean stop() {
		thread.interrupt();
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			Logger.getLogger("webserver.core").severe("Can`t close server socket");
			return false;
		}
		Logger.getLogger("webserver.core").info("Server stopped");
		return true;
	}
	
	/**
	 * Spusteni vlakna starajici se o vytvoreni noveho handleru pro obsluhu kazdeho pozadavku
	 */
	public void run() {
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				Handler handler = handlerPrototype.clone();
				
				handler.setSocket(clientSocket);
				
				Thread thread = new Thread(handler);
				thread.start();
			
			} catch (SocketException e) {
				// SocketException throwed when serverSocket is closed
				break;
			} catch (IOException e) {
				Logger.getLogger("webserver.core").severe(e.getMessage());
				break;
			}
		}
	}
}
