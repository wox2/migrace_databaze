package cz.XmlTester.Tests;

/**
 * This class stores one requirement details. It is used by subclasses of the
 * SyntaxRequirements class.
 * 
 * @author ju
 * 
 */
public class Requirement {
	/**
	 * Requirement specification. Syntax depends on the SyntaxRequirements class
	 * specialization in which the requirement will be used.
	 */
	private String requirement;

	/**
	 * Textual description of the requirement that will be shown to the user in
	 * case of an error.
	 */ 
	private String description;
	
	/**
	 * Creates new instance of the Requirement class
	 * @param requirement Requirement specification.
	 * @param description Requirement description.
	 */
	public Requirement(String requirement, String description) {
		this.requirement = requirement;
		this.description = description;
	}

	/**
	 * @return Returns requirement.
	 */
	public String getRequirement() {
		return requirement;
	}

	/**
	 * @return Returns requirement description.
	 */
	public String getDescription() {
		return description;
	}
	
}
