
package cz.ctu.fee.murinrad.paymentserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.ctu.fee.murinrad.paymentserver package. 
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

    private final static QName _PaymentException_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "PaymentException");
    private final static QName _PerformCardTransactionResponse_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performCardTransactionResponse");
    private final static QName _PerformCashTransaction_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performCashTransaction");
    private final static QName _PerformCashTransactionResponse_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performCashTransactionResponse");
    private final static QName _PerformCardTransaction_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performCardTransaction");
    private final static QName _DatabaseException_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "DatabaseException");
    private final static QName _PerformBankTransaction_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performBankTransaction");
    private final static QName _PerformBankTransactionResponse_QNAME = new QName("http://paymentserver.murinrad.fee.ctu.cz/", "performBankTransactionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.ctu.fee.murinrad.paymentserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PerformCashTransactionResponse }
     * 
     */
    public PerformCashTransactionResponse createPerformCashTransactionResponse() {
        return new PerformCashTransactionResponse();
    }

    /**
     * Create an instance of {@link PerformCashTransaction }
     * 
     */
    public PerformCashTransaction createPerformCashTransaction() {
        return new PerformCashTransaction();
    }

    /**
     * Create an instance of {@link PerformCardTransactionResponse }
     * 
     */
    public PerformCardTransactionResponse createPerformCardTransactionResponse() {
        return new PerformCardTransactionResponse();
    }

    /**
     * Create an instance of {@link PaymentException }
     * 
     */
    public PaymentException createPaymentException() {
        return new PaymentException();
    }

    /**
     * Create an instance of {@link PerformBankTransactionResponse }
     * 
     */
    public PerformBankTransactionResponse createPerformBankTransactionResponse() {
        return new PerformBankTransactionResponse();
    }

    /**
     * Create an instance of {@link PerformBankTransaction }
     * 
     */
    public PerformBankTransaction createPerformBankTransaction() {
        return new PerformBankTransaction();
    }

    /**
     * Create an instance of {@link DatabaseException }
     * 
     */
    public DatabaseException createDatabaseException() {
        return new DatabaseException();
    }

    /**
     * Create an instance of {@link PerformCardTransaction }
     * 
     */
    public PerformCardTransaction createPerformCardTransaction() {
        return new PerformCardTransaction();
    }

    /**
     * Create an instance of {@link CashPayment }
     * 
     */
    public CashPayment createCashPayment() {
        return new CashPayment();
    }

    /**
     * Create an instance of {@link BankPayment }
     * 
     */
    public BankPayment createBankPayment() {
        return new BankPayment();
    }

    /**
     * Create an instance of {@link CardPayment }
     * 
     */
    public CardPayment createCardPayment() {
        return new CardPayment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "PaymentException")
    public JAXBElement<PaymentException> createPaymentException(PaymentException value) {
        return new JAXBElement<PaymentException>(_PaymentException_QNAME, PaymentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformCardTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performCardTransactionResponse")
    public JAXBElement<PerformCardTransactionResponse> createPerformCardTransactionResponse(PerformCardTransactionResponse value) {
        return new JAXBElement<PerformCardTransactionResponse>(_PerformCardTransactionResponse_QNAME, PerformCardTransactionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformCashTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performCashTransaction")
    public JAXBElement<PerformCashTransaction> createPerformCashTransaction(PerformCashTransaction value) {
        return new JAXBElement<PerformCashTransaction>(_PerformCashTransaction_QNAME, PerformCashTransaction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformCashTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performCashTransactionResponse")
    public JAXBElement<PerformCashTransactionResponse> createPerformCashTransactionResponse(PerformCashTransactionResponse value) {
        return new JAXBElement<PerformCashTransactionResponse>(_PerformCashTransactionResponse_QNAME, PerformCashTransactionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformCardTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performCardTransaction")
    public JAXBElement<PerformCardTransaction> createPerformCardTransaction(PerformCardTransaction value) {
        return new JAXBElement<PerformCardTransaction>(_PerformCardTransaction_QNAME, PerformCardTransaction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "DatabaseException")
    public JAXBElement<DatabaseException> createDatabaseException(DatabaseException value) {
        return new JAXBElement<DatabaseException>(_DatabaseException_QNAME, DatabaseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformBankTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performBankTransaction")
    public JAXBElement<PerformBankTransaction> createPerformBankTransaction(PerformBankTransaction value) {
        return new JAXBElement<PerformBankTransaction>(_PerformBankTransaction_QNAME, PerformBankTransaction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PerformBankTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://paymentserver.murinrad.fee.ctu.cz/", name = "performBankTransactionResponse")
    public JAXBElement<PerformBankTransactionResponse> createPerformBankTransactionResponse(PerformBankTransactionResponse value) {
        return new JAXBElement<PerformBankTransactionResponse>(_PerformBankTransactionResponse_QNAME, PerformBankTransactionResponse.class, null, value);
    }

}
