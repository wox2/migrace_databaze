
package cz.ctu.fee.murinrad.ifaceserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrival" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="arrivalAPT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseFare" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="departAPT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flight", namespace = "http://bookingserver.murinrad.fee.ctu.cz/", propOrder = {
    "arrival",
    "arrivalAPT",
    "baseFare",
    "departAPT",
    "departure",
    "id",
    "fNumber"
})
public class Flight {

    protected Long arrival;
    protected String arrivalAPT;
    protected Integer baseFare;
    protected String departAPT;
    protected Long departure;
    protected Integer id;
    protected String fNumber;

    /**
     * Gets the value of the arrival property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setArrival(Long value) {
        this.arrival = value;
    }

    /**
     * Gets the value of the arrivalAPT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalAPT() {
        return arrivalAPT;
    }

    /**
     * Sets the value of the arrivalAPT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalAPT(String value) {
        this.arrivalAPT = value;
    }

    /**
     * Gets the value of the baseFare property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBaseFare() {
        return baseFare;
    }

    /**
     * Sets the value of the baseFare property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBaseFare(Integer value) {
        this.baseFare = value;
    }

    /**
     * Gets the value of the departAPT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartAPT() {
        return departAPT;
    }

    /**
     * Sets the value of the departAPT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartAPT(String value) {
        this.departAPT = value;
    }

    /**
     * Gets the value of the departure property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDeparture(Long value) {
        this.departure = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the fNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFNumber() {
        return fNumber;
    }

    /**
     * Sets the value of the fNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFNumber(String value) {
        this.fNumber = value;
    }

}
