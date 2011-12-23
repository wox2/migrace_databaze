package y36xml;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;

/**
 * Upravte výstup předchozího řešení. Výstupem bude HTML tabulka, jejíž řádky
 * odpovídají položkám objednávky. Pro každou položku vypište její název,
 * jednotkovou cenu, počet kusů a celkovou cenu (jednotková cena * počet). Pod
 * tabulkou vypište celkovou cenu objednávky.
 */
public class Priklad3 {

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
        
        System.out.println("<html><head/><body>");
        
    }
    /**
     * Obsluha události "konec dokumentu"
     */     
    public void endDocument() throws SAXException {
        
        System.out.printf("<p><b>Cena celkem:</b> %d</p>\n", cenaCelkem);
        System.out.println("</body></html>");
        
    }
    
    String tabs = "";
    
    String chyba = "";
    
    int pocetPolozek = 0;
    
    boolean dorucovaciAdresaMod = false;
    boolean ulice = false;
    boolean cp = false;
    boolean psc = false;
    boolean mesto = false;
    
    boolean polozkaMod = false;
    boolean polozkaNazevMod = false;
    String nazev = "";
    boolean polozkaCenaMod = false;
    String cena = "";
    boolean polozkaKusuMod = false;
    String kusu = "";
    
    int cenaCelkem = 0;
    
    /**
     * Obsluha události "zacátek elementu".
     * @param uri URI jmenného prostoru elementu (prázdné, pokud element není v žádném jmenném prostoru)
     * @param localName Lokální jméno elementu (vždy neprázdné)
     * @param qName Kvalifikované jméno (tj. prefix-uri + ':' + localName, pokud je element v nejakém jmenném prostoru, nebo localName, pokud element není v žádném jmenném prostoru)
     * @param atts Atributy elementu     
     */     
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        if ( localName.equals("polozky") )  {
            System.out.println("<table>");
            System.out.println("<th><td>Název</td><td>Jednotková cena</td><td>Počet kusů</td><td>Cena celkem</td></th>");
        } else if ( localName.equals("polozka") )  {
            pocetPolozek++;
            polozkaMod = true;
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
        } else if ( polozkaMod == true )    {
            if ( localName.equals("nazev") )    {
                polozkaNazevMod = true;
            } else if ( localName.equals("jednotkova-cena") )    {
                polozkaCenaMod = true;
            } else if ( localName.equals("kusu") )    {
                polozkaKusuMod = true;
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
            System.out.println("</table>");
        } else if ( localName.equals("objednavka") && chyba.length()>0 ) {
            System.out.printf("\t<chyby-validace xmlns=\"http://ksi.ms.mff.cuni.cz/chyby\">\n%s\t</chyby-validace>\n", chyba);
        } else if ( localName.equals("polozka") )  {
            polozkaMod = false;
            int cenaZaPolozku = (new Integer(cena))*(new Integer(kusu));
            cenaCelkem += cenaZaPolozku;
            System.out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%d</td></tr>\n", nazev, cena, kusu, cenaZaPolozku);
            nazev = "";
            cena = "";
            kusu = "";
        } else if ( localName.equals("nazev") )    {
            polozkaNazevMod = false;
        } else if ( localName.equals("jednotkova-cena") )    {
            polozkaCenaMod = false;
        } else if ( localName.equals("kusu") )    {
            polozkaKusuMod = false;
        }

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

        if ( polozkaNazevMod == true )  {
            nazev += new String(ch, start, length);
        } else if ( polozkaCenaMod == true )  {
            cena += new String(ch, start, length);
        } else if ( polozkaKusuMod == true )  {
            kusu += new String(ch, start, length);
        }
        
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