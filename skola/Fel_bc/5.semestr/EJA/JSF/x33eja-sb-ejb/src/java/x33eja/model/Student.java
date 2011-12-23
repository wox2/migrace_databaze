package x33eja.model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("student")
@NamedQueries(
@NamedQuery(name = Student.Q_GET_ALL_STUDENTS, query = "SELECT s FROM Student s ORDER BY s.surname, s.firstName"))
public class Student extends Person {

    //Anotace Transient zajisti, ze nasledujici field nebude pouzit pro objektove - relacni mapovani
    @Transient
    public static final String Q_GET_ALL_STUDENTS = "Student.GET_ALL";

    @ManyToMany
    private List<Course> mEnrolledIn;

    @ManyToOne
    private Teacher mHasSupervisor;

    public List<Course> getEnrolledIn () {
        return mEnrolledIn;
    }

    public void setEnrolledIn (List<Course> val) {
        this.mEnrolledIn = val;
    }

    public Teacher getHasSupervisor () {
        return mHasSupervisor;
    }

    public void setHasSupervisor (Teacher val) {
        this.mHasSupervisor = val;
    }
}

