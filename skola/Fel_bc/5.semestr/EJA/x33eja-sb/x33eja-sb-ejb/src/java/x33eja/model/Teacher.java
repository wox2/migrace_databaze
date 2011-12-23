package x33eja.model;

import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class Teacher extends Person {

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

