package y36xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * ZADANI:
 * Upravte program tak, aby transformoval vstupni XML dokument na vystupni pomoci akcii
 * uvedenych v komentarich metody processTree(doc).
 */
public class DOM_podpora {

    private static final String VSTUPNI_SOUBOR = "src/y36xml/objednavka.xml";
    private static final String VYSTUPNI_SOUBOR = "src/y36xml/objednavka-output.xml";

    public static void main(String[] args) {
        
        try {
            
            //DocumentBuilderFactory vytváří DOM parsery
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //nebudeme validovat
            dbf.setValidating(false);

            //vytvoříme si DOM parser
            DocumentBuilder builder = dbf.newDocumentBuilder();

            //parser zpracuje vstupní soubor a vytvoří z něj strom DOM objektů
            Document doc = builder.parse(VSTUPNI_SOUBOR);

            //zpracujeme DOM strom
            processTree(doc);

            //TransformerFactory vytváří serializátory DOM stromů
            TransformerFactory tf = TransformerFactory.newInstance();

            //Transformer serializuje DOM stromy
            Transformer writer = tf.newTransformer();

            //nastavíme kodování
            writer.setOutputProperty(OutputKeys.ENCODING, "windows-1250");

            //spustíme transformaci DOM stromu do XML dokumentu
            writer.transform(new DOMSource(doc), new StreamResult(new File(VYSTUPNI_SOUBOR)));


        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
    }

    /**
     * Zpracuje DOM strom
     */
    private static void processTree(Document doc) {
        
        
        
    }
}
