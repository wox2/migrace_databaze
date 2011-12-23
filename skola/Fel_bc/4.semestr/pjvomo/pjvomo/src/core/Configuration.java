package core;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Configurace serveru, vyuziva vzor singleton 
 * @author quick
 *
 */
public class Configuration {
	private static Configuration instance;
	private InetAddress ip;
	private int port;
	private String root;
	
	/**
	 * Privatni konstruktor, tridu lze vytvorit pouze pres getInstance()
	 */
	private Configuration() {
		
	}
	
	/**
	 * Vraci instanci tridy Configuration
	 * @return
	 */
	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		
		return instance;
	}
	
	/**
	 * Nastavi root serveru
	 * @param root
	 */
	public void setRoot(String root) {
		this.root = root;
	}
	
	/**
	 * Vraci root serveru
	 * @return
	 */
	public String getRoot() {
		return root;
	}
	
	/**
	 * Nastavi ip adresu serveru
	 * @param ip
	 */
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	/**
	 * Nastavi ip adresu serveru
	 * @param ip
	 * @throws UnknownHostException
	 */
	public void setIp(String ip) throws UnknownHostException {
		this.ip = InetAddress.getByName(ip);
	}
	
	/**
	 * Vraci ip adresu serveru
	 * @return
	 */
	public InetAddress getIp() {
		return ip;
	}
	
	/**
	 * Nastavi port serveru
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Nastavi port serveru
	 * @param port
	 */
	public void setPort(String port) {
		this.port = Integer.parseInt(port);
	}
	
	/**
	 * Vraci port serveru
	 * @return
	 */
	public int getPort() {
		return port;
	}
	
}
