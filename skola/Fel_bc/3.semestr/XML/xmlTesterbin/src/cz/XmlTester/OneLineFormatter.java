package cz.XmlTester;
import java.util.logging.LogRecord;

/**
 * Log message formatter. Only the log level and the log message are printed
 * out. The log time and logger name are discarded.
 * 
 * @author ju
 * 
 */
public class OneLineFormatter extends java.util.logging.Formatter {
  public String format(LogRecord record) {

		StringBuffer buf = new StringBuffer(180);
		buf.append(record.getLevel());
		buf.append(": ");
		buf.append(formatMessage(record));

		if (record.getThrown() != null) {
			final Throwable thrown = record.getThrown();
			if (thrown != null) {
				final StackTraceElement[] ste = thrown.getStackTrace();
				for (int i = 0; i < ste.length; i++) {
					buf.append("\n").append(ste[i]);
				}
			}
		}
		buf.append("\n");
		
		return buf.toString();
	}
}
