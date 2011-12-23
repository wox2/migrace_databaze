package cz.XmlTester.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * Abstract class defining Test structure.
 * 
 * @author ju
 *
 */
public abstract class Test {
	protected final static Logger logger = Logger.getLogger(Test.class.getName());
	
	/**
	 * Runs the test.
	 * @return Returns {@code true} if the test completed successfully; {@code false} otherwise
	 */
	public abstract boolean run();

	/** Returns name of the test */
	public abstract String getName();
	
	/** Stores the main xml file name. */
	protected final static String xmlFileName = "data.xml";
	
	/** Stores the base path of the xml test project */
	protected static String basePath;
	
	public static void setBasePath(String path) {
		basePath = path;
	}

	/**
	 * Returns the absolute path constructed from {@code path} and {@code filename}. 
	 * @param path Directory part of the path.
	 * @param fileName Directory or file name in {@code path}.
	 * @return
	 */
	protected String getFullPath(String path, String fileName) {
		//logger.log(Level.FINE, new File(path, fileName).getAbsolutePath());
		return new File(path, fileName).getAbsolutePath();
	}
	
	/** Stores the last value of the system property {@code user.dir}. */ 
	private String originalCurDir;
	
	/**
	 * Sets the new working directory to the specified value. The current
	 * working directory is stored in {@link #originalCurDir}.
	 * 
	 * @param path New current working directory.
	 */
	protected void setCurrentDirectory(String path) {
		/*
		 * TODO does not work correctly, see java bug database, bug #4117557
		 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4117557
		 */
		originalCurDir = System.getProperty("user.dir");
		System.setProperty("user.dir", path);
	}
	
	/** Restores current working directory from the {@link #originalCurDir}. */
	protected void restorCurrentDirectory() {
		System.setProperty("user.dir", originalCurDir);
	}


	
	/**
	 * Reads file into String. Uses default ASCII encoding.
	 * @param fileName File to be read.
	 * @return Returns the contents of the file in a String.
	 * @throws IOException Thrown if the file was not found.
	 */
	protected String readFile(String fileName, String charset) throws IOException {
		FileInputStream f = new FileInputStream(new File(fileName).getAbsolutePath());
		FileChannel src = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(65536);
		StringBuilder str = new StringBuilder(65536);
		CharsetDecoder cd = Charset.forName(charset).newDecoder();
		
		while (src.read(buf) != -1) {
			str.append(cd.decode(((ByteBuffer) (buf.flip()))));
			buf.clear();
		}
		f.close();
		
		return str.toString();
	}
	
	
	/**
	 * Reads file into String. Uses character encoding set in XML file header.
	 * @param fileName File to be read.
	 * @return Returns contents of the file in a String.
	 */
	public static String readXmlFile(String fileName) {
		File file = new File(fileName);
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(file);
			DOMSource source = new DOMSource(doc);
	        StringWriter result = new StringWriter();
		
	        TransformerFactory transFactory = TransformerFactory.newInstance();
	        Transformer transformer = transFactory.newTransformer();

	        transformer.transform(source, new StreamResult(result));
	        return result.toString();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "An error occured while reading XML file '" + fileName + "'", e);
		}
		return null;
	}

	
	protected String getCharset(String fileName) {
		String charset = "ISO-8859-1";
		try {
			charset = readFile(fileName, "ISO-8859-1").trim();
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getMessage() + " Using default ISO-8859-1 character set.");
		}
		return charset;

	}
}
