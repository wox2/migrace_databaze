
package cz.ctu.fee.murinrad.ifaceserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payForAReservationCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="payForAReservationCard">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticketID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="amountDue" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cardData" type="{http://interfaceserver.murinrad.fee.ctu.cz/}cardData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payForAReservationCard", propOrder = {
    "ticketID",
    "amountDue",
    "cardData"
})
public class PayForAReservationCard {

    protected Integer ticketID;
    protected Double amountDue;
    protected CardData cardData;

    /**
     * Gets the value of the ticketID property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTicketID() {
        return ticketID;
    }

    /**
     * Sets the value of the ticketID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTicketID(Integer value) {
        this.ticketID = value;
    }

    /**
     * Gets the value of the amountDue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmountDue() {
        return amountDue;
    }

    /**
     * Sets the value of the amountDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmountDue(Double value) {
        this.amountDue = value;
    }

    /**
     * Gets the value of the cardData property.
     * 
     * @return
     *     possible object is
     *     {@link CardData }
     *     
     */
    public CardData getCardData() {
        return cardData;
    }

    /**
     * Sets the value of the cardData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardData }
     *     
     */
    public void setCardData(CardData value) {
        this.cardData = value;
    }

}
