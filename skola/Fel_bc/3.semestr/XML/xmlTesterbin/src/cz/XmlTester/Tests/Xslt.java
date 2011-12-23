package cz.XmlTester.Tests;

import java.io.File;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

//import org.apache.xalan.xslt.*;
import net.sf.saxon.TransformerFactoryImpl;


/**
 * Validates XSL Transformation and performs it on the {@code xmlFileName}. 
 * 
 * @author ju
 *
 */
public class Xslt extends Test {

	/** Name of the file containing xsl transformations in the {@code basePath}. */
	public static final Pattern xsltFileNamePattern = Pattern.compile("(data2)(\\w+)\\.xsl");

	@Override
	public boolean run() {
		setCurrentDirectory(basePath);
		boolean ret = true;

		File dir = new File(basePath);
		if (!dir.exists()) {
			logger.log(Level.WARNING, "Path " + dir.getAbsolutePath() + "does not exist.");
			return false;
		}
		if (!dir.isDirectory()) {
			logger.log(Level.WARNING, "Path " + dir.getAbsolutePath() + "is not directory.");
			return false;
		}

		File[] files = dir.listFiles();
		int cnt = 0;
		for (File file : files) {
			Matcher matcher = xsltFileNamePattern.matcher(file.getName());
			if (!matcher.matches())
				continue;

			cnt++;
			String outputFileName = matcher.group(1)+matcher.group(2)+"."+matcher.group(2);
			File xsltFileOut = new File(outputFileName);

			Source xmlSource = new StreamSource(xmlFileName);
			Source xsltSource = new StreamSource(file);
			Result result = new StreamResult(xsltFileOut);

			// create an instance of TransformerFactory
			try {
//				String xmlSystemId = new File(xmlFileName).toURL().toExternalForm(  );
//	        	String xsltSystemId = new File(xsltFileName).toURL().toExternalForm(  );

				/* XALAN
		        XSLTProcessor processor = XSLTProcessorFactory.getProcessor(  );
		        XSLTInputSource xmlInputSource = new XSLTInputSource(xmlSystemId);
		        XSLTInputSource xsltInputSource = new XSLTInputSource(xsltSystemId);
		        XSLTResultTarget resultTree = new XSLTResultTarget(System.out);
		        processor.process(xmlInputSource, xsltInputSource, resultTree);
				/**/

				/* SAXON */
				// create a new compiled stylesheet
				TransformerFactoryImpl factory = new TransformerFactoryImpl();
				Templates templates = factory.newTemplates(xsltSource);
				// create a transformer that can be used for a single transformation
				Transformer inst = templates.newTransformer();
				inst.transform(xmlSource, result);
				/**/

				/* JAVA native
		        // nefunguje funkce day-from-dateTime()
		        TransformerFactory transFact = TransformerFactory.newInstance();
		        Transformer trans = transFact.newTransformer(xsltSource);
				trans.transform(xmlSource, result);
				/**/

				logger.log(Level.INFO, "XSLT Transformation " + file.getName() + " SUCCESSFUL");
			} catch (TransformerException e) {
				logger.log(Level.WARNING, "XSLT Transformation " + file.getName() + " error: " + e.toString());
				ret = false;
			}

			ret &= new SyntaxRegex(file.getAbsolutePath(), true, requirements, new String[] {"<!--"}, new String[] {"-->"}).run();
			//ret &= new SyntaxXpath(file.getAbsolutePath(), true, requirementsXpath).run();
		}
		logger.log(Level.INFO, "Total " + cnt + " XSL transformations were processed.");
		if(cnt == 0)
			logger.log(Level.INFO, "Files containing XSL transformations should be named 'data2\\w+.xsl' and located in the project directory.");
		return ret;
	}

	@Override
	public String getName() {
		return "XSLT";
	}


	/** Regular expression requirements on the XSLT file. */
	private final static Requirement[] requirements = {
		new Requirement("<[^:]+:apply-templates|<[^:]+:call-template", "XSLT <[^:]+:apply-templates> or <[^:]+:call-template> Element"), 
		new Requirement("<[^:]+:template\\s+match=", "XSLT <[^:]+:template match> Element"), 
		new Requirement("<[^:]+:template\\s+name=", "XSLT <[^:]+:template name> Element"), 
		new Requirement("<[^:]+:if|<[^:]+:choose|<[^:]+:when", "XSLT <[^:]+:if>, <[^:]+:when> or <[^:]+:choose> Element"),
		new Requirement("<[^:]+:for-each", "XSLT <[^:]+:for-each> Element"),
		new Requirement("<[^:]+:variable", "XSLT <[^:]+:variable> Element"),
		new Requirement("<[^:]+:param", "XSLT <[^:]+:param> Element"),
		new Requirement("<[^:]+:with-param", "XSLT <[^:]+:with-param> Element"),
		new Requirement("<[^:]+:element", "XSLT <[^:]+:element> Element"),
		new Requirement("<[^:]+:attribute", "XSLT <[^:]+:attribute> Element"),
		new Requirement("<[^:]+:value-of", "XSLT <[^:]+:value-of> Element"),
		new Requirement("<[^:]+:text", "XSLT <[^:]+:text> Element"),
		new Requirement("<[^:]+:copy[^-]", "XSLT <[^:]+:copy> Element"),
		new Requirement("<[^:]+:copy-of", "XSLT <[^:]+:copy-of> Element"),
	};

	/**
	 * XPath expression requirements on the XSLT file.
	 *  
	 * TODO fix-this to match XSLT spec.
	 */
	private final static Requirement[] requirementsXpath = {
		new Requirement("//xsl:apply-templates|//xsl:call-template", "XSLT <xsl:apply-templates> or <xsl:call-template> Element"), 
		new Requirement("//xsl:template[@match]", "XSLT <xsl:template match=\"...\"> Element"), 
		new Requirement("//xsl:template[@name]", "XSLT <xsl:template name=\"...\"> Element"), 
		new Requirement("//xsl:if|//xsl:choose|//xsl:when", "XSLT <xsl:if>, <xsl:choose> or <xsl:when> Element"),
		new Requirement("//xsl:for-each", "XSLT <xsl:for-each> Element"),
		new Requirement("//xsl:variable", "XSLT <xsl:variable> Element"),
		new Requirement("//xsl:param", "XSLT <xsl:param> Element"),
		new Requirement("//xsl:with-param", "XSLT <xsl:with-param> Element"),
		new Requirement("//xsl:element", "XSLT <xsl:element> Element"),
		new Requirement("//xsl:attribute", "XSLT <xsl:attribute> Element"),
		new Requirement("//xsl:value-of", "XSLT <xsl:value-of> Element"),
		new Requirement("//xsl:text", "XSLT <xsl:etxt> Element"),
		new Requirement("//xsl:copy", "XSLT <xsl:copy> Element"),
		new Requirement("//xsl:copy-of", "XSLT <xsl:copy-of> Element"),
		new Requirement("//xsl:apply-templates[@select]", "XSLT <xsl:apply-templates> Element")
	};

}
