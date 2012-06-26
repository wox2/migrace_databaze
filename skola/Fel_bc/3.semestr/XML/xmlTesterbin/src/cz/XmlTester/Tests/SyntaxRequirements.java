package cz.XmlTester.Tests;


/**
 * An abstract class that should be extended to implement a Test validating
 * source file to match a syntax requirements.
 * 
 * @author ju
 * 
 */
public abstract class SyntaxRequirements extends Test {

	/** Name of the file on which the test will be run. */ 
	protected String fileName;
	/**
	 * Whether or not the input file is XML file. If the input file is XML file,
	 * then the readXmlFile method is used to read the file into String
	 * representation considering proper character encoding. Otherwise, the
	 * readFile method is used.
	 */ 
	protected boolean isXml;

	/**
	 * Array of the requirements that will be checked. Requirement syntax
	 * depends on the class specialization.
	 */
	protected Requirement[] requirements;
	
	public SyntaxRequirements(String fileName, boolean isXml, Requirement[] requirements) {
		this.fileName = fileName;
		this.isXml = isXml;
		this.requirements = requirements;
	}
}
