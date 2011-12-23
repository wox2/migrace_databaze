package cz.XmlTester.Tests;

import java.io.File;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Analyzes XML document and tests whether it satisfies conditions:
 * 
 *  - document is well-formed, 
 * 
 *  - minimal fan-out,
 * 
 *  - minimal root-to-leaf path length. 
 *  
 * @author ju
 *
 */
public class XmlValid extends Test {

	/** At least one element in  the XML document has to have at least this number of descendants. */
	private static final int minDescendants = 10;

	/** All the path from the document root to the leaf element should have at least this number of elements. */
	private static final int minTreeDepth = 3;
	
	/** At least one path from the document root to the leaf element should have at least this number of elements. */
	private static final int maxTreeDepth = 5;
	
	
	@Override
	public boolean run() {
		setCurrentDirectory(basePath);
		boolean ret = true;
		File file = new File(xmlFileName);
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(file);

			// test whether document contains element with at least minDescendants descendants
			int rootDescendants = getDocumentMaxFanOut(doc.getDocumentElement());
			if(rootDescendants < minDescendants) {
				logger.log(Level.WARNING,
						"The document element has " + rootDescendants
						+ " descendants. Minimal allowed number of descendants is "
						+ minDescendants + ".");
				ret = false;
			}

			/*
			// test the tree depth - the shortest path from the root to a leaf
			int treeMinDepth = getDocumentMinDepth(doc.getDocumentElement());
			if(treeMinDepth < minTreeDepth) {
				logger.log(Level.WARNING,
						"The document depth is " + treeMinDepth
						+ " elements. Minimal allowed document depth is "
						+ minTreeDepth+ ".");
				ret = false;
			}
			*/
			
			// test the tree depth - the longest path from the root to a leaf
			int treeMaxDepth = getDocumentMaxDepth(doc.getDocumentElement());
			if(treeMaxDepth < maxTreeDepth) {
				logger.log(Level.WARNING,
						"The document depth is " + treeMaxDepth
						+ " elements. Minimal allowed document depth is "
						+ maxTreeDepth+ ".");
				ret = false;
			}

			// check whether it matches all the syntax requirements
			ret &= new SyntaxRegex(xmlFileName, 
					false,	//cannot be read as XML due to entity name-to-value replacement 
					requirements, 
					new String[] {"\\<\\!\\-\\-"}, new String[] {"\\-\\-\\>"}).run();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString());
			ret = false;
		}

		return ret;
	}
	
	@Override
	public String getName() {
		return "Validate XML";
	}

	/** Regular expression requirements on XML file. */
	private final static Requirement[] requirements = {
		new Requirement("&[^; \t\n\r]+;", "Use of an entity"),			// entity usage
		new Requirement(".{7}\\<\\?", "Processing instruction"),		// processing instruction only if it is not in xml document prologue 
		new Requirement("<!\\[CDATA\\[.*\\]\\]>", "CDATA section"),	 
	};


	
	/**
	 * Returns the length of the shortest path from the document root to the leaf element.
	 * @param node Starting point for the root-to-leaf analysis. Usually the document element.
	 * @return
	 */
	private int getDocumentMinDepth(Node node) {
		if(!node.hasChildNodes() && node.getNodeType() == Node.ELEMENT_NODE)
			return 1;
		if(node.getNodeType() != Node.ELEMENT_NODE)
			return 0;
		
		NodeList nodes = node.getChildNodes();
		int minDepth = -1; 
		for(int i = 0; i < nodes.getLength(); i++) {
			Node xnode = nodes.item(i); 
			if(xnode.getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			int retDepth = getDocumentMinDepth(xnode);
			if(minDepth == -1 || retDepth < minDepth)
				minDepth = retDepth;
		}
		
		return minDepth == -1 ? 1 : minDepth+1;
	}

	
	/**
	 * Returns the length of the longest path from the document root to the leaf element.
	 * @param node Starting point for the root-to-leaf analysis. Usually the document element.
	 * @return
	 */
	private int getDocumentMaxDepth(Node node) {
		if(node.getNodeType() != Node.ELEMENT_NODE)
			return 0;
		if(!node.hasChildNodes() && node.getNodeType() == Node.ELEMENT_NODE)
			return 1;
		
		NodeList nodes = node.getChildNodes();
		int maxDepth = 0; 
		for(int i = 0; i < nodes.getLength(); i++) {
			int retDepth = getDocumentMaxDepth(nodes.item(i));
			if(retDepth > maxDepth)
				maxDepth = retDepth;
		}
		
		return maxDepth+1;
	}

	
	/**
	 * Returns the largest fan-out of the elements in XML document.
	 * @param node Starting point for the fan-out analysis. Usually the document element.  
	 * @return
	 */
	private int getDocumentMaxFanOut(Node node) {
		if(!node.hasChildNodes() || (node.getNodeType() != Node.ELEMENT_NODE))
			return 0;
		
		NodeList nodes = node.getChildNodes();
		int maxFanOut = 0; 
		int curFanOut = 0; 
		for(int i = 0; i < nodes.getLength(); i++) {
			if(nodes.item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			curFanOut++;
			int retFanOut = getDocumentMaxFanOut(nodes.item(i));
			if(retFanOut > maxFanOut)
				maxFanOut = retFanOut;
		}
		if(curFanOut > maxFanOut)
			maxFanOut = curFanOut;
		
		return maxFanOut;
	}
	
}
