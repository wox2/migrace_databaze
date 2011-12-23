package cz.XmlTester;

import java.util.logging.Logger;


/**
 * Any class that should be validated by the cz.XmlTester.Tests.Java should
 * extend this abstract class.
 * 
 * Debug information should be printed out using {@code logger.info(message);}.
 * 
 * @author ju
 * 
 */
public abstract class TestJava {
	
	protected static Logger logger = Logger.getLogger(TestJava.class.getName());
	/**
	 * This method does all the work. 
	 */
	public abstract void run();
}
