package x33eja.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {

    private String firstName;

    private String surname;

    @Id
    private int birthNumber;

    public Person () {
    }

    public int getBirthNumber () {
        return birthNumber;
    }

    public void setBirthNumber (int val) {
        this.birthNumber = val;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String val) {
        this.firstName = val;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String val) {
        this.surname = val;
    }

}

