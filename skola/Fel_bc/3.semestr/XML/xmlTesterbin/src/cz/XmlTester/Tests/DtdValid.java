package cz.XmlTester.Tests;


/**
 * Validates XSL Transformation and performs it on the {@code xmlFileName}. 
 * 
 * @author ju
 *
 */
public class DtdValid extends Test {

	/** File name of the file containing DTD */
	private final static String dtdFileName = "data.dtd";
	
	
	@Override
	public boolean run() {
		boolean ret = true;
		setCurrentDirectory(basePath);
		// check syntax constructions without comments 
		ret &= new SyntaxRegex(dtdFileName, false, requirements, new String[] {"<!--"}, new String[] {"-->"}).run();
		// check comments 
		ret &= new SyntaxRegex(dtdFileName, false, requirementsComment, new String[]{}, new String[]{}).run();
		return ret;
	}

	@Override
	public String getName() {
		return "Validate DTD";
	}

	
	/** Regular expression requirements on the DTD file. */
	private final static Requirement[] requirements = {
		new Requirement("<!ELEMENT", "Element definition"), 
		new Requirement("<!ATTLIST", "Attribute list definition"), 
		new Requirement("#PCDATA", "PCDATA"), 
		new Requirement("\\?", "Zero or One Occurrences of an Element"), 
		new Requirement("\\*", "Zero or More Occurrences of an Element"), 
		new Requirement("ID[^R]", "ID attribute"), 
		new Requirement("IDREF", "IDREF attribute"), 
		new Requirement("#REQUIRED"," The REQUIRED attribute definition"), 
		new Requirement("#IMPLIED", "The IMPLIED attribute definition"), 
		new Requirement(",", "List of elements"), 
		new Requirement("|", "Element must be one from an enumerated list"), 
	};

	/** Checks whether DTD contains comments */
	private final static Requirement[] requirementsComment = {
		new Requirement("<!--.*-->", "Comment")
	};
}