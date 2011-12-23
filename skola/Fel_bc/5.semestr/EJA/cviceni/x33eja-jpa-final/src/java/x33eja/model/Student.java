package x33eja.model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("student")
public class Student extends Person {

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

