/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package x33eja.sb;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import x33eja.model.Course;
import x33eja.model.Student;
import x33eja.model.Teacher;

/**
 *
 * @author blcha
 */
@Local
public interface SchoolCenterLocal {

    Course getCourse(String course);

    List<Course> getAllCourses();

    List<Course> getNotEnrolledCoursesForCurrent(Student student);

    Student getStudent(int birthNumber);

    void updateStudent(Student student);

    Map<Student, Long> getStudentsWithCourseCount();

    public List<Student> getAllStudents();

    public void removeStudent(Student student);

    public List<Teacher> getAllTeachers();

    public Teacher getTeacher(int birthNumber);
}
