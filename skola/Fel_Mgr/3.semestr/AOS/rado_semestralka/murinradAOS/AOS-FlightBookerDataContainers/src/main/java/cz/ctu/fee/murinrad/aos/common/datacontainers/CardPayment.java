/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import javax.xml.bind.annotation.XmlElement;

/**
 * Represents a card payment.
 * @author murinr
 */
public class CardPayment extends Payment{
   String expiration;
    Integer cardNumber, CVC;
    String owner;

    public CardPayment(String epirationString, Integer cNumber, Integer secCode, Double amountDue, Integer ticketID,String owner) {
        super(amountDue, ticketID);
        this.expiration = epirationString;
        this.cardNumber = cNumber;
        this.CVC = secCode;
        this.owner=owner;
    }

    public CardPayment() {
    }

       public void setCVC(Integer secCode) {
        this.CVC = secCode;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

  

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @XmlElement(required = true)
    public Integer getCVC() {
        return CVC;
    }
    @XmlElement(required = true)
    public Integer getCardNumber() {
        return cardNumber;
    }

  
    @XmlElement(required = true)
    public String getExpiration() {
        return expiration;
    }
    @XmlElement(required = true)
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    

   

    public CardPayment(Double amountDue, Integer ticketID) {
        super(amountDue, ticketID);
    }
    
    
    
    
    
    
}
