package x33eja.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {

    /**
     * Protoze byl primarni klic zvolen nevhodne jako datum narozeni, musi
     * byt rucne osetreno, aby se neulozila duplicita, jinak nastane vyjimka:
     * java.sql.SQLIntegrityConstraintViolationException
     * Spravne generovani klice je naznaceno v entite Department
     */

    @Id
    private int birthNumber;

    private String firstName;

    private String surname;

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

