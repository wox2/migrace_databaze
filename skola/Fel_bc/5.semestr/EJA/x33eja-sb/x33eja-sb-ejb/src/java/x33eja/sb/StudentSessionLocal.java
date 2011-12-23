/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package x33eja.sb;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import x33eja.model.Course;
import x33eja.model.Person;
import x33eja.model.Student;

/**
 *
 * @author kremen
 */
@Local
public interface StudentSessionLocal {

    void init(String birthNumber);

    Person getCurrent();

    List<Course> getNotEnrolledCoursesForCurrent();

    Map<Student,Long> getStudentsWithCourseCount();

    void save(String birthNumber, String firstName, String surname);

    void addCourse(String courseId);

    void discard();
}
