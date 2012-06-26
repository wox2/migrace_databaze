/*
 * sax.java
 *
 * Created on 30. prosinec 2007, 19:04
 *
 * by Michal Pavlasek, pavlam2@gmail.com
 *
 * zdrojem pro cely program byly tutorialy na IBM developerWorks
 *
 * program nacte soubor data.xml, a pomoci SAX udalosti pro zacatek dokumentu,
 * zacatek elementu a konec dokumentu spocita a vypise pocty urcitych elementu
 */

import cz.XmlTester.TestJava;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class MyHandler extends DefaultHandler {
    
    int vysilani = 0; //pocet vysilani
    int porad = 0; //pocet poradu
    int clovek = 0; //pocet lidi
        
    /**
     * konstruktor tridy sax (parser musi byt objekt)
     */
    public MyHandler() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //System.out.println("sax vytvoren");
    }
    
    public void showEvent(String s) {
        System.out.println("showevent: "+s);
    }
    
    /**
     * zacatek xml documentu
     * @throws org.xml.sax.SAXException chyba rozhrani sax
     */
    public void startDocument() throws SAXException {
        //System.out.println("soubor byl pocat");
    }
    
    /**
     * zacatek elementu
     */
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        //System.out.println(qName);
        String name = qName;
        if(name == "vysilani") vysilani++;
        if(name == "porad") porad++;
        if(name == "clovek") clovek++;
    }
    
    /* public void endElement(String namespaceURI, String localName, String qName) throws SAXException {        
    }
     */
    
    /* public void characters(char[] ch, int start, int length) throws SAXException {        
    }
     */
    
    /**
     * konec dokumentu
     */
    public void endDocument() throws SAXException {        
        System.out.println("Statistika: ");
        System.out.println(vysilani+ " vysilani");
        System.out.println(porad+ " poradu");
        System.out.println(clovek+ " lidi");
    }
    
    /**
     * chyba
     */
    public void error (SAXParseException e) {
        System.out.println("SAXParseException: error");
        e.printStackTrace();
   }

    /**
     * varovani
     */
   public void warning (SAXParseException e) {
        System.out.println("SAXParseException: warning");
        e.printStackTrace();
   }

    /**
     * selhani
     */
   public void fatalError (SAXParseException e) {
        System.out.println("SAXParseException: fatal error");
        System.exit(1);
   }
}

public class TestSax extends TestJava {    
   /**
     * main funkce
     */
    public void run() {
        
        String filename = "../data.xml";
        
        try {            
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            spfactory.setValidating(false);
            
            SAXParser saxparser = spfactory.newSAXParser();
            
            XMLReader xmlreader = saxparser.getXMLReader();
            
            xmlreader.setContentHandler(new MyHandler());
            xmlreader.setErrorHandler(new MyHandler());
            
            InputSource source = new InputSource(filename);
            xmlreader.parse(source);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
