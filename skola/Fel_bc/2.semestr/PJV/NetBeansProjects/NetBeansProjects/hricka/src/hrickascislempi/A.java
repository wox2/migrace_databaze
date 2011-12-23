/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hrickascislempi;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author já
 */
public class A extends B{
{ System.out.println("2");}
static{ System.out.println("5");}
A(){
System.out.println("T");
}

public static void main(String[] args) {
try {
InetAddress a = InetAddress.getByName("http://www.cvut.cz");
byte[] b = a.getAddress();
System.out.println(b[0] + "." + b[1] + "." + b[2] + "." + b[3]);
} catch (UnknownHostException ex) {
ex.printStackTrace();
};
}
}
class B{
{ System.out.println("8");}
static{ System.out.println("4");}
B(){
System.out.println("F");
}

class C{

}
}

