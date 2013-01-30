
package cz.ctu.fee.murinrad.printingserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.ctu.fee.murinrad.printingserver package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PrintTicketResponse_QNAME = new QName("http://printingserver.murinrad.fee.ctu.cz/", "printTicketResponse");
    private final static QName _PrintTicket_QNAME = new QName("http://printingserver.murinrad.fee.ctu.cz/", "printTicket");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.ctu.fee.murinrad.printingserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrintTicketResponse }
     * 
     */
    public PrintTicketResponse createPrintTicketResponse() {
        return new PrintTicketResponse();
    }

    /**
     * Create an instance of {@link PrintTicket }
     * 
     */
    public PrintTicket createPrintTicket() {
        return new PrintTicket();
    }

    /**
     * Create an instance of {@link Passenger }
     * 
     */
    public Passenger createPassenger() {
        return new Passenger();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printingserver.murinrad.fee.ctu.cz/", name = "printTicketResponse")
    public JAXBElement<PrintTicketResponse> createPrintTicketResponse(PrintTicketResponse value) {
        return new JAXBElement<PrintTicketResponse>(_PrintTicketResponse_QNAME, PrintTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://printingserver.murinrad.fee.ctu.cz/", name = "printTicket")
    public JAXBElement<PrintTicket> createPrintTicket(PrintTicket value) {
        return new JAXBElement<PrintTicket>(_PrintTicket_QNAME, PrintTicket.class, null, value);
    }

}
