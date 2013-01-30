
package cz.ctu.fee.murinrad.paymentserver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for performBankTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="performBankTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentData" type="{http://paymentserver.murinrad.fee.ctu.cz/}bankPayment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "performBankTransaction", propOrder = {
    "paymentData"
})
public class PerformBankTransaction {

    protected BankPayment paymentData;

    /**
     * Gets the value of the paymentData property.
     * 
     * @return
     *     possible object is
     *     {@link BankPayment }
     *     
     */
    public BankPayment getPaymentData() {
        return paymentData;
    }

    /**
     * Sets the value of the paymentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankPayment }
     *     
     */
    public void setPaymentData(BankPayment value) {
        this.paymentData = value;
    }

}
