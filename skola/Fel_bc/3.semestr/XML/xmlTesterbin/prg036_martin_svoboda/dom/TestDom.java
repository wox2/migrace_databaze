
//package prg036_martin_svoboda.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cz.XmlTester.TestJava;

public class TestDom  extends TestJava {
	
	Document doc;
	
	public static void main(String[] args) {
		TestDom dom = new TestDom();
		dom.run();
	}
	
	@Override
	public void run() {
		String filename = "../data.xml";
		String outfile = "../data.out.xml";
		
		try { 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(new File(filename));
			
			
			this.processTree();
			
			TransformerFactory tf = TransformerFactory.newInstance();
			
			// ulozeni
			Transformer writer = tf.newTransformer();
			writer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			writer.transform(new DOMSource(doc), new StreamResult(new File(outfile)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Seradi cestujici podle prijmeni a jmena
	 * !!! prvni radi podle prijmeni az pak podle krestniho jmena !!!
	 * @param dom
	 */
	public void processTree() {
		
		// elementy cestujicich pro jednotlive lety
		NodeList paxs_elms = this.doc.getElementsByTagName("passengers");
		
		for (int i=0; i < paxs_elms.getLength(); i++) {
			
			// list cestujicich pro konkretni let
			NodeList paxs = paxs_elms.item(i).getChildNodes();
			
			Node tmp;
			for (int j=0; j < paxs.getLength(); j++) {
				for (int k=0; k < paxs.getLength() - 1; k++) {
					if (joinNames(paxs.item(k)).compareTo(joinNames(paxs.item(k + 1))) > 0) {
						tmp = paxs.item(k);
						paxs.item(0).getParentNode().insertBefore(paxs.item(k + 1), paxs.item(k));
						paxs.item(0).getParentNode().replaceChild(tmp, paxs.item(k + 1));
					}
				}
			}
		}
	}
	
	/**
	 * Vraci string ve formatu prijmeni/jmeno/druhejmeno
	 * @param n
	 * @return
	 */
	public static String joinNames(Node n) {
		
		if (!n.getNodeName().equals("passenger")){
			throw new RuntimeException("Node isn`t passenger node, but "+ n.getNodeName());
		}
		
		String out = "";
		String names[] = new String[3];
		
		NodeList elms = n.getChildNodes();
		for (int i=0; i < elms.getLength(); i++) {
			if (elms.item(i).getNodeName().equals("last_name")){
				names[0] = elms.item(i).getTextContent();
			} else if (elms.item(i).getNodeName().equals("first_name")) {
				names[1] = elms.item(i).getTextContent();
			} else if (elms.item(i).getNodeName().equals("first_name")) {
				names[2] = elms.item(i).getTextContent();
			}
		}
		
		for (int i=0; i < names.length; i++) {
			out += "/"+ names[i];
		}
		
		return out.substring(1);
	}
}
