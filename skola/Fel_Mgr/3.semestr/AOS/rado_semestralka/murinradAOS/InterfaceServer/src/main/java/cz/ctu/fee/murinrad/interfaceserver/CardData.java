/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.interfaceserver;

/**
 * Card data
 * represents a credit card
 * @author Radovan Murin
 */
public class CardData {
    String owner,expiration;
    Integer cvc,number;

    public CardData(String owner, Integer number, Integer cvc, String expiration) {
        this.owner = owner;
        this.number = number;
        this.cvc = cvc;
        this.expiration = expiration;
    }

    public CardData() {
    }

    public Integer getCvc() {
        return cvc;
    }

    public String getExpiration() {
        return expiration;
    }

    public Integer getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public void setCvc(Integer cvc) {
        this.cvc = cvc;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    
    
}
