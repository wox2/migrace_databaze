
package cz.ctu.fee.murinrad.paymentserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for performCardTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="performCardTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentData" type="{http://paymentserver.murinrad.fee.ctu.cz/}cardPayment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "performCardTransaction", propOrder = {
    "paymentData"
})
public class PerformCardTransaction {

    protected CardPayment paymentData;

    /**
     * Gets the value of the paymentData property.
     * 
     * @return
     *     possible object is
     *     {@link CardPayment }
     *     
     */
    public CardPayment getPaymentData() {
        return paymentData;
    }

    /**
     * Sets the value of the paymentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardPayment }
     *     
     */
    public void setPaymentData(CardPayment value) {
        this.paymentData = value;
    }

}
