package cz.XmlTester.Tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This test checks if the file matches all the multi-line regular expression requirements.   
 * 
 * @author ju
 *
 */
public class SyntaxRegex extends SyntaxRequirements {

	
	/**
	 * Strings beginning a comment. Should be paired with commentEnd.
	 */
	protected String[] commentStarts;

	/**
	 * Contents of the file to be checked. If null when run method is invocated,
	 * program loads fileContent from file specified in attribute fileName
	 */
	protected String fileContents;
	
	/**
	 * Strings ending a comment. Should be paired with commentBegin.
	 */
	protected String[] commentEnds;

	/**
	 * Creates the new instance of the {@code SyntaxRegex} class. 
	 * 
	 * @param fileName File to be checked.
	 * @param requirements List of the regular expressions.
	 */
	public SyntaxRegex(String fileName, boolean isXml, Requirement[] requirements, 
			String[] commentStarts, String[] commentEnds) {
		super(fileName, isXml, requirements);
		this.fileContents = null;
		this.commentStarts = commentStarts;
		this.commentEnds = commentEnds;
	}
	
	
	/**
	 * Creates the new instance of the {@code SyntaxRegex} class. 
	 * 
	 * @param fileName File name used in output information only.
	 * @param fileContents Contents of the file to be checked.
	 * @param requirements List of the regular expressions.
	 */
	public SyntaxRegex(String fileName, String fileContents, boolean isXml, Requirement[] requirements, 
			String[] commentStarts, String[] commentEnds) {
		super(fileName, isXml, requirements);
		this.fileContents = fileContents;
		this.commentStarts = commentStarts;
		this.commentEnds = commentEnds;
	}
	
	
	@Override
	/**
	 * Checks if the file {@code filename} matches all the regular expression
	 * requirements {@code #requirements}.
	 */
	public boolean run() {
		try {
			String str = null;
			if(fileContents == null) {
				if(isXml)
					fileContents = readXmlFile(fileName);
				else
					fileContents = readFile(fileName, "ISO-8859-1");
			}
			
			// delete all the commented text 
			fileContents = excludeComments(fileContents); 
			
			boolean ret = true;
			for (Requirement requirement : requirements) {
				Pattern pattern = Pattern.compile(requirement.getRequirement(), 
						Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
				Matcher matcher = pattern.matcher(fileContents);
				if(!matcher.find()) {
					logger.log(Level.WARNING, "Pattern '" + requirement.getDescription() + 
							"' not found in file " + fileName + ".");
					ret = false;
				}
			}

			return ret;
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			return false;
		}
	}
	
	
	/**
	 * Deletes all the commented text. Comments are specified by the
	 * commentStarts and commentEnds atrributes. One-line comments (defined only
	 * by the commentStarts) are not supported.
	 * 
	 * @param src Source string with comments.
	 * @return Returns src with comments ommited.
	 */
	private String excludeComments(String src) {
		for (int i = 0; i < commentStarts.length; i++) {
			Pattern pattern = Pattern.compile(commentStarts[i] + ".*?" + commentEnds[i], 
					Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			Matcher matcher = pattern.matcher(src);
			src = matcher.replaceAll("");
		}
		return src;
	}
	
	
	@Override
	public String getName() {
		return "Validate file against Regular Expression";
	}

}
