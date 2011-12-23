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
 * Na začátek seznamu položek přidejte novou položku se všemi potřebnými údaji (tj. stejné údaje jako u jiných položek).
 * Váš kód musí fungovat i když neexistuje žádná položka, neexistuje element položky a dokonce i v případě, že dokument je prázdný (obsahuje pouze prázdný kořenový element).
 */
public class Priklad6 {

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
            writer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

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
        
        Element novaPolozka = doc.createElement("polozka");
        novaPolozka.setAttributeNS("http://ksi.mff.cuni.cz/vyrobky", "vyr:cislo", "B547");
        
        Element novaPolozkaNazev = doc.createElementNS("http://ksi.mff.cuni.cz/vyrobky", "vyr:nazev");
        novaPolozkaNazev.setTextContent("Oracle Database 10g");
        
        Element novaPolozkaCena = doc.createElementNS("http://ksi.mff.cuni.cz/objednavky", "jednotkova-cena");
        novaPolozkaCena.setTextContent("1234");
        
        Element novaPolozkaKusu = doc.createElementNS("http://ksi.mff.cuni.cz/objednavky", "kusu");
        novaPolozkaKusu.setTextContent("1");
        
        novaPolozka.appendChild(novaPolozkaNazev);
        novaPolozka.appendChild(novaPolozkaCena);
        novaPolozka.appendChild(novaPolozkaKusu);
        
        NodeList polozkySeznam = doc.getElementsByTagName("polozky");
        Node polozky = null;
        if ( polozkySeznam.getLength() > 0 )    {
            polozky = polozkySeznam.item(0);
        } else {
            polozky = doc.createElementNS("http://ksi.mff.cuni.cz/objednavky", "polozky");
            if ( doc.getDocumentElement()!=null )   {
                doc.getDocumentElement().appendChild(polozky);
            } else {
                doc.appendChild(doc.createElementNS("http://ksi.mff.cuni.cz/objednavky", "objednavka"));
            }
        }
        if ( polozky.getFirstChild() != null )  {
            polozky.insertBefore(novaPolozka, polozky.getFirstChild());
        } else {
            polozky.appendChild(novaPolozka);
        }
        
    }
}
