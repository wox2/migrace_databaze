//package prg036_martin_svoboda.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import cz.XmlTester.TestJava;

/**
 * SaxHandler
 * Vypise statistiky (pocty cestujicich) pro jednotlive lety a pro vsechny lety
 * Vypisuje celkovy pocet cestujich, dospelych, mladistvych a malych deti
 * @author quick
 *
 */
class TestSaxHandler extends DefaultHandler {
	
	protected int pax = 0;
	protected int adults = 0;
	protected int children = 0;
	protected int infants = 0;
	
	protected int flightPax = 0;
	protected int flightAdults = 0;
	protected int flightChildren = 0;
	protected int flightInfants = 0;
	
	@Override
	public void startDocument() throws SAXException {
//		System.out.println("Parsuji soubor");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		String gender;
		
		if (qName.equals("flight")) {
			System.out.println("Statistika let "
									+ attributes.getValue("number")
									+ " "
									+ attributes.getValue("date"));
			System.out.println("================================");
			
			flightPax = 0;
			flightAdults = 0;
			flightChildren = 0;
			flightInfants = 0;
		}
		
		if (qName.equals("passenger")) {
			gender = attributes.getValue("gender");
			flightPax++;
			if (gender.equals("INF")) {
				flightInfants++;
			} else if (gender.equals("CHD")) {
				flightChildren++;
			} else {
				flightAdults++;
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if (qName.equals("flight")) {
			System.out.println("Celkem cestujicich: "+ flightPax);
			System.out.println("Celkem dospelych: "+ flightAdults);
			System.out.println("Celkem mladistvych: "+ flightChildren);
			System.out.println("Celkem deti: "+ flightInfants);
			System.out.println("\n");
			
			pax += flightPax;
			adults += flightAdults;
			children += flightChildren;
			infants += flightInfants;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Celkova statistika");
		System.out.println("==================");
		
		System.out.println("Celkem cestujicich: "+ pax);
		System.out.println("Celkem dospelych: "+ adults);
		System.out.println("Celkem mladistvych: "+ children);
		System.out.println("Celkem deti: "+ infants);
	}
	
	/**
     * chyba
     */
    @Override
	public void error (SAXParseException e) {
        System.out.println("SAXParseException: error");
        e.printStackTrace();
   }

    /**
     * varovani
     */
   @Override
public void warning (SAXParseException e) {
        System.out.println("SAXParseException: warning");
        e.printStackTrace();
   }

    /**
     * selhani
     */
   @Override
public void fatalError (SAXParseException e) {
        System.out.println("SAXParseException: fatal error");
        System.exit(1);
   }
}

public class TestSax extends TestJava {
	
	public static void main(String[] args) {
		TestSax sax = new TestSax();
		sax.run();
	}

	@Override
	public void run() {
		String filename = "../data.xml";
        
        try {            
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            spfactory.setValidating(false);
            
            SAXParser saxparser = spfactory.newSAXParser();
            
            XMLReader xmlreader = saxparser.getXMLReader();
            
            xmlreader.setContentHandler(new TestSaxHandler());
            xmlreader.setErrorHandler(new TestSaxHandler());
            
            InputSource source = new InputSource(filename);
            xmlreader.parse(source);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
