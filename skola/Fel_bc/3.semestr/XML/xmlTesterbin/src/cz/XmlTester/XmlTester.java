package cz.XmlTester;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.XmlTester.Tests.*;


/**
 * Main class of the XmlTester. Runs all the test on the XML projects. 
 * 
 * @author ju
 *
 */
public class XmlTester {

	private static Logger logger = Logger.getLogger(XmlTester.class.getName());
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// vypis informaci o systemu
		logger.log(Level.INFO, "System info:");
		Properties sp = System.getProperties();
		Set<String> s = sp.stringPropertyNames();
		for (String property : s) {
			logger.log(Level.INFO, property + "=" + sp.getProperty(property));
		}

		// zpracovani argumentu prikazoveho radku
		if(args.length != 1) {
			logger.log(Level.SEVERE, "Path to .zip file or directory " +
				"should be specified as argument.");
			return;
		} else if(!(new File(args[0]).exists())) {
			logger.log(Level.SEVERE, "Path '" + args[0] + "' not found.");
			return;
		}
		
		String basePath = args[0];
		File baseDir = new File(basePath);
		
		// extract the zip archive contents
		if(baseDir.isFile()) {
			File zipFile = baseDir;
			int zipIdx = basePath.lastIndexOf(".zip");
			if(zipIdx == -1) {
				logger.log(Level.SEVERE, "File " + args[0] + " does not seems to be valid zip file.");
				return;
			}
			basePath = basePath.substring(0, zipIdx) + File.separator;
			baseDir = new File(basePath);
			baseDir.mkdir();
			new ZipArchive().getZipFiles(zipFile.getAbsolutePath(), baseDir.getAbsolutePath());

			if(!baseDir.exists()) {
				logger.log(Level.SEVERE, "Zip file " + args[0] + " extraction was not successfull.");
				return;
			}
		} else if(!baseDir.isDirectory()) {
			logger.log(Level.SEVERE, "Path " + args[0] + " is not a file nor a directory.");
			return;
		}

		// instantiate test classes
		logger.log(Level.INFO, "Running XmlTester on project path: "+ baseDir.getAbsolutePath());
		logger.log(Level.INFO, "Validation details can be found in logfile.");
		Test.setBasePath(baseDir.getAbsolutePath());
		
		List<Test> tests = new ArrayList<Test>();
		tests.add(new XmlValid());
		tests.add(new DtdValid());
		tests.add(new XmlAgainstDtd());
		tests.add(new XPathTest());
		tests.add(new XmlSchemaValid());
		tests.add(new XmlAgainstXmlSchema());
		tests.add(new Xslt());
		tests.add(new XQuery());
		tests.add(new Sax());
		tests.add(new Dom());
		
		// run tests
		for(Test test : tests) {
			try {
				logger.log(Level.INFO, "===========================================================================");
				logger.log(Level.INFO, "Executing test '" + test.getName() + "'");
				if(test.run()) {
					logger.log(Level.INFO, "Final report: Test '" + test.getName() + "' completed SUCCESSFULY!");
				} else {
					logger.log(Level.INFO, "Final report: Test '" + test.getName() + "' FAILED!");
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, "Final report: Test '" + test.getName() + "' FAILED! " + e.getMessage(), e);
			} catch (Error e) {
				logger.log(Level.SEVERE, "Final report: Test '" + test.getName() + "' FAILED! " + e.getMessage(), e);
			} finally {
				logger.log(Level.INFO, "===========================================================================\n");
			}
		}
		logger.log(Level.INFO, "Running XmlTester on project path: "+ baseDir.getAbsolutePath() + " completed.");

	}

}
