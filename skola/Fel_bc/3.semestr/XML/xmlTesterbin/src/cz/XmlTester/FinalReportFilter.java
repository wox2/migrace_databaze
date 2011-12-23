package cz.XmlTester;

import java.util.logging.Filter;
import java.util.logging.LogRecord;


/**
 * Messages that are not logged by the XmlTester logger are discarded. 
 * 
 * @author ju
 *
 */
public class FinalReportFilter implements Filter {
	public boolean isLoggable(LogRecord lr) {
		return lr.getLoggerName().equals(XmlTester.class.getName())
				&& lr.getMessage().contains("Final report"); 
	}
}