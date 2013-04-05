package cz.XmlTester.Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.xml.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.*;


/**
 * Validates XML Schema. 
 * 
 * @author ju
 *
 */
public class XmlSchemaValid extends Test {

	/** File name of the file containing instance of the XML Schema */
	protected String xmlSchemaFileName = "data.xsd";

	/** Validator based on the {@code #xmlSchemaFileName} */
	protected Validator validator;
	
	
	@Override
	public boolean run() {
		setCurrentDirectory(basePath);
		
		boolean ret = true;
		ret &= loadXmlSchema(xmlSchemaFileName);
		ret &= new SyntaxRegex(xmlSchemaFileName, true, requirements, new String[] {"<!--"}, new String[] {"-->"}).run();
//		ret &= new SyntaxXpath(xmlSchemaFileName, true, requirementsXpath).run();
		return ret;
	}
	
	@Override
	public String getName() {
		return "Validate XML Schema";
	}

	/**
	 * Naète XML Schema, èímž zároveò provede jeho validaci.
	 * 
	 * @throws FileNotFoundException Pokud nebyl soubor se schématem nalezen.
	 * @throws SAXException Pokud se vyskytly chyby pøi zpracování schématu.
	 * 
	 */
	public boolean loadXmlSchema(String path) {
		// Handle validation
		try {
			SchemaFactory constraintFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI/**/);
			Schema schema = constraintFactory.newSchema(new File(path));
			validator = schema.newValidator();
			return true;
		} catch (SAXException e) {
			logger.log(Level.WARNING, e.toString());
			return false;
		}
	}
	

	/** Regular expression requirements on the XML Schema file. */
	private final static Requirement[] requirements = {
		new Requirement("<[^:]+:element", "Element definition"), 
		new Requirement("<[^:]+:attribute", "Attribute definition"), 
		new Requirement("<[^:]+:complexType", "Complex Type definition"), 
//		new Requirement("<[^:]+:complexContent", "Complex Content definition"),
		new Requirement("<[^:]+:simpleType", "Simple Type definition"),
//		new Requirement("<[^:]+:simpleContent", "Simple Content definition"),
		new Requirement("key[^r]", "XML Schema key Element"),
		new Requirement("keyref", "XML Schema keyref Element"),
//		new Requirement("<[^:]+:all", "XML Schema all Element"),
//		new Requirement("<[^:]+:list", "XML Schema list Element"),
//		new Requirement("<[^:]+:unique", "XML Schema unique Element"),
		new Requirement("<[^:]+:sequence", "XML Schema sequence Element"),
		new Requirement("<[^:]+:restriction|<[^:]+:extension", "XML Schema restriction or expansion Element")
	};

	/** 
	 * XPath expression requirements on the XML Schema file. 
	 * 
	 * TODO fix-this to match XML Schema spec.
	 */
	private final static Requirement[] requirementsXpath = {
		new Requirement("//xs:element", ""), 
		new Requirement("//xs:attribute", ""), 
		new Requirement("//xs:element/xs:complexType", ""), 
		new Requirement("//xs:element/xs:simpleType", ""),
		new Requirement("//xs:complexType/(xs:complexContent|xs:simpleContent)", ""),
		new Requirement("//xs:simpleType/(xs:restriction|xs:list|xs:union)", ""),
		new Requirement("//xs:simpleContent", ""),
		new Requirement("//xs:element/key", ""),
		new Requirement("//xs:element/keyref", ""),
		new Requirement("//xs:all", ""),
		new Requirement("//xs:list", ""),
		new Requirement("//xs:unique", ""),
		new Requirement("//xs:sequence", ""),
		new Requirement("//(xs:restriction|xs:extension)", "")
	};
}
