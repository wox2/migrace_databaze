package y36xml;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;

public class SAX_podpora {

    public static void main(String[] args) {
        // Cesta ke zdrojovému XML dokumentu  
        String sourcePath = "src/objednavka.xml";

        try {
            
            // Vytvoríme instanci parseru.
            XMLReader parser = XMLReaderFactory.createXMLReader();
            
            // Vytvoríme vstupní proud XML dat.
            InputSource source = new InputSource(sourcePath);
            
            // Nastavíme náą vlastní content handler pro obsluhu SAX událostí.
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
    // Umožňuje zacílit místo v dokumentu, kde vznikla aktualní událost
    String text= new String();
    boolean podelement=false;
    boolean objednavkaMod=false;
    boolean chyba=false;
    Locator locator;
    String partialText;
    
    /**
     * Nastaví locator
     */     
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * Obsluha události "za�?átek dokumentu"
     */     
    public void startDocument() throws SAXException {
        
    }
    /**
     * Obsluha události "konec dokumentu"
     */     
    public void endDocument() throws SAXException {
         if(chyba==false) {System.out.println("Element objednavka je prazdny");};
        // ...
        
    }
    
    /**
     * Obsluha události "za�?átek elementu".
     * @param uri URI jmenného prostoru elementu (prázdné, pokud element není v žádném jmenném prostoru)
     * @param localName Lokální jméno elementu (vždy neprázdné)
     * @param qName Kvalifikované jméno (tj. prefix-uri + ':' + localName, pokud je element v nějakém jmenném prostoru, nebo localName, pokud element není v žádném jmenném prostoru)
     * @param atts Atributy elementu     
     */     
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
         // zjistim, kde jsem pomoci localName.equals(), nastavim mistoMod=true
        // ...

        if (localName.equals("objednavka")){objednavkaMod=true; podelement=false;}
        if (objednavkaMod){podelement=true;}

    }
    /**
     * Obsluha události "konec elementu"
     * Parametry mají stejný význam jako u @see startElement     
     */     
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (objednavkaMod==false & podelement==false){chyba=true;}
        
        // ...

    }
    
    /**
     * Obsluha události "znaková data".
     * SAX parser muľe znaková data dávkovat jak chce. Nelze tedy pocítat s tím, že je celý text dorucen v rámci jednoho volání.
     * Text je v poli (ch) na pozicich (start) az (start+length-1).
     * @param ch Pole se znakovými daty
     * @param start Index zacátku úseku platných znakových dat v poli.
     * @param length Délka úseku platných znakových dat v poli.
     */               
    public void characters(char[] ch, int start, int length) throws SAXException {
        // ...
        
    }
    
    /**
     * Obsluha události "deklarace jmenného prostoru".
     * @param prefix Prefix prirazený jmennému prostoru.
     * @param uri URI jmenného prostoru.
     */     
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        
        // ...
        
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