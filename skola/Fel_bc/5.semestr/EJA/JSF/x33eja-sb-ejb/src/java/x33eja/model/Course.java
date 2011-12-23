package x33eja.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries(
@NamedQuery(name = Course.Q_GET_ALL_COURSES, query = "SELECT c FROM Course c"))
public class Course {

    //Anotace Transient zajisti, ze nasledujici field nebude pouzit pro objektove - relacni mapovani
    @Transient
    public static final String Q_GET_ALL_COURSES = "Course.GET_ALL";

    @Id
    private String courseId;

    private String name;

    @ManyToMany(mappedBy="mIsTeacherOf")
    private List<Teacher> mHasTeacher;

    public Course () {
    }

    public String getCourseId () {
        return courseId;
    }

    public void setCourseId (String val) {
        this.courseId = val;
    }

    public List<Teacher> getHasTeacher () {
        return mHasTeacher;
    }

    public void setHasTeacher (List<Teacher> val) {
        this.mHasTeacher = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if ((this.courseId == null) ? (other.courseId != null) : !this.courseId.equals(other.courseId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.courseId != null ? this.courseId.hashCode() : 0);
        return hash;
    }

    
}

