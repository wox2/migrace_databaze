/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.ctu.fee.murinrad.aos.common.datacontainers;

import javax.xml.bind.annotation.XmlElement;

/**
 * A passenger. A passenger represents a unique person and is identified by an ID. From the outside world he is 
 * identified by the idDocNumber which is his passport/ID card number.
 * @author Rado
 */
public class Passenger {
    String name,surname,idDocNumber,sex,title;
    Integer id;

    public Passenger(String name, String surname, String idDocNumber, String sex, String title, Integer id) {
        this.name = name;
        this.surname = surname;
        this.idDocNumber = idDocNumber;
        this.sex = sex;
        this.title = title;
        this.id = id;
    }
    public Passenger(String name, String surname, String idDocNumber, String sex, String title  ) {
        this.name = name;
        this.surname = surname;
        this.idDocNumber = idDocNumber;
        this.sex = sex;
        this.title = title;
        this.id = -1;
    }

    public Passenger() {
    }
    
    
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getSurname() {
        return surname;
    }

    public String getTitle() {
        return title;
    }
    
    

    public Integer getId() {
        return id;
    }
@XmlElement(required = true)
    public String getIdDocNumber() {
        return idDocNumber;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append('\n').append("Passenger: ").append(name).append(" ")
                .append(surname).append('\n').append("Sex: ")
                .append(sex).append('\n').append("Document ID: ")
                .append(idDocNumber).append('\n');
        return sb.toString();
    }
    
    
    
    
    
}
