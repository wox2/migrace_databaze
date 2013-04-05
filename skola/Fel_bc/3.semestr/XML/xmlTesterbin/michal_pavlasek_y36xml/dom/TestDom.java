/*
 * dom.java
 *
 * Created on 31. prosinec 2007, 10:10
 *
 * by Michal Pavlasek , pavlam2@gmail.com
 *
 * zdrojem pro cely program byly tutorialy na IBM developerWorks
 *
 * program nacte soubor data.xml, pomoci DOM v nem nalezne element verze
 * a jeho obsah prepise aktualnim datem a casem, vysledek zapise do souboru processed.xml
 *
 */


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import cz.XmlTester.TestJava;


public class TestDom extends TestJava {
    
    private static void vypis (Node node) {
      System.out.println(node.getNodeName()+" = "+node.getNodeValue());   

      for (Node child = node.getFirstChild(); 
          child != null;
          child = child.getNextSibling())
      {
      vypis(child);
      }
 }
   
    /**
     * @param args the command line arguments
     */
    public void run() {
                
        String filename = "../data.xml";
        String outfile = "../data.out.xml";
        
        File file = new File(filename);
        Document doc = null;
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Element root = doc.getDocumentElement();
        
        NodeList verze = root.getElementsByTagName("verze");
        //System.out.println(verze.item(0).getNodeName()+":");
        System.out.println("puv. timestamp: "+verze.item(0).getFirstChild().getNodeValue()+" ("+filename+")");
        
        Date now = new Date();        
        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                                   DateFormat.MEDIUM,
                                   Locale.GERMAN);        
        
        Node totalNode = doc.createTextNode(formatter.format(now));		  
        
        verze.item(0).replaceChild(totalNode, verze.item(0).getFirstChild());
        System.out.println("novy timestamp: "+verze.item(0).getFirstChild().getNodeValue()+" ("+outfile+")");
        
      try {
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(new File(outfile)));
	
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();

        transformer.transform(source, result);
      } catch (Exception e) {
        e.printStackTrace();
      }        
    }    
}

/* ruzne casti kodu, ktere nakonec nebyly pouzity:
        System.out.println(root.getNodeName());
        
        NodeList children = root.getElementsByTagName("verze");
        System.out.println("pocet nodu: "+children.getLength());
        for (int i = 0; i < children.getLength(); i++) {
            System.out.println(children.item(i).getFirstChild().getNodeValue());
        }        
        System.out.println(children.item(0).getNodeName());
        System.out.println(children.item(0).getFirstChild().getNodeValue());
        
        vypis(children.item(0));
        Element totalElement = doc.createElement("total");
        totalElement.appendChild(totalNode);		  

        verze.item(0).getFirstChild().
 
        replaceChild(verze.item(0).getFirstChild(), new Node(doc.createTextNode("neco")));        
 */      