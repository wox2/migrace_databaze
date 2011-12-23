import java.io.File;
import cz.XmlTester.TestJava;
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
public class TestDom extends TestJava {

    private static final String VSTUPNI_SOUBOR = "../data.xml";
    private static final String VYSTUPNI_SOUBOR = "../data_output.xml";

    public void run() {
        
        try {
            
            //DocumentBuilderFactory vytvĂˇĹ™Ă­ DOM parsery
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            //nebudeme validovat
            dbf.setValidating(false);

            //vytvoĹ™Ă­me si DOM parser
            DocumentBuilder builder = dbf.newDocumentBuilder();

            //parser zpracuje vstupnĂ­ soubor a vytvoĹ™Ă­ z nÄ›j strom DOM objektĹŻ
            Document doc = builder.parse(VSTUPNI_SOUBOR);

            //zpracujeme DOM strom
            processTree(doc);

            //TransformerFactory vytvĂˇĹ™Ă­ serializĂˇtory DOM stromĹŻ
            TransformerFactory tf = TransformerFactory.newInstance();

            //Transformer serializuje DOM stromy
            Transformer writer = tf.newTransformer();

            //nastavĂ­me kodovĂˇnĂ­
            writer.setOutputProperty(OutputKeys.ENCODING, "windows-1250");

            //spustĂ­me transformaci DOM stromu do XML dokumentu
            writer.transform(new DOMSource(doc), new StreamResult(new File(VYSTUPNI_SOUBOR)));


        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
    }

    /**
     * Zpracuje DOM strom
     */
    private static void processTree( Document doc ) {

        // Pokud je dorucovaci a adresa bydliste stejna tak se element dorucovaci adresa smaze
        slouceni( doc );
        
        // Převod atributu ks na Element pocetKS
        prevod( doc );
    }
    
    public static void prevod( Document doc ) {
        NodeList childs = doc.getElementsByTagName( "objednavka" );
          for ( int i = 0; i < childs.getLength( ); i++) { 
            NodeList newChilds = ( ( Element ) childs.item( 0 ) ).getElementsByTagName( "vyrobek" );
            for (int j = 0; j < newChilds.getLength( ); j++) {
                String ks = newChilds.item( j ).getAttributes( ).getNamedItem( "ks" ).getTextContent( );
                Element pocet_kusu = doc.createElement( "pocetKS" );
                Text value = doc.createTextNode( ks );
                pocet_kusu.appendChild( value );
                newChilds.item( j ).appendChild( pocet_kusu );
                newChilds.item( j ).getAttributes( ).removeNamedItem( "ks" );
            }
        }    
    }
    
    public static void slouceni( Document doc ) {
        NodeList childs = doc.getElementsByTagName( "objednavka" );

        for (int i = 0; i < childs.getLength( ); i++) {
            Element potomek = ( Element ) childs.item( i ) ;
            NodeList a = potomek.getElementsByTagName( "adresa_bydliste" ).item( 0 ).getChildNodes( );
            NodeList b = potomek.getElementsByTagName( "adresa_dorucovaci" ).item( 0 ).getChildNodes( );
            if ( a.item( 0 ).getTextContent( ).equals( b.item( 0 ).getTextContent( ) ) &&
                 a.item( 1 ).getTextContent( ).equals( b.item( 1 ).getTextContent( ) ) &&
                 a.item( 2 ).getTextContent( ).equals( b.item( 2 ).getTextContent( ) ) &&
                 a.item( 3 ).getTextContent( ).equals( b.item( 3 ).getTextContent( ) )
                    ) {
            Node parent = potomek.getElementsByTagName( "adresa_dorucovaci" ).item( 0 ).getParentNode( );
            parent.removeChild( potomek.getElementsByTagName( "adresa_dorucovaci" ).item( 0 ) );
            }
            
        }

    }
}
