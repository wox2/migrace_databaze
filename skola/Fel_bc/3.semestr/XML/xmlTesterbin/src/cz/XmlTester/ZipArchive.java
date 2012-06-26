package cz.XmlTester;

import java.io.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;

/**
 * This class contains methods for accessing ZIP file contents. 
 * 
 * @author ju
 *
 */
public class ZipArchive {

	private static Logger logger = Logger.getLogger(ZipArchive.class.getName());

	/**
	 * Extracts contents of the zip file to the destination directory.
	 * @param zipFilename Path to the ZIP file.
	 * @param destinationDirName Path to the destination directory.
	 */
	public void getZipFiles(String zipFilename, String destinationDirName) {
		if(destinationDirName.charAt(destinationDirName.length()-1) != File.separatorChar)
			destinationDirName += File.separator;
		
		try {
			ZipFile zipFile = new ZipFile(zipFilename);

			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while(entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();

				File outFile = new File(destinationDirName + entry.getName());
				File parentDir = outFile.getParentFile();
				if (!parentDir.exists()){
					parentDir.mkdirs();
				}

				
				if(entry.isDirectory()) {
					// Assume directories are stored parents first then children.
					logger.log(Level.FINE, "Extracting directory: " + entry.getName());
					// This is not robust, just for demonstration purposes.
					(new File(destinationDirName+entry.getName())).mkdir();
					continue;
				}
				
				logger.log(Level.FINE, "Extracting file: " + entry.getName());
				
				FileOutputStream fileoutputstream = new FileOutputStream(destinationDirName + entry.getName());
				InputStream zipEntryInputStream = zipFile.getInputStream(entry);
				int n;
				int bufSize = 65536;
				byte[] buf = new byte[bufSize];
				while ((n = zipEntryInputStream.read(buf, 0, bufSize)) > -1)
					fileoutputstream.write(buf, 0, n);
				fileoutputstream.close();
			}

			zipFile.close();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Unhandled exception during zip extraction:", e);
		}
	}
}
