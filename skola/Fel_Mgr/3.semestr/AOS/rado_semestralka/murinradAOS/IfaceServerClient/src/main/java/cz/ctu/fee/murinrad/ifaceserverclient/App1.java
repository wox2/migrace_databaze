package cz.ctu.fee.murinrad.ifaceserverclient;
import org.apache.cxf.tools.wsdlto.WSDLToJava;
/**
 * Hello world!
 *
 */
public class App1 
{
    public static void main( String[] args )
    {
        
       WSDLToJava.main(new String[] {
                "-client",
                "-d",
                "src/main/java",
              
                "-p",
                
                "cz.ctu.fee.murinrad.ifaceserver",
                "http://murko2-pc:8080/InterfaceServer/InterfaceServiceService?wsdl"
        });
        System.out.println("Done!");
    
    }
}
