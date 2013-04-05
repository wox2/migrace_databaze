package cz.XmlTester.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



/**
 * Validates XSL Transformation and performs it on the {@code xmlFileName}. 
 * 
 * @author ju
 *
 */
public class XmlAgainstDtd extends DtdValid {

	@Override
	public boolean run() {
		boolean ret = true;
		setCurrentDirectory(basePath);
		try {
			validateXmlDocument();
		} catch (Exception e) {
			ret = false;
			logger.log(Level.WARNING, e.toString());
		}
		return ret;
	}

	@Override
	public String getName() {
		return "Validate XML document against DTD";
	}


	/**
	 * Validates XML document against DTD
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void validateXmlDocument() throws ParserConfigurationException, SAXException, IOException, Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true); 
		factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		MyErrorHandler handler = new MyErrorHandler();
		builder.setErrorHandler(handler);
		builder.parse(xmlFileName);
		if(handler.errorOccured()) {
			throw new Exception(handler.getErrors());
		}
		
	}

	
	
	private static class MyErrorHandler implements ErrorHandler {
		private List<String> errors = new ArrayList<String>();
		
		public void warning(SAXParseException e) throws SAXException {
			errors.add("Warning: " + getInfo(e));
		}

		public void error(SAXParseException e) throws SAXException {
			errors.add("Error: " + getInfo(e));
		}

		public void fatalError(SAXParseException e) throws SAXException {
			errors.add("Fatal error: " + getInfo(e));
		}

		public String getErrors() {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < errors.size(); i++) {
				str.append(errors.get(i));
				if(i < errors.size()-1)
					str.append("\n");
			}
			return str.toString();
		}
		
		public boolean errorOccured() {
			return (errors.size() > 0);
		}
		
		private String getInfo(SAXParseException e) {
			return "Line number: " + e.getLineNumber() +
					", Column number: " + e.getColumnNumber() +
					", Message: " + e.getMessage();
		}
	}
	
}