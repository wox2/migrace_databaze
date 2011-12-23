package x33eja.back;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import x33eja.model.Student;
import x33eja.sb.SchoolCenterLocal;

/**
 *
 * @author valekf1
 */
@ManagedBean(name="students")
@SessionScoped
public class StudentsBean implements Serializable {
    @EJB
    private SchoolCenterLocal schoolCenter;
    private Student student = null;


    /** Creates a new instance of StudentsBean */
    public StudentsBean() {
    }

    public List<Student> getAllStudents() {
        return schoolCenter.getAllStudents();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String newStudent() {
       student = new Student();
       return "student";
    }

    public String saveStudent() {
        schoolCenter.updateStudent(student);
        return "students";
    }

    public String editStudent(Student student) {
        this.student = student;
        return "student";
    }

    public void removeStudent(Student student) {
        schoolCenter.removeStudent(student);
    }
}
