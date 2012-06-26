package cz.XmlTester.Tests;

import java.io.IOException;
import java.util.logging.Level;

import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;


/**
 * Validates XML document against XML Schema. 
 * 
 * @author ju
 *
 */
public class XmlAgainstXmlSchema extends XmlSchemaValid {

	@Override
	public boolean run() {
		setCurrentDirectory(basePath);
		return validateXmlDocument();
	}

	
	@Override
	public String getName() {
		return "Validate XML document against XML Schema";
	}

	/**
	 * Validates XML document against XML Schema. 
	 * @return  
	 */
	public boolean validateXmlDocument() {
		// load XMLSchema
		loadXmlSchema(xmlSchemaFileName);
		boolean ret = true;
		try {
			validator.validate(new StreamSource(xmlFileName));
		} catch (SAXException e) {
			logger.log(Level.WARNING, e.toString());
			ret = false;
		} catch (IOException e) {
			logger.log(Level.WARNING, e.toString());
			ret = false;
		}

		return ret;
		
		/*
		// Get SAX Parser Factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// Turn on validation, and turn off namespaces
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		SAXParser parser = factory.newSAXParser();
		
		InputSource jis = new InputSource(new FileInputStream(getFullPath(path, xmlFileName)));
		parser.parse(jis, new MySaxHandler());
		*/
	}
}
