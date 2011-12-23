package x33eja.model;

import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("teacher")
@NamedQueries(
@NamedQuery(name = Teacher.Q_GET_ALL_TEACHERS, query = "SELECT t FROM Teacher t"))
public class Teacher extends Person {

    @Transient
    public static final String Q_GET_ALL_TEACHERS = "Teacher.GET_ALL";

    @ManyToOne
    private Organization mIsMemberOf;

    @ManyToMany
    private List<Course> mIsTeacherOf;

    public Organization getIsMemberOf () {
        return mIsMemberOf;
    }

    public void setIsMemberOf (Organization val) {
        this.mIsMemberOf = val;
    }

    public List<Course> getIsTeacherOf () {
        return mIsTeacherOf;
    }

    public void setIsTeacherOf (List<Course> val) {
        this.mIsTeacherOf = val;
    }

    public void setIsTeacherOf (ArrayList<Course> val) {
        this.mIsTeacherOf = val;
    }


}

