/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author já
 */
public class MujHashMap {
 protected static void createExample(){
 Map m = new HashMap();        // vytvoří se asociativní kontejner
m.put("txt",  "text/plain");  // vložení dvojic klíč-hodnota
m.put("html", "text/html");
m.put("jpg",  "image/jpeg");
m.put("mpg",  "video/mpeg");

// nyní pomocí klíče přistupujeme k hodnotám
System.out.println("Přípona .jpg má typ: " + m.get("jpg"));  // vypíše "image/jpeg"
System.out.println("Přípona .gif má typ: " + m.get("gif"));  // vypíše "null" (nebylo nalezeno)


 }
}
