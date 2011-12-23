import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;
import cz.XmlTester.TestJava;


public class TestSax extends TestJava{

    public void run() {

        // Cesta ke zdrojovĂ©mu XML dokumentu  
        String sourcePath = "../data.xml";

        try {
            
            // VytvorĂ­me instanci parseru.
            XMLReader parser = XMLReaderFactory.createXMLReader();
            
            // VytvorĂ­me vstupnĂ­ proud XML dat.
            InputSource source = new InputSource(sourcePath);
            
            // NastavĂ­me nĂˇÄ… vlastnĂ­ content handler pro obsluhu SAX udĂˇlostĂ­.
            parser.setContentHandler(new MujContentHandler());
            
            // Zpracujeme vstupnĂ­ proud XML dat.
            parser.parse(source);
            
        } catch (Exception e) {
        
            e.printStackTrace();
            
        }
        
    }
    
}
/**
 * NĂˇĹˇ vlastnĂ­ content handler pro obsluhu SAX udĂˇlostĂ­.
 * Implementuje metody interface ContentHandler. 
 */ 
class MujContentHandler implements ContentHandler {

    // UmoĹľĹ�uje zacĂ­lit mĂ­sto v dokumentu, kde vznikla aktualnĂ­ udĂˇlost
    int pocetPolozek;
    int vyssiPolozka;
    int vyssiCena;
    int celkovyPocetPolozek;
    int celkovaCena;
    Locator locator;
    
    /**
     * NastavĂ­ locator
     */     
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * Obsluha udĂˇlosti "zaÄŤĂˇtek dokumentu"
     */     
    public void startDocument() throws SAXException {
        
        // ...
        
    }
    /**
     * Obsluha udĂˇlosti "konec dokumentu"
     */     
    public void endDocument() throws SAXException {
        
        // ...
        System.out.println("Počet položek na seznamu: " + pocetPolozek );
        System.out.println("Celkový počet zboží: " + celkovyPocetPolozek );
        System.out.println("Celková cena: " + celkovaCena + "Kč");
        
        System.out.println("Počet položek dražších než 3000Kč: " + vyssiPolozka );
        System.out.println("Celkova cena položek dražších než 3000Kč: " + vyssiCena );
        
    }
    
    /**
     * Obsluha udĂˇlosti "zaÄŤĂˇtek elementu".
     * @param uri URI jmennĂ©ho prostoru elementu (prĂˇzdnĂ©, pokud element nenĂ­ v ĹľĂˇdnĂ©m jmennĂ©m prostoru)
     * @param localName LokĂˇlnĂ­ jmĂ©no elementu (vĹľdy neprĂˇzdnĂ©)
     * @param qName KvalifikovanĂ© jmĂ©no (tj. prefix-uri + ':' + localName, pokud je element v nÄ›jakĂ©m jmennĂ©m prostoru, nebo localName, pokud element nenĂ­ v ĹľĂˇdnĂ©m jmennĂ©m prostoru)
     * @param atts Atributy elementu     
     */     
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if ( localName.equals( "vyrobek" ) ) { pocetPolozek++; 
        int pocetKS = Integer.decode( atts.getValue( "ks" ) ); 
        celkovyPocetPolozek += pocetKS; 
        int cena = pocetKS * Integer.decode( atts.getValue( "cena" ) );
        celkovaCena+= cena; 
        
            if ( cena > 3000 ) { vyssiCena += cena; vyssiPolozka++;  }
        
        }
        
        // ...

    }
    /**
     * Obsluha udĂˇlosti "konec elementu"
     * Parametry majĂ­ stejnĂ˝ vĂ˝znam jako u @see startElement     
     */     
    public void endElement(String uri, String localName, String qName) throws SAXException {

        // ...

    }
    
    /**
     * Obsluha udĂˇlosti "znakovĂˇ data".
     * SAX parser muÄľe znakovĂˇ data dĂˇvkovat jak chce. Nelze tedy pocĂ­tat s tĂ­m, Ĺľe je celĂ˝ text dorucen v rĂˇmci jednoho volĂˇnĂ­.
     * Text je v poli (ch) na pozicich (start) az (start+length-1).
     * @param ch Pole se znakovĂ˝mi daty
     * @param start Index zacĂˇtku Ăşseku platnĂ˝ch znakovĂ˝ch dat v poli.
     * @param length DĂ©lka Ăşseku platnĂ˝ch znakovĂ˝ch dat v poli.
     */               
    public void characters(char[] ch, int start, int length) throws SAXException {

        // ...
        
    }
    
    /**
     * Obsluha udĂˇlosti "deklarace jmennĂ©ho prostoru".
     * @param prefix Prefix prirazenĂ˝ jmennĂ©mu prostoru.
     * @param uri URI jmennĂ©ho prostoru.
     */     
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        
        // ...
        
    }

    /**
     * Obsluha udĂˇlosti "konec platnosti deklarace jmennĂ©ho prostoru".
     */     
    public void endPrefixMapping(String prefix) throws SAXException {
    
        // ...
    
    }

    /**
     * Obsluha udĂˇlosti "ignorovanĂ© bĂ­lĂ© znaky".
     * StejnĂ© chovĂˇnĂ­ a parametry jako @see characters     
     */     
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        
        // ...
        
    }

    /**
     * Obsluha udĂˇlosti "instrukce pro zpracovĂˇnĂ­".
     */         
    public void processingInstruction(String target, String data) throws SAXException {
      
      // ...
            
    }

    /**
     * Obsluha udĂˇlosti "nezpracovanĂˇ entita"
     */         
    public void skippedEntity(String name) throws SAXException {
    
      // ...
    
    }
}