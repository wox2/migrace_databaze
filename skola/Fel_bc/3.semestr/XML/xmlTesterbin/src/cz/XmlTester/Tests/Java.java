package cz.XmlTester.Tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;

import sun.tools.java.ClassPath;
import cz.XmlTester.TestJava;

/**
 * Tests SAX handler implementation.
 * 
 * Compiles classes found in file {@value #saxFileName} and executes method
 * {@link #run()} in class {@value #saxClassName}.
 * 
 * @author ju
 * 
 */
public abstract class Java extends Test {

	/**
	 * Runs java class specified by the {@link ClassPath}.
	 * @param classPath Class path to the {@link #className}
	 * @param className Class name
	 */
	protected boolean runJavaCode(URL classPath, String className) {
		try {
			URL[] urls = {classPath};
			URLClassLoader loader = URLClassLoader.newInstance(urls);
			TestJava test = (TestJava) loader.loadClass(className).newInstance();
			test.run();
			logger.log(Level.INFO, "Run successful");
			return true;
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "Runtime errors:", e);
			return false;
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, "Runtime errors:", e);
			return false;
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, "Runtime errors:", e);
			return false;
		}
	}

	
	/**
	 * Compiles source code specified by the Path2D {@code path}.
	 * 
	 * @param path Path to the file that should be compiled.
	 */
	protected boolean compileJavaCode(String path) {
		final String[] commandLine = new String[] { path };
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintWriter pw = new PrintWriter(baos);
		
//		logger.severe("java.endorsed.dirs=" + System.getProperty("java.endorsed.dirs"));
//		logger.severe("java.home=" + System.getProperty("java.home"));
//		logger.severe("sun.boot.class.path=" + System.getProperty("sun.boot.class.path"));
//		logger.severe("java.ext.dirs=" + System.getProperty("java.ext.dirs"));
//		logger.severe("env.class.path=" + System.getProperty("env.class.path"));
//		logger.severe("application.home=" + System.getProperty("application.home"));
//		logger.severe("java.class.path=" + System.getProperty("java.class.path"));
		
//		int errorCode = AccessController.doPrivileged(new PrivilegedAction<Integer>() {
//			public Integer run() {
//				return com.sun.tools.javac.Main.compile(commandLine, pw);
		int errorCode = com.sun.tools.javac.Main.compile(commandLine, pw);
//			}
//		});
		
		pw.close();
		if(errorCode != 0) {
			logger.log(Level.WARNING, "Compilation errors: code=" + errorCode + ", Message=" + baos.toString());
			return false;
		} else {
			logger.log(Level.INFO, "Compilation successful");
			return true;
		}

	}
}