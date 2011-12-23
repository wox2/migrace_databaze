package x33eja.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {

    private String name;

    @Id
    private String courseId;

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
}

