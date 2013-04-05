/*
* Torzo pro SAX
*/

import cz.XmlTester.TestJava;
import org.xml.sax.helpers.DefaultHandler;

public class TestSax extends TestJava {
	
	/**
	* main funkce
	*/
	public void run() {
		
		final String filename = "../data.xml";
		
		// tady zavolat vse, co je potreba pro demonstraci funkcnosti
		
	}
	
}


class MyHandler extends DefaultHandler {
	
	// vlastni zpracovani udalosti od SAX Parseru
	
}
