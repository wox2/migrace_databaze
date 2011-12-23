package y36xml;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;

/**
 * Na základě SAX událostí rekonstruujte elementy a atributy původního
 * XML dokumentu (včetně původního vnoření elementu a jmenných prostorů).
 * Znaková data ignorujte. Zanorené elementy formátujte odstavením pomocí tabulátoru.
 */ 
public class Priklad1 {

    public static void main(String[] args) {

        // Cesta ke zdrojovému XML dokumentu  
        String sourcePath = "src/y36xml/objednavka.xml";

        try {
            
            // Vytvoříme instanci parseru.
            XMLReader parser = XMLReaderFactory.createXMLReader();
            
            // Vytvoříme vstupní proud XML dat.
            InputSource source = new InputSource(sourcePath);
            
            // Nastavíme náš vlastní content handler pro obsluhu SAX událostí.
            parser.setContentHandler(new MujContentHandler());
            
            // Zpracujeme vstupní proud XML dat.
            parser.parse(source);
            
        } catch (Exception e) {
        
            e.printStackTrace();
            
        }
        
    }
    
}

/**
 * Náš vlastní content handler pro obsluhu SAX událostí.
 * Implementuje metody interface ContentHandler. 
 */ 
class MujContentHandler implements ContentHandler {

    // Umožnuje zacílit místo v dokumentu, kde vznikla aktualní událost
    Locator locator;
    
    /**
     * Nastaví locator
     */     
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * Obsluha události "zacátek dokumentu"
     */     
    public void startDocument() throws SAXException {
        
        // ...
        
    }
    /**
     * Obsluha události "konec dokumentu"
     */     
    public void endDocument() throws SAXException {
        
        // ...
        
    }
    
    String tabs = "";
    
    /**
     * Obsluha události "zacátek elementu".
     * @param uri URI jmenného prostoru elementu (prázdné, pokud element není v žádném jmenném prostoru)
     * @param localName Lokální jméno elementu (vždy neprázdné)
     * @param qName Kvalifikované jméno (tj. prefix-uri + ':' + localName, pokud je element v nejakém jmenném prostoru, nebo localName, pokud element není v žádném jmenném prostoru)
     * @param atts Atributy elementu     
     */     
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        String attsStr = "";
        for ( int i = 0; i < atts.getLength(); i++ )    {
            attsStr += ( " " + atts.getQName(i) + "=\"" + atts.getValue(i) + "\"" );
        }
        
        System.out.printf("%s<%s%s%s>\n", tabs, qName, namespaces, attsStr);
        
        tabs += "\t";
        
        if ( namespaces.length()>0 )    {
            namespaces = "";
        }

    }
    /**
     * Obsluha události "konec elementu"
     * Parametry mají stejný význam jako u @see startElement     
     */     
    public void endElement(String uri, String localName, String qName) throws SAXException {

        tabs = tabs.substring(1);
        
        System.out.printf("%s</%s>\n", tabs, qName);

    }
    
    /**
     * Obsluha události "znaková data".
     * SAX parser muže znaková data dávkovat jak chce. Nelze tedy pocítat s tím, že je celý text dorucen v rámci jednoho volání.
     * Text je v poli (ch) na pozicich (start) az (start+length-1).
     * @param ch Pole se znakovými daty
     * @param start Index zacátku úseku platných znakových dat v poli.
     * @param length Délka úseku platných znakových dat v poli.
     */               
    public void characters(char[] ch, int start, int length) throws SAXException {

        // ...
        
    }
    
    String namespaces = "";
    
    /**
     * Obsluha události "deklarace jmenného prostoru".
     * @param prefix Prefix prirazený jmennému prostoru.
     * @param uri URI jmenného prostoru.
     */     
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        
        if ( prefix.length() == 0 ) {
            namespaces += " xmlns=\"" + uri + "\"";
        } else {
            namespaces += " xmlns:" + prefix + "=\"" + uri + "\"";
        }
        
    }

    /**
     * Obsluha události "konec platnosti deklarace jmenného prostoru".
     */     
    public void endPrefixMapping(String prefix) throws SAXException {
    
        // ...
    
    }

    /**
     * Obsluha události "ignorované bílé znaky".
     * Stejné chování a parametry jako @see characters     
     */     
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        
        // ...
        
    }

    /**
     * Obsluha události "instrukce pro zpracování".
     */         
    public void processingInstruction(String target, String data) throws SAXException {
      
      // ...
            
    }

    /**
     * Obsluha události "nezpracovaná entita"
     */         
    public void skippedEntity(String name) throws SAXException {
    
      // ...
    
    }
}