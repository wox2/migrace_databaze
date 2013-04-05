
package cz.ctu.fee.murinrad.ifaceserver;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.ctu.fee.murinrad.ifaceserver package. 
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

    private final static QName _PayForAReservationCard_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "payForAReservationCard");
    private final static QName _GetTicketsPerPassenger_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "getTicketsPerPassenger");
    private final static QName _SearchException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "SearchException");
    private final static QName _ChangeReservation_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "changeReservation");
    private final static QName _PaymentException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "PaymentException");
    private final static QName _ReservationException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "ReservationException");
    private final static QName _CancelReservationResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "cancelReservationResponse");
    private final static QName _FindFlightResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "findFlightResponse");
    private final static QName _PrintingException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "PrintingException");
    private final static QName _ChangeReservationResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "changeReservationResponse");
    private final static QName _FindFlight_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "findFlight");
    private final static QName _GetFreeSeatsForFlightResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "getFreeSeatsForFlightResponse");
    private final static QName _CancelReservation_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "cancelReservation");
    private final static QName _GetTicketsPerPassengerResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "getTicketsPerPassengerResponse");
    private final static QName _PayForAReservationCash_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "payForAReservationCash");
    private final static QName _ChangeReservationException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "ChangeReservationException");
    private final static QName _PrintTicketResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "printTicketResponse");
    private final static QName _PayForAReservationCardResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "payForAReservationCardResponse");
    private final static QName _BookFlight_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "bookFlight");
    private final static QName _PayForAReservationCashResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "payForAReservationCashResponse");
    private final static QName _PrintTicket_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "printTicket");
    private final static QName _BookFlightResponse_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "bookFlightResponse");
    private final static QName _GetFreeSeatsForFlight_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "getFreeSeatsForFlight");
    private final static QName _CancelReservationException_QNAME = new QName("http://interfaceserver.murinrad.fee.ctu.cz/", "CancelReservationException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.ctu.fee.murinrad.ifaceserver
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PayForAReservationCard }
     * 
     */
    public PayForAReservationCard createPayForAReservationCard() {
        return new PayForAReservationCard();
    }

    /**
     * Create an instance of {@link GetTicketsPerPassenger }
     * 
     */
    public GetTicketsPerPassenger createGetTicketsPerPassenger() {
        return new GetTicketsPerPassenger();
    }

    /**
     * Create an instance of {@link SearchException }
     * 
     */
    public SearchException createSearchException() {
        return new SearchException();
    }

    /**
     * Create an instance of {@link ChangeReservation }
     * 
     */
    public ChangeReservation createChangeReservation() {
        return new ChangeReservation();
    }

    /**
     * Create an instance of {@link PaymentException }
     * 
     */
    public PaymentException createPaymentException() {
        return new PaymentException();
    }

    /**
     * Create an instance of {@link ReservationException }
     * 
     */
    public ReservationException createReservationException() {
        return new ReservationException();
    }

    /**
     * Create an instance of {@link CancelReservationResponse }
     * 
     */
    public CancelReservationResponse createCancelReservationResponse() {
        return new CancelReservationResponse();
    }

    /**
     * Create an instance of {@link FindFlightResponse }
     * 
     */
    public FindFlightResponse createFindFlightResponse() {
        return new FindFlightResponse();
    }

    /**
     * Create an instance of {@link PrintingException }
     * 
     */
    public PrintingException createPrintingException() {
        return new PrintingException();
    }

    /**
     * Create an instance of {@link ChangeReservationResponse }
     * 
     */
    public ChangeReservationResponse createChangeReservationResponse() {
        return new ChangeReservationResponse();
    }

    /**
     * Create an instance of {@link FindFlight }
     * 
     */
    public FindFlight createFindFlight() {
        return new FindFlight();
    }

    /**
     * Create an instance of {@link GetFreeSeatsForFlightResponse }
     * 
     */
    public GetFreeSeatsForFlightResponse createGetFreeSeatsForFlightResponse() {
        return new GetFreeSeatsForFlightResponse();
    }

    /**
     * Create an instance of {@link CancelReservation }
     * 
     */
    public CancelReservation createCancelReservation() {
        return new CancelReservation();
    }

    /**
     * Create an instance of {@link PayForAReservationCash }
     * 
     */
    public PayForAReservationCash createPayForAReservationCash() {
        return new PayForAReservationCash();
    }

    /**
     * Create an instance of {@link GetTicketsPerPassengerResponse }
     * 
     */
    public GetTicketsPerPassengerResponse createGetTicketsPerPassengerResponse() {
        return new GetTicketsPerPassengerResponse();
    }

    /**
     * Create an instance of {@link PrintTicketResponse }
     * 
     */
    public PrintTicketResponse createPrintTicketResponse() {
        return new PrintTicketResponse();
    }

    /**
     * Create an instance of {@link ChangeReservationException }
     * 
     */
    public ChangeReservationException createChangeReservationException() {
        return new ChangeReservationException();
    }

    /**
     * Create an instance of {@link PayForAReservationCardResponse }
     * 
     */
    public PayForAReservationCardResponse createPayForAReservationCardResponse() {
        return new PayForAReservationCardResponse();
    }

    /**
     * Create an instance of {@link BookFlight }
     * 
     */
    public BookFlight createBookFlight() {
        return new BookFlight();
    }

    /**
     * Create an instance of {@link PayForAReservationCashResponse }
     * 
     */
    public PayForAReservationCashResponse createPayForAReservationCashResponse() {
        return new PayForAReservationCashResponse();
    }

    /**
     * Create an instance of {@link PrintTicket }
     * 
     */
    public PrintTicket createPrintTicket() {
        return new PrintTicket();
    }

    /**
     * Create an instance of {@link BookFlightResponse }
     * 
     */
    public BookFlightResponse createBookFlightResponse() {
        return new BookFlightResponse();
    }

    /**
     * Create an instance of {@link GetFreeSeatsForFlight }
     * 
     */
    public GetFreeSeatsForFlight createGetFreeSeatsForFlight() {
        return new GetFreeSeatsForFlight();
    }

    /**
     * Create an instance of {@link CancelReservationException }
     * 
     */
    public CancelReservationException createCancelReservationException() {
        return new CancelReservationException();
    }

    /**
     * Create an instance of {@link CardData }
     * 
     */
    public CardData createCardData() {
        return new CardData();
    }

    /**
     * Create an instance of {@link Passenger }
     * 
     */
    public Passenger createPassenger() {
        return new Passenger();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link FlightCollection }
     * 
     */
    public FlightCollection createFlightCollection() {
        return new FlightCollection();
    }

    /**
     * Create an instance of {@link TicketPackage }
     * 
     */
    public TicketPackage createTicketPackage() {
        return new TicketPackage();
    }

    /**
     * Create an instance of {@link SeatCollection }
     * 
     */
    public SeatCollection createSeatCollection() {
        return new SeatCollection();
    }

    /**
     * Create an instance of {@link Seat }
     * 
     */
    public Seat createSeat() {
        return new Seat();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForAReservationCard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "payForAReservationCard")
    public JAXBElement<PayForAReservationCard> createPayForAReservationCard(PayForAReservationCard value) {
        return new JAXBElement<PayForAReservationCard>(_PayForAReservationCard_QNAME, PayForAReservationCard.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsPerPassenger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "getTicketsPerPassenger")
    public JAXBElement<GetTicketsPerPassenger> createGetTicketsPerPassenger(GetTicketsPerPassenger value) {
        return new JAXBElement<GetTicketsPerPassenger>(_GetTicketsPerPassenger_QNAME, GetTicketsPerPassenger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "SearchException")
    public JAXBElement<SearchException> createSearchException(SearchException value) {
        return new JAXBElement<SearchException>(_SearchException_QNAME, SearchException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "changeReservation")
    public JAXBElement<ChangeReservation> createChangeReservation(ChangeReservation value) {
        return new JAXBElement<ChangeReservation>(_ChangeReservation_QNAME, ChangeReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "PaymentException")
    public JAXBElement<PaymentException> createPaymentException(PaymentException value) {
        return new JAXBElement<PaymentException>(_PaymentException_QNAME, PaymentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "ReservationException")
    public JAXBElement<ReservationException> createReservationException(ReservationException value) {
        return new JAXBElement<ReservationException>(_ReservationException_QNAME, ReservationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "cancelReservationResponse")
    public JAXBElement<CancelReservationResponse> createCancelReservationResponse(CancelReservationResponse value) {
        return new JAXBElement<CancelReservationResponse>(_CancelReservationResponse_QNAME, CancelReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "findFlightResponse")
    public JAXBElement<FindFlightResponse> createFindFlightResponse(FindFlightResponse value) {
        return new JAXBElement<FindFlightResponse>(_FindFlightResponse_QNAME, FindFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "PrintingException")
    public JAXBElement<PrintingException> createPrintingException(PrintingException value) {
        return new JAXBElement<PrintingException>(_PrintingException_QNAME, PrintingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeReservationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "changeReservationResponse")
    public JAXBElement<ChangeReservationResponse> createChangeReservationResponse(ChangeReservationResponse value) {
        return new JAXBElement<ChangeReservationResponse>(_ChangeReservationResponse_QNAME, ChangeReservationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "findFlight")
    public JAXBElement<FindFlight> createFindFlight(FindFlight value) {
        return new JAXBElement<FindFlight>(_FindFlight_QNAME, FindFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeSeatsForFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "getFreeSeatsForFlightResponse")
    public JAXBElement<GetFreeSeatsForFlightResponse> createGetFreeSeatsForFlightResponse(GetFreeSeatsForFlightResponse value) {
        return new JAXBElement<GetFreeSeatsForFlightResponse>(_GetFreeSeatsForFlightResponse_QNAME, GetFreeSeatsForFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "cancelReservation")
    public JAXBElement<CancelReservation> createCancelReservation(CancelReservation value) {
        return new JAXBElement<CancelReservation>(_CancelReservation_QNAME, CancelReservation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsPerPassengerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "getTicketsPerPassengerResponse")
    public JAXBElement<GetTicketsPerPassengerResponse> createGetTicketsPerPassengerResponse(GetTicketsPerPassengerResponse value) {
        return new JAXBElement<GetTicketsPerPassengerResponse>(_GetTicketsPerPassengerResponse_QNAME, GetTicketsPerPassengerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForAReservationCash }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "payForAReservationCash")
    public JAXBElement<PayForAReservationCash> createPayForAReservationCash(PayForAReservationCash value) {
        return new JAXBElement<PayForAReservationCash>(_PayForAReservationCash_QNAME, PayForAReservationCash.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeReservationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "ChangeReservationException")
    public JAXBElement<ChangeReservationException> createChangeReservationException(ChangeReservationException value) {
        return new JAXBElement<ChangeReservationException>(_ChangeReservationException_QNAME, ChangeReservationException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "printTicketResponse")
    public JAXBElement<PrintTicketResponse> createPrintTicketResponse(PrintTicketResponse value) {
        return new JAXBElement<PrintTicketResponse>(_PrintTicketResponse_QNAME, PrintTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForAReservationCardResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "payForAReservationCardResponse")
    public JAXBElement<PayForAReservationCardResponse> createPayForAReservationCardResponse(PayForAReservationCardResponse value) {
        return new JAXBElement<PayForAReservationCardResponse>(_PayForAReservationCardResponse_QNAME, PayForAReservationCardResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "bookFlight")
    public JAXBElement<BookFlight> createBookFlight(BookFlight value) {
        return new JAXBElement<BookFlight>(_BookFlight_QNAME, BookFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayForAReservationCashResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "payForAReservationCashResponse")
    public JAXBElement<PayForAReservationCashResponse> createPayForAReservationCashResponse(PayForAReservationCashResponse value) {
        return new JAXBElement<PayForAReservationCashResponse>(_PayForAReservationCashResponse_QNAME, PayForAReservationCashResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "printTicket")
    public JAXBElement<PrintTicket> createPrintTicket(PrintTicket value) {
        return new JAXBElement<PrintTicket>(_PrintTicket_QNAME, PrintTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookFlightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "bookFlightResponse")
    public JAXBElement<BookFlightResponse> createBookFlightResponse(BookFlightResponse value) {
        return new JAXBElement<BookFlightResponse>(_BookFlightResponse_QNAME, BookFlightResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreeSeatsForFlight }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "getFreeSeatsForFlight")
    public JAXBElement<GetFreeSeatsForFlight> createGetFreeSeatsForFlight(GetFreeSeatsForFlight value) {
        return new JAXBElement<GetFreeSeatsForFlight>(_GetFreeSeatsForFlight_QNAME, GetFreeSeatsForFlight.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelReservationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaceserver.murinrad.fee.ctu.cz/", name = "CancelReservationException")
    public JAXBElement<CancelReservationException> createCancelReservationException(CancelReservationException value) {
        return new JAXBElement<CancelReservationException>(_CancelReservationException_QNAME, CancelReservationException.class, null, value);
    }

}
