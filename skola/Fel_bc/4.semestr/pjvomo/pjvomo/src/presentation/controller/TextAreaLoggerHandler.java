package presentation.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

/**
 * Zajistuje vypis logu do textoveho boxu v GUI
 * @author quick
 *
 */
public class TextAreaLoggerHandler extends Handler {

	/**
	 * Instance textoveho boxu, do ktereho bude vypisovat logovani
	 */
	protected JTextArea logTA;
	
	/**
	 * Konstruktor prijima instanci textoveho boxu, do ktereho bude vypisovat logovani
	 * @param textArea
	 */
	public TextAreaLoggerHandler(JTextArea textArea) {
		logTA = textArea;
	}
	
	@Override
	public void close() throws SecurityException {
		// Nothing to do
	}

	@Override
	public void flush() {
		// Nothing to do
	}

	/**
	 * Provede zapis do logu
	 */
	@Override
	public void publish(LogRecord record) {
		Date date = new Date(record.getMillis());
		
		logTA.append(record.getLevel().getName() +" "+ DateFormat.getInstance().format(date) +": "+ record.getMessage() + "\n");
		logTA.setCaretPosition(logTA.getText().length());
	}

}
