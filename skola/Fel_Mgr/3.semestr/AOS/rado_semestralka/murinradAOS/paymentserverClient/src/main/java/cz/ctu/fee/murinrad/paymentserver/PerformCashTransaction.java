
package cz.ctu.fee.murinrad.paymentserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for performCashTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="performCashTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentData" type="{http://paymentserver.murinrad.fee.ctu.cz/}cashPayment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "performCashTransaction", propOrder = {
    "paymentData"
})
public class PerformCashTransaction {

    protected CashPayment paymentData;

    /**
     * Gets the value of the paymentData property.
     * 
     * @return
     *     possible object is
     *     {@link CashPayment }
     *     
     */
    public CashPayment getPaymentData() {
        return paymentData;
    }

    /**
     * Sets the value of the paymentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashPayment }
     *     
     */
    public void setPaymentData(CashPayment value) {
        this.paymentData = value;
    }

}
