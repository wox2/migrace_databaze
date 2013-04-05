package cz.XmlTester.Tests;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Tests DOM handler implementation.
 * 
 * Compiles classes found in file {@value #domFileName} and executes method
 * {@link #run()} in class {@value #domClassName}.
 * 
 * @author ju
 * 
 */
public class Dom extends Java {

	/** The name of the class that implements {@link TestJava}. */ 
	public final static String domClassNamePattern = "(TestDom[a-zA-Z]*)";

	/** Filename of the file containing #{@value #domClassName}. */ 
	public final static Pattern domFileNamePattern = Pattern.compile(domClassNamePattern + "\\.java");
	
	/** Directory containing files with DOM handler implementation. */ 
	public final static String domBaseDir = "dom";

	@Override
	public boolean run() {
		boolean ret = true;
		File dir = new File(basePath, domBaseDir);
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
			Matcher matcher = domFileNamePattern.matcher(file.getName());
			if (!matcher.matches())
				continue;
		
			logger.log(Level.INFO, "Checking file '" + file.getName() + "'");
			cnt++;
			
			// compile the code
			ret &= compileJavaCode(new File(file.getName()).getAbsolutePath());
			
			// run the code
			try {
				String domClassName = matcher.group(1);
				ret &= runJavaCode(dir.toURL(), domClassName);
			} catch (MalformedURLException e) {
				logger.log(Level.SEVERE, "Error:", e);
				ret = false;
			}
		}
		logger.log(Level.INFO, "Total " + cnt + " files were processed.");
		if(cnt == 0)
			logger.log(Level.INFO, "Files containing Test DOM classes should be named 'TestDom[a-zA-Z]*.java' and located in the directory 'dom' in project directory.");
		return ret;
	}

	@Override
	public String getName() {
		return "DOM";
	}

}
