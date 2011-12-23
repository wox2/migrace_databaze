/*
 *  created 18.10. 2009 by Martin Lukes
 * Program vypise pocet elementu, pocet jmen(osob), ktere se zapasu ucastni, dale pocet hracu, kteri hraji,
 * pocet hracu, kteri dostali nejakou kartu a nakonec i seznam jmen vsech hracu 
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

    final int MAXIMALNI_POCET_HRACU = 30;
    boolean hracMod = false;
    boolean jmenoMod = false;
    boolean faulMod = false;
    int pocetHracu = 0;
    int pocetFaulu = 0;
    String[] seznamJmen = new String[MAXIMALNI_POCET_HRACU];
    int pocetElementu = 0;
    int pocetJmen = 0;

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
        System.out.println("showevent: " + s);
    }

    /**
     * zacatek xml documentu
     * @throws org.xml.sax.SAXException chyba rozhrani sax
     */
    public void startDocument() throws SAXException {
        //System.out.println("soubor byl pocat");
    }

    /**
     * zacatek  Elementu
     */
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        String name=qName;
        pocetElementu++;
        if (name.equals("hrac")) {
            hracMod = true;
        }
        if ((name.equals("jmeno"))) {
            if (hracMod) {
                pocetHracu++;
            }
            jmenoMod=true;
            pocetJmen++;
        }
        if (name.equals("faul_s_cervenou_kartou") || name.equals("faul_s_zlutou_kartou")) {
            faulMod = true;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        String name=qName;
        if (faulMod && name.equals("hrac")) {
            pocetFaulu++;
            faulMod = false;
        } else {
            if (jmenoMod && name.equals("jmeno")) {
                jmenoMod = false;
            } else if (hracMod && name.equals("hrac")) {
                hracMod = false;
            }
        }
    }
    /*Statistika faulujicich hracu s nejakou kartou, poctu hracu ucastnicich se turnaje*/

    public void endDocument() throws SAXException {
        System.out.println("Pocet pocetElementuu: " + pocetElementu + "\nPocet jmen (hracu, ale i nehracu)" + pocetJmen + "\nStatistika:\nPocet faulujicich hracu s kartou: " + pocetFaulu + "\nPocet hracu ucastnicich se zapasu: " + pocetHracu + "\n Seznam jmen hracu:\n");
        for (int i = 0; i < pocetHracu; i++) {
            System.out.println(seznamJmen[i] + "\n");
        }
        
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (hracMod && jmenoMod) {
            seznamJmen[pocetHracu - 1] = new String(ch, start, length);
        }
    }

    /**
     * chyba
     */
    public void error(SAXParseException e) {
        System.out.println("SAXParseException: error");
        e.printStackTrace();
    }

    /**
     * varovani
     */
    public void warning(SAXParseException e) {
        System.out.println("SAXParseException: warning");
        e.printStackTrace();
    }

    /**
     * selhani
     */
    public void fatalError(SAXParseException e) {
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

