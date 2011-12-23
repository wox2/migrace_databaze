package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import presentation.view.MainLayout;
import core.Configuration;
import core.Server;
import core.http.HttpHandler;

/**
 * Trida zpracovava udalost na tlacitku Start/Stop server
 * @author quick
 *
 */
public class StartStopServerAction implements ActionListener {

	/**
	 * Instance layoutu 
	 */
	protected MainLayout layout;
	
	/**
	 * Instance spusteneho serveru
	 */
	protected Server server;
	
	/**
	 * Konstruktor musi obsahovat instanci layoutu 
	 * @param layout
	 */
	public StartStopServerAction(MainLayout layout) {
		this.layout = layout;
	}
	
	/**
	 * Vraci instanci serveru
	 * @return
	 */
	protected Server getServer() {
		if (server == null) {
			server = new Server(new HttpHandler());
		}
		
		return server;
	}
	
	/**
	 * Zpracovava akci onlick na tlacitku start/stop
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (getServer().isAlive()) {
			stopServer();
		} else {
			startServer();
		}
	}
	
	/**
	 * Provede zapnuti serveru
	 */
	protected void startServer() {
		
		if (layout.portTF.getText().length() == 0) {
			Logger.getLogger("webserver.core").warning("Please input port field");
			return ;
		}
		
		if (layout.rootTF.getText().length() == 0) {
			Logger.getLogger("webserver.core").warning("Please input web root field");
			return ;
		}
		
		try {
			Configuration conf = Configuration.getInstance();
			conf.setRoot(layout.rootTF.getText());
			conf.setIp(layout.ipTF.getText());
			conf.setPort(layout.portTF.getText());
		} catch (UnknownHostException e) {
			Logger.getLogger("webserver.core").warning("Unknown IP address");
		}
		
		if (getServer().start()) {
			layout.rootTF.setEditable(false);
			layout.ipTF.setEditable(false);
			layout.portTF.setEditable(false);
		}
	}
	
	/**
	 * Provede vypnuti serveru
	 */
	protected void stopServer() {
		if (getServer().stop()) {
			layout.rootTF.setEditable(true);
			layout.ipTF.setEditable(true);
			layout.portTF.setEditable(true);
		}
	}
}
