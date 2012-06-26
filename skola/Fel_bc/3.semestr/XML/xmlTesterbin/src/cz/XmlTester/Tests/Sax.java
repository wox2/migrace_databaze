package cz.XmlTester.Tests;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Tests SAX handler implementation.
 * 
 * Compiles classes found in file {@value #saxFileName} and executes method
 * {@link #run()} in class {@value #saxClassName}.
 * 
 * @author ju
 * 
 */
public class Sax extends Java {

	/** The name of the class that implements {@link TestJava}. */ 
	public final static String saxClassNamePattern = "(TestSax[a-zA-Z]*)";

	/** Filename of the file containing #{@value #saxClassName}. */ 
	public final static Pattern saxFileNamePattern = Pattern.compile(saxClassNamePattern + "\\.java");
	
	/** Directory containing files with SAX handler implementation. */ 
	public final static String saxBaseDir = "sax";

	@Override
	public boolean run() {
		boolean ret = true;
		File dir = new File(basePath, saxBaseDir);
		setCurrentDirectory(dir.getAbsolutePath());
		
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
			Matcher matcher = saxFileNamePattern.matcher(file.getName());
			if (!matcher.matches())
				continue;
		
			logger.log(Level.INFO, "Checking file '" + file.getName() + "'");
			cnt++;

			// compile the code
			ret &= compileJavaCode(new File(file.getName()).getAbsolutePath());

			// run the code
			try {
				String saxClassName = matcher.group(1);
				ret &= runJavaCode(dir.toURL(), saxClassName);
			} catch (MalformedURLException e) {
				logger.log(Level.SEVERE, "Error:", e);
				ret = false;
			}
		}
		logger.log(Level.INFO, "Total " + cnt + " files were processed.");
		if(cnt == 0)
			logger.log(Level.INFO, "Files containing Test SAX classes should be named 'TestSax[a-zA-Z]*.java' and located in the directory 'sax' in project directory.");
		return ret;
	}
	
	@Override
	public String getName() {
		return "SAX";
	}

}
