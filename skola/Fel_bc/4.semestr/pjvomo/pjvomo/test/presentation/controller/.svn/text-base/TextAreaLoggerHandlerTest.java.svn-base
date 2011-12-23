package presentation.controller;

import static org.junit.Assert.assertTrue;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

import org.junit.Test;

public class TextAreaLoggerHandlerTest {
	@Test
	public void testLogger() throws Exception {
		JTextArea ta = new JTextArea();
		Handler h = new TextAreaLoggerHandler(ta);
		
		h.publish(new LogRecord(Level.INFO, "moje zprava"));
		h.publish(new LogRecord(Level.SEVERE, "moje druha zprava"));
		
		assertTrue(ta.getText().contains("INFO"));
		assertTrue(ta.getText().contains("moje zprava"));
		
		assertTrue(ta.getText().contains("SEVERE"));
		assertTrue(ta.getText().contains("moje druha zprava"));
	}
}
