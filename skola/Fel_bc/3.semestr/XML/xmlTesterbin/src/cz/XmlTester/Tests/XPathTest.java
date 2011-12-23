package cz.XmlTester.Tests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

/**
 * Performs test of the XQueries.
 * 
 * @author ju
 * 
 */
public class XPathTest extends Test {

	/** Directory in the {@code #basePath} containing XPath expressions */
	public final static String xpathBaseDir = "xpath";

	/**
	 * Regular expression specifying names of the files that contains XPath
	 * expressions.
	 */
	public final static String xpathFileName = "xpath\\d.xp";

	/**
	 * Filename of the file containing encoding of the files containing XPath expressions.
	 *
	 * NOT USED YET.
	 */
	public final static String charsetFileName = "charset.xp";
	
	/** Extension for the output files of the XPath expressions. */
	public final static String xpathOutputExtension = ".out.xml";

	@Override
	public boolean run() {
		boolean ret = true;
		String xPathPath = getFullPath(basePath, xpathBaseDir);
		setCurrentDirectory(xPathPath);

		File dir = new File(xPathPath);
		if (!dir.exists()) {
			logger.log(Level.WARNING, "Path " + dir.getAbsolutePath() + " does not exist.");
			return false;
		}
		if (!dir.isDirectory()) {
			logger.log(Level.WARNING, "Path " + dir.getAbsolutePath() + " is not directory.");
			return false;
		}

		File[] files = dir.listFiles();
		int cnt = 0;
		String charset = "UTF-8";//getCharset(getFullPath(dir.getAbsolutePath(), charsetFileName));
		for (File file : files) {
			if (!file.getName().matches(xpathFileName))
				continue;

			cnt++;

			try {
				// read XPath expression from the file 
				String xPathExpr = readFile(getFullPath(dir.getAbsolutePath(), file.getName()), charset);				
				
				// evaluate XPath expression on the xml file 
				XPath xpath = XPathFactory.newInstance().newXPath();
				InputSource inputSource = new InputSource(new File(getFullPath(basePath, xmlFileName)).toURI().toString());
				List nodes = (List) xpath.evaluate(xPathExpr, inputSource, XPathConstants.NODESET);
				logger.log(Level.INFO, "XPath expression '" + xPathExpr + 
							"' in file '" + file.getName() + "' finished successfully. " + nodes.size() + " nodes were selected.");

			} catch (IOException e) {
				logger.log(Level.WARNING, e.getMessage(), e);
				ret &= false;
			} catch (XPathExpressionException e) {
				logger.log(Level.WARNING, "Error in file '" + file.getName() + "': " + e.toString() + 
						", Caused by: " + e.getCause().toString());
				ret &= false;
			}

		}
		logger.log(Level.INFO, "Total " + cnt + " XPath expressions were processed.");
		if(cnt == 0)
			logger.log(Level.INFO, "Files containing XPath expressions should be named 'xpath[0-9].xp' and located in 'xpath' directory.");

		return ret;
	}

	@Override
	public String getName() {
		return "XPath";
	}
}
