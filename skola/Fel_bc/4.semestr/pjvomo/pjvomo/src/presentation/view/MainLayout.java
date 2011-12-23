package presentation.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import presentation.controller.SelectDirectoryAction;
import presentation.controller.StartStopServerAction;
import presentation.controller.TextAreaLoggerHandler;


/**
 * Vytvari GUI pro obsluhu serveru
 * @author quick
 *
 */
public class MainLayout extends JFrame {
	private static final long serialVersionUID = -56761231932072671L;
	
	public JButton startStopBt;
	public JTextField ipTF;
	public JTextField portTF;
	public JTextField rootTF;
	public JTextArea logTA;
	public JButton fileDialogBt;
	
	/**
	 * Spousti aplikaci
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new MainLayout();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * Vytvori zakladni layout aplikace
	 */
	public MainLayout() {
		super("Webserver");
		
		createItems();
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridBagLayout());
		setContentPane(pane);
				
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		Insets insetsLeft = new Insets(5, 50, 5, 5);
		Insets insetsRight = new Insets(5, 5, 5, 20);
		Insets insetsCenter = new Insets(5, 5, 5, 5);
		
		c.weightx = 20;
		c.insets = new Insets(20, 20, 20, 20);
		add(new JLabel("Preferences for webserver:"), c);
		
		c.gridy = 1;
		c.gridx = 0;
		c.insets = insetsLeft;
		c.weightx = 20;
		add(new JLabel("IP"), c);
		c.gridx = 1;
		c.insets = insetsCenter;
		c.weightx = 70;
		add(ipTF, c);
		
		
		c.gridy = 2;
		c.gridx = 0;
		c.insets = insetsLeft;
		c.weightx = 0;
		add(new JLabel("Port"), c);
		c.gridx = 1;
		c.insets = insetsCenter;
		add(portTF, c);
		
		c.gridy = 3;
		c.gridx = 0;
		c.insets = insetsLeft;
		add(new JLabel("Web. root"), c);
		c.gridx = 1;
		c.insets = insetsCenter;
		add(rootTF, c);
		c.gridx = 2;
		c.insets = insetsRight;
		add(fileDialogBt, c);
		
		c.gridy = 4;
		c.gridx = 1;
		c.insets = insetsCenter;
		add(startStopBt, c);
		
		c.gridy = 5;
		c.gridx = 0;
		c.gridwidth = 3;
		c.gridheight = 10;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		add(new JScrollPane(logTA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
	}
	
	/**
	 * Vytvori prvky aplikace
	 */
	protected void createItems() {
		ipTF = new JTextField("");
		portTF = new JTextField("8080");
		rootTF = new JTextField("");
		logTA = new JTextArea("", 10, 40);
		logTA.setEditable(false);
		
		Logger logger = Logger.getLogger("webserver");
		logger.addHandler(new TextAreaLoggerHandler(logTA));
		logger.setLevel(Level.ALL);
		
		startStopBt = new JButton("Start/Stop");
		startStopBt.addActionListener(new StartStopServerAction(this));
		
		fileDialogBt = new JButton("...");
		fileDialogBt.addActionListener(new SelectDirectoryAction(rootTF));
	}
}
