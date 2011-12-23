package y36xml;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;

/**
 * Implementujte v předchozím řešení validaci vstupního XML dokumentu:
 *  - každá objednávka musí mít alespoň jednu položku
 *  - doručovací adresa musí obsahovat ulici, číslo popisné, psč a město
 *    v libovolném pořadí
 *  - stav objednávky musí mít hodnotu odeslána, doručena nebo zrušena
 * Chybové hlášení připojte jako element chyba-validace na konec XML dokumentu.
 * Pro každou chybu vypište místo v XML dokumentu (řádek, sloupec), kde k chybě
 * došlo, a text, který událost blíže popisuje.
 */ 
public class Priklad2 {

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
    
    String chyba = "";
    
    int pocetPolozek = 0;
    
    boolean dorucovaciAdresaMod = false;
    boolean ulice = false;
    boolean cp = false;
    boolean psc = false;
    boolean mesto = false;
    
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
        
        if ( localName.equals("polozka") )  {
            pocetPolozek++;
        } else if ( localName.equals("dorucovaci-adresa") ) {
            dorucovaciAdresaMod = true;
        } else if ( localName.equals("objednavka") ) {
            int i = atts.getIndex("stav");
            if ( i>=0 && !atts.getValue(i).equals("odeslana") && !atts.getValue(i).equals("dorucena") && !atts.getValue(i).equals("zrusena") )  {
                chyba += "\t\t<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Atribut stav elementu objednavka má nepovolenou hodnotu " + atts.getValue(i) + ". Musí mít jednu z následujících hodnot: odeslana, dorucena, zrusena.</chyba>\n";
            }
        } else if ( dorucovaciAdresaMod == true )   {
            if ( localName.equals("ulice") )    {
                ulice = true;
            } else if ( localName.equals("cp") )    {
                cp = true;
            } else if ( localName.equals("psc") )    {
                psc = true;
            } else if ( localName.equals("mesto") )    {
                mesto = true;
            }                
        }

    }
    /**
     * Obsluha události "konec elementu"
     * Parametry mají stejný význam jako u @see startElement     
     */     
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ( localName.equals("dorucovaci-adresa") ) {
            dorucovaciAdresaMod = false;
            if ( !ulice )    {
                chyba += "\t\t<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Element dorucovaci-adresa musí obsahovat element ulice.</chyba>\n";
            }
            if ( !cp )    {
                chyba += "\t\t<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Element dorucovaci-adresa musí obsahovat element cp.</chyba>\n";
            }
            if ( !psc )    {
                chyba += "\t\t<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Element dorucovaci-adresa musí obsahovat element psc.</chyba>\n";
            }
            if ( !mesto )    {
                chyba += "\t\t<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Element dorucovaci-adresa musí obsahovat element mesto.</chyba>\n";
            }
        } else if ( localName.equals("polozky") ) {
            if ( pocetPolozek==0 )  {
                chyba += "<chyba radek=\"" + locator.getLineNumber() + "\" sloupec=\"" + locator.getColumnNumber() + "\">Element polozky musí obsahovat alespon jeden element polozka.</chyba>\n";
            }
        } else if ( localName.equals("objednavka") && chyba.length()>0 ) {
            System.out.printf("\t<chyby-validace xmlns=\"http://ksi.ms.mff.cuni.cz/chyby\">\n%s\t</chyby-validace>\n", chyba);
        }
        
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