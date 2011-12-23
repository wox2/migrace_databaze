package cz.XmlTester.Tests;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.query.QueryResult;
import net.sf.saxon.trans.XPathException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.SourceLocator;

/**
 * Performs test of the XQueries.
 * 
 * @author ju
 *
 */
public class XQuery extends Test {

	/** Directory in the {@code #basePath} containing XQueries */ 
	public final static String xqueryBaseDir = "xquery";

	/** Regular expression specifying names of the files that contains XQueries. */ 
	public final static String xqueryFileName = "query\\d+.xq";
	
	/** Extension for the output files of the XQuery queries. */ 
	public final static String xqueryOutputExtension = ".out.xml";

	@Override
	public boolean run() {
		boolean ret = true;
		String xQueryPath = getFullPath(basePath, xqueryBaseDir);
		setCurrentDirectory(xQueryPath);

		File dir = new File(xQueryPath);
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
		StringBuilder str = new StringBuilder();
		StringBuilder fileNames = new StringBuilder();
		for (File file : files) {
			if (!file.getName().matches(xqueryFileName))
				continue;

			fileNames.append(file.getName()).append(", ");
			
			cnt++;
			File output = new File(file.getAbsolutePath() + xqueryOutputExtension);

			try {
				queryXQ(getFullPath(basePath, xmlFileName), file.getAbsolutePath(), output.getAbsolutePath());
				str.append(readFile(file.getAbsolutePath(), "UTF-8"));
			} catch (XPathException e) {
				SourceLocator sl = e.getLocator();
				if(sl == null) {
					logger.log(Level.WARNING,  "XQuery: " + file.getName()
							+ ", following error occured: " + e.toString());
				} else {
					logger.log(Level.WARNING, "XQuery: " + file.getName()
							+ ", following error occured: " + e.toString() + "(line "
						+ e.getLocator().getLineNumber() + ", column "
						+ e.getLocator().getColumnNumber() + ")");
				}
				ret &= false;
			} catch (IOException e) {
				logger.log(Level.WARNING, "XQuery: " + file.getName()
						+ ", following error occured: " + e.toString());
				ret &= false;
			}

			if(ret)
				logger.log(Level.INFO, "XQuery: " + file.getName()
					+ " finished successfully. Output written to file "
					+ output.getName() + " (" + output.length() + " bytes)");
		}

		if(fileNames.length() > 0)
			fileNames.delete(fileNames.length()-2, fileNames.length());	// umazat posledni carku a mezeru.
		ret &= new SyntaxRegex(fileNames.toString(), str.toString(), true, requirements, new String[] {"\\(\\:"}, new String[] {"\\:\\)"}).run();
		
		logger.log(Level.INFO, "Total " + cnt + " XQueries were processed.");
		if(cnt == 0)
			logger.log(Level.INFO, "Files containing XQuery queries should be named 'query[0-9].xq' and located in '" + xqueryBaseDir + "' directory.");

		return ret;
	}

	@Override
	public String getName() {
		return "XQuery";
	}

	/**
	 * Runs the XQuery on the xml file and stores the query output to the output file. 
	 * @param xmlFile  
	 * @param xQuery
	 * @param outputXmlFile
	 * @throws XPathException
	 * @throws IOException
	 */
	protected void queryXQ(String xmlFile, String xQuery, String outputXmlFile)
			throws XPathException, IOException {

		final Configuration config = new Configuration();
		final StaticQueryContext sqc = new StaticQueryContext(config);
		final XQueryExpression exp = sqc.compileQuery(new FileReader(xQuery));
		final DynamicQueryContext dynamicContext = new DynamicQueryContext(config);
		Properties props = new Properties();

		dynamicContext.setContextItem(sqc.buildDocument(new StreamSource(new File(xmlFile))));
		final SequenceIterator iter = exp.iterator(dynamicContext);
		
		// Nasledujici dva jsou pro XHTML vystup
		//props.setProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd");
		//props.setProperty(OutputKeys.METHOD, "html");
		
		// Nasledujici dva jsou pro XML vystup
		props.setProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		props.setProperty(OutputKeys.INDENT, "yes");
		OutputStream out = null;
		try {
			out = new FileOutputStream(outputXmlFile);
			QueryResult.serializeSequence(iter, config, out, props);
		} finally {
			if (out != null)
				out.close();
		}
	}




	/** Regular expression requirements on the XQuery files. */
	private final static Requirement[] requirements = {
		new Requirement("where.*?(min|max|avg|sum)", "min, max, avg or sum function"), 
		new Requirement("every.*?satisfies|some.*?satisfies", "every ... satisfies or some ... satisfies"), 
		new Requirement("distinct-values", "distinct-values"), 
		new Requirement("if.*then.*else", "if ... then ... else"),
	};

}
