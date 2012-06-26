package cz.XmlTester.Tests;

import java.util.logging.Level;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * This test checks if the output of every XPath expression in
 * {@code #requirements} evaluated on the file {@code filename} contains at
 * least one node.
 * 
 * @author ju
 * 
 */
public class SyntaxXpath extends SyntaxRequirements {

	/**
	 * Creates the new instance of the {@code SyntaxXpath} class. 
	 * 
	 * @param fileName File to be checked.
	 * @param requirements List of the XPath expressions.
	 */
	public SyntaxXpath(String fileName, boolean isXml, Requirement[] requirements) {
		super(fileName, isXml, requirements);
	}
	
	
	@Override
	/**
	 * Checks if the output of every XPath expression in {@code #requirements}
	 * evaluated on the file {@code filename} contains at least one node.
	 */
	public boolean run() {
		boolean ret = true;
		for (Requirement requirement : requirements) {
			try {
				XPath xpath = XPathFactory.newInstance().newXPath();
				InputSource inputSource = new InputSource(fileName);
				NodeList nodes = (NodeList) xpath.evaluate(requirement.getRequirement(), inputSource, XPathConstants.NODESET);
				if(nodes.getLength() == 0) {
					logger.log(Level.WARNING, "Pattern '" + requirement.getDescription() + 
							"' not found in file " + fileName + ".");
					ret = false;
				}
			} catch (XPathExpressionException e) {
				logger.log(Level.WARNING, "XPath Error", e);
			}
		}

		return ret;
	}

	@Override
	public String getName() {
		return "Validate file against XPath Expression";
	}

}
