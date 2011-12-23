/*
 * TestDom.java
 *
 * Created on 18.10. 2009
 * by Martin Lukes 
 *
 * k napsani programu byly pouzity vzorove priklady a prednasky
 * Parser pradava jednoho rozhodciho (Vaclav Havel), dale pridava do teamu s hracem,
 * ktery dostal cervenou kartu hrace s atributem prezdivka = "zabijak", pridava elementu sestava
 * atribut prezdivka = "zabijaci" a otaci poradi hracu v tomto teamu.
 *
 * Puvodni soubor zustava netknuty, vystup je ulozen v souboru data.out.xml
 *
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import cz.XmlTester.TestJava;
import javax.xml.transform.OutputKeys;

public class TestDom extends TestJava {

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
            processTree(doc);

            // TransformerFactory vytvari serializatory DOM stromu
            TransformerFactory tf = TransformerFactory.newInstance();
            // Transformer serializuje DOM stromy
            Transformer writer = tf.newTransformer();
            // nastavime kodovani
            writer.setOutputProperty(OutputKeys.ENCODING, "windows-1250");
            // spustime transformaci DOM stromu do XML dokumentu
            writer.transform(new DOMSource(doc), new StreamResult(new File(outfile)));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processTree(Document doc) {

        NodeList seznamRozhodci = doc.getElementsByTagName("rozhodci");
        for (int i = 0; i < seznamRozhodci.getLength(); i++) {
            Node actualNode = seznamRozhodci.item(i);
            for (Node actualChildNode = actualNode.getFirstChild(); actualChildNode != null; actualChildNode = actualChildNode.getNextSibling()) {
                if (actualChildNode.getNodeName().equals("hlavni")) {
                    Node copyNode = actualChildNode.cloneNode(true);
                    for (Node child = copyNode.getFirstChild(); child != null; child = child.getNextSibling()) {
                        if (child.getNodeName().equals("jmeno")) {
                            child.setTextContent("Vaclav Havel");
                        } else if (child.getNodeName().equals("prezdivka")) {
                            child.setTextContent("Prikulovac");
                        }
                    }
                    actualChildNode.appendChild(copyNode);
                }
            }
        }

        NodeList seznamHrisniku = doc.getElementsByTagName("faul_s_cervenou_kartou");
        Node actualNode = null;
        for (int i = 0; i < seznamHrisniku.getLength(); i++) {
            actualNode = seznamHrisniku.item(i);
            while (!(actualNode.getParentNode().getNodeName().equals("muzstvo") || actualNode.getParentNode().equals(null))) {
                actualNode = actualNode.getParentNode();
            }
            Element player = doc.createElement("hrac");
            player.setAttribute("prezdivka", "zabijak");
            actualNode.appendChild(player);
        }
        Element sestava = doc.createElement("sestava");
        sestava.setAttribute("prezdivka", "zabijaci");
        if (actualNode != null) {
            while (actualNode.hasChildNodes()) {
                Node copy = actualNode.getLastChild().cloneNode(true);
                sestava.appendChild(copy);
                actualNode.removeChild(actualNode.getLastChild());
            }
            Node upravenyNode = actualNode.getParentNode();
            upravenyNode.removeChild(actualNode);
            upravenyNode.appendChild(sestava);
        }
    }
} 
